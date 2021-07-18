package com.lds.main.java;

public class Billete500 implements Billete {
    Integer cantidad;

    public Billete500(Integer cantidad) {
        this.cantidad = cantidad;
    }


    @Override
    public Double getValorNominal() {
        return 500d;
    }

    @Override
    public Integer getCantidad() {
        return cantidad;
    }

}
