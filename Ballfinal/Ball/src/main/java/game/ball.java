package game;

import constants.constants;

public class ball {

    //横纵坐标

    private double x;
    private double y;

    //大小

    private int size;

    // 速度

    private double speedx;
    private double speedy;

    public ball(double x,double y){
        this.x = x;
        this.y = y;
        this.size = constants.sizeBall;
        this.speedx = 0;
        this.speedy = 0;
    }

    //返回横纵坐标

    public double getx(){
        return x;
    }

    public double gety(){
        return y;
    }

    //返回横纵坐标速度

    public double getSpeedx(){
        return speedx;
    }

    public  double getSpeedy(){
        return speedy;
    }

//    public int getsize(){
//        return size;
//    }

    //球出边界

//    public boolean ballIsOut(double x,double y){
//        if(x < 0 || y < 0){
//            return true;
//        }
//        if(x > constants.rowBig || y > constants.collomnBig){
//            return true;
//        }
//        return false;
//    }

    //球移动

    public void move(double time){
        this.x += this.speedx * time ;
        this.y += this.speedy * time ;
        //this.speedx += time * 1;
        this.speedy += time * 3;
    }

    //设置球的速度

    public void setSpeed(double speedx,double speedy){

        this.speedx = speedx;
        this.speedy = speedy;
    }

    public void setX(double x) {
        this.x = x;
    }

    public void setY(double y) {
        this.y = y;
    }
}
