package com.example.project_pl2;

public interface Utility {
     enum CompletionStatus {
        PENDING, IN_PROGRESS, COMPLETED, CANCELED
    }
    public class UserSingle{
         private static UserSingle instance = null;
         public Employee emp = null;
         public Admin admin = null;
         private UserSingle(){}
         public static UserSingle getInstance(){
             if (instance == null){
                 instance = new UserSingle();
             }
             return instance;
         }
         public static void nullifyInstance(){
                instance = null;
         }

    }

}
