package com.eryk.pong.model;

import com.badlogic.gdx.math.Vector2;

/** Zadanie klasy
 * 
 * 	Tworzyc swiat i obiekty w nim wystepujace
 * Tutaj bedzie to tylko pileczka i dwoje graczy
 * 
 */

public class World {
	
	private Level level;
	private Ball ball;
	private Player player1;
	private Player player2;
	private Vector2 borderMargin = new Vector2(4,4);
	
	public World() {
		this.level = new Level(borderMargin);
		player1 = new Player(level.getPlayer1BPos());
		player2 = new Player(level.getPlayer2BPos());
		this.ball = new Ball(level.getBallBPos(),new Vector2(100f,100f), new Vector2(100f,0f));
	}
	
	public Vector2 getBorderMargin() {
		return borderMargin;
	}
	public Ball getBall() {
		return ball;
	}
	
	public Wall[] getWalls() {
		return level.getWalls();
	}

	public Player getPlayer1() {
		return player1;
	}

	public Player getPlayer2() {
		return player2;
	}
	
}
