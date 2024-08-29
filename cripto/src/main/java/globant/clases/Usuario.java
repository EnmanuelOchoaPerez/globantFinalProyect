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
import org.apache.commons.validator.EmailValidator;

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
            this.id = generarIdUnico();
            this.cartera.put("USD", new BigDecimal("0.0"));
            this.cartera.put("BTC", new BigDecimal("0.0"));
            this.cartera.put("ETH", new BigDecimal("0.0"));
        }

        public Builder setNombre() {
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
            return this;
        }

        public Builder setEmail() {
            EmailValidator validator = EmailValidator.getInstance();
            Scanner scanner = new Scanner(System.in);
            while (true) {
                System.out.println("Ingrese su email: \n");
                String email = scanner.nextLine();
                if (validator.isValid(email)) {
                    this.email = email;
                    break;
                } else {
                    System.out.println("Email inválido, escriba de nuevo.");
                }
            }
            return this;
        }

        public Builder setPassword() {
            Scanner scanner = new Scanner(System.in);
            String regex = "^(?=.*[0-9])(?=.*[A-Z])(?=.*[!@#$%^&*()_+\\-=\\[\\]{};':\"\\\\|,.<>\\/?]).+$";
            while (true) {
                System.out.println("Crea su contraseña (min 12 caracteres, con mayúsculas, números y símbolos): \n");
                String pass = scanner.nextLine();
                if (pass.length() >= 12 && pass.matches(regex)) {
                    this.password = pass;
                    break;
                } else {
                    System.out.println("Contraseña débil, escriba otra.");
                }
            }
            return this;
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
    @Override
    public String toString() {
        return "Usuario:" + id + "\n" + "de nombre" + nombre + "\n" +
               "Email:" + email;
    }
}
