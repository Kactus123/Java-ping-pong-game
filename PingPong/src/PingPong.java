import javax.swing.*;

public class PingPong {

    public static void main(String[] args){
        String first_player_name = JOptionPane.showInputDialog("Enter name of the first player:");
        String second_player_name = JOptionPane.showInputDialog("Enter name of the second player:");
        Panel panel = new Panel(first_player_name, second_player_name);
        Frame frame = new Frame(first_player_name, second_player_name);
    }
}
