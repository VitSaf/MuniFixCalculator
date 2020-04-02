package excelOperations;

import Frames.MakeMuniHigherFrame;
import Frames.MakeMuniLowerFrame;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;

import javax.swing.*;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class FixMuniWorkbook extends WorkbookOperations {
    @Override
    public void write(Object o) {

        WorkbookOperations.create();

        try {
            HSSFWorkbook wb = new HSSFWorkbook(new FileInputStream(WORKBOOK));
            Sheet sheet = wb.getSheet("Results");
            Row row = sheet.createRow(sheet.getPhysicalNumberOfRows());
            Cell cell;

            switch (o.getClass().toString()){
                case "class Frames.MakeMuniHigherFrame" :{
                    MakeMuniHigherFrame frame = (MakeMuniHigherFrame)o;
                    cell = row.createCell(0);
                    cell.setCellValue(frame.getCurrentMuni());

                    cell = row.createCell(1);
                    cell.setCellValue(frame.getCurrentL());

                    cell = row.createCell(2);
                    cell.setCellValue(frame.getMuniBatarei());

                    cell = row.createCell(3);
                    cell.setCellValue(frame.getMaslo());

                    cell = row.createCell(6);
                    cell.setCellValue(0);

                    cell = row.createCell(7);
                    cell.setCellValue(frame.getPolimer());

                    cell = row.createCell(8);
                    cell.setCellValue(frame.getNewMuni());

                    cell = row.createCell(9);
                    cell.setCellValue(frame.getNewMaslo());
                }
                break;
                case "class Frames.MakeMuniLowerFrame" :{
                    MakeMuniLowerFrame frame = (MakeMuniLowerFrame)o;

                    cell = row.createCell(0);
                    cell.setCellValue(frame.getCurrentMuni());

                    cell = row.createCell(1);
                    cell.setCellValue(frame.getCurrentL());

                    cell = row.createCell(2);
                    cell.setCellValue(frame.getMuniBatarei());

                    cell = row.createCell(3);
                    cell.setCellValue(frame.getMaslo());

                    cell = row.createCell(4);
                    cell.setCellValue(frame.getRo());

                    cell = row.createCell(5);
                    cell.setCellValue(frame.getSuhoyOstatok());

                    cell = row.createCell(6);
                    cell.setCellValue(frame.getAddMaslo());

                    cell = row.createCell(7);
                    cell.setCellValue(0);

                    cell = row.createCell(8);
                    cell.setCellValue(frame.getNewMuni());

                    cell = row.createCell(9);
                    cell.setCellValue(frame.getNewMaslo());
                }
                break;
            }
            wb.write(new FileOutputStream(WORKBOOK));
            wb.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
