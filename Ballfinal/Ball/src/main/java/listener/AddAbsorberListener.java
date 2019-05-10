package listener;

import game.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddAbsorberListener implements ActionListener {

    private game.model model;

    public AddAbsorberListener(model m){

        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        model.setPatternState(1);
        model.setBarrierState(5);
    }
}
