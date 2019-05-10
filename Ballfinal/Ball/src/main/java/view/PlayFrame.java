package view;

import constants.constants;
import game.model;
import listener.AddKeyListener;
import listener.PlayToBuildListener;

import javax.swing.*;
import java.awt.*;

public class PlayFrame extends JPanel {
    private game.model model;
    private static JFrame playFrame;
    private static JPanel playPanel;
    private MainFrame mainFrame;
    private PlayToBuildListener playToBuildListener;
    private AddKeyListener addKeyListener;

    public PlayFrame(model m){
        model = m;
        playToBuildListener = new PlayToBuildListener(m);
        addKeyListener = new AddKeyListener(m);
        Build();
        Mode();
        Options();
        Board();
        makePlayFrameVisible();
    }

    public void Build(){
        playPanel = new JPanel();
        playFrame = new JFrame();
        playFrame.getContentPane().add(playPanel, BorderLayout.NORTH);
        playFrame.setTitle("Gizmoball_Playmode");
        playFrame.setFocusable(true);
        playFrame.setFocusTraversalKeysEnabled(false);
        playFrame.setSize(1000,1000);
        playFrame.setLocationRelativeTo(null);
        playFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        playFrame.setResizable(false);
        //playFrame.addKeyListener(addKeyListener);
    }

    public void makePlayFrameVisible(){
        playFrame.setVisible(true);
        mainFrame.requestFocus();
        playFrame.pack();
    }

    public static void makePlayFrameInvisible(){
        playFrame.dispose();
        playFrame.pack();
    }

    public void Mode(){
        JButton jButton = new JButton("Build");
        jButton.addActionListener(playToBuildListener);
        playPanel.add(jButton);
    }

    public void Options(){
        JButton jButton = new JButton("Start");
        jButton.addActionListener(playToBuildListener);
        playPanel.add(jButton);
        JButton jButton2 = new JButton("Stop");
        jButton2.addActionListener(playToBuildListener);
        playPanel.add(jButton2);
        //playPanel.setFocusable(true);
    }

    public void Board(){
        mainFrame = new MainFrame(constants.collomnBig,constants.rowBig,model);
        mainFrame.addKeyListener(addKeyListener);
        mainFrame.setBackground(Color.WHITE);
        playFrame.getContentPane().add(mainFrame,BorderLayout.CENTER);
        playFrame.pack();
    }
}
