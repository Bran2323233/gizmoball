package listener;

import game.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DeleteBarrierListener implements ActionListener {

    private game.model model;

    public DeleteBarrierListener(model m){
        this.model = m;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        model.setPatternState(0);
    }
}
