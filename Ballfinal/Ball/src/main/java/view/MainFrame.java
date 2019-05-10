package view;

import constants.constants;
import game.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;

public class MainFrame extends JPanel implements Observer {

    private model model;
    private static int width;
    private static int height;
    protected static final int L=20;
    //以正方形为例

    public MainFrame(int w,int h,model m){
        width = w;
        height = h;
        model = m;
    }
    //向布局者推荐使用尺寸

    @Override
    public Dimension getPreferredSize(){return new Dimension(width,height);}

    @Override
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        paintGrid(g);
        paintCircle(g);
        paintRectangle(g);
        paintTriangle(g);
        paintBall(g);
        paintFlipper(g);
        paintAbsorber(g);
        repaint();
    }

    //画小球

    public void paintBall(Graphics g){

        int xS;
        int yS;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.orange);
        g2.setStroke(new BasicStroke(2f));
        ArrayList<ball> balls = (ArrayList<ball>) model.getAllBall();
        for(ball i:balls){
            xS = (int)i.getx() - constants.sizeBall/2;
            yS = (int)i.gety() - constants.sizeBall/2;
            g2.fillOval(xS,yS , constants.sizeBall,constants.sizeBall);
        }
    }
    //画挡板

    public void paintFlipper(Graphics g){
        int xS;
        int yS;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        g2.setStroke(new BasicStroke(2f));
        ArrayList<flipper> flippers = (ArrayList<flipper>) model.getAllFlipper();
        for(flipper i:flippers){
            xS = i.getx();
            yS = i.gety() + constants.size/2;
            for(int j = xS; j < xS + constants.rowBig/2 - constants.size/2 ;j += constants.size/2){
                g2.fillRect(j,yS , constants.size,constants.size);
            }
        }
    }

    //画吸收器

    public  void paintAbsorber(Graphics g){

        int xS;
        int yS;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.black);
        g2.setStroke(new BasicStroke(2f));
        ArrayList<absorber> absorbers = (ArrayList<absorber>) model.getAllAbsorber();
        for(absorber i:absorbers){
            xS = i.getx();
            yS = i.gety();
            g2.fillOval(xS,yS , constants.size,constants.size);
        }
    }

    //画圆，正方形，三角形

    public void paintCircle(Graphics g ){
        int xS;
        int yS;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.red);
        g2.setStroke(new BasicStroke(2f));
        ArrayList<circle> circles = (ArrayList<circle>) model.getAllCircle();
        for(circle i:circles){
            xS = i.getx();
            yS = i.gety();
            g2.fillOval(xS,yS , constants.size,constants.size);
        }
    }

    public void paintRectangle(Graphics g){
        int xS;
        int yS;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.blue);
        g2.setStroke(new BasicStroke(2f));
        ArrayList<rectangle> rectangles = (ArrayList<rectangle>) model.getAllRectangle();
        for(rectangle i:rectangles){
            xS = i.getx();
            yS = i.gety();
            g2.fillRect(xS,yS , constants.size,constants.size);
        }
    }

    public void paintTriangle(Graphics g){
        int xS;
        int yS;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.green);
        g2.setStroke(new BasicStroke(2f));
        ArrayList<triangle> triangles = (ArrayList<triangle>) model.getAllTriangle();
        for(triangle i:triangles){
            xS = i.getx();
            yS = i.gety();
            Polygon filledPolygon = new Polygon();
            switch(i.getState()){
                case 0:
                    filledPolygon.addPoint(xS,yS);
                    filledPolygon.addPoint(xS + constants.size,yS + constants.size);
                    filledPolygon.addPoint(xS,yS + constants.size);
                    g2.fillPolygon(filledPolygon);
                    break;
                case 1:
                    filledPolygon.addPoint(xS,yS);
                    filledPolygon.addPoint(xS + constants.size, yS);
                    filledPolygon.addPoint(xS,yS + constants.size);
                    g2.fillPolygon(filledPolygon);
                    break;
                case 2:
                    filledPolygon.addPoint(xS,yS);
                    filledPolygon.addPoint(xS + constants.size,yS + constants.size);
                    filledPolygon.addPoint(xS + constants.size,yS);
                    g2.fillPolygon(filledPolygon);
                    break;
                case 3:
                    filledPolygon.addPoint(xS + constants.size,yS);
                    filledPolygon.addPoint(xS + constants.size,yS + constants.size);
                    filledPolygon.addPoint(xS,yS + constants.size);
                    g2.fillPolygon(filledPolygon);
                    break;
                    default:
            }
        }

    }
    public void paintGrid(Graphics g){
        g.setColor(Color.black);
        for (int k = 0; k <= L; k++) {
            g.drawLine(0, k * height/ L,width,k * height / L);
        }
        for (int k = 0; k <= L; k++) {
            g.drawLine(k * width / L, 0, k * width / L, height);
        }
    }

    @Override
    public void update(Observable arg0, Object arg1){ repaint(); }
}
