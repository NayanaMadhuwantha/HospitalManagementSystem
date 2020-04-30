import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Login extends JFrame implements ActionListener{
    JPanel panel;
    JLabel lblUser, lblPassword;
    JTextField txtUser, txtPassword;
    JButton btnLogin, btnReset;



    Login(){
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("C:/Users/Nayana Madhuwantha/IdeaProjects/HospitalManagementSystem/src/dp.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture));
        picLabel.setBounds(10,10,200,200);

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
        btnLogin = new JButton("Login");
        btnLogin.setBounds(95,380,80,30);
        btnLogin.addActionListener(this);

        btnReset = new JButton("Reset");
        btnReset.setBounds(220,380,80,30);
        btnReset.addActionListener(this);

        panel = new JPanel();
        panel.setLayout(null);

        panel.add(lblUser);
        panel.add(txtUser);
        panel.add(lblPassword);
        panel.add(txtPassword);
        panel.add(btnLogin);
        panel.add(btnReset);
        panel.add(picLabel);

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
        if (e.getSource() == btnLogin){
            if (txtUser.getText().equals("") || txtPassword.getText().equals("")){
                JOptionPane.showMessageDialog(null,"Username or password missing");
            }
            else {
                Home home = new Home();
            }
        }
        else if (e.getSource() == btnReset){
            txtUser.setText("");
            txtPassword.setText("");
        }
    }
}
