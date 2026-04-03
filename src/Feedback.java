
/**
 *
 * @author ejjaz
 */
public class Feedback implements FileHandlerInterface {
    private String feedbackID;
    private String customerFeedback;
    private String technicianFeedback;
    private Appointment appointment;
    private Customer customer;
    private Technician technician;
    
    public Feedback(String feedbackID, String customerFeedback, String technicianFeedback,  
        Appointment appointment, Customer customer, Technician technician) {
          
        this.feedbackID = feedbackID;
        this.customerFeedback = customerFeedback;
        this.technicianFeedback = technicianFeedback;
        this.appointment = appointment;
        this.customer = customer;
        this.technician = technician;
    }
    
    public String getFeedbackID() {
        return feedbackID;
    }
    
    public String getCustomerFeedback() {
        return customerFeedback;
    }
    
    public String getTechnicianFeedback() {
        return technicianFeedback;
    }
    
    
    public void setCustomerFeedback(String finalCustomerFeedback) {
        this.customerFeedback = finalCustomerFeedback;
    }
    
    public void setTechnicianFeedback(String finalTechnicianFeedback) {
        this.technicianFeedback = finalTechnicianFeedback;
    }
    
    
    @Override
    public String getFileKey() {
        return feedbackID;
    }
    
    @Override
    public String toString() {
        return feedbackID + "|" + customerFeedback + "|" + technicianFeedback + "|" 
                + appointment.getAppointmentID() + "|" + customer.getUserID() + "|" + technician.getUserID();
    }    
}
