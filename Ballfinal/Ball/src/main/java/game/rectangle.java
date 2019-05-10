package game;

import constants.constants;
import interFace.barrier;
import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;
import java.util.List;

public class rectangle extends barrier {

    private double myx,myy;
    List<LineSegment> lineSegments;
    List<Circle> circles;

    public rectangle(int x, int y) {
        super(x, y);
        myx = (double) x;
        myy = (double) y;
    }

    public List<LineSegment> getLineSegments() {
        lineSegments = new ArrayList<LineSegment>();
        lineSegments.add(new LineSegment(myx,myy,myx+constants.size,myy));
        lineSegments.add(new LineSegment(myx+constants.size,myy,myx+constants.size,myy+constants.size));
        lineSegments.add(new LineSegment(myx,myy+constants.size,myx+constants.size,myy+constants.size));
        lineSegments.add(new LineSegment(myx,myy,myx,myy+constants.size));
        return lineSegments;
    }

    public List<Circle> getCircles() {
        circles = new ArrayList<Circle>();
        circles.add(new Circle(myx,myy,0));
        circles.add(new Circle(myx,myy+constants.size,0));
        circles.add(new Circle(myx+constants.size,myy,0));
        circles.add(new Circle(myx+constants.size,myy+constants.size,0));
        return circles;
    }

    @Override
    public boolean isCollide(ball b) {

        double x1 = (double) this.getx();
        double y1 = (double) this.gety();
        double ballleft = b.getx() - (double)constants.sizeBall/2;
        double ballright = b.getx() + (double)constants.sizeBall/2;
        double balltop = b.gety() - (double)constants.sizeBall/2;
        double ballbottom = b.gety() + (double)constants.sizeBall/2;

        if(ballright > x1 && ballleft < x1+(double) constants.size && ballbottom > y1 && balltop < y1 + (double) constants.size){
            return true;
        }
        return false;
    }

    @Override
    public void collisionResult(ball b) {

        double x1 = (double) this.getx();
        double y1 = (double) this.gety();
        double ballleft = b.getx() - (double)constants.sizeBall/2;
        double ballright = b.getx() + (double)constants.sizeBall/2;
        double balltop = b.gety() - (double)constants.sizeBall/2;
        double ballbottom = b.gety() + (double)constants.sizeBall/2;

        //上下边，左右边
        boolean leftAndright = (ballleft < x1 || ballright > x1 + (double) constants.size) && balltop > y1 && ballbottom < y1 + (double) constants.size ;

        boolean topAndbottom = (balltop < y1 || ballbottom > y1 + (double) constants.size) && ballleft > x1 && ballright < x1 + (double) constants.size;

        if(leftAndright){
            b.setSpeed(-b.getSpeedx(),b.getSpeedy());
        }
        else if(topAndbottom){
            b.setSpeed(b.getSpeedx(),-b.getSpeedy());
        }
        else{
            b.setSpeed(-b.getSpeedx(),-b.getSpeedy());
        }

    }

}
