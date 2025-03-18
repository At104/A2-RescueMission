package ca.mcmaster.se2aa4.island.team104;

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
            case NORTH:
                y++;
                break;
            case EAST:
                x++;
                break;
            case SOUTH:
                y--;
                break;
            case WEST:
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
            case NORTH:
                x++;
                y++;
                break;
            case EAST:
                x++;
                y--;
                break;
            case SOUTH:
                x--;
                y--;
                break;
            case WEST:
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
