package model;

import java.util.List;

public interface CarAcceptor {
	public boolean accept (Car c, double position);
	
	public void passObserver (Model m);
	
	public double distanceToObstacle (double d);
	
	public CarAcceptor next ();
	
	public boolean getState();

	public List<Car> getCars();

	public double getLength();
	
	public Direction getDirection();
	
	public String getID();
}