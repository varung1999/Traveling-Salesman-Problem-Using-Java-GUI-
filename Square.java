import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.Rectangle;
/**
 * 
 * @author Chandana Bandlamudi
 * @version 1.0
 *
 */
public class Square implements Decorator{
	Rectangle bounds;
	String label;
	String color;
	
	public Square(String label, int x, int y , int size, String color)
	{
		this.label= label;
		bounds = new Rectangle(x,y,size,size);
		this.color = color;
	}
	
	/**
	 * 
	 * X coordinate of the city
	 *
	 */

	public int getX() {
		return bounds.x;
		}
	
	/**
	 * 
	 * Y coordinate of the city
	 *
	 */
	public int getY() { 
		return bounds.y; 
		}
	
	/**
	 * Gives the label of the city
	 */
	public String getLabel()
	{
		return label;
	}
	
	/**
	 * 
	 * draw the city in the GUI
	 * @param g object of Graphics Class
	 *
	 */
	public void draw (Graphics g) {
		int x = bounds.x, y = bounds.y, h = bounds.height, w = bounds.width;
		g.drawRect(x, y, w, h);
		Color c = g.getColor();
		g.setColor(Color.decode(color));
		g.fillRect(x + 1, y + 1, w - 1, h - 1);
		g.setColor(Color.red);
		g.setFont(new Font("Courier", Font.PLAIN, 10));
		g.drawString(label, x + w, y);
		g.setColor(c);
	}
	
	/**
	 * 
	 * when the mouse is dragged it will detect the coordinates of the dragged point
	 * @param x x coordinate of dragged point
	 * @param y y coordinate of dragged point
	 *
	 */
	public void move(int x, int y) {
		bounds.x = x;
		bounds.y = y;
		}
	
	/**
	 * 
	 *It will return the center of the point
	 *
	 */
	
	public Point center() {
		return new Point(bounds.x + bounds.width / 2,
				  bounds.y + bounds.height / 2);
	}
	
	/**
	 * 
	 * it will call on one city 
	 * @param b is another city which is the destination
	 * @param g is the object of Graphics2D class
	 *
	 */
	public void drawConnect(City b, Graphics2D g) {
		g.drawLine(center().x, center().y, b.center().x, b.center().y);
	}
	
	/**
	 * 
	 * Method will check wheather the given x, y contains in the city
	 * @param x xcoordinate of given point
	 * @param y ycoordinate of given point
	 *
	 */
	
	public boolean contains(int x, int y) {
		if( (x >= bounds.x && x<= bounds.x + bounds.width) && (y >= bounds.y && y<= bounds.y + bounds.height) ) {

			return true;
		}
		else {

			return false;
		}
	}


	/**
	 * Gives the size of the city
	 */
	@Override
	public int getSize() {

			return bounds.width;
		
	}

	/**
	 * Gives the color of the city
	 */
	@Override
	public String getColor() {
		// TODO Auto-generated method stub
		return color;
	}
}
