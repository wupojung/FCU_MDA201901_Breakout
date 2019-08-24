package net.wustudio.game.breakout.Game.GameComponent;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Rect;

import java.util.ArrayList;

public class BlockManager {

    private ArrayList<BlockShapeDrawable> blocksList;

    public BlockManager() {
        Initialize();
    }

    private void Initialize() {
        blocksList = new ArrayList<>();
    }

    public void InitCoords(int width, int height) {
        int blockHeight = height / 36;
        int spacing = width / 144;
        int topOffset = height / 10;
        int blockWidth = (width / 10) - spacing;

        blocksList.clear();

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 10; j++) {
                int y_coordinate = (i * (blockHeight + spacing)) + topOffset;
                int x_coordinate = j * (blockWidth + spacing);

                Rect r = new Rect();
                r.set(x_coordinate, y_coordinate, x_coordinate + blockWidth, y_coordinate + blockHeight);

                int color;

                if (i < 2)
                    color = Color.RED;
                else if (i < 4)
                    color = Color.YELLOW;
                else if (i < 6)
                    color = Color.GREEN;
                else if (i < 8)
                    color = Color.MAGENTA;
                else
                    color = Color.LTGRAY;

                BlockShapeDrawable block = new BlockShapeDrawable(r, color);

                blocksList.add(block);
            }
        }
    }

    public void DrawToCanvas(Canvas canvas){
        for (int i = 0; i < blocksList.size(); i++) {
            blocksList.get(i).DrawToCanvas(canvas);
        }
    }
}
