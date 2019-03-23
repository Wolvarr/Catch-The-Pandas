package Catch_The_Pandas;

public class CandyVending extends Item {
    @Override
    public boolean steppedOn(Animal inComingAnimal){
        System.out.print("Nem tudsz ide lépni!");
        return false;
    }
}
