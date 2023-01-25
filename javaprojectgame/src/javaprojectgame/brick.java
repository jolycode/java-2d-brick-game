package javaprojectgame;

import java.awt.*;

import javaprojectgame.Main.STATE;
public class brick {
	public int map[][];
	public int Width;
	public int Height;
	public static STATE state= STATE.MENU;
	public brick(int row,int col) {
		map=new int [row][col];
	//if(state==STATE.LEVEL1||state==STATE.GAME) {
		for(int i = 0;i < map.length;i++) {
			for(int j = 0;j < map[0].length;j++) {
				map[i][j] = 1;}}//set the hp of brick to 1,one hit
		//1 means have not intersect with ball
		//}
		if(state==STATE.LEVEL2) {//set the hp of brick to 2,two hit
			for(int i = 0;i < map.length;i++) {
				if(i == 1) {
					for(int j = 0;j < map[0].length;j++) {
						map[i][j] = 2;}}
				else {
					for(int j = 0;j < map[0].length;j++) {
						map[i][j] = 1;}}}} 
		if(state==STATE.LEVEL3) {
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				map[i][j]=3;}}}//set the hp of brick to 3,three hit
		Width=530/col;
		Height = 100/row;
	}
	public void draw(Graphics2D g) { //draw the bricks,set the color of each
		for(int i=0;i<map.length;i++) {
			for(int j=0;j<map[0].length;j++) {
				if(map[i][j]==1) {
					g.setColor(Color.white);
					g.fillRect( 90 + j * Width+ j * 7,100 + i * Height + i * 15, Width, Height);	
					}
				if(map[i][j]==2) {
					g.setColor(Color.yellow);
					g.fillRect( 90 + j * Width+ j * 7,100 + i * Height + i * 15, Width, Height);	
					}
				if(map[i][j]==3) {
					g.setColor(Color.black);
					g.fillRect( 90 + j * Width+ j * 7,100 + i * Height + i * 15, Width, Height);	
					}
				}
			}
		}	
	public void brickmap(int tmp,int row,int col) {
		map[row][col]=tmp;}
}

