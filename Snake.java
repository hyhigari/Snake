package snake;

import java.awt.*;
import java.util.*;
import javax.swing.*;

import game.*;

/*
 * 蛇
 */

public class Snake{
	private static final int BLOCK_WIDTH = MainFrame.BLOCK_WIDTH;
	private static final int BLOCK_HEIGHT = MainFrame.BLOCK_HEIGHT; 
	
	private static final int ROW = MainFrame.ROW;
	private static final int COL = MainFrame.COL;
	
	//游戏主界面
	private MainFrame mf;
	
	//蛇前进的方向，默认向右
	public Direction direction = Direction.RIGHT;
	//存储蛇的身体结点
	public LinkedList<Node> snakeBody = new LinkedList<Node>();
	
	//蛇的颜色，默认为红色
	private Color color = Color.RED;
	//随机生成蛇的初始位置
	private Random r = new Random();
	private int startx, starty;
	private Node node;
	
	//蛇的速度
	private int speed;
	//默认速度
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
		
		//初始化蛇的位置
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
	
	//蛇的移动
	public void move() {
		//添加头结点
		addNodeInHead();
		//检查是否撞墙或撞到自己
		isDead();
		//删除尾结点
		snakeBody.removeLast();
	}
	
	//判断死亡
	private void isDead() {
		//撞墙
		if (snakeBody.getFirst().x < 0 || snakeBody.getFirst().x >= COL || snakeBody.getFirst().y < 0 || snakeBody.getFirst().y >= ROW) {
			mf.end();
		}
		
		//撞到自己
		for (int i = 1; i < snakeBody.size(); i++) {
			node = snakeBody.get(i);
			if (snakeBody.getFirst().x == node.x && snakeBody.getFirst().y == node.y) {
				mf.end();
			}
		}
	}
	
	//用于碰撞检测
	public Rectangle getRect() {
		return new Rectangle(snakeBody.getFirst().x * BLOCK_WIDTH, snakeBody.getFirst().y * BLOCK_HEIGHT, BLOCK_WIDTH, BLOCK_HEIGHT);
	}
	
	//判断是否吃到食物
	public boolean eatFood(Food food) {
		if (this.getRect().intersects(food.getRect())) {
			addNodeInHead();
			food.reAppear();
			return true;
		} else {
			return false;
		}
	}
		
	//添加头结点
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
