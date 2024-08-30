/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package globant.vistas;

import globant.clases.Usuario;
import globant.controladores.UsuarioControl;
import java.util.LinkedHashMap;
import java.util.Scanner;
import java.util.concurrent.Callable;

/**
 *
 * @author Enmanuel
 */
public abstract class Menu {

    public static final String separador = new String(new char[50]).replace('\0', '=');
    public static final LinkedHashMap<String, Callable<?>> opciones = new LinkedHashMap<>();
            
    private Menu() {
        throw new UnsupportedOperationException("No se puede instanciar esta clase.");
    }

    public static LinkedHashMap MenuBuilder(String tipo) {
        switch (tipo) {
            case "inicio":
                System.out.println("Bienvenido al exchange");
                System.out.println(Menu.separador);
                opciones.clear();
                opciones.put("1. Iniciar sesion. ", () -> {
                    UsuarioControl.verificarusuarios();
                    return null;
                });
                opciones.put("2. Registrarse. ", () -> {
                    Usuario usuario = new Usuario.Builder().build();
                    UsuarioControl.registrarUsuario(usuario);
                    return usuario;
                });
                opciones.put("0. Salir del exchange. ", () -> {
                    System.exit(0);
                    return null;
                });
                break;

            case "principal":
                opciones.clear();
                opciones.put("1. Comprar al exchange. ", () -> {
                    System.out.println("Comprar.");
                    return null;
                });
                opciones.put("2. Publicar orden de compra. ", () -> {
                    System.out.println("Comprar.");
                    return null;
                });
                opciones.put("3. Publicar orden de venta. ", () -> {
                    System.out.println("Comprar.");
                    return null;
                });
                opciones.put("4. Ver balance de cartera. ", () -> {
                    System.out.println("Comprar.");
                    return null;
                });
                opciones.put("5. Cerrar Sesión", () -> {
                    System.out.println("Comprar.");
                    return null;
                });
                opciones.put("0. Salir del exchange. ", () -> {
                    System.exit(0);
                    return null;
                });

                break;
        }
        return opciones;
    }

    public static void mostrarMenu() {
        for (String key : opciones.keySet()) {
            System.out.println(key);
        }
    }

    public static String seleccionarOpcion(LinkedHashMap<String, Callable<?>> opciones) {
        Scanner scanner = new Scanner(System.in);
        String opcion = scanner.nextLine();
        for (String key : opciones.keySet()) {
            if (!opcion.isEmpty() && opcion.equals(String.valueOf(key.charAt(0)))) {
                return key;
            }
        }
        scanner.close();
        return "";
    }
}
