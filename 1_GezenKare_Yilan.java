package com.yilandene;

public class Yilan {

	final int YUKARI = 0;
	final int ASAGI = 1;
	final int SOL = 2;
	final int SAG = 3;
	int x, y;
	int mod = YUKARI;
	
	public Yilan(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public void yuru() {
		if(mod == YUKARI)
		{
			y++;
		}
		else if(mod == ASAGI)
		{
			y--;
		}
		else if(mod == SOL)
		{
			x--;
		}
		else if(mod == SAG)
		{
			x++;
		}
	}
	
	public void modDegistir(int i) {
		if(i < 4 && i >= 0)
			mod = i;
	}
}
