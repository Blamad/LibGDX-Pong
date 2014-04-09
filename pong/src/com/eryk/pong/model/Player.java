package com.eryk.pong.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	
	private final static float width = 8f, height = 20f;
	
	private Rectangle borders = new Rectangle();
	private Vector2 position = new Vector2();
	private Vector2 velocity = new Vector2();
	private Vector2 acceleration = new Vector2(0,0);
	private int score = 0;
	
	public Player(Vector2 pos) {
		this.borders.setWidth(width);
		this.borders.setHeight(height);
		this.position = pos;
		this.borders.setX(pos.x);
		this.borders.setY(pos.y);
	}
	
	public void incScore() {
		score++;
	}
	
	public int getScore() {
		return score;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void move(float delta) {
		velocity.mul(delta);
		
		position.add(velocity);
		this.borders.setX(position.x);
		this.borders.setY(position.y);
		
		velocity.mul(1/delta);
	}
	
	public Rectangle getRect() {
		return borders;
	}

	public Vector2 getAcceleration() {
		return acceleration;
	}

	public Vector2 getVelocity() {
		return velocity;
	}
}
