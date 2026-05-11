
public class Customer extends User {
    
    public Customer(String userID, String username, String password, 
            String userRole) {
        
        super(userID, username, password, userRole);
    }
    
    public boolean hasAssignedAppointment(Appointment[] appointments, int appointmentCount) {

        for (int i = 0; i < appointmentCount; i++) {
            Appointment a = appointments[i];

            if (a == null) continue;

            if (a.getCustomer() == null) continue;

            if (!a.getCustomer().getUserID().equalsIgnoreCase(this.getUserID())) continue;

            if ("ASSIGNED".equalsIgnoreCase(a.getAppointmentStatus())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean canBeDeleted(DataHandler dh) {
        Appointment[] appointments = dh.getAppointmentByUserID(this.getUserID());
        return !hasAssignedAppointment(appointments, appointments.length);
    }
}
