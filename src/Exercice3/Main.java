package Exercice3;

import java.util.Collection;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;

public class Main {
    public static void main(String[] args) {
        Matiere m1 = new Matiere("MAT1");
        Matiere m2 = new Matiere("MAT2");
        UE ue1 = new UE("UE1", Map.of(m1, 2, m2, 2));
        Matiere m3 = new Matiere("MAT3");
        UE ue2 = new UE("UE2", Map.of(m3, 1));
        Annee a1 = new Annee(Set.of(ue1, ue2));
        Etudiant e1 = new Etudiant("39001", "Alice", "Merveille", a1);
        e1.noter(m1, 12.0);
        e1.noter(m2, 14.0);
        e1.noter(m3, 10.0);
        System.out.println(e1);
        Etudiant e2 = new Etudiant("39002", "Bob", "Eponge", a1);
        e2.noter(m1, 14.0);
        e2.noter(m3, 14.0);
        Etudiant e3 = new Etudiant("39003", "Charles", "Chaplin", a1);
        e3.noter(m1, 18.0);
        e3.noter(m2, 5.0);
        e3.noter(m3, 14.0);

        System.out.println("************ Début : Exercice 3 : Question 1 : V1 ************");
        afficheSi("**TOUS LES ETUDIANTS", etudiant -> etudiant.notes().containsKey(m3),a1);
        System.out.println("************ Fin : Exercice 3 : Question 1 : V1 ************ \n");


        System.out.println("************ Début : Exercice 3 : Question 1 : V2 ************");
        afficheSi2("**TOUS LES ETUDIANTS", etudiant -> etudiant.notes().containsKey(m3),a1);
        System.out.println("************ Fin : Exercice 3 : Question 1 : V2 ************ \n");

        System.out.println("************ Début : Exercice 3 : Question 2 : aDEF ************");
        afficheSi2("**Etudiant défaillant", aDEF,a1);
        System.out.println("************ Fin : Exercice 3 : Question 2 : aDEF ************\n");

        System.out.println("************ Début : Exercice 3 : Question 3 : aNoteEliminatoire ************");
        afficheSi2("**ETUDIANTS AVEC NOTE ELIMINATOIRE", aNoteEliminatoire,a1);
        System.out.println("************ Fin : Exercice 3 : Question 3 : aNoteEliminatoire ************\n");

        System.out.println("************ Début : Exercice 3 : Question 4 : moyenne ************");
        afficheMoyenne(aDEF,a1);
        System.out.println("************ Fin : Exercice 3 : Question 4 : moyenne ************\n");

        /*System.out.println("************ Début : Exercice 3 : Question 5 : naPasLaMoyennev1 ************\n");
        afficheSi2("**ETUDIANTS SOUS LA MOYENNE v1",naPasLaMoyennev1,a1);
        System.out.println("************ Fin : Exercice 3 : Question 5 : naPasLaMoyennev1 ************\n");*/

        System.out.println("************ Début : Exercice 3 : Question 6 : naPasLaMoyennev2 ************");
        afficheSi2("**ETUDIANTS SOUS LA MOYENNE v1",naPasLaMoyennev2,a1);
        System.out.println("************ Fin : Exercice 3 : Question 6 : naPasLaMoyennev2 ************\n");

        System.out.println("************ Début : Exercice 3 : Question 7 : session2v1 ************");
        afficheSi2("**ETUDIANTS SOUS LA MOYENNE v1",session2v1,a1);
        System.out.println("************ Fin : Exercice 3 : Question 7 : session2v1 ************\n");

        System.out.println("************ Début : Exercice 3 : Question 8 ************");
        afficheSiV2("**TOUS LES ETUDIANTS", etudiant -> etudiant.notes().containsKey(m3), a1, new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                if (moyenne(etudiant) == null) {
                    return etudiant.prenom() + " " + etudiant.nom() + " : " + "défaillant";
                } else {
                    return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenne(etudiant);
                }
            }
        });
        System.out.println("************ Fin : Exercice 3 : Question 8  ************\n");

        System.out.println("************ Début : Exercice 3 : Question 9 : moyenneIndicative ************");
        afficheSiV2("**TOUS LES ETUDIANTS", etudiant -> etudiant.notes().containsKey(m3), a1, new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });
        System.out.println("************ Fin : Exercice 3 : Question 9 : moyenneIndicative ************\n");

        System.out.println("************ Début : Exercice 3 : Question 10 : naPasLaMoyenneGeneralise ************");
        afficheSiV2("**TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralisee(Main::moyenneIndicative), a1, new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });
        System.out.println("************ Fin : Exercice 3 : Question 10 : naPasLaMoyenneGeneralise ************\n");


        System.out.println("************ Début : Exercice 3 : Question 10 : naPasLaMoyenneGeneralise en remplaçant les deux 14/20 de Bob par des 20/20 ************");
        e2.noter(m1,20.0);
        e2.noter(m3,20.0);
        afficheSiV2("**TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralisee(Main::moyenneIndicative), a1, new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });
        System.out.println("************ Fin : Exercice 3 : Question 10 : naPasLaMoyenneGeneralise en remplaçant les deux 14/20 de Bob par des 20/20 ************\n");



    }

    /* Question 1 : AfficheSi version 1*/
    private static void afficheSi(String enTete, Predicate<Etudiant> e1, Annee anne){
        System.out.println(enTete);
        for(Etudiant e : anne.etudiants()){
            if(e1.test(e)){
                System.out.println(e);
            }
        }
    }

    /* Question 1 : AfficheSi version 2*/
    private static void afficheSi2(String enTete, Predicate<Etudiant> e1, Annee anne){
        System.out.println(enTete);
        anne.etudiants().forEach(e -> {
            if(e1.test(e)){
                System.out.println(e);
            }
        });
    }

    /* Question 2 : aDEF*/
    private static Boolean defaillant(Etudiant e){
        for (UE ue : e.annee().ues()){
            Set<Matiere> matieres = ue.ects().keySet();
            for (Matiere matiere : matieres){
                if(!e.notes().containsKey(matiere)){
                    return true;
                }
            }
        }
        return false;
    }

    private static final Predicate<Etudiant> aDEF = e -> defaillant(e);

    /*Question 3 : aNoteEliminatoire*/
    private static final Predicate<Etudiant> aNoteEliminatoire = e -> {
        Collection<Double> list = e.notes().values();
        for (Double l1 : list)
            if (l1 < 6) {
                return true;
            }
        return false;
    };

    /*Question 4 : moyenne*/

    private static Double moyenne(Etudiant etudiant) {
        double somme = 0.0;
        double sommeCoefficient = 0.0;
        if (defaillant(etudiant)){
            return null;
        }

        for (UE ue : etudiant.annee().ues()) {
            for (Integer i : ue.ects().values()) {
                sommeCoefficient += i;
            }
        }

        for (Map.Entry<Matiere, Double> entry : etudiant.notes().entrySet()) {
            Integer coefficient = coefficientMatiere(etudiant, entry.getKey());
            somme += entry.getValue() * coefficient;
        }
        return somme / sommeCoefficient;
    }


    private  static void afficheMoyenne(Predicate<Etudiant> aDEF, Annee annee){
        System.out.println("**MOYENNE DES ETUDIANTS");
        for(Etudiant e : annee.etudiants()){
            if(!aDEF.test(e)){
                System.out.println(e.nom() + " a " + moyenne(e) + " de moyenne");
            }
            else{
                System.out.println(e.nom() + " a une moyenne null");
            }
        }
    }

    /*Question 5 : naPasLaMoyennev1 */
    private static final Predicate<Etudiant> naPasLaMoyennev1 = etudiant -> moyenne(etudiant) < 10;

    /*Question 6 : naPasLaMoyennev2 */
    private static final Predicate<Etudiant> naPasLaMoyennev2 = aDEF.or(naPasLaMoyennev1);

    /*Question 7 : session2v1 */
    private static final Predicate<Etudiant> session2v1 = aDEF.or(naPasLaMoyennev1).or(aNoteEliminatoire);

    /*Question 8 : afficheSiv2 */
    private static void afficheSiV2(String enTete, Predicate<Etudiant> e, Annee annee, Function<Etudiant, String> r) {
        System.out.println(enTete);
        annee.etudiants().forEach(e1 -> {
            if (e.test(e1)) {
                System.out.println(r.apply(e1));
            }
        });
    }

    /*Question 9 : moyenneIndicative */
    public static Double moyenneIndicative(Etudiant etudiant) {
        double somme = 0.0;
        double sommeCoef = 0.0;
        for (UE ue : etudiant.annee().ues()) {
            for (Integer i : ue.ects().values()) {
                sommeCoef += i;
            }
        }

        for (Map.Entry<Matiere, Double> entry : etudiant.notes().entrySet()) {
            Integer coef = coefficientMatiere(etudiant, entry.getKey());
            if (entry.getValue() < 10) {
                somme += 10 * coef;
            } else {
                somme += entry.getValue() * coef;
            }
        }
        return somme / sommeCoef;
    }


    /*Question 10 : naPasLaMoyenneGeneralisee */
    private static Predicate<Etudiant> naPasLaMoyenneGeneralisee(Function<Etudiant, Double> moyenne) {
        return etudiant -> moyenne.apply(etudiant) < 10;
    }


    /*other functions*/
    private static Integer coefficientMatiere(Etudiant etudiant, Matiere mat) {
        for (UE ue : etudiant.annee().ues()) {
            for (Map.Entry<Matiere, Integer> couple : ue.ects().entrySet()) {
                if (couple.getKey().equals(mat)) {
                    return couple.getValue();
                }
            }
        }
        return null;
    }
}
