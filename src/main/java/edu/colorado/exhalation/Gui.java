package edu.colorado.exhalation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//https://stackoverflow.com/questions/13787873/adding-buttons-using-gridlayout
public class Gui implements ActionListener, MouseListener, KeyListener {
    private JFrame frame;
    private JPanel player_panel;
    private JButton[][] player_buttons;
    private JButton[][] npc_buttons;
    private ArrayList<GuiObserver> observers;
    private final int SIZE = 10;
    private GridLayout boardLayout;
    private Game game;
    private Board player_state;
    private Board npc_state;
    private JButton sonar_pulse;
    char orientation = 'v';
    private boolean pulsed = false;
    public Gui(){

        observers = new ArrayList<GuiObserver>();
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
        /*JButton jb2 = new JButton("Air Strike");
        middle_panel.add(jb2);*/
        //panel for buttons
        boardLayout =  new GridLayout(SIZE,SIZE);
        player_panel = new JPanel();
        JPanel npc_panel = new JPanel();
        npc_panel.setLayout(boardLayout);
        player_panel.setLayout(boardLayout);
        player_buttons = new JButton[SIZE][SIZE];
        npc_buttons = new JButton[SIZE][SIZE];
        //addButtons();
        for(int j=0;j<SIZE;j++)
            for(int i=0;i<SIZE;i++)
            {
                Board board = this.getPlayerState();
                Board npc_board = this.getNpcState();
                player_buttons[i][j] = new JButton();
                npc_buttons[i][j] = new JButton();
                //npc_buttons[i][j].addActionListener(this);
                player_buttons[i][j].setBackground(Color.GREEN);
                npc_buttons[i][j].setBackground(Color.RED);
                npc_panel.add(npc_buttons[i][j]);
                player_panel.add(player_buttons[i][j]);
                npc_buttons[i][j].addMouseListener(this);
                npc_buttons[i][j].addKeyListener(this);


                player_buttons[i][j].addMouseListener(this);
                player_buttons[i][j].addKeyListener(this);

                //create new obsever, add to list of observers here
                GuiObserver player_observer = new GuiObserver(this,player_buttons[i][j], new Point(i,j), true);
                GuiObserver npc_observer = new GuiObserver(this,npc_buttons[i][j], new Point(i,j), false);
            }
        outer_panel.add(player_panel);
        outer_panel.add(middle_panel);
        outer_panel.add(npc_panel);
        frame.add(outer_panel);

        notifyAllObservers();
        frame.setVisible(true);
        frame.setFocusable(true); //needed so that we can do keyListener
        this.frame = frame;
        frame.addKeyListener(this);
        //JOptionPane.showMessageDialog(null, "Place your ships");

    }

    public Board getPlayerState(){
        return this.player_state;
    }

    public Board getNpcState(){
        return this.npc_state;
    }

    public void setPlayerState(Board state){
        this.player_state = state;
        notifyAllObservers();
    }

    public void notifyAllObservers() {
        for (GuiObserver observer: this.observers) {
            observer.update();
        }
    }

    public void attach(GuiObserver observer){
        observers.add(observer);
    }

    public ArrayList<GuiObserver> getObservers(){
        return this.observers;
    }

    public JFrame getFrame(){
        return this.frame;
    }

