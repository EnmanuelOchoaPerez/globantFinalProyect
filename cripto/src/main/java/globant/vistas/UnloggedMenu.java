/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package globant.vistas;

/**
 *
 * @author Enmanuel
 */
public final class UnloggedMenu implements Menu {

    private UnloggedMenu() {
        throw new UnsupportedOperationException("No se puede instanciar esta clase.");
    }

    public static void mostrarMenu() {
        System.out.println("Bienvenido al exchange");
        System.out.println(Menu.separador);
        System.out.println("Que desea hacer: ");
        System.out.println("1. Iniciar sesion. ");
        System.out.println("2. Registrarse. ");
        System.out.println("0. Salir del exchange. ");
        System.out.println(Menu.separador);
    }
}
