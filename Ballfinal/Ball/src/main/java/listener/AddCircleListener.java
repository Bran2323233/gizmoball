package listener;

import game.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AddCircleListener implements ActionListener {

    private model model;

    public AddCircleListener(model m) { model = m;}

    @Override
    public void actionPerformed(ActionEvent e){
        model.setPatternState(1);
        model.setBarrierState(0);

    }
}
