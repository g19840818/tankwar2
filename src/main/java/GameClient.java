import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHight;
    private Tank playerTank;
    private List<Tank> enemyTanks = new ArrayList<>();
    private List<Wall> walls = new ArrayList<>();
    private boolean stop;
    private boolean[] dirs = new boolean[4];

    private ArrayList<GameObject> objects=new ArrayList<>();


//    public static GameClient instance;
//
//    public static GameClient getInstance(){
//        return instance;
//    }

    GameClient() {
        //設定畫面大小
        this(1000, 1000);
    }

    public GameClient(int screenWidth, int screenHight) {
        this.screenWidth = screenWidth;
        this.screenHight = screenHight;
        this.setPreferredSize(new Dimension(screenWidth, screenHight));
        init();
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (!stop) {
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
        Image[] brickImage={Tools.getImage("brick.png")};
        Image[] iTankImage=new Image[8];
        Image[] eTankImage=new Image[8];
        String[] sub={"U.png","D.png","L.png","R.png","RU.png","LU.png","RD.png","LD.png"};


        for (int i=0;i<iTankImage.length;i++){
            iTankImage[i]=Tools.getImage("itank"+sub[i]);
            eTankImage[i]=Tools.getImage("etank"+sub[i]);
        }

        playerTank = new Tank(100, 100, Direction.UP,iTankImage);
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 4; j++) {
                enemyTanks.add(new Tank(300 + 100 * j, 500 + 100 * i, Direction.UP,true, eTankImage));
            }
        }
        Wall[] walls = {
                new Wall(200, 250, true, 18,brickImage),
                new Wall(150, 350, false, 15,brickImage),
                new Wall(800, 350, false, 15,brickImage)
        };
        this.walls.addAll(Arrays.asList(walls));
        objects.addAll(this.walls);
        objects.add(playerTank);
        objects.addAll(enemyTanks);
    }

    // 因為 extends JComponent所以可以使用paintComponent將JPG等圖變成PNG檔
    @Override
    protected void paintComponent(Graphics g) {

        //選擇顏色
        g.setColor(Color.GRAY);
        //填滿視窗
        g.fillRect(0, 0, getWidth(), getHeight());
        playerTank.draw(g);
        for (Tank tank : enemyTanks) {
            tank.draw(g);
        }
        for (Wall wall : walls) {
            wall.draw(g);
        }
    }

    public void keyPress(KeyEvent e) {
        boolean[] dirs = playerTank.getDirs();
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
        boolean[] dirs = playerTank.getDirs();
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                dirs[0] = false;
                break;
            case KeyEvent.VK_DOWN:
                dirs[1] = false;
                break;
            case KeyEvent.VK_LEFT:
                dirs[2] = false;
                break;
            case KeyEvent.VK_RIGHT:
                dirs[3] = false;
                break;
        }

    }

}