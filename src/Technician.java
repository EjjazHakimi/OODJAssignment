
import java.time.LocalTime;

public class Technician extends User{
    
    public Technician(String userID, String username, String password, 
            String userRole) {
        
        super(userID, username, password, userRole);
    }
    
    public boolean isTechnicianAvailable(Appointment[] appointments, int appointmentCount, 
            String date, LocalTime startTime, LocalTime endTime) {
        
        for (int i = 0; i < appointmentCount; i++) {

            Appointment a = appointments[i];

            if (a == null) continue;
            
            if (a.getTechnician() == null) continue;

            if (!a.getTechnician().getUserID().equalsIgnoreCase(this.getUserID())) continue;

            if (!a.getAppointmentDate().equals(date)) continue;
            
            if (!"ASSIGNED".equalsIgnoreCase(a.getAppointmentStatus())) continue;

            LocalTime existingStart = a.getAppointmentStartTime();
            LocalTime existingEnd = a.getAppointmentEndTime();

            if (existingEnd == null) continue;

            boolean overlap = startTime.isBefore(existingEnd) && endTime.isAfter(existingStart);

            if (overlap) {
                return false;
            }
            
        }
        return true;
    }
    
    public boolean hasAssignedAppointment(Appointment[] appointments, int appointmentCount) {

        for (int i = 0; i < appointmentCount; i++) {
            Appointment a = appointments[i];

            if (a == null) continue;

            if (a.getTechnician() == null) continue;

            if (!a.getTechnician().getUserID().equalsIgnoreCase(this.getUserID())) continue;

            if ("ASSIGNED".equalsIgnoreCase(a.getAppointmentStatus())) {
                return true;
            }
        }
        return false;
    }
    
    @Override
    public boolean canBeDeleted(DataHandler dh) {
        Appointment[] appointments = dh.getAppointmentByTechnicianID(this.getUserID());
        return !hasAssignedAppointment(appointments, appointments.length);
    }
}
