/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package globant.controladores;

import globant.clases.Compra;
import globant.clases.Orden;
import globant.clases.Usuario;
import globant.clases.Venta;
import java.math.BigDecimal;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 *
 * @author Enmanuel
 */
public final class UsuarioControl {

    private static final LinkedHashMap<String, Usuario> usuarios = new LinkedHashMap<>();
    private static Usuario acctual;

    private UsuarioControl() {
        throw new UnsupportedOperationException("No se puede instanciar esta clase.");
    }

    public static boolean verificarusuarios(String mail, String pass) {

        for (Map.Entry<String, Usuario> entrada : usuarios.entrySet()) {
            if (entrada.getValue().validarUsuario(mail, pass)) {
                acctual = entrada.getValue();
                return true;
            }
        }
        return false;
    }

    public static Usuario getUser() {
        return acctual;
    }

    public static void registrarUsuario(Usuario us) {
        if (UsuarioControl.usuarios.containsValue(us)) {
            System.out.println("El usuario ya esta registrado");
        } else {
            UsuarioControl.usuarios.put(us.getEmail(), us);
        }
    }

    public static void cerrarSesion() {
        acctual = null;
    }

    public static Compra comprar(String maximo, String cript, String tipoCript, Usuario u) {
        BigDecimal maximoBD = new BigDecimal(maximo);
        BigDecimal criptBD = new BigDecimal(cript);

        if (acctual.comprobar(maximo, "USD") >= 0) {
            acctual.restar(maximoBD, "USD");
            Compra t = new Compra(maximo, cript, tipoCript, u);
            TransactionControl.archivar(t);

            return t;
        } else {
            return null;
        }
    }

    public static Object vender(String precio, String cript, String tipoCript, Usuario u) {

        if (acctual.comprobar(cript, tipoCript) == 1 | (acctual.comprobar(cript, tipoCript) == 0)) {
            acctual.restar(new BigDecimal(cript), tipoCript);
            Venta t = new Venta(precio, cript, tipoCript, u);
            TransactionControl.archivar(t);
            return t;
        } else {
            return "";
        }
    }

    public static String compraExch(String valor, String cript, String tipoCript) {

        if (acctual.comprobar(valor, "USD") == 1 | (acctual.comprobar(valor, "USD") == 0)) {
            acctual.restar(new BigDecimal(valor), "USD");
            acctual.sumar(new BigDecimal(cript), tipoCript);
            return "compra";
        } else {
            return "No";
        }
    }

    public static void depositar(BigDecimal cantidad) {
        acctual.sumar(cantidad, "USD");
    }
}
