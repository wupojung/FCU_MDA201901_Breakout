package net.wustudio.game.breakout.Game.GameComponent;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;

public class BlockShapeDrawable extends ShapeDrawable {

    private Paint paint;
    private int blockColor;

    public BlockShapeDrawable(Rect rect, int color) {
        super(new RectShape());
        this.setBounds(rect);
        paint = new Paint();
        paint.setColor(color);
        blockColor = color;
    }

    public void drawToCanvas(Canvas canvas) {
        canvas.drawRect(this.getBounds(), paint);
    }

    public int getColor() {
        return paint.getColor();
    }

    public int[] toIntArray() {
        int[] arr = {this.getBounds().left, this.getBounds().top, this.getBounds().right, this.getBounds().bottom, blockColor};
        return arr;
    }
}
