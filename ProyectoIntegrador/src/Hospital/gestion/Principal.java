package Hospital.gestion;

import Hospital.estructuras.*;
import Hospital.modelo.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {

    private static final int tamañoMedicamentos = ((int) (Math.random() * 10)) + 2;
    public static Scanner input = new Scanner(System.in);
    public static Medicamento[] medicamentos = new Medicamento[tamañoMedicamentos];
    public static BinarySearchTree<Medico> medicosDisponibles = new BinarySearchTree<>();
    public static QueueCircular<Paciente> prioridadAlta = new QueueCircular<>();
    public static QueueCircular<Paciente> prioridadMedia = new QueueCircular<>();
    public static PilaGenerica<Cirugia> cirugiasProgramadas;
    public static DoubleLinkedList<Consulta> consultaRealizadas;
    public static DoubleLinkedList<Cirugia> cirugiasRealizadas;
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
            System.out.println("5- Consultar Medicos Disponibles");
            System.out.println("6- Consultar Cirugias Realizadas");
            System.out.println("7- Mostrar consultas Medicas Realizadas");
            System.out.println("8- Salir");
            opcion = Helper.validarEnteroEnRango(input, "ingrese opcion:", 1, 8);
            System.out.println(Helper.repetirLetra("_", 50));
            switch (opcion) {
                case 1:
                    inicioJornada(codigos, medicamentos, medicosDisponibles, input);
                    break;
                case 2:
                    atencionPacientes(input, prioridadAlta, prioridadMedia);
                    break;
                case 3:
                    break;
                case 4:
                    break;
                case 5:
                    break;
                case 6:
                    break;
                case 7:
                    break;
            }
        } while (opcion != 8);
    }

    public static void inicioJornada(ArrayList<Integer> codigos, Medicamento[] medicamentos, BinarySearchTree<Medico> medicosDisponibles, Scanner input) {
        int opcion;
        boolean bandera = false;
        do {
            System.out.println("1- Gestion de Medicamentos");
            System.out.println("2- Gestion de Medicos");
            System.out.println("3- Volver...");
            opcion = Helper.validarEnteroEnRango(input, "ingrese opcion:", 1, 3);
            System.out.println(Helper.repetirLetra("_", 50));
            switch (opcion) {
                case 1 -> {
                    if (bandera) {
                        System.out.println("Ya no puedes modificar la lista de Medicamentos...");
                        System.out.println(Helper.repetirLetra("_", 50));
                        break;
                    } else {
                        System.out.println("   GESTION DE MEDICAMENTOS   ");
                        for (int i = 0; i < medicamentos.length; i++) {
                            String nombre = Helper.validarSoloLetras(input, "nombre: ");
                            double precio = Helper.validarDouble(input, "precio: ");
                            int stockDisponible = Helper.validarEntero(input, "Stock Disponible: ");
                            Medicamento medicamento = new Medicamento(nombre, precio, stockDisponible);
                            medicamentos[i] = medicamento;
                            System.out.println("Medicamento Agregado...");
                        }
                        System.out.println("Lista de Medicamentos Actualizada: ");
                        for (Medicamento medicamento : medicamentos) {
                            System.out.println(medicamento);
                        }
                        bandera = true;
                    }
                    System.out.println(Helper.repetirLetra("_", 50));
                }
                case 2 -> {
                    System.out.println("   GESTION DE MEDICOS   ");
                    /*
                    TODO :Reemplazar `for-loop` por `do-while` y preguntar si
                    quiere agregar otro medico en cada ciclo
                     */
                    int cantidad = Helper.validarEntero(input, "Cantidad de Medicos Disponibles: ");
                    for (int i = 0; i < cantidad; i++) {
                        int matricula = (int) (Math.random() * 100);
                        String nombre = Helper.validarSoloLetras(input, "Nomnbre: ");
                        String especialidad = Helper.validarEspecialidad(input);
                        Medico medico = new Medico();
                        medico.setEspecialidad(especialidad);
                        medico.setMatricula(matricula);
                        medico.setNombre(nombre);
                        medicosDisponibles.add(medico);
                        System.out.println("Medico agregado correctamente...");
                    }
                    medicosDisponibles.InOrder();
                    System.out.println();
                    System.out.println(Helper.repetirLetra("_", 50));
                }
            }
        } while (opcion != 3);
    }

    public static void atencionPacientes(Scanner input, QueueCircular<Paciente> prioridadAlta, QueueCircular<Paciente> prioridadMedia) {
        System.out.println("               GESTION DE PACIENTES               ");
        int dni = Helper.validarEntero(input, "Dni: ");
        int edad = Helper.validarEntero(input, "Edad: ");
        String nombre = Helper.validarSoloLetras(input, "Nombre: ");
        String[] antecedentes = Helper.validarAntecedentes(input);
        Paciente paciente = new Paciente(dni, edad, nombre, antecedentes);
        System.out.println(Helper.repetirLetra("_", 50));
        int diagnostico = (int) (Math.random() * 2) + 1;
        if (diagnostico == 1) {
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
