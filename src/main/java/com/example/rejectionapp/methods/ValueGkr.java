package com.example.rejectionapp.methods;
import java.util.HashMap;

public class ValueGkr {
    private HashMap<Integer, Double> gKr = new HashMap<>();

    public void setGkr() {
        gKr.put(3, 1.155 );
        gKr.put(4, 1.496 );
        gKr.put(5, 1.764 );
        gKr.put(6, 1.973 );
        gKr.put(7, 2.139 );
        gKr.put(8, 2.274 );
        gKr.put(9, 2.387 );
        gKr.put(10, 2.482 );
        gKr.put(11, 2.564 );
        gKr.put(12, 2.636 );
        gKr.put(13, 2.699 );
        gKr.put(14, 2.755 );
        gKr.put(15, 2.806 );
        gKr.put(16, 2.852 );
        gKr.put(17, 2.894 );
        gKr.put(18, 2.932 );
        gKr.put(19, 2.968 );
        gKr.put(20, 3.001 );
        gKr.put(21, 3.031 );
        gKr.put(22, 3.060 );
        gKr.put(23, 3.087 );
        gKr.put(24, 3.112 );
        gKr.put(25, 3.135 );
        gKr.put(26, 3.157 );
        gKr.put(27, 3.178 );
        gKr.put(28, 3.199 );
        gKr.put(29, 3.218 );
        gKr.put(30, 3.236 );
        gKr.put(31, 3.253 );
        gKr.put(32, 3.270 );
        gKr.put(33, 3.286 );
        gKr.put(34, 3.301 );
        gKr.put(36, 3.330 );
        gKr.put(38, 3.356 );
        gKr.put(40, 3.381 );
    }

    public double getValue(int key) {
        return gKr.get(key);
    }
}
