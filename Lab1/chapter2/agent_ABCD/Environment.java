package chapter2.agent_ABCD;

import java.util.ArrayList;
import java.util.List;

public class Environment {
	public static final Action MOVE_LEFT = new DynamicAction("LEFT");
	public static final Action MOVE_RIGHT = new DynamicAction("RIGHT");
	public static final Action MOVE_UP = new DynamicAction("UP");
	public static final Action MOVE_DOWN = new DynamicAction("DOWN");
	public static final Action SUCK_DIRT = new DynamicAction("SUCK");
	public static final String LOCATION_A = "A";
	public static final String LOCATION_B = "B";
	public static final String LOCATION_C = "C";
	public static final String LOCATION_D = "D";
	public static long point = 0;

	public enum LocationState {
		CLEAN, DIRTY
	}

	private EnvironmentState envState;
	private boolean isDone = false;// all squares are CLEAN
	private Agent agent = null;
	private String[][] matrix = {{Environment.LOCATION_A, Environment.LOCATION_B}, {Environment.LOCATION_C, Environment.LOCATION_D}};
	private List<String> list = new ArrayList<>();
	private int currentLocation ;
	
	public void add() {
		list.add(LOCATION_A);
		list.add(LOCATION_B);
		list.add(LOCATION_C);
		list.add(LOCATION_D);
	}
	
	public Environment(LocationState locAState, LocationState locBState, LocationState locCState, LocationState locDState) {
		envState = new EnvironmentState(locAState, locBState, locCState,locDState);
	}

	// add an agent into the enviroment
	public void addAgent(Agent agent, String location) {
		this.agent = agent;
		envState.setAgentLocation(location);
	}
	public EnvironmentState getCurrentState() {
		return this.envState;
	}

	// Update enviroment state when agent do an action
	public EnvironmentState executeAction(Action action) {
		if(action == Environment.SUCK_DIRT) {
			envState.setLocationState(envState.getAgentLocation(), Environment.LocationState.CLEAN);
		}else {
			findLocationOfAgent(action);
		}
		return envState;
	}
	public void findLocationOfAgent(Action action) {
		// current location of Agent - Vi tri hien tai cua Agent
		double index = checkIndex(envState.getAgentLocation());
		int i = (int) index;
		int j = (int) (index*10)%10;
		if(action == Environment.MOVE_LEFT) {
			envState.setAgentLocation(matrix[i][j-1]);
		}else if(action == Environment.MOVE_RIGHT) {
			envState.setAgentLocation(matrix[i][j+1]);
		}else if(action == Environment.MOVE_DOWN) {
			envState.setAgentLocation(matrix[i+1][j]);
		}else if(action == Environment.MOVE_UP) {
			envState.setAgentLocation(matrix[i-1][j]);
		}
	}
//	/** Get index in location
//	A B
//	C D
//	A=0, B=1, C=2, D=3
//**/
//	public void findLocationOfAgentZ(Action action) {
//		// current location of Agent - Vi tri hien tai cua Agent
//		double index = list.indexOf(action);
//		if(action == Environment.MOVE_LEFT) {
//		}else if(action == Environment.MOVE_RIGHT) {
//			envState.setAgentLocation(matrix[i][j+1]);
//		}else if(action == Environment.MOVE_DOWN) {
//			envState.setAgentLocation(matrix[i+1][j]);
//		}else if(action == Environment.MOVE_UP) {
//			envState.setAgentLocation(matrix[i-1][j]);
//		}
//	}
	/** Get index in location matrix
			A B
			C D
	**/
	public double checkIndex(String location) {
		int i;
		int j;
		for ( i = 0; i < matrix.length; i++) {
			for (j = 0; j < matrix.length; j++) {
				if(matrix[i][j].equals(location)) {
					return i + (double)j/10.0;
				}
			}
		}
		return -1;
	}
	// get percept<AgentLocation, LocationState> at the current location where agent
	// is in.
	public Percept getPerceptSeenBy() {
		Percept p = new Percept(envState.getAgentLocation(), envState.getLocationState(envState.getAgentLocation()));
		return p;
	}

	public void step() {
		envState.display();
		String agentLocation = this.envState.getAgentLocation();
		Action anAction = agent.execute(getPerceptSeenBy());
		EnvironmentState es = executeAction(anAction);
//		while(es.getLocationState(es.getAgentLocation())==Environment.LocationState.CLEAN) {
//			anAction = agent.execute(getPerceptSeenBy());
//			es = executeAction(anAction);
//		}

		System.out.println("Agent Loc.: " + agentLocation + "\tAction: " + anAction);

		if ((es.getLocationState(LOCATION_A) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_B) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_C) == LocationState.CLEAN)
				&& (es.getLocationState(LOCATION_D) == LocationState.CLEAN))
			isDone = true;// if both squares are clean, then agent do not need to do any action
		es.display();
	}

	public void step(int n) {
		for (int i = 0; i < n; i++) {
//			step();
			stepUntilDone();
			
		}
	}

	public void stepUntilDone() {
		int i = 0;

		while (!isDone) {
			System.out.println("step: " + i++);
			step();
			System.out.println("-------------------------");
		}
	}
}
