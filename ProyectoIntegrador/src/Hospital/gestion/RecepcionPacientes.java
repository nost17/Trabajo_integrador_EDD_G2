package Hospital.gestion;

import Hospital.estructuras.QueueCircular;
import Hospital.modelo.Paciente;
import java.util.Scanner;

public class RecepcionPacientes {
    public static void recepcionPacientes(Scanner input, QueueCircular<Paciente> prioridadAlta, QueueCircular<Paciente> prioridadMedia){
        System.out.println("               GESTION DE PACIENTES               ");
        int dni = Helper.validarEntero(input, "Dni: ");
        int edad = Helper.validarEnteroEnRango(input, "Edad: ", 1, 100);
        String nombre = Helper.validarSoloLetras(input, "Nombre: ");
        String[] antecedentes = Helper.validarAntecedentes(input);
        Paciente paciente = new Paciente(dni, edad, nombre, antecedentes);
        System.out.println(Helper.repetirLetra("_", 50));
        int diagnostico = (int)(Math.random()*2)+1;
        if (diagnostico == 1){
            System.out.println("PACIENTE AGREGADO PARA PRIORIDAD ALTA...");
            prioridadAlta.offer(paciente);
        } else {
            System.out.println("PACIENTE AGREGADO PARA PRIORIDAD MEDIA...");
            prioridadMedia.offer(paciente);
        }
        System.out.println("Paciente agregado correctamente...");
        System.out.println(Helper.repetirLetra("_", 50));
    }
}
