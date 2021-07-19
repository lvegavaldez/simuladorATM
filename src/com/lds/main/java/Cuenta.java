package com.lds.main.java;

import com.lds.main.Alias;
import com.lds.main.CajaDeAhorroDolares;
import com.lds.main.CajaDeAhorroPesos;
import com.lds.main.CuentaCorriente;

import java.util.Arrays;

public class Cuenta {
    private Saldo cuentaCorriente;
    private Saldo cajaDeAhorroPesos;
    private Saldo cajaDeAhorroDolare;

    public Cuenta(Saldo... saldos) {
        Arrays.stream(saldos).forEach(saldo -> {
            saldo.completar(this);
        });

    }

    public void setCuentaCorriente(CuentaCorriente cuentaCorriente) {
        this.cuentaCorriente = cuentaCorriente;
    }

    public void setCajaDeAhorroDolares(CajaDeAhorroDolares cajaDeAhorroDolares) {
        this.cajaDeAhorroDolare = cajaDeAhorroDolares;
    }

    public void setCajaDeAhorroPesos(CajaDeAhorroPesos cajaDeAhorroPesos) {
        this.cajaDeAhorroPesos = cajaDeAhorroPesos;
    }

    public boolean retirarCA(Double monto) {
        return cajaDeAhorroPesos.retirar(monto);
    }

    public boolean retirarCA(Integer monto) {
        return retirarCA(new Double(monto));
    }

    public boolean retirarCC(Double monto) {
        return cuentaCorriente.retirar(monto);
    }

    public boolean retirarCC(Integer monto) {
        return retirarCC(new Double(monto));
    }

    public String consultarSaldoCA(PantallaPresentador presentador) {
        return presentador.mostrarSaldo(cajaDeAhorroPesos);
    }

    public String consultarCAUSD(PantallaPresentador presentador) {
        return presentador.mostrarSaldo(cajaDeAhorroDolare);
    }

    public String consultarSaldoCC(PantallaPresentador presentador) {
        return presentador.mostrarSaldo(cuentaCorriente);
    }

    public void depositarCA(Double saldo) {
        cajaDeAhorroPesos.depositar(saldo);
    }

    public void depositarUsdCA(Double saldo) {
        cajaDeAhorroDolare.depositar(saldo);
    }

    public boolean contiene(Alias alias, Double saldo) {
        return cuentaCorriente.isAliasSumar(alias, saldo) ||
                cajaDeAhorroPesos.isAliasSumar(alias, saldo) ||
                cajaDeAhorroDolare.isAliasSumar(alias, saldo);
    }
}
