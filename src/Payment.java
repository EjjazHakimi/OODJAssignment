
/**
 *
 * @author ejjaz
 */
public class Payment implements FileHandlerInterface {
    private String paymentID;
    private double paymentAmount;
    private String paymentDate;
    private String paymentStatus;
    private Appointment appointment;
    private Customer customer;
    private CounterStaff counterStaff;
    
    public Payment(String paymentID, double paymentAmount, String paymentDate, String paymentStatus,
            Appointment appointment, Customer customer, CounterStaff counterStaff) {

        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
        this.paymentStatus = paymentStatus;
        this.appointment = appointment;
        this.customer = customer;
        this.counterStaff = counterStaff;
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
    
    public String getPaymentStatus() {
        return paymentStatus;
    }
    
    public Appointment getAppointment() {
        return appointment;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public CounterStaff getCounterStaff() {
        return counterStaff;
    }
    
    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
    
    public void setCounterStaff(CounterStaff paymentCollector) {
        this.counterStaff = paymentCollector;
    }
    
    @Override
    public String toString() {
        return paymentID + "|" + String.format("%.2f", paymentAmount) + "|" +  paymentDate + "|" + paymentStatus + "|" +
                appointment.getAppointmentID() + "|" + customer.getUserID() + "|" + 
                (counterStaff != null ? counterStaff.getUserID() : "null");
    }
    
    @Override 
    public String getFileKey() {
        return paymentID;
    }
}
