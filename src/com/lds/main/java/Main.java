package com.lds.main.java;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        Scanner leer = new Scanner(System.in);

        Menu menu = new Menu();
        menu.mostrar();
        System.out.println("Seleccione una opcion");
        Integer valorIngresado = leer.nextInt();
        menu.seleccionarOpcion(valorIngresado);
    }
}
