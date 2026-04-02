
/**
 *
 * @author ejjaz
 */
public class Payment extends Appointment {
    private String paymentID;
    private double paymentAmount;
    private String paymentDate;
    
    public Payment(String paymentID, double paymentAmount, String paymentDate, 
            String appointmentID, String appointmentLocation, String appointmentType, String appointmentDate, 
            String appointmentStartTime, boolean appointmentStatus, boolean appointmentPaymentStatus, 
            Technician technician, Customer customer, CounterStaff counterStaff) {
        
        super(appointmentID, appointmentType, appointmentLocation, appointmentDate, appointmentStartTime, 
                appointmentStatus, appointmentPaymentStatus, technician, customer, counterStaff);
        
        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
    }
    
    public String getPaymentID() {
        return paymentID;
    }
    
    public double getPaymentAmount() {
        return paymentAmount;
    }
    
    public String getPaymentDate() {
        return paymentDate;
    }
    
    public void setCounterStaff(CounterStaff paymentCollector) {
        this.counterStaff = paymentCollector;
    }
    
    @Override
    public String toString() {
        return paymentID + "|" + paymentDate + "|" + appointmentID + "|" + appointmentLocation + "|" + 
                appointmentType + "|" + paymentAmount + "|" + customer.getUserID() + "|" + counterStaff.getUserID();
    }
}
