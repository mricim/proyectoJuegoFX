package main.java.testLucha;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeMap;

public class MainLucha {
    public static TreeMap<Integer, TipoSoldados> tipoSoldadosTreeMap = new TreeMap();

    private static ArrayList<Soldados> listaSoldados = new ArrayList<>();
    private static ArrayList<Soldados> listaSoldados2 = new ArrayList<>();
    private static ArrayList<ArrayList> table = new ArrayList<>();

    public static void main(String[] args) {
        ArrayList<Integer> list1=new ArrayList<>();
        list1.add(0);
        list1.add(2);
        list1.add(4);
        list1.add(6);
        list1.add(8);
        ArrayList<Integer> list2=new ArrayList<>();
        list2.add(2);
        list2.add(0);
        list2.add(3);
        list2.add(5);
        list2.add(7);
        ArrayList<Integer> list3=new ArrayList<>();
        list3.add(4);
        list3.add(3);
        list3.add(0);
        list3.add(4);
        list3.add(6);
        ArrayList<Integer> list4=new ArrayList<>();
        list4.add(6);
        list4.add(5);
        list4.add(4);
        list4.add(0);
        list4.add(5);
        ArrayList<Integer> list5=new ArrayList<>();
        list5.add(8);
        list5.add(7);
        list5.add(6);
        list5.add(5);
        list5.add(0);
        table.add(list1);
        table.add(list2);
        table.add(list3);
        table.add(list4);
        table.add(list5);


        new TipoSoldados(1, "Espadachines");
        new TipoSoldados(2, "Lanceros");
        new TipoSoldados(3, "Arqueros");
        new TipoSoldados(4, "Caballeros");


        listaSoldados.add(new Soldados(1, 100));
        listaSoldados2.add(new Soldados(1, 100));

        listaSoldados.add(new Soldados(1, 100));
        listaSoldados2.add(new Soldados(1, 50));

        listaSoldados.add(new Soldados(3, 100));
        listaSoldados2.add(new Soldados(2, 100));

        listaSoldados.add(new Soldados(3, 100));
        listaSoldados2.add(new Soldados(2, 50));

        listaSoldados.add(new Soldados(4, 100));
        listaSoldados2.add(new Soldados(1, 100));

        listaSoldados.add(new Soldados(4, 50));
        listaSoldados2.add(new Soldados(1, 100));

        int count = listaSoldados.size();

        for (int i = 0; i < count; i++) {
            Soldados grupo1 = listaSoldados.get(i);
            Soldados grupo2 = listaSoldados2.get(i);
            System.out.println(grupo1 + " - " + grupo2);

            int grupo1tipo = grupo1.getTipoSoldado();
            int grupo2tipo = grupo2.getTipoSoldado();
            int grupo1Cantidad = grupo1.getCantidad();
            int grupo2Cantidad = grupo2.getCantidad();
            double grupo1Salida;
            double grupo2Salida;
            if (grupo1tipo == grupo2tipo) {
                grupo1Salida = grupo1Cantidad-(((grupo1Cantidad * 100 / grupo2Cantidad)/100)+1*20);
                grupo2Salida = grupo2Cantidad-(((grupo2Cantidad * 100 / grupo1Cantidad)/100)+1*20);
            } else {
                int tableNumber1=(Integer) table.get(grupo1tipo).get(grupo2tipo);
                if (grupo1tipo>grupo2tipo){
                    grupo1Salida=grupo1Cantidad-(((grupo1Cantidad/tableNumber1)*100)/20);
                    grupo2Salida=grupo2Cantidad-(20*100)/(grupo2Cantidad*100/grupo1Cantidad);
                }else {
                    grupo1Salida=grupo1Cantidad-(20*100)/(grupo1Cantidad*100/grupo2Cantidad);
                    grupo2Salida=grupo2Cantidad-(((grupo2Cantidad/tableNumber1)*100)/20);
                }
            }
            grupo1.setCantidad((int)grupo1Salida);
            grupo2.setCantidad((int)grupo2Salida);
            System.out.println(grupo1 + " - " + grupo2);
            System.out.println();
        }


    }
    private static int randomx(int max){
        int min=max-10;
        return new Random().nextInt(max + 1 - min) + min;
    }
}
