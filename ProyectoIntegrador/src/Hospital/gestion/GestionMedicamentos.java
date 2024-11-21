package Hospital.gestion;

import Hospital.modelo.Medicamento;
import java.util.Random;

public class GestionMedicamentos {

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
        throw new RuntimeException("No hay medicamentos con el stock solicitado, pida otra cantidad");
    }
}
