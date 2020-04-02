package excelOperations;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public abstract class WorkbookOperations {

    public static final String WORKBOOK = "Исправление Муни в усреднителе.xls";

    public abstract void write(Object o);
    public static void create(){
        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(WORKBOOK));
            wb.close();
        } catch (IOException e) {
            Workbook wb = new HSSFWorkbook();
            Sheet sheet  = wb.createSheet("Results");
            Row row = sheet.createRow(0);//Если первый раз, то первой строкой создаем заголовки
            Cell cell;

            cell = row.createCell(0);
            cell.setCellValue("Начальный Муни в усреднителе, у.е.");

            cell = row.createCell(1);
            cell.setCellValue("Уровень в усреднителе, %");

            cell = row.createCell(2);
            cell.setCellValue("Муни на батарее, у.е.");

            cell = row.createCell(3);
            cell.setCellValue("Начальный уровень масла, %");

            cell = row.createCell(4);
            cell.setCellValue("Плотность");

            cell = row.createCell(5);
            cell.setCellValue("Сухой остаток, %");

            cell = row.createCell(6);
            cell.setCellValue("Необходимо добавить масла, кг");

            cell = row.createCell(7);
            cell.setCellValue("Необходимо добавить полимера, %");

            cell = row.createCell(8);
            cell.setCellValue("Новый Муни, у.е.");

            cell = row.createCell(9);
            cell.setCellValue("Новый уровень масла, %");

            try {
                wb.write(new FileOutputStream(WORKBOOK));
                wb.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }
}
