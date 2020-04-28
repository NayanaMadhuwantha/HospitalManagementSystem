import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
public class Login extends JFrame implements ActionListener{
    JPanel panel;
    JLabel lblUser, lblPassword;
    JTextField txtUser, txtPassword;
    JButton btnSubmit;
    Login(){
        // Username Label
        lblUser = new JLabel();
        lblUser.setText("User Name");
        lblUser.setBounds(100,220,100,30);

        txtUser = new JTextField();
        txtUser.setBounds(200,220,100,30);

        // Password Label
        lblPassword = new JLabel();
        lblPassword.setText("Password");
        lblPassword.setBounds(100,250,100,100);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(200,285,100,30);
        // Submit
        btnSubmit = new JButton("SUBMIT");
        btnSubmit.setBounds(140,380,100,30);
        btnSubmit.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(null);

        panel.add(lblUser);
        panel.add(txtUser);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnSubmit);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        add(panel,  BorderLayout.CENTER);
        setTitle("Login");
        setSize(450,350);
        setVisible(true);
        setBounds(100,100,400,500);
        setResizable(false);
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        setLocation(dim.width/2-this.getSize().width/2, dim.height/2-this.getSize().height/2);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSubmit){
            System.out.println("test");
            Home home = new Home();
        }
    }
}
