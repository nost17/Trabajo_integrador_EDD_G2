package Hospital.modelo;

import java.time.LocalDate;

public class Consulta {

    private Medico medicoAcargo;
    private Paciente paciente;
    private Medicamento medicacionAdministrada;
    private int cantidadAplicada;
    private LocalDate fecha;

    public Consulta(Medico medicoAcargo, Paciente paciente, Medicamento medicacionAdministrada, int cantidadAplicada, LocalDate fecha) {
        this.medicoAcargo = medicoAcargo;
        this.paciente = paciente;
        this.medicacionAdministrada = medicacionAdministrada;
        this.cantidadAplicada = cantidadAplicada;
        this.fecha = fecha;
    }
    
    
    public Medico getMedicoAcargo() {
        return medicoAcargo;
    }

    public void setMedicoAcargo(Medico medicoAcargo) {
        this.medicoAcargo = medicoAcargo;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Medicamento getMedicacionAdministrada() {
        return medicacionAdministrada;
    }

    public void setMedicacionAdministrada(Medicamento medicacionAdministrada) {
        this.medicacionAdministrada = medicacionAdministrada;
    }

    public int getCantidadAplicada() {
        return cantidadAplicada;
    }

    public void setCantidadAplicada(int cantidadAplicada) {
        this.cantidadAplicada = cantidadAplicada;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Consulta [medicoAcargo=" + medicoAcargo + ", paciente=" + paciente + ", medicacionAdministrada="
                + medicacionAdministrada + ", cantidadAplicada=" + cantidadAplicada + ", fecha=" + fecha + "]";
    }

}
