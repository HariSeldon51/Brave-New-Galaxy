package com.degauvendesign.interstella;

public interface Composite {

	Composite parentComposite = null;
	
	public void addChild(Composite c);
	public void removeChild();
	
	public int getChildCount();
	public Composite getChild(int i);
}
