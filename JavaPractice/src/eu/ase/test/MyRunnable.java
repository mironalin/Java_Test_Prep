package eu.ase.test;

/* 
 *  Implement the public class MyRunnable which implements Runnable interface
 *  The class contains the following private fields:
 *  vector of type Juice[] // classic array of juices
 *  sumMl of type float // the sum of the ml quantities
 *  startPos of type int //the start position in the vector
 *  endPos of type int //the end position in the vector
 *  
 *  Implement the public constructor with 3 parameters:
 *  "data" of type vector of Objects, "startPos" of type int, "endPos" of type int
 *  each element from the array of the class is reference to an object from the data array
 *  
 *  Override the run() method to be called in multi-threading environment for calculating the sum of ml fields from the "vector" array
 *  between "startPos" and "endPos" positions and provide public method getSumMl() which is returning the float "sumMl"
 */

public class MyRunnable implements Runnable {

    private Juice[] juices;
    private float sumMl;
    private int startPos;
    private int endPos;

    public MyRunnable(Object[] data, int startPos, int endPos) {
        this.juices = new Juice[data.length];
        for (int i = 0; i < juices.length; i++) {
            this.juices[i] = (Juice) data[i];
        }

        this.sumMl = 0;
        this.startPos = startPos;
        this.endPos = endPos;
    }

    @Override
    public void run() {
        this.sumMl = 0;
        for (int i = this.startPos; i < this.endPos; i++) {
            this.sumMl += this.juices[i].getMl();
        }
    }

    public float getSumMl() {
        return this.sumMl;
    }

}
