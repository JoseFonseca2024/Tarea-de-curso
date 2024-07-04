public class Caja {

    private double saldo;

    
    public Caja (double saldo) {
        this.saldo = saldo = 0.00;
    }

    public double getSaldo() {
        return saldo;
    }

    public void registrarMovimiento(Movimientos movimiento) {
        if (movimiento.getTipo().equals("Entrada")) {
            saldo += movimiento.getMonto();
        } else if (movimiento.getTipo().equals("Salida")) {
            saldo -= movimiento.getMonto();
        }
    }
}
