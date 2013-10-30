package org.alexys.objetos;

/**
 *
 * @author Alexys
 */
public class MiObjeto {
    private String nombre;
    private int edad;

    public MiObjeto() {
    }

    
    public MiObjeto(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    
}
