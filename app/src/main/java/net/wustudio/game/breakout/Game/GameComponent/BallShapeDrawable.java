package net.wustudio.game.breakout.Game.GameComponent;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;

import java.util.Random;

public class BallShapeDrawable extends ShapeDrawable {
    // ball dimensions
    private int left;
    private int right;
    private int top;
    private int bottom;
    private int radius;

    // ball speed
    private int velocityX;
    private int velocityY;

    // timer when ball hits screen bottom
    private final int resetBallTimer = 1000;

    private int SCREEN_WIDTH;
    private int SCREEN_HEIGHT;
    private boolean paddleCollision;
    private boolean blockCollision;
    private Rect mPaddle;
    private Rect ballRect;


    public BallShapeDrawable() {
        super(new OvalShape());
        this.getPaint().setColor(Color.CYAN);
    }



    public void InitCoords(int width, int height) {
        Random rnd = new Random(); // starting x velocity direction

        paddleCollision = false;
        blockCollision = false;
        SCREEN_WIDTH = width;
        SCREEN_HEIGHT = height;

        radius = SCREEN_WIDTH / 72;
        velocityX = radius;
        velocityY = radius * 2;

        // ball coordinates
        left = (SCREEN_WIDTH / 2) - radius;
        right = (SCREEN_WIDTH / 2) + radius;
        top = (SCREEN_HEIGHT / 2) - radius;
        bottom = (SCREEN_HEIGHT / 2) + radius;

        int startingXDirection = rnd.nextInt(2); // random beginning direction
        if (startingXDirection > 0) {
            velocityX = -velocityX;
        }
    }

    public void DrawToCanvas(Canvas canvas) {
        this.setBounds(left, top, right, bottom);
        this.draw(canvas);
    }
}
