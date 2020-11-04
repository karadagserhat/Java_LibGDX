package com.yilandene;

public class YilanNode {

	YilanNode siradakiYilanNode;
	
	public int x;
	public int y;
	public YilanNode(int x, int y) {
		this.x = x;
		this.y = y;
		this.siradakiYilanNode = null;
	}
	
	public void siradakiEkle(YilanNode siradaYilanNode) {
		this.siradakiYilanNode = siradaYilanNode;
	}
	
	public void yuru(int yeniX, int yeniY) {
		if(siradakiYilanNode != null)
		{
			siradakiYilanNode.yuru(x, y);
		}
		x = yeniX;
		y = yeniY;
	}
}
