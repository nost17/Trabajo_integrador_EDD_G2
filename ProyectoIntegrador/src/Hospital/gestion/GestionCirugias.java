package Hospital.gestion;

import Hospital.estructuras.BinarySearchTree;
import Hospital.estructuras.DoubleLinkedList;
import Hospital.estructuras.PilaGenerica;
import Hospital.modelo.Cirugia;
import Hospital.modelo.Medico;

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
        }
    }
}
