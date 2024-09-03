/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package globant.vistas;

import globant.clases.Compra;
import globant.clases.Orden;
import globant.clases.Usuario;
import globant.clases.Venta;
import globant.controladores.TransactionControl;
import globant.controladores.UsuarioControl;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 *
 * @author Enmanuel
 */
public final class Menu {

    public static final String separador = new String(new char[50]).replace('\0', '=');
    private static final LinkedHashMap<String, Callable<String>> opciones = new LinkedHashMap<>();
    private static Double btcValue;
    private static Double ethValue;

    private Menu() {
        throw new UnsupportedOperationException("No se puede instanciar esta clase.");
    }

    public static void MenuBuilder(String tipo) {
        switch (tipo) {
            case "inicio":
                Menu.opciones.clear();
                Menu.opciones.put("1. Iniciar sesion. ", () -> {
                    Scanner scanner = new Scanner(System.in);
                    System.out.println("Escriba su correo: ");
                    String mail = scanner.nextLine();
                    System.out.println("Escriba su contraseña: ");
                    String pass = scanner.nextLine();

                    boolean verificar = UsuarioControl.verificarusuarios(mail, pass);

                    if (verificar == true) {
                        return "SesIn";
                    } else {
                        return "SesNo";
                    }
                });
                Menu.opciones.put("2. Registrarse. ", () -> {

                    Usuario usuario1 = new Usuario.Builder().build();
                    UsuarioControl.registrarUsuario(usuario1);
                    return "UsCr";
                });
                Menu.opciones.put("0. Salir del exchange. ", () -> {
                    return "Salir";
                });
                break;

            case "principal":
                Menu.opciones.clear();
                Menu.opciones.put("1. Ver precios del exchange. ", () -> {
                    return "Vexch";
                });
                Menu.opciones.put("2. Comprar al exchange. ", () -> {
                    return "Cpr";
                });
                Menu.opciones.put("3. Publicar orden de compra. ", () -> {
                    return "OrCpr";
                });
                Menu.opciones.put("4. Publicar orden de venta. ", () -> {
                    System.out.println("Comprar.");
                    return "OrVd";
                });
                Menu.opciones.put("5. Depositar fiat. ", () -> {
                    return "DepF";
                });
                Menu.opciones.put("6. Ver balance de cartera. ", () -> {
                    return "Bl";
                });
                Menu.opciones.put("7. Cerrar Sesión", () -> {
                    return "SesNo";
                });
                Menu.opciones.put("0. Salir del exchange. ", () -> {
                    return "Salir";
                });

                break;

        }

    }

    public static String seleccionarOpcion() {

        Scanner scanner = new Scanner(System.in);
        do {
            System.out.println(Menu.separador);
            System.out.println("Ingrese una de las siguientes opciones.");
            for (String key : Menu.opciones.keySet()) {
                System.out.println(key);
            }
            System.out.println(Menu.separador);

            String opcion = scanner.nextLine();
            for (String key : Menu.opciones.keySet()) {
                if (!opcion.isEmpty() && opcion.equals(String.valueOf(key.charAt(0)))) {
                    try {
                        return Menu.opciones.get(key).call();
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "";
                    }
                }
            }
            System.out.println("Opcion invalida.");
        } while (true);

    }

