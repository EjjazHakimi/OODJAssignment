
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;


/**
 *
 * @author ejjaz
 */
public class Appointment implements FileHandlerInterface {
    private String appointmentID;
    private String appointmentType;
    private String appointmentLocation;
    private String appointmentDate;
    private LocalTime appointmentStartTime;
    private LocalTime appointmentEndTime;
    private String appointmentStatus;
    private String appointmentPaymentStatus;
    private Technician technician;
    private Customer customer;
    private CounterStaff counterStaff;
    
    public Appointment(String appointmentID, String appointmentLocation, 
            String appointmentType, String appointmentDate, LocalTime appointmentStartTime, LocalTime appointmentEndTime, 
            String appointmentStatus, String appointmentPaymentStatus, Technician technician, Customer customer, CounterStaff counterStaff) {
        this.appointmentID = appointmentID;
        this.appointmentLocation = appointmentLocation;
        this.appointmentType = appointmentType;
        this.appointmentDate = appointmentDate;
        this.appointmentStartTime = appointmentStartTime;
        this.appointmentEndTime = appointmentEndTime;
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
    
    public LocalTime getAppointmentStartTime() {
        return appointmentStartTime;
    }
    
    public LocalTime getAppointmentEndTime() {
        return appointmentEndTime;
    }
    
    public String getAppointmentStatus() {
        return appointmentStatus;
    }
    
    public String getAppointmentPaymentStatus() {
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
    
    public void setAppointmentType(String newAppointmentType) {
        this.appointmentType = newAppointmentType;
    }
    
    public void setAppointmentDate(String newAppointmentDate) {
        this.appointmentDate = newAppointmentDate;
    }
    
    public void setAppointmentStartTime(LocalTime newAppointmentStartTime) {
        this.appointmentStartTime = newAppointmentStartTime;
    }
    
    public void setAppointmentEndTime(LocalTime newAppointmentEndTime) {
        this.appointmentEndTime = newAppointmentEndTime;
    }
    
    public void setAppointmentStatus(String currentAppointmentStatus) {
        this.appointmentStatus = currentAppointmentStatus;
    }
    
    public void setAppointmentPaymentStatus(String currentAppointmentPaymentStatus) {
        this.appointmentPaymentStatus = currentAppointmentPaymentStatus;
    }
    
    public void setTechnician(Technician newTechnician) {
        this.technician = newTechnician;
    }
    
    public void setCounterStaff(CounterStaff newCounterStaff) {
        this.counterStaff = newCounterStaff;
    }
    
    @Override
    public String toString(){
        return appointmentID + "|" + appointmentLocation + "|" + appointmentType + "|" + 
                appointmentDate + "|" + appointmentStartTime.format(DateTimeFormatter.ofPattern("HH:mm")) + "|" + 
                appointmentEndTime.format(DateTimeFormatter.ofPattern("HH:mm")) + "|" + appointmentStatus + "|" + appointmentPaymentStatus + "|" +
                technician.getUserID() + "|" + customer.getUserID() + "|" + counterStaff.getUserID();
    }

    @Override
    public String getFileKey() {
        return appointmentID;
    }
}
