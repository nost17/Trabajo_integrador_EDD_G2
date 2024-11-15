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
}