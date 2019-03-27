package Catch_The_Pandas.IO;

import java.util.ArrayList;
import java.util.List;

public class ListDisplay {
    private int selected = 0;
    List<String[]> display = new ArrayList<>();


    public boolean select(int selection){
        if (selection <= display.size()){
            selected = selection;
            return true;
        }
        else return false;
    }

    public void addItem(String[] newitem){
        display.add(newitem);
    }

    public void show(){
        for(String[] item: display){
            for(int i=0; i<item.length; i++){
                System.out.println(item[i]);
            }
        }
    }

    public int getSelected(){
        return selected;
    }

    public void clear(){
        selected = 0;
        display = new ArrayList<>();
    }

}
