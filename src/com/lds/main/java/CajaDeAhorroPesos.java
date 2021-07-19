package com.lds.main.java;

public class CajaDeAhorroPesos implements Saldo {
    private Alias alias;
    private Double saldo;

    public CajaDeAhorroPesos(Integer saldo) {
        this(saldo, Alias.crear());
    }

    public CajaDeAhorroPesos(Integer saldo, Alias alias) {
        this.saldo = new Double(saldo);
        this.alias = alias;
    }

    @Override
    public void completar(Cuenta cuenta) {
        cuenta.setCajaDeAhorroPesos(this);
    }

    @Override
    public boolean retirar(Double monto) {
        if (saldo >= monto) {
            saldo = Double.sum(saldo, (monto * -1));
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

    @Override
    public boolean isAliasSumar(Alias alias, Double saldo) {
        if (this.alias.equals(alias)) {
            this.saldo = Double.sum(this.saldo, saldo);
            return true;
        }
        return false;
    }
}
