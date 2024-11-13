package Hospital.gestion;
import Hospital.estructuras.*;
import Hospital.modelo.*;
import java.util.*;
public class Principal {
    
    public static void main(String[] args) {
        Medicamento[] medicamentos;
        BinarySearchTree<Medico> medicosDisponibles;
        Queue<Paciente> prioridadAlta;
        Queue<Paciente> prioridadMedia;
        Stack<Cirugia> cirugiasProgramadas;
        DoubleLinkedList<Consulta> consultaRealizadas;
        DoubleLinkedList<Cirugia> cirugiasRealizadas; 
	Scanner input = new Scanner(System.in);
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
		opcion = Helper.validarEntero(input, "ingrese opcion: ");
            } while (opcion!=8);
    }

}
