package net.wustudio.game.breakout;

import android.app.Activity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class InGameActivity extends Activity {

    private GameSurfaceView gameSurfaceView ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // fullscreen
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //自定義 SurfaceView 取代 View
        gameSurfaceView = new GameSurfaceView(this);
        setContentView(gameSurfaceView);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if(gameSurfaceView!=null){ //傳遞底層訊號
            gameSurfaceView.destroy();
        }
        this.finish();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(gameSurfaceView!=null){ //傳遞底層訊號
            gameSurfaceView.pause();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        if(gameSurfaceView!=null){  //傳遞底層訊號
            gameSurfaceView.resume();
        }
    }

}
