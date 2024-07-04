import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class App {

    private static String rutaname = "C:\\Users\\Usuario\\Desktop\\Tarea de curso\\src\\Archivo\\";
    private static ArrayList<Movimientos> movimientos = new ArrayList<>();
    private static Caja caja = new Caja(0);;

    public static void main(String[] args) throws Exception {
        Scanner leer = new Scanner(System.in);

        leer();

        boolean continuar = true;

        do {
            System.out.println("==================================");
            System.out.println("||Sistema de operaciones de caja||");
            System.out.println("==================================");
            System.out.println("Seleccione una opción:");

                System.out.println("1. Ingresar saldo");
                System.out.println("2. Registrar entrada");
                System.out.println("3. Registrar salida");
                System.out.println("4. Mostrar movimientos y saldo actual");
                System.out.println("5. Guardar y Salir");

            int respuesta = leer.nextInt();

            switch (respuesta) {
                case 1:
                System.out.println("==================================");
                    ingresarsaldo(leer);
                    break;
                
                case 2:
                System.out.println("==================================");
                    registrarEntrada(leer);
                    break;
                
                case 3:
                System.out.println("==================================");
                    registrarSalida(leer);
                    break;

                case 4: 
                System.out.println("==================================");
                    mostrarMovimientos();
                    break;

                case 5:
                System.out.println("==================================");
                    continuar = false;
                    break;
                default:
                    break;
            }
        } while (continuar);

        escribir();

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

    private static void registrarSalida(Scanner leer) {
        System.out.println("Ingrese el monto de la salida: ");
        double montoSalida = leer.nextDouble();
        leer.nextLine(); 
        if (montoSalida > caja.getSaldo()) {
            System.out.println("Error: No se puede registrar una salida mayor al saldo disponible.");
            return;
        }
        System.out.println("Ingrese la descripción de la salida: ");
        String descripcionSalida = leer.nextLine();
        System.out.println("Ingrese la fecha del movimiento (dia/mes/año): ");
        String fecha = leer.nextLine();
        Movimientos salida = new Salidas(montoSalida, descripcionSalida, fecha);
        movimientos.add(salida);
        caja.registrarMovimiento(salida);
        System.out.println("Salida registrada correctamente.");
    }
    
    private static void mostrarMovimientos() {
       if (!movimientos.isEmpty()) {
        for (Movimientos movimiento : movimientos) {
            System.out.println(movimiento.getFecha() + " - " + movimiento.getTipo() + " - " + movimiento.getMonto() + " | " +movimiento.getDescripcion()+ " | ");
        }
       }
        System.out.println("Saldo actual de caja: "+caja.getSaldo());
    }

     private static void escribir() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(rutaname+"Movimiento.txt", true))) {
            for (Movimientos movimiento : movimientos) {
                writer.write(movimiento.getFecha() + "," + movimiento.getTipo() + "," + movimiento.getMonto() + "," + movimiento.getDescripcion());
                writer.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        }

        private static void leer() {
        try (BufferedReader reader = new BufferedReader(new FileReader(rutaname+"Movimiento.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                String[] partes = linea.split(",");
                String fecha = partes[0];
                String tipo = partes[1];
                double monto = Double.parseDouble(partes[2]);
                String descripcion = partes[3];

                Movimientos movimiento;
                if (tipo.equals("Entrada")) {
                    movimiento = new Entradas(monto, descripcion, fecha);
                } else {
                    movimiento = new Salidas(monto, descripcion, fecha);
                }

                movimientos.add(movimiento);
                caja.registrarMovimiento(movimiento);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
