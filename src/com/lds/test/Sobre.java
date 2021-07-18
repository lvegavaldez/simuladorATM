package com.lds.test;

import com.lds.main.java.Billete;
import com.lds.main.java.Billete100;
import com.lds.main.java.Billete1000;
import com.lds.main.java.Billete500;

import java.util.Arrays;
import java.util.List;

public class Sobre {
    Billete billete100;
    Billete billete500;
    Billete billete1000;

    public Sobre(Billete100 billete100, Billete500 billete500, Billete1000 billete1000) {
        this.billete100 = billete100;
        this.billete500 = billete500;
        this.billete1000 = billete1000;
    }

    public List<Billete> sacar() {
        return Arrays.asList(billete100, billete500, billete1000);
    }
}
