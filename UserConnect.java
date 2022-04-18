

import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Chandana Bandlamudi
 * @version 1.0
 *
 */
public class UserConnect implements AlgoritmsInterface{
    static int p;
	@Override
    public ArrayList<ArrayList<Route>> algorithmInterface(List<City> cityList) {
        return null;
    }
	/**
	 * when we click the userConnect in the menu bar ,
	 * it will invoke this class and the 
	 * release method when the mouse is released	 
	 * 
	 */
    public static int Release(MouseEvent e ,List<City> cities )
	{

			for (int k = 0 ;k < cities.size();k++)
			{

				if(cities.get(k).contains (e.getX(), e.getY()))
				{
					p =k;
				}
			}
			
			return p;
			

	}
}
