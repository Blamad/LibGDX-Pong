package com.eryk.pong.model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Ball {

	public final static float SIZE = 6f;
	
	private Vector2 position = new Vector2();
	private Vector2 baseVelocity = new Vector2();
	private Vector2 velocity = new Vector2();
	private Rectangle bounds = new Rectangle();
	
	public Ball(Vector2 pos, Vector2 vel, Vector2 base) {
		this.position = pos;
		this.bounds.setX(pos.x);
		this.bounds.setY(pos.y);
		this.bounds.width = SIZE;
		this.bounds.height = SIZE;
		this.velocity = vel;
		this.baseVelocity = base;
	}
	
	public Vector2 getPosition() {
		return position;
	}
	
	public void setPosition(Vector2 pos) {
		position = pos;
		bounds.setX(pos.x);
		bounds.setY(pos.y);
	}
	
	public Vector2 getVelocity() {
		return velocity;
	}
	
	public Vector2 getBaseVel() {
		return baseVelocity;
	}
	
	public Rectangle getRect() {
		return bounds;
	}
	
	public void move(float delta) {
		velocity.mul(delta);
		
		position.add(velocity);
		bounds.setX(position.x);
		bounds.setY(position.y);
		
		velocity.mul(1/delta);
	}
	
}
