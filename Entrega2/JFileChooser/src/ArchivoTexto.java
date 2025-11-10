import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class ArchivoTexto {

    public static void guardarArchivo(String rutaArchivo, String contenido) throws IOException {
        if (!rutaArchivo.toLowerCase().endsWith(".txt")) {
            rutaArchivo += ".txt";
        }
        Path p = Paths.get(rutaArchivo);
        try (BufferedWriter bw = Files.newBufferedWriter(p, StandardCharsets.UTF_8)) {
            bw.write(contenido);
        }
    }
}
//Hernandez Juarez Aldair