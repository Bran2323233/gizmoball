package listener;

import game.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RotateBarrierListener implements ActionListener {

    private game.model model;

    public RotateBarrierListener(model m){
        model = m;

    }
    @Override
    public void actionPerformed(ActionEvent e) {

        model.setPatternState(4);
    }
}
