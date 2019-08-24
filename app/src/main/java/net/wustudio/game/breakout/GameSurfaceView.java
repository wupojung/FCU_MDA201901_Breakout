package net.wustudio.game.breakout;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import net.wustudio.game.breakout.Game.BreakoutGame;

public class GameSurfaceView extends SurfaceView implements Runnable {

    //region  // Fields
    private static String TAG = "Game";

    //for rendering system
    private SurfaceHolder holder;
    private Canvas canvas;

    //for game core
    private boolean running = true;   //working in while loop (game loop)
    private final int frameRate = 33;
    private Thread gameThread = null;

    //for game config
    private float touchOffsetX = 0;
    private float touchOffsetY = 0;
    private boolean touched = false;

    //for game
    BreakoutGame game;

    //endregion

    //region // Ctor
    public GameSurfaceView(Context context) {
        super(context);

        Initialize(context);
    }
    //endregion

    //region  // Utilities
    private void Initialize(Context context) {
        holder = getHolder();

        game = new BreakoutGame();
    }
    //endregion

    //region  // Methods

    //Override
    @Override
    public void run() {
        while (running) {
            try {
                Thread.sleep(frameRate);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if (!holder.getSurface().isValid()) {
                continue;
            }

            canvas = holder.lockCanvas(); //反鎖畫布
            canvas.drawColor(Color.BLACK); //清空畫布

            //for game logic
            //Log.i(TAG,"Running");//for debug
            if(game!=null){
                if(game.GameComponentReady){
                    if (touched) {  //有人觸發Touch
                        game.DispatchTouchOffset(touchOffsetX, touchOffsetY);
                    }
                    game.Refresh(canvas);
                }else{
                    game.SetupGameComponent(canvas);
                }
            }
            holder.unlockCanvasAndPost(canvas); // 解除畫布反鎖
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        //return super.onTouchEvent(event);
        if (event.getAction() == MotionEvent.ACTION_DOWN || event.getAction() == MotionEvent.ACTION_MOVE) {
            touchOffsetX = event.getX();
            touchOffsetY = event.getY();
            touched = true;
        }
        Log.i(TAG, "onTouchEvent! touch[" + touchOffsetX + "," + touchOffsetY+"]");
        return touched;
    }

    public void Pause() {
        running = false; //關閉GameLoop
        while (true) {
            try {
                gameThread.join();
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
                break;
            }
        }
        gameThread = null;
    }

    public void Resume() {
        running = true;  //還原GameLoop
        gameThread = new Thread(this);
        gameThread.start();
    }

    //endregion

    //region // Properties
    //endregion

}
