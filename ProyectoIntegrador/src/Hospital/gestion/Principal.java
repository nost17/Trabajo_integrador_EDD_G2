package Hospital.gestion;

import Hospital.estructuras.*;
import Hospital.modelo.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {

//    private static final int tamañoMedicamentos = ((int) (Math.random() * 10)) + 2;
    public static Scanner input = new Scanner(System.in);
    public static Medicamento[] medicamentos;
    public static BinarySearchTree<Medico> medicosDisponibles = new BinarySearchTree<>();
    public static QueueCircular<Paciente> prioridadAlta = new QueueCircular<>();
    public static QueueCircular<Paciente> prioridadMedia = new QueueCircular<>();
    public static PilaGenerica<Cirugia> cirugiasProgramadas = new PilaGenerica<>();
    public static DoubleLinkedList<Consulta> consultaRealizadas = new DoubleLinkedList<>();
    public static DoubleLinkedList<Cirugia> cirugiasRealizadas = new DoubleLinkedList<>();
    public static ArrayList<Integer> codigos = new ArrayList<>();
    public static ArrayList<Integer> matriculas = new ArrayList<>();

    public static void main(String[] args) {

        int opcion;
        do {
            System.out.println("--- Menu gestion Hospital ---");
            System.out.println("1- Inicio de Jornada Laboral");
            System.out.println("2- Recepcionar Paciente");
            System.out.println("3- Atender Paciente Prioridad Alta");
            System.out.println("4- Atender Paciente Prioridad Media");
            System.out.println("5- Realizar cirugias programadas");
            System.out.println("6- Ver consultas disponibles");
            System.out.println("7- Salir");
            opcion = Helper.validarEnteroEnRango(input, "ingrese opcion:", 1, 7);
            System.out.println(Helper.repetirLetra("_", 50));

            try {
                ejecutarOpcion(opcion);
            } catch (Exception RuntimeException) {
                System.out.println(RuntimeException.getMessage());
            } finally {
                if (opcion != 1) {
                    System.out.println(Helper.repetirLetra("-", 50));
                }
            }
        } while (opcion != 7);
    }

    public static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 ->
                GestionJornada.inicioJornada();
            case 2 ->
                GestionPacientes.atencionPacientes();
            case 3 ->
                GestionPacientes.elegirAtencion(1);
            case 4 ->
                GestionPacientes.elegirAtencion(2);
            case 5 ->
                GestionPacientes.realizarCirugiasProgramadas();
            case 6 ->
                GestionConsultas.GestionConsultas();

        }
    }

}
