package model;

import java.util.ArrayList;
import java.util.List;

public class Computer extends Player {
	private List<Shot> priorityShots;
	
	public Computer() {
		super();
		this.priorityShots = new ArrayList<Shot>();
	}

	public List<Shot> getPriorityShots() {
		return priorityShots;
	}
	
	public Shot getPriorityShot() {
		if (priorityShots.isEmpty()) {
			return null;
		} else {
			return priorityShots.get(0);
		}
	}
	
}
