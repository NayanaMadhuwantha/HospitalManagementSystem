import javax.swing.*;

public class HospitalManager {
    public static void main(String args[]){
        try {
            UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
        } catch (Exception e) {
            System.err.println("Unable to load Windows look and feel. Reverting to default.");
        }

        Login login = new Login();
        //Home home = new Home();
        //PatientRegistation patientRegistation = new PatientRegistation();
        //PatientInformation patientInformation = new PatientInformation();
        //StaffInformation staffInformation = new StaffInformation();
    }
}
