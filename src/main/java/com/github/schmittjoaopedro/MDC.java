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
     *
     * Para a = 348 e b = 156
     *
     *  q   |  2  |  4  |  3  |
     *  348 | 156 |  36 |  12 |
     *  r   |  36 |  12 |  0  |
     */
    public BigInteger getMDC() { // O(n^3) [6]
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

    /**
     * Modular inverse [5].
     */
    public BigInteger getModInv() {
        if(a.compareTo(b) < 0) {
            BigInteger aux = b;
            b = a;
            a = aux;
        }
        BigInteger a = this.a;
        BigInteger b = this.b;
        BigInteger p2 = new BigInteger("0");
        BigInteger p1 = new BigInteger("1");
        BigInteger q;
        BigInteger p;
        while (b.compareTo(new BigInteger("0")) > 0) { // O(n^3) [6]
            BigInteger r = a.mod(b);
            if(r.compareTo(new BigInteger("0")) > 0) {
                q = a.divide(b);
                p = p2.subtract(p1.multiply(q)).mod(this.a);
                p2 = p1;
                p1 = p;
            }
            a = b;
            b = r;
        }
        return p1;
    }

    /**
     * Calculate the inverse factors "x" and "y" of "a" and "b", given: ax + by = gcd(a,b). [6]
     */
    public void calculateEuclidean() {
        if(a.compareTo(b) < 0) {
            BigInteger aux = a;
            a = b;
            b = aux;
        }
        this.euclidExtension(this);
    }

    private void euclidExtension(MDC mdc) {
        if (mdc.b.compareTo(new BigInteger("0")) == 0) {
            mdc.x = new BigInteger("1");
            mdc.y = new BigInteger("0");
            mdc.d = mdc.a;
        } else {
            MDC call = new MDC();
            call.a = mdc.b;
            call.b = mdc.a.mod(mdc.b);
            euclidExtension(call);
            mdc.x = new BigInteger(call.y.toString());
            BigInteger abFloor = mdc.a.divide(mdc.b);
            mdc.y = new BigInteger(call.x.subtract(abFloor.multiply(call.y)).toString());
            mdc.d = call.d;
        }
    }

}
