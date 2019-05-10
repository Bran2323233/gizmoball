package listener;

import game.model;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class AddKeyListener implements KeyListener {

    private game.model model;

    public AddKeyListener(model m){
        model = m;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(model.getAllFlipper().size() != 0){
            if(e.getKeyCode() ==  KeyEvent.VK_LEFT){
                model.moveFlipper(1);
            }else if(e.getKeyCode() ==  KeyEvent.VK_RIGHT){
                model.moveFlipper(2);
            }
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
