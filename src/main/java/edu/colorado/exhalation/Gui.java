package edu.colorado.exhalation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

//https://stackoverflow.com/questions/13787873/adding-buttons-using-gridlayout
public class Gui implements ActionListener, MouseListener, KeyListener {
    private JFrame frame_;
    private JPanel player_panel_;
    private JButton[][] player_buttons_;
    private JButton[][] npc_buttons_;
    static final int SIZE = 10;
    private int player_laser_ = 0;
    private int npc_laser_ = 0;
    private GridLayout boardLayout_;
    private Game game_;
    char orientation_ = 'v';
    private boolean pulsed_ = false;

    private ArrayList<GuiObserver> observers_;
    private Board player_state_;
    private Board npc_state_;

    public Gui(){

        observers_ = new ArrayList<GuiObserver>();
        this.game_ = new Game();
        //game.placeShipsNpc();

        this.player_state_ = game_.getPlayerBoard();
        this.npc_state_ = game_.getNpcBoard();

        JFrame frame = new JFrame("Battleship");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600,650);

        FlowLayout outer_grid_layout = new FlowLayout();
        JPanel outer_panel = new JPanel();
        outer_panel.setLayout(outer_grid_layout);
        JPanel middle_panel = new JPanel();
        //panel for buttons
        boardLayout_ =  new GridLayout(SIZE,SIZE);
        player_panel_ = new JPanel();
        JPanel npc_panel = new JPanel();
        npc_panel.setLayout(boardLayout_);
        player_panel_.setLayout(boardLayout_);
        player_buttons_ = new JButton[SIZE][SIZE];
        npc_buttons_ = new JButton[SIZE][SIZE];
        //addButtons();
        for(int j=0;j<SIZE;j++)
            for(int i=0;i<SIZE;i++)
            {
                Board board = this.getPlayerState();
                Board npc_board = this.getNpcState();
                player_buttons_[i][j] = new JButton();
                npc_buttons_[i][j] = new JButton();
                //npc_buttons[i][j].addActionListener(this);
                player_buttons_[i][j].setBackground(Color.GREEN);
                npc_buttons_[i][j].setBackground(Color.RED);
                npc_panel.add(npc_buttons_[i][j]);
                player_panel_.add(player_buttons_[i][j]);
                npc_buttons_[i][j].addMouseListener(this);
                npc_buttons_[i][j].addKeyListener(this);


                player_buttons_[i][j].addMouseListener(this);
                player_buttons_[i][j].addKeyListener(this);

                //create new obsever, add to list of observers here
                GuiObserver player_observer = new GuiObserver(this, player_buttons_[i][j], new Point(i,j), true);
                GuiObserver npc_observer = new GuiObserver(this, npc_buttons_[i][j], new Point(i,j), false);
            }
        outer_panel.add(player_panel_);
        //outer_panel.add(middle_panel);
        outer_panel.add(npc_panel);
        frame.add(outer_panel);

