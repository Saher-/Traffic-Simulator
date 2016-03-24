package model;

import java.awt.Color;
import java.util.Observable;
import java.util.Observer;

public class Car implements Agent {
	private double _maxVelocity;
	private double _velocity;
	private double _breakDistance = MP.maxBreakDistance; //Math.random() * (MP.maxBreakDistance - MP.minBreakDistance) + MP.minBreakDistance;
	private double _stopDistance = Math.random() * (MP.maxStopDistance - MP.minStopDistance) + MP.minStopDistance;
	private double _length;
	private double _position=0;
	private double _nextFrontPosition;
	private CarAcceptor _R;
	private Model _observer;
	private TimeServer _time;
	private boolean _direction;
	private int _ID;
	private Car r;
	private Light _l;
	private static int _count = 0; 
	private java.awt.Color _color = new java.awt.Color((int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255),(int)Math.ceil(Math.random()*255));
	
	public Car(Road r){
		_R = r;
		_maxVelocity = (Math.random() * (MP.maxVelocity - MP.minVelocity) + MP.minVelocity);
		_ID = ++ _count;
	}

	public void run(double duration) {
		boolean status = true;
		if (_R.getDirection() == Direction.sn) {
			
			if (_R.next().getState()) {
				_nextFrontPosition = _position + _maxVelocity;
				_position = _nextFrontPosition;
				_R.accept(this, _position);
			}
			else {
				_position = slowDown (_position);
//				_nextFrontPosition = _position + 0.5;
//				_position = _nextFrontPosition;
				_R.accept(this, _position);
			}
		}
		else {
			if (! _R.next().getState()) {
				_nextFrontPosition = _position + _maxVelocity;
				_position = _nextFrontPosition;
				_R.accept(this, _position);
			}
			else {
				_position = slowDown(_position);
//				_nextFrontPosition = _position + 0.5;
//				_position = _nextFrontPosition;
				_R.accept(this, _position);
			}
		}
		System.out.println(this.toString());
	}

	private double slowDown (double v) {
		if (v <= _R.getLength()-15)
			return v + MP.breakFactor;
		else //(v > _R.getLength()-15)
			return v+MP.stopFactor;
	}
	
	public void passTimeServer (TimeServer time) {
		_time = time;
	}
	
	public CarAcceptor getRoad() {
			return _R;
	}
	
	double backPosition() {
		return _position - _length;
	}

	public Color getColor() {
		return _color;
	}

	public double getPosition() {
		return _position;
	}
	
	public double getCarLength() {
		return _length;
	}
	
	public int getCarID() {
		return _ID;
	}
	
	public void setSpeed(double speed) {
	   _maxVelocity = speed;
	 }

	public void setPosition(double pos) {
		_position = pos;
	}
	
	public void setCurrentRoad (CarAcceptor r) {
		_R = r;
	}

	public void passObserver(Model m) {
		_observer = m;
		_R.passObserver (_observer);
		
	}

	public double getBreakDistance() {
		return _breakDistance;
	}
	
	public String toString() {
		StringBuilder id = new StringBuilder();
		id.append("Car #");
		id.append(_ID);
		id.append(" is at Road ");
		id.append(_R.getID());
		id.append(" - position [");
		id.append(this._position);
		id.append(" -> ");
		id.append(this._nextFrontPosition);
		id.append("]");
		return id.toString();
		
	}
}
