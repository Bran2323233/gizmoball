package game;

import constants.constants;
import interFace.barrier;
import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;
import java.util.List;

public class flipper extends barrier {

    private double myx,myy;
    List<LineSegment> lineSegments;
    List<Circle> circles;

    public flipper(int x, int y) {

        super(x, y );
        myx = (double)x;
        myy = (double)(y + constants.size/2);
    }

    public List<LineSegment> getLineSegments() {

        lineSegments = new ArrayList<LineSegment>();
        lineSegments.add(new LineSegment(myx,myy,myx+constants.rowBig/2,myy));
        lineSegments.add(new LineSegment(myx+constants.rowBig/2,myy,myx+constants.rowBig/2,myy+constants.size/2));
        lineSegments.add(new LineSegment(myx,myy+constants.size/2,myx+constants.rowBig/2,myy+constants.size/2));
        lineSegments.add(new LineSegment(myx,myy,myx,myy+constants.size/2));
        return lineSegments;
    }

    public List<Circle> getCircles() {
        circles = new ArrayList<Circle>();
        circles.add(new Circle(myx,myy,0));
        circles.add(new Circle(myx,myy+constants.size/2,0));
        circles.add(new Circle(myx+constants.rowBig/2,myy,0));
        circles.add(new Circle(myx+constants.rowBig/2,myy+constants.size,0));
        return circles;
    }
    @Override
    public boolean isCollide(ball b) {

        boolean in = b.getx() > this.getx() && this.getx() + this.getsize()*this.getsize() > b.getx() && b.gety() > this.gety() && this.gety() + this.getsize()*this.getsize() > b.gety();

        if(in){
            return true;
        }
        return false;
    }

    @Override
    public void collisionResult(ball b) {

        b.setSpeed(b.getSpeedx(),-b.getSpeedy());
    }

    public void setMyx(double myx) {
        this.myx = myx;
    }


}
