package listener;

import game.model;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

public class ClearBoardListener implements ActionListener {

    private model model;

    public ClearBoardListener(model m){
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        model.clearBoard();
    }
}
