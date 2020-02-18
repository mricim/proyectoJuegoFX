package main.java.testLucha;

import java.util.ArrayList;
import java.util.TreeMap;

public class MainLucha1 {
    public static TreeMap<Integer, TipoSoldados> tipoSoldadosTreeMap = new TreeMap();

    private static ArrayList<Soldados> listaSoldados = new ArrayList<>();
    private static ArrayList<Soldados> listaSoldados2 = new ArrayList<>();


    public static void main(String[] args) {



        new TipoSoldados(0, "Espadachines");
        new TipoSoldados(1, "Lanceros");
        new TipoSoldados(2, "Arqueros");
        new TipoSoldados(3, "Caballeros");


        listaSoldados.add(new Soldados(0, 100));
        listaSoldados2.add(new Soldados(0, 100));

        listaSoldados.add(new Soldados(0, 100));
        listaSoldados2.add(new Soldados(0, 50));

        listaSoldados.add(new Soldados(2, 100));
        listaSoldados2.add(new Soldados(1, 100));

        listaSoldados.add(new Soldados(2, 100));
        listaSoldados2.add(new Soldados(1, 50));

        listaSoldados.add(new Soldados(2, 1000));
        listaSoldados2.add(new Soldados(1, 1000));

        listaSoldados.add(new Soldados(2, 1000));
        listaSoldados2.add(new Soldados(1, 500));

        listaSoldados.add(new Soldados(2, 500));
        listaSoldados2.add(new Soldados(1, 1000));

        listaSoldados.add(new Soldados(3, 100));
        listaSoldados2.add(new Soldados(0, 100));

        listaSoldados.add(new Soldados(3, 50));
        listaSoldados2.add(new Soldados(0, 100));

        listaSoldados.add(new Soldados(0, 1000));
        listaSoldados2.add(new Soldados(3, 50));

       CalculadoraLucha calculadoraLucha = new CalculadoraLucha();

        int count = listaSoldados.size();

        //while (listaSoldados.size() > 0 && listaSoldados2.size() > 0) {
            for (int i = 0; i < count; i++) {
                Soldados grupo1 = listaSoldados.get(i);
                Soldados grupo2;
                try {
                    grupo2 = listaSoldados2.get(i);
                } catch (Exception e) {
                        break;
                }
                System.out.println(grupo1 + " - " + grupo2);
                Integer[] asd = calculadoraLucha.metodoChungo(grupo1, grupo2);
                grupo1.setCantidad(asd[0]);
                grupo2.setCantidad(asd[1]);
                System.out.println(grupo1 + " - " + grupo2);
                System.out.println();
                if (grupo1.getCantidad() <= 0) {
                    listaSoldados.remove(grupo1);
                }
                if (grupo2.getCantidad() <= 0) {
                    listaSoldados2.remove(grupo2);
                }
            }
      //  }
        System.out.println(listaSoldados);
        System.out.println(listaSoldados2);

    }


}
