package chapter04.section03;

import java.util.HashMap;
import java.util.Map;

/**
 */
public class CheeseDao {
	private static final Map<Long, Cheese> cheeses = new HashMap<Long, Cheese>();

	static {
		cheeses.put(1L, new Cheese());
		cheeses.put(2L, new Cheese());
		cheeses.put(3L, new Cheese());

		Cheese cheese = cheeses.get(1L);
		cheese.setId(1L);
		cheese.setName("Gouda");
		cheese
				.setDescription("Gouda is a yellowish Dutch cheese named after the city of Gouda. The cheese is made from cow's milk that is cultured and heated until the curd is separate from the whey. About ten percent of the mixture is curds which are pressed into circular moulds for several hours.");

		cheese = cheeses.get(2L);
		cheese.setId(2L);
		cheese.setName("Edam");
		cheese
				.setDescription("Edam (Dutch Edammer) is a Dutch cheese that is traditionally sold as spheres with pale yellow interior and a coat of paraffin. Its Spanish name is queso de bola, literally 'ball cheese'. It is named after the town of Edam in the province of North Holland[1], where the cheese is coated for export and for tourist high season. Edam which has aged for at least 17 weeks is coated with black wax, rather than the usual red or yellow.");

		cheese = cheeses.get(3L);
		cheese.setId(3L);
		cheese.setName("Old Amsterdam");
		cheese
				.setDescription("Old Amsterdam is a Dutch gourmet cheese that is ripened to perfection and regularly checked for flavor. It is a gourmet cheese of exceptionally high and consistent quality, with a buttery mature aged Gouda flavor that cuts with ease.");
	}

	public Cheese getCheese(Long id) {
		return cheeses.get(id);
	}
}
