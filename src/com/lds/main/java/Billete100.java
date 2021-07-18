package com.lds.main.java;

public class Billete100 implements Billete {
    Integer cantidad;

    public Billete100(Integer cantidad) {
        this.cantidad = cantidad;
    }

    public Double getValorNominal() {
        return 100d;
    }

    @Override
    public Integer getCantidad() {
        return cantidad;
    }
}
