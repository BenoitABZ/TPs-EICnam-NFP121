package question3;

//Implémentation du pattern Factory -- Ici l'interface.

//Création d'une fabrique d'éléments de type Set<T>.

//L'objectif est que l'implémentation concrète ne soit décidée par le client qu'à l'éxécution.

//Le client ne manipule qu'un AbstractProduct (Set) et un AbstractFabrique (Fabrique<Set>).
public interface Factory<T> {
    public T create();
}
