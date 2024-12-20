package cassebrique;

import cassebrique.models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

public class CasseBrique extends Canvas implements KeyListener {

    public JFrame fenetre = new JFrame();
    public ArrayList<Balle> listeBalle = new ArrayList<>();
    public ArrayList<Brique> listeBrique = new ArrayList<>();
    public ArrayList<Sprite> listeSprite = new ArrayList<>();
    public Barre barre;

    public static final int LARGEUR = 500;
    public static final int HAUTEUR = 700;

    public boolean toucheDroite = false;
    public boolean toucheGauche = false;

    public CasseBrique() throws InterruptedException {

        this.fenetre.setSize(LARGEUR, HAUTEUR);
        this.setSize(LARGEUR, HAUTEUR);
        this.setBounds(0,0,LARGEUR, HAUTEUR);

        this.fenetre.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        JPanel panneau = new JPanel();
        panneau.add(this);
        this.fenetre.setContentPane(panneau);

        this.setIgnoreRepaint(true);
        this.setFocusable(false);
        this.fenetre.pack();
        this.fenetre.setResizable(false);
        this.fenetre.requestFocus();
        this.fenetre.addKeyListener(this);

        this.fenetre.setVisible(true);
        this.createBufferStrategy(2);

        lancerUnePartie();
    }

    public void lancerUnePartie() throws InterruptedException {

        listeBalle = new ArrayList<>();
        listeBalle.add(new Balle(100,300,3,4));
        listeBalle.add(new Balle(200,300,2,3));
        listeBalle.add(new Balle(150,300,1,2));

        listeSprite.add(listeBalle.get(0));
        listeSprite.add(listeBalle.get(1));
        listeSprite.add(listeBalle.get(2));

        barre = new Barre(
                CasseBrique.LARGEUR / 2 - Barre.largeurDefaut / 2,
                CasseBrique.HAUTEUR - 100);

        listeBrique = new ArrayList<>();
        for (int indexLigne = 0; indexLigne < 5; indexLigne ++) {
            for (int indexColonne = 0; indexColonne < 7; indexColonne ++) {
                Brique brique = new Brique(
                        indexColonne * (Brique.largeurDefaut + 2),
                        indexLigne * (Brique.hauteurDefaut + 2),
                        Color.CYAN);
                listeBrique.add(brique);
                listeSprite.add(brique);
            }
        }

        //la balle peut avoir une couleur differente
        //ajouter un constructeur permettant de definir la couleur de la balle
        //si aucune couleur n'est donnée (utilisation du premier constructeur) : la couleur est aléatoire
        //    Math.random() = donne un nombre entre 0 et 1 (un double)
        //    new Color(R, G , B)  prend 3 float en parametre (pour rappel un double est trop grand pour un float)
        while(true) {

            Graphics2D dessin = (Graphics2D)this.getBufferStrategy().getDrawGraphics();

            dessin.setColor(Color.WHITE);
            dessin.fillRect(0, 0, LARGEUR, HAUTEUR);

            for(Balle balle : listeBalle) {
                balle.deplacer();
            }

            for(Sprite sprite : listeSprite) {
                sprite.dessiner(dessin);
            }

            if(toucheDroite){
                barre.deplacementDroite();
            }
            if(toucheGauche){
                barre.deplacementGauche();
            }

            barre.dessiner(dessin);

            for (int i = 0; i < listeBrique.size(); i++) {
                Brique brique = listeBrique.get(i);
                brique.dessiner(dessin);
                for (Balle balle : listeBalle) {
                    if (Collision.Col(balle.getX(), balle.getY(), balle.getDiametre(), balle.getDiametre(),
                            brique.getX(), brique.getY(), brique.getLargeurDefaut(), brique.getHauteurDefaut())) {
                        balle.setVitesseY(-balle.getVitesseY());
                        listeBrique.remove(i);
                        listeSprite.remove(brique);
                        i--;
                        break;
                    }
                }
            }

            dessin.dispose();
            this.getBufferStrategy().show();

            for (Balle balle : listeBalle) {
                balle.deplacer();
                balle.dessiner(dessin);
                if (Collision.Col(balle.getX(), balle.getY(), balle.getDiametre(), balle.getDiametre(),
                        barre.getX(), barre.getY(), barre.getLargeurDefaut(), barre.getHauteurDefaut())) {
                    balle.setVitesseY(-balle.getVitesseY());
                }
            }

            dessin.dispose();
            this.getBufferStrategy().show();

            Thread.sleep(1000 / 60);
        }
    }

    //main : raccourci
    public static void main(String[] args) throws InterruptedException {
        new CasseBrique();
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            toucheDroite = true;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_A) {
            toucheGauche = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_RIGHT || e.getKeyCode() == KeyEvent.VK_D) {
            toucheDroite = false;
        }

        if(e.getKeyCode() == KeyEvent.VK_LEFT || e.getKeyCode() == KeyEvent.VK_Q || e.getKeyCode() == KeyEvent.VK_A) {
            toucheGauche = false;
        }
    }
}