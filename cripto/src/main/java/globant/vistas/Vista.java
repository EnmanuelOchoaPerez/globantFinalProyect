/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package globant.vistas;

//import globant.clases.Usuario;

import java.util.LinkedHashMap;


/**
 *
 * @author Enmanuel
 */
public class Vista {
    
    public static void main(String[] args) {
        LinkedHashMap opciones = Menu.MenuBuilder("inicio");
        Menu.mostrarMenu();
        System.out.println(Menu.seleccionarOpcion(opciones));
    }
}
