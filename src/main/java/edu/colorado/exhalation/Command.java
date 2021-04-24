package edu.colorado.exhalation;

public interface Command {
    public void char_action(char letter);
    public void point_action(Point point_);
    public void num_action(int num_);
    public int char_point_action(char character, Point point);
    public void undo();
    public void redo();
}
