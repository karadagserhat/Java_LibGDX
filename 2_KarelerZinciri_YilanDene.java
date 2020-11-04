package com.yilandene;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
public class YilanDene extends ApplicationAdapter implements InputProcessor{
	
	final int KARE_BIRIM = 22;
	final int LIMIT_KARE = 24;
	
	ShapeRenderer shapeRenderer;
	Yilan yilan;
	
	int anlikKare = 0;
	
	
	int[][] saha = new int[20][20];
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		yilan = new Yilan(3, 3);
		Gdx.input.setInputProcessor(this);
		
	}

	@Override
	public void render () {
		if(anlikKare == LIMIT_KARE)
		{
			mantikGuncelle();
			anlikKare = 0;
		}
		else
		{
			anlikKare++;
		}
		
		Gdx.gl.glClearColor(1, 1, 1, 1); //r g b opaklık
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		shapeRenderer.setColor(Color.BLACK);
		shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
		
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if(saha[i][j] == 0) {
					shapeRenderer.setColor(Color.BROWN);
					shapeRenderer.rect(i * KARE_BIRIM, j * KARE_BIRIM, KARE_BIRIM - 1, KARE_BIRIM - 1);
				}
			}
		}
		
		shapeRenderer.setColor(Color.BLACK);
		YilanNode yilanElemeni = yilan.yilaninBasi;
		while( yilanElemeni != null) {
			shapeRenderer.rect(yilanElemeni.x * KARE_BIRIM, yilanElemeni.y * KARE_BIRIM, KARE_BIRIM - 1, KARE_BIRIM - 1);
			yilanElemeni = yilanElemeni.siradakiYilanNode;
		}
		
		
		shapeRenderer.end();
		

	}
	
	private void mantikGuncelle() {
		yilan.yuru();
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.UP)
		{
			yilan.modDegistir(yilan.YUKARI);
			return true;
		}
		else if(keycode == Input.Keys.DOWN)
		{
			yilan.modDegistir(yilan.ASAGI);
			return true;
		}
		else if(keycode == Input.Keys.LEFT)
		{
			yilan.modDegistir(yilan.SOL);
			return true;
		}
		else if(keycode == Input.Keys.RIGHT)
		{
			yilan.modDegistir(yilan.SAG);
			return true;
		}
		else if(keycode == Input.Keys.E)
		{
			yilan.kareEkle();
			return true;
		}
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		// TODO Auto-generated method stub
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
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean scrolled(float amountX, float amountY) {
		// TODO Auto-generated method stub
		return false;
	}
}
