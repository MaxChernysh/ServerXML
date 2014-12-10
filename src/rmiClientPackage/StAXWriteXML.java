package rmiClientPackage;

import javax.xml.stream.XMLOutputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class StAXWriteXML implements XMLwrite{


    public void write(ArrayList<Tank> list, String filename) {
        XMLOutputFactory factory = XMLOutputFactory.newInstance();

        try {

            System.out.println(getClass().getResource(filename).toString());
            XMLStreamWriter writer = factory.createXMLStreamWriter(new FileWriter(getClass().getResource(filename).getPath()));

            writer.writeStartDocument();
            writer.writeStartElement(TAGS.ROOT_TAG);
            for(Tank item:list) {
                writer.writeStartElement(TAGS.TANK_TAG);

                writer.writeStartElement(TAGS.NATION_TAG);
                writer.writeCharacters(item.getNation());
                writer.writeEndElement();
                
                writer.writeStartElement(TAGS.MODEL_TAG);
                writer.writeCharacters(item.getModel());
                writer.writeEndElement();

                writer.writeStartElement(TAGS.CALIBERGUN_TAG);
                writer.writeCharacters(String.valueOf(item.getCaliberGun()));
                writer.writeEndElement();

                writer.writeStartElement(TAGS.ENGINEPOWER_TAG);
                writer.writeCharacters(String.valueOf(item.getEnginePower()));
                writer.writeEndElement();

                writer.writeStartElement(TAGS.WEIGHT_TAG);
                writer.writeCharacters(String.valueOf(item.getWeight()));
                writer.writeEndElement();


                writer.writeEndElement();
            }
            writer.writeEndElement();
            writer.writeEndDocument();

            writer.flush();
            writer.close();

        } catch (XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
