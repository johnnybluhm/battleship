package edu.colorado.exhalation;

public class Main {

    public static void main(String[] args) throws InterruptedException {

        Gui gui = new Gui();

        Thread.sleep(3000);
        gui.getGame().getPlayerBoard().move('N');
        gui.notifyAllObservers();





    }
}
