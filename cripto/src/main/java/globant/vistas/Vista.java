/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package globant.vistas;

import globant.controladores.TransactionControl;
import java.util.Random;

/**
 *
 * @author Enmanuel
 */

public class Vista {

    public static void main(String[] args) {
        Menu.fluctuar();
        
        System.out.println("Bienvenido al exchange");
        Menu.MenuBuilder("inicio");
        while (true) {
            String opcion = Menu.seleccionarOpcion();
            Menu.ejecutarOpcion(opcion);
        }
    }
}
