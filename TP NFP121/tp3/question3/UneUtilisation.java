package question3;

import question1.PolygoneRegulier;

public class UneUtilisation {

	public static void main(String[] args) throws Exception {

		PileI<PolygoneRegulier> p1 = new Pile2<PolygoneRegulier>(10);
		PileI<PileI<PolygoneRegulier>> p2 = new Pile2<PileI<PolygoneRegulier>>(10);
		PileI<PileI<PileI<PolygoneRegulier>>> p3 = new Pile2<PileI<PileI<PolygoneRegulier>>>(10);
		PileI<PileI<PileI<PileI<PolygoneRegulier>>>> p4 = new Pile2<PileI<PileI<PileI<PolygoneRegulier>>>>(10);
		PileI<PileI<PileI<PileI<PileI<PolygoneRegulier>>>>> p5 = new Pile2<PileI<PileI<PileI<PileI<PolygoneRegulier>>>>>(
				10);
		PileI<Integer> p6 = new Pile2<Integer>(10);
		PileI<String> p7 = new Pile2<String>(10);
		PileI<Object> p8 = new Pile2<Object>(10);

		// p1 est ici une pile de polygones r�guliers PolygoneRegulier.java
		p1.empiler(new PolygoneRegulier(4, 100));
		p1.empiler(new PolygoneRegulier(5, 100));

		System.out.println(" la pile p1 = " + p1);

		p2.empiler(p1);
		System.out.println(" la pile p2 = " + p2);

		p3.empiler(p2);
		System.out.println(" la pile p3 = " + p3);

		p4.empiler(p3);
		System.out.println(" la pile p4 = " + p4);

		p5.empiler(p4);
		System.out.println(" la pile p5 = " + p5);

		p5.depiler();
		System.out.println(" la pile p5 = " + p5);

		p4.depiler();
		System.out.println(" la pile p4 = " + p4);

		p3.depiler();
		System.out.println(" la pile p3 = " + p3);

		p2.depiler();
		System.out.println(" la pile p2 = " + p2);

		p1.depiler();
		System.out.println(" la pile p1 = " + p1);

		try {

			p8.empiler(new PolygoneRegulier(5, 100)); // d�sormais une erreur de
			// compilation
			// ....
			String s = (String) p8.depiler(); // d�sormais une erreur de
			// compilation
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}