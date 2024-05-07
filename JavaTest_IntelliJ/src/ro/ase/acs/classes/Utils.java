package ro.ase.acs.classes;

import java.util.List;
import java.util.concurrent.Callable;
import java.util.stream.Stream;

import ro.ase.acs.models.TrainTicket;

public class Utils {
	public static Integer[] array;

	public static Callable<Double> lambdaOperation = () -> {
		double product = 1;
		for (int i = 0; i < array.length; i++) {
			product *= array[i];
		}
		return product;
	};

	public static Stream<TrainTicket> getLongDistanceTrains(List<TrainTicket> trains, String departure) {
		return trains.stream().distinct()
				.filter(train -> train.getDeparture().equals(departure) && train.getDistance() > 100);
	}
}
