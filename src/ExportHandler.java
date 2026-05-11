import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.poi.xssf.usermodel.*;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

public class ExportHandler {
    
    
    public ExportHandler(){
        
    }
    
    public void appointmentTxtToExcel(String textFile) throws FileNotFoundException, IOException {
        
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        
        File folder = new File("schedules");
        
        if (!folder.exists()) {
            folder.mkdirs();
        }

        String outputFile = "schedules/upcomingSchedule_" + timestamp + ".xlsx";
        
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
    
    public void generateReceiptPDF(Payment payment) {
        try {
            
            File folder = new File("receipts");
            
            if (!folder.exists()) {
            folder.mkdirs();
            }
            
            String fileName = "receipts/receipt_" + payment.getPaymentID() + ".pdf";

            Document document = new Document();
            PdfWriter.getInstance(document, new FileOutputStream(fileName));

            document.open();
            
            Image logo = Image.getInstance(getClass().getResource("/images/APU Logo.png"));
            logo.scaleToFit(200, 200);
            logo.setAlignment(Element.ALIGN_CENTER);

            document.add(logo);

            Font shopFont = new Font(Font.FontFamily.HELVETICA, 16, Font.BOLD);
            Paragraph shopName = new Paragraph("APU SERVICE CENTRE", shopFont);
            shopName.setAlignment(Element.ALIGN_CENTER);

            Paragraph address = new Paragraph("Taman Teknologi Malaysia, Bukit Jalil, Kuala Lumpur, Malaysia\n\n");
            address.setAlignment(Element.ALIGN_CENTER);

            document.add(shopName);
            document.add(address);

        
            Font titleFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD);
            Paragraph title = new Paragraph("PAYMENT RECEIPT\n\n", titleFont);
            title.setAlignment(Element.ALIGN_CENTER);
            document.add(title);

       
            PdfPTable table = new PdfPTable(2);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10f);

            addRow(table, "Payment ID", payment.getPaymentID());
            addRow(table, "Payment Date", String.valueOf(payment.getPaymentDate()));
            addRow(table, "Customer", payment.getCustomer().getUsername());
            addRow(table, "Appointment ID", payment.getAppointment().getAppointmentID());
            addRow(table, "Payment Status", payment.getPaymentStatus());
            addRow(table, "Collected By",
                payment.getCounterStaff() != null ? payment.getCounterStaff().getUsername() : "N/A");

            document.add(table);

        
            Font amountFont = new Font(Font.FontFamily.HELVETICA, 14, Font.BOLD, BaseColor.WHITE);

            PdfPCell amountLabel = new PdfPCell(new Phrase("TOTAL AMOUNT", amountFont));
            amountLabel.setBackgroundColor(BaseColor.DARK_GRAY);
            amountLabel.setHorizontalAlignment(Element.ALIGN_CENTER);
            amountLabel.setPadding(8f);

            PdfPCell amountValue = new PdfPCell(
                new Phrase("RM " + String.format("%.2f", payment.getPaymentAmount()), amountFont));
            amountValue.setBackgroundColor(BaseColor.DARK_GRAY);
            amountValue.setHorizontalAlignment(Element.ALIGN_CENTER);
            amountValue.setPadding(8f);

            PdfPTable amountTable = new PdfPTable(2);
            amountTable.setWidthPercentage(100);
            amountTable.addCell(amountLabel);
            amountTable.addCell(amountValue);

            document.add(amountTable);

        
            Paragraph footer = new Paragraph("\nThank you for your payment!");
            footer.setAlignment(Element.ALIGN_CENTER);

            Paragraph footer2 = new Paragraph("This is a computer generated receipt.");
            footer2.setAlignment(Element.ALIGN_CENTER);

            document.add(footer);
            document.add(footer2);

            document.close();

            System.out.println("Receipt created: " + fileName);

        } catch (Exception e) {
            e.printStackTrace();
        } 
    }
    
    private void addRow(PdfPTable table, String label, String value) {
        PdfPCell cell1 = new PdfPCell(new Phrase(label));
        PdfPCell cell2 = new PdfPCell(new Phrase(value));

        cell1.setPadding(5);
        cell2.setPadding(5);

        table.addCell(cell1);
        table.addCell(cell2);
    }
}
