package com.eryk.pong.model;

import com.badlogic.gdx.math.Vector2;

public class Level {
	
	private int width = 400, height = 240;
	private Vector2 border_margin;
	
	private Wall walls[] = new Wall[4];
	private Vector2 ballBPos = new Vector2(width/2,height/2);
	private Vector2 player1BPos = new Vector2();
	private Vector2 player2BPos = new Vector2();
	
	public Level(Vector2 borderMargin) {
		this.border_margin = borderMargin;
		createLevel();
	}
	
	public Vector2 getBallBPos() {
		return ballBPos;
	}
	
	public Wall[] getWalls() {
		return walls;
	}
	
	private void createLevel() {
			walls[0] = new Wall(border_margin.x, border_margin.y, width - 2*border_margin.x, border_margin.y);
			walls[1] = new Wall(border_margin.x, height - 2*border_margin.y, width - 2*border_margin.x, border_margin.y);
			walls[2] = new Wall(border_margin.x, 2*border_margin.y, border_margin.y , height - 4*border_margin.y);
			walls[3] = new Wall(width - 2*border_margin.x, 2*border_margin.y, border_margin.y , height - 4*border_margin.y);
			player1BPos.set(width - 5*border_margin.x ,height/2);
			player2BPos.set(4*border_margin.x ,height/2);
	}

	public Vector2 getPlayer1BPos() {
		return player1BPos;
	}

	public Vector2 getPlayer2BPos() {
		return player2BPos;
	}
}
