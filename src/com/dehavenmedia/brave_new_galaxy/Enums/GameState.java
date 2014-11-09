package com.dehavenmedia.brave_new_galaxy.Enums;

public enum GameState implements State {
	ST_COMPANY {
		@Override
		public void step() {
			// State's logic goes here
		}
	}, 
	ST_TITLE {
		@Override
		public void step() {
			// State's logic goes here
		}
	}, 
	ST_INTRO {
		@Override
		public void step() {
			// State's logic goes here
		}
	}, 
	ST_MAINMENU {
		@Override
		public void step() {
			// State's logic goes here
		}
	}, 
	ST_SETUP {
		@Override
		public void step() {
			// State's logic goes here
		}
	}, 
	ST_LOAD {
		@Override
		public void step() {
			// State's logic goes here
		}
	}, 
	ST_GAMEWORLD {
		@Override
		public void step() {
			// State's logic goes here
		}
	}, 
	ST_EXIT {
		@Override
		public void step() {
			// State's logic goes here
		}
	}, 
	ST_NULL {
		@Override
		public void step() {
			// State's logic goes here
		}
	};
	
	public void step() {
		// Logic here
	}
}
