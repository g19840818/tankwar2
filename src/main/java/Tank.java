import object.Direction;
import object.GameObject;

import javax.swing.*;
import java.awt.*;

public class Tank extends GameObject {
    private int speed;
    private Direction direction;
    private Tank playerTank;
    private boolean enemy;
    private boolean[] dirs = new boolean[4];

    public Tank(int x, int y, Direction direction,Image[] image){
        this(x,y,direction,false,image );
    }


    public Tank(int x, int y, Direction direction,boolean enemy,Image[] image) {
        super(x,y,image);
        this.direction = direction;
        speed = 20;
        this.enemy=enemy;
    }




    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Direction getDirection() {
        return direction;
    }

    public int getSpeed() {
        return speed;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public void move() {
        switch (direction) {
            case UP:
                y -= speed;
                break;
            case DOWN:
                y += speed;
                break;
            case LEFT:
                x -= speed;
                break;
            case RIGHT:
                x += speed;
                break;
            case UP_LEFT:
                y -= speed;
                x -= speed;
                break;
            case UP_RIGHT:
                y -= speed;
                x += speed;
                break;
            case DOWN_LEFT:
                y += speed;
                x -= speed;
                break;
            case DOWN_RIGHT:
                y += speed;
                x += speed;
                break;
        }
    }

    private void determineDirection() {
        if (dirs[0] && dirs[2] && !dirs[1] && !dirs[3]) direction = Direction.UP_LEFT;
        else if (dirs[0] && dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP_RIGHT;
        else if (dirs[1] && dirs[2] && !dirs[0] && !dirs[3]) direction = Direction.DOWN_LEFT;
        else if (dirs[1] && dirs[3] && !dirs[2] && !dirs[0]) direction = Direction.DOWN_RIGHT;
        else if (dirs[0] && !dirs[3] && !dirs[2] && !dirs[1]) direction = Direction.UP;
        else if (dirs[1] && !dirs[3] && !dirs[2] && !dirs[0]) direction = Direction.DOWN;
        else if (dirs[2] && !dirs[3] && !dirs[0] && !dirs[1]) direction = Direction.LEFT;
        else if (dirs[3] && !dirs[0] && !dirs[2] && !dirs[1]) direction = Direction.RIGHT;
    }

    public boolean[] getDirs() {
        return dirs;
    }

    public void draw(Graphics g) {
        if (!isStop()){
        determineDirection();
        move();}
        g.drawImage(image[direction.ordinal()], x, y, null);
    }

    private boolean isStop() {
        for (int i=0;i<dirs.length;i++){
            if (dirs[i]){
                return false;
            }
        }
        return true;
    }

}
