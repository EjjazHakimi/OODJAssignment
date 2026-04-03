
/**
 *
 * @author ejjaz
 */
public class Payment implements FileHandlerInterface {
    private String paymentID;
    private double paymentAmount;
    private String paymentDate;
    private Appointment appointment;
    private Customer customer;
    private CounterStaff counterStaff;
    
    public Payment(String paymentID, double paymentAmount, String paymentDate, 
            Appointment appointment, Customer customer, CounterStaff counterStaff) {

        this.paymentID = paymentID;
        this.paymentAmount = paymentAmount;
        this.paymentDate = paymentDate;
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
    
    public void setCounterStaff(CounterStaff paymentCollector) {
        this.counterStaff = paymentCollector;
    }
    
    @Override
    public String toString() {
        return paymentID + "|" + paymentAmount + "|" +  paymentDate + "|" +
                appointment.getAppointmentID() + "|" + customer.getUserID() + "|" + counterStaff.getUserID();
    }
    
    @Override 
    public String getFileKey() {
        return paymentID;
    }
}
