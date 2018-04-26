package com.github.schmittjoaopedro;

import java.io.Serializable;
import java.math.BigInteger;

public class PrivateKey implements Serializable {

    public int keySize;

    public BigInteger n;

    public BigInteger d;

    public String decrypt(String message) {
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
