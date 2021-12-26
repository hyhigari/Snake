package snake;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import game.*;

/*
 * ��
 */

public class Snake{
	private static final int BLOCK_WIDTH = MainFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = MainFrame.BLOCK_HEIGHT; 
	
	private static final int ROW = MainFrame.ROW;
	private static final int COL = MainFrame.COL;
	
	//��Ϸ������
	private MainFrame mf;
	
	//��ǰ���ķ���Ĭ������
	public Direction direction = Direction.RIGHT;
	//�洢�ߵ�������
	public LinkedList<Node> snakeBody = new LinkedList<Node>();
	
	//�ߵ���ɫ��Ĭ��Ϊ��ɫ
	private Color color = Color.RED;
	//��������ߵĳ�ʼλ��
	private Random r = new Random();
	private int startx, starty;
	private Node node;
	
	//�ߵ��ٶ�
	private int speed;
	//Ĭ���ٶ�
	private int defaultSpeed = 500;
	
	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}
	
	public void resetSpeed() {
		this.speed = defaultSpeed;
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Snake(MainFrame mf) {
		this.mf = mf;
		speed = defaultSpeed;
		
		//��ʼ���ߵ�λ��
		startx = r.nextInt(COL);
		starty = r.nextInt(ROW);
		node = new Node(startx, starty, Direction.RIGHT, color);
		
		snakeBody.addFirst(node);
	}
	
	public void draw(Graphics g) {
		if (snakeBody.getFirst() == null) {
			return;
		}
		
		move();
		
		for (int i = 0; i < snakeBody.size(); i++) {
			snakeBody.get(i).draw(g); 
		}	
	}
	
	//�ߵ��ƶ�
	public void move() {
		//���ͷ���
		addNodeInHead();
		//����Ƿ�ײǽ��ײ���Լ�
		isDead();
		//ɾ��β���
		snakeBody.removeLast();
	}
	
	//�ж�����
	private void isDead() {
		//ײǽ
		if (snakeBody.getFirst().x < 0 || snakeBody.getFirst().x >= COL || snakeBody.getFirst().y < 0 || snakeBody.getFirst().y >= ROW) {
			mf.end();
		}
		
		//ײ���Լ�
		for (int i = 1; i < snakeBody.size(); i++) {
			node = snakeBody.get(i);
			if (snakeBody.getFirst().x == node.x && snakeBody.getFirst().y == node.y) {
				mf.end();
			}
		}
	}
	
	//������ײ���
	public Rectangle getRect() {
		return new Rectangle(snakeBody.getFirst().x * BLOCK_WIDTH, snakeBody.getFirst().y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
	}
	
	//�ж��Ƿ�Ե�ʳ��
	public boolean eatFood(Food food) {
		if (this.getRect().intersects(food.getRect())) {
			addNodeInHead();
			food.reAppear();
			return true;
		} else {
			return false;
		}
	}
		
	//���ͷ���
	private void addNodeInHead() {
		Node node = null;
		switch(snakeBody.getFirst().direction) {
		case RIGHT:
			node = new Node(snakeBody.getFirst().x + 1, snakeBody.getFirst().y, snakeBody.getFirst().direction, color);
			break;
		case LEFT:
			node = new Node(snakeBody.getFirst().x - 1, snakeBody.getFirst().y, snakeBody.getFirst().direction, color);
			break;
		case UP:
			node = new Node(snakeBody.getFirst().x, snakeBody.getFirst().y - 1, snakeBody.getFirst().direction, color);
			break;
		case DOWN:
			node = new Node(snakeBody.getFirst().x, snakeBody.getFirst().y + 1, snakeBody.getFirst().direction, color);
			break;
		}
			
		snakeBody.addFirst(node);
	}
}
