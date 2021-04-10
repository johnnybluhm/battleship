package edu.colorado.exhalation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

//https://stackoverflow.com/questions/13787873/adding-buttons-using-gridlayout
public class Gui implements ActionListener {
    private JFrame frame;
    private JPanel grid_panel;
    private JButton[][]buttons;
    private final int SIZE = 10;
    private GridLayout gridLayout;
    private Game game;
    public Gui(){
        this.game = new Game();
        game.placeShipsNpc();

        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800,800);

        //panel for buttons
        gridLayout =  new GridLayout(SIZE,SIZE);
        grid_panel = new JPanel();
        grid_panel.setLayout(gridLayout);
        buttons = new JButton[SIZE][SIZE];
        addButtons();
        frame.add(grid_panel);

        frame.setVisible(true);
        this.frame = frame;
    }

    public Game getGame(){
        return this.game;
    }

    public void addButtons()
    {
        for(int i=0;i<SIZE;i++)
            for(int j=0;j<SIZE;j++)
            {
                Board board = this.getGame().getNpcBoard();
                buttons[i][j] = new JButton(String.valueOf(board.getPeg(i,j).print()));
                JButton button = buttons[i][j];
                button.addActionListener(this);

                
                grid_panel.add(buttons[i][j]);
            }

    }

    public void changeColor(){

    }

    public JFrame getFrame(){
        return this.frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        JButton button = (JButton) e.getSource();
        int x = button.getX();
        int y = button.getY();
        y = (y-1) / 76;
        x = (x-3) / 78;

        Board board = this.getGame().getNpcBoard();
        board.hit(new Point(x,y));
        int hit_count = board.getPeg(x,y).getHitCount()[0];


        button.setText(String.valueOf(hit_count));
    }
}
