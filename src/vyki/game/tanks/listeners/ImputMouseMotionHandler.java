package vyki.game.tanks.listeners;

import vyki.game.tanks.Game;
import vyki.game.tanks.GlobalVariables;

import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

/**
 * Created by selo0215 on 20.07.2015.
 */
public class ImputMouseMotionHandler implements MouseMotionListener {
    private static Game game;

    public ImputMouseMotionHandler(Game game) {
        this.game = game;
    }

    @Override
    public void mouseDragged(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {
        GlobalVariables.cursore_X = e.getX()-400;
        GlobalVariables.cursore_Y = (e.getY()-400)*-1;
        //System.out.println("X= " +e.getY() + " Y= "+e.getY());
    }
}
