package view;

import game.model;

import java.awt.*;

public class Run {

    public Run(){
        model mymodel = new model();
        new BuildFrame(mymodel);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Run();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
