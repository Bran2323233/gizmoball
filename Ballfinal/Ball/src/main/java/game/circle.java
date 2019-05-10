package game;

import constants.constants;
import interFace.barrier;
import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;
import java.util.List;


public class circle extends barrier {

    private double myx,myy;
    private Circle circle;
    List<Circle> circles;

    //
    public circle(int x, int y ) {
        super(x, y);
        myx = (double) x + (double) constants.size/2;
        myy = (double) y + (double) constants.size/2;
    }

    @Override
    public boolean isCollide(ball b) {

        if(Math.sqrt(Math.pow(Math.abs(this.getx()+(double)(constants.size/2) - b.getx()),2) + Math.pow(Math.abs(this.gety() +
                (double)(constants.size/2)- b.gety()),2)) < constants.sizeBall/2 + constants.size/2){
            return true;
        }
        return false;
    }

    @Override
    public void collisionResult(ball b){

        b.setSpeed(-b.getSpeedx(),-b.getSpeedy());
    }

    public List<LineSegment> getLineSegments() {
        return new ArrayList<LineSegment>();
    }

    public List<Circle> getCircles() {
        this.circle = new Circle(myx, myy, (double) (constants.size/2));
        circles = new ArrayList<Circle>();
        circles.add(circle);
        return circles;
    }
}
