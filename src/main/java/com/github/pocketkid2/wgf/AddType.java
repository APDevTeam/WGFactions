package com.github.pocketkid2.wgf;

public enum AddType {
	// Leader and officers in the faction
	OFFICERS,
	// All faction members except recruits
	MEMBERS,
	// All (including recruits)
	ALL;

	@Override
	public String toString() {
		switch (this) {
		case ALL:
			return "all players";
		case MEMBERS:
			return "all players except recruits";
		case OFFICERS:
			return "leader and officer";
		default:
			return null;
		}
	}
}
