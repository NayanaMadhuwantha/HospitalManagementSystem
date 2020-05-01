import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Objects;

public class Home extends JFrame implements ActionListener{
    JPanel panel;
    JButton btnPatientReg, btnPatientInfo, btnStafinfo;
    BufferedImage patientRegImg = null;
    BufferedImage patientInfoImg = null;
    BufferedImage StaffInfoImg = null;
    JLabel patientReg, patientInfo, staffInfo;
    Home(){

        try {
            patientRegImg =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("images/patientReg.png")));
            patientInfoImg =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("images/patientInfo.png")));
            StaffInfoImg =  ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("images/staffInfo.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        patientRegImg = HospitalManager.resize(patientRegImg,150,150);
        patientInfoImg = HospitalManager.resize(patientInfoImg,150,150);
        StaffInfoImg = HospitalManager.resize(StaffInfoImg,150,150);

        patientReg = new JLabel(new ImageIcon(patientRegImg));
        patientReg.setBounds(80,80,150,150);
        patientInfo = new JLabel(new ImageIcon(patientInfoImg));
        patientInfo.setBounds(280,80,150,150);
        staffInfo = new JLabel(new ImageIcon(StaffInfoImg));
        staffInfo.setBounds(480,80,150,150);

        btnPatientReg = new JButton("Patient Registration");
        btnPatientReg.setBounds(80,300,150,50);
        btnPatientReg.addActionListener(this);

        btnPatientInfo = new JButton("Patient Information");
        btnPatientInfo.setBounds(280,300,150,50);
        btnPatientInfo.addActionListener(this);

        btnStafinfo = new JButton("Staff Information");
        btnStafinfo.setBounds(480,300,150,50);
        btnStafinfo.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(null);

        panel.add(btnPatientReg);
        panel.add(btnPatientInfo);
        panel.add(btnStafinfo);
        panel.add(patientReg);
        panel.add(patientInfo);
        panel.add(staffInfo );

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel,  BorderLayout.CENTER);
        setTitle("Home");
        setSize(450,350);
        setVisible(true);
        setBounds(100,100,730,450);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnPatientReg){
            PatientRegistation patientRegistation = new PatientRegistation(this);
            this.setEnabled(false);
        }
        else if (e.getSource() == btnPatientInfo){
            PatientInformation patientInformation = new PatientInformation(this);
            this.setEnabled(false);
        }
        else if (e.getSource() == btnStafinfo){
            StaffInformation staffInformation = new StaffInformation(this);
            this.setEnabled(false);
        }
    }
}
