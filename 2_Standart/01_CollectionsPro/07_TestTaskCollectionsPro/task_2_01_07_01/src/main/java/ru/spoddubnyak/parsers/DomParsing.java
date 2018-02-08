package ru.spoddubnyak.parsers;


import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import ru.spoddubnyak.model.Order;
import ru.spoddubnyak.model.Parsers;
import ru.spoddubnyak.model.XMLAttributes;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.Set;

/**
 * Class DomParsing parsing method DOM.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 05.02.2018
 */
public class DomParsing extends Parsers {
    @Override
    public Set<Order> parsingTo() {
        try {
            DocumentBuilder e = DocumentBuilderFactory.newInstance().newDocumentBuilder();
            Document document = e.parse(super.getFilePath());
            Element root = document.getDocumentElement();
            NodeList books = root.getChildNodes();
            for (int i = 0; i < books.getLength(); ++i) {
                Node book = books.item(i);
                if (book.getNodeName().equals(XMLAttributes.BOOK_ADD.name())) {
                    String name = book.getAttributes().getNamedItem(XMLAttributes.NAME_BOOK.getAtributes()).getTextContent();
                    String operation = book.getAttributes().getNamedItem(XMLAttributes.OPERATION.getAtributes()).getTextContent();
                    double price = Double.parseDouble(book.getAttributes().getNamedItem(XMLAttributes.PRICE.getAtributes()).getTextContent());
                    int volume = Integer.parseInt(book.getAttributes().getNamedItem(XMLAttributes.VOLUME.getAtributes()).getTextContent());
                    int orderId = Integer.parseInt(book.getAttributes().getNamedItem(XMLAttributes.ORDER_ID.getAtributes()).getTextContent());
                    Order orderValuy = new Order(orderId, name, operation, volume, price);
                    super.getAllOrders().add(orderValuy);
                } else if (book.getNodeName().equals(XMLAttributes.BOOK_DEL.getAtributes())) {
                    int orderId = Integer.parseInt(book.getAttributes().getNamedItem(XMLAttributes.ORDER_ID.getAtributes()).getTextContent());
                    String name = book.getAttributes().getNamedItem(XMLAttributes.NAME_BOOK.getAtributes()).getTextContent();
                    Order orderDelete = new Order(orderId, name);
                    super.getAllOrders().remove(orderDelete);
                }
            }
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
        return super.getAllOrders();
    }
}