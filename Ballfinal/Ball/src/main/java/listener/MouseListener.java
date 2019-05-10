package listener;

import game.model;
import constants.constants;
import java.awt.*;
import java.awt.event.MouseEvent;

public class MouseListener implements java.awt.event.MouseListener {

    private game.model model;

    private double x1, y1, x2, y2;

    public MouseListener(model m) {
        model = m;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        //this.drawGizmo(e);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

        this.drawGizmo(e);
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
    public void drawGizmo(MouseEvent e) {

        Point coords = e.getPoint();
        int x = ((int)coords.getX() / constants.size)*constants.size;
        int y = ((int)coords.getY() / constants.size)*constants.size;
        if(model.isPlacementMode()){
            model.userPlaceBarrier(x, y);
        }else if(model.isDeleteMode()){
            model.deleteBarrier(x,y);
        }else if(model.isRotateMode()){
            model.triangleRotate(x,y);
        }else if(model.isMoveMode()){
            model.moveBarrier(x,y);
        }else if(model.isSelectMode()){
            model.recordLastLocation(x,y);
        }
    }
}
