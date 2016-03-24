package main;

import model.MP;
import model.Model;
import model.swing.SwingAnimatorBuilder;

public class Control {
	Control(){}
	public void run() {
		Model m = new Model(new SwingAnimatorBuilder(), MP.getRows(), MP.getColumns());
//		m.run(MP.simulationRuntime);
		m.run(Integer.MAX_VALUE);
		m.dispose();
	}
}
