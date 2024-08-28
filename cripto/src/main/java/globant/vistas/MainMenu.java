/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package globant.vistas;

/**
 *
 * @author Enmanuel
 */
public final class MainMenu implements Menu {

    private MainMenu() {
        throw new UnsupportedOperationException("No se puede instanciar esta clase.");
    }

    public void mostrarMenu() {
        System.out.println(Menu.separador);
        System.out.println("Que desea hacer: ");
        System.out.println("1. Comprar al exchange. ");
        System.out.println("2. Publicar orden de compra. ");
        System.out.println("3. Publicar orden de venta. ");
        System.out.println("4. Ver balance de cartera. ");
        System.out.println("5. Cerrar sesion. ");
        System.out.println("0. Salir del exchange. ");

        System.out.println(Menu.separador);
    }
}
