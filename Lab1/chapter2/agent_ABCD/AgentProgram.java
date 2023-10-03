package chapter2.agent_ABCD;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class AgentProgram {
	// ABCD theo mang 2 chieu {(A,B),(C,D)}
	public Action execute(Percept p) {// location, status
		Action re = NoOpAction.NO_OP;
		if(p.getLocationState() == Environment.LocationState.DIRTY) {
			Environment.point = 500;
			System.out.println("Point = "+Environment.point);
			return Environment.SUCK_DIRT;
		}else if(p.getAgentLocation() == Environment.LOCATION_A) {
			return check(Environment.MOVE_LEFT, Environment.MOVE_UP) ;
		}else if(p.getAgentLocation() == Environment.LOCATION_B) {
			return check(Environment.MOVE_RIGHT, Environment.MOVE_UP) ;
		}else if(p.getAgentLocation() == Environment.LOCATION_C) {
			return check(Environment.MOVE_LEFT, Environment.MOVE_DOWN) ;
		}else if(p.getAgentLocation() == Environment.LOCATION_D) {
			return check(Environment.MOVE_RIGHT, Environment.MOVE_DOWN) ;
		}
		return re;
	}
	// location can't move
	public Action check(Action a1, Action a2 ) {
		List<Action> list = new ArrayList<>();
		list.add(a1);
		list.add(a2);
		Action[] action = {Environment.MOVE_LEFT, Environment.MOVE_RIGHT, Environment.MOVE_DOWN, Environment.MOVE_UP};
		Random rd = new Random();
		int i = rd.nextInt(4);
		while(list.contains(action[i])) {
			Environment.point = -100;
			System.out.println("Point = "+Environment.point);
			i = rd.nextInt(4);
		}
		Environment.point = -10;
		System.out.println("Point = "+Environment.point);
		return action[i];
	}
}