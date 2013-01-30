import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.widgets.Canvas;

public class Square extends AbstractShape
{
    public Square()
    {
        setName("Square");
        shapeDrawn(false);
    }

    @Override
    public void paintControl(PaintEvent e)
    {
        Canvas canvas = (Canvas) e.widget;

        int xBounds = canvas.getBounds().width;
        int yBounds = canvas.getBounds().height;

        if (firstDrawn)
        {
            x = (randomRoll.nextInt(xBounds) + 1) - radius;
            y = (randomRoll.nextInt(yBounds) + 1) - radius;
            firstDrawn = false;
        }

        e.gc.setBackground(randColor);
        e.gc.fillGradientRectangle(x, y, width, height, true);
    }
}
