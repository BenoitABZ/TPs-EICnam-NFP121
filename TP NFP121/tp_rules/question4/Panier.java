package question4;

import java.util.ArrayList;
import java.util.List;

public class Panier {

	private int poidMax;

	private List<Produit> produits;

	public Panier() {

		produits = new ArrayList<>();
	}

	public List<Produit> getProduits() {
		return produits;
	}

	public void setProduits(List<Produit> produits) {
		this.produits = produits;
	}

	public int getPoidMax() {
		return poidMax;
	}

	public void setPoidMax(int poidMax) {
		this.poidMax = poidMax;
	}

	public void setAjouterProduit(Produit produit) {
		produits.add(produit);
	}

}