        notifyAllObservers();
        frame.setVisible(true);
        frame.setFocusable(true); //needed so that we can do keyListener
        this.frame_ = frame;
        frame.addKeyListener(this);
        this.disableNpcButtons();
        JOptionPane.showMessageDialog(null, "Place your ships");

    }
    public void notifyAllObservers() {
        for (GuiObserver observer: this.observers_) {
            observer.update();
        }
    }
    public void attach(GuiObserver observer){
        observers_.add(observer);
    }
    public Board getPlayerState(){
        return this.player_state_;
    }
    public Board getNpcState(){
        return this.npc_state_;
    }




    public ArrayList<GuiObserver> getObservers(){
        return this.observers_;
    }

    public JFrame getFrame(){
        return this.frame_;
    }

    public JButton[][] getPlayerButtons(){
        return this.player_buttons_;
    }
    public void setPlayerState(Board state){
        this.player_state_ = state;
        notifyAllObservers();
    }
    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public Game getGame(){
        return this.game_;
    }
    public void setGame(Game game){
        this.game_ = game;
    }

    public void disableAllButtons(){
        for (int i = 0; i < player_buttons_.length ; i++) {
            for (int j = 0; j < player_buttons_.length; j++) {
                player_buttons_[i][j].setEnabled(false);
            }
        }
        for (int i = 0; i < npc_buttons_.length ; i++) {
            for (int j = 0; j < npc_buttons_.length; j++) {
                npc_buttons_[i][j].setEnabled(false);
            }
        }
    }//disable

    public void enableAllButtons(){
        for (int i = 0; i < player_buttons_.length ; i++) {
            for (int j = 0; j < player_buttons_.length; j++) {
                player_buttons_[i][j].setEnabled(true);
            }
        }
        for (int i = 0; i < npc_buttons_.length ; i++) {
            for (int j = 0; j < npc_buttons_.length; j++) {
                npc_buttons_[i][j].setEnabled(true);
            }
        }
    }//disable

    public void disablePlayerButtons(){
        for (int i = 0; i < player_buttons_.length ; i++) {
            for (int j = 0; j < player_buttons_.length; j++) {
                player_buttons_[i][j].setEnabled(false);
            }
        }
    }

    public void disableNpcButtons(){
        for (int i = 0; i < npc_buttons_.length ; i++) {
            for (int j = 0; j < npc_buttons_.length; j++) {
                npc_buttons_[i][j].setEnabled(false);
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
        for (int row = 0; row < npc_buttons_.length; row++) {
            for (int col = 0; col < npc_buttons_.length; col++) {
                if(npc_buttons_[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    board = this.getNpcState();
                    break;
                }//npc buttons
                else if(player_buttons_[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    board = this.getPlayerState();
                    break;
                }
            }
        }

        if(board !=null){
            if(SwingUtilities.isRightMouseButton(e)){

                if(this.pulsed_ == true){
                    this.pulsed_ = false;
                    this.enableAllButtons();
                    board.undoSonarPulse(new Point(x,y));

                    npcTakeBasicTurn();
                }
                else{
                    //right click to sonar pulse

                    board.sonarPulse(new Point(x,y));
                    notifyAllObservers();
                    this.disableAllButtons();
                    this.npc_buttons_[x][y].setEnabled(true);
                    this.pulsed_ = true;
                }


            }
            else if (SwingUtilities.isLeftMouseButton(e)){
                //standard left click hit
                if(pulsed_ == true){
                    //pulse is on so user must disable pulse
                    return;
                }
                if(board.getShips()[Board.MINESWEEPER] == null){
                    //must place minesweeper
                    if(game_.getRemote().char_point_action(2, this.orientation_, new Point(x,y)) <1){
                        JOptionPane.showMessageDialog(null,"Invalid placement, try again");
                    }
                    notifyAllObservers();
                    return;
                }
                else if(board.getShips()[Board.DESTROYER] == null){
                    //must place destroyer
                    if(game_.getRemote().char_point_action(2, this.orientation_, new Point(x,y)) <1){
                        JOptionPane.showMessageDialog(null,"Invalid placement, try again");
                    }
                    notifyAllObservers();
                    return;
                }
                else if(board.getShips()[Board.BATTLESHIP] == null){
                    //must place battleship
                    if(game_.getRemote().char_point_action(2, this.orientation_, new Point(x,y)) <1){

                        JOptionPane.showMessageDialog(null,"Invalid placement, try again");
                    }
                    notifyAllObservers();
                    return;
                }
                else if(board.getShips()[Board.SUBMARINE] == null){
                    //must place submarine
                    if(game_.getRemote().char_point_action(2, this.orientation_, new Point(x,y)) <1){
                        JOptionPane.showMessageDialog(null,"Invalid placement, try again");
                        return;
                    }
                    notifyAllObservers();
                    JOptionPane.showMessageDialog(null, "All ships placed. Have fun!");
                    this.enableAllButtons();
                    this.disablePlayerButtons();
                    return;
                }

                //ships are placed so we can hit
                this.getGame().getRemote().point_action(Game.HIT, new Point(x,y));
                this.getGame().checkLaser();
                if (board.getWeapon() == Board.LASER){
                    this.player_laser_++;
                    if(player_laser_ == 1){
                        JOptionPane.showMessageDialog(null, "Laser activated!");
                    }
                }
                if(this.getGame().gameOver()){
                    JOptionPane.showMessageDialog(null, "GAME OVER\nPlayer Won");
                    System.exit(0);
                }
                notifyAllObservers();

                npcTakeBasicTurn();
            }
            else if(SwingUtilities.isMiddleMouseButton(e)){
                if(board.airStrikeUsed()){
                    JOptionPane.showMessageDialog(null, "Air strike already used");
                    return;
                }
                this.getGame().getRemote().num_action(Game.AIR_STRIKE, y);
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
        if(!button.isEnabled()){
            return;
        }
        int x = -1;
        int y = -1;
        Board board = null;
        Peg peg;

        //find which button was pressed
        for (int row = 0; row < npc_buttons_.length; row++) {
            for (int col = 0; col < npc_buttons_.length; col++) {
                if(npc_buttons_[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    board = this.getNpcState();
                    break;
                }//npc buttons
                else if(player_buttons_[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    board = this.getPlayerState();
                    break;
                }
            }
        }

        if(board.getShips()[Board.MINESWEEPER] == null){
            //must place minesweeper
            Point[] points;
            if(this.orientation_ == 'h'){
                points = Minesweeper.getHorizontalPoints(new Point(x,y));
            }
            else{
                points = Minesweeper.getVerticalPoints(new Point(x,y));
            }
            int x1;
            int y1;
            for (int i = 0; i <points.length ; i++) {
                if(!points[i].isValid()){
                    continue;
                }
                x1 = points[i].getX();
                y1 = points[i].getY();
                player_buttons_[x1][y1].setBackground(Color.RED);
            }
        }
        else if(board.getShips()[Board.DESTROYER] == null){
            //must place minesweeper
            Point[] points;
            if(this.orientation_ == 'h'){
                points = Destroyer.getHorizontalPoints(new Point(x,y));
            }
            else{
                points = Destroyer.getVerticalPoints(new Point(x,y));
            }
            int x1;
            int y1;
            for (int i = 0; i <points.length ; i++) {
                if(!points[i].isValid()){
                    continue;
                }
                x1 = points[i].getX();
                y1 = points[i].getY();
                player_buttons_[x1][y1].setBackground(Color.RED);
            }
        }
        else if(board.getShips()[Board.BATTLESHIP] == null){
            //must place minesweeper
            Point[] points;
            if(this.orientation_ == 'h'){
                points = Battleship.getHorizontalPoints(new Point(x,y));
            }
            else{
                points = Battleship.getVerticalPoints(new Point(x,y));
            }
            int x1;
            int y1;
            for (int i = 0; i <points.length ; i++) {
                if(!points[i].isValid()){
                    continue;
                }
                x1 = points[i].getX();
                y1 = points[i].getY();
                player_buttons_[x1][y1].setBackground(Color.RED);
            }
        }
        else if(board.getShips()[Board.SUBMARINE] == null){
            //must place minesweeper
            Point[] points;
            if(this.orientation_ == 'h'){
                points = Submarine.getHorizontalPoints(new Point(x,y));
            }
            else{
                points = Submarine.getVerticalPoints(new Point(x,y));
            }
            int x1;
            int y1;
            for (int i = 0; i <points.length ; i++) {
                if(!points[i].isValid()){
                    continue;
                }
                x1 = points[i].getX();
                y1 = points[i].getY();
                player_buttons_[x1][y1].setBackground(Color.RED);
            }
        }
        else{
            button.setBackground(Color.GREEN);
            //notifyAllObservers();
        }

    }

    @Override
    public void mouseExited(MouseEvent e) {
        JButton button = (JButton) e.getSource();
        if(!button.isEnabled()){
            return;
        }
        int x = -1;
        int y = -1;
        Board board = null;
        Peg peg;

        //find which button was pressed
        for (int row = 0; row < npc_buttons_.length; row++) {
            for (int col = 0; col < npc_buttons_.length; col++) {
                if(npc_buttons_[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    board = this.getNpcState();
                    break;
                }//npc buttons
                else if(player_buttons_[row][col] == e.getSource()){
                    x = row;
                    y = col;
                    board = this.getPlayerState();
                    break;
                }
            }
        }

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
            //JOptionPane.showMessageDialog(null,"Orientation is now "+this.orientation);
        }
        else if(e.getKeyCode() == KeyEvent.VK_UP){
            //this.getPlayerState().move('N');
            this.getGame().getRemote().char_action(Game.MOVE, 'N');
            notifyAllObservers();
            npcTakeBasicTurn();
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN){
            this.getGame().getRemote().char_action(Game.MOVE, 'S');
            notifyAllObservers();
            npcTakeBasicTurn();
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT){
            this.getGame().getRemote().char_action(Game.MOVE, 'W');
            notifyAllObservers();
            npcTakeBasicTurn();
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            this.getGame().getRemote().char_action(Game.MOVE, 'E');
            notifyAllObservers();
            npcTakeBasicTurn();
        }
        else if(e.getKeyCode() == KeyEvent.VK_U){
            JOptionPane.showMessageDialog(null, "undid last action");
            this.getGame().getRemote().undo();
            notifyAllObservers();
        }
        else if(e.getKeyCode() == KeyEvent.VK_N){
            JOptionPane.showMessageDialog(null, "redid last action");
            this.getGame().getRemote().redo();
            notifyAllObservers();
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void switchOrientation(){
        if(orientation_ == 'v'){
            orientation_ = 'h';
        }
        else{
            orientation_ = 'v';
        }
    }

    public void npcTakeBasicTurn(){
        //now npc takes it turn
        this.getGame().npcRandomHit();
        this.getGame().checkLaser();
        if (this.getGame().getPlayerBoard().getWeapon() == Board.LASER){
            this.npc_laser_++;
            if(npc_laser_ == 1){
                JOptionPane.showMessageDialog(null, "Npc has activated laser!");
            }
        }
        if(this.getGame().gameOver()){
            JOptionPane.showMessageDialog(null, "GAME OVER\nPlayer lost");
            System.exit(0);
        }
        this.getGame().generateStorm();
        notifyAllObservers();
    }
}
