package com.lds.main;

import com.lds.main.java.Cuenta;
import com.lds.main.java.Saldo;

public class CajaDeAhorroPesos implements Saldo {
    private Double saldo;

    public CajaDeAhorroPesos(Integer saldo) {
        this.saldo = new Double(saldo);
    }

    @Override
    public void completar(Cuenta cuenta) {
        cuenta.setCajaDeAhorroPesos(this);
    }

    @Override
    public boolean retirar(Double monto) {
        if (saldo >= monto) {
            saldo -= monto;
            return true;
        }
        return false;
    }

    public Double getSaldo() {
        return saldo;
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
