
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.swing.JOptionPane;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ejjaz
 */
public class DataHandler {
    private static final int maxCount = 1000;
    
    private User [] users = new User[maxCount];
    private Technician [] technicians = new Technician[maxCount];
    private CounterStaff [] counterStaffs = new CounterStaff[maxCount];
    private Customer [] customers = new Customer[maxCount];
    private Manager [] managers = new Manager[maxCount];
    private Appointment [] appointments = new Appointment[maxCount];
    private Payment [] payments = new Payment[maxCount];
    private Feedback [] feedbacks = new Feedback[maxCount];
    private AuditLog [] auditLogs = new AuditLog[maxCount];
    
    private int userCount = 0;
    private int technicianCount = 0;
    private int counterStaffCount = 0;
    private int customerCount = 0;
    private int managerCount = 0;
    private int appointmentCount = 0;
    private int paymentCount = 0;
    private int feedbackCount = 0;
    private int auditLogCount = 0;
    
    private String userFile = "user.txt";
    private String appointmentFile = "appointment.txt";
    private String paymentFile = "payment.txt";
    private String feedbackFile = "feedback.txt";
    private String auditLogFile = "auditLog.txt";
    
    public DataHandler(){ 
    }
    
    public Appointment[] getAppointments() {
        return appointments;
    }

    public int getAppointmentCount() {
        return appointmentCount;
    }
    
    public Payment[] getPayments() {
        return payments;
    }

    public int getPaymentCount() {
        return paymentCount;
    }
    
