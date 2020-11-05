package com.yilandene;

import java.util.Random;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
public class YilanDene extends ApplicationAdapter implements InputProcessor{
	
	final int KARE_BIRIM = 22;
	int limitKare = 14;
	
	final int BOS_KARE = 0;
	final int DUVAR_KARE = 1;
	final int PUAN_KARE = 2;
	final int YILAN_KARE = 3;
	
	final int SAHAX = 20;
	final int SAHAY = 20;
	
	ShapeRenderer shapeRenderer;
	Random random = new Random();
	Yilan yilan;
	
	int anlikKare = 0;
	
	
	int[][] saha = new int[SAHAX][SAHAY];
	
	boolean oyunDevam = false;
	boolean yonDegistirilebilir = true;
	
	@Override
	public void create () {
		shapeRenderer = new ShapeRenderer();
		
		Gdx.input.setInputProcessor(this);
		
		sahaDuzeni1();
		
		yilan = new Yilan(3, 3, saha);
		
		oyunDevam = true;
		
	}
	
	private void sahaDuzeni1() {
		//Saha düzenleme
		for(int i = 0; i < 20; i++) {
			for(int j = 0; j < 20; j++) {
				if(i == 0 || i == 19) {
					saha[i][j] = DUVAR_KARE;
				}
				else if(j == 0 || j == 19)
				{
					saha[i][j] = DUVAR_KARE;
				}
				else
				{
					saha[i][j] = BOS_KARE;
				}
			}
		}
	}

	@Override
	public void render () {
		if(anlikKare >= limitKare)
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
				if(saha[i][j] == BOS_KARE) {
					shapeRenderer.setColor(Color.LIGHT_GRAY);
					shapeRenderer.rect(i * KARE_BIRIM, j * KARE_BIRIM, KARE_BIRIM - 1, KARE_BIRIM - 1);
				}
				else if(saha[i][j] == DUVAR_KARE) {
					shapeRenderer.setColor(Color.DARK_GRAY);
					shapeRenderer.rect(i * KARE_BIRIM, j * KARE_BIRIM, KARE_BIRIM - 1, KARE_BIRIM - 1);
				}
				else if(saha[i][j] == YILAN_KARE) {
					if(oyunDevam)
					{
						shapeRenderer.setColor(Color.BLACK);
					}
					else
					{
						shapeRenderer.setColor(Color.RED); // eğer oyun devam etmezse yılanın rengini kırmızı yapar
					}
					
					shapeRenderer.rect(i * KARE_BIRIM, j * KARE_BIRIM, KARE_BIRIM - 1, KARE_BIRIM - 1);
				}
				else if(saha[i][j] == PUAN_KARE) {
					shapeRenderer.setColor(Color.GREEN);
					shapeRenderer.rect(i * KARE_BIRIM, j * KARE_BIRIM, KARE_BIRIM - 1, KARE_BIRIM - 1);
				}
			}
		}
		/*
		if(oyunDevam)
		{
			shapeRenderer.setColor(Color.BLACK);
		}
		else
		{
			shapeRenderer.setColor(Color.RED); // eğer oyun devam etmezse yılanın rengini kırmızı yapar
		}
		YilanNode yilanElemeni = yilan.yilaninBasi;
		while( yilanElemeni != null) {
			shapeRenderer.rect(yilanElemeni.x * KARE_BIRIM, yilanElemeni.y * KARE_BIRIM, KARE_BIRIM - 1, KARE_BIRIM - 1);
			yilanElemeni = yilanElemeni.siradakiYilanNode;
		}
		*/
		
		shapeRenderer.end();
		

	}
	
	int mantikFrame = 0;
	final int MANTIK_FRAME_LIMIT = 5;
	
	private void mantikGuncelle() {
		if(oyunDevam)
			mantikFrame++;
		
		if(mantikFrame >= MANTIK_FRAME_LIMIT)
		{
			puanEkle();
			mantikFrame = 0;
		}
		if(oyunDevam)
		{
			oyunDevam = yilan.yuru();
			yonDegistirilebilir = true;
		}
		else
		{
			
		}
	}

	private boolean puanEkle() {
		int x = random.nextInt(SAHAX);
		int y = random.nextInt(SAHAY);
		
		if(saha[x][y] == BOS_KARE)
		{
			saha[x][y] = PUAN_KARE; //eğer boş kareyse  bu kareye yiyecek ekleyebiliriz
		}
		
		return false;
	}
	
	@Override
	public void dispose () {
		shapeRenderer.dispose();
	}

	@Override
	public boolean keyDown(int keycode) {
		if(keycode == Input.Keys.UP)
		{
			if(oyunDevam && yonDegistirilebilir) {
				yilan.modDegistir(yilan.YUKARI);
				yonDegistirilebilir = false;
			}
			return true;
		}
		else if(keycode == Input.Keys.DOWN)
		{
			if(oyunDevam && yonDegistirilebilir) {
				yilan.modDegistir(yilan.ASAGI);
				yonDegistirilebilir = false;
			}
			return true;
		}
		else if(keycode == Input.Keys.LEFT)
		{
			if(oyunDevam && yonDegistirilebilir) {
				yilan.modDegistir(yilan.SOL);
				yonDegistirilebilir = false;
			}
			return true;
		}
		else if(keycode == Input.Keys.RIGHT)
		{
			if(oyunDevam && yonDegistirilebilir) {
				yilan.modDegistir(yilan.SAG);
				yonDegistirilebilir = false;
			}
			return true;
		}
		else if(keycode == Input.Keys.E)
		{
			if(oyunDevam)
				yilan.kareEkle();
			return true;
		}
		else if(keycode == Input.Keys.R)
		{
			// r ye basınca oyun tekrar başlar
			sahaDuzeni1();
			yilan = new Yilan(3,3, saha);
			oyunDevam = true;
			yonDegistirilebilir = true;
			return true;
		}
		else if(keycode == Input.Keys.NUM_1)
		{
			limitKare++; //oyun yavaşlıcak
			return true;
		}
		else if(keycode == Input.Keys.NUM_2)
		{
			if(limitKare > 1)
				limitKare--; //oyun hızlanıcak
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
