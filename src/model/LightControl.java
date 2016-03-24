package model;

class LightControl implements Agent{
	
	private TimeServer _time;
	private Model _observer;
	private boolean _state;
	private double _tik;
	private Light _light;
	
	LightControl(Light l){
		_light = l;
	}
	
	public void passTimeServer(TimeServer time) {
		_time = time;
	}

	public void passObserver(Model observer) {
		_observer = observer;
	}

	public void run(double time) {
		if (_tik < time) {
			_tik ++;
			if (_tik % 40 == 0) {
//				System.out.println("time is " + _tik);
				_state = !_state;
				_light.setState(_state);
			}
			_time.enqueue(_time.currentTime()+MP.simulationTimestep, this);
		}
	}

}
