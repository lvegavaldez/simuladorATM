package com.lds.main.java;

import com.lds.main.Alias;

public interface Saldo {
    void completar(Cuenta cuenta);

    boolean retirar(Double monto);

    Double getSaldo();

    String getMoneda();

    void depositar(Double saldo);

    boolean isAliasSumar(Alias alias, Double saldo);
}
