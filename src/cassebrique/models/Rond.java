package cassebrique.models;

import java.awt.*;

public class Rond extends Sprite {

    protected int diametre;

    public Rond(int x, int y, int diametre, Color couleur) {
        super(x, y, couleur);
        this.diametre = diametre;
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