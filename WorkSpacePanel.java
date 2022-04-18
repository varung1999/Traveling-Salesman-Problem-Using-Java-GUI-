

import javax.swing.*;


import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.Observable;
import java.util.Observer;
import java.util.TimerTask;

/**
 * A panel to display the cities and the route between them. It allows 
 * marking of a new city with a mouse click and also
 * allows to move an existing city to a new location by clicking the city 
 * and dragging it to a new location.
 * <p>
 * It implements the <tt>Observer</tt> interface so that it can observe 
 * an <tt><Observable</tt> (in our case, it is the
 * TSP class) and redraw the panel.
 *
 * @author Zhuoran Li, Rishav Kumar
 * @version 1.0
 * @since 2021-10-02
 * 
 * 
 * @author Chandana Bandlamudi
 * @version 2.0
 */
public class WorkSpacePanel extends JPanel implements MouseListener, MouseMotionListener, Observer {
    private final WorkSpace workSpace;
    ArrayList<ArrayList<City>> userconnect= new ArrayList<ArrayList<City>>();
    ArrayList<ArrayList<Route>> routeList = new ArrayList<ArrayList<Route>>();
    City clickedCity = null;
    int preX, preY;
    City citySelected;
    
    boolean b = false;
    int p =0;
    int eventCount = 0;
    boolean move = false;
    boolean create = false;
	java.util.Timer timer = new java.util.Timer("doubleClickTimer", false);
	boolean flag = false;//new change
	
    /**
     * Default constructor. It initializes the <code>workSpace</code> object and 
     * defines the listeners for the mouse
     * actions.
     */
	
    public WorkSpacePanel(WorkSpace workSpace) {
        this.workSpace = workSpace;
        addMouseMotionListener(this);
        addMouseListener(this);
    }
    /**
     * when we click the userconnect the move and the create should block
     */
    
    public void userconnect()
    {
    	create= false;
    	flag = true;
    	move = false;
    }
    /**
     * when we click the connect the move and the create should block
     */
    public void connect()
    {
    	create = false;
    	flag = true;
    	move = false;
    }
    
    /**
     * clearing routes for every city
     */
    public void clearRoutes()
    {
    	flag = false;
    	routeList.clear();
    	userconnect.clear();
    	
    }
    
    /**
     *clearing the royte of userconnect
     */
    public void clear()
    {
    	flag = false;
    	userconnect.clear();
    }
    /**
     * clearing workspace
     */
    public void clearWorkspace()
    {
    	workSpace.getCityList().clear();
    	repaint();
    }
    /**
     * when we click on move we have to enable move and disable the create
     */
    public void moving()
    {
    	create = false;
    	move = true;
    }
    /**
     * when we click on create we have to enable create and disable the move and connect
     */
    
    public void create()
    {
    	flag = false;
    	move = false;
    	create = true;
    }
    /**
     * it will block all the actions
     */
    public void block()
    {
    	create = false;
    	move = false;
    	flag = false;
    }
    
    /**
     * Plots the cities (as rectangles) and routes (as lines) onto 
     * the display area. The city names are also displayed
     * alongside the cities.
     *
     * @param g graphics object to plot the content
     */
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;

        for (City city : workSpace.getCityList())
            city.draw(g2);
         routeList = workSpace.getRouteList();
        if (routeList != null && routeList.size() > 0) {
            for (ArrayList<Route> routes : routeList)
                for (Route route: routes)
                route.getSrc().drawConnect(route.getDest(), g2);
        }

