

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;

/**
* 
* @author Srikar Vodeti 1223253239
* @author Chandana Bandlamudi 1220495432
* version 1.0
* 
* @author Chandana Bandlamudi 1220495432
* version 2.0
*/ 
public class City {
	private Decorator bounds;
	private String label;
	
	public City(String label, int x, int y, int w, int h, int size, String color) {
		bounds = new Square(label, x, y, size, color);
		this.label = label;
	}
	
	public City(String label, int x, int y,int w, int h, int size, String color, int d) {
		this.label = label;
		if(d == 1) {
			bounds = new CircleSquare(new Square(label,x,y,size,color));
		}
		else if(d == 3) {
			bounds = new CircleMultipleRectangle(new MultipleRectangle(new Square(label,x,y,size,color)));
		}
		else if(d == 2) {
			bounds = new MultipleRectangle(new Square(label,x,y,size,color));
		}	
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
}

