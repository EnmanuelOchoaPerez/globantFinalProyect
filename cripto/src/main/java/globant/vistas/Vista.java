/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package globant.vistas;

//import globant.clases.Usuario;
import globant.clases.Usuario;
import java.util.LinkedHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import static javax.swing.UIManager.get;

/**
 *
 * @author Enmanuel
 */
public class Vista {

    public static void main(String[] args) {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        LinkedHashMap opciones = Menu.MenuBuilder("inicio");
        Menu.mostrarMenu();
        String opcion = Menu.seleccionarOpcion(opciones);
        Future<?> future = executor.submit(Menu.opciones.get(opcion));
        executor.shutdown();
//        switch (opcion) {
//            case("1"):
//            case("2"):
//            case("3"):
//            case("4"):
//            default:
//        }
    }
}
