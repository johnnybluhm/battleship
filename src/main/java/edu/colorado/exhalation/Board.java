package edu.colorado.exhalation;

public class Board {

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

    //creates board with all empty pegs, positions are set in the loop
    public Board(){
        this.weapon_=BOMB;
        for(int i =0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE;j++){
                this.peg_array_[i][j] = new Peg(i,j,this);
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

    //returns -1 on error
    public int placeShip(Ship ship){
        if(!ship.isValid()){
            return -4;
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
                if((peg_for_ship.getShip() != null)){
                    return -3;
                }
            }
            //all pegs are water, and no other ship exists
            for(int i = 0; i<point_array.length; i++){
                Peg peg_for_ship = this.getPeg(point_array[i]);
                peg_for_ship.setShip(ship);
                ship.setPeg(peg_for_ship,i);
                ship.setHash(peg_for_ship,peg_for_ship.getHitCount());
            }
        }//submarine if
        else{
            //we are placing a submarine, no need to check if water
            for(int i = 0; i<point_array.length; i++){
                Peg peg_for_ship = this.getPeg(point_array[i]);
                peg_for_ship.setSub((Submarine) ship);
                ship.setPeg(peg_for_ship,i);
                ship.setHash(peg_for_ship,peg_for_ship.getHitCount());
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

    public int getBOARD_SIZE(){
        return BOARD_SIZE;
    }

    public Peg[][] getPegArray(){
        return this.peg_array_;
    }

    public boolean isSunk(Ship ship){
        return ship.isSunk();
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

    public Board sonarPulse(Point point){

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
        Board pulse_board = this.copy();
        for(int i =0; i<points.length; i++){
            if(points[i].isValid()){
                pulse_board.getPeg(points[i]).setVisible();
            }
        }
        return pulse_board;
    }

    public Board copy(){
        Board copy_board = new Board();
        for(int i =0; i< BOARD_SIZE; i++){
            for(int j = 0; j< BOARD_SIZE;j++){
                //copy_board.getPegArray()[i][j] = new Peg(this.getPeg(i,j));
                copy_board.getPegArray()[i][j] = this.getPeg(i,j).copy();
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

    public void move(char direction) {

        if (direction == 'E') {
            for (Ship ship :
                    this.getShips()) { // loops through the ships on the board
                if (ship != null && !ship.isSunk()) { // makes sure ship isn't sunk and not null

                    ship.move(direction);

                    /*Peg[] pegs = ship.getPegs(); // gets pegs that ship is on
                    int length = pegs.length;

                    if(pegs[length - 1].getPoint().getX() == 9){ // Ship cannot go any further East, it's on the edge of the map
                        //do not move ship
                        System.out.println("SHIP NAME cannot move any further East, it's on the East edge of the board!");
                    }
                    else { // Ship can move East
                        if(ship instanceof Submarine){

                            Peg nub_of_ship = pegs[length-1];
                            Peg head_of_ship = pegs[3];
                            Peg tail_of_ship = pegs[0];

                            Peg next_head_peg;
                            Point current_peg_point = head_of_ship.getPoint();
                            Point next_peg_point = new Point(current_peg_point.getX() + 1, current_peg_point.getY());
                            next_head_peg = head_of_ship.getBoard().getPeg(next_peg_point);

                            Peg next_nub_peg;
                            Point current_nub_peg_point = nub_of_ship.getPoint();
                            Point next_nub_peg_point = new Point(current_nub_peg_point.getX() + 1, current_nub_peg_point.getY());
                            next_nub_peg = nub_of_ship.getBoard().getPeg(next_nub_peg_point);

                            tail_of_ship.setSub(null);
                            next_head_peg.setSub(head_of_ship.getSub());
                            next_nub_peg.setSub(nub_of_ship.getSub());
                            nub_of_ship.setSub(null);

                            Point[] new_points = new Point[length]; // new array of points for where ship is moving to
                            for (int i = 0; i < length; i++) {
                                new_points[i] = new Point(pegs[i].getPoint().getX() + 1, pegs[i].getPoint().getY());
                            }
                            //System.out.println(Arrays.toString(ship.getPoints()));
                            ship.setPoints(new_points);
                            //System.out.println(Arrays.toString(ship.getPoints()));
                        }
                        else {
                            Peg head_of_ship = pegs[length - 1];
                            Peg tail_of_ship = pegs[0];

                            Peg next_peg;
                            Point current_peg_point = head_of_ship.getPoint();
                            Point next_peg_point = new Point(current_peg_point.getX() + 1, current_peg_point.getY());
                            next_peg = head_of_ship.getBoard().getPeg(next_peg_point);

                            tail_of_ship.setShip(null);

                            next_peg.setShip(head_of_ship.getShip());

                            Point[] new_points = new Point[length]; // new array of points for where ship is moving to
                            for (int i = 0; i < length; i++) {
                                new_points[i] = new Point(pegs[i].getPoint().getX() + 1, pegs[i].getPoint().getY());
                            }
                            //System.out.println(Arrays.toString(ship.getPoints()));
                            ship.setPoints(new_points);
                            //System.out.println(Arrays.toString(ship.getPoints()));
                        }
                    }
                }
            }*/
                }//East
            }
        }
    }//move

    public void setShips(Ship[] ships){
        this.ships_array_ = ships;
    }
}//Board
