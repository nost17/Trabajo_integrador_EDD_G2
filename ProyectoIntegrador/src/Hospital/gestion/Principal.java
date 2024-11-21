package Hospital.gestion;

import Hospital.estructuras.*;
import Hospital.modelo.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Principal {
    public static Scanner input = new Scanner(System.in);
    public static Medicamento[] medicamentos = new Medicamento[0];
    public static BinarySearchTree<Medico> medicosDisponibles = new BinarySearchTree<>();
    public static QueueCircular<Paciente> prioridadAlta = new QueueCircular<>();
    public static QueueCircular<Paciente> prioridadMedia = new QueueCircular<>();
    public static PilaGenerica<Cirugia> cirugiasProgramadas;
    public static DoubleLinkedList<Consulta> consultaRealizadas;
    public static DoubleLinkedList<Cirugia> cirugiasRealizadas;
    public static ArrayList<Integer> codigos = new ArrayList<>();
    public static ArrayList<Integer> matriculas = new ArrayList<>();

    public static void main(String[] args) {
        GestionarInicioDeJornada.inicioJornada(medicosDisponibles, input);
        for (Medicamento medicamento : Principal.medicamentos) {
                            System.out.println(medicamento);
                        }
        medicosDisponibles.InOrder();
        int opcion;
        do {
            System.out.println("--- Menu gestion Hospital ---");
            System.out.println("1- Recepcionar Paciente");
            System.out.println("2- Atender Paciente Prioridad Alta");
            System.out.println("3- Atender Paciente Prioridad Media");
            System.out.println("4- Realizar consultas");
            System.out.println("5- Salir");
            opcion = Helper.validarEnteroEnRango(input, "ingrese opcion:", 1, 6);
            System.out.println(Helper.repetirLetra("_", 50));
            switch (opcion) {  
                case 1 ->
                    GestionPacientes.atencionPacientes(input, prioridadAlta, prioridadMedia);
                case 2 -> 
                    GestionPacientes.elegirAtencion(1);
                case 3 ->
                    GestionPacientes.elegirAtencion(2);
                case 4 ->
                    GestionCirugias.GestionConsultas();
            }
            System.out.println(Helper.repetirLetra("_", 50));
        } while (opcion != 5);
    }
//        boolean hayMedicamentos = false;
//        for (Medicamento medicamento : medicamentos) {
//            if (medicamento.getStockDisponible() > 0) {
//                hayMedicamentos = true;
//            }
//        }
//
//        if (!hayMedicamentos) {
//            throw new RuntimeException("No hay stock de medicamentos.");
//        }

}
