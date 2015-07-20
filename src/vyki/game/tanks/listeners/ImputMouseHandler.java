package vyki.game.tanks.listeners;


import vyki.game.tanks.Game;

import java.awt.event.*;

/**
 * Created by selo0215 on 20.07.2015.
 */
public class ImputMouseHandler implements MouseListener {

    private static Game game;

    public ImputMouseHandler(Game game) {
        this.game = game;
    }

    public void mouseClicked(MouseEvent e) {
        System.out.println("mouseClicked");
       // System.out.println("mouse X = "+e.getX()+" mouse Y = "+e.getY());
    }


    public void mousePressed(MouseEvent e) {
       // System.out.println("mousePressed");
    }


    public void mouseReleased(MouseEvent e) {
       // System.out.println("mouseReleased");
    }


    public void mouseEntered(MouseEvent e) {
        //System.out.println("mouseEntered");
    }


    public void mouseExited(MouseEvent e) {
        //System.out.println("mouseExited");
    }

}
