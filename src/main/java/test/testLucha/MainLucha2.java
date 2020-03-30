package main.java.test.testLucha;

import java.util.Collections;
import java.util.HashMap;
import java.util.TreeMap;

public class MainLucha2 {
    protected static TreeMap<Integer, TipoSoldados> tipoSoldadosTreeMap = new TreeMap();

    protected static TreeMap<Integer, SoldadosXX> ejercito1 = new TreeMap<>(Collections.reverseOrder());
    protected static TreeMap<Integer, SoldadosXX> ejercito2 = new TreeMap<>(Collections.reverseOrder());
    protected static HashMap<String, Posicion> listaPosicionesSoldados1 = new HashMap<>();
    protected static HashMap<String, Posicion> listaPosicionesSoldados2 = new HashMap<>();

    public static void main(String[] args) {
        new TipoSoldados(0, "Espadachines");
        new TipoSoldados(1, "Lanceros");
        new TipoSoldados(2, "Arqueros");
        new TipoSoldados(3, "Caballeros");


        ejercito1.put(0, new SoldadosXX(0, 10000));
        ejercito2.put(0, new SoldadosXX(0, 10000));

        ejercito1.put(1, new SoldadosXX(1, 10000));
        ejercito2.put(1, new SoldadosXX(1, 10000));

        ejercito1.put(2, new SoldadosXX(2, 10000));
        ejercito2.put(2, new SoldadosXX(2, 10000));

        ejercito1.put(3, new SoldadosXX(3, 10000));
        ejercito2.put(3, new SoldadosXX(3, 10000));

        int ejecito1count = 0;
        for (SoldadosXX soldadosXX : ejercito1.values()) {
            ejecito1count += soldadosXX.getCantidad();
        }
        int ejecito2count = 0;
        for (SoldadosXX soldadosXX : ejercito2.values()) {
            ejecito2count += soldadosXX.getCantidad();
        }
        boolean ejecito1puede = false;
        if (ejecito1count > 7000) {
            ejecito1puede = true;
        }
        boolean ejecito2puede = false;
        if (ejecito2count > 7000) {
            ejecito2puede = true;
        }
        for (SoldadosXX soldadosXX : ejercito1.values()) {
            for (int i = listaPosicionesSoldados1.size(); i < 7; i++) {
                if (soldadosXX.getCantidad() >= 1000) {
                    new Posicion(1, i, 1, 1000, soldadosXX);
                }else {
                    break;
                }
            }
        }
        for (SoldadosXX soldadosXX : ejercito2.values()) {
            for (int i = listaPosicionesSoldados2.size(); i < 7; i++) {
                if (soldadosXX.getCantidad() >= 1000) {
                    new Posicion(2, i, 2, 1000, soldadosXX);
                }else {
                    break;
                }
            }
        }

        for (Posicion posiciones : listaPosicionesSoldados1.values()) {
            System.out.println(posiciones);
        }
        System.out.println();
        for (Posicion posiciones : listaPosicionesSoldados2.values()) {
            System.out.println(posiciones);
        }
        System.out.println();



        CalculadoraLucha calculadoraLucha = new CalculadoraLucha();

        int count = ejercito1.size();

        //while (listaSoldados.size() > 0 && listaSoldados2.size() > 0) {
            for (int i = 0; i < count; i++) {
                SoldadosXX grupo1 = ejercito1.get(i);
                SoldadosXX grupo2;
                try {
                    grupo2 = ejercito2.get(i);
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
                    ejercito1.remove(grupo1);
                }
                if (grupo2.getCantidad() <= 0) {
                    ejercito2.remove(grupo2);
                }
            }
      //  }
        System.out.println(ejercito1);
        System.out.println(ejercito2);

    }


}
