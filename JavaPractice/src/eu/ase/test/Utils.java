package eu.ase.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

/*
 * Create the class Utils.
 * 
 * The class Utils has the following private fields:
 * vector: Object[] for the references to the Juice objects
 * 
 * Additionally, there the following private fields used for the intermediary results:
 *  listObjects of type List<Juice>;
 *  threadsArrayWorkerTasks of type MyRunnable[];
 * 
 * The class has no constructor - besides the default constructor (without parameters) 
 * automatically added by Java
 * 
 * Implement proper get/set method(s) for all fields:
 * (listObjects, threadsArrayWorkerTasks)
 * The set method for vector field (public void setVector(Object[] data) throws Exception)
 * is setting the "vector" field with a deep clone of "data" by applying clone method
 * for each element of the "data" array (and each element from "data" is casted to Juice class).
 * 
 * Create method displayVector() -> void which put on the screen the content of the matrix field
 * 
 * Create public methods:
 *  writeBinary(String file) which returns void and serialize the vector field into a file; 
 *  hint: 
 *  save the number of elements as first integer in file;
 *  each object from the "vector" is from class Juice
 *  
 *  readBinary(String file) which returns void and restore/de-serialize the objects into "vector" field from a file; 
 *  hint: 
 *  read the number of elements as first integer from file;
 *  each object from the "vector" is from class Juice
 *  
 *  
 *  Implement the public method sortPlusFilterWithLamdaPredicate() 
 *  which returns List<Juice>
 *  which transform the vector into an ArrayList<Juice> and then, 
 *  sort the juices and filter them by using lambda function as predicate within filter function (id > 10)
 *  and returns the new sorted list by using functional processing streams.
 *  This method must set the this.listObjects field;
 *  
 *
 *  Implement public method calculateSumMultiThreading() which use multi-threading / framework Executor-Service 
 *  for creating worker tasks using the field: this.threadsArrayWorkerTasks from class MyRunnable (which implements Runnable) 
 *  and calculates the ml sum of the "vector" elements by using
 *  the number of worker tasks equals to 2.
 *  This method uses the field this.threadsArrayWorkerTasks
 */

public class Utils {
    private Object[] vector;
    private List<Juice> listObjects;
    private MyRunnable[] threadsArrayWorkerTasks;

    public Object[] getVector() {
        return vector;
    }

    public void setVector(Object[] data) {
        this.vector = new Object[data.length];
        for (int i = 0; i < this.vector.length; i++) {
            this.vector[i] = (Juice) data[i];
        }
    }

    public List<Juice> getListObjects() {
        return listObjects;
    }

    public void setListObjects(List<Juice> listObjects) {
        this.listObjects = listObjects;
    }

    public MyRunnable[] getThreadsArrayWorkerTasks() {
        return threadsArrayWorkerTasks;
    }

    public void setThreadsArrayWorkerTasks(MyRunnable[] threadsArrayWorkerTasks) {
        this.threadsArrayWorkerTasks = threadsArrayWorkerTasks;
    }

    public void displayVector() {
        for (int i = 0; i < this.vector.length; i++) {
            System.out.println(this.vector[i]);
        }
    }

    public void writeBinary(String file) {
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeInt(this.vector.length);
            for (int i = 0; i < this.vector.length; i++) {
                objectOutputStream.writeObject(this.vector[i]);
            }
            objectOutputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void readBinary(String file) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file));
            this.vector = new Object[objectInputStream.readInt()];
            for (int i = 0; i < this.vector.length; i++) {
                this.vector[i] = (Juice) objectInputStream.readObject();
            }
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<Juice> sortPlusFilterWithLamdaPredicate() {
        this.listObjects = new ArrayList<>();
        for (int i = 0; i < this.vector.length; i++) {
            this.listObjects.add((Juice) this.vector[i]);
        }
        this.listObjects.stream().filter(obj -> obj.getId() > 10).toList();

        return this.listObjects;
    }
    /*
     * * Implement public method calculateSumMultiThreading() which use
     * multi-threading / framework Executor-Service
     * for creating worker tasks using the field: this.threadsArrayWorkerTasks from
     * class MyRunnable (which implements Runnable)
     * and calculates the ml sum of the "vector" elements by using
     * the number of worker tasks equals to 2.
     * This method uses the field this.threadsArrayWorkerTasks
     */

    public float calculateSumMultiThreading() {
        final int NO_OF_THREADS = 2;
        float sum = 0;
        this.threadsArrayWorkerTasks = new MyRunnable[NO_OF_THREADS];
        Thread[] vectorThreads = new Thread[NO_OF_THREADS];

        for (int i = 0; i < NO_OF_THREADS; i++) {
            int startIdx = i * this.vector.length / NO_OF_THREADS;
            int endIdx = (i + 1) * this.vector.length / NO_OF_THREADS;
            threadsArrayWorkerTasks[i] = new MyRunnable(this.vector, startIdx, endIdx);
            vectorThreads[i] = new Thread(threadsArrayWorkerTasks[i]);
        }

        for (int i = 0; i < NO_OF_THREADS; i++) {
            vectorThreads[i].start();
        }

        for (int i = 0; i < NO_OF_THREADS; i++) {
            try {
                vectorThreads[i].join();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        for (int i = 0; i < NO_OF_THREADS; i++) {
            sum += this.threadsArrayWorkerTasks[i].getSumMl();
        }

        return sum;
    }

}
