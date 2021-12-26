package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.Border;
import java.util.*;

import snake.*;

/*
 * ��Ϸ������
 */

public class MainFrame extends JFrame{
	//����Ŀ�ͳ�
	public static final int BLOCK_WIDTH = 15;
	public static final int BLOCK_HEIGHT = 15;
			
	//��Ϸ����ķ��������������
	public static final int ROW = 36;
	public static final int COL = 40;
	
	//�˵���
	JMenuBar menubar = new JMenuBar();
	//��Ϸ���滭��
	private JPanel paintPanel;
	private JPanel info = new JPanel();
	
	//�������
	private int score = 0;
	private int length = 1;
	private JLabel lScore = new JLabel("����: ");
	private JLabel nowScore = new JLabel("0");
	private JLabel lLength = new JLabel("��ǰ����");
	private JLabel nowLength = new JLabel("1");
	private JLabel lSysTime;
	
	//�ٶ�����
	private JRadioButtonMenuItem[] speedItems;
	private ButtonGroup speedGroup;
	
	private Timer timer;

	private Snake snake;
	private Food food;
	
	//��Ϸ��ͣ��ʶ
	private boolean pause = true;
	//��Ϸ������ʶ
	private boolean isOver = false;
	//��Ϸ���¿�ʼ��ʶ
	private boolean re = false;
	
	//����
	private Font font14 = new Font("΢���ź�",Font.PLAIN,14);
	private Font font12 = new Font("΢���ź�",Font.PLAIN,12);
	
	public boolean getIsOver() {
		return isOver;
	}
	
	public boolean getPause() {
		return pause;
	}
	
	public void setPause(boolean pause) {
		this.pause = pause;
	}
	
	//��ȡϵͳʱ��
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
	
	//��Ϸ����
	public void end() {
		isOver = true;
		
		//�����Ի���ѯ���Ƿ������Ϸ
		int result = JOptionPane.showConfirmDialog(null, "��Ϸ�����Ƿ��ٴο�ʼ��Ϸ", "��Ϸ����", JOptionPane.YES_NO_OPTION);
		
		if (result == JOptionPane.YES_NO_OPTION) {
			repaint();
			re = true;
			restart();
		} else {
			pause = true;
		}
	}
		
	//���¿�ʼ��Ϸ
	public void restart() {
		isOver = true;
		pause = false;
		
		//��ʼ���ߺ�ʳ����ٶȵ�Ϊ����
		snake = null;
		snake = new Snake(this);
		speedItems[1].setSelected(true);
		
		food = null;
		food = new Food();
		
		//��ʼ�������ͳ���
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
		//���ñ���
		this.setTitle("̰������Ϸ");
		//���÷��
		String lookAndFeel = UIManager.getSystemLookAndFeelClassName();
		UIManager.setLookAndFeel(lookAndFeel);
		
		//�˵���
		JMenu setting = new JMenu("����");
		setting.setFont(font14);
		JMenu help = new JMenu("����");
		help.setFont(font14);
				
		//������
		JMenuItem set_snake = new JMenuItem("��������ɫ");
		set_snake.setFont(font12);
		JMenuItem set_food = new JMenuItem("����ʳ����ɫ");
		set_food.setFont(font12);
		JMenu set_speed = new JMenu("�����ٶ�");
		set_speed.setFont(font12);
		
		//�����ٶ�
		String[] speed = {"��", "����", "��", "�ɿ�"};
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
		
		//������
		JMenuItem explain = new JMenuItem("˵��", new ImageIcon("src//explain.jpg"));
		explain.setFont(font12);
		JMenuItem about = new JMenuItem("����");
		about.setFont(font12);
				
		help.add(explain);
		help.addSeparator();
		help.add(about);
		
		menubar.add(setting);
		menubar.add(help);
		
		this.setJMenuBar(menubar);
		
		//��װ��ͼ
		info.add(lScore);
		lScore.setBounds(15, 10, 80, 20);
		lScore.setFont(font14);
		info.add(nowScore);
		nowScore.setBounds(15, 35, 80, 20);
		nowScore.setFont(new Font("΢���ź�", Font.BOLD, 14));
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
		
		//��ʼ����Ϸ
		snake = new Snake(this);
		food = new Food();
		timer = new Timer();
		new Thread(timer).start();
		
		//������
		//�����ߵ���ɫ
		set_snake.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color result = JColorChooser.showDialog(paintPanel, "��ɫѡ����", Color.white);
				
				snake.setColor(result);
			}
		});
		
		//����ʳ����ɫ
		set_food.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				Color result = JColorChooser.showDialog(paintPanel, "��ɫѡ����", Color.white);
				
				food.setColor(result);
			}
		});
		
		//����˵������
		explain.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pause = true;
				new Explain();
			}	
		});
		
		//�������ڽ���
		about.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				pause = true;
				new About();
			}
		});
		
		this.addKeyListener(new MyKeyListener());
		
		//����ͼ��
		this.setIconImage(new ImageIcon("src//title.jpg").getImage());
		//���ô��ڴ�С��λ��
		this.setBounds(400, 150, 800, 600);
		//��ӹرմ��ڹ���
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//��ֹ�ı䴰�ڴ�С
		this.setResizable(false);
		//��ʾ����
		this.setVisible(true);
	}
	
	public static void main(String[] args) throws Exception {
		MainFrame mf = new MainFrame();
		mf.init();
	}
	
	private class PaintPanel extends JPanel {
		//������Ϸ����
		public void paint(Graphics g) {
			Color c = g.getColor();
			//��������
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
			//�Ե�ʳ��ļӷ�
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
			
			//��ʾ��Ϸ����
			if (isOver) {
				g.setFont(new Font("΢���ź�", Font.BOLD, 50));
				g.setColor(Color.BLUE);
				
				g.drawString("��Ϸ����", 250, 250);
			}
		}
	}
	
	//��ʱ��
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
	
	//������
	private class MyKeyListener extends KeyAdapter {
		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			Direction direction = snake.snakeBody.getFirst().direction;
			
			if(key == KeyEvent.VK_RIGHT || key == KeyEvent.VK_D) {          //����
                if (!pause && direction != Direction.RIGHT) {
                	snake.snakeBody.getFirst().direction = Direction.RIGHT;
                }
            } else if(key == KeyEvent.VK_LEFT || key == KeyEvent.VK_A) {    //����
                if (!pause && direction != Direction.LEFT) {
                	snake.snakeBody.getFirst().direction = Direction.LEFT;
                }
            } else if(key == KeyEvent.VK_UP || key == KeyEvent.VK_W) {      //����
                if (!pause && direction != Direction.UP) {
                	snake.snakeBody.getFirst().direction = Direction.UP;
                }
            } else if(key == KeyEvent.VK_DOWN || key == KeyEvent.VK_S) {    //����
                if (!pause && direction != Direction.DOWN) {
                	snake.snakeBody.getFirst().direction = Direction.DOWN;
                }
            } else if(key == KeyEvent.VK_ESCAPE) {  //���¿�ʼ
                restart();
            } else if(key == KeyEvent.VK_SPACE) {
                if(!pause) { //��ͣ
                    pause = true;
                    isOver = false;
                } else { //��ʼ
                    pause = false;
                    isOver = false;
                }
            }
		}
	}
}