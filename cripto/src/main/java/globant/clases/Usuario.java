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

public class Usuario {

    private String id;
    private String nombre;
    private String email;
    private String password;
    private Map<String, BigDecimal> cartera;

    public Usuario(String nombre, String email, String password) {
        this.id = generarIdUnico();
        this.nombre = nombre;
        this.email = email;
        this.password = password;
        this.cartera = new HashMap<String, BigDecimal>() {{
            put("USD", new BigDecimal("1000.00"));
            put("BTC", new BigDecimal("800.00"));
            put("ETH", new BigDecimal("1560.00"));
        }};
    }

    private String generarIdUnico() {
        return UUID.randomUUID().toString();
    }

    public void sumar(BigDecimal cantidad, String tipo) {
        cartera.put(tipo, cartera.get(tipo).add(cantidad));
    }

    public void restar(BigDecimal cantidad, String tipo) {
        cartera.put(tipo, cartera.get(tipo).subtract(cantidad));
    }

    public void balance() {
        System.out.println("Balance: ");
        for(Map.Entry<String, BigDecimal> entrada : cartera.entrySet()){
            String tipo = entrada.getKey();
            BigDecimal valor = entrada.getValue();
            System.out.println(tipo + ": " + valor);
        }
    }
}
