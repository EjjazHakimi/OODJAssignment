
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLog implements FileHandlerInterface {
    private String auditID;
    private String userID;
    private String action;
    private String targetID;
    private String details;
    private String timestamp;
    
    
    public AuditLog(String auditID, String userID, String action, String targetID, String details) {
        this.auditID = auditID;
        this.userID = userID;
        this.action = action;
        this.targetID = targetID;
        this.details = details;
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.timestamp = LocalDateTime.now().format(formatter);
    }
    
    public AuditLog(String auditID, String timestamp, String userID, String action, String targetID, String details) {
        this.auditID = auditID;
        this.timestamp = timestamp;
        this.userID = userID;
        this.action = action;
        this.targetID = targetID;
        this.details = details;
    }
    
    public String getAuditID() {
        return auditID;
    }
    
    public String getTimestamp() {
        return timestamp;
    }
    
    public String getUserID() {
        return userID;
    }
    
    public String getAction() {
        return action;
    }
    
    public String getTargetID() {
        return targetID;
    }
    
    public String getDetails() {
        return details;
    }
    
    public static void log(FileHandler fh, String auditID, String role,
                           String action, String targetID, String details) {
        try {
            fh.writeRecord(new AuditLog(auditID, role, action, targetID, details));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    @Override
    public String toString() {
        return auditID + "|" + timestamp + "|" + userID + "|" + action + "|" + targetID + "|" + details;
    }
    
    @Override
    public String getFileKey() {
        return auditID;
    }
    
}
