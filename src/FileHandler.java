
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import javax.swing.JOptionPane;
import java.nio.file.*;

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
    
    public void overwriteRecord(FileHandlerInterface record) throws FileNotFoundException, IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            bw.write(record.toString());
            bw.newLine();
        }
    }
    
    public void writeLoginUserRecord(FileHandlerInterface record) throws FileNotFoundException, IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            bw.write(record.toString());
        }
    }
    
    public void writePriceRecord(String record) throws FileNotFoundException, IOException {
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(filename, false))) {
            bw.write(record);
        }
    }
    
    public void deleteRecord(String recordID) throws FileNotFoundException, IOException {
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");
        
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))){
            String line;
            
            while((line = br.readLine()) != null) {
                String [] records = line.split("\\|");
                
                if(!records[0].trim().equals(recordID.trim())) {
                    bw.write(line);
                    bw.newLine();
                }
  
            }
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
    
    public void deleteRecords(String [] recordIDs) throws FileNotFoundException, IOException {
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");

        try (BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))) {
            String line;
            
            while((line = br.readLine()) != null) {
                String [] records = line.split("\\|");
                
                if (records.length == 0) continue;
                String currentID = records[0].trim();
                
                boolean shouldDelete = false;
                
                for(String id: recordIDs) {
                    if (currentID.equals(id.trim())) {
                        shouldDelete = true;
                        break;
                    }
                }
                
                if (!shouldDelete) {
                    bw.write(line);
                    bw.newLine();
                }   
            }
        }
        if (!inputFile.delete()) {
            System.out.println("Failed to delete input file: " + inputFile);
            return;
    }

        if (!tempFile.renameTo(inputFile)) {
            System.out.println("Failed to rename temp file: " + tempFile);
            return;
        }
    }
    
    public void updateRecord(String recordID, String newRecord) throws FileNotFoundException, IOException{
        
        System.out.println("WRITE user.txt");
        
        
        File inputFile = new File(filename);
        File tempFile = new File("temp.txt");
        
        try(BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(tempFile))){
            
            String line;
            
            while((line = br.readLine()) != null) {
                String [] records = line.split("\\|");
                
                if(records[0].trim().equals(recordID.trim())) {
                    bw.write(newRecord);
                } else {
                    bw.write(line);
                }
                bw.newLine();
            }
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
    
    public void resetFile() throws IOException {
        new FileWriter(filename, false).close();
    }
    
    public String generateNextID(String prefix) throws IOException {
        
        String lastLine = null;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {

            String line;

            while ((line = br.readLine()) != null) {
                if (!line.trim().isEmpty()) {
                    lastLine = line;
                }
            }
        
            if (lastLine == null) {
                return prefix + String.format("%06d", 0);
            }
        
            String lastID = lastLine.split("\\|")[0];
        
            if (!lastID.startsWith(prefix)) {
                throw new IllegalStateException("Invalid ID prefix in file: " + lastID);
            }
        
            int number = Integer.parseInt(lastID.substring(prefix.length()));
            number++;
        
            return prefix + String.format("%06d", number);
        }
    }
}
