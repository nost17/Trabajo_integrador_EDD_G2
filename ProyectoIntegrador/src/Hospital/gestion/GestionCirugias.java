package Hospital.gestion;

import Hospital.estructuras.BinarySearchTree;
import Hospital.estructuras.DoubleLinkedList;
import Hospital.estructuras.PilaGenerica;
import Hospital.modelo.Cirugia;
import Hospital.modelo.Consulta;
import Hospital.modelo.Medicamento;
import Hospital.modelo.Medico;
import Hospital.modelo.Paciente;
import java.time.LocalDate;
import java.util.Iterator;

public class GestionCirugias {

    public static void realizarCirugia(
            PilaGenerica<Cirugia> cirugiasProgramadas,
            DoubleLinkedList<Cirugia> cirugiasRealizadas,
            BinarySearchTree<Medico> medicosDisponibles) {

        for (int i = 0; i < 3 && !cirugiasProgramadas.empty(); i++) {
            Cirugia cirugiaHecha = cirugiasProgramadas.pop();
            cirugiasRealizadas.addLast(cirugiaHecha);
            medicosDisponibles.add(cirugiaHecha.getMedicoAcargo());
            System.out.println("Se ha concretado la cirugia: " + cirugiaHecha);
        }
    }

    public static void GestionConsultas() {
        int opcion;
        do {
            opcion = Helper.validarEnteroEnRango(Principal.input, "Elije una opcion: ", 1, 9);
            System.out.println(Helper.repetirLetra("-", 50));
            ejecutarOpcion(opcion);
            System.out.println(Helper.repetirLetra("_", 50));
        } while (opcion != 3);
    }

    private static void ejecutarOpcion(int opcion) {
        switch (opcion) {
            case 1 -> {
                System.out.println("Lista de medicos disponibles en el hospital:");
                Principal.medicosDisponibles.InOrder();
            }
            case 2 -> {
                consultarMedicamentos();
            }
            case 3 -> {
                consultarCirugiasRealizadas();
            }
            case 4 -> {
                consultarConsultasEfectuadas();
            }
            case 5 -> {
                consultarPacientesPorFecha();
            }
            case 6 -> {
                consultarOperadosPorEdad();
            }
            case 7 -> {
                consultarPacientesPorAntecedenteEnConsultas();
            }
        }
    }

    private static void consultarMedicamentos() {
        int stockComparar = Helper.validarEnteroNoNegativo(Principal.input, "Ingrese stock maximo a mostrar: ");
        System.out.println(Helper.repetirLetra("-", 50));
        System.out.println("Lista de medicamentos con stock menor a " + stockComparar);
        for (Medicamento medicamento : Principal.medicamentos) {
            if (medicamento.getStockDisponible() <= stockComparar) {
                System.out.println(medicamento);
            }
        }
    }

    private static void consultarCirugiasRealizadas() {
        System.out.println("Lista de cirugias efectuadas");
        for (Cirugia cirugiaRealizada : Principal.cirugiasRealizadas) {
            System.out.println(cirugiaRealizada);
        }
    }

    private static void consultarConsultasEfectuadas() {
        System.out.println("Lista de consultas efectuadas");
        for (Consulta consultaRealizada : Principal.consultaRealizadas) {
            System.out.println(consultaRealizada);
        }
    }

    private static int contarCirugiasPorFecha(LocalDate fechaInicial, LocalDate fechaFinal) {
        int contador = 0;
        for (Cirugia cirugiaRealizada : Principal.cirugiasRealizadas) {
            LocalDate fechaCirugia = cirugiaRealizada.getFecha();

            if (fechaCirugia.isAfter(fechaInicial) && fechaCirugia.isBefore(fechaFinal)) {
                ++contador;
//                System.out.println(cirugiaRealizada);
            }
        }
        return contador;
    }

    private static int contarConsultasPorFecha(LocalDate fechaInicial, LocalDate fechaFinal) {
        int contador = 0;
        for (Consulta consultaRealizada : Principal.consultaRealizadas) {
            LocalDate fechaCirugia = consultaRealizada.getFecha();

            if (fechaCirugia.isAfter(fechaInicial) && fechaCirugia.isBefore(fechaFinal)) {
                ++contador;
//                System.out.println(consultaRealizada);
            }
        }
        return contador;
    }

    private static void consultarPacientesPorFecha() {
        LocalDate fechaInicial = Helper.validarFecha(Principal.input, "Fecha inicial: ", "dd/MM/yyyy");
        LocalDate fechaFinal = Helper.validarFecha(Principal.input, "Fecha limite: ", "dd/MM/yyyy");
        System.out.println("Pacientes antendidos desde " + fechaFinal + " hasta " + fechaFinal);
        System.out.println("En cirugias: " + contarCirugiasPorFecha(fechaInicial, fechaFinal) + " pacientes");
        System.out.println("En consultas: " + contarConsultasPorFecha(fechaInicial, fechaFinal) + " pacientes");
    }

    private static void consultarOperadosPorEdad() {
        int contador = 0;

        int edadInicial = Helper.validarEnteroNoNegativo(Principal.input, "Edad minima: ");
        int edadFinal = Helper.validarEnteroNoNegativo(Principal.input, "Edad maxima: ");

        for (Cirugia cirugiaRealizada : Principal.cirugiasRealizadas) {
            int edadPacienteOperado = cirugiaRealizada.getPaciente().getEdad();

            if (edadPacienteOperado >= edadInicial && edadPacienteOperado <= edadFinal) {
                ++contador;
            }
        }

        System.out.println("Hay " + contador + " pacientes operados de "
                + edadInicial
                + " años" + " hasta "
                + edadFinal
                + " años");
    }

    private static void consultarPacientesPorAntecedenteEnConsultas() {
        int contador = 0;
        String casoBuscado = Helper.validarStringNoVacio(Principal.input, "Escriba el pacedimiento a buscar: ");

        for (Consulta consultaRealizada : Principal.consultaRealizadas) {
            String[] antecedentes = consultaRealizada.getPaciente().getAntecedentes();

            for (String caso : antecedentes) {
                if (caso.equals(casoBuscado)) {
                    ++contador;
                }
            }
        }

        System.out.println(contador + " pacientes padecieron de " + casoBuscado);
    }
}
