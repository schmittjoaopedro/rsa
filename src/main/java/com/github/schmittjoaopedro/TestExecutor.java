package com.github.schmittjoaopedro;

import org.apache.commons.io.FileUtils;

import java.nio.file.Paths;

public class TestExecutor {

    public static void executeBruteForce(String messageFile, String outputFile, int[] keysSize, int trials) throws Exception {
        String message = FileUtils.readFileToString(Paths.get(messageFile).toFile(), "UTF-8");
        FileUtils.write(Paths.get(outputFile).toFile(), "key_size;generate_keys;encrypt;decrypt;brute_force\n", "UTF-8", true);
        for(int t = 0; t < trials; t++) {
            for(int keySize : keysSize) {
                RSA rsa = new RSA(keySize);
                long time = System.currentTimeMillis();
                rsa.generateKeys();
                String csvSample = keySize + ";" + (System.currentTimeMillis() - time) + ";";
                time = System.currentTimeMillis();
                String encrypted = rsa.getPublicKey().encrypt(message);
                csvSample += (System.currentTimeMillis() - time) + ";";
                time = System.currentTimeMillis();
                String decrypted = rsa.getPrivateKey().decrypt(encrypted);
                if (decrypted.equals(message)) {
                    csvSample += (System.currentTimeMillis() - time) + ";";
                } else {
                    csvSample += "-1;";
                }
                time = System.currentTimeMillis();
                String bruteForce = BruteForce.solve(rsa.getPublicKey(), encrypted);
                if (bruteForce.equals(message)) {
                    csvSample += (System.currentTimeMillis() - time) + ";\n";
                } else {
                    csvSample += "-1\n";
                }
                FileUtils.write(Paths.get(outputFile).toFile(), csvSample, "UTF-8", true);
            }
        }
    }

}
