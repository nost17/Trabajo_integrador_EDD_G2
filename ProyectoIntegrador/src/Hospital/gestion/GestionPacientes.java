package Hospital.gestion;

import Hospital.estructuras.QueueCircular;
import Hospital.modelo.Cirugia;
import Hospital.modelo.Consulta;
import Hospital.modelo.Medicamento;
import Hospital.modelo.Medico;
import Hospital.modelo.Paciente;
import java.time.LocalDate;
import java.util.Random;
import java.util.Scanner;

public class GestionPacientes {

    public static int obtenerCantidadAleatoria() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    public static void recepcionPrioridadMedia() {
        if (Principal.prioridadMedia.isEmpty()) {
            System.out.println("No hay pacientes con prioridad media en espera...");
            return;
        }
            if (Principal.contGeneral <= 0){
                System.out.println("Aun No Se Encuentran Disponibles Medicos En Clinica General...");
                System.out.println("1. Agregar Medico");
                System.out.println("2. Volver");
                int opcion = Helper.validarEnteroEnRango(Principal.input, "Ingrese Opcion: ", 1, 2);
                if (opcion == 1){
                    GestionMedicos.agregarMedicosGeneral();
                    return;
                }
                if (opcion == 2){
                    return;
                }
             }
            if (Principal.contPacientes == 0){
                Principal.medicoGeneral = GestionMedicos.buscarMedicoGeneral("general");
                --Principal.contGeneral;
                Principal.contPacientes = 10;
            }
            Paciente paciente = Principal.prioridadMedia.remove();
            int index = 0;
            int cantidadNecesaria = obtenerCantidadAleatoria();
            Medicamento medicacion = GestionMedicamentos.obtenerMedicamentoDisponible(index); //tambien obtenemos el valor de la posicion del medicamento
            System.out.println("se le ha recetado: " + cantidadNecesaria + " de " + medicacion.getNombre());
            GestionMedicamentos.verificarStockMedicamento(medicacion, cantidadNecesaria, index);
        
            LocalDate fecha = Helper.fechasAleatorias();
            Consulta consulta = new Consulta(Principal.medicoGeneral, paciente, medicacion, cantidadNecesaria, fecha);
            Principal.consultaRealizadas.addLast(consulta);
            --Principal.contPacientes;
    }

    public static void recepcionPrioridadAlta() {
        if (Principal.prioridadAlta.isEmpty()) {
            System.out.println("No hay pacientes con prioridad alta en espera");
            return;
        } else {
        int longitudFila = Principal.prioridadAlta.size();
        if (longitudFila < 3){
            System.out.println("aun no hay sufientes pacientes para la atencion... deben ser 3");
            System.out.println("1. Si");
            System.out.println("2. No");
            int opcion = Helper.validarEnteroEnRango(Principal.input, "Dese agregar algun paciente mas?...",1,2);
            if (opcion == 1){
                agregarPacientePrioridadAlta();
                return;
            }
            if (opcion == 2){
                return;
            }
        }
        if (Principal.contCirujano <= 0){
            System.out.println("No Se Encuentran Disponibles Medicos Cirujanos...");
            System.out.println("1. Agregar Medico");
            System.out.println("2. Volver...");
            int opcion = Helper.validarEnteroEnRango(Principal.input, "Ingrese opcion: ", 1, 2);
            if (opcion == 1){
                GestionMedicos.agregarMedicosCirujanos();
                return;
            }
            if (opcion == 2){
                return;
            }
        }
        if (longitudFila >= 3){
            for (int i = 0; i < 3; i++) {
                LocalDate fecha = LocalDate.now();
                Paciente paciente = Principal.prioridadAlta.remove();
                Medico medico = GestionMedicos.buscarMedicoCirujano("cirugia");
                --Principal.contCirujano;
                Cirugia programarCirugia = new Cirugia(medico, paciente, fecha);
                Principal.cirugiasProgramadas.push(programarCirugia);
                System.out.println("cirugia programada: " + programarCirugia);
            }
          }
        }
    }
    
    public static void atencionPacientes(Scanner input, QueueCircular<Paciente> prioridadAlta, QueueCircular<Paciente> prioridadMedia) {
        System.out.println("               GESTION DE PACIENTES               ");
        int dni = Helper.validarEntero(input, "Dni: ");
        int edad = Helper.validarEnteroEnRango(input, "Edad: ", 1, 100);
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
   
   public static void agregarPacientePrioridadAlta(){
       int dni = Helper.validarEntero(Principal.input, "Dni: ");
        int edad = Helper.validarEnteroEnRango(Principal.input, "Edad: ", 1, 100);
        String nombre = Helper.validarSoloLetras(Principal.input, "Nombre: ");
        String[] antecedentes = Helper.validarAntecedentes(Principal.input);
        Paciente paciente = new Paciente(dni, edad, nombre, antecedentes);
        Principal.prioridadAlta.offer(paciente);
        System.out.println(Helper.repetirLetra("_", 50));
   }
}
