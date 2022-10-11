package Exercice2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {

        /***** Initialisation Objet Paire (client) *****/
        Paire<Integer, Double> p1 = new Paire<>(98, 60.0); //taille trop petite et taille Incorrecte
        Paire<Integer, Double> p2 = new Paire<>(210, 60.0); //taille trop grande et taille Incorrecte
        Paire<Integer, Double> p3 = new Paire<>(150, 60.0); //Taille correcte, poids correcte & accesAutorise
        Paire<Integer, Double> p4 = new Paire<>(150, 151.1); //poidsTropLourd

        /***** Predicat Taille *****/
        Predicate<Paire<Integer,Double>> tailleTropPetite = p -> p.getTaille() < 100;
        Predicate<Paire<Integer,Double>> tailleTropGrande = p -> p.getTaille() > 200;
        Predicate<Paire<Integer,Double>> tailleIncorrect = tailleTropPetite.or(tailleTropGrande);
        Predicate<Paire<Integer,Double>> tailleCorrect = tailleIncorrect.negate();

        /***** Predicat Poids *****/
        Predicate<Paire<Integer,Double>> poidsTropLourd = p -> p.getPoids() > 150.0;
        Predicate<Paire<Integer,Double>> poidsCorrect = poidsTropLourd.negate();

        /***** Predicat Accès Autorisé *****/
        Predicate<Paire<Integer,Double>> accesAutorise = tailleCorrect.and(poidsCorrect);

        System.out.println("La taille de "+ p1 + " est "+ (tailleIncorrect.test(p1) ? "incorrecte" : "correcte") + " et sa taille est " + (tailleTropPetite.test(p1) ? " trop petite" : ""));
        System.out.println("La taille de "+ p2 + " est "+ (tailleIncorrect.test(p2) ? "incorrecte" : "correcte") + " et sa taille est " + (tailleTropGrande.test(p2) ? " taille Trop Grande" : ""));
        System.out.println("La taille de "+ p3 + " est "+ (tailleCorrect.test(p3) ? "correcte" : "incorrecte") + " et son poids est " + (poidsCorrect.test(p3) ? " correct" : " incorrect") + " donc l'acces est " + (accesAutorise.test(p3) ? " autorise" : " refuse"));
        System.out.println("Le poids de " + p4 + " est "+ (poidsTropLourd.test(p4) ? "trop lourd" : "") +"\n");


        /***** Question 2 : Filtrage Predicatif *****/
        Filter filtre = new Filter();
        List<Paire> client = new ArrayList<Paire>();
        client.add(p1);
        client.add(p2);
        client.add(p3);
        client.add(p4);

        List<Predicate<Paire<Integer, Double>>> listPredicat = new ArrayList<>();
        /*listPredicat.add(tailleTropPetite);
        listPredicat.add(tailleTropGrande);
        listPredicat.add(tailleIncorrect);
        listPredicat.add(poidsTropLourd);
        listPredicat.add(poidsCorrect);*/
        listPredicat.add(accesAutorise);


        System.out.println("/*********************************************/");
        System.out.println(filtre.filtragePredicatif(listPredicat,client));
        System.out.println("/*********************************************/");

    }
}
