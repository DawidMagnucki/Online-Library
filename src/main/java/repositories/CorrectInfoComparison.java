package repositories;

public class CorrectInfoComparison { // ta klasa ma na celu sprawdzić, czy dana 1 równa sie danej 2

    public boolean comparator(String object1, String object2){
        return object1.equals(object2);
    }
    public boolean comparator(int object1, int object2){
        return object1 == object2;
    }
    public boolean comparator(char object1, char object2){
        return object1 == object2;
    }
    public boolean comparator(double object1, double object2){
        return object1 == object2;
    }

}

