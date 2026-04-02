
/**
 *
 * @author ejjaz
 */
public class Feedback extends Appointment {
    private String feedbackID;
    private String customerFeedback;
    private String technicianFeedback;
    
    public Feedback(String feedbackID, String customerFeedback, String technicianFeedback, 
            String appointmentID, String appointmentLocation, String appointmentType, String appointmentDate, 
            String appointmentStartTime, boolean appointmentStatus, boolean appointmentPaymentStatus, 
            Technician technician, Customer customer, CounterStaff counterStaff) {
        
        super(appointmentID, appointmentType, appointmentLocation, appointmentDate, appointmentStartTime, 
                appointmentStatus, appointmentPaymentStatus, technician, customer, counterStaff);
        
        this.feedbackID = feedbackID;
        this.customerFeedback = customerFeedback;
        this.technicianFeedback = technicianFeedback;
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
    public String toString() {
        return feedbackID + "|" + appointmentID + "|" + technician.getUserID() 
                + "|" + customer.getUserID() + "|" + customerFeedback + "|" + technicianFeedback;
    }    
}
