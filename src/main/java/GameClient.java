import object.Direction;
import object.Tank;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.logging.Handler;
import java.awt.event.KeyAdapter;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHight;
    private Tank playerTank;
    private boolean stop;
    private boolean[] dirs=new boolean[4];
//    public static GameClient instance;
//
//    public static GameClient getInstance(){
//        return instance;
//    }

    GameClient(){
        //設定畫面大小
        this(800,600);
    }

    public GameClient(int screenWidth,int screenHight){
        this.screenWidth=screenWidth;
        this.screenHight=screenHight;
        this.setPreferredSize(new Dimension(screenWidth,screenHight));
        init();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop){
                   repaint();
                   try {
                       Thread.sleep(50);
                   } catch (InterruptedException e) {
                       e.printStackTrace();
                   }
                }
            }
        }).start();
    }

    private void init() {
        playerTank=new Tank(200,200, Direction.UP);
    }

    // 因為 extends JComponent所以可以使用paintComponent將JPG等圖變成PNG檔
    @Override
    protected void paintComponent(Graphics g) {
        //選擇顏色
        g.setColor(Color.GRAY);
        //填滿視窗
        g.fillRect(0,0,getWidth(),getHeight());
        playerTank.draw(g);
    }
    public void keyPress(KeyEvent e){
        boolean[] dirs=playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = true;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = true;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = true;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = true;
                break;
        }
    }
    public void keyReleased(KeyEvent e) {
        boolean[] dirs=playerTank.getDirs();
        switch (e.getKeyCode()){
            case KeyEvent.VK_UP:
                dirs[0]=false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1]=false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2]=false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3]=false;
                break;
        }

    }

}