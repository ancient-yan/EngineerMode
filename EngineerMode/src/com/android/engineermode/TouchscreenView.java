package com.android.engineermode;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.view.MotionEvent;
import android.view.View;

public class TouchscreenView extends View implements Runnable {
	Path p;

	public TouchscreenView(Context context) {
		super(context);
		new Thread(this).start();
		p = new Path();
	}

	int startX = 0;
	int startY = 0;
	int stopX = 0;
	int stopY = 0;

	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		int talHeight = canvas.getHeight() - 60;
		int talWidth = canvas.getWidth();
		Paint paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.BLACK);
		paint.setStyle(Style.FILL);
		canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);

		paint.setColor(Color.YELLOW);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(5);
		canvas.drawRect(new Rect(10, 10, talWidth - 10, talHeight), paint);

		canvas.drawLine(10, 10, talWidth - 10, talHeight, paint);
		canvas.drawLine(talWidth - 10, 10, 10, talHeight, paint);
		canvas.drawLine(10, talHeight / 2 + 5, talWidth - 10,
				talHeight / 2 + 5, paint);
		canvas.drawLine(talWidth / 2, 10, talWidth / 2, talHeight, paint);

		paint.setColor(Color.BLUE);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(15);
		
		canvas.drawPath(p, paint);
	}

	List<Map<String, Integer>> xy = new ArrayList<Map<String, Integer>>();

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			startX = (int) event.getX();
			startY = (int) event.getY();
			p.moveTo(startX, startY);
		} else if (event.getAction() == MotionEvent.ACTION_MOVE) {
			stopX = (int) event.getX();
			stopY = (int) event.getY();
			p.lineTo(stopX, stopY);
		} else if (event.getAction() == MotionEvent.ACTION_UP) {
			p.close();
		}
		return true;
	}

	public void run() {
		while (!Thread.currentThread().isInterrupted()) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
			postInvalidate();
		}
	}

}
