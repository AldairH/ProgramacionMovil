package javaapplication1;

public class Prueba {
    public static void main(String[] args) {
        HolaMundo.despedirse();
        HolaMundo hm = new HolaMundo();
        
        hm.nombre = "Omar";
        hm.saludar();
        hm.saludar("Ana");
    }
}
