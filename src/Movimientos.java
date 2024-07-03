abstract class Movimientos {

    private String tipo;
    private double monto;
    private String descripcion;
    private String fecha;

    public Movimientos (String tipo, double monto, String descripcion, String fecha) {
        this.tipo = tipo;
        this.monto = monto;
        this.descripcion = descripcion;
        this.fecha = fecha;
    }
    public String getFecha() {
        return fecha;
    }

    public String getTipo() {
        return tipo;    
    }

    public double getMonto() {
        return monto;
    }

    public String getDescripcion() {
        return descripcion;
    }

    

}
