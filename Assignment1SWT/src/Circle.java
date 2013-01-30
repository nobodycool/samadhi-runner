import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Canvas;

public class Circle extends AbstractShape
{
    public Circle()
    {
    	setName("Circle");
        shapeDrawn(true);
    }

    @Override
    public void paintControl(PaintEvent e)
    {
        System.out.println("Circle");

        // Get the canvas for drawing and its width and height
        Canvas canvas = (Canvas) e.widget;
        int xBounds = canvas.getBounds().width;
        int yBounds = canvas.getBounds().height;

        radius = 20;

        if (firstDrawn)
        {
            //randomly place the circle in the panel
            x = (randomRoll.nextInt(xBounds) + 1) - radius;
            y = (randomRoll.nextInt(yBounds) + 1) - radius;
            firstDrawn = false;
        }

        e.gc.setBackground(randColor);
        e.gc.fillOval(x, y, width, height);
    }
}
