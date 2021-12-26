package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;

import snake.*;

/*
 * 游戏主界面
 */

public class MainFrame extends JFrame{
	//方格的宽和长
	public static final int BLOCK_WIDTH = 15;
	public static final int BLOCK_HEIGHT = 15;
			
	//游戏界面的方格的行数和列数
	public static final int ROW = 36;
	public static final int COL = 40;
	
	//菜单栏
	JMenuBar menubar = new JMenuBar();
	//游戏界面画板
	private JPanel paintPanel;
	private JPanel info = new JPanel();
	
	//各种组件
	private int score = 0;
	private int length = 1;
	private JLabel lScore = new JLabel("分数: ");
	private JLabel nowScore = new JLabel("0");
	private JLabel lLength = new JLabel("当前长度");
	private JLabel nowLength = new JLabel("1");
	private JLabel lSysTime;
	
	//速度设置
	private JRadioButtonMenuItem[] speedItems;
	private ButtonGroup speedGroup;
	
	private Timer timer;

	private Snake snake;
	private Food food;
	
	//游戏暂停标识
	private boolean pause = true;
	//游戏结束标识
	private boolean isOver = false;
	//游戏重新开始标识
	private boolean re = false;
	
	//字体
	private Font font14 = new Font("微软雅黑",Font.PLAIN,14);
	private Font font12 = new Font("微软雅黑",Font.PLAIN,12);
	
	public boolean getIsOver() {
		return isOver;
	}
	
