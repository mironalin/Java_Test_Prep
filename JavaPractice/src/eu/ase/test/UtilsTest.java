// package eu.ase.test;

// import org.junit.jupiter.api.BeforeEach;
// import org.junit.jupiter.api.Test;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.assertThrows;

// public class UtilsTest {
// private Utils utils;

// @BeforeEach
// public void setUp() {
// utils = new Utils();
// }

// @Test
// public void testCalculateSumMultiThreadingWithEmptyVector() {
// utils.setVector(new Juice[] {});
// assertEquals(0, utils.calculateSumMultiThreading());
// }

// @Test
// public void testCalculateSumMultiThreadingWithSingleElement() {
// utils.setVector(new Juice[] { new Juice(1, 100) });
// assertEquals(100, utils.calculateSumMultiThreading());
// }

// @Test
// public void testCalculateSumMultiThreadingWithMultipleElements() {
// utils.setVector(new Juice[] { new Juice(1, 100), new Juice(2, 200), new
// Juice(3, 300) });
// assertEquals(600, utils.calculateSumMultiThreading());
// }

// @Test
// public void testCalculateSumMultiThreadingWithNullVector() {
// assertThrows(NullPointerException.class, () ->
// utils.calculateSumMultiThreading());
// }
// }