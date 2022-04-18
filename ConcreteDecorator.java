import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
/**
 * 
 * @author Chandana Bandlamudi
 * @version 1.0
 *
 */
public class ConcreteDecorator implements Decorator {
	Decorator bounds;
	
	public ConcreteDecorator(Decorator bounds)
	{
		super();
		this.bounds= bounds;
		
	}
	
	/**
	 * 
	 *It will return the center of the point
	 *
	 */
	public Point center()
	{
		 return bounds.center();
	}
	
	/**
	 * 
	 * Method will check wheather the given x, y contains in the city
	 * @param x xcoordinate of given point
	 * @param y ycoordinate of given point
	 *
	 */
	
	public boolean contains(int x, int y)
	{
		return bounds.contains(x,y);
	}
	
	/**
	 * 
	 * when the mouse is dragged it will detect the coordinates of the dragged point
	 * @param x x coordinate of dragged point
	 * @param y y coordinate of dragged point
	 *
	 */
	public void move(int x, int y)
	{
		bounds.move(x,y);
	}
	
	/**
	 * 
	 * X coordinate of the city
	 *
	 */
	public int getX()
	{
		return bounds.getX();
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
	}
	
	/**
	 * 
	 * it will call on one city 
	 * @param b is another city which is the destination
	 * @param g is the object of Graphics2D class
	 *
	 */
	public void drawConnect(City b, Graphics2D g)
	{
		bounds.drawConnect(b, g);
	}
	
	/**
	 * 
	 * Y coordinate of the city
	 *
	 */
	public int getY()
	{
		return bounds.getY();
	}
	
	/**
	 * Gives the label of the city
	 */
	public String getLabel()
	{
		return bounds.getLabel();
	}

	/**
	 * Gives the size of the city
	 */
	@Override
	public int getSize() {
		// TODO Auto-generated method stub
		return bounds.getSize();
	}

	/**
	 * Gives the size of the city
	 */
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return bounds.getColor();
	}

}
