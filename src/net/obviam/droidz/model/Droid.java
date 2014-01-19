/**
 * 
 */
package net.obviam.droidz.model;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.view.MotionEvent;

/**
 * @author impaler
 *
 */
public class Droid {

	private Bitmap bitmap;	// the actual bitmap
	private double nowX;			// the X coordinate
	private double nowY;			// the Y coordinate
	private double targetX;
	private double targetY;
	private boolean touched;	// if droid is touched/picked up
	private long lastUpdateTime;
	
	public Droid(Bitmap bitmap, double x, double y) {
		this.bitmap = bitmap;
		nowX = x;
		nowY = y;
		targetX = x;
		targetY = y;
		lastUpdateTime = System.currentTimeMillis();
	}
	
	public Bitmap getBitmap() {
		return bitmap;
	}
	public void setBitmap(Bitmap bitmap) {
		this.bitmap = bitmap;
	}
	
	public void setTargetCoor(double x, double y) {
		targetX = x;
		targetY = y;
	}

	public boolean isTouched() {
		return touched;
	}

	public void setTouched(boolean touched) {
		this.touched = touched;
	}
	
	public void draw(Canvas canvas) {
		long now = System.currentTimeMillis();
		long ahead = now - lastUpdateTime;
		if (ahead >= 30) {
			// update time
			if (ahead > 30)
				ahead = 30;
			lastUpdateTime = now - ahead;
			
			if (touched) {
				double dx = targetX - nowX;
				double dy = targetY - nowY;
				double newX = nowX + dx * 0.2;
				double newY = nowY + dy * 0.2;
				nowX = newX;
				nowY = newY;
				canvas.drawBitmap(bitmap, (int)newX, (int)newY, null);
			}
		}
	}

	/**
	 * Handles the {@link MotionEvent.ACTION_DOWN} event. If the event happens on the 
	 * bitmap surface then the touched state is set to <code>true</code> otherwise to <code>false</code>
	 * @param eventX - the event's X coordinate
	 * @param eventY - the event's Y coordinate
	 */
	/*public void handleActionDown(int eventX, int eventY) {
		if (eventX >= (x - bitmap.getWidth() / 2) && (eventX <= (x + bitmap.getWidth()/2))) {
			if (eventY >= (y - bitmap.getHeight() / 2) && (y <= (y + bitmap.getHeight() / 2))) {
				// droid touched
				setTouched(true);
			} else {
				setTouched(false);
			}
		} else {
			setTouched(false);
		}

	}*/
}
