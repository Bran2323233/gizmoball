package interFace;

import game.ball;

public abstract class barrier {

    //物体位置


    private int x;
    private int y;
    private int size = 45;

    public barrier(int x,int y){

        this.x = x;
        this.y = y;
    }

    //碰撞函数

    public abstract boolean isCollide(ball b);

    public abstract void collisionResult(ball b);

    public int getx(){
        return x;
    }

    public int gety(){
        return y;
    }

    public int getsize(){
        return size;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }
}
