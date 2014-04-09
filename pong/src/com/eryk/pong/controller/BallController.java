package com.eryk.pong.controller;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.math.Rectangle;
import com.eryk.pong.model.Ball;
import com.eryk.pong.model.Wall;
import com.eryk.pong.model.World;

public class BallController {
	
	private Ball ball;
	private World world;
	
	private Sound sound = Gdx.audio.newSound(Gdx.files.internal("data/blip.wav"));
	
	public BallController(World world) {
		this.world = world;
		this.ball = world.getBall();
	}
	
	public void update(float delta) {
		checkCollision(delta);
		
		ball.move(delta);
	}
	
	private void checkCollision(float delta) {
		
		
		Wall walls[] = world.getWalls();
		Rectangle ballRect = new Rectangle();
		ballRect.set(ball.getRect());
		ball.getVelocity().mul(delta);
		
		//symulacja ruchu w kierunku
		ballRect.y += ball.getVelocity().y;
		ballRect.x += ball.getVelocity().x;
		
		ball.getVelocity().mul(1/delta);
		
		for(int i = 0; i < 4; i++) {
			if(ballRect.overlaps(walls[i])) {
				if(i < 2) {
					ball.getVelocity().y *= -1;
					sound.play(1f);
				} else {
					if(i == 2)
						world.getPlayer1().incScore();
					else
						world.getPlayer2().incScore();
					
					ball.getVelocity().x *= -1;
				}
				break;
			}
		}
		//odbicie pileczki od gracza
		if(ballRect.overlaps(world.getPlayer1().getRect())) {
			sound.play(1f);
			ball.getVelocity().set(ball.getBaseVel());
			ball.getVelocity().add(world.getPlayer1().getVelocity());
			ball.getVelocity().x *= -1;
		}
		if(ballRect.overlaps(world.getPlayer2().getRect())) {
			sound.play(1f);
			ball.getVelocity().set(ball.getBaseVel().x *= -1, ball.getBaseVel().y);
			ball.getVelocity().add(world.getPlayer2().getVelocity());
			ball.getVelocity().x *= -1;
		}
	}
}
