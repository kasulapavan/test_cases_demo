package thrymr.net.hospital.management.service.impl;

import jakarta.servlet.ServletOutputStream;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import thrymr.net.hospital.management.entity.ExcelData;
import thrymr.net.hospital.management.repository.ExcelRepo;
import thrymr.net.hospital.management.service.ExcelService;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;
@Service
public class ExcelServiceImpl implements ExcelService {
    @Autowired
    ExcelRepo excelRepo;

    public static boolean isValidExcelFile(MultipartFile file) {
        return Objects.equals(file.getContentType(), "/home/thrymr/Downloads/P4-4 huge file - 3M (1).csv");
    }

    public void excelToDB(MultipartFile file) throws IOException {
        if (file != null) {
            List<ExcelData> excelData = excelToDB(file.getInputStream());
            excelRepo.saveAll(excelData);
        } else {
            throw new IllegalArgumentException("This file is not a valid excel file");
        }
    }


    //    public static List<ExcelData> excelToDB(InputStream inputStream) throws IOException {
//        List<ExcelData> excelDataList = new ArrayList<>();
//        try (FileInputStream fis = new FileInputStream("/home/thrymr/Downloads/P4-4 huge file - 3M.xlsx");
//             Workbook workbook = new XSSFWorkbook(fis)) {
//            StringBuilder concatenatedData1 = new StringBuilder();
//            Sheet sheet = workbook.getSheetAt(0);
//            Row row1 = sheet.getRow(0);
////            for(int columnIndex = 17; columnIndex <= row1.getLastCellNum(); columnIndex++){
////                concatenatedData1.append(sheet.getRow(0).getCell(columnIndex).getDateCellValue());
////                concatenatedData1.append(",");
////
//////                    String text =  new  DataFormatter().formatCellValue(row.getCell(rowIndex));
////            }
//            System.out.println(concatenatedData1.toString());
//
//            // Iterate through rows, starting from the first data row (skipping the header)
//            for (int rowIndex = 1; rowIndex <= sheet.getLastRowNum(); rowIndex++) {
//                Row row = sheet.getRow(rowIndex);
//
//
//                // Assuming the column indexes for the fields you want to store
//                String resourceId = new DataFormatter().formatCellValue(row.getCell(0));
//                String resourceName = new DataFormatter().formatCellValue(row.getCell(1));
//                String resourceType = new DataFormatter().formatCellValue(row.getCell(2));
//                String activityId = new DataFormatter().formatCellValue(row.getCell(3));
//                String activityName = new DataFormatter().formatCellValue(row.getCell(4));
//                String wbs = new DataFormatter().formatCellValue(row.getCell(5));
//                String wbsName = new DataFormatter().formatCellValue(row.getCell(6));
//                String soeId = new DataFormatter().formatCellValue(row.getCell(7));
//                String start = new DataFormatter().formatCellValue(row.getCell(8));
//                String finish = new DataFormatter().formatCellValue(row.getCell(9));
//                String unitOfMeasure = new DataFormatter().formatCellValue(row.getCell(10));
//                String budgetedUnits = new DataFormatter().formatCellValue(row.getCell(11));
//                String actualUnits = new DataFormatter().formatCellValue(row.getCell(12));
//                String remainingUnits = new DataFormatter().formatCellValue(row.getCell(13));
//                String calendar = new DataFormatter().formatCellValue(row.getCell(14));
//                String curve = new DataFormatter().formatCellValue(row.getCell(15));
//                String spreadsheetField = new DataFormatter().formatCellValue(row.getCell(16));
//
//
//
//
//
//
//                // Iterate through rows, starting from the first data row (skipping the header)
//                StringBuilder concatenatedData = new StringBuilder();
//                for (int columnIndex = 17; columnIndex <= row.getLastCellNum(); columnIndex++) {
//                    String cellValue = new DataFormatter().formatCellValue(row.getCell(columnIndex));
//                    if (!cellValue.isEmpty()) {
//                        if (!concatenatedData.isEmpty()) {
////                            concatenatedData.append(new DataFormatter().formatCellValue(row.getCell(columnIndex)));
////                            concatenatedData.append(row);
////                            System.out.println(sheet.getRow(0).getCell(columnIndex));
//                            concatenatedData.append(sheet.getRow(columnIndex));
////                            for(int columnIndex1 = 17; columnIndex <= row.getLastCellNum(); columnIndex++) {
////                                concatenatedData.append(sheet.getRow(0).getCell(columnIndex).getDateCellValue());
//
//                            }
//                            concatenatedData.append(":");
//                        }
//                        concatenatedData.append(cellValue);
//                    }
//
//
//
//                // Create ExcelData object and set the concatenated "Dates" field
//                ExcelData excelData = new ExcelData();
//                excelData.setDates(concatenatedData.toString());
//
//                // Create ExcelData object and set the fields
//                excelData.setResourceId(resourceId);
//                excelData.setResourceName(resourceName);
//                excelData.setResourceType(resourceType);
//                excelData.setActivityID(activityId);
//                excelData.setActivityName(activityName);
//                excelData.setWbs(wbs);
//                excelData.setWbsName(wbsName);
//                excelData.setSoeID(soeId);
//                excelData.setStart(start);
//                excelData.setFinish(finish);
//                excelData.setCalendar(calendar);
//                excelData.setCurve(curve);
//                excelData.setSpreadsheetField(spreadsheetField);
//                excelData.setUnitOfMeasure(unitOfMeasure);
//                excelData.setRemainingUnits(remainingUnits);
//                excelData.setActualUnits(actualUnits);
//                excelData.setBudgetedUnits(budgetedUnits);
//
//                // Add the ExcelData object to the list
//                excelDataList.add(excelData);
//            }
//        }
//        return excelDataList;
//    }
    public void getExcel(HttpServletResponse response) throws IOException {
        List<ExcelData> excelDataList = excelRepo.findAll();
        HSSFWorkbook workbook = new HSSFWorkbook();
        HSSFSheet sheet = workbook.createSheet("team info");
        HSSFRow row = sheet.createRow(0);
        row.createCell(0).setCellValue("resourceId");
        row.createCell(1).setCellValue("resourceName");
        row.createCell(2).setCellValue("resourceType");
        row.createCell(3).setCellValue("activityID");
        row.createCell(4).setCellValue("activityName");
        row.createCell(5).setCellValue("wbs");
        row.createCell(6).setCellValue("wbsName");
        row.createCell(7).setCellValue("soeID");
        row.createCell(8).setCellValue("start");
        row.createCell(9).setCellValue("finish");
        row.createCell(10).setCellValue("unitOfMeasure");
        row.createCell(11).setCellValue("budgetedUnits");
        row.createCell(12).setCellValue("remainingUnits");
        row.createCell(13).setCellValue("actualUnits");
        row.createCell(14).setCellValue("calendar");
        row.createCell(15).setCellValue("curve");
        row.createCell(16).setCellValue("spreadsheetField");
        row.createCell(17).setCellValue("dates");
        int index = 1;
        for (ExcelData excelData : excelDataList) {
            HSSFRow row1 = sheet.createRow(index);
            row1.createCell(0).setCellValue(excelData.getResourceId());
            row1.createCell(1).setCellValue(excelData.getResourceName());
            row1.createCell(2).setCellValue(excelData.getResourceType());
            row1.createCell(3).setCellValue(excelData.getSoeID());
            row1.createCell(4).setCellValue(excelData.getCalendar());
            row1.createCell(5).setCellValue(excelData.getSpreadsheetField());
            row1.createCell(6).setCellValue(excelData.getStart());
            row1.createCell(7).setCellValue(excelData.getUnitOfMeasure());
            row1.createCell(8).setCellValue(excelData.getWbs());
            row1.createCell(9).setCellValue(excelData.getWbsName());
            row1.createCell(10).setCellValue(excelData.getBudgetedUnits());
            row1.createCell(11).setCellValue(excelData.getActualUnits());
            row1.createCell(12).setCellValue(excelData.getCurve());
//            row1.createCell(13).setCellValue(excelData.getDates());
            row1.createCell(14).setCellValue(excelData.getFinish());
            row1.createCell(15).setCellValue(excelData.getRemainingUnits());
            row1.createCell(16).setCellValue(excelData.getActivityID());
            index++;
        }
        ServletOutputStream outputStream = response.getOutputStream();
        workbook.write(outputStream);
        workbook.close();
        outputStream.close();

    }

