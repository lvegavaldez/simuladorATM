package com.lds.main.java;

public interface Billete {
    Double getValorNominal();
    Integer getCantidad();
    default Double sumar(){
        return getValorNominal() * getCantidad();
    }
}
