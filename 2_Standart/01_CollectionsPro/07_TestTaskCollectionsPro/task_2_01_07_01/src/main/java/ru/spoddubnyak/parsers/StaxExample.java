//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package ru.spoddubnyak.parsers;

import ru.spoddubnyak.model.Order;
import ru.spoddubnyak.model.Parsers;
import ru.spoddubnyak.model.XMLAttributes;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;


/**
 * Class StaxExample parsing method StAX.
 *
 * @author Sergei Poddubnyak (forvvard09@gmail.com)
 * @version 1.0
 * @since 01.02.2018
 */
public class StaxExample extends Parsers {
    /**
     * property.
     */
    private InputStream in;
    /**
     * property.
     */
    private XMLStreamReader streamReader;

    /**
     * Method create xml stream reader.
     *
     * @return XMLStreamReader streamReader
     * @throws XMLStreamException    errors
     * @throws FileNotFoundException errors
     */
    private XMLStreamReader initStreamReader() throws XMLStreamException, FileNotFoundException {
        XMLInputFactory inputFactory = XMLInputFactory.newInstance();
        in = new FileInputStream(super.getFilePath());
        streamReader = inputFactory.createXMLStreamReader(in);
        return streamReader;
    }

    /**
     * Method to clean up stream or resource.
     *
     * @throws XMLStreamException errors
     * @throws IOException        errors
     */
    private void closeStream() throws IOException, XMLStreamException {
        if (in != null) {
            in.close();
        }
        if (streamReader != null) {
            streamReader.close();
        }
    }

    @Override
    public Set<Order> parsingTo() throws FileNotFoundException {
        try {
            XMLStreamReader streamReader = initStreamReader();

            while (streamReader.hasNext()) {
                int i = 0;
                if (streamReader.isStartElement()) {
                    if (streamReader.getLocalName().equals(XMLAttributes.BOOK_ADD.getAtributes())) {
                        String name = streamReader.getAttributeValue(i++);
                        String operation = streamReader.getAttributeValue(i++);
                        double price = Double.parseDouble(streamReader.getAttributeValue(i++));
                        int volume = Integer.parseInt(streamReader.getAttributeValue(i++));
                        int orderId = Integer.parseInt(streamReader.getAttributeValue(i++));
                        Order orderValue = new Order(orderId, name, operation, volume, price);
                        super.getAllOrders().add(orderValue);
                    } else if (streamReader.getLocalName().equals(XMLAttributes.BOOK_DEL.getAtributes())) {
                        String name = streamReader.getAttributeValue(i++);
                        int orderId = Integer.parseInt(streamReader.getAttributeValue(i++));
                        Order orderDelete = new Order(orderId, name);
                        super.getAllOrders().remove(orderDelete);
                    }
                }
                streamReader.next();
                streamReader.close();
            }
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        try {
            closeStream();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (XMLStreamException e) {
            e.printStackTrace();
        }
        return getAllOrders();
    }
}