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

    public static void atencionPrioridadMedia(Medicamento medicacion, int cantidadNecesaria, LocalDate fecha) {
        Paciente paciente = Principal.prioridadMedia.remove();
        Medico medico = GestionMedicos.buscarMedico("general");
        Consulta consulta = new Consulta(medico, paciente, medicacion, cantidadNecesaria, fecha);
        Principal.consultaRealizadas.addLast(consulta);

    }

    public static void atencionPrioridadAlta(LocalDate fecha) {
        Paciente paciente = Principal.prioridadAlta.remove();
        Medico medico = GestionMedicos.buscarMedico("cirugia");
        Cirugia programarCirugia = new Cirugia(medico, paciente, fecha);
        Principal.cirugiasProgramadas.push(programarCirugia);

    }

    public static void elegirAtencion(int prioridad) {

        int cantidadNecesaria = obtenerCantidadAleatoria();

        Medicamento medicacion = GestionMedicamentos.obtenerMedicamentoDisponible();
        
        while(medicacion == null){
            System.out.println("No se hay suficientes dosis para su medicacion... se le dara una cantidad menor");
        }

        if (medicacion == null) {
            throw new RuntimeException("No hay medicamentos con el stock solicitado, pida otra cantidad");
        }

        if (Principal.prioridadAlta.isEmpty()) {
            throw new RuntimeException("No hay pacientes con prioridad alta en espera");
        }

        if (Principal.prioridadMedia.isEmpty()) {
            throw new RuntimeException("No hay pacientes con prioridad media en espera");
        }

        if (prioridad == 1) {
            int longitudFila = Principal.prioridadAlta.size();
            if (longitudFila > 3) {
                longitudFila = 3;
            }

            for (int i = 0; i < longitudFila; i++) {
                atencionPrioridadAlta(Helper.fechasAleatorias());
            }

        } else if (prioridad == 2) {
            atencionPrioridadMedia(medicacion, cantidadNecesaria, Helper.fechasAleatorias());
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
}
