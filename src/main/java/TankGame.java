import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class TankGame {
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        GameClient gameClient=new GameClient(1000,800);
        //將GameClient物件進行裝載(使用JFrame.add方法)
        frame.add(gameClient);
        frame.setTitle("坦克大戰");
        //置中顯示
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        //完整關閉視窗功能(視窗左上角出現X,不會殘留記憶體,會確實釋放記憶體)
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        gameClient.repaint();


        //按鍵偵測
        frame.addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                gameClient.keyPress(e);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                gameClient.keyReleased(e);
            }
        });
    }
}