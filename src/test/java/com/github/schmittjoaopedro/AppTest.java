package com.github.schmittjoaopedro;

import org.junit.Assert;
import org.junit.Test;

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
        int keySize = 16;
        RSA rsa = new RSA(keySize);
        rsa.generateKeys();
        String encrypted = rsa.getPublicKey().encrypt(msg);
        String decrypted = rsa.getPrivateKey().decrypt(encrypted);

        System.out.println(msg);
        System.out.println(encrypted);
        System.out.println(decrypted);
        Assert.assertNotEquals(msg, encrypted);
        Assert.assertEquals(msg, decrypted);

        long time = System.currentTimeMillis();
        String bruteForce = PollardForce.solve(rsa.getPublicKey(), encrypted);
        Assert.assertEquals(msg, bruteForce);
        System.out.println("PollardForce = " + (System.currentTimeMillis() - time));

        time = System.currentTimeMillis();
        bruteForce = BruteForce.solve(rsa.getPublicKey(), encrypted);
        Assert.assertEquals(msg, bruteForce);
        System.out.println("BruteForce = " + (System.currentTimeMillis() - time));
    }

}
