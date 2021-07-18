package com.lds.main.java;

import org.junit.Assert;
import org.junit.Test;

public class GestorDolaresTest {
    @Test
    public void test_00() {
        GestorMonedaExtrangera gestorDolares = new GestorDolares(101.17, 65.0);

        Assert.assertEquals("166.9305", gestorDolares.convertir(1).toString());
    }

}
