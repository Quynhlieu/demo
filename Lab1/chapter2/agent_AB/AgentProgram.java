package chapter2.agent_AB;

public class AgentProgram {

	public Action execute(Percept p) {// location, status
		Action re = NoOpAction.NO_OP;
		if(p.getLocationState().equals(Environment.LocationState.DIRTY)) {
			return re = Environment.SUCK_DIRT;
		}else if(p.getAgentLocation().equalsIgnoreCase(Environment.LOCATION_A)) {
			return re = Environment.MOVE_RIGHT;
		}else if(p.getAgentLocation().equalsIgnoreCase(Environment.LOCATION_B)) {
			return re = Environment.MOVE_LEFT;
		}
		return re;
	}
}