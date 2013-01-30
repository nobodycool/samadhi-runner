import org.eclipse.swt.widgets.Canvas;



public interface IDrawable {
	
	public void eraseShape(Canvas canvas, AbstractShape shape);
	public void moveShape(int x, int y,  AbstractShape shape);
	public void increaseSize(AbstractShape shape);
	public void decreaseSize(AbstractShape shape);
	public boolean shapeDrawn(boolean value);
	public void save(AbstractShape shape);

}
