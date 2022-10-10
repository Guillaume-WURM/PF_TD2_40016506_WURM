package Exercice1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {

        /***** Question 1 *****/
        //variable de type Integer, double, long et String
        Integer a =1, b=2;
        Double c=3.0, d=4.0;
        Long e=5L, f=6L;
        String g="7", h="8";

        /******* Question 1 : version lambda *****/
        Somme<Integer> e1lambda = (x,y) -> x+y;
        System.out.println("/******* Début : Question 1 : version lambda *****/");
        System.out.println("a= " + a + " b= " +b);
        System.out.println("Integer version lambda : A + B ="+e1lambda.somme(a,b));

        Somme<Double> e2lambda = (x,y) -> x+y;
        System.out.println("c= " + c + " d= " +d);
        System.out.println("Double version lambda : C + D ="+e2lambda.somme(c,d));

        Somme<Long> e3lambda = (x,y) -> x+y;
        System.out.println("e= " + e + " f= " +f);
        System.out.println("Long version lambda : E + F ="+e3lambda.somme(e,f));

        Somme<String> e4lambda = (x,y) -> x+y;
        System.out.println("g= " + g + " h= " +h);
        System.out.println("String version lambda : G + H ="+e4lambda.somme(g,h));
        System.out.println("/******* Fin  : Question 1 : version lambda *****/" +"\n");

        /******* Question 2 : Lambda List & Map *****/
        List<String> list = new ArrayList<String>();
        Map<String, Integer> mp = new HashMap<String,Integer>();

        for(int i=0; i<10; i++){
            list.add("e"+i);
            mp.put("k"+i, i);
        }

        ToString<List<String>> toStringl1 = i -> {
            StringBuilder sb = new StringBuilder();
            String virgule = "";
            for (String s : list){
                sb.append(virgule).append(s);
                virgule = ",";
            }
            return sb.toString();
        };

        ToString<Map<String,Integer>> toStringMap = i -> {
            StringBuilder sb = new StringBuilder();
            String virgule = "";
            for (Map.Entry<String, Integer> entry : mp.entrySet()){
                sb.append(virgule).append(entry.getKey() + ":").append("v").append(entry.getValue());
                virgule = ",";
            }
            return sb.toString();
        };

        /***** Sout List & Map ******/

        System.out.println("/******* Début  : Question 2 : version lambda *****/" );
        System.out.println("List : " + toStringl1.toString(list));
        System.out.println("Map : " +toStringMap.toString(mp));
        System.out.println("/******* Fin  : Question 2 : version lambda *****/" +"\n");

        /***** Question 3 *****/
        /*
         * Function : une fonction qui prend en paramètre un objet de type T et qui retourne un objet de type R.
         *                => Convertie (map) un type vers un autre. Lambda avec une variable
         *
         * Predicate : Test l'argument d'entrée selon un critère et renvoie true ou false.
         *
         * Consummer : La fonction a un argument en entrée de type T et n'a pas de retour.
         *                => consumme une entrée et ne retourne rien
         *
         * Supplier : Ne possède pas d'argument en entrée et retourne un objet de type T.
         */






        /******* Question 1 : version lourde *****/
        Somme<Integer> e1 = new Somme<Integer>() {
            @Override
            public Integer somme(Integer a, Integer b) {
                return a+b;
            }
        };

        Somme<Double> e2 = new Somme<Double>() {
            @Override
            public Double somme(Double a, Double b) {
                return a+b;
            }
        };


        Somme<Long> e3 = new Somme<Long>() {
            @Override
            public Long somme(Long a, Long b) {
                return a+b;
            }
        };



        Somme<String> e4 = new Somme<String>() {
            @Override
            public String somme(String a, String b) {
                return a+b;
            }
        };

        /***** Question 2 : version lourde *****/
        List<String> l1 = new ArrayList<String>();
        l1.add("e1");
        l1.add("e2");

        Map<String,Integer> map1 = new HashMap<String,Integer>();
        map1.put("k1:",1);
        map1.put("k2:",2);

        ToString<List<String>> listString = new ToString<List<String>>() {
            @Override
            public String toString(List<String> a) {
                StringBuilder sb = new StringBuilder();
                String comma = "";

                for (String item : a) {
                    sb.append(comma);
                    sb.append(item);
                    comma = ", ";
                }
                return sb.toString();
            }
        };

        ToString<Map<String, Integer>> mapStringInteger = new ToString<Map<String, Integer>>() {
            @Override
            public String toString(Map<String, Integer> a) {
                StringBuilder sb = new StringBuilder();
                String val = "";

                for(Map.Entry<String, Integer> entry : a.entrySet()) {
                    sb.append(val);
                    sb.append(entry.getKey() + ":");
                    sb.append("v" + entry.getValue());
                    val = ", ";
                }


                return sb.toString();
            }
        };
        /***** Sout 2eme version ******/
        System.out.println("/******* Début : Question 1 : version lourde *****/");
        System.out.println("Integer version lourde : A+B = "+e1.somme(a,b));
        System.out.println("Double version lourde : C+D = " +e2.somme(c,d));
        System.out.println("Longue version lourde : E+F = " +e3.somme(e,f));
        System.out.println("Longue version lourde : G+H = " +e4.somme(g,h) );
        System.out.println("/******* Fin : Question 1 : version lambda *****/" +"\n");

        System.out.println("/******* Début  : Question 2 : version lourde *****/" );
        System.out.println(listString.toString(l1));
        System.out.println(mapStringInteger.toString(map1));
        System.out.println("/******* Fin  : Question 2 : version lourde *****/" +"\n");
    }
}
