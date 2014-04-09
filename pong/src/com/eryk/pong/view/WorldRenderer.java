package com.eryk.pong.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Vector2;
import com.eryk.pong.model.Ball;
import com.eryk.pong.model.Player;
import com.eryk.pong.model.Wall;
import com.eryk.pong.model.World;

public class WorldRenderer {
	
	private static final float CAM_WIDTH = 400f;
	private static final float CAM_HEIGHT = 240f;
	
	Vector2 ppu = new Vector2();
	
	BitmapFont font = new BitmapFont();
	
	private Vector2 border_margin = new Vector2();
	private World world;
	
	//do rysowania
	private SpriteBatch spriteBatch;
	private OrthographicCamera cam;
	private ShapeRenderer renderer = new ShapeRenderer();
	
	public WorldRenderer(World world) {
		this.world = world;
		this.cam = new OrthographicCamera(CAM_WIDTH,CAM_HEIGHT);
		this.cam.position.set(CAM_WIDTH/2,CAM_HEIGHT/2,0);
		this.cam.update();
		this.spriteBatch = new SpriteBatch();
		border_margin = world.getBorderMargin();
	}
	
	public void resize(int width, int height) {
		ppu.x = width/CAM_WIDTH;
		ppu.y = height/CAM_HEIGHT;
	}
	
	public void render() {
		spriteBatch.begin();
		font.draw(spriteBatch, Integer.toString(world.getPlayer2().getScore()), 40, CAM_HEIGHT*5/4);
		font.draw(spriteBatch, Integer.toString(world.getPlayer1().getScore()), CAM_WIDTH + 40, CAM_HEIGHT*5/4);
		spriteBatch.end();
		drawBorder();
		drawPlayers();
		drawBall();
	}
	
	private void drawPlayers() {
		renderer.setProjectionMatrix(cam.combined);
		renderer.begin(ShapeType.FilledRectangle);
		renderer.setColor(new Color(1,0,1,1));
		
		//render gracza 1
		Player player = world.getPlayer1();
		renderer.filledRect(player.getPosition().x,player.getPosition().y, player.getRect().width, player.getRect().height);
		//render gracza 2
		player = world.getPlayer2();
		renderer.filledRect(player.getPosition().x,player.getPosition().y, player.getRect().width, player.getRect().height);
		
		renderer.end();
	}
	
	private void drawBall() {
		Ball ball = world.getBall();
		renderer.setProjectionMatrix(cam.combined);
		renderer.begin(ShapeType.FilledRectangle);
		renderer.setColor(new Color(1,1,1,1));
		renderer.filledRect(ball.getPosition().x, ball.getPosition().y, ball.SIZE, ball.SIZE);
		renderer.end();
	}
	
	private void drawBorder() {
		renderer.setProjectionMatrix(cam.combined);
		renderer.begin(ShapeType.FilledRectangle);
		renderer.setColor(new Color(0,1,0,1));
		//ramka
		Wall walls[] = world.getWalls();
		for(int i = 0; i < 4; i++) {
			renderer.filledRect(walls[i].getX(),walls[i].getY(),walls[i].getWidth(),walls[i].getHeight());
		}
		
		//linia*/
		renderer.setColor(new Color(1,0,0,1));
		renderer.filledRect(CAM_WIDTH/2 - border_margin.x/2, 2*border_margin.y, border_margin.y , CAM_HEIGHT - 4*border_margin.y);
		renderer.end();
	}
}