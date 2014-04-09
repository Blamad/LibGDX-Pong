package com.eryk.pong;

import com.badlogic.gdx.Game;
import com.eryk.pong.screens.GameScreen;

public class Pong extends Game {

	@Override
	public void create() {
		setScreen(new GameScreen());
		
	}

	
}