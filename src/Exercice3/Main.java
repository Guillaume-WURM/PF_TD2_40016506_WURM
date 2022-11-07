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

        /* Exercice 3 Question 1*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 1 :");
        afficheSi(a1, "**TOUS LES ETUDIANTS\n", etudiant -> etudiant.notes().containsKey(m3));

        /* Exercice 3 Question 2*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 2 :");
        afficheSi(a1,"Etudiant défaillant", aDEF);

        /* Exercice 3 Question 3*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 3 :");
        afficheSi(a1,"**ETUDIANTS AVEC NOTE ELIMINATOIRE\n", aNoteEliminatoire);

        /* Exercice 3 Question 4*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 4 :");
        afficherMoyenne(a1, aDEF);

        /* Exercice 3 Question 5*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 5 :");
        // afficheSi(a1,"**ETUDIANTS SOUS LA MOYENNE\n", naPasLaMoyennev1);
        System.out.println("Erreur affiché à cause de DEF : Erreur Finished with non-zero exit value");

        /* Exercice 3 Question 6*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 6 :");
        afficheSi(a1,"**ETUDIANTS SOUS LA MOYENNE", naPasLaMoyennev2);

        /* Exercice 3 Question 7*/
        System.out.println("-------------------------------");
        System.out.println("Exercice 3 \nQuestion 7 :");
        // afficheSi(a1,"**ETUDIANTS EN SESSION 2 (v2)", session2v1);
        afficheSi2(a1,"**ETUDIANTS EN SESSION 2 (v2)", session2v1);

        /*Exercice 3 Question 8*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 8 :");
        afficheSiv2(a1,"**TOUS LES ETUDIANTS", etudiant -> etudiant.notes().containsKey(m3),new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                if (moyenne(etudiant) == null) {
                    return etudiant.prenom() + " " + etudiant.nom() + " : " + "défaillant";
                } else {
                    return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenne(etudiant);
                }
            }
        });

        /*Exercice 3 Question 9*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 9 :");
        afficheSiv2(a1,"**TOUS LES ETUDIANTS AVEC MOYENNE INDICATIVE", etudiant -> etudiant.notes().containsKey(m3), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });

        /*Exercice 3 Question 10*/
        System.out.println("\n-------------------------------");
        System.out.println("Exercice 3 \nQuestion 10 :");
        afficheSiv2(a1,"**TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralisee(Main::moyenneIndicative), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });

        e2.noter(m1, 20.0);
        e2.noter(m3, 20.0);

        System.out.println("\nExercice 3 \nQuestion 10 (Apres changement des notes 14 en 20) :");
        afficheSiv2(a1,"**TOUS LES ETUDIANTS SOUS LA MOYENNE INDICATIVE", naPasLaMoyenneGeneralisee(Main::moyenneIndicative), new Function<Etudiant, String>() {
            @Override
            public String apply(Etudiant etudiant) {
                return etudiant.prenom() + " " + etudiant.nom() + " : " + moyenneIndicative(etudiant);
            }
        });

    }

    /* Exercice 3 Question 1*/
    private static void afficheSi(Annee annee, String titre, Predicate<Etudiant> predicate) {
        System.out.println(titre);
        for (Etudiant etudiant : annee.etudiants()) {
            if (predicate.test(etudiant)) {
                System.out.println(etudiant);
            }
        }
    }

    private static void afficheSi2(Annee annee,String titre, Predicate<Etudiant> predicate) {
        System.out.println(titre);
        annee.etudiants().forEach(etudiant -> {
            if (predicate.test(etudiant)){
                System.out.println(etudiant);
            }
        });
    }

    /* Exercice 3 Question 2*/
    private static Boolean estDefaillant(Etudiant etudiant) {
        for (UE ue : etudiant.annee().ues()) {
            Set<Matiere> listeDeMatieres = ue.ects().keySet();
            for (Matiere matiere : listeDeMatieres) {
                if (!etudiant.notes().containsKey(matiere)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static final Predicate<Etudiant> aDEF = etudiant -> (estDefaillant(etudiant));

    /* Exercice 3 Question 3*/
    private static final Predicate<Etudiant> aNoteEliminatoire = etudiant -> {
        Collection<Double> list = etudiant.notes().values();
        for (Double l : list)
            if (l < 6) {
                return true;
            }
        return false;
    };

    /* Exercice 3 Question 4*/
    private static double sommeCoef(Etudiant etudiant) {
        double sommeCoef = 0;
        for (UE ue : etudiant.annee().ues()) {
            for (Integer i : ue.ects().values()) {
                sommeCoef += i;
            }
        }
        return sommeCoef;
    }

    private static Integer coefMatiere(Etudiant etudiant, Matiere mat) {
        for (UE ue : etudiant.annee().ues()) {
            for (Map.Entry<Matiere, Integer> couple : ue.ects().entrySet()) {
                if (couple.getKey().equals(mat)) {
                    return couple.getValue();
                }
            }
        }
        return null;
    }

    private static Double matiereNotesCoef(Etudiant etudiant) {
        double somme = 0.0;

        for (Map.Entry<Matiere, Double> entry : etudiant.notes().entrySet()) {
            Integer coef = coefMatiere(etudiant, entry.getKey());
            somme += entry.getValue() * coef;
        }
        return somme;
    }

    private static Double moyenne(Etudiant etudiant) {
        if (estDefaillant(etudiant)) return null;
        Double n = matiereNotesCoef(etudiant);
        return n / sommeCoef(etudiant);
    }

    private static void afficherMoyenne(Annee annee, Predicate<Etudiant> aDef) {
        System.out.println("**MOYENNE DES ETUDIANTS");
        for (Etudiant etudiant : annee.etudiants()) {
            if (aDef.test(etudiant)) System.out.println(etudiant.nom() + " a une moyenne null");
            else System.out.println(etudiant.nom() + " a " + moyenne(etudiant) + " de moyenne");
        }
    }

    /* Exercice 3 Question 5*/
    private static final Predicate<Etudiant> naPasLaMoyennev1 = etudiant -> moyenne(etudiant) < 10;

    /* Exercice 3 Question 6*/
    private static final Predicate<Etudiant> naPasLaMoyennev2 = aDEF.or(naPasLaMoyennev1);

    /* Exercice 3 Question 7*/
    private static final Predicate<Etudiant> session2v1 = aDEF.or(naPasLaMoyennev1).or(aNoteEliminatoire);

    /* Exercice 3 Question 8*/
    private static void afficheSiv2(Annee annee, String titre, Predicate<Etudiant> predicate, Function<Etudiant, String> representation) {
        System.out.println(titre);
        annee.etudiants().forEach(etudiant -> {
            if (predicate.test(etudiant)) {
                String s = representation.apply(etudiant);
                System.out.println(s);
            }
        });
    }

    /* Exercice 3 Question 9*/
    public static Double moyenneIndicative(Etudiant etudiant) {
        Double n = matiereNotesCoef(etudiant);
        return n / sommeCoef(etudiant);
    }

    /* Exercice 3 Question 10*/
    private static Predicate<Etudiant> naPasLaMoyenneGeneralisee(Function<Etudiant, Double> moyenne) {
        return etudiant -> moyenne.apply(etudiant) < 10;
    }
}
