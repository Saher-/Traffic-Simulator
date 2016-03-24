package model;

import java.util.ArrayList;
import java.util.List;


/**
 * A light has a boolean state.
 */

public class Light implements CarAcceptor {
	private CarAcceptor _nxt;
	private List<Car> _cars = new ArrayList<Car>();
	private boolean _state;
	private LightControl _controler;
	
	Light(CarAcceptor nxt) {
		_nxt = nxt;
	}		


	public boolean accept(Car c, double position) {
		_cars.remove(c);
		System.out.println("Car # " + c.getCarID() + "is at INTERSECTION");
		if (c.getRoad().getDirection() == Direction.sn) {
			if (_state == true) {
				return _nxt.accept(c, 0);
			}
			else {
				c.setPosition(position);
				_cars.add(c);
				c.setCurrentRoad(this);
				return false;
			}
		}
		if (c.getRoad().getDirection() == Direction.ew) {
			if (_state != true) {
				return _nxt.accept(c, 0);
			}
			else {
				c.setPosition(position);
				_cars.add(c);
				c.setCurrentRoad(this);
				return false;
			}
		}
		else {
			return false;
		}
	}


	public CarAcceptor next() {
		return _nxt;
	}
	
	
	public boolean getState () {
		return _state;
	}
	
	
	public void setState (boolean nw) {
		_state = nw;;
	}

	//No need for observer
	public void passObserver(Model m) {	}

	// ID is traffic light = it's a traffic light
	public String getID() {
		return "Traffic Light";
	}

	
	public List<Car> getCars() {
		return null;
	}

	//No intersection length yet
	public double getLength() {
		return 0;
	}

	//No need for direction
	public Direction getDirection() {
		return null;
	}

	//NOT WORKING YET
	public double distanceToObstacle(double d) {
		return 0;
	}
}