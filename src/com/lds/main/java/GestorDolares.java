package com.lds.main.java;

public class GestorDolares implements GestorMonedaExtrangera {
    private Double valorOficial;
    private Double impuestos;

    public GestorDolares(Double valorOficial, Double impuestos) {
        this.valorOficial = valorOficial;
        this.impuestos = impuestos;
    }


    @Override
    public Double convertir(Integer dolaresAComprar) {
        return valorOficialConImpuestos(dolaresAComprar);
    }

    private Double valorOficialConImpuestos(Integer dolaresAComprar) {
        return dolaresAComprar * multiplicarPorImpuestos(valorOficial);
    }

    private Double multiplicarPorImpuestos(Double valorOficial) {
        return valorOficial * calcularImpuestos();
    }

    private Double calcularImpuestos() {
        return (impuestos / 100) + 1;
    }
}
