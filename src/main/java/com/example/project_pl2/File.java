package com.example.project_pl2;

import java.io.*;

public interface File {
     void update();

      void delete();

      String read();

      void write(int id,String content,String email,String password)throws IOException;
}
