/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package globant.clases;

import java.math.BigDecimal;
import java.util.UUID;

/**
 *
 * @author Enmanuel
 */
public class Orden {

    private String id;
    private BigDecimal valorFiat;
    private BigDecimal valorCripto;
    private String tipoCripto;
    private Usuario dueño;

    public Orden(String valorFiat, String valorCripto, String tipoCript, Usuario dueñoo) {
        this.id = generarId();
        this.valorFiat = new BigDecimal(valorFiat);
        this.valorCripto = new BigDecimal(valorCripto);
        this.tipoCripto = tipoCripto;
        this.dueño = dueñoo;
    }

    @Override
    public String toString() {
        return valorFiat + ", " + valorCripto + ", " + tipoCripto;
    }

    public BigDecimal getValorFiat() {
        return valorFiat;
    }

    public BigDecimal getValorCripto() {
        return valorCripto;
    }

    public String getTipoCripto() {
        return tipoCripto;
    }

    public String getId() {
        return id;
    }

    public Usuario getDueño() {
        return dueño;
    }
    
    

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }

        Orden other = (Orden) obj;

        return valorFiat.equals(other.valorFiat)
                && valorCripto.equals(other.valorCripto)
                && tipoCripto.equals(other.tipoCripto);
    }

    @Override
    public int hashCode() {
        int result = valorFiat.hashCode();
        result = 31 * result + valorCripto.hashCode();
        result = 31 * result + tipoCripto.hashCode();
        return result;
    }

    private static String generarId() {
        return UUID.randomUUID().toString();
    }
}
