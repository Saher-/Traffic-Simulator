package model;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.Queue;

import util.Animator;
import model.Direction;

/**
 * An example to model for a simple visualization. The model contains roads
 * organized in a matrix. See {@link #Model(AnimatorBuilder, int, int)}.
 */
public class Model extends Observable {
	private List<CarAcceptor> _ewroads;
	private List<CarAcceptor> _snroads;
	private List<Agent> _agents;
	private Animator _animator;
	private boolean _disposed;
	private double _time=0;
	private double _tik = 0.0;

	/**
	 * Creates a model to be visualized using the <code>builder</code>. If the
	 * builder is null, no visualization is performed. The number of
	 * <code>rows</code> and <code>columns</code> indicate the number of
	 * {@link Light}s, organized as a 2D matrix. These are separated and
	 * surrounded by horizontal and vertical {@link Road}s. For example, calling
	 * the constructor with 1 row and 2 columns generates a model of the form:
	 * 
	 * <pre>
	 *     |  |
	 *   --@--@--
	 *     |  |
	 * </pre>
	 * 
	 * where <code>@</code> is a {@link Light}, <code>|</code> is a vertical
	 * {@link Road} and <code>--</code> is a horizontal {@link Road}. Each road
	 * has one {@link Car}.
	 * 
	 * <p>
	 * The {@link AnimatorBuilder} is used to set up an {@link Animator}.
	 * {@link AnimatorBuilder#getAnimator()} is registered as an observer of
	 * this model.
	 * <p>
	 */
	public Model(AnimatorBuilder builder, int rows, int columns) {
		if (rows < 0 || columns < 0 || (rows == 0 && columns == 0)) {
			throw new IllegalArgumentException();
		}
		if (builder == null) {
			builder = new NullAnimatorBuilder();
		}
		_ewroads = new LinkedList<CarAcceptor>();
		_snroads = new LinkedList<CarAcceptor>();
		_agents = new ArrayList<>();
		setupH(builder, rows, columns);
		setupV(builder, rows, columns);
		_animator = builder.getAnimator();
		super.addObserver(_animator);
	}

	/**
	 * Run the simulation for <code>duration</code> model seconds.
	 */
	public void run(int duration) {
		if (_disposed)
			throw new IllegalStateException();
		TimeServer time = new TimeServerLinked();
		boolean first = true;
		while (! time.empty() || first == true)
		{
				first = false;
				for (Agent a : _agents.toArray(new Agent[0])) {
					time.enqueue(0,	a);
					a.passTimeServer(time);
					a.passObserver(this);
					a.run(duration);
				}  
		super.setChanged();
		super.notifyObservers();
		}
	}

	/**
	 * Throw away this model.
	 */
	public void dispose() {
		_animator.dispose();
		_disposed = true;
	}

	
	
	/**
	 * Construct the model, establishing correspondences with the visualizer.
	 */
	/**
	 * @param builder
	 * @param rows
	 * @param columns
	 */
	
	//ADD VERTICAL
	private void setupH(AnimatorBuilder builder, int rows, int columns) {
		for (int i = 0; i <= rows; i++) {
			Road r = new Road (Direction.ew, new Sink());
			builder.addHorizontalRoad(r, i-1, 0, true);
			System.out.print("Sink (0,"+i+") <- RoadH (0,"+i+")");
			for (int j = 0; j <= columns; j++) {
				Light l = new Light(r);
				builder.addLight(l, i-1, j-1);
				LightControl lc = new LightControl(l);
				_agents.add(lc);
				System.out.print(" <--- Light ("+j+","+i+")");
				r = new Road (Direction.ew, l);
				builder.addHorizontalRoad(r, i-1, j, true);
				System.out.print(" <--- RoadH ("+j+","+i+")");
			}
			Source s = new Source (r);
			_agents.add(s);
			System.out.println(" <--- Source");
		}
	}
	
	//ADD HORIZONTAL 
	private void setupV(AnimatorBuilder builder, int rows, int columns) {
		 
		for (int i = 0; i < rows; i++) {
			Road r = new Road (Direction.sn, new Sink());
			builder.addVerticalRoad(r, 0, i, true);
			System.out.print("Sink (0,"+i+") <- RoadV (0,"+i+")");
			for (int j = 0; j < columns; j++) {
				Light l = new Light(r);
				builder.addLight(l, j, i);
				LightControl lc = new LightControl(l);
				_agents.add(lc);
				System.out.print(" <--- Light ("+j+","+i+")");
				r = new Road (Direction.sn, l);
				builder.addVerticalRoad(r, j+1, i, true);
				System.out.print(" <--- RoadV ("+(j+1)+","+i+")");
				if (j+1 == columns) {
					Source s = new Source (r);
					_agents.add(s);
					System.out.println(" <--- Source");
				}
			}
		}
	}


	public  List<Agent> getAgents() {
		return _agents;
	}
	

	public Model getObserver() {
		return this;
	}
}