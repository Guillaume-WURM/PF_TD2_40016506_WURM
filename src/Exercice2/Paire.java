package Exercice2;

import java.util.function.Predicate;

public class Paire <T,P> {
    public T taille;
    public P poids;

    public Paire(T taille, P poids) {
        this.taille = taille;
        this.poids = poids;
    }

    public T getTaille() {
        return taille;
    }

    public void setTaille(T taille) {
        this.taille = taille;
    }

    public P getPoids() {
        return poids;
    }

    public void setPoids(P poids) {
        this.poids = poids;
    }

    @Override public String toString() {
        return String.format("(%s,%s)",taille.toString(),poids.toString());
    }

}