    public User [] loadUsers() throws IOException {
        
        System.out.println("READ user.txt");
        userCount = 0;
        technicianCount = 0;
        counterStaffCount = 0;
        managerCount = 0;
        customerCount = 0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(userFile))) {
            String line;
            while((line = br.readLine()) != null && userCount < maxCount ) {
                String [] userRecord = line.split("\\|");
                String userID = userRecord[0];
                String username = userRecord[1];
                String password = userRecord[2];
                String userRole = userRecord[3];
                
                User u = null;
                
                switch(userRole) {
                    case "TECHNICIAN":
                        u = new Technician(userID, username, password, userRole);
                        technicians[technicianCount++] = (Technician) u;
                        break;
                    case "COUNTERSTAFF":
                        u = new CounterStaff(userID, username, password, userRole);
                        counterStaffs[counterStaffCount++] = (CounterStaff) u;
                        break;
                    case "CUSTOMER":
                        u = new Customer(userID, username, password, userRole);
                        customers[customerCount++] = (Customer) u;
                        break;
                    case "MANAGER":
                        u = new Manager(userID, username, password, userRole);
                        managers[managerCount++] = (Manager) u;
                        break;
                    default:
                        u = new User(userID, username, password, userRole);
                        
                }
                
                users[userCount++] = u;
            }
        }
        
        return users;
    }
    
    public User [] getUserByRole(String role) {
        
        User [] result = new User[maxCount];
        int count = 0;
        
        for(int i = 0; i < userCount; i++) {
            if(users[i] != null && users[i].getUserRole().equalsIgnoreCase(role)) {
                result[count++] = users[i];
            }
        }
        
        return java.util.Arrays.copyOf(result, count);
    }
    
    public User [] getUserByRoles(String... roles) {
        User[] result = new User[maxCount];
        int count = 0;

        for (int i = 0; i < userCount; i++) {
            if (users[i] != null) {
                String userRole = users[i].getUserRole();

                for (String role : roles) {
                    if (userRole.equalsIgnoreCase(role)) {
                        result[count++] = users[i];
                        break;
                    }
                }
            }
        }   
        return java.util.Arrays.copyOf(result, count);
    }
 
    public User getUserByID(String ID) {
        for(int i = 0; i < userCount; i++) {
            if(users[i].getUserID().equals(ID)) {
                return users[i];
            }
        }
        return null;
    }
    
    public User getUserByUsername(String username) {
        for(int i = 0; i < userCount; i++) {
            if(users[i].getUsername().equals(username)) {
                return users[i];
            }
        }
        return null;
    }
    
    public Appointment [] loadAppointments() throws IOException {
        
        appointmentCount = 0;

        try(BufferedReader br = new BufferedReader(new FileReader(appointmentFile))){
            String line;
            while((line = br.readLine()) != null && appointmentCount < maxCount){
                String [] appointmentRecord = line.split("\\|");
                String appointmentID = appointmentRecord[0];
                String appointmentLocation = appointmentRecord[1];
                String appointmentType = appointmentRecord[2];
                String appointmentDate = appointmentRecord[3];
                LocalTime appointmentStartTime = LocalTime.parse(appointmentRecord[4], DateTimeFormatter.ofPattern("HH:mm"));
                
                LocalTime appointmentEndTime = null;
                if (!appointmentRecord[5].equalsIgnoreCase("null")) {
                    appointmentEndTime = LocalTime.parse(appointmentRecord[5].trim(), DateTimeFormatter.ofPattern("HH:mm"));
                }
                
                String appointmentStatus = appointmentRecord[6];
                String appointmentPaymentStatus = appointmentRecord[7];
              
                String technicianID = appointmentRecord[8];
                String customerID = appointmentRecord[9];
                String counterStaffID = appointmentRecord[10];
                
                Technician technician = (Technician) getUserByID(technicianID);
                Customer customer = (Customer) getUserByID(customerID);
                CounterStaff counterStaff = (CounterStaff) getUserByID(counterStaffID);
                
                appointments[appointmentCount++] = new Appointment(
                    appointmentID, appointmentLocation, appointmentType,
                    appointmentDate, appointmentStartTime, appointmentEndTime, appointmentStatus, 
                    appointmentPaymentStatus, technician, customer, counterStaff);
            } 
        }      
        return appointments;
    }
    
    public Appointment getAppointmentByID(String ID) {
        for(int i = 0; i < appointmentCount; i++) {
            if(appointments[i].getAppointmentID().equals(ID)) {
                return appointments[i];
            }
        }
        return null;
    }
    
    public Appointment [] getAppointmentByUserID(String ID) {
        
        Appointment [] result = new Appointment[maxCount];
        int count = 0;
        
        for(int i = 0; i < appointmentCount; i++) {
                 
            if(appointments[i] != null && appointments[i].getCustomer() != null &&
                    appointments[i].getCustomer().getUserID().trim().equalsIgnoreCase(ID)) {
                
                result[count++] = appointments[i];
            }
        }
        
        return java.util.Arrays.copyOf(result, count);
    }
    
    public Appointment [] getAppointmentByTechnicianID(String ID) {
        
        Appointment [] result = new Appointment[maxCount];
        int count = 0;
        
        for(int i = 0; i < appointmentCount; i++) {
                 
            if(appointments[i] != null && appointments[i].getTechnician() != null &&
                    appointments[i].getTechnician().getUserID().trim().equalsIgnoreCase(ID)) {
                
                result[count++] = appointments[i];
            }
        }
        
        return java.util.Arrays.copyOf(result, count);
    }
    
    public Appointment [] getAppointmentByStatus(String status) {
        
        Appointment [] result = new Appointment[maxCount];
        int count = 0;
        
        for(int i = 0; i < appointmentCount; i++) {
                 
            if(appointments[i] != null && appointments[i].getAppointmentStatus().trim().equalsIgnoreCase(status)) {
                result[count++] = appointments[i];
            }
        }
        
        return java.util.Arrays.copyOf(result, count);
    }
    
    public Appointment [] getAppointmentByPaymentStatus(String status) {
        
        Appointment [] result = new Appointment[maxCount];
        int count = 0;
        
        for(int i = 0; i < appointmentCount; i++) {
                 
            if(appointments[i] != null && appointments[i].getAppointmentPaymentStatus().trim().equalsIgnoreCase(status)) {
                result[count++] = appointments[i];
            }
        }
        
        return java.util.Arrays.copyOf(result, count);
    }
    
    public Payment [] loadPayments() throws IOException {
        
        paymentCount = 0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(paymentFile))) {
            String line;
            while((line = br.readLine()) != null && paymentCount < maxCount) {
                String [] paymentRecord = line.split("\\|");
                String paymentID = paymentRecord[0];
                double paymentAmount = Double.parseDouble(paymentRecord[1]);
                String paymentDate = paymentRecord[2];
                String paymentStatus = paymentRecord[3];
                
                String appointmentID = paymentRecord[4];
                String customerID = paymentRecord[5];
                String counterStaffID = paymentRecord[6];
                
                Appointment appointment = getAppointmentByID(appointmentID);
                Customer customer = (Customer) getUserByID(customerID);
                CounterStaff counterStaff = (CounterStaff) getUserByID(counterStaffID);
                
                payments[paymentCount++] = new Payment(paymentID, paymentAmount, paymentDate, paymentStatus,
                        appointment, customer, counterStaff);
            }
        }
        return payments;
    }
    
    public Payment getPaymentByID(String ID) {
        for(int i = 0; i < paymentCount; i++) {
            if(payments[i].getPaymentID().equals(ID)) {
                return payments[i];
            }
        }
        return null;
    }
    
    public Payment [] getPaymentByStatus(String status) {
        
        Payment [] result = new Payment[maxCount];
        int count = 0;
        
        for(int i = 0; i < paymentCount; i++) {
                 
            if(payments[i] != null && payments[i].getPaymentStatus().trim().equalsIgnoreCase(status)) {
                result[count++] = payments[i];
            }
        }
        
        return java.util.Arrays.copyOf(result, count);
    }
    
    public Feedback [] loadFeedback() throws IOException {
        
        feedbackCount = 0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(feedbackFile))) {
            String line;
            while((line = br.readLine()) != null && feedbackCount < maxCount) {
                String [] feedbackRecord = line.split("\\|");
                String feedbackID = feedbackRecord[0];
                String customerFeedback = feedbackRecord[1];
                String technicianFeedback = feedbackRecord[2];
                
                String appointmentID = feedbackRecord[3];
                String customerID = feedbackRecord[4];
                String technicianID = feedbackRecord[5];
                
                Appointment appointment = getAppointmentByID(appointmentID);
                Customer customer = (Customer) getUserByID(customerID);
                Technician technician = (Technician) getUserByID(technicianID);
                
                feedbacks[feedbackCount++] = new Feedback(feedbackID, customerFeedback, technicianFeedback,
                    appointment, customer, technician);
            }
        }
        return feedbacks;
    }
    
    public Feedback getFeedbackByID(String ID) {
        for(int i = 0; i < feedbackCount; i++) {
            if(feedbacks[i].getFeedbackID().equals(ID)){
                return feedbacks[i];
            }
        }
        return null;
    }
    
    public Feedback getFeedbackByAppointmentID(String ID) {
        for(int i = 0; i < feedbackCount; i++) {
            if(feedbacks[i].getAppointment().getAppointmentID().equals(ID)){
                return feedbacks[i];
            }
        }
        return null;
    }
    
    public Feedback[] getFeedbacksByAppointmentID(String... IDs) {
    
    Feedback[] result = new Feedback[feedbackCount];
    int count = 0;

    for (int i = 0; i < feedbackCount; i++) {

        if (feedbacks[i] == null) continue;

        String apptID = feedbacks[i].getAppointment().getAppointmentID();

        for (String id : IDs) {
            if (apptID.equalsIgnoreCase(id)) {
                result[count++] = feedbacks[i];
                break;
            }
        }
    }

    return java.util.Arrays.copyOf(result, count);
}
    
    public Feedback [] getFeedbackByUserID(String ID) {
        
        Feedback [] result = new Feedback[maxCount];
        int count = 0;
        
        for(int i = 0; i < feedbackCount; i++) {
                 
            if(feedbacks[i] != null && feedbacks[i].getCustomer() != null &&
                    feedbacks[i].getCustomer().getUserID().trim().equalsIgnoreCase(ID)) {
                
                result[count++] = feedbacks[i];
            }
        }
        
        return java.util.Arrays.copyOf(result, count);
    }
    
    public AuditLog [] loadAuditLog() throws IOException {
        
        auditLogCount = 0;
        
        try(BufferedReader br = new BufferedReader(new FileReader(auditLogFile))) {
            String line;
            while((line = br.readLine()) != null && auditLogCount < maxCount) {
                String [] auditLogRecord = line.split("\\|");
                String auditID = auditLogRecord[0];
                String timestamp = auditLogRecord[1];
                String userRole = auditLogRecord[2];
                String action = auditLogRecord[3];
                String targetID = auditLogRecord[4];
                String details = auditLogRecord[5];
                
                auditLogs[auditLogCount++] = new AuditLog(auditID, timestamp, userRole, action, targetID, details);
            }
        }
        return auditLogs;
    }
    
    public AuditLog getAuditLogByID(String ID) {
        for(int i = 0; i < auditLogCount; i++) {
            if(auditLogs[i].getAuditID().equals(ID)){
                return auditLogs[i];
            }
        }
        return null;
    }
    
    public User Login(String loginID, String loginUsername, String loginPassword) throws IOException {
        User [] users = loadUsers();
        for(User user: users) {
            if(user != null && user.isValidLogin(loginID, loginUsername, loginPassword)) {
                return user;
            }
        }
        return null;
    }
    
    public User RegisterUser(String newUserID, String newUsername, String newPassword, String newRole) throws IOException {
        
        User u = null;
        
        if(newUserID.isEmpty()) {
            JOptionPane.showMessageDialog(null, "ID field is empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if(newUsername.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username field is empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (newPassword.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password field is empty", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (newRole.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Role is not selected", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        } else if (newUserID.length() != 8) {
            JOptionPane.showMessageDialog(null, "Invalid ID", "ERROR", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        
        switch (newRole) {
            case "TECHNICIAN":
                        u = new Technician(newUserID, newUsername, newPassword, newRole);
                        technicians[technicianCount++] = (Technician) u;
                        break;
                    case "COUNTERSTAFF":
                        u = new CounterStaff(newUserID, newUsername, newPassword, newRole);
                        counterStaffs[counterStaffCount++] = (CounterStaff) u;
                        break;
                    case "CUSTOMER":
                        u = new Customer(newUserID, newUsername, newPassword, newRole);
                        customers[customerCount++] = (Customer) u;
                        break;
                    case "MANAGER":
                        u = new Manager(newUserID, newUsername, newPassword, newRole);
                        managers[managerCount++] = (Manager) u;
                        break;
        }
        return u;
    }
    
    public String [] getAppointmentIDsByUserID(String ID) {
        
        Appointment [] a = getAppointmentByUserID(ID);
        
        String [] IDs = new String[a.length];
        
        for (int i = 0; i < a.length; i++) {
            IDs[i] = a[i].getAppointmentID();
        }
        
        return IDs;
        
    }
    
    public String [] getFeedbackIDsByUserID(String ID) {
        
        Feedback [] f = getFeedbackByUserID(ID);
        
        String [] IDs = new String[f.length];
        
        for (int i = 0; i < f.length; i++) {
            IDs[i] = f[i].getFeedbackID();
        }
        
        return IDs;
        
    }
    
    public String ObtainPrice(String appointmentType) throws FileNotFoundException, IOException {
        
        String price = "";
        
        try(BufferedReader br = new BufferedReader(new FileReader("servicePrice.txt"))) {
            String line;
            
            if((line = br.readLine()) != null) {
                String [] record = line.split("\\|");
                
                String normalServicePrice = record[0].trim();
                String majorServicePrice = record[1].trim();
                
                price =  appointmentType.equals("NORMAL") ? normalServicePrice : majorServicePrice;
            }   
        }
        return price;
    }
}
