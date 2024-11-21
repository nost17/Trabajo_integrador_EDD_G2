package Hospital.gestion;

import Hospital.modelo.Cirugia;
import Hospital.modelo.Consulta;
import Hospital.modelo.Medicamento;
import Hospital.modelo.Medico;
import Hospital.modelo.Paciente;
import java.time.LocalDate;
import java.util.Random;

public class GestionPacientes {

    private static int obtenerCantidadAleatoria() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    public static void atencionPrioridadMedia(Medicamento medicacion, Medico medico, int cantidadNecesaria, LocalDate fecha) {
        Paciente paciente = Principal.prioridadMedia.remove();
        Consulta consulta = new Consulta(medico, paciente, medicacion, cantidadNecesaria, fecha);
        Principal.consultaRealizadas.addLast(consulta);
        System.out.println("SE ATENDIO A " + paciente.getNombre());
    }

    public static void atencionPrioridadAlta(LocalDate fecha) {
        Paciente paciente = Principal.prioridadAlta.remove();
        Medico medico = GestionMedicos.buscarMedico("cirujano");
        Cirugia programarCirugia = new Cirugia(medico, paciente, fecha);
        Principal.cirugiasProgramadas.push(programarCirugia);
        System.out.println("SE PROGRAMO UNA CIRUJIA PARA " + paciente.getNombre());
    }

    public static void realizarCirugiasProgramadas() {

        if (Principal.cirugiasProgramadas.empty()) {
            throw new RuntimeException("No hay cirugias programadas");
        }

        for (int i = 0; i < 3 && !Principal.cirugiasProgramadas.empty(); i++) {
            Cirugia cirugia = Principal.cirugiasProgramadas.pop();
            Medico medico = cirugia.getMedicoAcargo();
            Principal.cirugiasRealizadas.addLast(cirugia);
            GestionMedicos.agregarMedico(medico);
            System.out.println(Helper.repetirLetra("*", 30));
            System.out.println(cirugia);
        }
    }

    public static void elegirAtencion(int prioridad) {

        int cantidadNecesaria = obtenerCantidadAleatoria();

        Medicamento medicacion = GestionMedicamentos.obtenerMedicamentoDisponible(cantidadNecesaria);

        if (prioridad == 1) {
            if (Principal.prioridadAlta.isEmpty()) {
                throw new RuntimeException("No hay pacientes con prioridad alta en espera");
            }

            for (int i = 0; i < 3 && !Principal.prioridadAlta.isEmpty(); i++) {
                atencionPrioridadAlta(Principal.fechasAleatorias());
            }

        } else if (prioridad == 2) {
            if (Principal.prioridadMedia.isEmpty()) {
                throw new RuntimeException("No hay pacientes con prioridad media en espera");
            }

            Medico medico = GestionMedicos.buscarMedico("general");
            for (int i = 0; i < 10 && !Principal.prioridadMedia.isEmpty(); i++) {
                atencionPrioridadMedia(medicacion, medico, cantidadNecesaria, Principal.fechasAleatorias());
            }
            GestionMedicos.agregarMedico(medico);
        }

    }
}
