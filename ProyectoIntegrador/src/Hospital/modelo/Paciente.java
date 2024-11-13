package Hospital.modelo;

import java.util.Arrays;

public class Paciente {

    private int dni;
    private int edad;
    private String nombre;
    private String[] antecedentes;

    public Paciente(int dni, int edad, String nombre, String[] antecedentes) {
        this.dni = dni;
        this.edad = edad;
        this.nombre = nombre;
        this.antecedentes = antecedentes;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String[] getAntecedentes() {
        return antecedentes;
    }

    public void setAntecedentes(String[] antecedentes) {
        this.antecedentes = antecedentes;
    }

    @Override
    public String toString() {
        return "Paciente [dni=" + dni + ", edad=" + edad + ", nombre=" + nombre + ", antecedentes="
                + Arrays.toString(antecedentes) + "]";
    }

}
