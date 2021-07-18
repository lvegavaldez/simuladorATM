package com.lds.main.java;

public interface Saldo {
    void completar(Cuenta cuenta);

    boolean retirar(Double monto);

    Double getSaldo();

    String getMoneda();

    void depositar(Double saldo);
}
