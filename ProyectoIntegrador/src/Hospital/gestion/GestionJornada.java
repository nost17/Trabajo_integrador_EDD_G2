package Hospital.gestion;

import Hospital.modelo.Medicamento;
import Hospital.modelo.Medico;

public class GestionJornada {

    static boolean bandera = false;

    public static void inicioJornada() {
        int opcion;
        do {
            System.out.println("1- Gestion de Medicamentos");
            System.out.println("2- Gestion de Medicos");
            System.out.println("3- Volver...");
            opcion = Helper.validarEnteroEnRango(Principal.input, "ingrese opcion:", 1, 3);
            System.out.println(Helper.repetirLetra("_", 50));
            switch (opcion) {
                case 1 -> {
                    registrarMedicamentos();
                    System.out.println(Helper.repetirLetra("_", 50));
                }
                case 2 -> {
                    registrarMedicos();
                    System.out.println(Helper.repetirLetra("_", 50));
                }
            }
        } while (opcion != 3);
    }

    public static void registrarMedicamentos() {
        if (bandera) {
            throw new RuntimeException("Ya no puedes modificar la lista de Medicamentos...");
        }
        System.out.println("   GESTION DE MEDICAMENTOS   ");

        int index = Helper.validarEnteroEnRango(Principal.input, "Ingrese la cantidad de medicamentos para esta jornada", 2, 8);
        Principal.medicamentos = new Medicamento[index];

        for (int i = 0; i < Principal.medicamentos.length; i++) {
            String nombre = Helper.validarSoloLetras(Principal.input, "nombre: ");
            double precio = Helper.validarDouble(Principal.input, "precio: ");
            int stockDisponible = Helper.validarEntero(Principal.input, "stock : ");
            Medicamento medicamento = new Medicamento(nombre, precio, stockDisponible);
            Principal.medicamentos[i] = medicamento;
            System.out.println("Medicamento Agregado...");
        }
        System.out.println(Helper.repetirLetra("*", 30));
        System.out.println("Lista de Medicamentos Actualizada: ");
        for (Medicamento medicamento : Principal.medicamentos) {
            System.out.println(medicamento);
        }
        bandera = true;
    }

    public static void registrarMedicos() {
        System.out.println("   GESTION DE MEDICOS   ");
        while (true) {
            int matricula = (int) (Math.random() * 100);
            String nombre = Helper.validarSoloLetras(Principal.input, "Nombre: ");
            String especialidad = Helper.validarEspecialidad(Principal.input);
            Medico medico = new Medico(matricula, nombre, especialidad);
            GestionMedicos.agregarMedico(medico);
            System.out.println("Medico agregado correctamente...");
            boolean agregarOtro = Helper.validarSiNo(Principal.input, "Agregar otro medico?");

            if (!agregarOtro) {
                break;
            }
        }
    }

    public static void verificarJornada() {
        if (Principal.medicamentos == null) {
            throw new RuntimeException("La jornada no ha comenzado.");
        }
    }
}
