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

public class ComprarDolaresTest {


    /*
        comprar dólares solo con pesos en alguna de sus cuentas (no olvidar el impuesto PAIS)
    */

    /*
    CA
     */
    @Test
    public void retirar_efectivo_con_dinero_suficiente_en_caja_ahorro() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCA(1));
    }

    @Test
    public void retirar_efectivo_con_dinero_insuficiente_en_caja_ahorro() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Dinero insuficiente", cajero.comprarUsdCA(10));
    }

    /*
    CC
     */
    @Test
    public void retirar_efectivo_con_dinero_suficiente_en_cc() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(1));
    }

    @Test
    public void retirar_efectivo_con_dinero_insuficiente_en_cc() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(1));
        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(5));
        Assert.assertEquals("Dinero insuficiente", cajero.comprarUsdCC(10));
    }

    @Test
    public void retirar_efectivo_con_dinero_insuficiente_junto_con_descubierto_en_cc() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(4));
        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(2));
        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(1));
        Assert.assertEquals("Efectivo entregado", cajero.comprarUsdCC(1));
        Assert.assertEquals("Dinero insuficiente", cajero.comprarUsdCC(10));
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
