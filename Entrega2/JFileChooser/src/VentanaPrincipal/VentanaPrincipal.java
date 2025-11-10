import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.io.File;
import java.io.IOException;

public class VentanaPrincipal extends JFrame {

    private JTextArea areaTexto;
    private JTextField campoNombre;
    private JButton botonGuardar, botonGuardarComo;

    public VentanaPrincipal() {
        super("Creador de archivos txt");

        areaTexto = new JTextArea(12, 36);
        campoNombre = new JTextField(20);
        botonGuardar = new JButton("Guardar (carpeta actual)");
        botonGuardarComo = new JButton("Guardar como…");

        JPanel panelNombre = new JPanel(new FlowLayout(FlowLayout.LEFT));
        panelNombre.add(new JLabel("Nombre del archivo:"));
        panelNombre.add(campoNombre);

        JScrollPane scroll = new JScrollPane(areaTexto);

        JPanel panelBotones = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        panelBotones.add(botonGuardar);
        panelBotones.add(botonGuardarComo);

        setLayout(new BorderLayout(8, 8));
        add(panelNombre, BorderLayout.NORTH);
        add(scroll, BorderLayout.CENTER);
        add(panelBotones, BorderLayout.SOUTH);

        botonGuardar.addActionListener(this::guardarEnCarpetaActual);
        botonGuardarComo.addActionListener(this::guardarConChooser);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    private void guardarEnCarpetaActual(ActionEvent e) {
        String nombre = sanitizarNombre(campoNombre.getText().trim());
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre de archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        String ruta = nombre.endsWith(".txt") ? nombre : (nombre + ".txt");
        try {
            ArchivoTexto.guardarArchivo(ruta, areaTexto.getText());
            JOptionPane.showMessageDialog(this, "Archivo guardado como " + ruta);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void guardarConChooser(ActionEvent e) {
        String nombre = sanitizarNombre(campoNombre.getText().trim());
        if (nombre.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Ingrese un nombre de archivo.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        JFileChooser fc = new JFileChooser();
        fc.setDialogTitle("Guardar archivo de texto");
        fc.setSelectedFile(new File(nombre.endsWith(".txt") ? nombre : (nombre + ".txt")));
        fc.setFileFilter(new FileNameExtensionFilter("Archivos de texto (*.txt)", "txt"));

        int op = fc.showSaveDialog(this);
        if (op != JFileChooser.APPROVE_OPTION) return;

        File destino = fc.getSelectedFile();
        if (!destino.getName().toLowerCase().endsWith(".txt")) {
            destino = new File(destino.getParentFile(), destino.getName() + ".txt");
        }

        if (destino.exists()) {
            int ok = JOptionPane.showConfirmDialog(this,
                    "El archivo ya existe.\n¿Deseas sobrescribirlo?",
                    "Confirmar sobrescritura",
                    JOptionPane.OK_CANCEL_OPTION,
                    JOptionPane.WARNING_MESSAGE);
            if (ok != JOptionPane.OK_OPTION) return;
        }

        try {
            ArchivoTexto.guardarArchivo(destino.getAbsolutePath(), areaTexto.getText());
            JOptionPane.showMessageDialog(this, "Archivo guardado en:\n" + destino.getAbsolutePath());
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar:\n" + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private String sanitizarNombre(String nombre) {
        return nombre.replaceAll("[\\\\/:*?\"<>|]", "").trim();
    }

    public static void main(String[] args) {
        new VentanaPrincipal();
    }
}
//Hernandez Juarez Aldair