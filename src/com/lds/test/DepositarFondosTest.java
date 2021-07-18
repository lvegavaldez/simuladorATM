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

public class DepositarFondosTest {


    /*
        comprar dólares solo con pesos en alguna de sus cuentas (no olvidar el impuesto PAIS)
    */

    /*
    CA
     */
    @Test
    public void despositar_caja_ahorro_pesos() {
        Cajero cajero = setUpCajero();
        Sobre sobre = new Sobre(new Billete100(1), new Billete500(1), new Billete1000(1));
        Assert.assertEquals("Deposito realizado con exito", cajero.depositarCA(sobre));
        Assert.assertEquals("Dinero disponible: ...... 2100.0 $", cajero.consultarSaldoCA());

    }

    @Test
    public void depositar_caja_ahorro_usd() {
        Cajero cajero = setUpCajero();
        Sobre sobre = new Sobre(new Billete100(1), new Billete500(1), new Billete1000(1));
        Assert.assertEquals("Deposito realizado con exito", cajero.depositarUsdCA(sobre));
        Assert.assertEquals("Dinero disponible: ...... 2100.0 USD", cajero.consultarSaldoUsdCA());
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
