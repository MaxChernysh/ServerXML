package rmiClientPackage;



import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.util.ArrayList;
import java.util.List;

public class StAXReadXML implements XMLread{


    public ArrayList<Tank> read(String filename) {
        ArrayList<Tank> tanklist = null;
        Tank curTank = null;
        String tagContent = null;
        try {
            XMLInputFactory factory = XMLInputFactory.newInstance();
            XMLStreamReader reader = factory.createXMLStreamReader(
                    StAXReadXML.class.getResourceAsStream(filename));

            while(reader.hasNext()){
                int event = reader.next();
                switch(event){
                    case XMLStreamConstants.START_ELEMENT:
                        if (TAGS.TANK_TAG.equals(reader.getLocalName())){
                                curTank = new Tank();
                        }
                        if(TAGS.ROOT_TAG.equals(reader.getLocalName())){
                            tanklist = new ArrayList();
                        }
                        break;

                    case XMLStreamConstants.CHARACTERS:
                        tagContent = reader.getText().trim();
                        break;

                    case XMLStreamConstants.END_ELEMENT:
                        if(TAGS.TANK_TAG.equals(reader.getLocalName())) {
                            tanklist.add(curTank);
                        } else if(TAGS.NATION_TAG.equals(reader.getLocalName())) {
                            curTank.setNation(tagContent);
                        } else if(TAGS.MODEL_TAG.equals(reader.getLocalName())) {
                            curTank.setModel(tagContent);
                        } else if(TAGS.CALIBERGUN_TAG.equals(reader.getLocalName())) {
                            curTank.setCaliberGun(Integer.parseInt(tagContent));
                        } else if(TAGS.ENGINEPOWER_TAG.equals(reader.getLocalName())) {
                            curTank.setEnginePower(Integer.parseInt(tagContent));
                        } else if(TAGS.WEIGHT_TAG.equals(reader.getLocalName())) {
                            curTank.setWeight(Integer.parseInt(tagContent));
                        }
                        break;

                    case XMLStreamConstants.START_DOCUMENT:
                        tanklist = new ArrayList();
                        break;
                }

            }  

        } catch (XMLStreamException ex) {
            ex.printStackTrace();
        }
        return tanklist;
    }
}
