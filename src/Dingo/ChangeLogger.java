/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;


import java.util.List;
import com.csvreader.CsvReader;
import com.csvreader.CsvWriter;
import java.io.File;
import java.io.FileWriter;

/**
 * @author mormon
 */
public class ChangeLogger {
    
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
     * Once again, this is an example as to what we are going to do. It will
     * be changed when needed.
     * @param change
     * @throws Exception 
     */
    public void appendLog(Change change) throws Exception {
        // use the write/append section under the bookmark(chrome)
        String outputFile = "c:/output.csv";
        
        // check to see of the file has been created
        boolean alreadyExists = new File(outputFile).exists();
        
        try {
            // this will add the comma in between each value
            CsvWriter output = new CsvWriter(new FileWriter(outputFile, true), ',');
            
            // make out the header for a file that does not exist
            if (!alreadyExists) { 
                output.write("id");
                output.write("name");
                output.endRecord();
            }
            
            // if the header already exists, add information
            output.write("3");
            output.write("Joel");
            output.endRecord();
            
            output.write("4");
            output.write("Heidy");
            output.endRecord();
            
            output.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * As of right now this is an example as to what we are going to do, but 
     * it will not take much to change it.
     * @param changes
     * @throws Exception 
     */
    public void readLog(Change changes) throws Exception {
        try { 
            CsvReader products = new CsvReader("c:/products.csv");
            products.readHeaders();
            while (products.readRecord()) {
                String productID = products.get("ProductID");
                String productName = products.get("ProductName");
                String supplierID = products.get("SupplierID");
                String categoryID = products.get("CategoryID");
                String quantityPerUnit = products.get("QuantityPerUnit");
                String unitPrice = products.get("UnitPrice");
                String unitsInStock = products.get("UnitsInStock");
                String unitsOnOrder = products.get("UnitsOnOrder");
                String reorderLevel = products.get("ReorderLevel");
                String discontinued = products.get("Discontinued");
                
                System.out.println(productID + ":" + productName);
            }
            products.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    /**
     * @param size
     * @return 
     */
    public float getFileSize(String size) {
        return 0;
    }

    List<Tower> getTowers() {
        return null;
    }

    void handleChanges(List<Change> changes) {
    }
    
    /**
     * make an XML file of towers
     *
    public List<Tower> getSavedWatchTowers() {
       // add the read XML part here
    }*/    
}