    public static List<ExcelData> excelToDB(InputStream inputStream) throws IOException {
        List<ExcelData> excelDataList = new ArrayList<>();
        try {

            XSSFWorkbook workbook = new XSSFWorkbook(inputStream);
            XSSFSheet sheet = workbook.getSheetAt(0);
            int rows = sheet.getLastRowNum();
            for (int i = 1; i <= rows; i++) {
                int cols = sheet.getRow(0).getLastCellNum();
                ExcelData excelData = new ExcelData();
                StringBuffer dates = new StringBuffer();
                for (int j = 0; j < cols; j++) {
                    XSSFCell cell1 = sheet.getRow(0).getCell(j);
                    XSSFCell cell = sheet.getRow(i).getCell(j);
                    if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Resource ID")) {
                        excelData.setResourceId(cell != null ? new DataFormatter().formatCellValue(cell) : null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Resource Name")) {
                        excelData.setResourceName(cell != null ? new DataFormatter().formatCellValue(cell) :  null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Resource Type")) {
                        excelData.setResourceType(cell != null ? new DataFormatter().formatCellValue(cell) :  null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Activity ID")) {
                        excelData.setActivityID(cell != null ? new DataFormatter().formatCellValue(cell) : null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Activity Name")) {
                        excelData.setActivityName(cell != null ? new DataFormatter().formatCellValue(cell) :  null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("WBS")) {
                        excelData.setWbs(cell != null ? new DataFormatter().formatCellValue(cell) : null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("WBS Name")) {
                        excelData.setWbsName(cell != null ? new DataFormatter().formatCellValue(cell) :  null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("SOE ID")) {
                        excelData.setSoeID(cell != null ? new DataFormatter().formatCellValue(cell) : null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Start")) {
                        if(cell!=null) {
                            String datess = new DataFormatter().formatCellValue(cell);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
                            Date date = formatter.parse(datess);
                            excelData.setStart(date);
                        }

                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Finish")) {
                        if(cell!=null) {
                            String datess = new DataFormatter().formatCellValue(cell);
                            SimpleDateFormat formatter = new SimpleDateFormat("dd-MMM-yy");
                            Date date = formatter.parse(datess);
                            excelData.setFinish(date);
                        }
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Unit of Measure")) {
                        excelData.setUnitOfMeasure(cell != null ? new DataFormatter().formatCellValue(cell) :  null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Budgeted Units")) {
//                        excelData.setBudgetedUnits(Long.valueOf(cell != null ? new DataFormatter().formatCellValue(cell) :  0L));
                        if (cell != null) {
                            excelData.setBudgetedUnits(Long.valueOf(new DataFormatter().formatCellValue(cell)));
                        } else {
                            excelData.setBudgetedUnits(0L);
                        }
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Actual Units")) {
//                        excelData.setActualUnits(Long.valueOf(cell != null ? new DataFormatter().formatCellValue(cell) :  null));
                        if (cell != null) {
                            excelData.setActualUnits(Long.valueOf(new DataFormatter().formatCellValue(cell)));
                        } else {
                            excelData.setActualUnits(0L);
                        }


                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Remaining Units")) {
                        excelData.setRemainingUnits(cell != null ? new DataFormatter().formatCellValue(cell) : null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Calendar")) {
                        excelData.setCalendar(cell != null ? new DataFormatter().formatCellValue(cell) : null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Curve")) {
                        excelData.setCurve(cell != null ? new DataFormatter().formatCellValue(cell) :null);
                    } else if (new DataFormatter().formatCellValue(cell1).equalsIgnoreCase("Spreadsheet Field")) {
                        excelData.setSpreadsheetField(cell != null ? new DataFormatter().formatCellValue(cell) :null);
                    }
                    if (j > 16) {
                        if (!new DataFormatter().formatCellValue(cell).isEmpty()) {
                            dates.append(new DataFormatter().formatCellValue(cell1)).append(":").append(cell != null ? new DataFormatter().formatCellValue(cell) : 0).append(";");
//                        excelData.setDates(List.of(new DataFormatter().formatCellValue(cell1)+":"+(cell != null ? new DataFormatter().formatCellValue(cell) : 0)));
                        }
                    }
                }
                excelData.setDates(String.valueOf(dates));
                excelDataList.add(excelData);
            }
            return excelDataList;
        } catch (Exception e) {
            return null;
        }
    }

    }
