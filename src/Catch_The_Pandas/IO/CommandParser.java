package Catch_The_Pandas.IO;

import Catch_The_Pandas.Resources.GameElements.Floor;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class CommandParser {
    private String path;
    private Floor floor;
    private OutputWriter output;

    public CommandParser(Floor f, String inputpath, OutputWriter o){
        this.path = inputpath;
        this.floor = f;
        output = o;
    }

    public ArrayList<Command> parse(){
        ArrayList<Command> temp = new ArrayList<>();
        List<String> lines = readLines(path + "commands.txt");
        for(String line:lines){
            String[] splittedLine = line.split("::");
            Command tempc = null;

            switch(splittedLine[0]){
                case "move":

                    //ez egy move tipusu parancs lesz
                    tempc = new Command(CommandType.move, floor, output);
                    tempc.setOrangutan(floor.getOrangutan(Integer.parseInt(splittedLine[1])));
                    tempc.setTarget(floor.getTile(Integer.parseInt(splittedLine[2])));
                    temp.add(tempc);

                    break;
                case "release":
                    //ez egy release tipusu parancs lesz
                    tempc = new Command(CommandType.release, floor, output);
                    tempc.setOrangutan(floor.getOrangutan(Integer.parseInt(splittedLine[1])));
                    temp.add(tempc);
                    break;

                case "display":
                    tempc = new Command(CommandType.display, floor, output);
                    tempc.setOrangutan(floor.getOrangutan(Integer.parseInt(splittedLine[1])));
                    temp.add(tempc);
                    break;

                case "displayLine":
                    tempc = new Command(CommandType.displayLine, floor, output);
                    tempc.setOrangutan(floor.getOrangutan(Integer.parseInt(splittedLine[1])));
                    temp.add(tempc);
                    break;

                case "displayAll":
                    tempc = new Command(CommandType.displayAll, floor, output);
                    temp.add(tempc);
                    break;

                case "eachTurn":
                    tempc = new Command(CommandType.eachTurn, floor, output);
                    temp.add(tempc);
                    break;

                default:
                    output.write("invalid command");
                }
        }
        return temp;
    }

    private List<String> readLines(String fullname){
        List<String> temp = null;
        //új stream api kifejezés a fullname elérési útvonalú fájl soraiból
        try (Stream<String> stream = Files.lines(Paths.get(fullname)))
        {
            //listába gyűjtjük a sorokat (jobb szeretném helyben feldolgozni, még gondolkozom rajta!)
            temp = stream.collect(Collectors.toList());
        } catch (IOException e)
        {
            e.printStackTrace();
        };
        return temp;
    }
}
