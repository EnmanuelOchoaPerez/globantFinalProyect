/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package globant.controladores;

import globant.clases.Usuario;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author Enmanuel
 */
public class UsuarioControl {

    private static final LinkedHashMap<String, Usuario> usuarios = new LinkedHashMap<>();

    private UsuarioControl() {
        throw new UnsupportedOperationException("No se puede instanciar esta clase.");
    }

    public static boolean verificarusuarios() {
        Scanner scanner = new Scanner(System.in);
        if (usuarios.isEmpty()) {
            System.out.println("No existe ningun usuario registrado.");
            return false;
        }
        System.out.println("Escriba su correo: ");
        String mail = scanner.nextLine();
        System.out.println("Escriba su contraseña: ");
        String pass = scanner.nextLine();
        for (Map.Entry<String, Usuario> entrada : usuarios.entrySet()) {
            if (usuarios.get(entrada.getKey()).validarUsuario(mail, pass)) {
                System.out.println("Credenciales correctos.");
                return true;
            }
        }
        System.out.println("Email o contraseña incorrecto.");
        scanner.close();
        return false;
    }

    public static void registrarUsuario(Usuario us) {
        usuarios.put(us.getEmail(), us);
    }
}
