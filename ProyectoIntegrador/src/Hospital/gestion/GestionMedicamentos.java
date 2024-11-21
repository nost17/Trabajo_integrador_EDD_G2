package Hospital.gestion;

import Hospital.modelo.Medicamento;
import java.util.Random;

public class GestionMedicamentos {
    public Medicamento obtenerMedicamentoAleatorio(Medicamento[] medicamentos) {
        Random random = new Random();
        while (true) {
            int index = random.nextInt(medicamentos.length);
            Medicamento medicamento = medicamentos[index];

            if (medicamento.getStockDisponible() > 0) {
                int cantidad = random.nextInt(5) + 1;
                if (medicamento.getStockDisponible() >= cantidad) {
                    medicamento.setStockDisponible(medicamento.getStockDisponible() - cantidad);
                    return medicamento;
                }
            }
        }
    }
    
        public static Medicamento obtenerMedicamentoDisponible(int index) {

        Random random = new Random();
        Medicamento medicamento = new Medicamento();

        for (int i = 0; i < Principal.medicamentos.length; i++) {
            index = random.nextInt(Principal.medicamentos.length);
            medicamento = Principal.medicamentos[index];
        }
        return medicamento;
    }
        public static void verificarStockMedicamento(Medicamento medicamento, int cantidadNecesaria, int index){
            int stock = medicamento.getStockDisponible();
            if (medicamento.getStockDisponible() == 0){
                System.out.println("no hay stock disponible del medicamento... derivar receta medica");
            }
            if (medicamento.getStockDisponible() < cantidadNecesaria && medicamento.getStockDisponible() > 0){
                System.out.println("no hay suficiente stock para brindarle lo pedido, se le dara una cantidad menor");
            }
            if (medicamento.getStockDisponible() >= cantidadNecesaria){
                System.out.println();
                 Principal.medicamentos[index].setStockDisponible(stock - cantidadNecesaria);
            }
        }
        
}
