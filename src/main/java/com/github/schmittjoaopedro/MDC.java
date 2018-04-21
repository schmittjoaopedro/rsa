package com.github.schmittjoaopedro;

import java.math.BigInteger;

/**
 * ax + by = gcd(a, b)
 * d = gcd(a, b)
 */
public class MDC {

    public BigInteger a;

    public BigInteger b;

    public BigInteger d;

    public BigInteger x;

    public BigInteger y;

    /**
     * Algorithm: (Euclidean algorithm) Computing the greatest common divisor of two integers. [4]
     */
    public BigInteger getMDC() {
        BigInteger a = this.a;
        BigInteger b = this.b;
        if(a.compareTo(b) < 0) {
            BigInteger aux = b;
            b = a;
            a = aux;
        }
        while (b.compareTo(new BigInteger("0")) > 0) {
            BigInteger r = a.mod(b);
            a = b;
            b = r;
        }
        return a;
    }

    public void calculateEuclidean() {
        if(a.compareTo(b) < 0) {
            BigInteger aux = a;
            a = b;
            b = aux;
        }
        if(b.compareTo(new BigInteger("0")) == 0) {
            d = a;
            x = new BigInteger("1");
            y = new BigInteger("0");
        } else {
            BigInteger a = this.a;
            BigInteger b = this.b;
            BigInteger x2 = new BigInteger("1");
            BigInteger x1 = new BigInteger("0");
            BigInteger y2 = new BigInteger("0");
            BigInteger y1 = new BigInteger("1");
            while(b.compareTo(new BigInteger("0")) > 0) {
                // q = floor(a/b), r = a - q*b, x = x2 - q*x1, y = y2 - q*y1
                BigInteger q = a.divide(b);
                BigInteger r = a.subtract(q.multiply(b));
                BigInteger x = x2.subtract(q.multiply(x1));
                BigInteger y = y2.subtract(q.multiply(y1));
                //a = b, b = r, x2 = x1, x1 = x, y2 = y1, y1 = y
                a = b;
                b = r;
                x2 = x1;
                x1 = x;
                y2 = y1;
                y1 = y;
            }
            d = a;
            x = x2;
            y = y2;
        }
    }

    public BigInteger getModInv() {
        BigInteger inv, t1, t3, q;
        BigInteger u1 = new BigInteger("1");
        BigInteger u3 = a;
        BigInteger v1 = new BigInteger("0");
        BigInteger v3 = b;
        BigInteger iter = new BigInteger("1");
        while(v3.compareTo(new BigInteger("0")) != 0) {
            q = u3.divide(v3);
            t3 = u3.mod(v3);
            t1 = u1.add(q.multiply(v1));
            u1 = v1;
            v1 = t1;
            u3 = v3;
            v3 = t3;
            iter = iter.multiply(new BigInteger("-1"));
        }
        if(u3.compareTo(new BigInteger("1")) != 0) { //u3 != 1
            throw new RuntimeException("Error: No inverse exists");
        }
        if(iter.compareTo(new BigInteger("0")) < 0) {
            inv = b.subtract(u1);
        } else {
            inv = u1;
        }
        return inv;
    }

}
