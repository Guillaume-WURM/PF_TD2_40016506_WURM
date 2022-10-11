package Exercice2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import static java.util.stream.Collectors.toList;

public class Filter<T> {
    public List<T> filtragePredicatif(List<Predicate<T>> l1, List<T> l2) {
        List<T> l3 = new ArrayList<T>();
        for (T e1 : l2){
            System.out.println("je test mon element =" +e1);
            for (Predicate<T> predicate : l1){
                System.out.println("je test mon predicat =" +predicate.test(e1) );
                if (predicate.test(e1)){
                    l3.add(e1);
                }
            }

        }
        return l3;
    }
}
