package com.yilandene;

public class Yilan {

	final int YUKARI = 0;
	final int ASAGI = 1;
	final int SOL = 2;
	final int SAG = 3;
	
	final int BOS_KARE = 0;
	final int DUVAR_KARE = 1;
	final int PUAN_KARE = 2;
	final int YILAN_KARE = 3;
	
	int[][] saha;
	
	YilanNode yilaninBasi;
	YilanNode yilaninSonu;
	
	int mod = YUKARI;
	
	boolean yilanUza = false;
	
	public Yilan(int x, int y, int[][] saha) {
		yilaninBasi = new YilanNode(x, y);
		yilaninSonu = yilaninBasi; //tek bir karede sonu ve başı aynı
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
		
		if(yilanUza) {
				if((saha[yurunecekX][yurunecekY] == BOS_KARE) //yürünecek kare boş kare olmalı veya başka puan içerecen kare olmalı ki
					|| (saha[yurunecekX][yurunecekY] == PUAN_KARE))
		{
			yilanUza = false;//yılan bir defa puan alınca sonsuza kadar uzamasın. bu yuzden false
			
			if(saha[yurunecekX][yurunecekY] == PUAN_KARE) //ama ardışık geliyorsa 
				yilanUza = true;
			
			YilanNode eskiBas = yilaninBasi; 
			yilaninBasi = new YilanNode(yurunecekX, yurunecekY);
			yilaninBasi.siradakiYilanNode = eskiBas;
			saha[yilaninBasi.x][yilaninBasi.y] = YILAN_KARE; 
			
			
			return true;
		}
				else {
					return false;
				}
		}
	
		//saha kontrolü
		//yürüme başarılı, boş kare
		if(saha[yurunecekX][yurunecekY] == BOS_KARE)  //ilerdeki kare bo kare ise
		{
			saha[yilaninSonu.x][yilaninSonu.y] = BOS_KARE;//kuyruğun terkettiği noktayı boş kareye çevirdik
			
			yilaninBasi.yuru(yurunecekX, yurunecekY); //yürüttük
			
			saha[yilaninBasi.x][yilaninBasi.y] = YILAN_KARE;//yılanın başının yeni gittiği noktayı yılan kareye çevirdik
			
			return true;
		}
		//yürüme başarılı, Puan kare
		else if(saha[yurunecekX][yurunecekY] == PUAN_KARE)
		{
			saha[yilaninSonu.x][yilaninSonu.y] = BOS_KARE;//kuyruğun terkettiği noktayı boş kareye çevirdik
			
			yilaninBasi.yuru(yurunecekX, yurunecekY); //yürüttük
			
			saha[yilaninBasi.x][yilaninBasi.y] = YILAN_KARE;//yılanın başının yeni gittiği noktayı yılan kareye çevirdik
			
			if(!yilanUza) {
				yilanUza = true;
			}
			
			return true;
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
