package view;

import constants.constants;
import game.model;
import listener.*;

import javax.swing.*;
import javax.swing.plaf.basic.BasicTabbedPaneUI;
import javax.swing.plaf.basic.BasicTreeUI;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class BuildFrame extends JPanel {

    private model model;
    private static JFrame buildFrame;
    private static JPanel buildPanel;
    private MouseListener mouseListener;
    private SaveListener saveListener;
    private LoadListener loadListener;
    private NewListener newListener;
    private AddCircleListener addcircleListener;
    private AddTriangleListener addtriangleListener;
    private AddRectangleListener addrectangleListener;
    private DeleteBarrierListener deleteBarrierListener;
    private RotateBarrierListener rotateBarrierListener;
    private ClearBoardListener clearBoardListener;
    private MoveBarrierListener moveBarrierListener;
    private AddBallListener addBallListener;
    private BuildToPlayListener buildToPlayListener;
    private AddFlipperListener addFlipperListener;
    private AddAbsorberListener addAbsorberListener;
    private MainFrame mainFrame;

    public BuildFrame(model m){

        this.model = m;
        mouseListener = new MouseListener(m);
        addcircleListener = new AddCircleListener(m);
        addrectangleListener = new AddRectangleListener(m);
        addtriangleListener = new AddTriangleListener(m);
        deleteBarrierListener = new DeleteBarrierListener(m);
        rotateBarrierListener = new RotateBarrierListener(m);
        clearBoardListener = new ClearBoardListener(m);
        moveBarrierListener = new MoveBarrierListener(m);
        buildToPlayListener = new BuildToPlayListener(m);
        addtriangleListener = new AddTriangleListener(m);
        addBallListener = new AddBallListener(m);
        addFlipperListener = new AddFlipperListener(m);
        addAbsorberListener = new AddAbsorberListener(m);
        saveListener = new SaveListener(m);
        loadListener = new LoadListener(m);
        //主页面
        Build();
        //菜单
        MenuBar();
        //模式切换
        Mode();
        //操作
        Operations();
        //设置Gizmos
        Gizmos();
        //BallEdit();
        MainFrame();
        makeFrameVisible();
    }



    public void Build(){
        buildPanel = new JPanel();
        buildFrame = new JFrame();
        buildPanel.setLayout(new GridLayout(14, 1));
        buildFrame.getContentPane().add(buildPanel,BorderLayout.WEST);
        buildFrame.setTitle("Gizmoball_BuildMode");
        buildFrame.setSize(800,800);
        buildFrame.setBackground(Color.black);
        buildFrame.setLocationRelativeTo(null);
        buildFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        buildFrame.setResizable(false);
    }

    public static void makeFrameVisible(){
        buildFrame.setVisible(true);
        buildFrame.pack();
    }

    public static void makeFrameInvisible(){
        buildFrame.dispose();
        buildFrame.pack();
    }

    public void MenuBar(){
        JMenuBar MenuBar = new JMenuBar();
        buildFrame.setJMenuBar(MenuBar);

        JMenu MenuOptions = new JMenu("File");
        MenuBar.add(MenuOptions);

        JMenuItem nnew = new JMenuItem("new");
        MenuOptions.add(nnew);
        nnew.addActionListener(newListener);

        JMenuItem open = new JMenuItem("Open");
        MenuOptions.add(open);
        open.addActionListener(loadListener);

        JMenuItem save = new JMenuItem("Save");
        MenuOptions.add(save);
        save.addActionListener(saveListener);
    }

    public void Mode(){
        JButton playMode = new JButton("play");
        buildPanel.add(playMode);
        playMode.addActionListener(buildToPlayListener);
        buildFrame.getContentPane().add(buildPanel,BorderLayout.EAST);
    }

    //小球加入到gizmo中
    public void Gizmos(){

        JButton ball = new JButton("Ball");
        buildPanel.add(ball);
        ball.addActionListener(addBallListener);

        JButton circle = new JButton("Circle");
        buildPanel.add(circle);
        circle.addActionListener(addcircleListener);

        JButton triangle = new JButton("Triangle");
        buildPanel.add(triangle);
        triangle.addActionListener(addtriangleListener);

        JButton square = new JButton("Square");
        buildPanel.add(square);
        square.addActionListener(addrectangleListener);

        JButton Flipper = new JButton("Flipper");
        buildPanel.add(Flipper);
        Flipper.addActionListener(addFlipperListener);

        JButton absorber = new JButton("Absorber");
        buildPanel.add(absorber);
        absorber.addActionListener(addAbsorberListener);

    }

    public void Operations(){
        JButton move = new JButton("Move");
        buildPanel.add(move);
        move.addActionListener(moveBarrierListener);

        JButton rotate = new JButton("Rotate");
        buildPanel.add(rotate);
        rotate.addActionListener(rotateBarrierListener);

        JButton delete = new JButton("Delete");
        buildPanel.add(delete);
        delete.addActionListener(deleteBarrierListener);

        JButton clear = new JButton("Clear Board");
        buildPanel.add(clear);
       clear.addActionListener(clearBoardListener);

    }

    public void MainFrame(){
        mainFrame = new MainFrame(constants.rowBig,constants.collomnBig,model);
        mainFrame.addMouseListener(mouseListener);
        buildFrame.getContentPane().add(mainFrame,BorderLayout.CENTER);
        buildFrame.pack();
    }
}
