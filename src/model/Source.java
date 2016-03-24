package model;

import java.util.ArrayList;
import java.util.List;

public class Source implements Agent{
	private Road _R;
	private Model _Observer;
	private TimeServer _time;
	private double _position = 0.0;
	private Car _car; 
	private double _tik;
	private List<Agent> _cars = new ArrayList<>();
	


	Source(Road r) {
		_R = r;
	}


	public void passTimeServer(TimeServer time) {
		_time = time;
	}


	public void passObserver(Model observer) {
		_Observer = observer;
	}
	

	public void run(double duration) {
		if (_tik < duration) {
			_tik ++;
			if (_tik %40 == 0) {
			_car = new Car(_R);
			_cars.add(_car);
			}
			for (Car a : _cars.toArray(new Car[0])) {
				a.passObserver(_Observer);
				a.passTimeServer(_time);
				a.run(_tik);
//				_time.enqueue(_time.currentTime()+10, this);
		}
		_tik+=MP.simulationTimestep;
		}
	}
}
