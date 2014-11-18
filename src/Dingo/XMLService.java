/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;

import java.io.File;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * @author mormon
 */
public class XMLService {
    
    /**
     * Make an XML document of the changes that were received
     * from the watchTowerServices.
     * @param change
     * @return 
     */
    public List<Change> getLastChanges(List<Change> change) throws Exception {
        return null;   
    }
   
    /**
     * create an XML and add to it
     */
    public void appendLog(Change change) throws Exception {
        String THE_FILE = "c:\\Users\\temp\\success.xml";
        
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        DocumentBuilder db = dbf.newDocumentBuilder();
        
        // root element
        Document doc = db.newDocument();
        Element rootElement = doc.createElement("event");
        doc.appendChild(rootElement);
        
        // file element
        Element fileElement = doc.createElement("file");
        rootElement.appendChild(fileElement);
        
        // flag element
        Element flagElement = doc.createElement("flag");
        fileElement.appendChild(flagElement);
        
        // start  the XML file
        TransformerFactory transfac = TransformerFactory.newInstance();
        Transformer trans = transfac.newTransformer();
        DOMSource src = new DOMSource(doc);
        // TODO check if file exists.
        File file = new File(THE_FILE);
        if (!file.exists()) {
            // Create file
            file.createNewFile();
        }
        StreamResult result = new StreamResult(file);
        trans.transform(src, result);
    }
    
    /**
     * @param size
     * @return 
     */
    public float getFileSize(String size) {
        return 0;
    }
    
    /**
     * make an XML file of towers
     *
    public List<Tower> getSavedWatchTowers() {
       // add the read XML part here
    }*/    
}
