package com.lds.main;

import com.lds.main.java.Cuenta;
import com.lds.main.java.Saldo;

public class CajaDeAhorroDolares implements Saldo {
    private Alias alias;
    private Double saldo;

    public CajaDeAhorroDolares(Integer saldo) {
        this(saldo, Alias.crear());
    }

    public CajaDeAhorroDolares(Integer saldo, Alias alias) {
        this.alias = alias;
        this.saldo = new Double(saldo);
    }

    @Override
    public void completar(Cuenta cuenta) {
        cuenta.setCajaDeAhorroDolares(this);
    }

    @Override
    public boolean retirar(Double monto) {
        return false;
    }

    @Override
    public Double getSaldo() {
        return saldo;
    }

    @Override
    public String getMoneda() {
        return "USD";
    }

    @Override
    public void depositar(Double saldo) {
        this.saldo = Double.sum(this.saldo, saldo);
    }

    @Override
    public boolean isAliasSumar(Alias alias, Double saldo) {
        if (this.alias.equals(alias)) {
            this.saldo += saldo;
            return true;
        }
        return false;
    }
}
