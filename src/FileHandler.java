
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;


/**
 *
 * @author ejjaz
 */
public class FileHandler {
    String filename;
    
    public FileHandler(String filename) {
        this.filename = filename;
    }
    
    public boolean doesRecordExists(FileHandlerInterface record) throws FileNotFoundException, IOException {
        try(BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while((line = br.readLine()) != null) {
                String [] records = line.split("\\|");
                String existingRecord = records[0];
                if(existingRecord.equals(record.getFileKey())) {
                    return true;
                }
            }
            return false;
        }
    }
    
    public void writeRecord(FileHandlerInterface record) throws FileNotFoundException, IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, true))) {
            bw.write(record.toString());
            bw.newLine();
        }
    }
    
    public void writeLoginUserRecord(FileHandlerInterface record) throws FileNotFoundException, IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            bw.write(record.toString());
        }
    }
    
    public void deleteRecord(String recordID) throws FileNotFoundException, IOException {
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");
        
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            String line;
            
            while((line = br.readLine()) != null) {
                String [] records = line.split("\\|");
                
                if(!records[0].equals(recordID)) {
                    bw.write(line);
                    bw.newLine();
                }
                
                if(!inputFile.delete()) {
                    System.out.println("Failed to delete input file: " + inputFile);
                    return;
                }
                
                if(!tempFile.renameTo(inputFile)) {
                    System.out.println("Failed to rename temp file: " + tempFile);
                    return;
                }
            }
        }
    }
    
    public void updateRecord(String recordID, String newRecord) throws FileNotFoundException, IOException {
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");
        
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile));
            
            String line;
            
            while((line = br.readLine()) != null) {
                String [] records = line.split("\\|");
                
                if(records[0].equals(recordID)) {
                    bw.write(newRecord);
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
            
            if(!inputFile.delete()) {
                    System.out.println("Failed to delete input file: " + inputFile);
                    return;
                }
                
                if(!tempFile.renameTo(inputFile)) {
                    System.out.println("Failed to rename temp file: " + tempFile);
                    return;
                }
            
        }
    }
 
}
