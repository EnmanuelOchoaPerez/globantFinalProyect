
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
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Usuario {

    private String id;
    private String nombre;
    private String email;
    private String password;
    private LinkedHashMap<String, BigDecimal> cartera;
    private final LinkedHashMap<String, Orden> registroU = new LinkedHashMap<>();

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
        private LinkedHashMap<String, BigDecimal> cartera = new LinkedHashMap<>();

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
                String name = scanner.nextLine();

                if (!name.isEmpty()) {
                    return name;
                } else {
                    System.out.println("Nombre vacío, escriba de nuevo.");
                }
            }
        }

        public String setEmail() {
            String regex = "^[a-zA-Z0-9_-]+@[a-zA-Z0-9]+\\.[a-zA-Z]{2,6}$";

            while (true) {
                System.out.println("Ingrese su email: \n");
                Scanner scanner = new Scanner(System.in);
                String mail = scanner.nextLine();

                if (mail.matches(regex)) {
                    return mail;
                } else {
                    System.out.println("Email invalido, escriba de nuevo.");
                }
            }
        }

        public String setPassword() {

            while (true) {
                System.out.println("Crea su contraseña (Minimo 8 caracteres.):\n");
                Scanner scanner = new Scanner(System.in);
                String pass = scanner.nextLine();

                if (pass.length() >= 8) {
                    return pass;
                } else {
                    System.out.println("Contraseña debil, escriba otra.");
                }
            }
        }

        public Usuario build() {
            return new Usuario(this);
        }
    }

    public int comprobar(String cantidad, String tipo) {
        BigDecimal currency = this.cartera.get(tipo);
        BigDecimal monto = new BigDecimal(cantidad);
        if (monto.compareTo(currency) >= 0) {
            System.out.println("fondos suficientes");

        }
        return monto.compareTo(currency);
    }

    public void sumar(BigDecimal cantidad, String tipo) {
        cartera.put(tipo, cartera.get(tipo).add(cantidad));
    }

    public void restar(BigDecimal cantidad, String tipo) {
        cartera.put(tipo, cartera.get(tipo).subtract(cantidad));
    }

    public void balance(Usuario Usu) {
        System.out.println("Balance: ");
        for (Map.Entry<String, BigDecimal> entrada : Usu.cartera.entrySet()) {
            String tipo = entrada.getKey();
            BigDecimal valor = entrada.getValue();
            System.out.println(tipo + ": " + valor);
        }
    }

    public String getEmail() {
        return this.email;
    }

    public boolean validarUsuario(String email, String password) {
        return (this.email != null && this.email.equals(email)) && (this.password != null && this.password.equals(password));
    }

    @Override
    public String toString() {
        return "Usuario:" + id + "\n" + "de nombre " + nombre + "\n"
                + "Email:" + email;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Usuario other = (Usuario) obj;

        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
