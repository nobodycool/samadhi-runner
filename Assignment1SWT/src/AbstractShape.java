
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.wb.swt.SWTResourceManager;

public class AbstractShape implements IDrawable, PaintListener
{
	protected  int x;
	protected  int y;
	protected String name;
    protected  int radius;
    protected int width =40;
    protected int height = 40;
    private static List<AbstractShape> shapes = new ArrayList<AbstractShape>();
    protected boolean firstDrawn = true;
    protected Random randomRoll = new Random();
    //create a random color to fill the circle
    protected Color randColor = new Color(null,(int) (256 * Math.random()), (int) (256 * Math.random()), (int) (256 * Math.random()));


    @Override
    public void paintControl(PaintEvent e){

    }

    @Override
    public void eraseShape(Canvas canvas, AbstractShape shape)
    {
        if (shape !=null && shape.shapeDrawn(true))
        {
            canvas.removePaintListener(shape);
            shape.shapeDrawn(false);
        }

    }

    @Override
    public void moveShape(int x, int y,  AbstractShape shape)
    { 	
    	shape.x = x;
    	shape.y = y;
    }

    @Override
    public void increaseSize(AbstractShape shape)
    {
    	System.out.println("Grow");
        shape.width= shape.width+5;
        shape.height = shape.height+5;
    }

    @Override
    public void decreaseSize(AbstractShape shape)
    {
    	System.out.println("Grow");
        shape.width= shape.width-5;
        shape.height = shape.height-5;
    }

	@Override
	public boolean shapeDrawn(boolean value) {
		return value;
	}

	@Override
	public void save(AbstractShape shape) {
		shapes.add(shape);
		
	}

	public static List<AbstractShape> drawAll() {
		return shapes;
	}

	protected String getName() {
		return name;
	}

	protected void setName(String name) {
		this.name = name;
	}
	
	public void printMessage(PaintEvent e)
	{

        e.gc.setBackground(SWTResourceManager.getColor(SWT.COLOR_BLACK));
        e.gc.drawString(getName()+ "has been saved." ,20, 20);
	}
	
	
}
