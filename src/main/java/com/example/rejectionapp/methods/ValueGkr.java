package com.example.rejectionapp.methods;
import java.util.HashMap;

public class ValueGkr {
    private HashMap<Integer, Double> gKr = new HashMap<>();

    public void setGkr() {
        gKr.put(3, 1.155 );
        gKr.put(4, 1.481 );
        gKr.put(5, 1.715 );
        gKr.put(6, 1.887 );
        gKr.put(7, 2.020 );
        gKr.put(8, 2.126 );
        gKr.put(9, 2.215 );
        gKr.put(10, 2.290 );
        gKr.put(11, 2.355 );
        gKr.put(12, 2.412 );
        gKr.put(13, 2.462 );
        gKr.put(14, 2.507 );
        gKr.put(15, 2.549 );
        gKr.put(16, 2.585 );
        gKr.put(17, 2.620 );
        gKr.put(18, 2.651 );
        gKr.put(19, 2.681 );
        gKr.put(20, 2.709 );
        gKr.put(21, 2.733 );
        gKr.put(22, 2.758 );
        gKr.put(23, 2.781 );
        gKr.put(24, 2.802 );
        gKr.put(25, 2.822 );
        gKr.put(26, 2.841 );
        gKr.put(27, 2.859 );
        gKr.put(28, 2.876 );
        gKr.put(29, 2.893 );
        gKr.put(30, 2.908 );
        gKr.put(31, 2.924 );
        gKr.put(32, 2.938 );
        gKr.put(33, 2.952 );
        gKr.put(34, 2.965 );
        gKr.put(36, 2.991 );
        gKr.put(38, 3.014 );
        gKr.put(40, 3.036 );
    }

    public double getValue(int key) {
        return gKr.get(key);
    }
}
