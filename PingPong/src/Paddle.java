import java.awt.*;
import java.awt.event.*;
public class Paddle extends Rectangle{

    int id;
    int yVelocity;
    int paddle_speed= 10;

    Paddle(int x, int y, int WIDTH_OF_THE_PADDLE, int HEIGHT_OF_THE_PADDLE, int id){
        super(x, y,WIDTH_OF_THE_PADDLE, HEIGHT_OF_THE_PADDLE);
        this.id = id;
    }
    public void keyPressed(KeyEvent e){
        // Creating switch to examine the contents of the id variable
        switch(id) {
            case 1:
                if(e.getKeyCode()== KeyEvent.VK_W) {
                    setDirectionY(-paddle_speed);
                    move();
                }
                if(e.getKeyCode()== KeyEvent.VK_S) {
                    setDirectionY(paddle_speed);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()== KeyEvent.VK_UP) {
                    setDirectionY(-paddle_speed);
                    move();
                }
                if(e.getKeyCode()== KeyEvent.VK_DOWN) {
                    setDirectionY(paddle_speed);
                    move();
                }
                break;
        }
    }
    public void keyReleased(KeyEvent e){
        switch(id) {
            case 1:
                if(e.getKeyCode()== KeyEvent.VK_W) {
                    setDirectionY(0);
                    move();
                }
                if(e.getKeyCode()== KeyEvent.VK_S) {
                    setDirectionY(0);
                    move();
                }
                break;
            case 2:
                if(e.getKeyCode()== KeyEvent.VK_UP) {
                    setDirectionY(0);
                    move();
                }
                if(e.getKeyCode()== KeyEvent.VK_DOWN) {
                    setDirectionY(0);
                    move();
                }
                break;
        }
    }
    public void setDirectionY(int DirectionY){
        yVelocity = DirectionY;

    }
    public void move(){
        y = y + yVelocity;
    }
    public void draw(Graphics g){
        if(id==1)
            g.setColor(Color.green);
        else
            g.setColor(Color.blue);
        g.fillRect(x, y, width, height);
    }
}