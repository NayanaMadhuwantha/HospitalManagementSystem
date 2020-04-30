import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PatientInformation extends JFrame implements ActionListener{
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
        }
        else {
            if (e.getSource() == btnSearch){
                System.out.println("search");
            }
            else if (e.getSource() == btnUpdate){
                System.out.println("update");
            }
            else if(e.getSource() == btnDelete){
                System.out.println("delete");
            }
        }
    }
}