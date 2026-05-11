
import java.time.LocalTime;

public class CounterStaff extends User {
    
    public CounterStaff(String userID, String username, String password, 
            String userRole) {
        
        super(userID, username, password, userRole);
    }
    
    public LocalTime calculateEndTime(String appointmentType, LocalTime startTime) {
        switch (appointmentType) {
            case "NORMAL":
                return startTime.plusHours(1);
            case "MAJOR":
                return startTime.plusHours(3);
            default:
                return null;
        }
    }
    
    public boolean canAssignTechnician(Technician technician, Appointment[] appointments, int appointmentCount,
            Appointment appointment, LocalTime appointmentEndTime) {

        if (technician == null || appointment == null) {
            return false;
        }
        
        boolean available = technician.isTechnicianAvailable(appointments, appointmentCount, appointment.getAppointmentDate(),
                appointment.getAppointmentStartTime(), appointmentEndTime);

        if (!available) {
            return false;
        }

        int activeJobs = 0;

        for (int i = 0; i < appointmentCount; i++) {
            Appointment a = appointments[i];
            if (a == null) continue;

            if (a.getTechnician() != null &&
                    a.getTechnician().getUserID().equalsIgnoreCase(technician.getUserID()) &&
                    "ASSIGNED".equalsIgnoreCase(a.getAppointmentStatus())) {
                
                activeJobs++;
            }
        }

        if (activeJobs >= 5) {
            return false;
        }
        return true;
    }
}
