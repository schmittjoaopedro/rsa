package com.github.schmittjoaopedro;

import java.math.BigInteger;

/**
 * Bibliography:
 * <p>
 * [1] W. Stallings, D. Vieira, A. Barbosa and M. Ferreira, Criptografia e segurança de redes. São Paulo: Pearson Prentice Hall, 2008.
 * [2] H. Java?, "How to generate a random BigInteger value in Java?", Stackoverflow.com, 2018. [Online]. Available: https://stackoverflow.com/questions/2290057/how-to-generate-a-random-biginteger-value-in-java. [Accessed: 20- Apr- 2018].
 * [3] w. David Ireland, "RSA Algorithm", Di-mgt.com.au, 2018. [Online]. Available: https://www.di-mgt.com.au/rsa_alg.html. [Accessed: 21- Apr- 2018].
 * [4] w. David Ireland, "The Euclidean Algorithm and the Extended Euclidean Algorithm", Di-mgt.com.au, 2018. [Online]. Available: https://www.di-mgt.com.au/euclidean.html. [Accessed: 21- Apr- 2018].
 * [5] "Extended Euclidean Algorithm", Www-math.ucdenver.edu, 2018. [Online]. Available: http://www-math.ucdenver.edu/~wcherowi/courses/m5410/exeucalg.html. [Accessed: 22- Apr- 2018].
 * [6] S. Dasgupta, C. Papadimitriou and U. Vazirani, Algorithms.
 * [7] W. RSA?, "Why do we need Euler's totient function $\varphi(N)$ in RSA?", Crypto.stackexchange.com, 2018. [Online]. Available: https://crypto.stackexchange.com/questions/33676/why-do-we-need-eulers-totient-function-varphin-in-rsa. [Accessed: 22- Apr- 2018].
 */
public class App {

    public static void main(String[] args) {
        String msgPlain = "Curso de Pos Graduacao em Computacao Aplicada UDESC";
        for(int i = 12; i < 4096; i++) {
            testRSA(i, msgPlain);
        }
    }

    public static boolean testRSA(int bitSize, String msgPlain) {
        RSA rsa = new RSA(bitSize);
        String rec = bitSize + ";";
        try {
            long time = System.currentTimeMillis();
            rsa.generateKeys();
            rec += (System.currentTimeMillis() - time) + ";"; time = System.currentTimeMillis();
            //System.out.println("Original = " + msgPlain);
            String msgEncrypt = rsa.getPublicKey().encrypt(msgPlain, 1);
            rec += (System.currentTimeMillis() - time) + ";"; time = System.currentTimeMillis();
            //System.out.println("Encrypted = " + msgEncrypt);
            String msgDecrypt = rsa.getPrivateKey().decrypt(msgEncrypt);
            rec += (System.currentTimeMillis() - time) + ";"; time = System.currentTimeMillis();
            //System.out.println("Decrypted = " + msgDecrypt);
            if (msgPlain.equals(msgDecrypt)) {
                String broken = BruteForce.broke(rsa, msgEncrypt);
                rec += (System.currentTimeMillis() - time) + ";"; time = System.currentTimeMillis();
                if(broken.equals(msgDecrypt)) {
                    System.out.println(rec);
                    return true;
                }
            }
        } catch (Exception ex) { }
        return false;
    }

}