	public boolean getPause() {
		return pause;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	//获取系统时间
	public static String getSysTime() {
		String sysTime = "";
		
		Calendar cld = Calendar.getInstance();
		int YY = cld.get(Calendar.YEAR);
			sysTime += YY;
		int MM = cld.get(Calendar.MONTH) + 1;
			if (MM < 10) {
				sysTime += "-0" + MM;
			} else {
				sysTime += "-" + MM;
			}
		int DD = cld.get(Calendar.DATE);
			if (DD < 10) {
				sysTime += "-0" + DD;
			} else {
				sysTime += "-" + DD;
			}
        int HH = cld.get(Calendar.HOUR_OF_DAY);
	        if (HH < 10) {
	        	sysTime += " 0" + HH;
	        } else {
	        	sysTime += " " + HH;
	        }
        int mm = cld.get(Calendar.MINUTE);
        	if (mm < 10) {
        		sysTime += ":0" + mm;
        	} else {
        		sysTime += ":" + mm;
        	}
        int SS = cld.get(Calendar.SECOND);
        	if (SS < 10) {
        		sysTime += ":0" + SS;
        	} else {
        		sysTime += ":" + SS;
        	}
		
		return sysTime;
	}
	
	//游戏结束
	public void end() {
		isOver = true;
		
		//弹出对话框，询问是否结束游戏
		int result = JOptionPane.showConfirmDialog(null, "游戏结束是否再次开始游戏", "游戏结束", JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.YES_NO_OPTION) {
			repaint();
			re = true;
			restart();
		} else {
			pause = true;
		}
	}
		
	//重新开始游戏
	public void restart() {
		isOver = true;
		pause = false;
		
		//初始化蛇和食物，将速度调为正常
		snake = null;
		snake = new Snake(this);
		speedItems[1].setSelected(true);
		
		food = null;
		food = new Food();
		
		//初始化分数和长度
		score = 0;
		length = 1;
		nowLength.setText("1");
		nowScore.setText("0");
		
		isOver = false;
		if (re) {
			new Thread(timer).start();
			re = false;
		}
	}
	
	public void init() throws Exception {
		//设置标题
		this.setTitle("贪吃蛇游戏");
		//设置风格
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		UIManager.setLookAndFeel(lookAndFeel);
		
		//菜单栏
		JMenu setting = new JMenu("设置");
		setting.setFont(font14);
		JMenu help = new JMenu("帮助");
		help.setFont(font14);
				
		//设置栏
		JMenuItem set_snake = new JMenuItem("设置蛇颜色");
		set_snake.setFont(font12);
		JMenuItem set_food = new JMenuItem("设置食物颜色");
		set_food.setFont(font12);
		JMenu set_speed = new JMenu("设置速度");
		set_speed.setFont(font12);
		
		//设置速度
		String[] speed = {"慢", "正常", "快", "飞快"};
		speedItems = new JRadioButtonMenuItem[speed.length];
		speedGroup = new ButtonGroup();
		for (int i = 0; i < speed.length; i++) {
			speedItems[i] = new JRadioButtonMenuItem(speed[i]);
			speedItems[i].setFont(font12);
			set_speed.add(speedItems[i]);
			speedGroup.add(speedItems[i]);
			speedItems[i].addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					// TODO Auto-generated method stub
					for (int i = 0; i < speedItems.length; i++) {
						if (speedItems[i].isSelected()) {
							if (i == 0) {
								snake.setSpeed(1000);
							} else if (i == 1) {
								snake.setSpeed(500);
							} else if (i == 2) {
								snake.setSpeed(300);
							} else if (i == 3) {
								snake.setSpeed(150);
							}
						}
					}
				}
			});
		}
		speedItems[1].setSelected(true);
		
		setting.add(set_snake);
		setting.add(set_food);
		setting.addSeparator();
		setting.add(set_speed);
		
		//帮助栏
		JMenuItem explain = new JMenuItem("说明", new ImageIcon("src//explain.jpg"));
		explain.setFont(font12);
		JMenuItem about = new JMenuItem("关于");
		about.setFont(font12);
				
		help.add(explain);
		help.addSeparator();
		help.add(about);
		
		menubar.add(setting);
		menubar.add(help);
		
		this.setJMenuBar(menubar);
		
		//组装视图
		info.add(lScore);
		lScore.setBounds(15, 10, 80, 20);
		lScore.setFont(font14);
		info.add(nowScore);
		nowScore.setBounds(15, 35, 80, 20);
		nowScore.setFont(new Font("微软雅黑", Font.BOLD, 14));
		nowScore.setForeground(Color.RED);
		info.add(lLength);
		lLength.setBounds(15, 70, 80, 20);
		lLength.setFont(font14);
		info.add(nowLength);
		nowLength.setBounds(15, 95, 80, 20);
		nowLength.setFont(font14);
		lSysTime = new JLabel(getSysTime());
		info.add(lSysTime);
		lSysTime.setBounds(10, 500, 200, 20);
		lSysTime.setFont(font14);
		
		info.setLayout(null);
		info.setBackground(Color.white);
		info.setBounds(600, 0, 185, 535);
		info.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 5));
		
		paintPanel = new PaintPanel();
		paintPanel.setBounds(0, 0, 600, 600);
		
		this.add(info);
		this.add(paintPanel);
		
		//初始化游戏
		snake = new Snake(this);
		food = new Food();
		timer = new Timer();
		new Thread(timer).start();
		
		//监听器
		//设置蛇的颜色
		set_snake.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color result = JColorChooser.showDialog(paintPanel, "颜色选择器", Color.white);
				
				snake.setColor(result);
			}
		});
		
		//设置食物颜色
		set_food.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color result = JColorChooser.showDialog(paintPanel, "颜色选择器", Color.white);
				
				food.setColor(result);
			}
		});
		
		//弹出说明界面
		explain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pause = true;
				new Explain();
			}	
		});
		
		//弹出关于界面
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pause = true;
				new About();
			}
		});
		
		this.addKeyListener(new MyKeyListener());
		
		//设置图标
		this.setIconImage(new ImageIcon("src//title.jpg").getImage());
		//设置窗口大小和位置
		this.setBounds(400, 150, 800, 600);
		//添加关闭窗口功能
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//禁止改变窗口大小
		this.setResizable(false);
		//显示窗口
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		MainFrame mf = new MainFrame();
		mf.init();
	}
	
	private class PaintPanel extends JPanel {
		//绘制游戏界面
		public void paint(Graphics g) {
			Color c = g.getColor();
			//绘制网格
			g.setColor(Color.BLACK);
			for (int i = 0; i < ROW; i++) {
				g.drawLine(0, i * BLOCK_HEIGHT, COL * BLOCK_WIDTH, i * BLOCK_HEIGHT);
			}
			for (int i = 0; i < COL; i++) {
				g.drawLine(i * BLOCK_WIDTH, 0, i * BLOCK_WIDTH, ROW * BLOCK_HEIGHT);
			}
			
			lSysTime.setText(MainFrame.getSysTime());
			g.setColor(c);
			snake.draw(g);
			boolean getScore = snake.eatFood(food);
			//吃到食物的加分
			if (getScore) {
				if (snake.getSpeed() == 1000) {
					score += 10;
				} else if (snake.getSpeed() == 500) {
					score += 20;
				} else if (snake.getSpeed() == 300) {
					score += 50;
				} else {
					score += 100;
				}
				
				length++;
				nowScore.setText(score + "");
				nowLength.setText(length + "");
			}
			food.draw(g);
			
			//显示游戏结束
			if (isOver) {
				g.setFont(new Font("微软雅黑", Font.BOLD, 50));
				g.setColor(Color.BLUE);
				
				g.drawString("游戏结束", 250, 250);
			}
		}
	}
	
	//计时器
	private class Timer implements Runnable {
		public void run() {
			while (!isOver) {	
				if (!pause) {
					repaint();
				}
				
				try {
					Thread.sleep(snake.getSpeed());
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	//监听器
	private class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			Direction direction = snake.snakeBody.getFirst().direction;
			
			if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {          //向右
                if (!pause && direction != Direction.RIGHT) {
                	snake.snakeBody.getFirst().direction = Direction.RIGHT;
                }
            } else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {    //向左
                if (!pause && direction != Direction.LEFT) {
                	snake.snakeBody.getFirst().direction = Direction.LEFT;
                }
            } else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {      //向上
                if (!pause && direction != Direction.UP) {
                	snake.snakeBody.getFirst().direction = Direction.UP;
                }
            } else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {    //向下
                if (!pause && direction != Direction.DOWN) {
                	snake.snakeBody.getFirst().direction = Direction.DOWN;
                }
            } else if(key == KeyEvent.VK_ESCAPE) {  //重新开始
                restart();
            } else if(key == KeyEvent.VK_SPACE) {
                if(!pause) { //暂停
                    pause = true;
                    isOver = false;
                } else { //开始
                    pause = false;
                    isOver = false;
                }
            }
		}
	}
}