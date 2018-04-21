package com.github.schmittjoaopedro;

import java.math.BigInteger;

public class PublicKey {

    public BigInteger n;

    public BigInteger e;

    public String encrypt(String message) {
        StringBuilder msgCrypt = new StringBuilder();
        for(int c = 0; c < message.length(); c++) {
            BigInteger msgText = new BigInteger(String.valueOf(((int) message.charAt(c))));
            BigInteger msgCode = msgText.modPow(e, n);
            msgCrypt.append(msgCode.toString());
            if(c != message.length() - 1) {
                msgCrypt.append("-");
            }
        }
        return msgCrypt.toString();
    }

}
