package PingPong;

import java.util.Random;

public class Ball {
    private final float BALL_R = 15;

    private float xSpeed = (float) 1.5;
    private float ySpeed = 1;
    private float xPos;
    private float yPos;

    public Ball() {
        this.xPos = Game.WIDTH / 2;
        this.yPos = Game.HEIGHT / 2;
    }

    public Ball(float xPos, float yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    public void reset() {
        this.xPos = Game.WIDTH / 2;
        this.yPos = Game.HEIGHT / 2;
    }

    public void randomSpeed() {
        xSpeed = (float) 1.5;
        ySpeed = 1;
        int r = new Random().nextInt(2);
        xSpeed = r == 0 ? xSpeed * 1 : xSpeed * (-1);
        ySpeed = r == 0 ? ySpeed * 1 : ySpeed * (-1);
    }

    public void move() {
        //STAY IN CANVAS
        if (this.yPos < 0 || this.yPos > Game.HEIGHT) {
            this.ySpeed *= (-1);
        }
        this.xPos += xSpeed;
        this.yPos += ySpeed;

    }

    public void bounce() {
        int r = new Random().nextInt(5);
        this.ySpeed += r * Math.signum(ySpeed);
        this.xSpeed += 1 * Math.signum(xSpeed);
        this.xSpeed *= (-1);
        this.ySpeed *= (-1);
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public float getBallR() {
        return BALL_R;
    }

    public float getxSpeed() {
        return xSpeed;
    }

    public float getySpeed() {
        return ySpeed;
    }
}
