package cassebrique.models;

public class Bonus extends Rond{
    private int vitesseY;
    private int type;
    private boolean malus;
    public static final int TYPE_VITESSE = 1;
    public static final int TYPE_TAILLE = 2;

    public Bonus(int x, int y, int type, boolean malus) {
        super(x, y);
        this.vitesseY = 2;
        this.type = type;
        this.malus = malus;
    }

    public void drop() {
        this.setY(this.getY() + vitesseY);
    }

    public int getType() {
        return type;
    }

    public boolean isMalus() {
        return malus;
    }

    public void gambling(Barre barre) {
        if (type == TYPE_VITESSE) {
            if (malus) {
                barre.diminuerVitesse();
            } else {
                barre.augmenterVitesse();
            }
        } else if (type == TYPE_TAILLE) {
            if (malus) {
                barre.reduireTaille();
            } else {
                barre.augmenterTaille();
            }
        }
    }
}
