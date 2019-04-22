package Catch_The_Pandas.IO;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class OutputWriter {
    OutputDestination target;
    String outputPath;

    public OutputWriter(OutputDestination t){
        target = t;
    }

    public void setPath(String path){
        outputPath = path;
    }

    public void write(String s){
        switch (target){
            case console:
                System.out.print(s);
                break;

            case file:
                BufferedWriter writer = null;
                try {
                    writer = new BufferedWriter(new FileWriter(outputPath));
                } catch (IOException e) {
                    e.printStackTrace();
                }
                try {
                    writer.append(s);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
