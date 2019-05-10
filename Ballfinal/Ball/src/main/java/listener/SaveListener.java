package listener;

import game.Save;
import game.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class SaveListener implements ActionListener {

    private game.model model;

    public SaveListener(model m){
        model = m;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Save")) {
            JFileChooser save = new JFileChooser();
            save.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int returnValue = save.showSaveDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                File saveFile = save.getSelectedFile();
                Save saveBuild = new Save();
                saveBuild.writeFile(model, saveFile.getName());
            }
        }
    }
}
