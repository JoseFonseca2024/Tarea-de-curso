import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static ArrayList<Movimientos> movimientos = new ArrayList<>();
    private static Caja caja = new Caja(0);;
    public static void main(String[] args) throws Exception {
        
        Scanner leer = new Scanner(System.in);

        boolean continuar = true;

        do {
            
            System.out.println("Sistema de operaciones de caja");

            System.out.println("Seleccione una opción:");

                System.out.println("1. Ingresar saldo");
                System.out.println("2. Registrar entrada");
                System.out.println("3. Registrar salida");
                System.out.println("4. Mostrar movimientos y saldo actual");
                System.out.println("5. Salir");

            int respuesta = leer.nextInt();

            switch (respuesta) {
                case 1:
                    ingresarsaldo(leer);
                    break;
                
                case 2:
                    registrarEntrada(leer);
                    break;
                
                case 3:

                    break;

                case 4: 

                    break;

                case 5:
                    continuar = false;
                    break;
                default:
                    break;
            }
        } while (continuar);

    }

    private static void ingresarsaldo (Scanner leer) {
        System.out.println("Ingrese el saldo existente en caja");
        double valor = leer.nextDouble();
        leer.nextLine();
        System.out.println("Ingrese la fecha del movimiento (Formato dia/mes/año) ");
        String fecha = leer.nextLine();
        Movimientos entradaSaldo = new Entradas(valor, "Ingreso de saldo", fecha);
        movimientos.add(entradaSaldo);
        caja.registrarMovimiento(entradaSaldo);
        System.out.println("Saldo registrado");

    }

    private static void registrarEntrada (Scanner leer) {

        System.out.println("Ingrese el monto de la entrada de dinero ");
        double valor = leer.nextDouble();
        leer.nextLine();

        System.out.println("Ingrese una descripcion al movimiento ");
        String descripcion = leer.nextLine();

        System.out.println("Ingrese la fecha del movimiento (Formato dia/mes/año)");
        String fecha = leer.nextLine();

        Movimientos entrada = new Entradas(valor, descripcion, fecha);
        movimientos.add(entrada);
        caja.registrarMovimiento(entrada);
        System.out.println("Entrada registrada correctamente.");
    }
}
