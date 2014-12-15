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

public class Excel {
  private WritableCellFormat timesBoldUnderline;
  private WritableCellFormat times;
  private String inputFile;
    
  public void setOutputFile(String inputFile) {
    this.inputFile = inputFile;
  }

    /**
     *
     * @param changes
     * @throws IOException
     * @throws WriteException
     */
    public void write(List<Change> changes) throws IOException, WriteException {
    File file = new File(inputFile);
    WorkbookSettings wbSettings = new WorkbookSettings();

    wbSettings.setLocale(new Locale("en", "EN"));

    WritableWorkbook workbook = Workbook.createWorkbook(file, wbSettings);
    workbook.createSheet("Report", 0);
    WritableSheet excelSheet = workbook.getSheet(0);
    createLabel(excelSheet);
    createContent(excelSheet, changes);

    workbook.write();
    workbook.close();
  }

  public void createLabel(WritableSheet sheet)
      throws WriteException {
    // Lets create a times font
    WritableFont times10pt = new WritableFont(WritableFont.TIMES, 10);
    // Define the cell format
    times = new WritableCellFormat(times10pt);
    // Lets automatically wrap the cells
    times.setWrap(true);

    // create create a bold font with unterlines
    WritableFont times10ptBoldUnderline = new WritableFont(WritableFont.TIMES, 10, WritableFont.BOLD, false,
        UnderlineStyle.SINGLE);
    timesBoldUnderline = new WritableCellFormat(times10ptBoldUnderline);
    // Lets automatically wrap the cells
    timesBoldUnderline.setWrap(true);

    CellView cv = new CellView();
    cv.setFormat(times);
    cv.setFormat(timesBoldUnderline);
    cv.setAutosize(true);

    // Write a few headers
    addCaption(sheet, 0, 0, "Filename");
    addCaption(sheet, 1, 0, "Change");    
    addCaption(sheet, 2, 0, "Change Date");

  }

  public void createContent(WritableSheet sheet, List<Change> changes) throws WriteException,
      RowsExceededException { 
    // Write a few number
    for (int i = 0; i < changes.size(); i++) {
      // First column. Row is i + 1 because the first row has the column's titles
      addLabel(sheet, 0, i + 1, changes.get(i).getFilename());
      // Second column
      addLabel(sheet, 1, i + 1, changes.get(i).getType());
      // Third column
      addLabel(sheet, 2, i + 1, changes.get(i).getDate().toString());
    }
  }

  public void addCaption(WritableSheet sheet, int column, int row, String s)
      throws RowsExceededException, WriteException {
    Label label;
    label = new Label(column, row, s, timesBoldUnderline);
    sheet.addCell(label);
  }

  public void addLabel(WritableSheet sheet, int column, int row, String s)
      throws WriteException, RowsExceededException {
    Label label;
    label = new Label(column, row, s, times);
    sheet.addCell(label);
  }
}