package game;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;

/*
 * ˵������
 */

public class Explain extends JDialog {
	//����
	private Font font = new Font("΢���ź�", Font.PLAIN, 15);
	//�������
	private JPanel contentPanel;
	private JScrollPane scroll;
	
	public Explain() {
		//���ñ���
		this.setTitle("��Ϸ˵��");
		//����ͼ��
		this.setIconImage(new ImageIcon("src//explainTitle.jpeg").getImage());
		
		contentPanel = new JPanel();
		contentPanel.setLayout(null);
		//���ñ���ɫ
		contentPanel.setBackground(new Color(102, 204, 255));
		
		//��Ϸ˵��
		JTextArea ta = new JTextArea("��Ϸ˵�����£�\nͨ�����̵ķ����������������WASD���������ߵķ���\nEsc��:\t���¿�ʼ��Ϸ\n�ո��:\t��ͣ��Ϸ\n"
				+ "������Ϸ����Ҫ���ո����ʼ��Ϸ\n"
				+ "�˵��������ò˵����Ը����ߵ���ɫ��ʳ�����ɫ�Լ��ߵ��ٶ�\n"
				+ "��Ϸ������ұ߻���ʾ��ǰ�ķ����͵�ǰ����\n"
				+ "ҳ������½ǻ���ʾ��ǰ��ϵͳʱ��\n"
				+ "�����趨�ٶȲ�ͬ��������Ҳ��ͬ\n"
				+ "�����ƶ���Ϸ�������ײ���Լ���Ϸ����\n");
		
		ta.setFont(font);
		//��Ϊ���ɱ༭
		ta.setEditable(false);
		//����͸��
		ta.setOpaque(false);
		//�Զ�����
		ta.setLineWrap(true);
		
		scroll = new JScrollPane(ta, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS, JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(BorderFactory.createTitledBorder(null, "��Ϸ˵��", TitledBorder.CENTER, TitledBorder.DEFAULT_POSITION, new Font("΢���ź�", Font.BOLD, 15)));
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
