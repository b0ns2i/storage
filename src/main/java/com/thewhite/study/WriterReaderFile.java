package com.thewhite.study;

import java.io.*;
public class WriterReaderFile {

    String pathFile;

    WriterReaderFile(String pathFile){
        this.pathFile = pathFile;
    }

    public void WriterWRT(String json){
        try(BufferedWriter bw = new BufferedWriter(new FileWriter(pathFile)))
        {

            bw.write(json);
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }
    }
    public String ReaderWRF(){
        String json = "";
        try(BufferedReader br = new BufferedReader(new FileReader(pathFile)))
        {

            String s;
            while((s=br.readLine())!=null){

                json += s;
            }
        }
        catch(IOException ex){

            System.out.println(ex.getMessage());
        }

        return json;
    }

}
