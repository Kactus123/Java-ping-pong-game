import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
public class Panel extends JPanel implements Runnable{

    //Using static to share the width_of_the_game as well as other values with all others and final to not accidently modify it, without them having individual value
    static final int WIDTH_OF_THE_GAME = 800;
    static final int HEIGHT_OF_THE_GAME = (int) (WIDTH_OF_THE_GAME * (0.55));
    static final Dimension SIZE_OF_THE_SCREEN = new Dimension(WIDTH_OF_THE_GAME, HEIGHT_OF_THE_GAME);
    static final int BALL_RADIUS = 30;
    static final int WIDTH_OF_THE_PADDLE = 40;
    static final int HEIGHT_OF_THE_PADDLE = 120;
    Thread gameThread;
    Image image;
    Graphics graphics;
    Random random;
    Paddle paddle;
    Paddle paddle2;
    Game_Ball game_ball;
    Score_system score_system;
    boolean gameOver;
    Panel(String first_player_name, String second_player_name) {
        newPaddle();
        new_Game_Ball();
        score_system = new Score_system(WIDTH_OF_THE_GAME, HEIGHT_OF_THE_GAME, first_player_name, second_player_name);
        this.setFocusable(true);
        this.addKeyListener(new action_listener());
        this.setPreferredSize(SIZE_OF_THE_SCREEN);
        gameOver = false; // set initial value of gameOver to false

        gameThread = new Thread(this);
        gameThread.start();
    }
    public void new_Game_Ball(){
        random = new Random();
        //Spawning the ball in the middle of the window
        game_ball = new Game_Ball((WIDTH_OF_THE_GAME/2) - (BALL_RADIUS/2), random.nextInt(HEIGHT_OF_THE_GAME - BALL_RADIUS), BALL_RADIUS, BALL_RADIUS);

    }
    public void newPaddle(){
        //Setting up the position od the paddles, positioning them on the seperate sites bu deviding the width and height of the window by 2
        paddle = new Paddle(0,(HEIGHT_OF_THE_GAME/2)-(HEIGHT_OF_THE_PADDLE/2), WIDTH_OF_THE_PADDLE, HEIGHT_OF_THE_PADDLE, 1);
        paddle2 = new Paddle(WIDTH_OF_THE_GAME- WIDTH_OF_THE_PADDLE,(HEIGHT_OF_THE_GAME/2)-(HEIGHT_OF_THE_PADDLE/2), WIDTH_OF_THE_PADDLE, HEIGHT_OF_THE_PADDLE, 2);
    }
    public void paint(Graphics g){
        image = createImage(getWidth(), getHeight());
        graphics = image.getGraphics();
        draw(graphics);
        g.drawImage(image,0,0, this);
    }
    public void draw(Graphics g){
        paddle.draw(g);
        paddle2.draw(g);
        game_ball.draw(g);
        score_system.draw(g);

        // Add restart and new game buttons if a player has won
        if (score_system.first_player_score >= 5 || score_system.second_player_score >= 5) {
            if (!gameOver) { // add this check to spawn only one window
                gameOver = true; // set the flag
                JFrame frame = new JFrame("Game Over");
                JPanel panel = new JPanel();
                panel.setLayout(new GridLayout(2, 1));

                // Add restart button
                JButton restartButton = new JButton("Restart");
                restartButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Reset game scores and objects
                        score_system.first_player_score = 0;
                        score_system.second_player_score = 0;
                        newPaddle();
                        new_Game_Ball();
                        frame.dispose();
                        gameOver = false; // reset the flag
                    }
                });
                panel.add(restartButton); // Add restart button to panel

