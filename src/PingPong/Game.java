package PingPong;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Game extends Application {
    static final float WIDTH = 800;
    static final float HEIGHT = 600;
    private int scoreP1 = 0;
    private int scoreP2 = 0;
    boolean gameStarted;
    Ball ball = new Ball(WIDTH / 2, HEIGHT / 2);
    AIPaddle aiPaddle = new AIPaddle();
    HumanPaddle humanPaddle = new HumanPaddle();


    @Override
    public void start(Stage stage) throws Exception {
        stage.setTitle("PING PONG");

        StackPane root = new StackPane();

        Canvas canvas = new Canvas(WIDTH, HEIGHT);


        GraphicsContext gc = canvas.getGraphicsContext2D();
        Timeline t1 = new Timeline(new KeyFrame(Duration.millis(10), e -> run(gc)));
        t1.setCycleCount(Timeline.INDEFINITE);
        canvas.setOnMouseClicked(e -> gameStarted = true);
        canvas.setOnMouseMoved(e -> humanPaddle.setyPos((float) e.getY()));

        root.getChildren().add(canvas);
        Scene scene = new Scene(root);
        stage.setScene(scene);

        stage.show();
        t1.play();

    }

    public void run(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(0, 0, WIDTH, HEIGHT);

        if (gameStarted) {

            //Ball Movement + Draw
            ball.move();
            gc.setFill(Color.WHEAT);
            gc.fillOval(ball.getxPos(), ball.getyPos(), ball.getBallR(), ball.getBallR());

            //AI Paddle
            if (ball.getxPos() < WIDTH - WIDTH / 4) {
                aiPaddle.setyPos(ball.getyPos() - (aiPaddle.getPaddleHeight() / 2));
            } else {
                if (ball.getyPos() > aiPaddle.getyPos() + aiPaddle.getPaddleHeight()/2)
                    aiPaddle.setyPos(aiPaddle.getyPos() + Math.abs(ball.getySpeed()));
                else
                    aiPaddle.setyPos(aiPaddle.getyPos() - Math.abs(ball.getySpeed()));
            }
        } else {
            //CLICK TO START GAME
            gc.setFont(Font.font(30));
            gc.setFill(Color.WHITE);
            gc.setTextAlign(TextAlignment.CENTER);
            gc.fillText("Click to Start", WIDTH / 2, HEIGHT / 2);

            //RESET BALL
            ball.reset();
            ball.randomSpeed();

        }
        //POINT
        if (ball.getxPos() < humanPaddle.getxPos() - humanPaddle.getPaddleWidth()) {
            scoreP2++;
            gameStarted = false;
        }
        if (ball.getxPos() > aiPaddle.getxPos() + aiPaddle.getPaddleWidth()) {
            scoreP1++;
            gameStarted = false;
        }

        //BOUNCE
        if ((ball.getxPos() > aiPaddle.getxPos()) && (ball.getyPos() >= aiPaddle.getyPos()) && (ball.getyPos() <= aiPaddle.getyPos() + aiPaddle.getPaddleHeight()) ||
                (ball.getxPos() < humanPaddle.getxPos() + humanPaddle.getPaddleWidth()) && (ball.getyPos() >= humanPaddle.getyPos()) && (ball.getyPos() <= humanPaddle.getyPos() + humanPaddle.getPaddleHeight())) {
            ball.bounce();
        }
        gc.fillText(scoreP1 + "\t\t\t\t\t\t\t\t" + scoreP2, WIDTH / 2, 100);
        gc.fillRect(aiPaddle.getxPos(), aiPaddle.getyPos(), aiPaddle.getPaddleWidth(), aiPaddle.getPaddleHeight());
        gc.fillRect(humanPaddle.getxPos(), humanPaddle.getyPos(), humanPaddle.getPaddleWidth(), humanPaddle.getPaddleHeight());
    }

    public static void main(String[] args) {
        launch(args);
    }
}
