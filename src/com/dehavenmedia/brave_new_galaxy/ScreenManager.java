package com.dehavenmedia.brave_new_galaxy;

import java.awt.*;
import javax.swing.JFrame;

public class ScreenManager {
	
	private GraphicsDevice device;
	
	public ScreenManager()
	{
		GraphicsEnvironment environment = GraphicsEnvironment.getLocalGraphicsEnvironment();
		device = environment.getDefaultScreenDevice();
	} // End of ScreenManager() constructor.
	
	public void setFullScreen(DisplayMode displayMode, JFrame window)
	{
		window.setUndecorated(true);
		window.setResizable(false);
		
		device.setFullScreenWindow(window);
		if (displayMode != null && device.isDisplayChangeSupported()) {
			try {
				device.setDisplayMode(displayMode);
			} catch (IllegalArgumentException ex) {	}
		}		
	} // End of setFullScreen().
	
	public Window getFullScreenWindow()
	{
		return device.getFullScreenWindow();
	} // End of getFullScreenWindow().
	
	public void restoreScreen()
	{
		Window window = device.getFullScreenWindow();
		if (window != null) {
			window.dispose();
		}
		device.setFullScreenWindow(null);
	} // End of restoreScreen().

} // End of ScreenManager class.
