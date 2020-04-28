import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Home extends JFrame implements ActionListener{
    JPanel panel;
    JButton btnPatientReg, btnPatientInfo, btnStafinfo;

    Home(){
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
            System.out.println("patient reg");
        }
        else if (e.getSource() == btnPatientInfo){
            System.out.println("patient info");
        }
        else if (e.getSource() == btnStafinfo){
            System.out.println("staff info");
        }
    }
}
