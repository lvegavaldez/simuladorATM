package com.lds.test;

import com.lds.main.*;
import com.lds.main.java.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

public class TransferirTest {


    /*
        comprar dólares solo con pesos en alguna de sus cuentas (no olvidar el impuesto PAIS)
    */

    /*
    CA
     */
    @Test
    public void despositar_caja_ahorro_pesos() {
        Cajero cajero = setUpCajero();
        Alias alias = new Alias("dos.dos.dos");
        Assert.assertEquals("Transferencia realizada con exito", cajero.transferirDesdeCA(alias, 300d));
        Assert.assertEquals("Dinero disponible: ...... 200.0 $", cajero.consultarSaldoCA());

    }

    @Test
    public void depositar_caja_ahorro_usd() {
        Cajero cajero = setUpCajero();
        Alias alias = new Alias("dos.dos.dos");
        Assert.assertEquals("Transferencia realizada con exito", cajero.transferirDesdeCC(alias, 250d));
        Assert.assertEquals("Dinero disponible: ...... 250.0 $", cajero.consultarSaldoCC());
    }

    private Cajero setUpCajero() {

        Saldo cuentaCorriente = new CuentaCorriente(500, 1000, new Alias("uno.dos.tres"));
        Saldo cajaDeAhorroPesos = new CajaDeAhorroPesos(500, new Alias("dos.tres.uno"));
        Saldo cajaDeAhorroDolares = new CajaDeAhorroDolares(500, new Alias("tres.uno.dos"));
        Cuenta miCuenta = new Cuenta(cuentaCorriente, cajaDeAhorroPesos, cajaDeAhorroDolares);

        Saldo cuentaCorrienteOtro = new CuentaCorriente(500, 1000, new Alias("uno.uno.uno"));
        Saldo cajaDeAhorroPesosOtro = new CajaDeAhorroPesos(500, new Alias("dos.dos.dos"));
        Saldo cajaDeAhorroDolaresOtro = new CajaDeAhorroDolares(500, new Alias("tres.tres.tres"));
        Cuenta otraCuenta = new Cuenta(cuentaCorrienteOtro, cajaDeAhorroPesosOtro, cajaDeAhorroDolaresOtro);

        Cuentas cuentas = new Cuentas(Arrays.asList(miCuenta, otraCuenta));

        Billete billete100 = new Billete100(500);
        Billete billete500 = new Billete500(500);
        Billete billete1000 = new Billete1000(500);
        List<Billete> billetes = Arrays.asList(billete100, billete500, billete1000);


        DispenserEfectivo dispenserEfectivo = new DispenserEfectivo(billetes);
        return new Cajero(dispenserEfectivo, cuentas);
    }

}