    public static void ejecutarOpcion(String opcion) {
        Scanner scanner = new Scanner(System.in);

        switch (opcion) {
            case "Salir":
                System.exit(0);
                break;
            case "SesIn":
                Menu.MenuBuilder("principal");
                System.out.println("Tenga en cuenta que los valores de las criptomonedas varia cada minuto.");
                break;
            case "SesNo":
                Menu.MenuBuilder("inicio");
                break;
            case "UsCr":
                Menu.MenuBuilder("inicio");
                break;
            case "Cpr":
                String[] valores = Menu.valorCompra().split(",");
                Map<String, BigDecimal> opcionesCripto = Map.of("BTC", new BigDecimal(valores[0]), "ETH", new BigDecimal(valores[1]));
                System.out.println("Escriba el tipo de cripto que desea comprar: ");
                String tipoCr = scanner.nextLine();
                if (opcionesCripto.containsKey(tipoCr)) {
                    System.out.println("Escriba la cantidad que desa comprar: ");
                    String cantidad = scanner.nextLine();
                    String conf = UsuarioControl.compraExch(opcionesCripto.get(tipoCr).toString(), cantidad, tipoCr);
                    if (conf.equals("No")) {
                        System.out.println("Saldo insuficiente.");
                        cantidad = scanner.nextLine();
                    } else {
                        TransactionControl.compExch(cantidad, tipoCr, UsuarioControl.getUser(), opcionesCripto);
                        System.out.println("Compra exitosa.");
                        cantidad = scanner.nextLine();
                    }

                } else {
                    System.out.println("Entrada invalida.");
                }
                System.out.println("Presione \"Enter\" para salir al menu.");
                tipoCr = scanner.nextLine();
                Menu.MenuBuilder("principal");
                break;

            case "OrCpr":
                System.out.println("Escriba el tipo de cripto que desea comprar: ");
                String tipo = scanner.nextLine();
                System.out.println("Escriba la cantidad que desea comprar: ");
                String cant = scanner.nextLine();
                System.out.println("Escriba cuanto está dispuesto a pagar: ");
                String max = scanner.nextLine();

                if (tipo.equals("BTC") || tipo.equals("ETH")) {
                    try {
                        BigDecimal cantidad = new BigDecimal(cant);
                        BigDecimal maximo = new BigDecimal(max);
                        Object resultado = UsuarioControl.comprar(max, cant, tipo, UsuarioControl.getUser());
                        if (resultado instanceof Compra) {
                            TransactionControl.matchOrden(UsuarioControl.getUser(), (Compra) resultado);
                        } else {
                            System.out.println("Fondos insuficientes");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ingreso de valores no válidos.");
                    }
                } else {
                    System.out.println("Tipo de cripto no válido.");
                }

                System.out.println("Presione \"Enter\" para salir al menú.");
                scanner.nextLine(); // Cambiado para evitar errores
                Menu.MenuBuilder("principal");
                break;
                
            case "OrVc":

                System.out.println("Escriba el tipo de cripto que desea vender: ");
                String tipoCipto = scanner.nextLine();
                System.out.println("Escriba la cantidad que desea vender: ");
                String cantidad = scanner.nextLine();
                System.out.println("Escriba su precio esta transaccion: ");
                String precio = scanner.nextLine();

                if (tipoCipto.equals("BTC") || tipoCipto.equals("ETH")) {
                    try {
                        double num = Double.parseDouble(cantidad);
                        double maximo = Double.parseDouble(precio);

                        if (UsuarioControl.vender(precio, cantidad, tipoCipto, UsuarioControl.getUser()) instanceof Venta) {
                            TransactionControl.matchOrden(UsuarioControl.getUser(), (Orden) UsuarioControl.vender(precio, cantidad, tipoCipto, UsuarioControl.getUser()));

                        } else {
                            System.out.println("Fondos insuficientes");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Ingreso de valores no validos.");
                    }
                }

                System.out.println("Presione \"Enter\" para salir al menu.");
                tipo = scanner.nextLine();
                Menu.MenuBuilder("principal");
                break;

            case "DepF":
                do {
                    System.out.println("Ingrese la cantidad a depositar.");
                    String num = scanner.nextLine();
                    try {
                        Double.parseDouble(num);
                        UsuarioControl.depositar(new BigDecimal(num));
                        break;
                    } catch (NumberFormatException e) {
                        System.out.println("Entrada no válida, por favor intente de nuevo.");
                    }
                } while (true);
                System.out.println("Presione \"Enter\" para salir al menu.");
                tipo = scanner.nextLine();
                Menu.MenuBuilder("principal");
                break;
            case "Bl":
                Usuario usuario = UsuarioControl.getUser();
                usuario.balance(usuario);
                System.out.println("Presione \"Enter\" para salir al menu.");
                tipo = scanner.nextLine();
                Menu.MenuBuilder("principal");

                break;
            case "Vexch":
                Menu.valorCompra();
                System.out.println("Presione \"Enter\" para salir al menu.");
                tipo = scanner.nextLine();
                Menu.MenuBuilder("principal");

                break;

            default:
        }

    }

    public static String valorCompra() {
        System.out.println("Los valores de compra en este momento son: \n"
                + "BTC: " + btcValue + "\n"
                + "ETH: " + ethValue);
        return btcValue + "," + ethValue;
    }

    public static void fluctuar() {
        Timer timer = new Timer();
        TimerTask tarea = new TimerTask() {
            @Override
            public void run() {
                Random random = new Random();
                btcValue = 50000.0 + (60000.0 - 50000.0) * random.nextDouble();
                ethValue = 2000.0 + (5000.0 - 2000.0) * random.nextDouble();
            }
        };
        long retraso = TimeUnit.SECONDS.toMillis(0);
        long intervalo = TimeUnit.SECONDS.toMillis(60);
        timer.scheduleAtFixedRate(tarea, retraso, intervalo);
    }

}
