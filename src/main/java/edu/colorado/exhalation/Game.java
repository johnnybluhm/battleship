package edu.colorado.exhalation;

public class Game {

    private Board player_board_;
    private Board npc_board_;
    private boolean is_player_turn_;
    private final playerAction remote_;
    private Storm storm_;

    final static int MOVE = 0;

    public Game(){
        this.player_board_ = new Board();
        this.npc_board_ = new Board();
        this.is_player_turn_ = true;
        this.remote_ = new playerAction();
        this.remote_.setCommand(0, new MoveCommand(this));
        this.remote_.setCommand(1, new HitCommand(this));
        this.remote_.setCommand(2, new SonarCommand(this));
        this.remote_.setCommand(3, new AirStrikeCommand(this));
        this.placeShipsNpc();
        this.storm_ = new Storm();
    }

    public void togglePlayer(){
        this.is_player_turn_ = !getIsPlayerTurn();
    }
    public Board getPlayerBoard(){
        return this.player_board_;
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
            if(player_board.isSunk(ship)){
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
            if(npc_board.isSunk(ship)){
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
            return sunk_count == board.getShips().length;
        }
    }

    public void setNpcBoard(Board board){
        this.npc_board_ = board;
    }
    public Board getNpcBoard() {
        return this.npc_board_;
    }
    public playerAction getRemote() {return this.remote_;}

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
        else return getPlayerBoard().getTimeLeft() >= 0;
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

    public void generateStorm(){
        Storm old_storm = this.storm_;
        this.storm_ = new Storm();

        Ship[] npc_ships = this.getNpcBoard().getShips();
        Ship[] player_ships = this.getPlayerBoard().getShips();

        for (Ship ship:npc_ships) {
            if(ship instanceof Submarine) {
                continue;
            }
            else if(ship == null){
                continue;
            }
            Point ship_start = ship.getPoints()[0];
            Point ship_end = ship.getPoints()[ship.getSize()-1];
            if(old_storm.includes(ship_start) && old_storm.includes(ship_end)){
                ship.setSunk();
            }

        }

        for (Ship ship:player_ships) {
            if(ship instanceof Submarine) {
                continue;
            }
            else if(ship == null){
                continue;
            }
            Point ship_start = ship.getPoints()[0];
            Point ship_end = ship.getPoints()[ship.getSize()-1];
            if(old_storm.includes(ship_start) && old_storm.includes(ship_end)){
                ship.setSunk();
            }
        }
    }

    public Storm getStorm(){
        return this.storm_;
    }
}//Game
