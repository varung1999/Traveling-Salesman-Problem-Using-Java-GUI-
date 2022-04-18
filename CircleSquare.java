import java.awt.Graphics;
/**
 * 
 * @author Chandana Bandlamudi
 * @version 1.0
 *
 */
public class CircleSquare extends ConcreteDecorator {

	public CircleSquare(Decorator bounds) {
		super(bounds);
		// TODO Auto-generated constructor stub
	}
	
	/**
	 * 
	 * draw the city in the GUI
	 * @param g object of Graphics Class
	 *
	 */
	public void draw(Graphics g) {
		bounds.draw(g); 
		drawingCircle(g);
	}
	
	/**
	 * Adding circle to the city object
	 */
	public void drawingCircle(Graphics g) {
		Decorator c = new Circle(bounds.getLabel(), bounds.getX(), bounds.getY(), bounds.getSize(), bounds.getColor());
		c.draw(g);
	}
	
	
	
	

	
}
