package com.yilandene;

public class Yilan {

	final int YUKARI = 0;
	final int ASAGI = 1;
	final int SOL = 2;
	final int SAG = 3;
	
	final int BOS_KARE = 0;
	final int DUVAR_KARE = 1;
	final int PUAN_KARE = 2;
	
	int[][] saha;
	
	YilanNode yilaninBasi;
	
	int mod = YUKARI;
	
	public Yilan(int x, int y, int[][] saha) {
		yilaninBasi = new YilanNode(x, y);
		this.saha = saha;
	}


	public boolean yuru() {
		int yurunecekX = yilaninBasi.x;
		int yurunecekY = yilaninBasi.y;
		
		if(mod == YUKARI)
		{
			//yilaninBasi.yuru(yilaninBasi.x, yilaninBasi.y + 1);
			yurunecekY++;
		}
		else if(mod == ASAGI)
		{
			//yilaninBasi.yuru(yilaninBasi.x, yilaninBasi.y - 1);
			yurunecekY--;
		}
		else if(mod == SOL)
		{
			//yilaninBasi.yuru(yilaninBasi.x - 1, yilaninBasi.y);
			yurunecekX--;
		}
		else if(mod == SAG)
		{
			//yilaninBasi.yuru(yilaninBasi.x + 1, yilaninBasi.y);
			yurunecekX++;
		}
		
		//yürümek mümkün mü
		//saha kontrolü
		if(saha[yurunecekX][yurunecekY] == BOS_KARE)
		{
			yilaninBasi.yuru(yurunecekX, yurunecekY);
			return true;
		}
		else if(saha[yurunecekX][yurunecekY] == DUVAR_KARE)
		{
			return false;
		}
		//yiyecek ve sınır
		//yılan çakışma kontrolü
		return false;
		
	}
	
	public void modDegistir(int i) {
		if(i < 4 && i >= 0) {
			if(mod == YUKARI && i == ASAGI)
			{
				
			}
			else if(mod == ASAGI && i == YUKARI)
			{
				
			}
			else if(mod == SOL && i == SAG)
			{
		
			}
			else if(mod == SAG && i == SOL)
			{
		
			}
			else
			{
				mod = i;
			}
				
		}
			
	}
	
	public void kareEkle() {
		int x = yilaninBasi.x;
		int y = yilaninBasi.y;
		
		yuru();
		YilanNode eklenecekKare = new YilanNode(yilaninBasi.x, yilaninBasi.y);
		
		yilaninBasi.x = x;
		yilaninBasi.y = y;
		
		eklenecekKare.siradakiEkle(yilaninBasi);
		yilaninBasi = eklenecekKare;
	}
}
