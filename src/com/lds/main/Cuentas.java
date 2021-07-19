package com.lds.main;

import com.lds.main.java.Cuenta;

import java.util.List;

public class Cuentas {
    private List<Cuenta> cuentas;

    public Cuentas(List<Cuenta> cuentas) {
        this.cuentas = cuentas;
    }

    public Cuenta buscar(String cuit) {
        //TODO falta proceso de busqueda de una cuenta completa
        return cuentas.get(0);
    }

    public boolean buscar(Alias alias, Double monto) {
        //TODO Proceso de buscar un saldo dentro de las cuentas
        for (Cuenta cuenta : cuentas) {
            if (cuenta.contiene(alias, monto)) {
                return true;
            }
        }
        return false;
    }
}
