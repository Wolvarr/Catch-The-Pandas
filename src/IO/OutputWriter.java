package IO;

import java.io.*;

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

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter(outputPath, true));
                    writer.newLine();
                    writer.append(s);

                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                break;
        }
    }

}
