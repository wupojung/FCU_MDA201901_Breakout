package net.wustudio.game.breakout.Game;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

import net.wustudio.game.breakout.Game.GameComponent.BallShapeDrawable;
import net.wustudio.game.breakout.Game.GameComponent.BlockManager;
import net.wustudio.game.breakout.Game.GameComponent.PaddleShapeDrawable;

public class BreakoutGame {

    //region  // Fields

    private int points = 0; //分數
    private int lives = 3;  //生命次數

    public boolean GameComponentReady = false;

    //畫筆
    private Paint getReadyPaint;
    private Paint scorePaint;
    private Paint livesPaint;

    //Game Component
    private BallShapeDrawable ball;
    private PaddleShapeDrawable paddle;
    private BlockManager blockManager;
    //

    /*

     */
    //endregion

    //region  // Ctor
    public BreakoutGame() {
        Initialize();
    }
    //endregion

    //region  // Utilities
    private void Initialize() {
        InitializeGameComponent(); //初始化所有遊戲元件
        InitializePaint(); //初始化 筆刷工具
    }

    private void InitializeGameComponent() {
        ball = new BallShapeDrawable();
        paddle = new PaddleShapeDrawable();
        blockManager = new BlockManager();
    }

    private void InitializePaint() {
        scorePaint = new Paint();
        scorePaint.setColor(Color.WHITE);
        scorePaint.setTextSize(25);

        livesPaint = new Paint();
        livesPaint.setTextAlign(Paint.Align.RIGHT);
        livesPaint.setColor(Color.WHITE);
        livesPaint.setTextSize(25);

        getReadyPaint = new Paint();
        getReadyPaint.setTextAlign(Paint.Align.CENTER);
        getReadyPaint.setColor(Color.WHITE);
        getReadyPaint.setTextSize(45);
    }

    private void RefreshUI(Canvas canvas) {
        canvas.drawText("得分:" + points, 0, 25, scorePaint);
        canvas.drawText("生命:" + lives, canvas.getWidth(), 25, livesPaint);
    }

    private void RefreshGameComponent(Canvas canvas) {
        blockManager.DrawToCanvas(canvas);
        paddle.DrawToCanvas(canvas);
        ball.DrawToCanvas(canvas);
    }
    //endregion

    //region  // Methods

    public void SetupGameComponent(Canvas canvas) {
        paddle.InitCoords(canvas.getWidth(), canvas.getHeight());
        ball.InitCoords(canvas.getWidth(), canvas.getHeight());
        blockManager.InitCoords(canvas.getWidth(), canvas.getHeight());
        GameComponentReady = true;
    }

    public void DispatchTouchOffset(float x, float y) {

    }

    public void Refresh(Canvas canvas) {
        RefreshUI(canvas);
        RefreshGameComponent(canvas);
    }
    //endregion

    //region  // Properties

    //endregion
}
