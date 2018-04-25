package com.github.schmittjoaopedro;

import org.junit.Assert;
import org.junit.Test;

import java.math.BigInteger;

public class AppTest {

    @Test
    public void smallestKeyTest() {
        String msg = "Universidade do Estado de Santa Catarina UDESC";
        RSA rsa = new RSA(16);
        rsa.generateKeys();
        String encrypted = rsa.getPublicKey().encrypt(msg);
        String decrypted = rsa.getPrivateKey().decrypt(encrypted);
        System.out.println(msg);
        System.out.println(encrypted);
        System.out.println(decrypted);
        Assert.assertNotEquals(msg, encrypted);
        Assert.assertEquals(msg, decrypted);
    }

    @Test
    public void mediumKeyTest() {
        String msg = "Universidade do Estado de Santa Catarina UDESC";
        RSA rsa = new RSA();
        rsa.generateKeys();
        String encrypted = rsa.getPublicKey().encrypt(msg);
        String decrypted = rsa.getPrivateKey().decrypt(encrypted);
        System.out.println(msg);
        System.out.println(encrypted);
        System.out.println(decrypted);
        Assert.assertNotEquals(msg, encrypted);
        Assert.assertEquals(msg, decrypted);
    }

    @Test
    public void bigKeyTest() {
        String msg = "Universidade do Estado de Santa Catarina UDESC";
        RSA rsa = new RSA(2048);
        rsa.generateKeys();
        String encrypted = rsa.getPublicKey().encrypt(msg);
        String decrypted = rsa.getPrivateKey().decrypt(encrypted);
        System.out.println(msg);
        System.out.println(encrypted);
        System.out.println(decrypted);
        Assert.assertNotEquals(msg, encrypted);
        Assert.assertEquals(msg, decrypted);
    }

    @Test
    public void appTestError() {
        String msg = "Universidade do Estado de Santa Catarina UDESC";
        RSA rsa = new RSA(16);
        rsa.generateKeys();
        rsa.getPublicKey().keySize = 16;
        rsa.getPublicKey().e = new BigInteger("257");
        rsa.getPublicKey().n = new BigInteger("51983");
        rsa.getPrivateKey().keySize = 16;
        rsa.getPrivateKey().d = new BigInteger("401");
        rsa.getPrivateKey().n = new BigInteger("51983");
        String encrypted = rsa.getPublicKey().encrypt(msg);
        String decrypted = rsa.getPrivateKey().decrypt(encrypted);
        String bruteForce = BruteForce.solve(rsa.getPublicKey(), encrypted);
        System.out.println(msg);
        System.out.println(encrypted);
        System.out.println(decrypted);
        Assert.assertNotEquals(msg, encrypted);
        Assert.assertEquals(msg, decrypted);
        Assert.assertEquals(msg, bruteForce);
    }

}
