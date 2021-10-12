package question4;

public class AuPoidMax implements SpecificationI<Panier> {

	@Override
	public boolean isSatisfiedBy(Panier p) {

		int poidTotal = 0;

		for (Produit produit : p.getProduits()) {

			poidTotal += produit.getPoid();

		}

		if (poidTotal > p.getPoidMax()) {
			return true;
		} else {

			return false;
		}
	}

}
