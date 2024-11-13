package Hospital.modelo;

import java.time.LocalDate;

public class Cirugia {

    private Medico medicoAcargo;
    private Paciente paciente;
    private LocalDate fecha;

    public Cirugia(Medico medicoAcargo, Paciente paciente, LocalDate fecha) {
        this.medicoAcargo = medicoAcargo;
        this.paciente = paciente;
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

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Cirugia [medicoAcargo=" + medicoAcargo + ", paciente=" + paciente + ", fecha=" + fecha + "]";
    }

}
