package game;

import constants.constants;
import interFace.barrier;
import physics.Circle;
import physics.Geometry;
import physics.LineSegment;
import physics.Vect;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static constants.constants.collomnBig;
import static constants.constants.freeSize;
import static constants.constants.rowBig;
import static constants.constants.size;

public class model {

    //数组维护物体位置

    private List<circle> allCircle;
    private List<rectangle> allRectangle;
    private List<triangle> allTriangle;
    private List<ball> allBall;
    private List<flipper> allFlipper;
    private List<absorber> allAbsorber;
    private int[][] allbarrier;
    private Walls walls;

    //记录操作的变量
    private boolean rectangleAdder;
    private boolean circleAdder;
    private boolean triangleAdder;
    private boolean absorberAdder;
    private boolean FlipperAdder;
    private boolean ballAdder;
    private boolean deleteMode;
    private boolean placementMode;
    private boolean selectMode;
    private boolean moveMode;
    private boolean rotateMode;

    //记录吸取和挡板
    private boolean isFlipper;
    private boolean isAbsorber;
    //move操作中物体上一次的位置

    private int lastx;
    private int lasty;

    public model(){
        allCircle = new ArrayList<circle>();
        allRectangle = new ArrayList<rectangle>();
        allTriangle = new ArrayList<triangle>();
        allBall = new ArrayList<ball>();
        allFlipper = new ArrayList<flipper>();
        allAbsorber = new ArrayList<absorber>();
        allbarrier = new int [rowBig + freeSize][collomnBig + freeSize];
        walls = new Walls(0,0,20,20);
        rectangleAdder = false;
        circleAdder = false;
        triangleAdder = false;
        absorberAdder = false;
        FlipperAdder = false;
        ballAdder = false;
        deleteMode = false;
        placementMode = true;
        selectMode = false;
        moveMode = false;
        rotateMode = false;
        isAbsorber = false;
        isFlipper = false;
        lastx = -1;
        lasty = -1;

        init();
    }

    //初始化
    public void init(){

        for(int i = 0 ;i < rowBig ;i++ ){
            for(int j = 0  ;j < collomnBig ;j++){
                allbarrier[i][j] = 0;
            }
        }
    }

    //添加挡板
    public  void addFlipper(){

        if(allFlipper.size() != 0){
            return;
        }
        for(int i = constants.rowBig/4 ;i < (constants.rowBig/4)*3 ;i += constants.size){
            if(isBarrier(i,constants.collomnBig - constants.size)){
                return;
            }
        }
        allFlipper.add(new flipper(constants.rowBig/4,constants.collomnBig - constants.size));
        for(int i = constants.rowBig/4 ;i < constants.rowBig/4*3 ;i += constants.size){
            allbarrier[i][constants.collomnBig - constants.size] = 5;
        }
    }

    //添加小球

    public  void addBall(int x,int y){
        if(!isBarrier(x,y) && allBall.size() == 0){
            allBall.add(new ball((double)(x + constants.size/2),(double)(y + constants.size/2)));
            allbarrier[x][y] = 4;
        }
    }

    //添加吸取器

    public void addAbsorber(int x,int y){

        if(!isBarrier(x,y)){
            absorber Absorber = new absorber(x,y);
            allAbsorber.add(Absorber);
            allbarrier[x][y] = 6;
        }
    }

    //添加圆物体

    public void addCircle(int x,int y){

        if(!isBarrier(x,y)){
            circle Circle = new circle(x,y);
            allCircle.add(Circle);
            allbarrier[x][y] = 1;
        }
    }

    //添加正方形

    public  void addRectangle(int x,int y){

        if(!isBarrier(x,y)){
            rectangle Rectangle = new rectangle(x,y);
            allRectangle.add(Rectangle);
            allbarrier[x][y] = 2;
        }

    }

    //添加三角形

    public void addTriangle(int x,int y){

        if(!isBarrier(x,y)){
            triangle Triangle = new triangle(x,y);
            allTriangle.add(Triangle);
            allbarrier[x][y] = 3;
        }

    }

    //删除操作

    public void deleteBarrier(int x,int y){

        switch(allbarrier[x][y]){
            case 0:
                return;
            case 1:
                this.deleteCircleInList(x,y);
                break;
            case 2:
                this.deleteRectangleInList(x,y);
                break;
            case 3:
               this.deleteTriangleInList(x,y);
               break;
            case 4:
                this.resetBall();
                break;
            case 5:
                this.deleteFlipper();
                break;
            case 6:
                this.deleteAbsorberInList(x,y);
                break;
                default:
                    JOptionPane.showInternalMessageDialog(null, "Barrier类型不为1-6，出错了");

        }

        allbarrier[x][y] = 0;
    }

