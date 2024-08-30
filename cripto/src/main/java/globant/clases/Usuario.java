/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package globant.clases;

/**
 *
 * @author Enmanuel
 */
import java.util.UUID;
import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Usuario {

    private String id;
    private String nombre;
    private String email;
    private String password;
    private Map<String, BigDecimal> cartera;

    private Usuario(Builder builder) {
        this.id = builder.id;
        this.nombre = builder.nombre;
        this.email = builder.email;
        this.password = builder.password;
        this.cartera = builder.cartera;
    }

    private static String generarIdUnico() {
        return UUID.randomUUID().toString();
    }

    public static class Builder {

        private String id;
        private String nombre;
        private String email;
        private String password;
        private Map<String, BigDecimal> cartera = new HashMap<>();

        public Builder() {
            this.nombre = setNombre();
            this.email = setEmail();
            this.password = setPassword();
            this.id = generarIdUnico();
            this.cartera.put("USD", new BigDecimal("0.0"));
            this.cartera.put("BTC", new BigDecimal("0.0"));
            this.cartera.put("ETH", new BigDecimal("0.0"));
        }

        public String setNombre() {
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Ingrese su nombre: \n");
                String nombre = scanner.nextLine();
                if (!nombre.isEmpty()) {
                    this.nombre = nombre;
                    break;
                } else {
                    System.out.println("Nombre vacío, escriba de nuevo.");
                }
            }
            return nombre;
        }

        public String setEmail() {
            String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$";
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Ingrese su email: \n");
                String email = scanner.nextLine();
                if (email.matches(regex)) {
                    return email;
                } else {
                    System.out.println("Email invalido, escriba de nuevo.");
                }
            }
        }

        public String setPassword() {
            Scanner scanner = new Scanner(System.in);
            String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$";
            while (true) {
                System.out.println("Crea su contraseña (Minimo 8 caracteres, 1 mayúsculas, 1 numero y 1 simbolo): \n");
                String pass = scanner.nextLine();
                if (pass.length() >= 8 && pass.matches(regex)) {
                    return password;
                } else {
                    System.out.println("Contraseña debil, escriba otra.");
                }
            }
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    public void sumar(BigDecimal cantidad, String tipo) {
        cartera.put(tipo, cartera.get(tipo).add(cantidad));
    }

    public void restar(BigDecimal cantidad, String tipo) {
        cartera.put(tipo, cartera.get(tipo).subtract(cantidad));
    }

    public void balance() {
        System.out.println("Balance: ");
        for (Map.Entry<String, BigDecimal> entrada : cartera.entrySet()) {
            String tipo = entrada.getKey();
            BigDecimal valor = entrada.getValue();
            System.out.println(tipo + ": " + valor);
        }
    }

    public String getEmail() {
        return email;
    }

    public boolean validarUsuario(String email, String password) {
        return (this.email != null && this.email.equals(email)) && (this.password != null && this.password.equals(password));
    }

    @Override
    public String toString() {
        return "Usuario:" + id + "\n" + "de nombre" + nombre + "\n"
                + "Email:" + email;
    }
}
