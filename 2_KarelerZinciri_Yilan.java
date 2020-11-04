package com.yilandene;

public class Yilan {

	final int YUKARI = 0;
	final int ASAGI = 1;
	final int SOL = 2;
	final int SAG = 3;
	
	YilanNode yilaninBasi;
	
	int mod = YUKARI;
	
	public Yilan(int x, int y) {
		yilaninBasi = new YilanNode(x, y);

	}
	
	public void yuru() {
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
		//yiyecek ve sınır
		//yılan çakışma kontrolü
		
		yilaninBasi.yuru(yurunecekX, yurunecekY);
	}
	
	public void modDegistir(int i) {
		if(i < 4 && i >= 0)
			mod = i;
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
