package JavaApplication;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class JavaApplication extends JFrame implements ActionListener {
    JButton b1, b2, b3;
    JTextField txt1, txt2;
    JPanel panel01, panelR;
    JLabel et01, et02, etR;

    public JavaApplication() {
        setTitle("Mi ventana");
        setSize(350, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 1));
        setResizable(false);

        b1 = new JButton("Sumar");
        b2 = new JButton("Limpiar");
        b3 = new JButton("Restar");

        txt1 = new JTextField(20);
        txt2 = new JTextField(20);
        et01 = new JLabel("Número 1:");
        et02 = new JLabel("Número 2:");
        etR = new JLabel();
        panel01 = new JPanel();
        panelR = new JPanel();
        panelR.setBackground(Color.GRAY);

        b1.addActionListener(this);
        b2.addActionListener(this);
        b3.addActionListener(this);

        panel01.add(et01);
        panel01.add(txt1);
        panel01.add(et02);
        panel01.add(txt2);
        panel01.add(b1);
        panel01.add(b3);
        panel01.add(b2);

        panelR.add(etR);

        add(panel01);
        add(panelR);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(b1)) { // Sumar
            int op1 = Integer.parseInt(txt1.getText());
            int op2 = Integer.parseInt(txt2.getText());
            int res = op1 + op2;
            JOptionPane.showMessageDialog(this, "Resultado: " + res);
            etR.setText("Resultado: " + res);
        }

        if (e.getSource().equals(b3)) { // Restar
            int op1 = Integer.parseInt(txt1.getText());
            int op2 = Integer.parseInt(txt2.getText());
            int res = op1 - op2;
            JOptionPane.showMessageDialog(this, "Resultado: " + res);
            etR.setText("Resultado: " + res);
        }

        if (e.getSource().equals(b2)) { // Limpiar
            txt1.setText("");
            txt2.setText("");
            etR.setText("");
        }
    }

    public static void main(String[] args) {
        JavaApplication v = new JavaApplication();
        v.setVisible(true);
    }
}
//Hernandez Juarez Aldair