/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;


import java.util.List;
import com.csvreader.CsvReader;

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
     * create an XML and add to it
     */
    public void appendLog(Change change) throws Exception {
        // use the write/append section under the bookmark(chrome)
    }
    
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
