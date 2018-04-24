package com.github.schmittjoaopedro;

import ch.obermuhlner.math.big.BigDecimalMath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;

public class BruteForce {

    public static String broke(RSA rsa, String crypt) {
        BigInteger p = null;
        BigInteger q = null;
        BigInteger n = rsa.getPublicKey().n;
        BigInteger max = BigDecimalMath.sqrt(new BigDecimal(n), new MathContext(0)).toBigInteger();
        BigInteger i = new BigInteger("3");
        for(; i.compareTo(max) < 0; i = i.add(new BigInteger("2"))) {
            if(n.mod(i).equals(new BigInteger("0"))) {
                p = n.divide(i);
                q = i;
                break;
            }
        }
        MDC mdc = new MDC();
        mdc.a = rsa.getPublicKey().e;
        mdc.b = getTotientEuler(p, q);
        PrivateKey privateKey = new PrivateKey();
        privateKey.n = rsa.getPublicKey().n;
        privateKey.d = mdc.getModInv();
        return privateKey.decrypt(crypt);
    }

    private static BigInteger getTotientEuler(BigInteger p, BigInteger q) {
        return p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
    }

}
