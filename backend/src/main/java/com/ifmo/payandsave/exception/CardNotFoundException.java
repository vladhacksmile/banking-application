package com.ifmo.payandsave.exception;

public class CardNotFoundException extends RuntimeException {
    public CardNotFoundException() {
        super("Card not found!");
    }
}
