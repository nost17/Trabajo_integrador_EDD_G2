package Hospital.gestion;

import Hospital.modelo.Medico;
import java.util.ArrayList;

public class GestionMedicos {

    private static ArrayList<Medico> listaAuxiliar = new ArrayList<>();

    public static void agregarMedico(Medico medico) {
        listaAuxiliar.add(medico);
        Principal.medicosDisponibles.add(medico);
    }

    public static Medico buscarMedico() {
        String especialidadBuscada = Helper.validarEspecialidad(Principal.input);
        return buscarMedico(especialidadBuscada);
    }

    public static Medico buscarMedico(String especialidadBuscada) {
        
        if (!hayMedicos()) {
            throw new RuntimeException("No hay medicos, deben registrar mas.");
        }
        
        int indiceAleatorio = (int) (Math.random() * listaAuxiliar.size());

        Medico medicoEncontrado = listaAuxiliar.remove(indiceAleatorio);

        if (medicoEncontrado.getEspecialidad().equals(especialidadBuscada)) {
            Principal.medicosDisponibles.remove(medicoEncontrado);
            return medicoEncontrado;
        }

        listaAuxiliar.add(medicoEncontrado);
        return buscarMedico(especialidadBuscada);
    }
    
    public static boolean hayMedicos() {
        return Principal.medicosDisponibles.NodeCount() > 0;
    }
}