                // Add new game button
                JButton newGameButton = new JButton("New Game");
                newGameButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        // Start a new game
                        score_system.first_player_score = 0;
                        score_system.second_player_score = 0;
                        newPaddle();
                        new_Game_Ball();
                        frame.dispose();
                        gameOver = false; // reset the flag
                    }
                });
                panel.add(newGameButton); // Add new game button to panel

                // Add panel to frame
                frame.add(panel);
                frame.pack();
                frame.setLocationRelativeTo(null); // Center the frame on the screen
                frame.setVisible(true);
            }
        } else {
            gameOver = false; // reset the flag when the game is not over
        }
    }
    public void move() {
        // making the paddles move more smoothly, it will also allow us to move both paddles at the same time!
        paddle.move();
        paddle2.move();
        game_ball.move();

    }

    public void Check_If_Collision() {
        //This function will stop the ball from going outside the box
        if (game_ball.y <= 0) {
            game_ball.setDirectionY(-game_ball.YVelocity);
        }
        if (game_ball.y >= HEIGHT_OF_THE_GAME - BALL_RADIUS) {
            game_ball.setDirectionY(-game_ball.YVelocity);
        }
        //Next function will bounce the ball from the paddle
        if (game_ball.intersects(paddle)) {
            game_ball.XVelocity = Math.abs(game_ball.XVelocity);
            game_ball.XVelocity++; //Adds velocity when bounced off the paddle
            if (game_ball.YVelocity > 0)
                game_ball.YVelocity++; //Adds velocity when bounced off the paddle
            else
                game_ball.YVelocity--;
            game_ball.setDirectionX(game_ball.XVelocity);
            game_ball.setDirectionY(game_ball.YVelocity);
        }
        if (game_ball.intersects(paddle2)) {
            game_ball.XVelocity = Math.abs(game_ball.XVelocity);
            game_ball.XVelocity++; //Adds velocity when bounced off the paddle
            if (game_ball.YVelocity > 0)
                game_ball.YVelocity++; //Adds velocity when bounced off the paddle

            else
                game_ball.YVelocity--;
            game_ball.setDirectionX(-game_ball.XVelocity);
            game_ball.setDirectionY(game_ball.YVelocity);
        }
        if (score_system.first_player_score >= 5 || score_system.second_player_score >= 5) {
            // Stop the game and display the winner
            String winner;
            if (score_system.first_player_score >= 5) {
                winner = score_system.first_player_name;
            } else {
                winner = score_system.second_player_name;
            }
        }

        //This function will stop paddles so they dont go through window edges
        if (paddle.y <= 0)
            paddle.y = 0;
        if (paddle.y >= (HEIGHT_OF_THE_GAME - HEIGHT_OF_THE_PADDLE))
            paddle.y = HEIGHT_OF_THE_GAME - HEIGHT_OF_THE_PADDLE;
        if (paddle2.y <= 0)
            paddle2.y = 0;
        if (paddle2.y >= (HEIGHT_OF_THE_GAME - HEIGHT_OF_THE_PADDLE))
            paddle2.y = HEIGHT_OF_THE_GAME - HEIGHT_OF_THE_PADDLE;
        //This piece of code gives a point to the player that scored and created new paddles and a ball
        if (game_ball.x <= 0) {
            score_system.second_player_score++;
            newPaddle();
            new_Game_Ball();
        }
        if (game_ball.x >= WIDTH_OF_THE_GAME - BALL_RADIUS) {
            score_system.first_player_score++;
            newPaddle();
            new_Game_Ball();
        }

    }
    public void run(){
        // Main game loop:
        long lastTime = System.nanoTime();
        double amount_of_ticks = 60.0;
        double nano_seconds = 1000000000 / amount_of_ticks;
        double delta = 0;
        while(true) {
            long now = System.nanoTime();
            delta += (now - lastTime)/ nano_seconds;
            lastTime = now;
            if(delta >= 1){
                move();
                Check_If_Collision();
                repaint();
                delta--;
            }
        }
    }
    public class action_listener extends KeyAdapter{
        public void keyPressed(KeyEvent e){
            paddle.keyPressed(e);
            paddle2.keyPressed(e);
        }
        public void keyReleased(KeyEvent e){
            paddle.keyReleased(e);
            paddle2.keyReleased(e);
        }
    }
}
