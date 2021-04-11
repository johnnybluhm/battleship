package edu.colorado.exhalation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

//https://stackoverflow.com/questions/13787873/adding-buttons-using-gridlayout
public class Gui implements ActionListener {
    private JFrame frame;
    private JPanel player_panel;
    private JButton[][] player_buttons;
    private JButton[][] npc_buttons;
    private ArrayList<GuiObserver> observers;
    private final int SIZE = 10;
    private GridLayout boardLayout;
    private Game state;

    public Gui(){
        this.state = new Game();
        state.placeShipsNpc();

        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,650);

        FlowLayout outer_grid_layout = new FlowLayout();
        JPanel outer_panel = new JPanel();
        outer_panel.setLayout(outer_grid_layout);
        JPanel middle_panel = new JPanel();
        BoxLayout box_layout = new BoxLayout(middle_panel, BoxLayout.X_AXIS);
        // Define new buttons
        JButton jb1 = new JButton("Sonar Pulse");
        JButton jb2 = new JButton("Air Strike");
        JButton jb3 = new JButton("Move Fleet");
        middle_panel.add(jb1);
        middle_panel.add(jb2);
        middle_panel.add(jb3);
        //panel for buttons
        boardLayout =  new GridLayout(SIZE,SIZE);
        player_panel = new JPanel();
        JPanel npc_panel = new JPanel();
        npc_panel.setLayout(boardLayout);
        player_panel.setLayout(boardLayout);
        player_buttons = new JButton[SIZE][SIZE];
        npc_buttons = new JButton[SIZE][SIZE];
        //addButtons();
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
            {
                Board board = this.getState().getNpcBoard();
                player_buttons[i][j] = new JButton(String.valueOf(board.getPeg(i,j).print()));
                npc_buttons[i][j] = new JButton(String.valueOf(board.getPeg(i,j).print()));
                player_buttons[i][j].addActionListener(this);
                npc_buttons[i][j].addActionListener(this);
                player_buttons[i][j].setBackground(Color.GREEN);
                npc_buttons[i][j].setBackground(Color.RED);
                npc_panel.add(npc_buttons[i][j]);
                player_panel.add(player_buttons[i][j]);
            }
        outer_panel.add(player_panel);
        outer_panel.add(middle_panel);
        outer_panel.add(npc_panel);
        frame.add(outer_panel);

        frame.setVisible(true);
        this.frame = frame;
    }

    public Game getState(){
        return this.state;
    }

    public JFrame getFrame(){
        return this.frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        int x = -1;
        int y = -1;
        for (int row = 0; row < player_buttons.length; row++) {
            for (int col = 0; col < player_buttons.length; col++) {
                if (player_buttons[row][col] == e.getSource() || npc_buttons[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    Board board = this.getState().getNpcBoard();
                    board.hit(new Point(x,y));
                    break;
                }
            }
        }

        button.setText(String.valueOf(x)+","+ String.valueOf(y));
    }

    public void attach(GuiObserver observer){
        observers.add(observer);
    }
}
