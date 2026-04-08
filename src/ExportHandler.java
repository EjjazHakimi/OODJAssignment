import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.xssf.usermodel.*;

/**
 *
 * @author ejjaz
 */
public class ExportHandler {
    
    
    public ExportHandler(){
        
    }
    
    public void appointmentTxtToExcel(String textFile) throws FileNotFoundException, IOException {
        
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String outputFile = "upcomingSchedule_" + timestamp + ".xlsx";
        
        try(BufferedReader br = new BufferedReader(new FileReader(textFile));
                
            XSSFWorkbook workbook = new XSSFWorkbook();
            FileOutputStream fos = new FileOutputStream(outputFile)) {
            
            XSSFSheet sheet = workbook.createSheet("Sheet1");
            
            XSSFRow header = sheet.createRow(0);
            header.createCell(0).setCellValue("Appointment ID");
            header.createCell(1).setCellValue("Location");
            header.createCell(2).setCellValue("Appointment Type");
            header.createCell(3).setCellValue("Date");
            header.createCell(4).setCellValue("Start Time");
            header.createCell(5).setCellValue("End Time");
            header.createCell(6).setCellValue("Status");
            header.createCell(7).setCellValue("Payment Status");
            header.createCell(8).setCellValue("Technician ID");
            header.createCell(9).setCellValue("Customer ID");
            header.createCell(10).setCellValue("Counter Staff ID");
            
            String line;
            int rowNum = 1;
            
            while ((line = br.readLine()) != null) {
                XSSFRow row = sheet.createRow(rowNum++);
                
                String[] values = line.split("\\|");

                for (int i = 0; i < values.length; i++) {
                    row.createCell(i).setCellValue(values[i]);
                }
            }
            
            int columnCount = header.getPhysicalNumberOfCells();
            for (int i = 0; i < columnCount; i++) {
                sheet.autoSizeColumn(i);
            }
                
            workbook.write(fos);
                
        }
    }
}
