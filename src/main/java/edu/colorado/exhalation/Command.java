package edu.colorado.exhalation;

public interface Command { // basic things all commands will have
    public void char_action(char direction); // does something with a character, called 'Move' because only moving ships does anything with a character so far
    public void Action(Point point_); // does something with a Point, hit and sonar both use this at the moment
    public void Undo(); // undo a previous command
    public void Redo(); // redo an undone command
}
