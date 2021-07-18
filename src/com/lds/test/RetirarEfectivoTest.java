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

public class RetirarEfectivoTest {


    /*
    retirar efectivo (de sus cuentas en pesos)
    */

    /*
    CA
     */
    @Test
    public void retirar_efectivo_con_dinero_suficiente_en_caja_ahorro() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Efectivo entregado", cajero.retirarCA(500));
    }

    @Test
    public void retirar_efectivo_con_dinero_insuficiente_en_caja_ahorro() {
        Cajero cajero = setUpCajero();
        Assert.assertEquals("Dinero insuficiente", cajero.retirarCA(600));
    }

    @Test
    public void retirar_efectivo_con_dinero_hasta_obtener_insuficiente_en_caja_ahorro() {
        Cajero cajero = setUpCajero();
        Assert.assertEquals("Efectivo entregado", cajero.retirarCA(400));
        Assert.assertEquals("Efectivo entregado", cajero.retirarCA(100));
        Assert.assertEquals("Dinero insuficiente", cajero.retirarCA(100));
    }

    /*
    CC
     */
    @Test
    public void retirar_efectivo_con_dinero_suficiente_en_cc() {
        Cajero cajero = setUpCajero();

        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(500));
    }

    @Test
    public void retirar_efectivo_con_dinero_insuficiente_en_cc() {
        Cajero cajero = setUpCajero();
        Assert.assertEquals("Dinero insuficiente", cajero.retirarCC(3000));
    }

    @Test
    public void retirar_efectivo_con_dinero_hasta_obtener_insuficiente_en_cc() {
        Cajero cajero = setUpCajero();
        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(400));
        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(100));
        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(500));
        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(500));
        Assert.assertEquals("Dinero insuficiente", cajero.retirarCC(3000));
    }

    @Test
    public void retirar_efectivo_con_mas_dinero_que_el_saldo_cc() {
        Cajero cajero = setUpCajero();
        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(600));
        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(100));
        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(500));
        Assert.assertEquals("Efectivo entregado", cajero.retirarCC(300));
        Assert.assertEquals("Dinero insuficiente", cajero.retirarCC(300));
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
