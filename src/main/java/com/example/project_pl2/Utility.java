package com.example.project_pl2;

import java.util.Random;

public interface Utility {
     enum CompletionStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELED
    }
     static int generateID(int seed){
        Random rand = new Random();
        return seed * 100 + rand.nextInt(100);
    }
}
