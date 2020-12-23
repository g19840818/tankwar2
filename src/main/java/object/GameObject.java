package object;

import java.awt.*;

public abstract class GameObject {
    protected int x;
    protected int y;
    protected Image[] image;
    protected int height;
    protected int width;


    public GameObject(int x,int y,Image[] image){
        this.x=x;
        this.y=y;
        this.image=image;
        height=image[0].getHeight(null);
        width=image[0].getWidth(null);
    }
    public abstract void draw(Graphics g);
}
