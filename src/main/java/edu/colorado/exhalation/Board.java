package edu.colorado.exhalation;

import javax.swing.*;
import java.util.HashMap;

public class Board {
    //actions
    final static int HIT = 1;
    final static int TIME = 2;
    final static int BOARD_SIZE = 10;
    //weapons
    final static int NUM_WEAPONS = 2;
    final static int BOMB = 0;
    final static int LASER = 1;
    //array indexes for ship types
    final static int MINESWEEPER = 0;
    final static int DESTROYER = 1;
    final static int BATTLESHIP = 2;
    final static int SUBMARINE = 3;

    private Peg[][]  peg_array_ = new Peg[BOARD_SIZE][BOARD_SIZE];
    //ships initialized to null so we can check for double ships
    private Ship[] ships_array_ = {null, null, null, null};

    private int weapon_;
    protected HashMap<Peg, int[]> peg_to_array_hashMap = new HashMap<Peg, int[]>();
    private boolean air_strike_used_ = false;
    private long time_left_ = 300000; //milliseconds
    private Ship[] previous_ships;



    public static Point[] getRowPoints(int row_number){
        Point[] points = new Point[BOARD_SIZE];
        Point row_point;
        for (int i = 0; i <points.length ; i++) {
            row_point = new Point(0+i, row_number);
            points[i] = row_point;
        }
        return points;
    }
    //creates board with all empty pegs, positions are set in the loop
    public Board(){
        this.weapon_=BOMB;
        for(int i =0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE;j++){
                this.peg_array_[i][j] = new Peg(i,j,this);
                int[] int_array = new int[2];
                int_array[0] = 0;
                int_array[1] = 0;
                this.setHash(this.peg_array_[i][j],int_array);
                //peg_array_[i][j].setShip(new Water(new Point(i,j)));
            }
        }
    }//constructor

    public Peg getPeg(Point point){

        return peg_array_[point.getX()][point.getY()];
    }

    public Peg getPeg(int x, int y){

        return peg_array_[x][y];
    }

    public String getStateString(){
        String top_of_board = "   A B C D E | F G H I J\n";
        String seperator = "   - - - - - | - - - - -\n";
        String board = top_of_board;

        //create first half of board before seperator is added
        for(int j =0; j< BOARD_SIZE/2; j++){
            //outer loop goes downward
            String num = String.valueOf(j);
            board += num+"| ";
            //inner loop goes left to right
            for(int i = 0; i< BOARD_SIZE; i++){
                //go across the row
                board += getPeg(i,j).print();
                board += " ";
                //insert | in middle of row
                if(i == 4) board += "| ";
            }//inner for
            board+="\n";
        }//outer for
        board += seperator;
        //create second half of board after seperator is added
        for(int j =BOARD_SIZE/2; j< BOARD_SIZE; j++){
            //outer loop goes downward
            String num = String.valueOf(j);
            board += num+"| ";
            //inner loop goes left to right
            for(int i = 0; i< BOARD_SIZE; i++){
                //go across the row
                board += getPeg(i,j).print();
                board += " ";

                //insert | in middle of row
                if(i == 4) board += "| ";

            }//inner for
            board+="\n";
        }//outer for
        return board;
    }//print

    //returns -1 if airstrike used already
    public int airStrike(int row_number){
        if(air_strike_used_ == true){
            return -1;
        }
        Point[] row_points = Board.getRowPoints(row_number);
        for (int i = 0; i <row_points.length ; i++) {
            this.hit(row_points[i]);
        }
        this.air_strike_used_ = true;
        return 1;
    }

    //returns -1 on most errors
    //returns -2 if ship can be placed, but we already have that ship on the board
    //returns -3 if ship is trying to be placed on another ship
    //returns -4 if ship is invalid
    public String getHiddenStateString(){
        String top_of_board = "   A B C D E | F G H I J\n";
        String seperator = "   - - - - - | - - - - -\n";
        String board = top_of_board;

        //create first half of board before seperator is added
        for(int j =0; j< BOARD_SIZE/2; j++){
            //outer loop goes downward
            String num = String.valueOf(j);
            board += num+"| ";
            //inner loop goes left to right
            for(int i = 0; i< BOARD_SIZE; i++){
                //go across the row
                board += getPeg(i,j).printHidden();
                board += " ";



                //insert | in middle of row
                if(i == 4) board += "| ";

            }//inner for
            board+="\n";
        }//outer for
        board += seperator;
        //create second half of board after seperator is added
        for(int j =BOARD_SIZE/2; j< BOARD_SIZE; j++){
            //outer loop goes downward
            String num = String.valueOf(j);
            board += num+"| ";
            //inner loop goes left to right
            for(int i = 0; i< BOARD_SIZE; i++){
                //go across the row
                board += getPeg(i,j).printHidden();
                board += " ";

                //insert | in middle of row
                if(i == 4) board += "| ";

            }//inner for
            board+="\n";
        }//outer for
        return board;
    }//printHidden

    //returns -1 if what is passed in is not a ship
    //returns -2 if placing a duplicate ship_type (2 minesweepers)
    //returns -3 if a ships is being place on top of another
    //returns -4 if ship is invalid
    public int placeShip(Ship ship){
        if(!ship.isValid()){
            return -4;
        }
        if(ship == null){
            return  -5;
        }
        int array_index;
        if(ship instanceof Minesweeper){
            array_index = MINESWEEPER;
        }
        else if(ship instanceof Battleship){
            array_index = BATTLESHIP;
        }
        else if(ship instanceof Destroyer){
            array_index = DESTROYER;
        }
        else if(ship instanceof Submarine){
            array_index = SUBMARINE;
        }
        else{
            return -1;
        }
        if(this.ships_array_[array_index] != null){
            //ship type is already on board
            return -2;
        }
        Point[] point_array = ship.getPoints();
        if(!(ship instanceof Submarine)){
            //ensure all requested pegs are water
            for(int i = 0; i<point_array.length; i++){
                Peg peg_for_ship = this.getPeg(point_array[i]);
                if((peg_for_ship.hasShip())){
                    return -3;
                }
            }
            //all pegs are water, and no other ship exists
            for(int i = 0; i<point_array.length; i++){
                Peg peg_for_ship = this.getPeg(point_array[i]);
                peg_for_ship.setShip(ship);
                setHash(peg_for_ship,peg_for_ship.getHitCount());
            }
        }//submarine if
        else{
            //we are placing a submarine, no need to check if water
            for(int i = 0; i<point_array.length; i++){
                Peg peg_for_ship = this.getPeg(point_array[i]);
                peg_for_ship.setSub((Submarine) ship);
                setHash(peg_for_ship,peg_for_ship.getHitCount());
            }
        }
        this.ships_array_[array_index] = ship;
        return 1;
    }//place ship

    public void hit(Point point){
        Peg peg = this.getPeg(point);
        int weapon = this.getWeapon();
        peg.hit();
        /*if(weapon == BOMB){
            if(peg.getShip() == null || !peg.getShip().isArmoured()){
                //point has no ship or ship is minesweeper
                peg.hit();
            }
            //check if point is captain's quarters point
            else{
                Point captains_quarters_point = peg.getShip().getPoints()[peg.getShip().getCaptainsQuarters()];
                if(point.equals(captains_quarters_point)){
                    //need to hit twice
                    if(peg.getShip().captainsQuartersHit()){
                        //we can set to hit cuz its been hit once before
                        peg.hit();
                    }
                    else{
                        peg.getShip().setCaptainsQuartersHit();
                    }
                }
            }//else
        }//BOMB
        else if(weapon == LASER){
            if(peg.getShip() == null || !peg.getShip().isArmoured()){
                //point has no ship or ship is nor armoured
                peg.hit();
            }
            //check if point is captain's quarters point
            else{
                Point captains_quarters_point = peg.getShip().getPoints()[peg.getShip().getCaptainsQuarters()];
                if(point.equals(captains_quarters_point)){
                    //need to hit twice
                    if(peg.getShip().captainsQuartersHit()){
                        //we can set to hit cuz its been hit once before
                        peg.hit();
                    }
                    else{
                        peg.getShip().setCaptainsQuartersHit();
                    }
                }
            }//else

        }*/


    }//hit

    public Peg[][] getPegArray(){
        return this.peg_array_;
    }

    public boolean isSunk(Ship ship){

        if(ship instanceof Minesweeper){
            Peg captains_quarters = this.getPeg(ship.getPoints()[Minesweeper.CAPTAINS_QUARTERS]);
            int[] captain_hit_count_array = this.getHitCountArray(captains_quarters);
            int total_hit = 0;
            for (int i = 0; i <captain_hit_count_array.length ; i++) {
                total_hit+= captain_hit_count_array[i];
            }
            if(total_hit >= 1){
                return true;
            }
            else {
                return false;
            }
        }//minesweeper
        else if(ship instanceof Destroyer){
            Peg captains_quarters = this.getPeg(ship.getPoints()[Destroyer.CAPTAINS_QUARTERS]);
            int[] captain_hit_count_array = this.getHitCountArray(captains_quarters);
            int total_hit = 0;
            for (int i = 0; i <captain_hit_count_array.length ; i++) {
                total_hit+= captain_hit_count_array[i];
            }
            if(total_hit >= 2){
                return true;
            }
            else {
                return false;
            }
        }//destroyer
        else if(ship instanceof Battleship){
            Peg captains_quarters = this.getPeg(ship.getPoints()[Battleship.CAPTAINS_QUARTERS]);
            int[] captain_hit_count_array = this.getHitCountArray(captains_quarters);
            int total_hit = 0;
            for (int i = 0; i <captain_hit_count_array.length ; i++) {
                total_hit+= captain_hit_count_array[i];
            }
            if(total_hit >= 2){
                return true;
            }
            else {
                return false;
            }
        }//battleship
        else if(ship instanceof Submarine){
            Peg captains_quarters = this.getPeg(ship.getPoints()[Submarine.CAPTAINS_QUARTERS]);
            int captains_laser_hit_count = this.getHitCount(captains_quarters, Board.LASER);
            if(captains_laser_hit_count >= 1){
                return true;
            }
            else {
                return false;
            }
        }//submarine
        else{
            return false;
        }
    }//isSunk()

    public String sonarPulseString(Point point){

        int x  = point.getX();
        int y = point.getY();
        final int PULSE_SIZE = 12;
        Point[] points = new Point[PULSE_SIZE+1];
        //get points to sides
        points[0] = new Point(x+1,y);
        points[1] = new Point(x+2,y);
        points[2] = new Point(x-1,y);
        points[3] = new Point(x-2,y);

        //get points above/below
        points[4] = new Point(x,y+1);
        points[5] = new Point(x,y+2);
        points[6] = new Point(x,y-1);
        points[7] = new Point(x,y-2);

        //get corners
        points[8] = new Point(x+1,y+1);
        points[9] = new Point(x-1,y-1);
        points[10] = new Point(x-1,y+1);
        points[11] = new Point(x+1,y-1);

        //add selected point
        points[12] = point;

        //set bad points to null
        for(int i =0; i<points.length; i++){
            if(points[i].isValid()==false){
                points[i] = null;
            }
        }

        //STRING MANIPLATION FROM HERE
        String top_of_board = "   A B C D E | F G H I J\n";
        String seperator = "   - - - - - | - - - - -\n";
        String board = top_of_board;

        //create first half of board before seperator is added
        for(int j =0; j< BOARD_SIZE/2; j++){
            //outer loop goes downward
            String num = String.valueOf(j);
            board += num+"| ";
            //inner loop goes left to right
            for(int i = 0; i< BOARD_SIZE; i++){
                //go across the row

                //loop through points and if point is in points
                //print not hidden
                Point current_point = new Point(i,j);
                boolean pulsed = false;

                //check if point should be pulsed
                for (int k=0; k<points.length; k++){
                    if (points[k] == null){
                        //do nothing
                    }
                    else if(current_point.equals(points[k])){

                        pulsed = true;
                        break;
                    }
                }//for k
                if(pulsed){
                    board += getPeg(current_point).print();
                }
                else{
                    board += getPeg(i,j).printHidden();
                }
                board += " ";

                //insert | in middle of row
                if(i == 4) board += "| ";

            }//inner for
            board+="\n";
        }//outer for
        board += seperator;
        //create second half of board after seperator is added
        for(int j =BOARD_SIZE/2; j< BOARD_SIZE; j++){
            //outer loop goes downward
            String num = String.valueOf(j);
            board += num+"| ";
            //inner loop goes left to right
            for(int i = 0; i< BOARD_SIZE; i++){
                //go across the row
                //loop through points and if point is in points
                //print not hidden
                Point current_point = new Point(i,j);
                boolean pulsed = false;
                //check if point should be pulsed
                for (int k=0; k<points.length; k++){
                    if (points[k] == null){
                        //do nothing
                    }
                    else if(current_point.equals(points[k])){
                        pulsed = true;
                        break;
                    }
                }//for k
                if(pulsed){
                    board += getPeg(current_point).print();
                }
                else{
                    board += getPeg(i,j).printHidden();
                }
                board += " ";

                //insert | in middle of row
                if(i == 4) board += "| ";

            }//inner for
            board+="\n";
        }//outer for
        return board;
    }

    public void sonarPulse(Point point){

        int x  = point.getX();
        int y = point.getY();
        final int PULSE_SIZE = 12;
        Point[] points = new Point[PULSE_SIZE+1];
        //get points to sides
        points[0] = new Point(x+1,y);
        points[1] = new Point(x+2,y);
        points[2] = new Point(x-1,y);
        points[3] = new Point(x-2,y);

        //get points above/below
        points[4] = new Point(x,y+1);
        points[5] = new Point(x,y+2);
        points[6] = new Point(x,y-1);
        points[7] = new Point(x,y-2);

        //get corners
        points[8] = new Point(x+1,y+1);
        points[9] = new Point(x-1,y-1);
        points[10] = new Point(x-1,y+1);
        points[11] = new Point(x+1,y-1);

        //add selected point
        points[12] = point;

        for(int i =0; i<points.length; i++){
            if(points[i].isValid()){
                this.getPeg(points[i]).setVisible();
            }
        }

    }

    public void undoSonarPulse(Point point){
        int x  = point.getX();
        int y = point.getY();
        final int PULSE_SIZE = 12;
        Point[] points = new Point[PULSE_SIZE+1];
        //get points to sides
        points[0] = new Point(x+1,y);
        points[1] = new Point(x+2,y);
        points[2] = new Point(x-1,y);
        points[3] = new Point(x-2,y);

        //get points above/below
        points[4] = new Point(x,y+1);
        points[5] = new Point(x,y+2);
        points[6] = new Point(x,y-1);
        points[7] = new Point(x,y-2);

        //get corners
        points[8] = new Point(x+1,y+1);
        points[9] = new Point(x-1,y-1);
        points[10] = new Point(x-1,y+1);
        points[11] = new Point(x+1,y-1);

        //add selected point
        points[12] = point;

        for(int i =0; i<points.length; i++){
            if(points[i].isValid()){
                this.getPeg(points[i]).setHidden();
            }
        }
    }

    public Board copy(){
        Board copy_board = new Board();
        for(int i =0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE;j++){
                //copy_board.getPegArray()[i][j] = new Peg(this.getPeg(i,j));
                copy_board.getPegArray()[i][j] = this.getPeg(i,j).copy();
                copy_board.getPegArray()[i][j].setBoard(copy_board);
            }
        }
        Ship[] ships = this.getShips();
        Ship[] new_ships = new Ship[this.getShips().length];
        Ship copy_ship;
        for (int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            if(ship instanceof Minesweeper){
                copy_ship = new Minesweeper(ship);
                new_ships[i] = copy_ship;
            }
            else if(ship instanceof Destroyer){
                copy_ship = new Destroyer(ship);
                new_ships[i] = copy_ship;
            }
            else if(ship instanceof Battleship){
                copy_ship = new Battleship(ship);
                new_ships[i] = copy_ship;
            }
            else if(ship instanceof Submarine){
                copy_ship = new Submarine(ship);
                new_ships[i] = copy_ship;
            }
        }
        copy_board.setShips(new_ships);
        copy_board.setWeapon(this.getWeapon());
        copy_board.setHashMap(this.peg_to_array_hashMap);
        return copy_board;
    }

    public boolean equals(Board compare_board){
        for(int i =0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE;j++){
                Point test_point = new Point(i,j);
                if(!this.getPeg(test_point).equals(compare_board.getPeg(test_point))){
                    //System.out.println(this.getPeg(test_point));
                    //System.out.println(compare_board.getPeg(test_point));
                    return false;
                }
            }
        }//outer for
        return true;
    }

    public Ship[] getShips(){
        return this.ships_array_;
    }

    public int getWeapon() {
        return weapon_;
    }

    //must take in a valid weapon, else return -1
    public int setWeapon(int weapon) {
        if(weapon== BOMB || weapon == LASER){
            this.weapon_ = weapon;
            return 1;
        }
        else {
            return -1;
        }
    }

    public void removeShips(){
        Ship[] ships = this.getShips();
        previous_ships = this.getShips().clone();
        for (int i = 0; i < ships.length; i++) {
            Ship ship = ships[i];
            if(!this.isSunk(ship) && ship != null){
                for (int j = 0; j < ship.getPoints().length; j++) {
                    Point ship_point = ship.getPoints()[j];
                    if(!(ship instanceof Submarine)){
                       this.getPeg(ship_point).setShip(null);
                    }
                    else { //it is a submarine
                        this.getPeg(ship_point).setSub(null);
                    }
                }
                this.ships_array_[i] = null;
            }//isNotSunk
        }//outer for
    }//remove ships

    public void move(char direction) {
        Ship[] old_ships = new Ship[this.getShips().length];

        for (int i = 0; i <this.getShips().length ; i++) {
            old_ships[i] = getShips()[i];
        }

        if (direction == 'E') {
            Point[] new_start_points = new Point[this.getShips().length];
            for (int i = 0; i < old_ships.length; i++) {
                new_start_points[i] =  new Point(old_ships[i].getPoints()[0].getX()+1, old_ships[i].getPoints()[0].getY());
            }
            this.removeShips();
            for (int i = 0; i <this.getShips().length ; i++) {
                Ship ship = this.getShips()[i];
                if(i == MINESWEEPER && ship == null){
                    Ship minesweeper;
                    if (old_ships[i].isVertical()) {
                        minesweeper = new Minesweeper('v', new_start_points[i]);
                    } else {
                        minesweeper = new Minesweeper('h', new_start_points[i]);
                    }
                    if(this.placeShip(minesweeper) <1 ){
                        this.placeShips(old_ships);
                        return;
                        //this.placeShip(old_ships[i]);
                    }
                }//minesweeper
                else if(i == DESTROYER && ship == null && !this.isSunk(ship)){
                    Ship destroyer;
                    if (old_ships[i].isVertical()) {
                        destroyer = new Destroyer('v', new_start_points[i]);
                    } else {
                        destroyer = new Destroyer('h', new_start_points[i]);
                    }
                    if(this.placeShip(destroyer) <1 ){
                        this.placeShips(old_ships);
                        return;

                        //this.placeShip(old_ships[i]);
                    }
                }
                else if(i == BATTLESHIP && ship == null){
                    Ship battleship;
                    if (old_ships[i].isVertical()) {
                        battleship = new Battleship('v', new_start_points[i]);
                    } else {
                        battleship = new Battleship('h', new_start_points[i]);
                    }
                    if(this.placeShip(battleship) <1 ){
                        this.placeShips(old_ships);
                        return;
                        //this.placeShip(old_ships[i]);
                    }
                }
                else if(i == SUBMARINE && ship == null){
                    Ship submarine;
                    if (old_ships[i].isVertical()) {
                        submarine = new Submarine('v', new_start_points[i]);
                    } else {
                        submarine = new Submarine('h', new_start_points[i]);
                    }
                    if(this.placeShip(submarine) <1 ){
                        this.placeShips(old_ships);
                        return;
                        //this.placeShip(old_ships[i]);
                    }
                }
            }
        }//East
        else if (direction == 'W') {

            Point[] new_start_points = new Point[this.getShips().length];
            for (int i = 0; i < old_ships.length; i++) {
                new_start_points[i] =  new Point(old_ships[i].getPoints()[0].getX()-1, old_ships[i].getPoints()[0].getY());
            }
            this.removeShips();
            for (int i = 0; i <this.getShips().length ; i++) {
                Ship ship = this.getShips()[i];
                if(i == MINESWEEPER && ship == null){
                    Ship minesweeper;
                    if (old_ships[i].isVertical()) {
                        minesweeper = new Minesweeper('v', new_start_points[i]);
                    } else {
                        minesweeper = new Minesweeper('h', new_start_points[i]);
                    }
                    if(this.placeShip(minesweeper) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }//minesweeper
                else if(i == DESTROYER && ship == null){
                    Ship destroyer;
                    if (old_ships[i].isVertical()) {
                        destroyer = new Destroyer('v', new_start_points[i]);
                    } else {
                        destroyer = new Destroyer('h', new_start_points[i]);
                    }
                    if(this.placeShip(destroyer) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
                else if(i == BATTLESHIP && ship == null){
                    Ship battleship;
                    if (old_ships[i].isVertical()) {
                        battleship = new Battleship('v', new_start_points[i]);
                    } else {
                        battleship = new Battleship('h', new_start_points[i]);
                    }
                    if(this.placeShip(battleship) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
                else if(i == SUBMARINE && ship == null){
                    Ship submarine;
                    if (old_ships[i].isVertical()) {
                        submarine = new Submarine('v', new_start_points[i]);
                    } else {
                        submarine = new Submarine('h', new_start_points[i]);
                    }
                    if(this.placeShip(submarine) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
            }
        }//West
        else if (direction == 'S') {
            Point[] new_start_points = new Point[this.getShips().length];
            for (int i = 0; i < old_ships.length; i++) {
                new_start_points[i] =  new Point(old_ships[i].getPoints()[0].getX(), old_ships[i].getPoints()[0].getY()+1);
            }
            this.removeShips();
            for (int i = 0; i <this.getShips().length ; i++) {
                Ship ship = this.getShips()[i];
                if(i == MINESWEEPER && ship == null){
                    Ship minesweeper;
                    if (old_ships[i].isVertical()) {
                        minesweeper = new Minesweeper('v', new_start_points[i]);
                    } else {
                        minesweeper = new Minesweeper('h', new_start_points[i]);
                    }
                    if(this.placeShip(minesweeper) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }//minesweeper
                else if(i == DESTROYER && ship == null){
                    Ship destroyer;
                    if (old_ships[i].isVertical()) {
                        destroyer = new Destroyer('v', new_start_points[i]);
                    } else {
                        destroyer = new Destroyer('h', new_start_points[i]);
                    }
                    if(this.placeShip(destroyer) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
                else if(i == BATTLESHIP && ship == null){
                    Ship battleship;
                    if (old_ships[i].isVertical()) {
                        battleship = new Battleship('v', new_start_points[i]);
                    } else {
                        battleship = new Battleship('h', new_start_points[i]);
                    }
                    if(this.placeShip(battleship) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
                else if(i == SUBMARINE && ship == null){
                    Ship submarine;
                    if (old_ships[i].isVertical()) {
                        submarine = new Submarine('v', new_start_points[i]);
                    } else {
                        submarine = new Submarine('h', new_start_points[i]);
                    }
                    if(this.placeShip(submarine) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
            }
        }//North
        else if (direction == 'N') {
            Point[] new_start_points = new Point[this.getShips().length];
            for (int i = 0; i < old_ships.length; i++) {
                new_start_points[i] =  new Point(old_ships[i].getPoints()[0].getX(), old_ships[i].getPoints()[0].getY()-1);
            }
            this.removeShips();
            for (int i = 0; i <this.getShips().length ; i++) {
                Ship ship = this.getShips()[i];
                if(i == MINESWEEPER && ship == null){
                    Ship minesweeper;
                    if (old_ships[i].isVertical()) {
                        minesweeper = new Minesweeper('v', new_start_points[i]);
                    } else {
                        minesweeper = new Minesweeper('h', new_start_points[i]);
                    }
                    if(this.placeShip(minesweeper) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }//minesweeper
                else if(i == DESTROYER && ship == null){
                    Ship destroyer;
                    if (old_ships[i].isVertical()) {
                        destroyer = new Destroyer('v', new_start_points[i]);
                    } else {
                        destroyer = new Destroyer('h', new_start_points[i]);
                    }
                    if(this.placeShip(destroyer) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
                else if(i == BATTLESHIP && ship == null){
                    Ship battleship;
                    if (old_ships[i].isVertical()) {
                        battleship = new Battleship('v', new_start_points[i]);
                    } else {
                        battleship = new Battleship('h', new_start_points[i]);
                    }
                    if(this.placeShip(battleship) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
                else if(i == SUBMARINE && ship == null){
                    Ship submarine;
                    if (old_ships[i].isVertical()) {
                        submarine = new Submarine('v', new_start_points[i]);
                    } else {
                        submarine = new Submarine('h', new_start_points[i]);
                    }
                    if(this.placeShip(submarine) <1 ){
                        this.placeShips(old_ships);
                        return;
                    }
                }
            }
        }//West

    }//move

    public void setShips(Ship[] ships){
        this.ships_array_ = ships;
    }

    public void setHash(Peg peg, int[] hit_count){
        if(this.peg_to_array_hashMap.containsKey(peg)){
            this.peg_to_array_hashMap.replace(peg,hit_count);
        }
        else{
            this.peg_to_array_hashMap.put(peg, hit_count);
        }
    }//setHash

    public int[] getHitCountArray(Peg peg){
        return this.peg_to_array_hashMap.get(peg);
    }

    //other methods may be useful in the future
    public int getHitCount(Peg peg, int weapon){
        int[] hitCount = this.peg_to_array_hashMap.get(peg);
        return hitCount[weapon];
    }

    public long getTimeLeft() {
        return time_left_;
    }

    public void setTimeLeft(long time_left) {
        this.time_left_ = time_left;
    }

    public void setHashMap(HashMap<Peg, int[]> hash_map){
        this.peg_to_array_hashMap = hash_map;
    }

    public void placeShips(Ship[] ships){

        removeShips();
        for (int i = 0; i <ships.length; i++) {
            this.placeShip(ships[i]);
        }
    }

    public boolean airStrikeUsed(){
        return this.air_strike_used_;
    }
}//Board
