//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.spoddubnyak.parsers;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;
import ru.spoddubnyak.model.Order;
import ru.spoddubnyak.model.Parsers;
import ru.spoddubnyak.model.XMLAttributes;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.util.Set;

/**
 * Class SaxExample parsing method SAX.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 03.02.2018
 */
public class SaxExample extends Parsers {
    /**
     * property allOrders for result parsing.
     */
    private Set<Order> allOrders;

    /**
     * Constructor it creates a new object XMLAttributes container with the specified values.
     */
    public SaxExample() {
        this.allOrders = super.getAllOrders();
    }

    @Override
    public Set<Order> parsingTo() {
        try {
            SAXParserFactory factory = SAXParserFactory.newInstance();
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DefaultHandler() {

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    int i = 0;
                    if (qName.equalsIgnoreCase(XMLAttributes.BOOK_ADD.getAtributes())) {
                        String name = attributes.getValue(i++);
                        String operation = attributes.getValue(i++);
                        double price = Double.parseDouble(attributes.getValue(i++));
                        int volume = Integer.parseInt(attributes.getValue(i++));
                        int orderId = Integer.parseInt(attributes.getValue(i++));
                        Order orderValue = new Order(orderId, name, operation, volume, price);
                        allOrders.add(orderValue);
                    } else if (qName.equalsIgnoreCase(XMLAttributes.BOOK_DEL.getAtributes())) {
                        String name = attributes.getValue(i++);
                        int orderId = Integer.parseInt(attributes.getValue(i++));
                        Order orderDelete = new Order(orderId, name);
                        allOrders.remove(orderDelete);
                    }
                }
            };
            saxParser.parse(super.getFilePath(), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return this.allOrders;
    }
}