package snake;

import java.awt.*;
import java.util.*;

import game.*;

/*
 * ʳ��
 */

public class Food {
	//����
	private int x;
	private int y;
	
	//��С
	private static final int BLOCK_WIDTH = MainFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = MainFrame.BLOCK_HEIGHT;
	
	private static final Random r = new Random();
	
	private Color color = Color.ORANGE;
	
	public Food(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Food() {
		this((r.nextInt(MainFrame.COL)), (r.nextInt(MainFrame.ROW)));
	}
	
	//��������ʳ��
	public void reAppear() {
		this.x = (r.nextInt(MainFrame.COL));
		this.y = (r.nextInt(MainFrame.ROW));
	}
	
	//��ײ���
	public Rectangle getRect() {
		return new Rectangle(x * BLOCK_WIDTH, y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
	}
	
	//��ʳ��
	public void draw(Graphics g) {
		Color c = g.getColor();
		g.setColor(color);
		g.fillOval(x * BLOCK_WIDTH, y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
		g.setColor(c);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
}
