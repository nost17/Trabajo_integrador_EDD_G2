package Hospital.gestion;
import Hospital.modelo.*;
import Hospital.estructuras.*;
import java.util.Scanner;

public class GestionarInicioDeJornada {
    public static void inicioJornada(BinarySearchTree<Medico> medicosDisponibles, Scanner input) {
        int opcion;
        boolean bandera = false;
        do {
            System.out.println("1- Gestion de Medicamentos");
            System.out.println("2- Gestion de Medicos");
            System.out.println("3- Comenzar...");
            opcion = Helper.validarEnteroEnRango(input, "ingrese opcion:", 1, 3);
            System.out.println(Helper.repetirLetra("_", 50));
            switch (opcion) {
                case 1 -> {
                    if (bandera) {
                        System.out.println("Ya no puedes modificar la lista de Medicamentos...");
                        System.out.println(Helper.repetirLetra("_", 50));
                        break;
                    } else {
                        System.out.println("   GESTION DE MEDICAMENTOS   ");

                        int index = Helper.validarEnteroEnRango(input, "Ingrese la cantidad de medicamentos para esta jornada", 2, 8);
                        Principal.medicamentos = new Medicamento[index];

                        for (int i = 0; i < Principal.medicamentos.length; i++) {
                            String nombre = Helper.validarSoloLetras(input, "nombre: ");
                            double precio = Helper.validarDouble(input, "precio: ");
                            int stockDisponible = Helper.validarEntero(input, "Stock Disponible: ");
                            Medicamento medicamento = new Medicamento(nombre, precio, stockDisponible);
                            Principal.medicamentos[i] = medicamento;
                            System.out.println("Medicamento Agregado...");
                        }
                        System.out.println("Lista de Medicamentos Actualizada: ");
                        for (Medicamento medicamento : Principal.medicamentos) {
                            System.out.println(medicamento);
                        }
                        bandera = true;
                    }
                    System.out.println(Helper.repetirLetra("_", 50));
                }
                case 2 -> {
                    System.out.println("   GESTION DE MEDICOS   ");
                    int cantidad = Helper.validarEntero(input, "Cantidad de Medicos Disponibles: ");
                    for (int i = 0; i < cantidad; i++) {
                        int matricula = (int) (Math.random() * 100);
                        String nombre = Helper.validarSoloLetras(input, "Nomnbre: ");
                        String especialidad = Helper.validarEspecialidad(input);
                        Medico medico = new Medico(matricula, nombre, especialidad);
                        GestionMedicos.agregarMedico(medico);
                        System.out.println("Medico agregado correctamente...");
                    }
                    medicosDisponibles.InOrder();
                    System.out.println();
                    System.out.println(Helper.repetirLetra("_", 50));
                }
            }
        } while (opcion != 3);
    }
}
