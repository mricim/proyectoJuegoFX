package main.java.testLucha;

import java.util.ArrayList;
import java.util.Random;
import java.util.TreeMap;

public class CalculadoraLucha {
    private static ArrayList<ArrayList> table = new ArrayList<>();

    public CalculadoraLucha() {
        ArrayList<Double> colum1 = new ArrayList<>();
        colum1.add(1.9);
        colum1.add(2.0);
        colum1.add(4.0);
        colum1.add(6.0);
        colum1.add(8.0);
        ArrayList<Double> colum2 = new ArrayList<>();
        colum2.add(2.0);
        colum2.add(1.0);
        colum2.add(3.0);
        colum2.add(5.0);
        colum2.add(7.0);
        ArrayList<Double> colum3 = new ArrayList<>();
        colum3.add(4.0);
        colum3.add(3.0);
        colum3.add(1.0);
        colum3.add(4.0);
        colum3.add(6.0);
        ArrayList<Double> colum4 = new ArrayList<>();
        colum4.add(6.0);
        colum4.add(5.0);
        colum4.add(4.0);
        colum4.add(1.0);
        colum4.add(5.0);
        ArrayList<Double> colum5 = new ArrayList<>();
        colum5.add(8.0);
        colum5.add(7.0);
        colum5.add(6.0);
        colum5.add(5.0);
        colum5.add(1.0);
        table.add(colum1);
        table.add(colum2);
        table.add(colum3);
        table.add(colum4);
        table.add(colum5);
    }

    Integer[] metodoChungo(Soldados grupo1, Soldados grupo2) {
        int grupo1tipo = grupo1.getTipoSoldado();
        int grupo2tipo = grupo2.getTipoSoldado();
        int grupo1Cantidad = grupo1.getCantidad();
        int grupo2Cantidad = grupo2.getCantidad();
        double grupo1Salida;
        double grupo2Salida;
        if (grupo1tipo == grupo2tipo) {
            double tableDañoBase = (double) table.get(grupo1tipo).get(1);
            double resta1 = (grupo2Cantidad * 100.0) / grupo1Cantidad / 100;
            double resta2 = (grupo1Cantidad * 100.0) / grupo2Cantidad / 100;
            grupo1Salida = grupo1Cantidad - (resta1 * randomx(tableDañoBase));
            grupo2Salida = grupo2Cantidad - (resta2 * randomx(tableDañoBase));
        } else {
            double tableNumber1 = (double) table.get(grupo1tipo).get(grupo2tipo);
            if (grupo1tipo > grupo2tipo) {
//                System.out.println(tableNumber1);
                double tableDañoBase = (double) table.get(grupo2tipo).get(0);
                double resta1 = (grupo1Cantidad / tableNumber1) / 100 * grupo2Cantidad;
                grupo1Salida = grupo1Cantidad - (resta1 / 100 * randomx(tableDañoBase));
                double resta2 = grupo2Cantidad / 100.0 * grupo1Cantidad;
//                System.out.println(resta2);
                grupo2Salida = grupo2Cantidad - (resta2 / 100 * randomx((int) tableNumber1));
            } else {
                double tableDañoBase = (double) table.get(grupo1tipo).get(0);
                double resta1 = grupo1Cantidad / 100.0 * grupo2Cantidad;
                grupo1Salida = grupo1Cantidad - (resta1 / 100 * randomx((int) tableNumber1));
                double resta2 = (grupo2Cantidad / tableNumber1) / 100 * grupo1Cantidad;
                grupo2Salida = grupo2Cantidad - (resta2 / 100 * randomx(tableDañoBase));
            }
        }/*
            if (grupo1Cantidad <= grupo1Salida) {
                int tableDañoBase = (Integer) table.get(grupo2tipo).get(0);
                System.err.println("no deberia entrar aqui");
                grupo1Salida = grupo1Cantidad - randomx(tableNumber1);
            }
            if (grupo2Cantidad < grupo2Salida) {
                System.err.println("no deberia entrar aqui");
                grupo2Salida = grupo2Cantidad - 1;
            }
            */
        Integer[] integers = {(int) grupo1Salida, (int) grupo2Salida};
        return integers;
    }


    private double randomx(double max) {
        int maximo;
        if (max < 10) {
            maximo = (int) (max * 10);
        } else if (max > 100) {
            maximo = 100;
        } else {
            maximo = (int) max;
        }
        int min = maximo - 9;
        return new Random().nextInt(maximo + 1 - min) + min;
    }
}
