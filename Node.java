package snake;

import java.awt.Color;
import java.awt.Graphics;

import game.MainFrame;

/*
 * 存储结点的坐标
 */

public class Node {
	private static final int BLOCK_WIDTH = MainFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = MainFrame.BLOCK_HEIGHT; 
	
	public int x;
	public int y;
	public Direction direction;
	private Color color = Color.RED;
	
	public Node(int x, int y, Direction direction, Color color) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.color = color;
	}
	
	public void draw(Graphics g){
		Color c = g.getColor();
		g.setColor(color);
		g.fillRect(x * BLOCK_WIDTH, y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
		g.setColor(c);
	}
}
