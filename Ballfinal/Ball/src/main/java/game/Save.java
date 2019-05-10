package game;

import physics.Circle;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Save {
    private File file;
    private static final double L = 20;

    public Save() {
    }

    public void writeFile(model model, String fileName) {
        file = new File(fileName + ".gizmo");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            FileWriter fileWriter = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);
            bufferedWriter.write(String.valueOf(model.getAllCircle()));
            bufferedWriter.write(String.valueOf(model.getAllTriangle()));
            bufferedWriter.write(String.valueOf(model.getAllRectangle()));
            bufferedWriter.write(String.valueOf(model.getAllBall()));
            bufferedWriter.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
