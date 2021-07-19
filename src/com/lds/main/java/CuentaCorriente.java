package com.lds.main.java;

public class CuentaCorriente implements Saldo {
    private Double saldo;
    private Double descubierto;
    private Double limiteDescubierto;
    private Alias alias;


    public CuentaCorriente(Integer saldo, Integer descubierto) {
        this(saldo, descubierto, Alias.crear());
    }

    public CuentaCorriente(Integer saldo, Integer descubierto, Alias alias) {
        this.saldo = new Double(saldo);
        this.descubierto = new Double(descubierto);
        this.limiteDescubierto = new Double(descubierto);
        this.alias = alias;
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

    @Override
    public boolean isAliasSumar(Alias alias, Double saldo) {
        if (this.alias.equals(alias)) {
            this.saldo += saldo;
            return true;
        }
        return false;
    }
}
