import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientRegistation extends JFrame implements ActionListener,DbInfo{
    JPanel panel;
    JLabel lblTitle,lblName,lblGender,lblAge,lblDate,lblContact,lblAddress;
    JTextField txtName,txtAge,txtDate,txtContact,txtAddress;
    JButton btnAdd,btnReset;
    JRadioButton rdoMale,rdoFemale;
    PatientRegistation(){
        lblTitle = new JLabel("Register new patient");
        lblTitle.setBounds(70,10,200,20);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 20));

        lblName = new JLabel("Name :");
        lblName.setBounds(10,50,60,20);
        txtName = new JTextField();
        txtName.setBounds(100,50,200,20);

        lblGender = new JLabel("Gender :");
        lblGender.setBounds(10,90,60,20);

        rdoMale = new JRadioButton("Male");
        rdoMale.setSelected(true);
        rdoMale.setSize(75, 20);
        rdoMale.setLocation(100, 90);
        rdoMale.addActionListener(this);

        rdoFemale = new JRadioButton("Female");
        rdoFemale.setSelected(false);
        rdoFemale.setSize(80, 20);
        rdoFemale.setLocation(180, 90);
        rdoFemale.addActionListener(this);

        lblAge = new JLabel("Age :");
        lblAge.setBounds(10,130,60,20);
        txtAge = new JTextField();
        txtAge.setBounds(100,130,200,20);

        lblDate = new JLabel("Date :");
        lblDate.setBounds(10,170,60,20);
        txtDate = new JTextField();
        txtDate.setBounds(100,170,200,20);

        lblContact = new JLabel("Contact :");
        lblContact.setBounds(10,210,60,20);
        txtContact = new JTextField();
        txtContact.setBounds(100,210,200,20);

        lblAddress = new JLabel("Address :");
        lblAddress.setBounds(10,250,60,20);
        txtAddress = new JTextField();
        txtAddress.setBounds(100,250,200,20);

        btnAdd = new JButton("Add Patient");
        btnAdd.setBounds(50,300,90,30);
        btnAdd.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setBounds(180,300,90,30);
        btnReset.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(null);

        panel.add(lblTitle);
        panel.add(lblName);
        panel.add(txtName);
        panel.add(lblGender);
        panel.add(lblAge);
        panel.add(txtAge);
        panel.add(lblDate);
        panel.add(txtDate);
        panel.add(lblContact);
        panel.add(txtContact);
        panel.add(lblAddress);
        panel.add(txtAddress);
        panel.add(btnAdd);
        panel.add(btnReset);
        panel.add(rdoMale);
        panel.add(rdoFemale);

        add(panel,  BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Patient Registration");
        setSize(450,350);
        setVisible(true);
        setBounds(100,100,330,400);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnReset){
            clear();
        }
        else if (e.getSource() == btnAdd){
            if (txtName.getText().equals("") || txtAge.getText().equals("") || txtDate.getText().equals("") || txtContact.getText().equals("") || txtAddress.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Please fill all fields");
            }
            else {
                //enter patient data
                String gender;
                String patiendID = "";
                if (rdoMale.isSelected()){
                    gender = "Male";
                }
                else {
                    gender = "Female";
                }
                try{
                    Class.forName(MysqlDriver);
                    Connection con= DriverManager.getConnection(database, databaseUser,databsePassword);
                    PreparedStatement pstmt = con.prepareStatement(querryInsertPatient);
                    pstmt.setString(1, txtName.getText()); //name,gender,age,date,contact,address
                    pstmt.setString(2, gender);
                    pstmt.setString(3, txtAge.getText());
                    pstmt.setString(4, txtDate.getText());
                    pstmt.setString(5, txtContact.getText());
                    pstmt.setString(6, txtAddress.getText());
                    int count = pstmt.executeUpdate();

                    if (count > 0){
                        pstmt = con.prepareStatement(getLatustPatient);
                        ResultSet data = pstmt.executeQuery();
                        while (data.next()){
                            patiendID = data.getString(1);
                        }
                        JOptionPane.showMessageDialog(null,txtName.getText()+"'s patient ID is "+patiendID);

                        clear();
                    }

                    con.close();
                    pstmt.close();
                }
                catch(Exception ex){
                    JOptionPane.showMessageDialog(null,"Something went wrong");
                    clear();
                    System.out.println(ex);
                }
            }
        }
        else if (e.getSource() == rdoMale){
            rdoFemale.setSelected(false);
            rdoMale.setSelected(true);
        }
        else if (e.getSource() == rdoFemale){
            rdoMale.setSelected(false);
            rdoFemale.setSelected(true);
        }
    }
    public void clear(){
        txtName.setText("");
        txtAge.setText("");
        txtDate.setText("");
        txtContact.setText("");
        txtAddress.setText("");
    }
}
