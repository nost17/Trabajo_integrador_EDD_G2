package Hospital.gestion;

import Hospital.estructuras.*;
import Hospital.modelo.*;
import java.time.LocalDate;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

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
                inicioJornada();
            case 2 ->
                atencionPacientes();
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

    public static void inicioJornada() {
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

                        int index = Helper.validarEnteroEnRango(input, "Ingrese la cantidad de medicamentos para esta jornada", 2, 8);
                        medicamentos = new Medicamento[index];

                        for (int i = 0; i < medicamentos.length; i++) {
                            String nombre = Helper.validarSoloLetras(input, "nombre: ");
                            double precio = Helper.validarDouble(input, "precio: ");
                            int stockDisponible = Helper.validarEntero(input, "stock : ");
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
                    while (true) {
                        int matricula = (int) (Math.random() * 100);
                        String nombre = Helper.validarSoloLetras(input, "Nombre: ");
                        String especialidad = Helper.validarEspecialidad(input);
                        Medico medico = new Medico(matricula, nombre, especialidad);
                        GestionMedicos.agregarMedico(medico);
                        System.out.println("Medico agregado correctamente...");
                        boolean agregarOtro = Helper.validarSiNo(input, "Agregar otro medico?");

                        if (!agregarOtro) {
                            break;
                        }
                    }
                    System.out.println(Helper.repetirLetra("_", 50));
                }
            }
        } while (opcion != 3);
    }

    public static void atencionPacientes() {
        System.out.println("               GESTION DE PACIENTES               ");
        int dni = Helper.validarEntero(input, "Dni: ");
        int edad = Helper.validarEnteroEnRango(input, "Edad", 1, 100);
        String nombre = Helper.validarStringNoVacio(input, "Nombre: ");
        String[] antecedentes = Helper.validarAntecedentes(input);
        Paciente paciente = new Paciente(dni, edad, nombre, antecedentes);
        System.out.println(Helper.repetirLetra("-", 50));
        int diagnostico = (int) (Math.random() * 2) + 1;
        if (diagnostico == 1) {
            System.out.println("PACIENTE AGREGADO PARA PRIORIDAD ALTA...");
            prioridadAlta.offer(paciente);
        } else {
            System.out.println("PACIENTE AGREGADO PARA PRIORIDAD MEDIA...");
            prioridadMedia.offer(paciente);
        }
//        System.out.println("Paciente agregado correctamente...");
//        System.out.println(Helper.repetirLetra("_", 50));
    }

    public static LocalDate fechasAleatorias() {
        long minDay = LocalDate.of(1980, 1, 1).toEpochDay();
        long maxDay = LocalDate.of(2014, 12, 31).toEpochDay();
        long randomDay = ThreadLocalRandom.current().nextLong(minDay, maxDay);
        return LocalDate.ofEpochDay(randomDay);
    }

    public static void verificarJornada() {
        if (medicamentos == null) {
            throw new RuntimeException("La jornada no ha comenzado.");
        }

    }

}
