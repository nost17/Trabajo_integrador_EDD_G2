package Hospital.gestion;
import Hospital.estructuras.*;
import Hospital.modelo.*;
import java.util.*;
import javax.sound.midi.SysexMessage;
public class Principal {
    
    public static void main(String[] args) {
        int tamañoMedicamentos = (int)(Math.random()*10);
        Medicamento[] medicamentos = new Medicamento[tamañoMedicamentos];
        BinarySearchTree<Medico> medicosDisponibles = new BinarySearchTree<>();
        Queue<Paciente> prioridadAlta;
        Queue<Paciente> prioridadMedia;
        Stack<Cirugia> cirugiasProgramadas;
        DoubleLinkedList<Consulta> consultaRealizadas;
        DoubleLinkedList<Cirugia> cirugiasRealizadas; 
        ArrayList<Integer> codigos = new ArrayList();
        ArrayList<Integer> matriculas = new ArrayList();
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
                while (opcion<1 || opcion>8){
                    System.out.println("Opcion Invalida...");
                    opcion = Helper.validarEntero(input, "ingrese opcion: ");
                }
                System.out.println("_______________________________________________________________________________________________________");
                switch(opcion) {
                    case 1: 
                        inicioJornada(codigos, medicamentos, medicosDisponibles, input);
                        break;
                    case 2:
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
            } while (opcion!=8);
    }
    
    public static void inicioJornada(ArrayList<Integer> codigos, Medicamento[] medicamentos, BinarySearchTree<Medico> medicosDisponibles, Scanner input){
        int opcion;
        boolean bandera = false;
        do {
            System.out.println("1- Gestion de Medicamentos");
            System.out.println("2- Gestion de Medicos");
            System.out.println("3- Volver...");
            opcion = Helper.validarEntero(input, "ingrese opcion: ");
            while (opcion>3 || opcion<1){
                System.out.println("Opcion Invalida...");
                opcion = Helper.validarEntero(input, "ingrese opcion: ");
            }
            System.out.println("_______________________________________________________________________________________________________");
            switch(opcion){
                case 1:
                    if (bandera == true){
                        System.out.println("Ya no puedes modificar la lista de Medicamentos...");
                        System.out.println("_______________________________________________________________________________________________________");
                        break;
                    } else {
                        System.out.println("   GESTION DE MEDICAMENTOS   ");
                        for (int i = 0; i < medicamentos.length; i++) {
                            int codigo = Helper.validarCodigo(codigos, input, "ingrese codigo del medicamento: ");
                            String nombre = Helper.validarSoloLetras(input, "nombre: ");
                            double precio = Helper.validarDouble(input, "precio: ");
                            int stockDisponible = Helper.validarEntero(input, "Stock Disponible: ");
                            Medicamento medicamento = new Medicamento();
                            medicamento.setCodigo(codigo);
                            medicamento.setNombre(nombre);
                            medicamento.setPrecio(precio);
                            medicamento.setStockDisponible(stockDisponible);
                            medicamentos[i] = medicamento;
                            System.out.println("Medicamento Agregado...");
                        }
                        bandera = true;
                        System.out.println("Lista de Medicamentos Actualizada: ");
                        for (int i = 0; i < medicamentos.length; i++) {
                            System.out.println(medicamentos[i]);
                        }
                    }
                    System.out.println("_______________________________________________________________________________________________________");
                    break;
                case 2:
                    System.out.println("   GESTION DE MEDICOS   ");
                    int cantidad = Helper.validarEnteroNoNegativo(input, "Cantidad Medicos Disponibles: ");
                    for (int i = 0; i < cantidad; i++) {
                        int matricula = (int) (Math.random()*100);
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
                    System.out.println("_______________________________________________________________________________________________________");
                    break;
             } 
        } while (opcion!=3);
    }
}

