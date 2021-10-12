package exemple_alcool;



public class ClientDuBar {
    private String nom;
    private int age;
    private Bar bar;
    private String boisson;

    public ClientDuBar(String nom, int age, Bar bar, String boisson) {
        super();
        this.nom = nom;
        this.age = age;
        this.bar = bar;
        this.boisson = boisson;
    }

    public ClientDuBar() {}

    
    public int getAge() {return age;}    
    public String getNom() {return nom;}
    public Bar getBar() {return bar;}
    public String getBoisson() {return boisson;}
    
    public void setAge(int age) {
        this.age = age;
    }
    public void setNom(String nom) {
        this.nom = nom;
    }
    public void setBar(Bar bar){this.bar = bar;}
    public void setBoisson(String boisson){this.boisson = boisson;}

    @Override
    public String toString() {
        return "ClientDuBar [nom=" + nom + ", age=" + age + ", bar=" +bar+"]";
    }

}
