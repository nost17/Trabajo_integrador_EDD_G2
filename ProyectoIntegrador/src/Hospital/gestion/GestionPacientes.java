package Hospital.gestion;

import Hospital.modelo.Cirugia;
import Hospital.modelo.Consulta;
import Hospital.modelo.Medicamento;
import Hospital.modelo.Medico;
import Hospital.modelo.Paciente;
import java.time.LocalDate;
import java.util.Random;

public class GestionPacientes {

    public static int obtenerCantidadAleatoria() {
        Random random = new Random();
        return random.nextInt(5) + 1;
    }

    public static Medicamento obtenerMedicamentoDisponible(int cantidad) {

        Principal.verificarJornada();
        Random random = new Random();

        for (int i = 0; i < Principal.medicamentos.length; i++) {
            int index = random.nextInt(Principal.medicamentos.length);
            Medicamento medicamento = Principal.medicamentos[index];

            int stock = medicamento.getStockDisponible();
            if (stock > 0 && stock >= cantidad) {
                Principal.medicamentos[index].setStockDisponible(stock - cantidad);
                return medicamento;
            }
        }
        return null;
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

        Medicamento medicacion = obtenerMedicamentoDisponible(cantidadNecesaria);

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
                atencionPrioridadAlta(Principal.fechasAleatorias());
            }

        } else if (prioridad == 2) {
            atencionPrioridadMedia(medicacion, cantidadNecesaria, Principal.fechasAleatorias());
        }

    }
}
