package PingPong;

public class AIPaddle extends Paddle {

    public AIPaddle() {
        this.xPos = Game.WIDTH - this.PADDLE_WIDTH;
        this.yPos = Game.HEIGHT / 2;
        move();
    }

}