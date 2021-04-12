package edu.colorado.exhalation;

public class Main {

    public static void main(String[] args) {

        Gui gui = new Gui();

        gui.getPlayerState().hit(new Point(9,9));
        gui.getPlayerState().move('S');
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gui.notifyAllPlayerObservers();

        gui.getPlayerState().move('S');
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gui.notifyAllPlayerObservers();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        gui.getGame().getRemote().char_action(0,'N');
        gui.notifyAllPlayerObservers();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        gui.notifyAllPlayerObservers();

        gui.getGame().getRemote().char_action(0,'S');

    }
}
