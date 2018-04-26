package com.github.schmittjoaopedro;

import java.math.BigInteger;

public class PollardForce {

    public static String solve(PublicKey publicKey, String crypt) {
        BigInteger p = factorize(publicKey.n);
        BigInteger q = publicKey.n.divide(p);
        MDC mdc = new MDC();
        mdc.a = publicKey.e;
        mdc.b = getTotientEuler(p, q);
        PrivateKey privateKey = new PrivateKey();
        privateKey.n = publicKey.n;
        privateKey.d = mdc.getModInv();
        return privateKey.decrypt(crypt);
    }

    private static BigInteger getTotientEuler(BigInteger p, BigInteger q) {
        return p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
    }

    /**
     * Pollards rho factorization [8].
     */
    public static BigInteger factorize(BigInteger n) {
        BigInteger xFixed = new BigInteger("2");
        BigInteger cycleSize = new BigInteger("2");
        BigInteger x = new BigInteger("2");
        BigInteger factor = new BigInteger("1");
        while(factor.compareTo(new BigInteger("1")) == 0) {
            for(BigInteger count = new BigInteger("1");
                count.compareTo(cycleSize) <= 0 && factor.compareTo(new BigInteger("1")) <= 0;
                count = count.add(new BigInteger("1"))) {
                x = (x.multiply(x).add(new BigInteger("1"))).mod(n);
                factor = gcd(x.subtract(xFixed), n);
            }
            cycleSize = cycleSize.multiply(new BigInteger("2"));
            xFixed = x;
        }
        return factor;
    }

    public static BigInteger gcd(BigInteger a, BigInteger b) {
        BigInteger remainder;
        while(b.compareTo(new BigInteger("0")) != 0) {
            remainder = a.mod(b);
            a = b;
            b = remainder;
        }
        return a;
    }

}
