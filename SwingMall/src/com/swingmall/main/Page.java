package com.swingmall.main;

import java.awt.Dimension;

import javax.swing.JPanel;

public class Page extends JPanel{

	private ShopMain shopMain;
	
	public Page(ShopMain shopMain) {
		this.shopMain=shopMain;
		this.setPreferredSize(new Dimension(ShopMain.WIDTH,ShopMain.HEIGHT-100));
	}
	public ShopMain getShopMain() {
		return shopMain;
	}
}
