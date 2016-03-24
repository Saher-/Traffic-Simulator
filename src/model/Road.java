package model;

import java.util.List;
import java.util.ArrayList;
import java.util.Set;

public class Road implements CarAcceptor {
	private double _length = MP.roadLength; //Math.random();
	private Direction _dir;
	private CarAcceptor _nxt;
	private Road _nextRoad;
	private String _id;
	private double _endPosition;
	private Model _observer;
	private List<Car> _cars = new ArrayList<Car>();
	private double endPosition;
	
	Road (Direction d, CarAcceptor a) {
//		_id = id;
		_nxt = a;
		_dir = d;
	}
	

	public boolean accept(Car c, double position) {
		if (c == null)
			return false;
		_cars.remove(c);
		if (position > _length) {
			return _nxt.accept(c, 0);
		}
		else {
			c.setCurrentRoad(this);
			c.setPosition(position);
			_cars.add(c);
//			System.out.println("Car Accepted.");
			return true;
		}
	} 
	
	
	public List<Car> getCars() {
		return _cars;
	}
	
	
	public double getLength() {
		return _length;
	}
	
	
	public String getID() {
		StringBuilder id = new StringBuilder();
		id.append("Direction ");
		id.append(_dir);
		return id.toString();
	}
	
	
	public Direction getDirection() {
		return _dir;
	}
	
	
	public void passObserver (Model m) {
		_observer = m;
	}
	
	
	public CarAcceptor next () {
		return _nxt;
	}
	
	
	public boolean getState() {
		return true;
	}
	
	
	private double distanceToCarBack(double frontPosition){
		double carBackPosition = Double.POSITIVE_INFINITY;
		for(Car c : _cars) {
			
			if(c.backPosition() >= frontPosition && c.backPosition() < carBackPosition)
				carBackPosition = c.backPosition();
		}
		return carBackPosition;
	}

	//NOT WORKING YET
	public double distanceToObstacle(double frontPosition) {
		double obstaclePosition =  distanceToCarBack(frontPosition);
  		if(obstaclePosition == Double.POSITIVE_INFINITY) {
			//double distanceToEnd = frontPosition - this._length; // What is this used for?
			if(_nxt.getClass() != Sink.class)
				obstaclePosition = _nxt.distanceToObstacle(frontPosition - this._length);
  				System.out.println("Back from recursive call");
			//obstaclePosition = _next.distanceToObstacle(this._length - frontPosition);
			//going to try the following
			//obstaclePosition = _next.distanceToObstacle(frontPosition);
		}
  		
  		//handle recursive cases where frontPosition is always negative
  		if(frontPosition < 0)
  			return obstaclePosition + this.getLength();
  		else
  			return obstaclePosition - frontPosition;
	}
}
