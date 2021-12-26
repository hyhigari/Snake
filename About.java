package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.net.*;

/*
 * ���ڽ���
 */

public class About extends JDialog{
	private JPanel versionPanel;
	private JPanel sheetPanel;
	
	//����
	private Font font = new Font("΢���ź�", Font.PLAIN, 15);
	private Font font2 = new Font("΢���ź�", Font.PLAIN, 13);
	
	public About() {
		//���ô��ڱ���
		setTitle("����");
		//����ͼ��
		this.setIconImage(new ImageIcon("src//aboutTitle.jpg").getImage());
		//����Ϊģ̬����
		this.setModal(true);
		
		versionPanel = new JPanel();
		versionPanel.setLayout(null);
		
		//��Ϸ��Ϣ
		JLabel name = new JLabel("̰����");
		name.setBounds(170, 25, 200, 30);
		name.setFont(font);
		JLabel version = new JLabel("�汾: 1.2.1");
		version.setBounds(155, 50, 200, 30);
		version.setFont(font);
		
		versionPanel.add(name);
		versionPanel.add(version);
		
		sheetPanel = new JPanel();
		sheetPanel.setLayout(null);
		
		JLabel label = new JLabel("��ҳ");
		label.setBounds(175, 10, 200, 30);
		label.setFont(font);
		JLabel lGithub = new JLabel("Github: ");
		lGithub.setFont(font2);
		lGithub.setBounds(50, 25, 200, 30);
		JLabel github = new JLabel("https://github.com/hyhigari/Snake");
		github.setFont(font2);
		github.setBounds(75, 50, 500, 30);
		github.addMouseListener(new InternetMonitor());
		JLabel lBlog = new JLabel("����: ");
		lBlog.setFont(font2);
		lBlog.setBounds(50, 75, 200, 30);
		JLabel blog = new JLabel("https://www.luogu.com.cn/blog/indexerror/");
		blog.setFont(font2);
		blog.setBounds(75, 100, 500, 30);
		blog.addMouseListener(new InternetMonitor());
		
		JLabel information = new JLabel("��2�� 086 ��˼��");
		information.setFont(font2);
		information.setBounds(135, 200, 200, 30);
		
		sheetPanel.setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
		sheetPanel.add(label);
		sheetPanel.add(lGithub);
		sheetPanel.add(github);
		sheetPanel.add(lBlog);
		sheetPanel.add(blog);
		sheetPanel.add(information);
		
		this.add(versionPanel);
		versionPanel.setBounds(0, 0, 400, 100);
		this.add(sheetPanel);
		sheetPanel.setBounds(0, 100, 386, 262);
		sheetPanel.setBackground(Color.white);
		
		this.setLayout(null);
		this.setBounds(600, 200, 400, 400);
		this.setResizable(false);
		this.setVisible(true);
	}
	
	private class InternetMonitor extends MouseAdapter {
		
		public void mouseClicked(MouseEvent e) {
			JLabel temp = (JLabel)e.getSource();
			String url = temp.getText(); 
			URI uri;
			try {
				uri = new URI(url);
				Desktop desk = Desktop.getDesktop();
				if(Desktop.isDesktopSupported() && desk.isSupported(Desktop.Action.BROWSE)){
					try {
						desk.browse(uri);
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} catch (URISyntaxException e1) {
					e1.printStackTrace();
			}
		}
		
		public void mouseEntered(MouseEvent e){
			JLabel JLabel_temp = (JLabel)e.getSource();
			JLabel_temp.setForeground(Color.RED);
		}
		
		public void mouseExited(MouseEvent e){
			JLabel JLabel_temp = (JLabel)e.getSource();
			JLabel_temp.setForeground(Color.BLUE);
		}
	}
}
