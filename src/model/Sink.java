package model;

import java.util.List;

public class Sink implements CarAcceptor {

	private Direction _dir;
	
	public boolean accept(Car c, double position) {
		_dir = c.getRoad().getDirection();
		System.out.println("Car # " + c.getCarID() + "is at SINK ... BYEee");
		c = null;
		return false;
	}

	
	public CarAcceptor next() {
		return null;
	}
	
	//Always true
	public boolean getState() {
		if (_dir == Direction.sn)
			return true;
		if (_dir == Direction.ew)
			return false;
		else
			return true;
	}

	//No need for observer
	public void passObserver(Model m) {	}

	
	public List<Car> getCars() {
		return null;
	}
	
	//ID is Sink = it's a sink
	public String getID() {
		return "Sink";
	}

	//No need for length
	public double getLength() {
		return 0;
	}

	//No need for direction
	public Direction getDirection() {
		return null;
	}


	//No need for this
	public double distanceToObstacle(double d) {
		return 0;
	}

}