    //是否存在物体

    public boolean isBarrier(int x,int y ){

        if(allbarrier[x][y] == 0){
            return false;
        }
        return true;
    }

    //三角形旋转

    public void triangleRotate(int x,int y){

        if(isBarrier(x,y) && allbarrier[x][y] == 3){
            for(triangle i:allTriangle){
                if(i.getx() == x && i.gety() == y){
                    i.changeState();
                    break;
                }
            }
        }
    }

    //记录物体上一次的位置

    public void recordLastLocation(int x,int y){

        if(isBarrier(x,y)){
            lastx = x;
            lasty = y;
            this.setPatternState(3);
        }

    }

    //移动物体

    public void moveBarrier(int x,int y){
        if(!isBarrier(x,y)){
            switch(allbarrier[lastx][lasty]){
                case 1:
                    this.deleteCircleInList(lastx,lasty);
                    allbarrier[lastx][lasty] = 0;
                    allbarrier[x][y] = 1;
                    allCircle.add(new circle(x,y));
                    break;
                case 2:
                    this.deleteRectangleInList(lastx,lasty);
                    allbarrier[lastx][lasty] = 0;
                    allbarrier[x][y] = 2;
                    allRectangle.add(new rectangle(x,y));
                    break;
                case 3:
                    allbarrier[lastx][lasty] = 0;
                    allbarrier[x][y] = 3;
                    triangle Triangle = new triangle(x,y);
                    for(triangle i: allTriangle){
                        if(i.getx() == lastx && i.gety() == lasty){
                            Triangle.setState(i.getState());
                            allTriangle.remove(i);
                            break;
                        }
                    }
                    allTriangle.add(Triangle);
                    break;
                case 4:
                    allbarrier[lastx][lasty] = 0;
                    allbarrier[x][y] = 4;
                    allBall.clear();
                    allBall.add(new ball((double)(x + constants.size/2),(double)(y + constants.size/2)));
                    break;
                case 5:
                    break;
                case 6:
                    this.deleteAbsorberInList(lastx,lasty);
                    allbarrier[lastx][lasty] = 0;
                    allbarrier[x][y] = 6;
                    allAbsorber.add(new absorber(x,y));
                    break;
                    default:
                        JOptionPane.showInternalMessageDialog(null, "allbarrier里不为0-6，出错了");
            }
            this.setPatternState(2);
        }
        else{
            this.recordLastLocation(x,y);
        }
    }
    //设置模式状态

    //重置物体先前位置

    public void resetLastLocation(){

        lastx = -1;
        lasty = -1;
    }
    public void setPatternState(int x){
        switch(x){
            case 0 :
                deleteMode = true;
                placementMode = false;
                selectMode = false;
                moveMode = false;
                rotateMode = false;
                break;
            case 1:
                deleteMode = false;
                placementMode = true;
                selectMode = false;
                moveMode = false;
                rotateMode = false;
                break;
            case 2:
                deleteMode = false;
                placementMode = false;
                selectMode = true;
                moveMode = false;
                rotateMode = false;
                break;
            case 3:
                deleteMode = false;
                placementMode = false;
                selectMode = false;
                moveMode = true;
                rotateMode = false;
                break;
            case 4:
                deleteMode = false;
                placementMode = false;
                selectMode = false;
                moveMode = false;
                rotateMode = true;
                break;
                default:JOptionPane.showInternalMessageDialog(null, "只有0-4的5种模式，请修改");
        }
    }
    //设置添加什么物体状态，记录操作

    public void setBarrierState(int x) {
        switch(x) {
            case 0:
                this.rectangleAdder = false;
                this.circleAdder = true;
                this.triangleAdder = false;
                this.absorberAdder = false;
                this.FlipperAdder = false;
                this.ballAdder = false;
                break;
            case 1:
                this.circleAdder = false;
                this.rectangleAdder = false;
                this.triangleAdder = true;
                this.absorberAdder = false;
                this.FlipperAdder = false;
                this.ballAdder = false;
                break;
            case 2:
                this.triangleAdder = false;
                this.rectangleAdder = true;
                this.circleAdder = false;
                this.absorberAdder = false;
                this.FlipperAdder = false;
                this.ballAdder = false;
                break;
            case 3:
                this.triangleAdder = false;
                this.rectangleAdder = false;
                this.circleAdder = false;
                this.absorberAdder = false;
                this.FlipperAdder = true;
                this.ballAdder = false;
                break;
            case 4:
                this.triangleAdder = false;
                this.rectangleAdder = false;
                this.circleAdder = false;
                this.absorberAdder = false;
                this.FlipperAdder = false;
                this.ballAdder = false;
                break;
            case 5:
                this.triangleAdder = false;
                this.rectangleAdder = false;
                this.circleAdder = false;
                this.absorberAdder = true;
                this.FlipperAdder = false;
                this.ballAdder = false;
                break;
            case 6:
                this.triangleAdder = false;
                this.rectangleAdder = false;
                this.circleAdder = false;
                this.absorberAdder = false;
                this.FlipperAdder = false;
                this.ballAdder = true;
                break;
                default:JOptionPane.showInternalMessageDialog(null, "只有0-7的八种添加物体状态，请修改");
        }
    }

