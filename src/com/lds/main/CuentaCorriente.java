package com.lds.main;

import com.lds.main.java.Cuenta;
import com.lds.main.java.Saldo;

public class CuentaCorriente implements Saldo {
    private Double saldo;
    private Double descubierto;
    private Double limiteDescubierto;

    public CuentaCorriente(Integer saldo, Integer descubierto) {
        this.saldo = new Double(saldo);
        this.descubierto = new Double(descubierto);
        this.limiteDescubierto = new Double(descubierto);
    }


    @Override
    public void completar(Cuenta cuenta) {
        cuenta.setCuentaCorriente(this);
    }

    @Override
    public boolean retirar(Double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        } else if (descubierto + saldo >= monto) {

            descubierto = descubierto - (monto - saldo);
            saldo = new Double(0);
            return true;
        }

        return false;
    }

    @Override
    public Double getSaldo() {
        return saldo + ((limiteDescubierto - descubierto) * -1);
    }

    @Override
    public String getMoneda() {
        return "$";
    }

    @Override
    public void depositar(Double saldo) {
        this.saldo = Double.sum(this.saldo, saldo);
    }
}
