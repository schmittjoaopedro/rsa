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

}