    //添加物体到List中

    public void userPlaceBarrier(int x, int y) {
        if(isBarrier(x,y)){
            return;
        }
        if (this.rectangleAdder || this.circleAdder || this.triangleAdder || this.absorberAdder || this.ballAdder) {
            if (this.circleAdder) {
                this.addCircle(x,y);
            } else if (this.rectangleAdder) {
                this.addRectangle(x,y);
            } else if (this.triangleAdder) {
                this.addTriangle(x,y);
            } else if (this.ballAdder) {
                this.addBall(x, y);
            }else  if(this.absorberAdder){
                this.addAbsorber(x,y);
            }
//            } else if (this.rFlipperAdder) {
//                this.addRFlipper((String)null, x, y, (Color)null);
//            } else if (this.absorberAdder) {
//                this.addAbsorber((String)null, x, y, x + 1.0D, y + 1.0D, (Color)null);
//            }
        }

    }

    //清除操作

    public void clearBoard(){

        allCircle.clear();
        allRectangle.clear();
        allTriangle.clear();
        allBall.clear();
        allFlipper.clear();
        allAbsorber.clear();
        for(int i = 0 ;i < rowBig ;i++ ){
            for(int j = 0  ;j < collomnBig ;j++){
                allbarrier[i][j] = 0;
            }
        }
    }

    //找到吸收器删除

    public  void deleteAbsorberInList(int x,int y){
        for(absorber i:allAbsorber){
            if(i.getx() == x && i.gety() == y){
                allAbsorber.remove(i);
                return;
            }
        }
    }

    //找到圆形删除

    public void deleteCircleInList(int x,int y){

        for(circle i:allCircle){
            if(i.getx() == x && i.gety() == y){
                allCircle.remove(i);
                return;
            }
        }
    }

    //找到正方形删除

    public void deleteRectangleInList(int x,int y){
        for(rectangle i:allRectangle){
            if(i.getx() == x && i.gety() == y){
                allRectangle.remove(i);
                return;
            }
        }

    }

    //找到三角形删除

    public void deleteTriangleInList(int x,int y){
        for(triangle i:allTriangle){
            if(i.getx() == x && i.gety() == y){
                allTriangle.remove(i);
                return;
            }
        }
    }

    //删除挡板

    public void deleteFlipper(){

        for(int i = constants.rowBig/4 ;i < constants.rowBig/4*3 ;i += constants.size){
            allbarrier[i][constants.collomnBig - constants.size] = 0;
        }
        allFlipper.clear();
    }

    //球移动
    public void moveBall() {

        if(allBall.size() != 0){
            ball Ball = allBall.get(0);
            Collisions cd = timeUntilCollision();
            double tuc = cd.getTuc();
            if(tuc > 0.01){
                Ball.move(0.01);
            }
            else{
                Ball.move(tuc);
                if(isFlipper == true){
                    Ball.setSpeed(Ball.getSpeedx() * 2,Ball.getSpeedy() * 2);
                    isFlipper = false;
                }
                Ball.setSpeed(cd.getVelo().x(),cd.getVelo().y());
                if(isAbsorber == true){
                    resetBall();
                    return;
                }
            }
        }
    }

    //移动挡板

    public void moveFlipper(int flag){
        int x = allFlipper.get(0).getx();
        int y = constants.collomnBig - constants.size;
        if(flag == 1){
            if(x > 0){
                allFlipper.get(0).setX(x - constants.size);
                allFlipper.get(0).setMyx(x - constants.size);
                allbarrier[x - constants.size][y] = 5;
                allbarrier[x + constants.rowBig/2 - constants.size][y] = 0;
            }
        }
        else if(flag == 2){
            if(x < constants.rowBig/2){
                allFlipper.get(0).setX(x + constants.size);
                allFlipper.get(0).setMyx(x + constants.size);
                allbarrier[x + constants.rowBig/2][y] = 5;
                allbarrier[x][y] = 0;
            }
        }
    }


    public List<circle> getAllCircle() {
        return allCircle;
    }

    public List<rectangle> getAllRectangle() {
        return allRectangle;
    }

    public List<triangle> getAllTriangle() {
        return allTriangle;
    }

