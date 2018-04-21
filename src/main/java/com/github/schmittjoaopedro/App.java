package com.github.schmittjoaopedro;

/**
 * Bibliography:
 *
 * [1] W. Stallings, D. Vieira, A. Barbosa and M. Ferreira, Criptografia e segurança de redes. São Paulo: Pearson Prentice Hall, 2008.
 * [2] H. Java?, "How to generate a random BigInteger value in Java?", Stackoverflow.com, 2018. [Online]. Available: https://stackoverflow.com/questions/2290057/how-to-generate-a-random-biginteger-value-in-java. [Accessed: 20- Apr- 2018].
 * [3] w. David Ireland, "RSA Algorithm", Di-mgt.com.au, 2018. [Online]. Available: https://www.di-mgt.com.au/rsa_alg.html. [Accessed: 21- Apr- 2018].
 * [4] w. David Ireland, "The Euclidean Algorithm and the Extended Euclidean Algorithm", Di-mgt.com.au, 2018. [Online]. Available: https://www.di-mgt.com.au/euclidean.html. [Accessed: 21- Apr- 2018].
 */
public class App  {

    public static void main( String[] args ) {
        RSA rsa = new RSA();
        rsa.generateKeys();
        String msgPlain = "Joao Pedro Schmitt";
        System.out.println("Original = " + msgPlain);
        String msgEncrypt = rsa.getPublicKey().encrypt(msgPlain);
        System.out.println("Encrypted = " + msgEncrypt);
        String msgDecrypt = rsa.getPrivateKey().decrypt(msgEncrypt);
        System.out.println("Decrypted = " + msgDecrypt);
    }

}
