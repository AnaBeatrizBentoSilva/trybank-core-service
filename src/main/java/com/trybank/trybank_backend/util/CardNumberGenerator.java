package com.trybank.trybank_backend.util;

import java.util.Random;

public class CardNumberGenerator {
    public static String generateCardNumber() {
        Random random = new Random();
        StringBuilder cardNumber = new StringBuilder();
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(10));
        }
        return cardNumber.toString();
    }
    
    public static String generateSecurityCode() {
        Random random = new Random();
        int code = 100 + random.nextInt(900);
        return String.valueOf(code);
    }
}