    public List<flipper> getAllFlipper() {
        return allFlipper;
    }

    public List<ball> getAllBall() {
        return allBall;
    }

    public List<absorber> getAllAbsorber(){return  allAbsorber;}

    public void setAllbarrier(int x,int y){

        allbarrier[x][y] = 4;
    }
    public void resetBall() {
        allBall.clear();
    }

    public void backFlipper(){
        if(allFlipper.size() != 0){
            flipper Flipper = allFlipper.get(0);
            int x = Flipper.getx();
            for(int i = x ;i < x + constants.rowBig ;i += constants.size){
                allbarrier[i][constants.collomnBig - constants.size] = 0;
            }
            Flipper.setX(constants.rowBig/4);
            Flipper.setMyx(constants.rowBig/4);
            x = Flipper.getx();
            for(int i = x ;i < x + constants.rowBig ;i += constants.size){
                allbarrier[i][constants.collomnBig - constants.size] = 5;
            }
        }

    }

    public void backBall(double x,double y){
        ball Ball = new ball(x,y);
        Ball.setSpeed(3,0);
        allBall.clear();
        allBall.add(Ball);
        isAbsorber = false;
    }
    private Collisions timeUntilCollision(){
        // Find Time Until Collision and also, if there is a collision, the new
        // speed vector.
        // Create a physics.Circle from Ball
        ball Ball = allBall.get(0);
        Circle ballCircle = new Circle(Ball.getx(),Ball.gety(),(double)constants.sizeBall/2);
        Vect ballVelocity = new Vect(Ball.getSpeedx(),Ball.getSpeedy());
        Vect newVelo = new Vect(0, 0);

        // Now find shortest time to hit a vertical line or a wall line
        double shortestTime = Double.MAX_VALUE;
        double time = 0.0;

        // Time to collide with 4 walls
        for (LineSegment line : walls.getLineSegments()) {
            time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
            if (time < shortestTime) {
                shortestTime = time;
                newVelo = Geometry.reflectWall(line, ballVelocity, 1);
                isAbsorber = false;
                isFlipper = false;
            }
        }
        for(absorber i:allAbsorber){
            for (LineSegment line : i.getLineSegments()) {
                time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectWall(line, ballVelocity, 0);
                    isAbsorber = true;
                    isFlipper = false;
                }
            }
            for(Circle circle : i.getCircles()){
                time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 0);
                    isAbsorber = true;
                    isFlipper = false;
                }
            }
        }
        for(circle i:allCircle){
            for (LineSegment line : i.getLineSegments()) {
                time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectWall(line, ballVelocity, 1);
                    isAbsorber = false;
                    isFlipper = false;
                }
            }
            for(Circle circle : i.getCircles()){
                time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
                    isAbsorber = false;
                    isFlipper = false;
                }
            }
        }
        for(rectangle i:allRectangle) {
            for (LineSegment line : i.getLineSegments()) {
                time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectWall(line, ballVelocity, 1);
                    isAbsorber = false;
                    isFlipper = false;
                }
            }
            for(Circle circle : i.getCircles()){
                time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
                    isAbsorber = false;
                    isFlipper = false;
                }
            }
        }

        for(triangle i:allTriangle) {
            for (LineSegment line : i.getLineSegments()) {
                time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectWall(line, ballVelocity, 1);
                    isAbsorber = false;
                    isFlipper = false;
                }
            }
            for(Circle circle : i.getCircles()){
                time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
                    isAbsorber = false;
                    isFlipper = false;
                }
            }
        }

        for(flipper i:allFlipper) {
            for (LineSegment line : i.getLineSegments()) {
                time = Geometry.timeUntilWallCollision(line, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectWall(line, ballVelocity, 1);
                    isAbsorber = false;
                    isFlipper = true;
                }
            }
            for(Circle circle : i.getCircles()){
                time = Geometry.timeUntilCircleCollision(circle, ballCircle, ballVelocity);
                if (time < shortestTime) {
                    shortestTime = time;
                    newVelo = Geometry.reflectCircle(circle.getCenter(), ballCircle.getCenter(), ballVelocity, 1);
                    isAbsorber = false;
                    isFlipper = true;
                }
            }
        }
        return new Collisions(shortestTime, newVelo);
    }

    public boolean isDeleteMode() {
        return deleteMode;
    }

    public boolean isPlacementMode() {
        return placementMode;
    }

    public boolean isSelectMode() {
        return selectMode;
    }

    public boolean isMoveMode() {
        return moveMode;
    }

    public boolean isRotateMode() {
        return rotateMode;
    }

    public boolean isFlipperAdder() {
        return FlipperAdder;
    }
}