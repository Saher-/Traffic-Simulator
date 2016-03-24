package model;

public interface TimeServer {
	public double currentTime();
	public void enqueue(double waketime, Agent thing);
	public Agent dequeue();
	public void run(double duration);
	public void addObserver(java.util.Observer o);
	public void deleteObserver(java.util.Observer o);
	public boolean empty();
}
