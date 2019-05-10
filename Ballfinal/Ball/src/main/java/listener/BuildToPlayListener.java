package listener;

import game.model;
import view.BuildFrame;
import view.PlayFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BuildToPlayListener implements ActionListener {

    private game.model model;

    public BuildToPlayListener(model m){
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e){

        String s = e.getActionCommand();
        if ("play".equals(s) && model.getAllBall().size() != 0) {
            BuildFrame.makeFrameInvisible();
            new PlayFrame(model);

        }
    }
}
