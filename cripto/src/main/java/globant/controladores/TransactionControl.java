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
public final class TransactionControl {

    private static final LinkedHashMap<String, Orden> libro = new LinkedHashMap<>();
    private static final LinkedHashMap<String, Orden> registroT = new LinkedHashMap<>();

    private static final LinkedHashMap<Usuario, Orden> registroEx = new LinkedHashMap<>();
    private static final LinkedHashMap<String, BigDecimal> exChange = new LinkedHashMap<>() {
        {
            put("USD", new BigDecimal("0.0"));
            put("BTC", new BigDecimal("500.0"));
            put("ETH", new BigDecimal("500.0"));
        }
    };

    private TransactionControl() {
        throw new UnsupportedOperationException("No se puede instanciar esta clase.");
    }

    public static void archivar(Orden t) {
        if (t instanceof Compra) {
            t = (Compra) t;
        }
        if (t instanceof Venta) {
            t = (Venta) t;
        }
        TransactionControl.libro.put(t.getId(), t);
    }

    public static void matchOrden(Usuario us, Orden ta) {
        for (Orden or : TransactionControl.libro.values()) {
            boolean matchTr = libro.get(or.getDueño()).getTipoCripto().equals(ta.getTipoCripto()) && libro.get(or.getDueño()).getValorCripto().equals(ta.getValorCripto());
            if (ta instanceof Compra && libro.get(or.getDueño()) instanceof Venta && matchTr) {
                ta = (Compra) ta;
                Venta value = (Venta) libro.get(or.getDueño());
                if (ta.getValorFiat().subtract(value.getValorFiat()).signum() == -1) {
                    System.out.println("No hubo ningun match con su orden, quedara agendada.");
                    return;
                }
                BigDecimal valor = ta.getValorFiat().subtract(value.getValorFiat());
                or.getDueño().sumar(value.getValorFiat(), "USD");
                us.sumar(BigDecimal.ONE, ta.getTipoCripto());
                us.sumar(valor, "USD");
                return;
            } else if (ta instanceof Venta && libro.get(or.getDueño()) instanceof Compra && matchTr) {
                ta = (Venta) ta;
                Compra value = (Compra) libro.get(or.getDueño());
                if (value.getValorFiat().subtract(ta.getValorFiat()).signum() == -1) {
                    System.out.println("No hubo ningun match con su orden, quedara agendada.");
                    return;
                }
                BigDecimal valor = value.getValorFiat().subtract(ta.getValorFiat());
                us.sumar(ta.getValorFiat(), "USD");

                or.getDueño().sumar(BigDecimal.ONE, ta.getTipoCripto());
                or.getDueño().sumar(valor, "USD");
                return;
            } else {
                System.out.println("No hubo ningun match con su orden, quedara agendada.");
                return;
            }
        }

    }

    public static void compExch(String cript, String tipoCript, Usuario user, Map<String, BigDecimal> valores) {
        BigDecimal valor = valores.get(tipoCript).multiply(new BigDecimal(cript));
        
        Orden tr = new Compra(valor.toString(), cript, tipoCript, user);
        TransactionControl.registroEx.put(user, tr);
        TransactionControl.exChange.put("USD", exChange.get("USD").add(valores.get(tipoCript)));
        TransactionControl.exChange.put(tipoCript, exChange.get(tipoCript).subtract(new BigDecimal(cript)));

    }

}
