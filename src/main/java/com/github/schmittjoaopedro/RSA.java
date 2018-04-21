package com.github.schmittjoaopedro;

import ch.obermuhlner.math.big.BigDecimalMath;
import org.apache.commons.math3.util.BigReal;
import org.apache.commons.math3.util.FastMath;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.MathContext;
import java.util.Random;

public class RSA {

    /**
     * Um tamanho típico para n é de 1024-bits, ou 309 dígitos decimais. Ou seja, n é menor do que 2^1024. [1]
     */
    private int keySize = 1024;

    private BigInteger p = null;

    private BigInteger q = null;

    private BigInteger n = null;

    private BigInteger phi = null;

    private PublicKey publicKey;

    private PrivateKey privateKey;

    public void generateKeys() {
        generatePrimeNumbers();
        n = p.multiply(q);
        phi = p.subtract(new BigInteger("1")).multiply(q.subtract(new BigInteger("1")));
        createPublicKey();
        createPrivateKey();
    }

    private void generatePrimeNumbers() {
        int half = keySize / 2;
        while(p == null) {
            BigInteger temp = getRandomNumberBitLength(half);
            if(isPrime(temp)) {
                p = temp;
            }
        }
        while(q == null) {
            BigInteger temp = getRandomNumberBitLength(half);
            if(!temp.equals(p) && isPrime(temp)) {
                q = temp;
            }
        }
    }

    /**
     * Na prática, as escolhas comuns de e são 3, 5, 17, 257 e 65537 (2^16 + 1). Esses números particulares são escolhidos
     * porque eles são primos e fazem a operação de exponenciação modular ser rápida, tendo somente dois bits de valor 1. [3]
     *
     * *Esses cinco números são os cinco primeiros números de Fermat, referidos como F0 até F4, onde Fx = 2^(2^x) + 1. Entretanto
     *  somente os cinco primeiros números são primos, o número F5 já não é primo: F5 = 4294967297 = 641 × 6700417.
     *
     * Se "e" for um número ímpar primo então pode-se fazer um test menos caro "(p mod e) != 1" ao invés de "gcd(p-1, e) = 1".
     * Isso porque se "e" for primo então "gcd(p-1, e) > 1" se e somente se "p-1" for múltiplo de "e". Que é, se "p-1 = 0 mod e"
     * ou "p = 1 mod e". Consequentemente, "gdc(p-1, e) = 1" se e somente se "1 != p mod e".
     */
    private void createPublicKey() {
        BigInteger[] tries = new BigInteger[5];
        tries[0] = new BigInteger("65537");
        tries[1] = new BigInteger("257");
        tries[2] = new BigInteger("17");
        tries[3] = new BigInteger("5");
        tries[4] = new BigInteger("3");
        publicKey = new PublicKey();
        publicKey.n = n;
        for(BigInteger e : tries) {
            MDC mdc = new MDC();
            mdc.a = e;
            mdc.b = phi;
            if(mdc.getMDC().equals(new BigInteger("1"))) {
                publicKey.e = e;
                break;
            }
        }
    }

    private void createPrivateKey() {
        privateKey = new PrivateKey();
        privateKey.n = n;
        MDC mdc = new MDC();
        mdc.a = publicKey.e;
        mdc.b = phi;
        privateKey.d = mdc.getModInv();
    }

    /**
     * Big integer generation. [2]
     */
    private BigInteger getRandomNumberBitLength(int bitLength) {
        Random random = new Random();
        BigInteger n = new BigInteger("2").pow(bitLength);
        BigInteger result = new BigInteger(bitLength, random);
        while(result.compareTo(n) >= 0) {
            result = new BigInteger(bitLength, random);
        }
        return result;
    }

    public PublicKey getPublicKey() {
        return this.publicKey;
    }

    public PrivateKey getPrivateKey() {
        return this.privateKey;
    }

    /**
     * REVER
     */

    private boolean isPrime(BigInteger bigInteger) {
        return bigInteger.isProbablePrime(100);
    }

}
