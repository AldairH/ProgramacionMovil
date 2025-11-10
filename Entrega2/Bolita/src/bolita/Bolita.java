package Bolita;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Bolita extends JFrame implements ActionListener {
    JPanel pt, pd, pb;
    int posX = 0, posY = 0;
    JButton btnAdelante, btnAtras, btnArriba, btnAbajo;

    public Bolita() {
        setTitle("Bolita 01");
        setSize(400, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(3, 1));

        pt = new PanelTexto();
        pd = new PanelDibujo();
        pb = new JPanel();
        pb.setLayout(new GridLayout(1, 4));

        btnAtras     = new JButton("<<");
        btnAdelante  = new JButton(">>");
        btnArriba    = new JButton("Arriba");
        btnAbajo     = new JButton("Abajo");

        btnAtras.addActionListener(this);
        btnAdelante.addActionListener(this);
        btnArriba.addActionListener(this);
        btnAbajo.addActionListener(this);

        pb.add(btnAtras);
        pb.add(btnAdelante);
        pb.add(btnArriba);
        pb.add(btnAbajo);

        add(pt);
        add(pd);
        add(pb);
    }
    
    public void inicializarListeners() {
    btnAtras.addActionListener(this);
    btnAdelante.addActionListener(this);
    btnArriba.addActionListener(this);
    btnAbajo.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        int maxX = Math.max(0, pd.getWidth()  - 20);
        int maxY = Math.max(0, pd.getHeight() - 20);

        if (e.getSource().equals(btnAdelante)) {         // mover derecha
            posX = (posX + 10);
            if (posX > maxX) posX = 0;
        }
        if (e.getSource().equals(btnAtras)) {            // mover izquierda
            posX = (posX - 10);
            if (posX < 0) posX = maxX;
        }
        if (e.getSource().equals(btnArriba)) {           // mover arriba
            posY = (posY - 10);
            if (posY < 0) posY = maxY;
        }
        if (e.getSource().equals(btnAbajo)) {            // mover abajo
            posY = (posY + 10);
            if (posY > maxY) posY = 0;
        }
        pd.repaint();
    }

    private class PanelTexto extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.BLUE);
            g.setFont(new Font("Arial", Font.PLAIN, 20));
            g.drawString("Juego Bolita", 10, 20);
        }
    }

    private class PanelDibujo extends JPanel {
        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.setColor(Color.ORANGE);
            g.fillOval(posX, posY, 20, 20);
        }
    }

    public static void main(String[] args) {
        Bolita b = new Bolita();
        b.inicializarListeners();
        b.setVisible(true);
    }
}
//Hernandez Juarez Aldair