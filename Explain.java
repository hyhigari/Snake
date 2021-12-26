package game;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/*
 * 说明界面
 */

public class Explain extends JDialog {
	//字体
	private Font font = new Font("微软雅黑", Font.PLAIN, 15);
	//内容面板
	private JPanel contentPanel;
	private JScrollPane scroll;
	
	public Explain() {
		//设置标题
		this.setTitle("游戏说明");
		//设置图标
		this.setIconImage(new ImageIcon("src//explainTitle.jpeg").getImage());
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		//设置背景色
		contentPanel.setBackground(new Color(102, 204, 255));
		
		//游戏说明
		JTextArea ta = new JTextArea("游戏说明如下：\n通过键盘的方向键↑←↓→或者WASD键来控制蛇的方向\nEsc键:\t重新开始游戏\n空格键:\t暂停游戏\n"
				+ "进入游戏后需要按空格键开始游戏\n"
				+ "菜单栏的设置菜单可以更改蛇的颜色和食物的颜色以及蛇的速度\n"
				+ "游戏界面的右边会显示当前的分数和当前长度\n"
				+ "页面的右下角会显示当前的系统时间\n"
				+ "根据设定速度不同分数增加也不同\n"
				+ "当蛇移动游戏界面外或撞到自己游戏结束\n");
		
		ta.setFont(font);
		//设为不可编辑
		ta.setEditable(false);
		//背景透明
		ta.setOpaque(false);
		//自动换行
		ta.setLineWrap(true);
		
		scroll = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(BorderFactory.createTitledBorder(null, "游戏说明", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("微软雅黑", Font.BOLD, 15)));
		scroll.setOpaque(false);
		scroll.getViewport().setOpaque(false);
		
		contentPanel.add(scroll);
		scroll.setBounds(10, 10, 400, 400);
		
		this.add(contentPanel);
		
		this.setBounds(600, 200, 400, 400);
		this.setResizable(false);
		this.setVisible(true);
	}
}
