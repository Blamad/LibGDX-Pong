package com.eryk.pong.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.eryk.pong.controller.BallController;
import com.eryk.pong.controller.PlayerController;
import com.eryk.pong.model.World;
import com.eryk.pong.view.WorldRenderer;

public class GameScreen implements Screen, InputProcessor {

	private enum GameState {
		GAME_READY, GAME_RUNNING, GAME_PAUSED, GAME_OVER;
	}
	
	private GameState STATE;
	
	private World world;
	private BallController bController;
	private PlayerController pController;
	private WorldRenderer renderer;
	
	
	@Override
	public void render(float delta) {
		if(STATE.equals(GameState.GAME_RUNNING)) {
			Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
			Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
			
			pController.update(delta,world.getPlayer1());
			pController.update(delta,world.getPlayer2());
			bController.update(delta);
			renderer.render();
		} else {
			renderer.render();
			
		}
	}

	@Override
	public void resize(int width, int height) {
		renderer.resize(width, height);
		
	}

	@Override
	public void show() {
		this.world = new World();
		this.bController = new BallController(world);
		this.pController = new PlayerController(world);
		this.renderer = new WorldRenderer(world);
		Gdx.input.setInputProcessor(this);
		STATE = GameState.GAME_READY;
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Keys.UP)
			pController.up1Pressed();
		if(keycode == Keys.DOWN)
			pController.down1Pressed();
		if(keycode == Keys.A)
			pController.up2Pressed();
		if(keycode == Keys.Z)
			pController.down2Pressed();
		return true;
	}
	
	@Override
	public boolean keyUp(int keycode) {
		if(keycode == Keys.UP)
			pController.up1Released();
		if(keycode == Keys.DOWN)
			pController.down1Released();
		if(keycode == Keys.A)
			pController.up2Released();
		if(keycode == Keys.Z)
			pController.down2Released();
		return true;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int x, int y, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int x, int y, int pointer, int button) {
		if(STATE == GameState.GAME_PAUSED || STATE == GameState.GAME_READY)
			STATE = GameState.GAME_RUNNING;
		else
			STATE = GameState.GAME_PAUSED;
	
		return false;
	}

	@Override
	public boolean touchDragged(int x, int y, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchMoved(int x, int y) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		// TODO Auto-generated method stub
		return false;
	}
}
