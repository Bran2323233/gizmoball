package listener;

import game.ball;
import game.model;
import view.BuildFrame;
import view.PlayFrame;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.concurrent.*;

public class PlayToBuildListener extends AddKeyListener implements ActionListener {

    private int count;
    private model model;
   // private ScheduledThreadPoolExecutor sec;
    private ball Ball;
    private ScheduledExecutorService service;

    private ScheduledExecutorTest test;
    public PlayToBuildListener(game.model m){
        super(m);
        model = m;
        service = Executors.newScheduledThreadPool(10);
        test = new ScheduledExecutorTest();
        count = 0;
       // sec = new ScheduledThreadPoolExecutor(10);
       // sec.scheduleAtFixedRate(new Runnable() {
           // @Override
           // public void run() {
             //   model.moveBall();
          //  }
       // }, 10, 10, TimeUnit.MILLISECONDS);
    }

    public class ScheduledExecutorTest implements Runnable{

        @Override
        public void run() {
            model.moveBall();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e){
//        if(e.getSource() == service){
//        }else{
            String s = e.getActionCommand();
            if ("Build".equals(s)) {
                count = 0;
                model.backBall(Ball.getx(),Ball.gety());
                model.backFlipper();
                service.shutdownNow();
                BuildFrame.makeFrameVisible();
                PlayFrame.makePlayFrameInvisible();

                //timer.stop();
            } else if("Stop".equals(s) && count != 0){
                count = 1;
                service.shutdownNow();
            } else if ("Start".equals(s) && count == 0) {
                Ball = new ball(model.getAllBall().get(0).getx(),model.getAllBall().get(0).gety());
                count = 2;
                long initialDelay = 1;
                long period = 1;
                service.scheduleAtFixedRate(test,initialDelay,period,TimeUnit.MILLISECONDS);
            }else if("Start".equals(s) && count == 1){
                count = 2;
                long initialDelay = 1;
                long period = 1;
                //test = new ScheduledExecutorTest();
                service = Executors.newScheduledThreadPool(10);
                service.scheduleAtFixedRate(test,initialDelay,period,TimeUnit.MILLISECONDS);
            }
//        }
    }
}
