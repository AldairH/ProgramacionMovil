package gestionusuarios;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.List;

public class InterfazGrafica extends JFrame {
    private JTable tabla;
    private DefaultTableModel modelo;
    private List<Usuario> usuarios;

    public InterfazGrafica() {
        super("Gestión de Usuarios");

        usuarios = UsuarioManager.cargarUsuarios();

        modelo = new DefaultTableModel(new Object[]{"Nombre", "Correo"}, 0) {
            @Override public boolean isCellEditable(int r, int c) { return false; } // no editar directo
        };
        tabla = new JTable(modelo);
        tabla.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // selección única
        cargarTabla();

        JButton btnAgregar  = new JButton("Agregar");
        JButton btnEditar   = new JButton("Editar");
        JButton btnEliminar = new JButton("Eliminar"); // ← NUEVO

        btnAgregar.addActionListener(e -> agregarUsuario());
        btnEditar.addActionListener(e -> editarUsuario());
        btnEliminar.addActionListener(e -> eliminarUsuario()); // ← NUEVO

        // Atajo de teclado: tecla SUPR para eliminar
        tabla.getInputMap(JComponent.WHEN_ANCESTOR_OF_FOCUSED_COMPONENT)
             .put(KeyStroke.getKeyStroke(KeyEvent.VK_DELETE, 0), "eliminarFila");
        tabla.getActionMap().put("eliminarFila", new AbstractAction() {
            @Override public void actionPerformed(java.awt.event.ActionEvent e) { eliminarUsuario(); }
        });

        JPanel panelBotones = new JPanel();
        panelBotones.add(btnAgregar);
        panelBotones.add(btnEditar);
        panelBotones.add(btnEliminar); // ← NUEVO

        this.setLayout(new BorderLayout());
        this.add(new JScrollPane(tabla), BorderLayout.CENTER);
        this.add(panelBotones, BorderLayout.SOUTH);

        this.setSize(500, 300);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    private void cargarTabla() {
        modelo.setRowCount(0);
        for (Usuario u : usuarios) {
            modelo.addRow(new Object[]{u.getNombre(), u.getCorreo()});
        }
    }

    private void agregarUsuario() {
        JTextField campoNombre = new JTextField();
        JTextField campoCorreo = new JTextField();
        Object[] campos = { "Nombre:", campoNombre, "Correo:", campoCorreo };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Agregar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            Usuario nuevo = new Usuario(campoNombre.getText().trim(), campoCorreo.getText().trim());
            if (nuevo.getNombre().isEmpty() || nuevo.getCorreo().isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y correo no pueden estar vacíos.");
                return;
            }
            usuarios.add(nuevo);
            UsuarioManager.guardarUsuarios(usuarios);
            cargarTabla();
        }
    }

    private void editarUsuario() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para editar.");
            return;
        }

        Usuario u = usuarios.get(filaSeleccionada);

        JTextField campoNombre = new JTextField(u.getNombre());
        JTextField campoCorreo = new JTextField(u.getCorreo());
        Object[] campos = { "Nombre:", campoNombre, "Correo:", campoCorreo };

        int opcion = JOptionPane.showConfirmDialog(this, campos, "Editar Usuario", JOptionPane.OK_CANCEL_OPTION);
        if (opcion == JOptionPane.OK_OPTION) {
            String nuevoNombre = campoNombre.getText().trim();
            String nuevoCorreo = campoCorreo.getText().trim();
            if (nuevoNombre.isEmpty() || nuevoCorreo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Nombre y correo no pueden estar vacíos.");
                return;
            }
            u.setNombre(nuevoNombre);
            u.setCorreo(nuevoCorreo);
            UsuarioManager.guardarUsuarios(usuarios);
            cargarTabla();
            tabla.setRowSelectionInterval(filaSeleccionada, filaSeleccionada);
        }
    }

    // ← NUEVO: eliminar con confirmación
    private void eliminarUsuario() {
        int fila = tabla.getSelectedRow();
        if (fila == -1) {
            JOptionPane.showMessageDialog(this, "Seleccione un usuario para eliminar.");
            return;
        }
        int ok = JOptionPane.showConfirmDialog(
                this,
                "¿Eliminar al usuario seleccionado?",
                "Confirmar eliminación",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.WARNING_MESSAGE
        );
        if (ok == JOptionPane.OK_OPTION) {
            usuarios.remove(fila);
            UsuarioManager.guardarUsuarios(usuarios);
            cargarTabla();
            if (fila > 0 && fila - 1 < tabla.getRowCount()) {
                tabla.setRowSelectionInterval(fila - 1, fila - 1);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(InterfazGrafica::new);
    }
}

//Hernandez Juarez Aldair