import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
/**
 * 
 * @author Chandana Bandlamudi
 * @version 1.0
 *
 */
public interface Decorator {


	/**
	 * 
	 *It will return the center of the point
	 *
	 */
	
	public Point center();
	
	/**
	 * 
	 * Method will check wheather the given x, y contains in the city
	 * @param x xcoordinate of given point
	 * @param y ycoordinate of given point
	 *
	 */
	public boolean contains(int x, int y);
	
	/**
	 * 
	 * when the mouse is dragged it will detect the coordinates of the dragged point
	 * @param x x coordinate of dragged point
	 * @param y y coordinate of dragged point
	 *
	 */
	public void move(int x, int y); 
	
	/**
	 * 
	 * X coordinate of the city
	 *
	 */
	public int getX();
	
	/**
	 * 
	 * draw the city in the GUI
	 * @param g object of Graphics Class
	 *
	 */
	public void draw(Graphics g);
	
	/**
	 * 
	 * it will call on one city 
	 * @param b is another city which is the destination
	 * @param g is the object of Graphics2D class
	 *
	 */
	
	
	public void drawConnect(City b, Graphics2D g);
	
	/**
	 * 
	 * Y coordinate of the city
	 *
	 */
	public int getY();
	
	/**
	 * 
	 * get label of the city
	 *
	 */
	public String getLabel();
	
	/**
	 * 
	 * get size of the city
	 *
	 */
	public int getSize();
	
	/**
	 * 
	 * get color of the city
	 *
	 */
	public String getColor();
	
}
