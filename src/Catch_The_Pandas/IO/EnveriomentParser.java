package Catch_The_Pandas.IO;

import Catch_The_Pandas.Resources.GameElements.*;
import Catch_The_Pandas.Resources.GameElements.*;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class EnveriomentParser {
    private String path = null;
    private Floor floor;

    public EnveriomentParser(Floor f, String filepath){
        path = filepath;
        floor = f;
    }

    //fő függvény, mely a megfelelő paraméter függvényében a beolvasott objektummal tér vissza
    public Object parse(InputType type) throws Exception{
        Object res = null;
            switch(type){
                case tiles:
                    res = parseTiles(path + "tiles.txt");
                    break;
                case objects:
                    res = parseObjects(path + "objects.txt");
                    break;
                case connections:
                    res = parseConnections(path + "connections.txt");
                    break;

            }
            if (res!=null) return res;
                else throw new Exception("error parsing file");

    }

    private Map<Integer, Tile> parseTiles(String fullname){
        java.util.Map<Integer, Tile> res = new HashMap<Integer, Tile>();

        //beolvassa a fájlt, és sorok tömbjében visszaadja
        List<String> temp = readLines(fullname);
        //soronként feldolgozzuk a bemenetet
        for(String tstring: temp){
            //csak tesztelési cél
            System.out.println(tstring);
            //a :: szeparátorral elválasztjuk a sort,
            //melynek szerkezete a következő: [TYPE]::[ID]::[HEALTH], ahonnan a health opcionális,
            //hiszen csak a törékeny csempének van élete... sima csempénél amennyiben jelen van, ignorálódik
            // a "//" kommentet jelöl a fájlban is, ignorálásra kerül
            String[] splittedline = tstring.split("::");
            if (splittedline[0].contains("//"))
                System.out.println("Comment line: " + splittedline[0]);
            if (splittedline.length<2) break;
            switch(splittedline[1]){
                case "t":
                    //ha a sor t-vel kezdődik, sima tile-t gyártunk
                    //hozzáadjuk a listához
                    res.put(Integer.parseInt(splittedline[0]),new Tile(floor, Integer.parseInt(splittedline[0])));
                    break;
                case "ft":
                    //ha a sor ft-vel kezdődik, törékeny csempét hozunk létre
                    //hozzáadjuk a listához
                    res.put(Integer.parseInt(splittedline[0]), new FragileTile(floor,
                            Integer.parseInt(splittedline[0]),Integer.parseInt(splittedline[2])));
                    break;
                case "ex":
                    res.put(Integer.parseInt(splittedline[0]),new Exit(floor, Integer.parseInt(splittedline[0])));
                case "en":
                    res.put(Integer.parseInt(splittedline[0]),new Entrance(floor, Integer.parseInt(splittedline[0])));
                    //kommentet // jelölés után írhatunk a fájlba
                default:
                    break;
            }
        }
        //visszatérünk az elkészült listával
        return res;
    }

    private ArrayList<TileConnection> parseConnections (String fullname){
        ArrayList<TileConnection> res = new ArrayList<TileConnection>();
        List<String> temp = readLines(fullname);
        for (String tempstring: temp){
            //előzőhöz hasonlóan
            //csak itt a fájl szerkezete az alábbi:
            //[ID1]::[ID2], ahol a két szomszédos csempe azonosítója a két ID
            System.out.println(tempstring);
            String[] splittedline = tempstring.split("::");
            if (splittedline[0].contains("//"))
                System.out.println("Comment line: " + splittedline[0]);
            if(splittedline.length<2) break;
            res.add(new TileConnection(
                    Integer.parseInt(splittedline[0]),
                    Integer.parseInt(splittedline[1])));
        }
        return res;
    }

    private HashMap<Integer, OnTileObject> parseObjects(String fullname){
        HashMap<Integer, OnTileObject> res = new HashMap<Integer, OnTileObject>();
        List<String> temp = readLines(fullname);
        for(String tempstring: temp){
            String[] splittedline = tempstring.split("::");
            if (splittedline[0].contains("//"))
                System.out.println("Comment line: " + splittedline[0]);
            if (splittedline.length<2) break;

            //formátum: locationID::TYPE::opcionális orangutanID
            switch(splittedline[1]){
                case "O":
                    Orangutan tempo = new Orangutan(Integer.parseInt(splittedline[2]));
                    res.put(Integer.parseInt(splittedline[0]), tempo);
                    floor.addOrangutan(tempo.getID(), tempo);

                    break;
                case "CP":
                    res.put(Integer.parseInt(splittedline[0]),new CowardPanda());
                    break;
                case "JP":
                    res.put(Integer.parseInt(splittedline[0]),new JumpyPanda());
                    break;
                case "LP":
                    res.put(Integer.parseInt(splittedline[0]),new LazyPanda());
                    break;
                case "ARC":
                    res.put(Integer.parseInt(splittedline[0]),new Arcade());
                    break;
                case "ARM":
                    res.put(Integer.parseInt(splittedline[0]),new Armchair());
                    break;
                case "CV":
                    res.put(Integer.parseInt(splittedline[0]),new CandyVending());
                    break;
                case "W":
                    res.put(Integer.parseInt(splittedline[0]),new Wardrobe());
                    break;
                default:
                    break;

            }
        }
        return res;
    }

    //reads lines into a list
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
