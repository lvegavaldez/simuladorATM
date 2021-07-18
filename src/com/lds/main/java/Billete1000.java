package com.lds.main.java;

public class Billete1000 implements Billete {
    Integer cantidad;

    public Billete1000(Integer cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public Double getValorNominal() {
        return 1000d;
    }

    @Override
    public Integer getCantidad() {
        return cantidad;
    }
}
