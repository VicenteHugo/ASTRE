package model.action;

import java.util.List;

public abstract class Action 
{
	
	protected String requetes;
	protected List<Object> info;
	
	public String getRequeteSQL() {
		return this.requetes;
	}

	public List<Object> getInfo() {
		return this.info;
	}

}
