/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dingo;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import jxl.CellView;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.format.UnderlineStyle;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import jxl.write.WriteException;
import jxl.write.biff.RowsExceededException;

/**
 *
 * @author HEIDY2016
 */
public class Excel {
    private WritableCellFormat timesBoldUnderline;
    private WritableCellFormat times;
    private String inputFile;
    
    public Excel(String file) {
        this.inputFile = file;
    }
    
    public void setOutputFile(String inputFile) {
        this.inputFile = inputFile;
    }
    
    /**
     *
     * @param changes
     * @throws IOException
     * @throws WriteException
     */
    public void write() throws IOException, WriteException {
        
    }
    
    public void createLabel(WritableSheet sheet) {
        
    }
    
    
}
