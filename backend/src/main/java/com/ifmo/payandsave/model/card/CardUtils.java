package com.ifmo.payandsave.model.card;

public class CardUtils {

    public static int generateEncryptedPan(int min, int max) {
        max -= min;
        return (int) (Math.random() * ++max) + min;
    }
}
