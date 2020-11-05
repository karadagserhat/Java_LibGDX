package com.mygdx.spritedene;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class SpriteDene extends ApplicationAdapter implements InputProcessor{
	SpriteBatch batch;
	Texture img;
	Sprite gor1;
	
	boolean donuyor = false;
	boolean yukariHareket = false;
	boolean asagiHareket = false;
	boolean solaHareket = false;
	boolean sagaHareket = false;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		gor1 = new Sprite(img, 0, 0, 100, 100);
		
		gor1.setPosition(200, 200);
		
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(donuyor) {
			gor1.rotate(1);
		}
		
		if(yukariHareket && asagiHareket) {
			// eger yukarı ve asağı tuşuna aynı anda basılırsa bir şey yapma
		}
		else if(yukariHareket) {
			gor1.translate(0, 5);
		}
		else if(asagiHareket) {
			gor1.translate(0, -5);
		}
		
		if(sagaHareket && solaHareket) {
			
		}
		else if(sagaHareket) {
			gor1.translate(5, 0);
		}
		else if(solaHareket) {
			gor1.translate(-5, 0);
		}
		
		batch.begin();
		
		gor1.draw(batch);
		batch.end();
	}
	
	@Override
	public void dispose () {
		batch.dispose();
		img.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.UP) {
			yukariHareket = true;
			return true;
		}
		else if(keycode == Input.Keys.DOWN) {
			asagiHareket = true;
			return true;
		}
		else if(keycode == Input.Keys.LEFT) {
			solaHareket = true;
			return true;
		}
		else if(keycode == Input.Keys.RIGHT) {
			sagaHareket = true;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {  //tuşu bıraktığın zaman
		if(keycode == Input.Keys.UP) {
			yukariHareket = false;
			return true;}
		else if(keycode == Input.Keys.DOWN) {
			asagiHareket = false;
			return true;
		}
		else if(keycode == Input.Keys.LEFT) {
			solaHareket = false;
			return true;
		}
		else if(keycode == Input.Keys.RIGHT) {
			sagaHareket = false;
			return true;
		}
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		if(gor1.getBoundingRectangle().contains(screenX, Gdx.graphics.getHeight() - screenY)) { //x ve y sol alt tan başladığı için
			donuyor = true;
		}
		else
		{
			donuyor = false;
		}
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
