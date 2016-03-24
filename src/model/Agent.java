package model;

public interface Agent {
	public void passTimeServer (TimeServer time);
	public void passObserver(Model observer);
	public void run(double duration);
}
