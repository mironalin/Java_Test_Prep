package eu.ase.test;

// This is the Java code playground

public class ProgMain {

	public static void main(String[] args) {
		Utils utils = new Utils();
		// utils.setVector(new Juice[] {});
		// System.out.println(utils.calculateSumMultiThreading());

		utils.setVector(new Juice[] { new Juice(1, null, "test1", 100), new Juice(2, null, "test2", 100),
				new Juice(3, null, "test3", 100) });
		System.out.println(utils.calculateSumMultiThreading());
	}

}
