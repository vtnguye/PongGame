package PingPong;

public class Paddle {
    protected  final int PADDLE_HEIGHT = 100;
    protected  final int PADDLE_WIDTH = 20;

    //    //Player Paddle Position
//    private float yPaddleOne= Game.HEIGHT/2;
//    private float xPaddleOne= 0;
//
//    //AI Paddle Position
//    private float yPaddleTwo= Game.HEIGHT/2;
//    private float xPaddleTwo= Game.WIDTH - PADDLE_WIDTH;
    protected float xPos;
    protected float yPos;

    protected void move() {
    }
    ;

    public  int getPaddleHeight() {
        return PADDLE_HEIGHT;
    }

    public  int getPaddleWidth() {
        return PADDLE_WIDTH;
    }

    public float getxPos() {
        return xPos;
    }

    public float getyPos() {
        return yPos;
    }

    public void setxPos(float xPos) {
        this.xPos = xPos;
    }

    public void setyPos(float yPos) {
        this.yPos = yPos;
    }
}
