import java.awt.*;
import java.util.*;
public class Game_Ball extends Rectangle{

    Random random;
    int XVelocity;
    int YVelocity;
    int ball_speed = 3;

    Game_Ball(int x, int y, int width, int height){
        super(x, y, width, height);
        random = new Random();
        int randomDirectionX = random.nextInt(2);
        if(randomDirectionX == 0)
            randomDirectionX--;
        setDirectionX(randomDirectionX * ball_speed);
        int randomDirectionY = random.nextInt(2);
        if(randomDirectionY == 0)
            randomDirectionY--;
        setDirectionY(randomDirectionY * ball_speed);

    }

    public void setDirectionX(int randomDirectionX){
        XVelocity = randomDirectionX;
    }
    public void setDirectionY(int randomDirectionY){
        YVelocity = randomDirectionY;
    }
    public void move(){
        x += XVelocity;
        y+= YVelocity;
    }
    public void draw(Graphics g){
        g.setColor(Color.yellow);
        g.fillOval(x, y, width, height);
    }
}
