package com.lds.main.java;

import com.lds.main.Cuentas;
import com.lds.test.Sobre;

import java.util.List;

public class Cajero {

    private static final String CUIT_HACK = "1-234-5";
    private DispenserEfectivo dispenserEfectivo;
    private Cuentas cuentas;
    private GestorMonedaExtrangera gestorDolares;
    private PantallaPresentador presentador;

    public Cajero(DispenserEfectivo dispenserEfectivo, Cuentas cuentas) {
        this.dispenserEfectivo = dispenserEfectivo;
        this.cuentas = cuentas;
        this.gestorDolares = new GestorDolares(101.17, 65.0);
        this.presentador = new MensajePresentador();

    }

    public String retirarCA(Integer monto) {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        return presentador.mensaje(dispenserEfectivo.retirar(monto) && cuenta.retirarCA(monto));
    }

    public String retirarCC(Integer monto) {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        return presentador.mensaje(dispenserEfectivo.retirar(monto) && cuenta.retirarCC(monto));
    }

    public String comprarUsdCA(Integer dolaresAComprar) {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        return presentador.mensaje(cuenta.retirarCA(gestorDolares.convertir(dolaresAComprar)));

    }

    public String comprarUsdCC(Integer dolaresAComprar) {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        return presentador.mensaje(cuenta.retirarCC(gestorDolares.convertir(dolaresAComprar)));

    }

    public String consultarSaldoCA() {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        return cuenta.consultarSaldoCA(presentador);
    }

    public String consultarSaldoUsdCA() {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        return cuenta.consultarCAUSD(presentador);
    }

    public String consultarSaldoCC() {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        return cuenta.consultarSaldoCC(presentador);
    }

    public String depositarCA(Sobre sobre) {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        dispenserEfectivo.sumarDinero(sobre);
        Double saldo = contarSaldo(sobre);
        cuenta.depositarCA(saldo);
        return "Deposito realizado con exito";
    }

    private Double contarSaldo(Sobre sobre) {
        List<Billete> billetes = sobre.sacar();
        return billetes.stream().mapToDouble(Billete::sumar).sum();
    }

    public String depositarUsdCA(Sobre sobre) {
        Cuenta cuenta = cuentas.buscar(CUIT_HACK);
        dispenserEfectivo.sumarDinero(sobre);
        Double saldo = contarSaldo(sobre);
        cuenta.depositarUsdCA(saldo);
        return "Deposito realizado con exito";
    }
}
