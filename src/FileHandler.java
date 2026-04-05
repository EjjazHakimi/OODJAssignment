
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


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
    
    public void deleteRecord() throws FileNotFoundException, IOException {
        //TODO
    }
    
    public void updateRecord() throws FileNotFoundException, IOException {
        //TODO
    }
 
}
