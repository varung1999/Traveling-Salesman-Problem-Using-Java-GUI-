import java.awt.Color;
import java.awt.Graphics;
/**
 * 
 * @author Chandana Bandlamudi
 * @version 1.0
 *
 */
public class MultipleRectangle extends ConcreteDecorator{

	public MultipleRectangle(Decorator bounds) {
		super(bounds);
		// TODO Auto-generated constructor stub
	}
	/**
	 * 
	 * draw the city in the GUI
	 * @param g object of Graphics Class
	 *
	 */
	
	public void draw(Graphics g)
	{
		bounds.draw(g);
		drawingMultipleRectangle(g);
	}
	
	/**
	 * 
	 * Drawing multiple rectangles
	 *
	 */
	public void drawingMultipleRectangle(Graphics g)
	{
		int x = bounds.getX();
		int y = bounds.getY();
		int size1= bounds.getSize();
		Color c = g.getColor(); 
		
		g.drawRect(x + size1, y, size1, size1); 
		g.setColor(Color.black); 
		g.fillRect(x + 1 + size1, y + 1, size1 - 1, size1 - 1); 
		g.setColor(Color.decode(bounds.getColor()));

		
		g.drawRect(x - size1, y, size1, size1); 
		g.setColor(Color.black); 
		g.fillRect(x + 1 - size1, y + 1, size1 - 1, size1 - 1); 
		g.setColor(Color.decode(bounds.getColor()));

		
		g.drawRect(x, y + size1, size1, size1); 
		g.setColor(Color.black); 
		g.fillRect(x + 1, y + 1 + size1, size1 - 1, size1 - 1); 
		g.setColor(Color.decode(bounds.getColor()));
 
		
		g.drawRect(x, y - size1, size1, size1); 
		g.setColor(Color.black); 
		g.fillRect(x + 1, y + 1 - size1, size1 - 1, size1 - 1); 
		g.setColor(Color.decode(bounds.getColor()));

		
		g.setColor(c);
	}

}
