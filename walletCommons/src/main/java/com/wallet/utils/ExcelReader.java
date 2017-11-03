package com.wallet.utils;

import com.google.common.collect.Maps;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;

import static com.google.common.collect.Lists.newArrayList;

/**
 * Created by EBR3556 on 21/09/2017.
 */
public class ExcelReader {

    private static final Logger LOGGER = LogManager.getLogger(ExcelReader.class);

    private Workbook book = null;
    private String activeSheet = "";
    private int nbRows;

    public ExcelReader(String filename) {

        try {
            if (filename.toUpperCase().endsWith(".XLS")) {
                book = new HSSFWorkbook(new FileInputStream(filename));
            }

            if (activeSheet == null || activeSheet.isEmpty()) {
                this.activeSheet = book.getSheetName(0);
                nbRows = getPhysicalNbRows();
            }

        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }

    /**
     * gets the number of row in the active sheet based on the POI
     * getPhysicalNumberOfRows function. FIXME it can be unreliable (gives wrong
     * number for .xlsx files?) -> it's recommended to compute the real number
     * outside of the class and use setNbRows instead -> see
     * ImportDataServiceImpl.parseDataRebalancing
     */
    public int getPhysicalNbRows() {
        if (book == null) {
            return 0;
        }
        return book.getSheet(activeSheet).getPhysicalNumberOfRows();
    }

    public List<Map<Integer, String>> getAllRowsContent(int fromIndex) {
        List<Map<Integer, String>> rowsContent = newArrayList();
        //
        if (book != null) {
            book.getSheet(activeSheet).forEach(row -> {
                if (row != null && row.getRowNum() >= fromIndex) {
                    Map<Integer, String> rowContentColumnIndexToValueMap = Maps.newHashMap();
                    extractRowContent(row, rowContentColumnIndexToValueMap);
                    if(!rowContentColumnIndexToValueMap.isEmpty()){
                        rowsContent.add(rowContentColumnIndexToValueMap);
                    }
                }
            });
        }
        //
        return rowsContent;
    }

    public Map<Integer, String> getRowContent(int rowIndex) {
        Map<Integer, String> rowContentColumnIndexToValueMap = Maps.newHashMap();
        //
        if (book != null) {
            Row row = book.getSheet(activeSheet).getRow(rowIndex);
            extractRowContent(row, rowContentColumnIndexToValueMap);
        }
        //
        return rowContentColumnIndexToValueMap;
    }

    private void extractRowContent(Row row, Map<Integer, String> intoMap) {
        if (row != null) {
            row.forEach(cell -> {
                if (cell != null) {
                    switch (cell.getCellTypeEnum()) {
                        case NUMERIC:
                            intoMap.put(cell.getColumnIndex(), Double.valueOf(cell.getNumericCellValue()).toString());
                            break;
                        case STRING:
                            intoMap.put(cell.getColumnIndex(), cell.getStringCellValue());
                            break;
                        default:
                            break;
                    }
                }
            });
        }
    }

    public void close() {
        try {
            this.book.close();
        } catch (IOException e) {
            LOGGER.error(e.getMessage(), e);
        }
    }


    public static void main(String[] args) {
//        String path = "";
        String path = "C:\\Users\\esteban.bravo\\Downloads\\export_03_11_2017_13_46_25.xls";
//        String path = "C:/Users/ebr3556/Desktop/Esteban/Documents/Personnel/transactiones/export_12_09_2017_10_34_07.xls";
        ExcelReader excelReader = new ExcelReader(path);
        excelReader.getRowContent(0);
        excelReader.getAllRowsContent(0);
        excelReader.close();
        LOGGER.info("test");
    }

}
