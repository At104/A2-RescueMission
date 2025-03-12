package ca.mcmaster.se2aa4.island.teamXXX;

public class CoordinateSystem {
    private int x;
    private int y;
    private Direction currentDirection;

    public CoordinateSystem(int x, int y, Direction currentDirection) {
        this.x = x;
        this.y = y;
        this.currentDirection = currentDirection;
    }

    public boolean moveForward() {
        switch (currentDirection) {
            case North:
                y++;
                break;
            case East:
                x++;
                break;
            case South:
                y--;
                break;
            case West:
                x--;
                break;
            default:
                return false;
        }
        return true;
    }


    public void turnLeft() {
        currentDirection = currentDirection.left();
        switch (currentDirection) {
            case North:
                x++;
                y++;
                break;
            case East:
                x++;
                y--;
                break;
            case South:
                x--;
                y--;
                break;
            case West:
                x--;
                y++;
                break;
            default:
                break;
        }
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Direction getCurrentDirection() {
        return currentDirection;
    }
}
