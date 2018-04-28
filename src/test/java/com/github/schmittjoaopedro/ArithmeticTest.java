package com.github.schmittjoaopedro;

import org.junit.Test;

import java.math.BigInteger;

public class ArithmeticTest {

    public static BigInteger TWO = new BigInteger("2");

    @Test
    public void testModExp() {
        BigInteger x = new BigInteger("123456");
        BigInteger y = new BigInteger("567890");
        BigInteger N = new BigInteger("192834");
        long time = System.currentTimeMillis();
        System.out.println(x.modPow(y, N));
        System.out.println("x.modPow(y, N) = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        System.out.println(x.pow(y.intValue()).mod(N));
        System.out.println("x.pow(y).mod(N) = " + (System.currentTimeMillis() - time));
        time = System.currentTimeMillis();
        System.out.println(modExp(x, y, N));
        System.out.println("modExp(x, y, N) = " + (System.currentTimeMillis() - time));
    }

    public BigInteger modExp(BigInteger x, BigInteger y, BigInteger N) {
        if(y.compareTo(BigInteger.ZERO) == 0) {
            return BigInteger.ONE;
        }
        BigInteger z = modExp(x, y.divide(TWO), N);
        BigInteger res = null;
        if(y.mod(TWO).compareTo(BigInteger.ZERO) == 0) {
            res = z.pow(2).mod(N);
        } else {
            res = x.multiply(z.pow(2)).mod(N);
        }
        //System.out.println(res);
        return res;
    }

}
