import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Objects;
import javax.imageio.ImageIO;
import javax.swing.*;
public class Login extends JFrame implements ActionListener,DbInfo{
    JPanel panel;
    JLabel lblUser, lblPassword;
    JTextField txtUser, txtPassword;
    JButton btnLogin, btnReset;
    BufferedImage dpImage = null;
    Login(){
        try {
            dpImage = ImageIO.read(Objects.requireNonNull(getClass().getClassLoader().getResource("images/dp.png")));
        } catch (IOException e) {
            e.printStackTrace();
        }
        dpImage = HospitalManager.resize(dpImage,150,150);
        JLabel picLabel = new JLabel(new ImageIcon(dpImage));
        picLabel.setBounds(120,30,150,150);

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
                //check username and password
                String userName = txtUser.getText();
                String passsword = txtPassword.getText();
                boolean loggedin = false;
                try{
                    Class.forName(MysqlDriver);
                    Connection con= DriverManager.getConnection(database, databaseUser,databsePassword);
                    PreparedStatement pstmt = con.prepareStatement(queryGetLoginData);
                    pstmt.setString(1, userName);
                    pstmt.setString(2, passsword);
                    ResultSet loginData = pstmt.executeQuery();


                    while(loginData.next()) {
                        System.out.println("User: "+loginData.getString(2));
                        loggedin = true;
                    }

                    con.close();
                    pstmt.close();
                    loginData.close();
                }
                catch(Exception ex){
                    System.out.println(ex);
                }

                if (loggedin){
                    this.setVisible(false);
                    Home home = new Home();
                }
                else {
                    txtUser.setText("");
                    txtPassword.setText("");
                    JOptionPane.showMessageDialog(null,"Incorrect username or password");
                }

            }
        }
        else if (e.getSource() == btnReset){
            txtUser.setText("");
            txtPassword.setText("");
        }
    }
}
