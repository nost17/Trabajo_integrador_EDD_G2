package Hospital.gestion;

import Hospital.estructuras.*;
import Hospital.modelo.*;
import java.util.Scanner;
import java.util.ArrayList;

public class Principal {

    public static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        int tamañoMedicamentos = ((int) (Math.random() * 10)) + 2;
        Medicamento[] medicamentos = new Medicamento[tamañoMedicamentos];
        BinarySearchTree<Medico> medicosDisponibles = new BinarySearchTree<>();
        QueueCircular<Paciente> prioridadAlta = new QueueCircular<>();
        QueueCircular<Paciente> prioridadMedia = new QueueCircular<>();
        PilaGenerica<Cirugia> cirugiasProgramadas;
        DoubleLinkedList<Consulta> consultaRealizadas;
        DoubleLinkedList<Cirugia> cirugiasRealizadas;
        ArrayList<Integer> codigos = new ArrayList<>();
        ArrayList<Integer> matriculas = new ArrayList<>();
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
                    InicioJornada.inicioJornada(codigos, medicamentos, medicosDisponibles, input);
                    break;
                case 2:
                    RecepcionPacientes.recepcionPacientes(input, prioridadAlta, prioridadMedia);
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
}
