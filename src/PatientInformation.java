import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class PatientInformation extends JFrame implements ActionListener,DbInfo{
    JPanel panel;
    JLabel lblId,lblTitle,lblName,lblGender,lblAge,lblDate,lblContact,lblAddress;
    JTextField txtId,txtName,txtAge,txtDate,txtContact,txtAddress;
    JButton btnSearch,btnUpdate,btnDelete;
    JRadioButton rdoMale,rdoFemale;
    PatientInformation(){
        lblTitle = new JLabel("Patient Information");
        lblTitle.setBounds(70,10,200,20);
        lblTitle.setFont(new Font("Arial", Font.PLAIN, 20));

        lblId = new JLabel("Patient ID :");
        lblId.setBounds(10,50,60,20);
        txtId = new JTextField();
        txtId.setBounds(100,50,200,20);

        lblName = new JLabel("Name :");
        lblName.setBounds(10,90,60,20);
        txtName = new JTextField();
        txtName.setBounds(100,90,200,20);

        lblGender = new JLabel("Gender :");
        lblGender.setBounds(10,130,60,20);

        rdoMale = new JRadioButton("Male");
        rdoMale.setSelected(true);
        rdoMale.setSize(75, 20);
        rdoMale.setLocation(100, 130);
        rdoMale.addActionListener(this);

        rdoFemale = new JRadioButton("Female");
        rdoFemale.setSelected(false);
        rdoFemale.setSize(80, 20);
        rdoFemale.setLocation(180, 130);
        rdoFemale.addActionListener(this);

        lblAge = new JLabel("Age :");
        lblAge.setBounds(10,170,60,20);
        txtAge = new JTextField();
        txtAge.setBounds(100,170,200,20);

        lblDate = new JLabel("Date :");
        lblDate.setBounds(10,210,60,20);
        txtDate = new JTextField();
        txtDate.setBounds(100,210,200,20);

        lblContact = new JLabel("Contact :");
        lblContact.setBounds(10,250,60,20);
        txtContact = new JTextField();
        txtContact.setBounds(100,250,200,20);

        lblAddress = new JLabel("Address :");
        lblAddress.setBounds(10,300,60,20);
        txtAddress = new JTextField();
        txtAddress.setBounds(100,300,200,20);

        btnSearch = new JButton("Search");
        btnSearch.setBounds(10,350,70,30);
        btnSearch.addActionListener(this);

        btnUpdate = new JButton("Update");
        btnUpdate.setBounds(120,350,70,30);
        btnUpdate.addActionListener(this);

        btnDelete= new JButton("Delete");
        btnDelete.setBounds(230,350,70,30);
        btnDelete.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(null);

        panel.add(lblId);
        panel.add(txtId);
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
        panel.add(btnSearch);
        panel.add(btnUpdate);
        panel.add(btnDelete);
        panel.add(rdoMale);
        panel.add(rdoFemale);

        add(panel,  BorderLayout.CENTER);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("Patient Information");
        setSize(450,350);
        setVisible(true);
        setBounds(100,100,330,450);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == rdoMale){
            rdoMale.setSelected(true);
            rdoFemale.setSelected(false);
        }
        else if (e.getSource() == rdoFemale){
            rdoMale.setSelected(false);
            rdoFemale.setSelected(true);
        }
        else if (txtId.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Please Enter Patient ID");
            clear();
        }
        else {
            if (e.getSource() == btnSearch){
                //search patient
                try{
                    Class.forName(MysqlDriver);
                    Connection con=DriverManager.getConnection(database, databaseUser,databsePassword);
                    PreparedStatement pstmt = con.prepareStatement(querryGetPatient);
                    pstmt.setString(1, txtId.getText());
                    ResultSet patientData = pstmt.executeQuery();

                    if (!patientData.isBeforeFirst()){
                        clear();
                        JOptionPane.showMessageDialog(null,"No matching data");
                    }
                    else {
                        while(patientData.next()) {
                            txtName.setText(patientData.getString(2));
                            if (patientData.getString(3).equals("Male")){
                                rdoMale.setSelected(true);
                                rdoFemale.setSelected(false);
                            }
                            else {
                                rdoMale.setSelected(false);
                                rdoFemale.setSelected(true);
                            }
                            txtAge.setText(patientData.getString(4));
                            txtDate.setText(patientData.getString(5));
                            txtContact.setText(patientData.getString(6));
                            txtAddress.setText(patientData.getString(7));
                        }
                    }

                    con.close();
                    pstmt.close();
                    patientData.close();
                }
                catch(Exception ee){
                    JOptionPane.showMessageDialog(null,"Something went wrong");
                    System.out.println(ee);
                }
            }
            else if (e.getSource() == btnUpdate){
                if (txtName.getText().equals("") || txtAge.getText().equals("") || txtDate.getText().equals("") || txtContact.getText().equals("") || txtAddress.getText().equals("")){
                    JOptionPane.showMessageDialog(null,"Please fill all fields");
                }
                else {
                    //update patient
                    String gender;
                    if (rdoMale.isSelected()){
                        gender = "Male";
                    }
                    else {
                        gender = "Female";
                    }
                    try{
                        Class.forName(MysqlDriver);
                        Connection con= DriverManager.getConnection(database, databaseUser,databsePassword);
                        PreparedStatement pstmt = con.prepareStatement(queryUpdatePatient);
                        pstmt.setString(1, txtName.getText()); //name,gender,age,date,contact,address
                        pstmt.setString(2, gender);
                        pstmt.setString(3, txtAge.getText());
                        pstmt.setString(4, txtDate.getText());
                        pstmt.setString(5, txtContact.getText());
                        pstmt.setString(6, txtAddress.getText());
                        pstmt.setString(7, txtId.getText());
                        int count = pstmt.executeUpdate();

                        if (count > 0){
                            JOptionPane.showMessageDialog(null,"Updated Successfully");
                            clear();
                        }
                        else {
                            JOptionPane.showMessageDialog(null,"No matching data");
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
            else if(e.getSource() == btnDelete){
                //delete patient
                try{
                    Class.forName(MysqlDriver);
                    Connection con= DriverManager.getConnection(database, databaseUser,databsePassword);
                    PreparedStatement pstmt = con.prepareStatement(queryDeletePatient);
                    pstmt.setString(1,txtId.getText());
                    int count = pstmt.executeUpdate();

                    if (count > 0){
                        JOptionPane.showMessageDialog(null,"Delete Successfully");
                        clear();
                    }
                    else {
                        JOptionPane.showMessageDialog(null,"No matching data");
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
    }
    public void clear(){
        txtName.setText("");
        txtAge.setText("");
        txtDate.setText("");
        txtContact.setText("");
        txtAddress.setText("");
    }
}
