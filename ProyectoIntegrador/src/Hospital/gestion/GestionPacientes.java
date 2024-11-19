package Hospital.gestion;

import static Hospital.gestion.Principal.cirugiasProgramadas;
import static Hospital.gestion.Principal.consultaRealizadas;
import static Hospital.gestion.Principal.fechasAleatorias;
import static Hospital.gestion.Principal.prioridadAlta;
import static Hospital.gestion.Principal.prioridadMedia;
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
    
    public static Medicamento obtenerMedicamentoDisponible(Medicamento[] medicamentos, int cantidad) {
        Random random = new Random();
        while (true) {
            int index = random.nextInt(medicamentos.length);
            Medicamento medicamento = medicamentos[index];
            int stock = medicamento.getStockDisponible();
            if (stock > 0 && stock >= cantidad) {
                medicamentos[index].setStockDisponible(stock - cantidad);
                return medicamento;
            }
        }
    }
    
    public static void atencionPrioridadMedia(Medicamento medicacion, int cantidadNecesaria, LocalDate fecha) {
        Paciente paciente = prioridadMedia.remove();
        Medico medico = GestionMedicos.buscarMedico("general");
        Consulta consulta = new Consulta(medico, paciente, medicacion, cantidadNecesaria, fecha);
        consultaRealizadas.addLast(consulta);
        
    }
    
    public static void atencionPrioridadAlta(LocalDate fecha) {
        Paciente paciente = prioridadAlta.remove();
        Medico medico = GestionMedicos.buscarMedico("cirugia");
        Cirugia programarCirugia = new Cirugia(medico, paciente, fecha);
        cirugiasProgramadas.push(programarCirugia);
        
    }
    
    public static void elegirAtencion(int prioridad) {
        
        int cantidadNecesaria = obtenerCantidadAleatoria();
        
        Medicamento medicacion = obtenerMedicamentoDisponible(Principal.medicamentos, cantidadNecesaria);
        
        if (prioridad == 1) {
            int longitudFila = prioridadAlta.size();
            if (longitudFila > 3) {
                longitudFila = 3;
            }
            for (int i = 0; i < longitudFila; i++) {
                atencionPrioridadAlta(fechasAleatorias());
            }
            
        } else if (prioridad == 2) {
            atencionPrioridadMedia(medicacion, cantidadNecesaria, fechasAleatorias());
        }
    }
}
