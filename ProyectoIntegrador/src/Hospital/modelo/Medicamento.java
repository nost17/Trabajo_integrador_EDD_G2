package Hospital.modelo;

public class Medicamento {

    private int codigo;
    private String nombre;
    private double precio;
    private int stockDisponible;
    
    public Medicamento(){
        
    }

    public Medicamento(int codigo, String nombre, double precio, int stockDisponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.stockDisponible = stockDisponible;
    }

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getStockDisponible() {
        return stockDisponible;
    }

    public void setStockDisponible(int stockDisponible) {
        this.stockDisponible = stockDisponible;
    }

    @Override
    public String toString() {
        return "Medicamento [codigo=" + codigo + ", nombre=" + nombre + ", precio=" + precio + ", stockDisponible="
                + stockDisponible + "]";
    }

}
