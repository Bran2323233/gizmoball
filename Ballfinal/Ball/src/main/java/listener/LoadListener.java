package listener;

import game.model;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class LoadListener implements ActionListener {

    private game.model model;

    public LoadListener(model m){
        this.model = m;
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Open")) {
            JFileChooser fileChooser = new JFileChooser();
            fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
            int returnValue = fileChooser.showOpenDialog(null);
            if (returnValue == JFileChooser.APPROVE_OPTION) {
                //model.clearBoard();
                File selectedFile = fileChooser.getSelectedFile();
                /*try {
                    /*model.setAllbarrier(selectedFile.toString());
                    model.setLoadedFile(selectedFile);
                } catch (FileNotFoundException e1) {
                    e1.printStackTrace();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }*/
            }
        }
    }
}
