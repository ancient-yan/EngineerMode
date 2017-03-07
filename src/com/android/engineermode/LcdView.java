package com.android.engineermode;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;


public class LcdView extends View 
{
	int i = 0;
    public static int isStop = 0;
	public LcdView(Context context, AttributeSet attrs) {
		super(context, attrs);
	}
	
	public LcdView(Context context) 
	{
		super(context);
	}
	
	protected void onDraw(Canvas canvas) 
	{
		super.onDraw(canvas);
		
		Paint paint = new Paint();   
	       

		switch(i)
		{
		case 1:
		{
			paint.setColor(Color.RED); 
		}
		break;
		
		case 2:
		{
			paint.setColor(Color.GREEN);			
		}
		break;
		
		case 3:	
		{
			paint.setColor(Color.BLUE);			
		}
		break;	
		
		default:
		{
			paint.setColor(Color.WHITE);
			isStop = i;
		}
		}
		
		i++;
	      
	    paint.setStyle(Style.FILL);   
	
	    canvas.drawRect(new Rect(0, 0, getWidth(), getHeight()), paint);   
	}
}
