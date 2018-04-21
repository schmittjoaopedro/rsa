package com.github.schmittjoaopedro;

import java.math.BigInteger;

public class PrivateKey {

    public BigInteger n;

    public BigInteger d;

    public String decrypt(String msgCrypt) {
        StringBuilder msgPlain = new StringBuilder();
        for(String block : msgCrypt.split("-")) {
            BigInteger charPlain = new BigInteger(block).modPow(d, n);
            msgPlain.append((char) charPlain.intValue());
        }
        return msgPlain.toString();
    }

    public String decrypt(String message, Integer blockSize) {
        StringBuilder msgPlain = new StringBuilder();
        for(String block : message.split("-")) {
            String msgBlock = new BigInteger(block).modPow(d, n).toString();
            int end = msgBlock.length();
            String messageTemp = "";
            while(end > 0) {
                String charMessage = String.valueOf((char) Integer.valueOf(msgBlock.substring(Math.max(end - 3, 0), end)).intValue());
                end -= 3;
                messageTemp = charMessage + messageTemp;
            }
            msgPlain.append(messageTemp);
        }
        return msgPlain.toString();
    }

}