    public JButton[][] getPlayerButtons(){
        return this.player_buttons;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public Game getGame(){
        return this.game;
    }
    public void setGame(Game game){
        this.game = game;
    }

    public void disableAllButtons(){
        for (int i = 0; i <player_buttons.length ; i++) {
            for (int j = 0; j < player_buttons.length; j++) {
                player_buttons[i][j].setEnabled(false);
            }
        }
        for (int i = 0; i <npc_buttons.length ; i++) {
            for (int j = 0; j < npc_buttons.length; j++) {
                npc_buttons[i][j].setEnabled(false);
            }
        }
    }//disable

    public void enableAllButtons(){
        for (int i = 0; i <player_buttons.length ; i++) {
            for (int j = 0; j < player_buttons.length; j++) {
                player_buttons[i][j].setEnabled(true);
            }
        }
        for (int i = 0; i <npc_buttons.length ; i++) {
            for (int j = 0; j < npc_buttons.length; j++) {
                npc_buttons[i][j].setEnabled(true);
            }
        }
    }//disable

    public void disablePlayerButtons(){
        for (int i = 0; i <player_buttons.length ; i++) {
            for (int j = 0; j < player_buttons.length; j++) {
                player_buttons[i][j].setEnabled(false);
            }
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }
    //https://stackoverflow.com/questions/22638926/how-to-put-hover-effect-on-jbutton
    @Override
    public void mousePressed(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        if(!button.isEnabled()){
            return;
        }
        int x = -1;
        int y = -1;
        Board board = null;
        Peg peg;
        //find which button was pressed
        for (int row = 0; row < npc_buttons.length; row++) {
            for (int col = 0; col < npc_buttons.length; col++) {
                if(npc_buttons[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    board = this.getNpcState();
                    break;
                }//npc buttons
                else if(player_buttons[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    board = this.getPlayerState();
                    break;
                }
            }
        }
        if(board !=null){
            if(SwingUtilities.isRightMouseButton(e)){

                if(this.pulsed == true){
                    this.pulsed = false;
                    this.enableAllButtons();
                    board.undoSonarPulse(new Point(x,y));

                    npcTakeBasicTurn();
                }
                else{
                    //right click to sonar pulse

                    button.setBackground(Color.RED);
                    board.sonarPulse(new Point(x,y));
                    notifyAllObservers();
                    this.disableAllButtons();
                    this.npc_buttons[x][y].setEnabled(true);
                    this.pulsed = true;
                }


            }
            else if (SwingUtilities.isLeftMouseButton(e)){
                //standard left click hit
                if(pulsed == true){
                    //pulse is on so user must disable pulse
                    return;
                }
                if(board.getShips()[Board.MINESWEEPER] == null){
                    //must place minesweeper
                    Ship minesweeper = new Minesweeper(this.orientation,new Point(x,y) );
                    board.placeShip(minesweeper);
                    notifyAllObservers();
                    return;
                }

                board.hit(new Point(x,y));
                peg = board.getPeg(new Point(x,y));
                //button.setText(e.getActionCommand());

                this.getGame().checkLaser();
                if(this.getGame().gameOver()){
                    JOptionPane.showMessageDialog(null, "GAME OVER\nPlayer Won");
                    System.exit(0);
                }
                notifyAllObservers();

                npcTakeBasicTurn();
            }

        }
    }//mousePressed

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Color.GREEN);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        button.setBackground(Color.WHITE);
        notifyAllObservers();
    }

    @Override
    public void keyTyped(KeyEvent e) {


    }

    @Override
    public void keyPressed(KeyEvent e) {
        //change orientation with r
        if(e.getKeyCode() == KeyEvent.VK_R){
            switchOrientation();
            JOptionPane.showMessageDialog(null,"Orientation is now "+this.orientation);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            this.getPlayerState().move('N');
            notifyAllObservers();
            npcTakeBasicTurn();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            this.getPlayerState().move('S');
            notifyAllObservers();
            npcTakeBasicTurn();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            this.getPlayerState().move('W');
            notifyAllObservers();
            npcTakeBasicTurn();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.getPlayerState().move('E');
            notifyAllObservers();
            npcTakeBasicTurn();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void switchOrientation(){
        if(orientation == 'v'){
            orientation = 'h';
        }
        else{
            orientation = 'v';
        }
    }

    public void npcTakeBasicTurn(){
        //now npc takes it turn
        this.getGame().npcRandomHit();
        this.getGame().checkLaser();
        if(this.getGame().gameOver()){
            JOptionPane.showMessageDialog(null, "GAME OVER\nPlayer lost");
            System.exit(0);
        }
        notifyAllObservers();
    }
}
