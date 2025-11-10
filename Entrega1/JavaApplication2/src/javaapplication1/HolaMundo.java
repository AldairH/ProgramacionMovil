package javaapplication1;

public class HolaMundo {
    public String nombre = "John Doe";

    public HolaMundo() {
    }

    public void saludar() {
        System.out.println("hola mundo " + nombre);
    }

    public void saludar(String nombre) {
        this.nombre = nombre;
        System.out.println("hola mundo " + this.nombre);
    }

    public static void despedirse() {
        System.out.println("adios");
    }
}
