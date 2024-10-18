package cassebrique.models;

public class Collision {

    public static boolean Col(int xP, int yP, int largeurP, int hauteurP, int xD, int yD, int largeurD, int hauteurD){
        return (xP < xD + largeurD &&
                xP + largeurP > xD &&
                yP < yD + hauteurD &&
                yP + hauteurP > yD);
    }}