package GBJava;

import java.util.Arrays;

public class Main {
    static final int size = 10000000;
    static final int h = size / 2;
    static float[] arr = new float[size];
    static float[] newArr1 = new float[size/2];
    static float[] newArr2 = new float[size/2];


    public static void main(String[] args) {
	    speedCount();
        speedCount2();
    }

    public static void speedCount () {
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        // System.out.println(Arrays.toString(arr));
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = (float)(arr[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
        }
        long b = System.currentTimeMillis();
        System.out.println("First test took: " + (b-a) + "ms");
      //  System.out.println(Arrays.toString(arr));
    }

    public synchronized static void speedCount2 () {
        for (int i = 0; i <arr.length ; i++) {
            arr[i] = 1;
        }
        long a = System.currentTimeMillis();
        System.arraycopy(arr,0,newArr1,0,newArr1.length );
        System.arraycopy(arr,newArr1.length,newArr2,0,newArr2.length );
//        System.out.println("NewArr 1: " + Arrays.toString(newArr1));
//        System.out.println("NewArr 2: " + Arrays.toString(newArr2));
        Thread t1 = new Thread();
        Thread t2 = new Thread();
        t1.start(); {
            for (int i = 0; i <newArr1.length ; i++) {
                newArr1[i] = (float)(newArr1[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
        t2.start(); {
            for (int i = 0; i <newArr2.length ; i++) {
                newArr2[i] = (float)(newArr2[i] * Math.sin(0.2f + i / 5) * Math.cos(0.2f + i / 5) * Math.cos(0.4f + i / 2));
            }
        }
        System.arraycopy(newArr1, 0, arr,0, newArr1.length);
        System.arraycopy(newArr2,0,arr,newArr1.length,newArr2.length);
        //System.out.println(Arrays.toString(arr));
        long b = System.currentTimeMillis();
        System.out.println("Second test took: " + (b-a) + "ms");
    }
}

