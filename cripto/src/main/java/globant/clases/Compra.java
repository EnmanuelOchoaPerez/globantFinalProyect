/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */package globant.clases;

/**
 *
 * @author Enmanuel
 */
public class Compra extends Orden {

    public final String tipo = "compra";

    public Compra(String valorFiat, String valorCripto, String tipoCripto, Usuario dueño) {
        super(valorFiat, valorCripto, tipoCripto, dueño);
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

