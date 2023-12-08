package com.example.project_pl2;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Random;

public interface Utility {
     enum CompletionStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELED
    }
    public class UserSingle{
         private static UserSingle instance = null;
         public Employee data = null;
         private UserSingle(){}
         public static UserSingle getInstance(){
             if (instance == null){
                 instance = new UserSingle();
             }
             return instance;
         }

    }

}
