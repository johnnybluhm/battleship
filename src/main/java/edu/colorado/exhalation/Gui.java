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
    private ArrayList<GuiObserver> npc_observers;
    private ArrayList<GuiObserver> player_observers;
    private final int SIZE = 10;
    private GridLayout boardLayout;
    private Game game;
    private Board player_state;
    private Board npc_state;

    public Gui(){

        player_observers = new ArrayList<GuiObserver>();
        this.game = new Game();
        game.placeShipsNpc();



        //get random ship placement for player board
        Game game2 = new Game();
        game2.placeShipsNpc();
        game.setPlayerBoard(game2.getNpcBoard());

        this.player_state = game.getPlayerBoard();
        this.npc_state = game.getNpcBoard();

        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,650);

        FlowLayout outer_grid_layout = new FlowLayout();
        JPanel outer_panel = new JPanel();
        outer_panel.setLayout(outer_grid_layout);
        JPanel middle_panel = new JPanel();
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
                Board board = this.getPlayerState();
                Board npc_board = this.getNpcState();
                player_buttons[i][j] = new JButton(String.valueOf(board.getPeg(i,j).print()));
                npc_buttons[i][j] = new JButton(String.valueOf(npc_board.getPeg(i,j).print()));
                player_buttons[i][j].addActionListener(this);
                npc_buttons[i][j].addActionListener(this);
                player_buttons[i][j].setBackground(Color.GREEN);
                npc_buttons[i][j].setBackground(Color.RED);
                npc_panel.add(npc_buttons[i][j]);
                player_panel.add(player_buttons[i][j]);
                //create new obsever, add to list of observers here
                GuiObserver observer = new GuiObserver(this,player_buttons[i][j], new Point(i,j));
            }
        outer_panel.add(player_panel);
        outer_panel.add(middle_panel);
        outer_panel.add(npc_panel);
        frame.add(outer_panel);
        notifyAllPlayerObservers();
        frame.setVisible(true);
        this.frame = frame;
    }

    public Board getPlayerState(){
        return this.player_state;
    }

    public Board getNpcState(){
        return this.npc_state;
    }

    public void setPlayerState(Board state){
        this.player_state = state;
        notifyAllPlayerObservers();
    }

    public void notifyAllPlayerObservers() {
        for (GuiObserver observer: this.player_observers) {
            observer.update();
        }
    }

    public void attachPlayer(GuiObserver observer){
        player_observers.add(observer);
    }

    public ArrayList<GuiObserver> getPlayerObservers(){
        return this.player_observers;
    }

    public JFrame getFrame(){
        return this.frame;
    }

    public JButton[][] getPlayerButtons(){
        return this.player_buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        int x = -1;
        int y = -1;
        for (int row = 0; row < player_buttons.length; row++) {
            for (int col = 0; col < player_buttons.length; col++) {
                if (player_buttons[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    Board board = this.getPlayerState();
                    board.hit(new Point(x,y));
                    notifyAllPlayerObservers();
                    break;
                }
                else if(npc_buttons[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    Board board = this.getNpcState();
                    board.hit(new Point(x,y));
                    //notifyAllNpcObservers();
                    break;

                }
            }
        }
        button.setText(String.valueOf(x)+","+ String.valueOf(y));
    }

    public Game getGame(){
        return this.game;
    }
    public void setGame(Game game){
        this.game = game;
    }

}
