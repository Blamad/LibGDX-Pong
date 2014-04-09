package com.eryk.pong.controller;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.math.Rectangle;
import com.eryk.pong.model.Player;
import com.eryk.pong.model.Wall;
import com.eryk.pong.model.World;

public class PlayerController {
	
	enum Keys {
		UP1, DOWN1, UP2, DOWN2
	}
	
	static Map<Keys, Boolean> keys = new HashMap<PlayerController.Keys, Boolean>();
	static {
		keys.put(Keys.UP1, false);
		keys.put(Keys.DOWN1, false);
		keys.put(Keys.UP2, false);
		keys.put(Keys.DOWN2, false);
	}
	
	private static final float ACCELERATION 	= 400f;
	private static final float MAX_VEL			= 300f;
	
	private Player player1, player2;
	private World world;
	
	public PlayerController(World world) {
		this.world = world;
		this.player1 = world.getPlayer1();
		this.player2 = world.getPlayer2();
	}
	
	public void update(float delta, Player player) {
		processInput();
		
		//uaktualnij ruch
		player.getVelocity().add(0,player.getAcceleration().y);
		//sprawdz kolizje
		CollisionWithWall(delta, player);
		if(player.getAcceleration().y == 0) player.getVelocity().y = 0;
		
		if(player.getVelocity().y > MAX_VEL) {
			player.getVelocity().y = MAX_VEL;
		}
		if(player.getVelocity().y < -MAX_VEL) {
			player.getVelocity().y = -MAX_VEL;
		}
		//wprowadz zmiany
		player.move(delta);
	}
	
	public void CollisionWithWall(float delta, Player player) {
		
		Wall walls[] = world.getWalls();
		Rectangle playerRect = new Rectangle();
		playerRect.set(player.getRect());
		player.getVelocity().mul(delta);
		
		//symulacja ruchu w kierunku
		playerRect.y += player.getVelocity().y;
		
		player.getVelocity().mul(1/delta);
		
		for(int i = 0; i < 2; i++) {
			if(playerRect.overlaps(walls[i])) {
				player.getVelocity().y = 0;
				break;
			}
		}
	}
	
	public void up1Pressed() {
		keys.get(keys.put(Keys.UP1,true));
	}
	
	public void up1Released() {
		keys.get(keys.put(Keys.UP1, false));
	}
	
	public void down1Pressed() {
		keys.get(keys.put(Keys.DOWN1,true));
	}
	
	public void down1Released() {
		keys.get(keys.put(Keys.DOWN1, false));
	}
	
	public void up2Pressed() {
		keys.get(keys.put(Keys.UP2,true));
	}
	
	public void up2Released() {
		keys.get(keys.put(Keys.UP2, false));
	}
	
	public void down2Pressed() {
		keys.get(keys.put(Keys.DOWN2,true));
	}
	
	public void down2Released() {
		keys.get(keys.put(Keys.DOWN2, false));
	}
	
	
	public void processInput() {
		//Player1
		if(keys.get(Keys.UP1)) {
			player1.getAcceleration().y = ACCELERATION;
			return;
		} else 
			if(keys.get(Keys.DOWN1)) {
				player1.getAcceleration().y = -ACCELERATION;
				return;
			}
		player1.getAcceleration().y = 0;
		
		//Player2
		if(keys.get(Keys.UP2)) {
			player2.getAcceleration().y = ACCELERATION;
			return;
		} else 
			if(keys.get(Keys.DOWN2)) {
				player2.getAcceleration().y = -ACCELERATION;
				return;
			}
		player2.getAcceleration().y = 0;
	}
	
}
