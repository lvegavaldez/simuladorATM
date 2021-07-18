package com.lds.main;

import com.lds.main.java.Cuenta;

import java.util.List;

public class Cuentas {
    private List<Cuenta> cuentas;

    public Cuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta buscar(String cuit) {
        return cuentas.get(0);
    }
}
