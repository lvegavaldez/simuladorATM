package com.lds.main.java;

public class MensajePresentador implements PantallaPresentador {
    private static final String MENSAJE_EXITOSO = "Efectivo entregado";
    private static final String MENSAJE_INSUFICIENTE = "Dinero insuficiente";

    @Override
    public String mensaje(boolean condicion) {
        return condicion ? MENSAJE_EXITOSO : MENSAJE_INSUFICIENTE;
    }

    @Override
    public String mostrarSaldo(Saldo saldo) {
        return "Dinero disponible: ...... " + redondearSaldo(saldo.getSaldo()) + " " + saldo.getMoneda();
    }

    private String redondearSaldo(Double saldo) {
        Double saldoRedondeado = Math.round(saldo * 100.0) / 100.0;
        return saldoRedondeado.toString();
    }
}
