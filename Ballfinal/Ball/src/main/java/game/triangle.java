package game;

import constants.constants;
import interFace.barrier;
import physics.Circle;
import physics.LineSegment;

import java.util.ArrayList;
import java.util.List;

public class triangle extends barrier {

    private double myx, myy;
    private int state;
    List<LineSegment> lineSegments;
    List<Circle> circles;

    public triangle(int x, int y) {
        super(x, y);
        this.state = 0;
        myx = (double) x;
        myy = (double) y;
    }


    @Override
    public boolean isCollide(ball b) {

        if(b.getx() - this.getx() > constants.smallFloat && this.getx() + this.getsize() - b.getx() > constants.smallFloat && b.gety() - this.gety() > constants.smallFloat && this.gety() + this.getsize() - b.gety() > constants.smallFloat && b.gety() - this.gety() - b.getx() + this.getx() > constants.smallFloat){
            return true;
        }
        return false;
    }

    @Override
    public void collisionResult(ball b) {

//        boolean left = this.getx() < b.getLastx() && b.getSpeedx() > 0 && b.getLasty() > this.gety() && b.getLasty() < this.gety() + this.getsize();
//        boolean bottom = this.gety() > b.getLastx() && b.getSpeedy() < 0 && b.getLastx() > this.getx() && b.getLastx() < this.getx() + this.getsize();
//        boolean Hypotenuse = false;
//
//        if(left){
//            b.setSpeed(-b.getSpeedx(),b.getSpeedy());
//        }
//        else if(bottom){
//            b.setSpeed(b.getSpeedx(),-b.getSpeedy());
//        }
//        else if(Hypotenuse){
//            if(Math.abs(b.getx()) > 0){
//                b.setSpeed(Math.abs(b.getSpeedy()),Math.abs(b.getSpeedx()));
//            }
//            else{
//                b.setSpeed(Math.abs(b.getSpeedy()),-Math.abs(b.getSpeedx()));
//            }
//        }
    }

    //返回三角形状态

    public int getState(){

        return state;
    }

    //设置三角形状态

    public  void changeState(){

        this.state = (this.state + 1)%4;
    }

    public void setState(int state) {
        this.state = state;
    }

    public List<LineSegment> getLineSegments() {
        lineSegments = new ArrayList<LineSegment>();

            if (state == 0) {
                lineSegments.add(new LineSegment(myx, myy, myx, myy + constants.size));
                lineSegments.add(new LineSegment(myx, myy, myx + constants.size, myy + constants.size));
                lineSegments.add(new LineSegment(myx, myy+ constants.size , myx + constants.size, myy+ constants.size));
            } else if (state == 1) {
                lineSegments.add(new LineSegment(myx, myy, myx, myy + constants.size));
                lineSegments.add(new LineSegment(myx, myy, myx + constants.size, myy ));
                lineSegments.add(new LineSegment(myx, myy+ constants.size , myx + constants.size, myy));
            } else if (state == 2) {
                lineSegments.add(new LineSegment(myx, myy, myx+ constants.size, myy ));
                lineSegments.add(new LineSegment(myx, myy, myx + constants.size, myy + constants.size));
                lineSegments.add(new LineSegment(myx+ constants.size, myy , myx + constants.size, myy+ constants.size));
            } else if (state == 3) {
                lineSegments.add(new LineSegment(myx+ constants.size, myy, myx+ constants.size, myy + constants.size));
                lineSegments.add(new LineSegment(myx+ constants.size, myy, myx , myy + constants.size));
                lineSegments.add(new LineSegment(myx, myy+ constants.size , myx + constants.size, myy+ constants.size));
            }

        return lineSegments;
    }

    public List<Circle> getCircles() {
        circles = new ArrayList<Circle>();
            if (state == 0) {
                circles.add(new Circle(myx, myy, 0));
                circles.add(new Circle(myx + constants.size, myy + constants.size, 0));
                circles.add(new Circle(myx, myy + constants.size, 0));
            } else if (state == 1) {
                circles.add(new Circle(myx, myy, 0));
                circles.add(new Circle(myx + constants.size, myy, 0));
                circles.add(new Circle(myx, myy + constants.size, 0));
            } else if (state == 2) {
                circles.add(new Circle(myx, myy, 0));
                circles.add(new Circle(myx + constants.size, myy, 0));
                circles.add(new Circle(myx + constants.size, myy + constants.size, 0));
            } else if (state == 3) {
                circles.add(new Circle(myx + constants.size, myy, 0));
                circles.add(new Circle(myx + constants.size, myy + constants.size, 0));
                circles.add(new Circle(myx, myy + constants.size, 0));
            }
        return circles;
    }
}
