import java.awt.*;

public class Score_system extends Rectangle {
    static int WIDTH_OF_THE_GAME;
    static int HEIGHT_OF_THE_GAME;
    int first_player_score;
    int second_player_score;
    String first_player_name;
    String second_player_name;

    public Score_system(int WIDTH_OF_THE_GAME, int HEIGHT_OF_THE_GAME, String first_player_name, String second_player_name) {
        Score_system.WIDTH_OF_THE_GAME = WIDTH_OF_THE_GAME;
        Score_system.HEIGHT_OF_THE_GAME = HEIGHT_OF_THE_GAME;
        this.first_player_name = first_player_name;
        this.second_player_name = second_player_name;
    }

    public void draw(Graphics g) {
        g.setColor(Color.yellow);
        g.setFont(new Font("Serif", Font.BOLD, 40));

        g.drawLine(WIDTH_OF_THE_GAME/2, 0, WIDTH_OF_THE_GAME/2, HEIGHT_OF_THE_GAME);

        g.drawString(first_player_name, (WIDTH_OF_THE_GAME/2) - 130, 50);
        g.drawString(second_player_name, (WIDTH_OF_THE_GAME/2) + 55, 50);

        // Draw the score for the first player
        g.setFont(new Font("Serif", Font.BOLD, 20));
        g.drawString("Score: " + first_player_score, 20, 40);

        // Draw the score for the second player
        g.drawString("Score: " + second_player_score, WIDTH_OF_THE_GAME - 120, 40);
        if (first_player_score >= 5 || second_player_score >= 5) {
            String winner;
            if (first_player_score >= 5) {
                winner = first_player_name;
            } else {
                winner = second_player_name;
            }
            g.setFont(new Font("Serif", Font.BOLD, 40));
            g.drawString(winner + " wins!", WIDTH_OF_THE_GAME/2 - 80, HEIGHT_OF_THE_GAME/3);
        }
    }
}
