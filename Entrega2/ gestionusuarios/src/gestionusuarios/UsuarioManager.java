package gestionusuarios;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioManager {

    private static final String ARCHIVO = "usuarios.txt";

    // Cargar todos los usuarios desde el archivo de texto
    public static List<Usuario> cargarUsuarios() {
        List<Usuario> lista = new ArrayList<>();

        File file = new File(ARCHIVO);
        if (!file.exists()) {
            return lista; // si no existe, regresa lista vacía
        }

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                Usuario u = Usuario.fromString(linea);
                if (u != null) {
                    lista.add(u);
                }
            }
        } catch (IOException e) {
            System.err.println("Error al cargar usuarios: " + e.getMessage());
        }

        return lista;
    }

    // Guardar la lista completa de usuarios en el archivo
    public static void guardarUsuarios(List<Usuario> usuarios) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(ARCHIVO))) {
            for (Usuario u : usuarios) {
                bw.write(u.toString());
                bw.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error al guardar usuarios: " + e.getMessage());
        }
    }
}

//Hernandez Juarez Aldair