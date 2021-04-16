package edu.colorado.exhalation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Game {

    private Board player_board_;
    private Board npc_board_;
    private boolean is_player_turn_;
    private playerAction remote;

    final static int MOVE = 0;

    public Game(){
        this.player_board_ = new Board();
        this.npc_board_ = new Board();
        this.is_player_turn_ = true;
        this.remote = new playerAction();
        this.remote.setCommand(0, new MoveShips(this));
    }

    public void togglePlayer(){
        if(getIsPlayerTurn() == false){
            this.is_player_turn_ = true;
        }
        else{
            this.is_player_turn_ = false;
        }
    }
    public Board getPlayerBoard(){
        return this.player_board_;
    }

    //for testing, takes a file as user input
    //returns -1 on error
    public int place(String file_name){

        File user_moves = new File(file_name);
        try (FileReader file_reader = new FileReader(user_moves)) {
            //successfully opened file

            for(int i = 0; i<this.getPlayerBoard().getShips().length; i++){
                System.out.println("Please select orientation");
                System.out.println("v : vertical");
                System.out.println("h : horizontal");
                char orientation = (char) file_reader.read();
                System.out.println(orientation);
                System.out.println("Please select x position of point as a number");
                char x =(char) file_reader.read();
                System.out.println(x);
                System.out.println("Please select y position of point as a number");
                char y =(char) file_reader.read();
                System.out.println(y);

                int pos_x = Character.getNumericValue(x);
                int pos_y = Character.getNumericValue(y);

                Point ship_start = new Point(pos_x,pos_y);

                if(i ==0){
                    Ship minesweeper = new Minesweeper(orientation, ship_start);
                    if(this.getPlayerBoard().placeShip(minesweeper) != 1){
                        System.out.println("Error placing ship");
                        return -1;
                    }
                }
                else if(i == 1){
                    Ship destroyer = new Destroyer(orientation, ship_start);
                    if(this.getPlayerBoard().placeShip(destroyer) != 1){
                        System.out.println("Error placing ship");
                        return -1;
                    }
                }
                else if(i==2){
                    Ship battleship = new Battleship(orientation, ship_start);
                    if(this.getPlayerBoard().placeShip(battleship) != 1){
                        System.out.println("Error placing ship");
                        return -1;
                    }
                }
                else {
                    Ship submarine = new Submarine(orientation, ship_start);
                    if(this.getPlayerBoard().placeShip(submarine) != 1){
                        System.out.println("Error placing ship");
                        return -1;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
        return -1;
    }

    public int placeShipsNpc(){
        int random_x;
        int random_y;
        int random_num_1_or_2;
        char orientation;
        Point random_point;
        for (int i = 0; i < npc_board_.getShips().length; i++) {

            random_num_1_or_2 = (Math.random() <= 0.5) ? 1 : 2;
            if (random_num_1_or_2 == 1){
                orientation = 'v';
            }
            else {
                orientation = 'h';
            }

            random_x = (int)(Math.random()*100 % 10);
            random_y = (int)(Math.random()*100 % 10);
            random_point = new Point(random_x,random_y);

            if(i ==0){
                Ship minesweeper = new Minesweeper(orientation, random_point);
                int error = this.getNpcBoard().placeShip(minesweeper);
                while(error == -3 || error == -4){
                    //try placing a ship with different random point
                    random_x = (int)(Math.random()*100 % 10);
                    random_y = (int)(Math.random()*100 % 10);
                    random_point = new Point(random_x,random_y);
                    minesweeper = new Minesweeper(orientation, random_point);
                    error = this.getNpcBoard().placeShip(minesweeper);
                }
                if( error != 1){
                    System.out.println("Error placing minesweeper \nError num: "+error);
                    return -1;
                }
            }
            else if(i == 1){
                Ship destroyer = new Destroyer(orientation, random_point);
                int error = this.getNpcBoard().placeShip(destroyer);
                while(error == -3 || error == -4){
                    //try placing a ship with different random point
                    random_x = (int)(Math.random()*100 % 10);
                    random_y = (int)(Math.random()*100 % 10);
                    random_point = new Point(random_x,random_y);
                    destroyer = new Destroyer(orientation, random_point);
                    error = this.getNpcBoard().placeShip(destroyer);
                }
                if( error != 1){
                    System.out.println("Error placing destroyer \nError num: "+error);
                    return -1;
                }
            }
            else if(i==2){
                Ship battleship = new Battleship(orientation, random_point);
                int error = this.getNpcBoard().placeShip(battleship);
                while(error == -3 || error == -4){
                    //try placing a ship with different random point
                    random_x = (int)(Math.random()*100 % 10);
                    random_y = (int)(Math.random()*100 % 10);
                    random_point = new Point(random_x,random_y);
                    battleship = new Battleship(orientation, random_point);
                    error = this.getNpcBoard().placeShip(battleship);
                }
                if( error != 1){
                    System.out.println("Error placing battleship \nError num: "+error);
                    return -1;
                }
            }
            else {
                Ship submarine = new Submarine(orientation, random_point);
                int error = this.getNpcBoard().placeShip(submarine);
                while(error == -3 || error == -4){
                    //try placing a ship with different random point
                    random_x = (int)(Math.random()*100 % 10);
                    random_y = (int)(Math.random()*100 % 10);
                    random_point = new Point(random_x,random_y);
                    submarine = new Submarine(orientation, random_point);
                    error = this.getNpcBoard().placeShip(submarine);
                }
                if( error != 1){
                    System.out.println("Error placing submarine \nError num: "+error);
                    return -1;
                }
            }

        }

        return 1;
    }

    public void setPlayerBoard(Board board){
        this.player_board_ = board;
    }

    public int getPlayerSunkCount(){
        Board player_board = this.getPlayerBoard();
        Ship[] ships = player_board.getShips();
        int sunk_count = 0;
        for (int i = 0; i <ships.length ; i++) {
            Ship ship = ships[i];
            if(player_board.isSunk(ship) == true){
                sunk_count++;
            }
        }
        return sunk_count;
    }

    public int getNpcSunkCount(){
        Board npc_board = this.getNpcBoard();
        Ship[] ships = npc_board.getShips();
        int sunk_count = 0;
        for (int i = 0; i <ships.length ; i++) {
            Ship ship = ships[i];
            if(npc_board.isSunk(ship) == true){
                sunk_count++;
            }
        }
        return sunk_count;
    }

    public boolean gameOver(){
        Board board = this.getPlayerBoard();
        Ship[] ships = board.getShips();
        int sunk_count =0;
        for(int i =0; i<ships.length; i++){
            if(board.isSunk(ships[i])){
                sunk_count++;
            }
        }
        if(sunk_count == board.getShips().length){
            return true;
        }
        else{
            board = this.getNpcBoard();
            ships = board.getShips();
            sunk_count =0;
            for(int i =0; i<ships.length; i++){
                if(board.isSunk(ships[i])){
                    sunk_count++;
                }
            }
            if(sunk_count == board.getShips().length){
                return true;
            }
        }
        return false;
    }//isWinner()

    public void setNpcBoard(Board board){
        this.npc_board_ = board;
    }
    public Board getNpcBoard() {
        return this.npc_board_;
    }
    public playerAction getRemote() {return this.remote;}

    public boolean getIsPlayerTurn() {
        return is_player_turn_;
    }

    public void takeTurn(int action){
        Board board = getBoard();
        long start_time;
        if (action == Board.HIT){
            start_time = System.currentTimeMillis();
            Point random_point = getRandomPoint();
            board.hit(random_point);
            updateChessClock(start_time);
        }
        else if (action == Board.TIME){
            start_time = System.currentTimeMillis();
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            updateChessClock(start_time);
        }
    }//take turn

    public static Point getRandomPoint(){

        Point point = new Point(-1,-1);
        int random_x;
        int random_y;
        while (!point.isValid()){
            random_x = (int)(Math.random()*100 % 10);
            random_y = (int)(Math.random()*100 % 10);
            point = new Point(random_x,random_y);
        }
        return point;
    }

    public boolean notOver(){
        int npc_sunk_count = getNpcSunkCount();
        int player_sunk_count = getPlayerSunkCount();
        if(npc_sunk_count == 4){
            return false;
        }
        else if(player_sunk_count == 4){
            return false;
        }
        else if(getNpcBoard().getTimeLeft()<0){
            return false;
        }
        else if(getPlayerBoard().getTimeLeft()<0){
            return false;
        }
        return true;
    }

    public Board getBoard(){
        if(is_player_turn_){
            return getPlayerBoard();
        }
        else {
            return getNpcBoard();
        }
    }

    public void updateChessClock(long start_time){
        Board board = getBoard();
        long end_time = System.currentTimeMillis();
        long time_elapsed = end_time - start_time;
        long time_left = board.getTimeLeft();
        board.setTimeLeft(time_left-time_elapsed);

    }

    public void npcRandomHit(){
        int random_x = (int)(Math.random()*100 % 10);
        int random_y = (int)(Math.random()*100 % 10);
        Point random_point = new Point(random_x,random_y);

        while(!random_point.isValid()){
            random_x = (int)(Math.random()*100 % 10);
            random_y = (int)(Math.random()*100 % 10);
            random_point = new Point(random_x,random_y);
        }

        this.getPlayerBoard().hit(random_point);
    }

    public void checkLaser(){
        if(this.getNpcSunkCount() == 1){
            this.getNpcBoard().setWeapon(Board.LASER);
        }
        if(this.getPlayerSunkCount()==1){
            this.getPlayerBoard().setWeapon(Board.LASER);
        }


    }
}//Game