        if(flag && b)
		{
			ArrayList<City> user = new ArrayList<City>();
			user.add(clickedCity);
			user.add(workSpace.getCityList().get(p));

			userconnect.add(user);

			for(int i =0;i< userconnect.size();i++)
			{
				int j=0;
				userconnect.get(i).get(j).drawConnect(userconnect.get(i).get(j+1), g2);
			}
			b = false;
		}

    }

    /**
     * This method is called whenever the observed object is changed.
     *  An application calls an <tt>Observable</tt>
     * object's <code>notifyObservers</code> method to have all the 
     * object's observers notified of the change.
     * <p>
     * This function gets the route information from an <tt>Observable</tt> 
     * and call the <code>repaint</code> function
     * to re-draw the panel.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    
    
    @Override
    public void update(Observable o, Object arg) {
        if (((Algorithms)o).getRouteList()!=null) {
            workSpace.setRouteList(((Algorithms) o).getRouteList());
        }else
            workSpace.setRouteList(new ArrayList<>());
        repaint();
    }

    /**
     * Invoked when the mouse button has been clicked (pressed and released) 
     * on a component.
     *
     * @param e the event to be processed
     */
    public void mouseClicked(final MouseEvent e) {
    	
		eventCount = e.getClickCount();
		if(e.getClickCount()==1)
		{
			timer.schedule(new TimerTask() {

				public void run()
				{
					
					if(eventCount>1)
					{
						//more than one click count
						int index=-1;
						 for (int i = 0; i < WorkSpace.getCityList().size(); i++) {
					            if (WorkSpace.getCityList().get(i).contains(e.getX(), e.getY())) {
					                citySelected = WorkSpace.getCityList().get(i);
					                index= i;
					            }
					        }
						 	cityedited(citySelected, index);
						repaint();
						
					}
					eventCount = 0;
				}

			},400);
		}
	}
    
    /**
     * it will edit the city object by adding all the editing features
     */

    protected void cityedited(City citySelected, int i) {
String[] color = new String[] {"Lavender", "black", "yellow", "green", "pink"};
		
		String[] shape = new String[] {"Square", "Circle", "Muiltiple square", "Multiple Square with circle"};
		
		String[] colorcodes = new String[] {"#B11FF9", "#050505", "#FBE812", "#76FB12", "#FB12E5"};
		
		Integer[] size = new Integer[] {10, 20, 30, 40, 50};
		
		
		JComboBox<String> c1 = new JComboBox<String>(shape);
		JComboBox<String> c2 = new JComboBox<String>(color);
		JComboBox<Integer> c3 = new JComboBox<Integer>(size);
	
		
		Object[] c = {
				"Choose a size", c3,
				"Choose a color", c2,
				"Choose a decorator", c1
		};
		
		JOptionPane.showConfirmDialog(null, c, "City Edit ", JOptionPane.OK_CANCEL_OPTION);
		int d = c1.getSelectedIndex();
		String Color =colorcodes[c2.getSelectedIndex()];
		int Size1 = c3.getSelectedIndex();
		int Size = size[Size1];		
		City city ;
		
		if(d == 0)
		{
			city = new City(citySelected.getLabel(),citySelected.getX(), citySelected.getY(),Size, Size, Size, Color);
		}
		else
		{
			city = new City(citySelected.getLabel(),citySelected.getX(), citySelected.getY(),Size, Size, Size, Color, d);
		}
		int index= i;
		if(city != null) {
			workSpace.getCityList().remove(index);
			workSpace.getCityList().add(index, city);
			repaint();	
		}
		
	}

	/**
     * Invoked when a mouse button has been pressed on a component.
     * <p>
     * A new city is marked when the user clicks on an empty space, 
     * else if the user clicks on a city, the city movement
     * operation is initiated.
     *
     * @param e the event to be processed
     */
    @Override
    public void mousePressed(MouseEvent e) {
        clickedCity = workSpace.getCityList().stream()
                .filter(x -> x.contains(e.getX(), e.getY()))
                .findFirst().orElse(null);
        if(create)
        {
        if (clickedCity == null) {

            String cityName = JOptionPane.showInputDialog(this, "Enter City Name");
            if (cityName != null && !cityName.isEmpty()) {
                City city = new City(cityName, e.getX(), e.getY(), WorkSpace.DEFAULT_CITY_WIDTH,
                        WorkSpace.DEFAULT_CITY_HEIGHT, 20, "#FCFAFC");
                workSpace.addNewCity(city);
            }
        }
        }
        
    }

    /**
     * Invoked when a mouse button is pressed on a component and 
     * then dragged. {@code MOUSE_DRAGGED} events will
     * continue to be delivered to the component where the 
     * drag originated until the mouse button is released
     * (regardless of whether the mouse position is within 
     * the bounds of the component).
     * <p>
     * Due to platform-dependent Drag&amp;Drop implementations,
     * {@code MOUSE_DRAGGED} events may not be delivered during
     * a native Drag&amp;Drop operation.
     * <p>
     * If a city movement is in progress, it updates the co-ordinates of the 
     * moving city based on the current cursor
     * location on screen.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseDragged(MouseEvent e) {
    	if(move)
    	{
        if (clickedCity != null)
            workSpace.moveExistingCity(clickedCity, preX + e.getX(), preY + e.getY());
    	}
    }

    /**
     * Invoked when the mouse cursor has been moved onto a component but no 
     * buttons have been pushed.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseMoved(MouseEvent e) { }

    /**
     * Invoked when a mouse button has been released on a component.
     * <p>
     * Commits the city movement operation and updates the city co-ordinates 
     * to the current cursor location on screen.
     *
     * @param e the event to be processed
     */
    @Override
public void mouseReleased(MouseEvent e) {
    	
    	if(flag)
    	{
        	p = UserConnect.Release(e, WorkSpace.getCityList());
        	b= true;
    	}
    	if(move)
    	{
    		 
    		        if (clickedCity != null && clickedCity.contains(e.getX(), e.getY())) {
    		            workSpace.moveExistingCity(clickedCity, preX + e.getX(), preY + e.getY());
    		            clickedCity = null;
    		        }
    		    
    	}
    	repaint();
        
    }
    /**
     * Invoked when the mouse enters a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseEntered(MouseEvent e) { }

    /**
     * Invoked when the mouse exits a component.
     *
     * @param e the event to be processed
     */
    @Override
    public void mouseExited(MouseEvent e) { }
}