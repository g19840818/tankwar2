import javax.swing.*;
import java.awt.*;

public class GameClient extends JComponent {
    private int screenWidth;
    private int screenHight;



    GameClient(){
        //設定畫面大小
        this(800,600);
    }

    public GameClient(int screenWidth,int screenHight){
        this.screenWidth=screenWidth;
        this.screenHight=screenHight;
        this.setPreferredSize(new Dimension(screenWidth,screenHight));
    }
    // 因為 extends JComponent所以可以使用paintComponent將JPG等圖變成PNG檔
    @Override
    protected void paintComponent(Graphics g) {
         g.drawImage(new ImageIcon("assets/images/itankD.png").getImage(),400,100,null);
    }
}
