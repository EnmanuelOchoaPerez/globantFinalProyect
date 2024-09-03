/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package globant.clases;

import globant.clases.Orden;
import globant.clases.Orden;
import globant.clases.Usuario;
import globant.clases.Usuario;

/**
 *
 * @author Enmanuel
 */
public class Venta extends Orden {

    public final String tipo = "venta";

    public Venta(String valorFiat, String valorCripto, String tipoCripto, Usuario dueño) {
        super(valorFiat, valorCripto, tipoCripto, dueño);
    }

    @Override
    public String toString() {
        return "Tipo de Transacción: " + tipo + ", " + super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return super.equals(obj);
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }
}
