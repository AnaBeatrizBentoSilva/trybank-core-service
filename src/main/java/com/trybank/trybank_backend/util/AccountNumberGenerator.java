package com.trybank.trybank_backend.util;

import java.util.Random;

public class AccountNumberGenerator {
    public static String generateAccountNumber(){
        Random random = new Random();
        int number = 100000 + random.nextInt(900000);
        return String.valueOf(number);
    }
}
