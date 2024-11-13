package cassebrique.models;

import java.awt.*;

public class Rond extends Sprite{
    private int diametre;

    public Rond(int x, int y, Color color, int diametre) {

    }

    public Rond() {

    }

    public Rond(int x, int y) {
    }

    public void dessiner(Graphics2D dessin) {
        dessin.setColor(couleur);
        dessin.fillOval(x,y,diametre,diametre);
    }

}

