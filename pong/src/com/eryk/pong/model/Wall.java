package com.eryk.pong.model;

import com.badlogic.gdx.math.Rectangle;

public class Wall extends Rectangle {
	
	public Wall(float x, float y, float w, float h) {
		this.setWidth(w);
		this.setHeight(h);
		this.setX(x);
		this.setY(y);
	}
	
}
