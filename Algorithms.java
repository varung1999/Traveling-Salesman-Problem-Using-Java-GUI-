

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

/**
 * Algorithms class is an entrance of the algorithms part and a data center.
 * <p>
 * For the algorithms part, It uses strategy pattern to define a family of algorithms,
 * encapsulate each one, and make them interchangeable. Besides, it implements observer
 * and observable to make this class become to the data center where the algorithms receive
 * the cityList and return the routeList.
 *
 * @author Zhuoran Li
 * @version 1.0
 * @since 2021-11-15
 */
public class Algorithms extends Observable implements Observer {
    private AlgoritmsInterface abstractAlgorithm = null;
    private ArrayList<ArrayList<Route>> routeList;
    private List<City> cityList;
    /**
     * This method is called whenever the observed object is changed. An
     * application calls an <tt>Observable</tt> object's
     * <code>notifyObservers</code> method to have all the object's
     * observers notified of the change.
     *
     * @param o   the observable object.
     * @param arg an argument passed to the <code>notifyObservers</code>
     */
    @Override
    public void update(Observable o, Object arg) {
        cityList = ((WorkSpace) o).getCityList();
        if (cityList != null) {
            if (abstractAlgorithm != null) {
                routeList = abstractAlgorithm.algorithmInterface(cityList);
            } else {
                routeList = new ArrayList<>();
            }
        }else {
            routeList = new ArrayList<>();
        }
        setChanged();
        notifyObservers();

    }

    /**
     * This method is where set which algorithms user want to run.
     * @param algorithmsInterface algorithm
     */
    public void setAlgorithms(AlgoritmsInterface algorithmsInterface) {
        abstractAlgorithm = algorithmsInterface;
            if (cityList != null) {
                if (abstractAlgorithm != null) {
                    routeList = algorithmsInterface.algorithmInterface(cityList);
                } else
                    routeList = new ArrayList<>();
            }else
                routeList = new ArrayList<>();
        setChanged();
        notifyObservers();
    }

    public ArrayList<ArrayList<Route>> getRouteList(){
        return routeList;
    }
}