
import java.time.LocalDate;


/**
 *
 * @author ejjaz
 */
public class Manager extends User {
    
    public Manager(String userID, String username, String password, 
            String userRole) {
        
        super(userID, username, password, userRole);
    }
    
    public int[] getAppointmentsPerMonth(Appointment[] appointments, int appointmentCount) {

        int[] monthCount = new int[12];

        for (int i = 0; i < appointmentCount; i++) {

            Appointment a = appointments[i];

            if (a == null) continue;

            LocalDate date = LocalDate.parse(a.getAppointmentDate());

            int monthIndex = date.getMonthValue() - 1;
            monthCount[monthIndex]++;
        }

        return monthCount;
    }
    
    public int[] getPaymentCounts(Appointment[] appointments, int appointmentCount) {

        int paid = 0, unpaid = 0, pending = 0;

        for (int i = 0; i < appointmentCount; i++) {

            Appointment a = appointments[i];

            if (a == null) continue;

            String status = a.getAppointmentPaymentStatus().trim();

            if (status.equalsIgnoreCase("PAID")) {
                paid++;
            } else if (status.equalsIgnoreCase("UNPAID")) {
                unpaid++;
            } else if (status.equalsIgnoreCase("PENDING")) {
                pending++;
            }
        }
        return new int[]{paid, unpaid, pending};
    }
    
    public double[] getMonthlyRevenue(Payment[] payments, int paymentCount) {

    double[] revenue = new double[12];

    for (int i = 0; i < paymentCount; i++) {

        Payment p = payments[i];
        if (p == null) continue;

        if (!p.getPaymentStatus().equalsIgnoreCase("COLLECTED")) continue;

        LocalDate date = LocalDate.parse(p.getPaymentDate());
        int monthIndex = date.getMonthValue() - 1;


        revenue[monthIndex] += p.getPaymentAmount();
    }

    return revenue;
}
}
