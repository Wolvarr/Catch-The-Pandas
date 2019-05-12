package Catch_The_Pandas.IO;

import Catch_The_Pandas.ImageContainer;
import Catch_The_Pandas.Resources.GameElements.*;
import javafx.geometry.Point2D;

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
    private ImageContainer imageContainer = new ImageContainer();
    private ArrayList<TileView> tileViews = new ArrayList<>();
    private ArrayList<OnTileObjectView> objectViews = new ArrayList<>();


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
                case graphics:
                    res = parseGraphics(path + "tileGraphics.txt");
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
                    tileViews.add(new TileView(imageContainer.normalTileImage));
                    break;
                case "ft":
                    //ha a sor ft-vel kezdődik, törékeny csempét hozunk létre
                    //hozzáadjuk a listához
                    res.put(Integer.parseInt(splittedline[0]), new FragileTile(floor,
                            Integer.parseInt(splittedline[0]),Integer.parseInt(splittedline[2])));
                    tileViews.add(new TileView(imageContainer.crackedTileImage));
                    break;
                case "ex":
                    res.put(Integer.parseInt(splittedline[0]),new Exit(floor, Integer.parseInt(splittedline[0])));
                    tileViews.add(new TileView(imageContainer.exitImage));
                case "en":
                    res.put(Integer.parseInt(splittedline[0]),new Entrance(floor, Integer.parseInt(splittedline[0])));
                    tileViews.add(new TileView(imageContainer.entranceImage));
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
                    //tempo.setView(new OnTileObjectView(Colour.none, ));
                    res.put(Integer.parseInt(splittedline[0]), tempo);
                    floor.addOrangutan(tempo.getID(), tempo);
                    tileViews.get(Integer.parseInt(splittedline[0])).objectView =
                            new OnTileObjectView(imageContainer.orangutanImage,
                                    imageContainer.orangutanImage1,
                                    imageContainer.orangutanImage2,
                                    imageContainer.orangutanImage3,
                                    imageContainer.orangutanImage4);

                    break;
                case "CP":
                    res.put(Integer.parseInt(splittedline[0]),new CowardPanda());
                    tileViews.get(Integer.parseInt(splittedline[0])).objectView =
                            new OnTileObjectView(imageContainer.scaredPandaImage,
                                    imageContainer.scaredPandaImage1,
                                    imageContainer.scaredPandaImage2,
                                    imageContainer.scaredPandaImage3,
                                    imageContainer.scaredPandaImage4);
                    break;
                case "JP":
                    res.put(Integer.parseInt(splittedline[0]),new JumpyPanda());
                    tileViews.get(Integer.parseInt(splittedline[0])).objectView =
                            new OnTileObjectView(imageContainer.jumpyPandaImage,
                                    imageContainer.jumpyPandaImage1,
                                    imageContainer.jumpyPandaImage2,
                                    imageContainer.jumpyPandaImage3,
                                    imageContainer.jumpyPandaImage4);
                    break;
                case "LP":
                    res.put(Integer.parseInt(splittedline[0]),new LazyPanda());
                    res.put(Integer.parseInt(splittedline[0]),new JumpyPanda());
                    tileViews.get(Integer.parseInt(splittedline[0])).objectView =
                            new OnTileObjectView(imageContainer.sleepyPandaImage,
                                    imageContainer.sleepyPandaImage1,
                                    imageContainer.sleepyPandaImage2,
                                    imageContainer.sleepyPandaImage3,
                                    imageContainer.sleepyPandaImage4);
                    break;
                case "ARC":
                    res.put(Integer.parseInt(splittedline[0]),new Arcade());
                    tileViews.get(Integer.parseInt(splittedline[0])).objectView =
                            new OnTileObjectView(imageContainer.arcadeImage);
                    break;
                case "ARM":
                    res.put(Integer.parseInt(splittedline[0]),new Armchair());
                    tileViews.get(Integer.parseInt(splittedline[0])).objectView =
                            new OnTileObjectView(imageContainer.sofaImage);
                    break;
                case "CV":
                    res.put(Integer.parseInt(splittedline[0]),new CandyVending());
                    tileViews.get(Integer.parseInt(splittedline[0])).objectView =
                            new OnTileObjectView(imageContainer.candyVendingImage);
                    break;
                case "W":
                    res.put(Integer.parseInt(splittedline[0]),new Wardrobe());
                    tileViews.get(Integer.parseInt(splittedline[0])).objectView =
                            new OnTileObjectView(imageContainer.wardrobeImage);
                    break;
                default:
                    break;

            }
        }
        return res;
    }

    private ArrayList<TileView> parseGraphics(String fullname){
        List<String> temp = readLines(fullname);
        for(int i=0; i<temp.size(); i++) {
            System.out.println("read position #" + i);
            String tempstring = temp.get(i);
            String[] splittedline = tempstring.split("::");
            tileViews.get(i).location = new Point2D(Integer.parseInt(splittedline[0]), Integer.parseInt(splittedline[1]));
        }
        return tileViews;
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
