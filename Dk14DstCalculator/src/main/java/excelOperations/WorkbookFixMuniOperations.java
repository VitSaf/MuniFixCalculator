package excelOperations;

import Frames.MakeMuniHigherFrame;
import Frames.MakeMuniLowerFrame;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

@Deprecated
public class WorkbookFixMuniOperations {

    public static void writeInBookMakeMuniHigher(String workbookName, MakeMuniHigherFrame frame) {
        try {//запись в конец существующего файла
            Workbook wb = new HSSFWorkbook(new FileInputStream(workbookName + ".xls"));
            Sheet sheet = wb.getSheet("Results");//

            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());

            Cell cell;

            cell = row.createCell(0);
            cell.setCellValue(frame.getCurrentMuni());

            cell = row.createCell(1);
            cell.setCellValue(frame.getCurrentL());

            cell = row.createCell(2);
            cell.setCellValue(frame.getMuniBatarei());

            cell = row.createCell(3);
            cell.setCellValue(frame.getPolimer());

            cell = row.createCell(4);
            cell.setCellValue(frame.getResult());

            wb.write(new FileOutputStream(workbookName + ".xls"));
            wb.close();
        } catch (IOException e) {//Если существующий файл не найден, то пилим новый
            Workbook wb = new HSSFWorkbook();
            Sheet sheet = wb.createSheet("Results");
            Row row = sheet.createRow(0);//Если первый раз, то первой строкой создаем заголовки
            Cell cell;

            cell = row.createCell(0);
            cell.setCellValue("Текущий Муни в усреднителе");

            cell = row.createCell(1);
            cell.setCellValue("Уровень в усреднителе");

            cell = row.createCell(2);
            cell.setCellValue("Муни на батарее");

            cell = row.createCell(3);
            cell.setCellValue("Количество добавляемого полимер, %");

            cell = row.createCell(4);
            cell.setCellValue("Расчетный Муни после добавления полимера");

            row = sheet.createRow(1);

            cell = row.createCell(0);
            cell.setCellValue(frame.getCurrentMuni());

            cell = row.createCell(1);
            cell.setCellValue(frame.getCurrentL());

            cell = row.createCell(2);
            cell.setCellValue(frame.getMuniBatarei());

            cell = row.createCell(3);
            cell.setCellValue(frame.getPolimer());

            cell = row.createCell(4);
            cell.setCellValue(frame.getResult());

            try {
                wb.write(new FileOutputStream(workbookName + ".xls"));
                wb.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void writeInBookMakeMuniLower(String workbookName, MakeMuniLowerFrame frame){
        try {//запись в конец существующего файла
            Workbook wb = new HSSFWorkbook(new FileInputStream(workbookName + ".xls"));
            Sheet sheet = wb.getSheet("Results");//

            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());

            Cell cell;

            cell = row.createCell(0);
            cell.setCellValue(frame.getCurrentMuni());

            cell = row.createCell(1);
            cell.setCellValue(frame.getCurrentL());

            cell = row.createCell(2);
            cell.setCellValue(frame.getSuhoyOstatok());

            cell = row.createCell(3);
            cell.setCellValue(frame.getRo());

            cell = row.createCell(4);
            cell.setCellValue(frame.getMuniBatarei());

            cell = row.createCell(5);
            cell.setCellValue(frame.getFactMuni());

            cell = row.createCell(6);
            cell.setCellValue(frame.getMaslo());

            cell = row.createCell(7);
            cell.setCellValue(frame.getAddMaslo());

            cell = row.createCell(8);
            cell.setCellValue(frame.getNewMuni());

            cell = row.createCell(9);
            cell.setCellValue(frame.getNewMaslo());

            wb.write(new FileOutputStream(workbookName + ".xls"));
            wb.close();
        } catch (IOException e) {//Если существующий файл не найден, то пилим новый
            Workbook wb = new HSSFWorkbook();
            Sheet sheet  = wb.createSheet("Results");
            Row row = sheet.createRow(0);//Если первый раз, то первой строкой создаем заголовки
            Cell cell;

            cell = row.createCell(0);
            cell.setCellValue("Текущий Муни в усреднителе, у.е.");

            cell = row.createCell(1);
            cell.setCellValue("Уровень в усреднителе, %");

            cell = row.createCell(2);
            cell.setCellValue("Сухой остаток, %");

            cell = row.createCell(3);
            cell.setCellValue("Плотность");

            cell = row.createCell(4);
            cell.setCellValue("Муни на батарее, у.е.");

            cell = row.createCell(5);
            cell.setCellValue("Фактический Муни");

            cell = row.createCell(6);
            cell.setCellValue("Масло, %");

            cell = row.createCell(7);
            cell.setCellValue("Необходимо добавить масла, тонн");

            cell = row.createCell(8);
            cell.setCellValue("Новый Муни, у.е.");

            cell = row.createCell(9);
            cell.setCellValue("Новый уровень масла, %");


            row = sheet.createRow(1);

            cell = row.createCell(0);
            cell.setCellValue(frame.getCurrentMuni());

            cell = row.createCell(1);
            cell.setCellValue(frame.getCurrentL());

            cell = row.createCell(2);
            cell.setCellValue(frame.getSuhoyOstatok());

            cell = row.createCell(3);
            cell.setCellValue(frame.getRo());

            cell = row.createCell(4);
            cell.setCellValue(frame.getMuniBatarei());

            cell = row.createCell(5);
            cell.setCellValue(frame.getFactMuni());

            cell = row.createCell(6);
            cell.setCellValue(frame.getMaslo());

            cell = row.createCell(7);
            cell.setCellValue(frame.getAddMaslo());

            cell = row.createCell(8);
            cell.setCellValue(frame.getNewMuni());

            cell = row.createCell(9);
            cell.setCellValue(frame.getNewMaslo());
            try {
                wb.write(new FileOutputStream(workbookName + ".xls"));
                wb.close();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }
    }

}
