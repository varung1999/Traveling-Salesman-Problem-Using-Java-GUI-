

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import javax.swing.JOptionPane;

/**
 *
 * @author Chandana Bandlamudi 1220495432
 * version 1.0
 */
public class Cluster implements AlgoritmsInterface{

	/**
	 * 
	 * Takes input the ArrayList of city and gives output the ArrayList of ArrayList of route
	 * The ArrayList of ArrayList of route contains the clusters
	 *
	 */
    public ArrayList<ArrayList<Route>> algorithmInterface(List<City> cities) 
    {
    	try {

        int numOfClusters =(Integer.parseInt( JOptionPane.showInputDialog("Enter the Number of Clusters")));
    
        int citySize = cities.size();

        ArrayList<City> centroids = new ArrayList<City>();
        ArrayList<ArrayList<City>> routelist = new ArrayList<ArrayList<City>>();
        if(numOfClusters > citySize)
        {
            JOptionPane.showInputDialog("We can't have more clusters then the citysize");
        }
        else
        {
            for(int i = 0 ; i < numOfClusters ; i++)
            {
                centroids.add(cities.get(i));
                ArrayList<City> routes = new ArrayList<City>();
                routes.add(cities.get(i));
                routelist.add(routes);
            }
            for (int i = numOfClusters; i < citySize; i++)
            {
                ArrayList<Double> distanceMatrix = new ArrayList<Double>();
                for(int j =0 ; j < numOfClusters ; j++)
                {
                    distanceMatrix.add(calculateDistance(cities.get(i), centroids.get(j)));

                }
                int indexOfMinimum = distanceMatrix.indexOf(Collections.min(distanceMatrix));
                routelist.get(indexOfMinimum).add(cities.get(i));
                City c = centroids.remove(indexOfMinimum);
                City newCity = new City(c.getLabel(), (c.getX()+cities.get(i).getX())/2, (c.getY()+cities.get(i).getY())/2 , 10,10, 20, "#FCFAFC");
                centroids.add(indexOfMinimum, newCity);
            }


        }
        for( int i =0;i <numOfClusters; i++)
        {
            routelist.get(i).add(centroids.get(i));
        }
        
        ArrayList<ArrayList<Route>> RouteList = new  ArrayList<ArrayList<Route>>();
        for( int i =0; i < routelist.size(); i++)
        {
        	ArrayList<Route> r = new ArrayList<Route>();
        	for (int j = 0 ; j< routelist.get(i).size();j++)
        	{
        		Route route = new Route();
        		route.setSrc(centroids.get(i));
        		route.setDest(routelist.get(i).get(j));
        		route.setDist(0);
        		r.add(route);
        	}
        	RouteList.add(r);
        }
        return RouteList;
    	}
    	catch(Exception e)
    	{
    		return null;
    	}
    }

    /**
	 * 
	 * Caluclates the distance between the points
	 *
	 */
    public double calculateDistance(City src, City dest)
    {
        double x1 = src.getX(), y1 = src.getY(), x2 = dest.getX(), y2 = dest.getY();
        return Math.sqrt((x1 + x2) * Math.abs(x1 - x2) + (y1 + y2) * Math.abs(y1 - y2));
    } 

}
