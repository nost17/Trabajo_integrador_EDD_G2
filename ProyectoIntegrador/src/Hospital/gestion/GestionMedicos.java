package Hospital.gestion;

import Hospital.modelo.Medico;
import java.util.ArrayList;

public class GestionMedicos {

    public static void agregarMedico(Medico medico) {
        Principal.listaMedicosAux.add(medico);
        Principal.medicosDisponibles.add(medico);
    }

    public static Medico buscarMedicoCirujano() {
        String especialidadBuscada = "cirujano";
        return buscarMedicoCirujano(especialidadBuscada);
    }

    public static Medico buscarMedicoCirujano(String especialidadBuscada) {
        boolean hayMedicos = hayMedicos();
        if (hayMedicos == false) {
            System.out.println("no hay medicos disponibles por el momento, agregar medicos disponibles...");
            agregarMedicosCirujanos();
        }
        if (Principal.contCirujano == 0){
            System.out.println("No Se Encuentran Disponibles Medicos Cirujanos, se espera el ingreso de uno...");
            agregarMedicosCirujanos();
        }
        
        int indiceAleatorio = (int) (Math.random() * Principal.listaMedicosAux.size());

        Medico medicoEncontrado = Principal.listaMedicosAux.remove(indiceAleatorio);

        if (medicoEncontrado.getEspecialidad().equals(especialidadBuscada)) {
            Principal.medicosDisponibles.remove(medicoEncontrado);
            --Principal.contCirujano;
            return medicoEncontrado;
        }

        Principal.listaMedicosAux.add(medicoEncontrado);
        return buscarMedicoCirujano();
    }
    
    public static Medico buscarMedicoGeneral() {
        String especialidadBuscada = "general";
        return buscarMedicoGeneral(especialidadBuscada);
    }
    public static Medico buscarMedicoGeneral(String especialidadBuscada) {
        boolean hayMedicos = hayMedicos();
        if (hayMedicos == false) {
            System.out.println("no hay medicos disponibles por el momento, agregar medicos disponibles...");
            agregarMedicosGeneral();
        }
        if (Principal.contGeneral == 0){
            System.out.println("No Se Encuentran Disponibles Medicos En Clinica General, se espera el ingreso de uno...");
            agregarMedicosGeneral();
        }
        int indiceAleatorio = (int) (Math.random() * Principal.listaMedicosAux.size());

        Medico medicoEncontrado = Principal.listaMedicosAux.remove(indiceAleatorio);

        if (medicoEncontrado.getEspecialidad().equals(especialidadBuscada)) {
            Principal.medicosDisponibles.remove(medicoEncontrado);
            --Principal.contGeneral;
            return medicoEncontrado;
        }

        Principal.listaMedicosAux.add(medicoEncontrado);
        return buscarMedicoGeneral();
    }
    
    public static boolean hayMedicos() {
        if (Principal.medicosDisponibles.NodeCount()<=0){
            return false;
        } else return true;
    }
    
    /*public static void agregarMedicosDisponibles(){
        System.out.println("   GESTION DE MEDICOS   ");
                    int cantidad = Helper.validarEntero(Principal.input, "Cantidad de Medicos Disponibles: ");
                    if (cantidad == 0){
                        return;
                    }
                    for (int i = 0; i < cantidad; i++) {
                        int matricula = (int) (Math.random() * 100);
                        String nombre = Helper.validarSoloLetras(Principal.input, "Nomnbre: ");
                        String especialidad = Helper.validarEspecialidad(Principal.input);
                        Medico medico = new Medico(matricula, nombre, especialidad);
                        GestionMedicos.agregarMedico(medico);
                        System.out.println("Medico agregado correctamente...");
                    }
                    Principal.medicosDisponibles.InOrder();
                    System.out.println();
                    System.out.println("Hay " + Principal.contCirujano + " Medicos Cirujanos");
                    System.out.println("Hay " + Principal.contGeneral + " Medicos en Climica General");
                    System.out.println(Helper.repetirLetra("_", 50));
                }*/
    public static void agregarMedicosCirujanos(){
        switch(1){
            case 1:
                    int cantidad = Helper.validarEntero(Principal.input, "Cantidad de Medicos Disponibles: ");
                    if (cantidad == 0){
                        System.out.println("Aun No Hay Medicos Cirujanos Disponibles...");
                        break;
                    }
                    for (int i = 0; i < cantidad; i++) {
                        int matricula = (int) (Math.random() * 100);
                        String nombre = Helper.validarSoloLetras(Principal.input, "Nomnbre: ");
                        String especialidad = "cirujano";
                        Medico medico = new Medico(matricula, nombre, especialidad);
                        GestionMedicos.agregarMedico(medico);
                        ++Principal.contCirujano;
                        System.out.println("Medico agregado correctamente...");
                    }
                    Principal.medicosDisponibles.InOrder();
                    System.out.println();
                    System.out.println("Hay " + Principal.contCirujano + " Medicos Cirujanos");
                    System.out.println("Hay " + Principal.contGeneral + " Medicos en Climica General");
                    System.out.println(Helper.repetirLetra("_", 50));
        }
    }
    
    public static void agregarMedicosGeneral(){
        switch(1){
            case 1:
                    int cantidad = Helper.validarEntero(Principal.input, "Cantidad de Medicos Disponibles: ");
                    if (cantidad == 0){
                        System.out.println("Aun No Hay Medicos En Clinica General Disponibles...");
                        break;
                    }
                    for (int i = 0; i < cantidad; i++) {
                        int matricula = (int) (Math.random() * 100);
                        String nombre = Helper.validarSoloLetras(Principal.input, "Nomnbre: ");
                        String especialidad = "general";
                        Medico medico = new Medico(matricula, nombre, especialidad);
                        GestionMedicos.agregarMedico(medico);
                        ++Principal.contGeneral;
                        System.out.println("Medico agregado correctamente...");
                    }
                    Principal.medicosDisponibles.InOrder();
                    System.out.println();
                    System.out.println("Hay " + Principal.contCirujano + " Medicos Cirujanos");
                    System.out.println("Hay " + Principal.contGeneral + " Medicos en Climica General");
                    System.out.println(Helper.repetirLetra("_", 50));
        }
    }
    
}
