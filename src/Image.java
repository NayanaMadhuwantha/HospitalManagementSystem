import javax.swing.*;
import java.awt.*;

class Image extends JFrame {
    private static JLabel instructions;
    private static JLabel p;
    Image(){
        instructions = new JLabel();
        add(new JLabel(new ImageIcon("dp.png")), BorderLayout.CENTER);
        instructions.setText("<html>YOU are the Arcane Inventor... A magical source that is responsible" +
                "<p> for creating all of the life-forms in existence. It is the Arcane " +
                "<p>Inventor's job to combine elements of MIGHT, SOUL, and WISDOM to bring " +
                "<p>These beings into existence. Good Luck...</html>");
        instructions.setFont(new Font("Narkisim", Font.BOLD, 20));
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);
        add(instructions, BorderLayout.SOUTH);
    }
}