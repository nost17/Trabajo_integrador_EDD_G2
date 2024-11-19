package Hospital.gestion;

import Hospital.estructuras.BinarySearchTree;
import Hospital.modelo.Medicamento;
import Hospital.modelo.Medico;
import java.util.ArrayList;
import java.util.Scanner;

public class InicioJornada {
    public static void inicioJornada(ArrayList<Integer> codigos, Medicamento[] medicamentos, BinarySearchTree<Medico> medicosDisponibles, Scanner input) {
        int opcion;
        boolean bandera = false;
        do {
            System.out.println("1- Gestion de Medicamentos");
            System.out.println("2- Gestion de Medicos");
            System.out.println("3- Volver...");
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
                        for (int i = 0; i < medicamentos.length; i++) {
                            String nombre = Helper.validarSoloLetras(input, "nombre: ");
                            double precio = Helper.validarDouble(input, "precio: ");
                            int stockDisponible = Helper.validarEntero(input, "Stock Disponible: ");
                            Medicamento medicamento = new Medicamento(nombre, precio, stockDisponible);
                            medicamentos[i] = medicamento;
                            System.out.println("Medicamento Agregado...");
                        }
                        System.out.println("Lista de Medicamentos Actualizada: ");
                        for (Medicamento medicamento : medicamentos) {
                            System.out.println(medicamento);
                        }
                        bandera = true;
                    }
                    System.out.println(Helper.repetirLetra("_", 50));
                }
                case 2 -> {
                    System.out.println("   GESTION DE MEDICOS   ");
                    /*
                    TODO :Reemplazar `for-loop` por `do-while` y preguntar si
                    quiere agregar otro medico en cada ciclo
                     */
                    int cantidad = Helper.validarEntero(input, "Cantidad de Medicos Disponibles: ");
                    for (int i = 0; i < cantidad; i++) {
                        int matricula = (int) (Math.random() * 100);
                        String nombre = Helper.validarSoloLetras(input, "Nomnbre: ");
                        String especialidad = Helper.validarEspecialidad(input);
                        Medico medico = new Medico();
                        medico.setEspecialidad(especialidad);
                        medico.setMatricula(matricula);
                        medico.setNombre(nombre);
                        medicosDisponibles.add(medico);
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
