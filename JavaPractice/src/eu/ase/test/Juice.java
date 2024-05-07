package eu.ase.test;

import java.util.Date;

/*
 * Create the class Juice which extends Product implements Cloneable and Comparable<Juice> interfaces. 
 * 
 * Besides the fields and methods inherited from the abstract class Product, the class Juice has the following private fields:
 * ml : float representing the ml quantity of the Juice
 * serialVersionUID : static final long
 * 
 * Create the constructor method with 4 parameters: 
 * "id" of type int, "expDate" of type Date, "title" of type String, "ml" of type float
 * The constructor should throw an Exception if the "ml" or the "id" are less than 0
 * 
 * Implement proper get/set method(s) and override equals method:
 * public method equals(Object o) which returns boolean
 * The setMl() method must throw an exception if the ml are less than 0
 * 
 * Implement consistently the clone method for creating deep copy of the current object:
 * public clone() which return Object and may throw CloneNotSupportedException
 * 
 * Override the methods from Comparable (compareTo() -> returns int and it compares Juices in terms of ml)
 * 
 * Override method getAbstractProductInfo() which returns a String which represents the concatenation of the inherited value
 * for the "id" and the "title" field value
 * 
 * Override toString() -> String method in terms of returning the String concatenation of the fields content
 */

public class Juice extends Product implements Cloneable, Comparable<Juice> {
    private float ml;
    private static final long serialVersionUID = 42L;

    public Juice(int id, Date expDate, String title, float ml) throws IllegalArgumentException {
        super(id, expDate, title);
        if (ml < 0) {
            throw new IllegalArgumentException("ID can not be less than 0.");
        }
        this.ml = ml;
    }

    public float getMl() {
        return ml;
    }

    public void setMl(float ml) {
        if (ml < 0) {
            throw new IllegalArgumentException("ID can not be less than 0.");
        }
        this.ml = ml;
    }

    public static long getSerialversionuid() {
        return serialVersionUID;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Juice other = (Juice) obj;
        if (Float.floatToIntBits(ml) != Float.floatToIntBits(other.ml))
            return false;
        return true;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        Juice clone = (Juice) super.clone();
        clone.ml = this.ml;
        return clone();
    }

    @Override
    public int compareTo(Juice o) {
        return Float.compare(this.ml, o.ml);
    }

    @Override
    public String getAbstractProductInfo() {
        return this.getId() + this.getTitle();
    }

    @Override
    public String toString() {
        return "> " + this.getId() + this.getExpDate() + this.getTitle() + this.getMl();
    }
}
