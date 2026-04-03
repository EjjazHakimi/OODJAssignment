
/**
 *
 * @author ejjaz
 */
public class Appointment implements FileHandlerInterface {
    private String appointmentID;
    private String appointmentType;
    private String appointmentLocation;
    private String appointmentDate;
    private String appointmentStartTime;
    private boolean appointmentStatus;
    private boolean appointmentPaymentStatus;
    private Technician technician;
    private Customer customer;
    private CounterStaff counterStaff;
    
    public Appointment(String appointmentID, String appointmentLocation, 
            String appointmentType, String appointmentDate, String appointmentStartTime, 
            boolean appointmentStatus, boolean appointmentPaymentStatus, Technician technician, Customer customer, CounterStaff counterStaff) {
        this.appointmentID = appointmentID;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentStatus = appointmentStatus;
        this.appointmentPaymentStatus = appointmentPaymentStatus;
        this.technician = technician;
        this.customer = customer;
        this.counterStaff = counterStaff;
    }
    
    public String getAppointmentID() {
        return appointmentID;
    }
    
    public String getAppointmentLocation() {
        return appointmentLocation;
    }
    
    public String getAppointmentType() {
        return appointmentType;
    }
    
    public String getAppointmentDate() {
        return appointmentDate;
    }
    
    public String getAppointmentStartTime() {
        return appointmentStartTime;
    }
    
    public boolean getAppointmentStatus() {
        return appointmentStatus;
    }
    
    public boolean getAppointmentPaymentStatus() {
        return appointmentPaymentStatus;
    }
    
    public Technician getTechnician() {
        return technician;
    }
    
    public Customer getCustomer() {
        return customer;
    }
    
    public CounterStaff getCounterStaff() {
        return counterStaff;
    }
    
    public void setAppointmentDate(String newAppointmentDate) {
        this.appointmentDate = newAppointmentDate;
    }
    
    public void setAppointmentStartTime(String newAppointmentStartTime) {
        this.appointmentStartTime = newAppointmentStartTime;
    }
    
    public void setAppointmentStatus(boolean currentAppointmentStatus) {
        this.appointmentStatus = !currentAppointmentStatus;
    }
    
    public void setAppointmentPaymentStatus(boolean currentAppointmentPaymentStatus) {
        this.appointmentPaymentStatus = !currentAppointmentPaymentStatus;
    }
    
    public void setTechnician(Technician newTechnician) {
        this.technician = newTechnician;
    }
    
    @Override
    public String toString(){
        return appointmentID + "|" + appointmentLocation + "|" + appointmentType + "|" + 
                appointmentDate + "|" + appointmentStartTime + "|" + 
                appointmentStatus + "|" + appointmentPaymentStatus + "|" +
                technician.getUserID() + "|" + customer.getUserID() + "|" + counterStaff.getUserID();
    }

    @Override
    public String getFileKey() {
        return appointmentID;
    }
}
