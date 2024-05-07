package ro.ase.acs.classes;

import java.util.List;
import java.util.stream.Stream;

public class Utils {
	public static DataSeriesOperation lambdaOperation = (array) -> {
		Double sum = 0.0;
		for (int i = 0; i < array.length; i++) {
			sum += array[i];
		}

		return sum / array.length;
	};

	public static Stream<String> getCardsByRank(List<String> cards, char rank) {
		return cards.stream().distinct().filter(card -> card.startsWith(String.valueOf(rank))).sorted();
	}
}
