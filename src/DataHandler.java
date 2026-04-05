
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;

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
    
    private int userCount = 0;
    private int technicianCount = 0;
    private int counterStaffCount = 0;
    private int customerCount = 0;
    private int managerCount = 0;
    private int appointmentCount = 0;
    private int paymentCount = 0;
    private int feedbackCount = 0;
    
    private String userFile = "user.txt";
    private String appointmentFile = "appointment.txt";
    private String paymentFile = "payment.txt";
    private String feedbackFile = "feedback.txt";
    
    
    public User [] loadUsers() throws IOException {

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
        
        return result;
    }
    
    public User getUserByID(String ID) {
        for(int i = 0; i < userCount; i++) {
            if(users[i].getUserID().equals(ID)) {
                return users[i];
            }
        }
        return null;
    }
    
    public Appointment [] loadAppointments() throws IOException {

        try(BufferedReader br = new BufferedReader(new FileReader(appointmentFile))){
            String line;
            while((line = br.readLine()) != null && appointmentCount < maxCount){
                String [] appointmentRecord = line.split("\\|");
                String appointmentID = appointmentRecord[0];
                String appointmentLocation = appointmentRecord[1];
                String appointmentType = appointmentRecord[2];
                String appointmentDate = appointmentRecord[3];
                String appointmentStartTime = appointmentRecord[4];
                
                boolean appointmentStatus = Boolean.parseBoolean(appointmentRecord[5]);
                boolean appointmentPaymentStatus = Boolean.parseBoolean(appointmentRecord[6]);
                
                String technicianID = appointmentRecord[7];
                String customerID = appointmentRecord[8];
                String counterStaffID = appointmentRecord[9];
                
                Technician technician = (Technician) getUserByID(technicianID);
                Customer customer = (Customer) getUserByID(customerID);
                CounterStaff counterStaff = (CounterStaff) getUserByID(counterStaffID);
                
                appointments[appointmentCount++] = new Appointment(
                    appointmentID, appointmentLocation, appointmentType,
                    appointmentDate, appointmentStartTime, appointmentStatus, 
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
    
    public Payment [] loadPayment() throws IOException {
        
        try(BufferedReader br = new BufferedReader(new FileReader(paymentFile))) {
            String line;
            while((line = br.readLine()) != null && paymentCount < maxCount) {
                String [] paymentRecord = line.split("\\|");
                String paymentID = paymentRecord[0];
                double paymentAmount = Double.parseDouble(paymentRecord[1]);
                String paymentDate = paymentRecord[2];
                
                String appointmentID = paymentRecord[3];
                String customerID = paymentRecord[4];
                String counterStaffID = paymentRecord[5];
                
                Appointment appointment = getAppointmentByID(appointmentID);
                Customer customer = (Customer) getUserByID(customerID);
                CounterStaff counterStaff = (CounterStaff) getUserByID(counterStaffID);
                
                payments[paymentCount++] = new Payment(paymentID, paymentAmount, paymentDate, 
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
    
    public Feedback [] loadFeedback() throws IOException {
        
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
    
    public User Login(String loginID, String loginUsername, String loginPassword) throws IOException {
        User [] users = loadUsers();
        for(User user: users) {
            if(user != null && user.isValidLogin(loginID, loginUsername, loginPassword)) {
                return user;
            }
        }
        return null;
    }
    
    public User CreateUser(String newUserID, String newUsername, String newPassword, String newRole) throws IOException {
        
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
}
