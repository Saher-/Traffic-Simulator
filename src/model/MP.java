package model;

public class MP {
	private MP() {}
	  public static double simulationTimestep = 50; //0.1D;
	  public static int simulationRuntime = 1000;

	  private static int rows = 4;
	  private static int columns = 4;

	  public static boolean isAlternating = true;

	  public static double carLength = 10.0D;

	  public static double roadDrawLength = 200.0D;

	  public static double minroadLength = 100.0D;
	  public static double maxroadLength = 150.0D;

	  public static double roadLength = Math.random() * (maxroadLength - minroadLength) + minroadLength;

	  public static double minVelocity = 1.0;
	  public static double maxVelocity = 5.0;

	  public static double minCarGeneration = 10.0D * getTimeStep();
	  public static double maxCarGeneration = 25.0D * getTimeStep();

	  public static double minCarLength = 10.0D;
	  public static double maxCarLength = 15.0D;

	  public static double minGreenLightTime = 30.0D;
	  public static double maxGreenLightTime = 180.0D;

	  public static double minYellowLightTime = 4.0D;
	  public static double maxYellowLightTime = 5.0D;

	  public static double minIntersectionLength = 10.0D;
	  public static double maxIntersectionLength = 15.0D;

	  public static double minBreakDistance = 9.0D;
	  public static double maxBreakDistance = 10.0D;
	  public static double breakFactor = 0.5D;

	  public static double minStopDistance = 0.5D;
	  public static double maxStopDistance = 5.0D;
	  public static double stopFactor = 0.0D;

	  public static int getRows() {
		  return rows;
	  }
	  
	  private static double getTimeStep() {
		return simulationRuntime;
	}

	public static int getColumns() {
		  return columns;
	  }
}
