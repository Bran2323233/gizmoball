package listener;

import game.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddBallListener implements ActionListener {

    private game.model model;

    public  AddBallListener(model m){
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        model.setPatternState(1);
        model.setBarrierState(6);
    }
}
