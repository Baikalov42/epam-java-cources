package com.epam.university.java.core.task019;

public class RobotImpl implements Robot {

    private int direction = 1;
    private RobotPosition robotPosition;

    public RobotImpl() {
        robotPosition = new RobotPositionImpl();
    }

    @Override
    public RobotPosition getPosition() {
        return robotPosition;
    }

    @Override
    public void setPosition(RobotPosition position) {
        robotPosition = position;
    }

    @Override
    public void invokeAction(RobotCommand command) {
        if (RobotCommand.MOVE_FORWARD == command) {
            switch (direction) {
                case 1:
                    robotPosition.setX(robotPosition.getX() + 1);
                    break;
                case 2:
                    robotPosition.setY(robotPosition.getY() + 1);
                    break;
                case 3:
                    robotPosition.setX(robotPosition.getX() - 1);
                    break;
                case 4:
                    robotPosition.setY(robotPosition.getY() - 1);
                    break;
                default:
                    throw new IllegalArgumentException();
            }
        } else if (RobotCommand.TURN_LEFT == command) {
            direction++;
            boardsOfDirection();
        } else if (RobotCommand.TURN_RIGHT == command) {
            direction--;
            boardsOfDirection();
        }

    }

    private void boardsOfDirection() {

        if (direction > 4) {
            direction = 1;
        } else if (direction < 1) {
            direction = 4;
        }
    }
}
