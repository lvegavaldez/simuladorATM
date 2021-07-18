package com.lds.test;

import com.lds.main.CajaDeAhorroDolares;
import com.lds.main.CajaDeAhorroPesos;
import com.lds.main.CuentaCorriente;
import com.lds.main.Cuentas;
import com.lds.main.java.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class ConsultarSaldoTest {


    /*
        comprar dólares solo con pesos en alguna de sus cuentas (no olvidar el impuesto PAIS)
    */

    /*
    CA
     */
    @Test
    public void consultar_saldo_caja_de_ahorro() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Dinero disponible: ...... 500.0 $", cajero.consultarSaldoCA());
    }

    @Test
    public void consultar_saldo_caja_de_ahorro_usd() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Dinero disponible: ...... 500.0 USD", cajero.consultarSaldoUsdCA());
    }

    /*
    CC
     */
    @Test
    public void consultar_saldo_cuenta_corriente() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Dinero disponible: ...... 500.0 $", cajero.consultarSaldoCC());
    }

    @Test
    public void retirar_efectivo_con_dinero_insuficiente_en_cc() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(1));
        Assert.assertEquals("Dinero disponible: ...... 333.07 $", cajero.consultarSaldoCC());
        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(5));
        Assert.assertEquals("Dinero disponible: ...... -501.58 $", cajero.consultarSaldoCC());
        Assert.assertEquals("Dinero insuficiente", cajero.comprarUsdCC(10));
        Assert.assertEquals("Dinero disponible: ...... -501.58 $", cajero.consultarSaldoCC());

    }

    @Test
    public void retirar_efectivo_con_dinero_insuficiente_junto_con_descubierto_en_cc() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(4));
        Assert.assertEquals("Dinero disponible: ...... -167.72 $", cajero.consultarSaldoCC());
        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(2));
        Assert.assertEquals("Dinero disponible: ...... -501.58 $", cajero.consultarSaldoCC());
        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(1));
        Assert.assertEquals("Dinero disponible: ...... -668.51 $", cajero.consultarSaldoCC());
        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(1));
        Assert.assertEquals("Dinero disponible: ...... -835.44 $", cajero.consultarSaldoCC());
        Assert.assertEquals("Dinero insuficiente", cajero.comprarUsdCC(10));
        Assert.assertEquals("Dinero disponible: ...... -835.44 $", cajero.consultarSaldoCC());
    }


    private Cajero setUpCajero() {

        Saldo cuentaCorriente = new CuentaCorriente(500, 1000);
        Saldo cajaDeAhorroPesos = new CajaDeAhorroPesos(500);
        Saldo cajaDeAhorroDolares = new CajaDeAhorroDolares(500);
        Cuenta cuenta = new Cuenta(cuentaCorriente, cajaDeAhorroPesos, cajaDeAhorroDolares);

        Cuentas cuentas = new Cuentas(Collections.singletonList(cuenta));

        Billete billete100 = new Billete100(500);
        Billete billete500 = new Billete500(500);
        Billete billete1000 = new Billete1000(500);
        List<Billete> billetes = Arrays.asList(billete100, billete500, billete1000);


        DispenserEfectivo dispenserEfectivo = new DispenserEfectivo(billetes);
        return new Cajero(dispenserEfectivo, cuentas);
    }

}
