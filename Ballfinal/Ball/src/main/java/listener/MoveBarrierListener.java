package listener;

import game.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MoveBarrierListener implements ActionListener {

    private model model;

    public MoveBarrierListener(model m){

        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        model.setPatternState(2);
    }
}
