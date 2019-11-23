package Util;

import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.SwingWorker;

public class CircleProgressBar extends JPanel {
	
	private int minimumProgress;
	private int maxmumProgress;
	private int progress;
	private String progressText;
	private Color backgroundColor;
	private Color foregroundColor;
	
	public CircleProgressBar() {
		minimumProgress = 0;
		maxmumProgress = 100;
		progressText = "0%";
	}
	
	public void paint(Graphics g) {
		super.paint(g);
		Graphics2D graphics2D = (Graphics2D) g;
		//开启抗锯齿
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		int x = 0;
		int y = 0;
		int width = 0;
		int height = 0;
		int fontSize = 0;
		if(getWidth()>=getHeight()) {
			x = (getWidth() - getHeight())/2 + 25;
			y = 25;
			width = getHeight() - 50;
			height = getHeight() - 50;
			fontSize = getWidth() / 8;
			
		}
		
		else {
			x = 25;
			y = (getHeight() - getWidth())/2 + 25;
			width = getWidth() - 50;
			height = getWidth() -50;
			fontSize = getHeight() /8;
		}
		
		graphics2D.setStroke(new BasicStroke(20.0f));
		graphics2D.setColor(backgroundColor);
		graphics2D.drawArc(x, y, width, height, 0, 360);
		graphics2D.setColor(foregroundColor);
		graphics2D.drawArc(x, y, width, height, 90, -(int)(360*((progress*1.0)/ (maxmumProgress - minimumProgress))));
		graphics2D.setFont(new Font("黑体",Font.BOLD,fontSize));
		FontMetrics fontMetrics = graphics2D.getFontMetrics();
		int digitalWidth = fontMetrics.stringWidth(progressText);
		int digitalAscent = fontMetrics.getAscent();
		graphics2D.setColor(foregroundColor);
		graphics2D.drawString(progressText,getWidth() /2 - digitalWidth /2, getHeight() / 2 + digitalAscent /2);
		
	}
	
	public int getProgress() {
		return progress;
	}

	public void setProgress(int progress) {
		if(progress >= minimumProgress && progress<=maxmumProgress) {
			this.progress = progress;
		}
		if(progress > maxmumProgress) {
			this.progress = maxmumProgress;
		}
		this.progressText = String.valueOf(progress + "%");
		this.repaint();
	}
	
	public Color getBackgroundColor() {
		return backgroundColor;
	}
	
	public void setBackgroundColor(Color backgroundColor) {
		this.backgroundColor = backgroundColor;
		this.repaint();
	}
	
	public Color getForegroundColor() {
		return foregroundColor;
	}
	
	public void setForegroundColor(Color foregroundColor) {
		this.foregroundColor = foregroundColor;
		this.repaint();
	}
	
	
	public static void main(String[] args) {
		GUIUtil.useLNF();
		//面板
		JPanel p = new JPanel();
		//进度条组件
		CircleProgressBar cpb =new CircleProgressBar();
		cpb.setBackgroundColor(ColorUtil.blueColor);
		cpb.setProgress(0);
		
		//按钮
		JButton b = new JButton("点击");
		//添加组件
		p.setLayout(new BorderLayout());
		p.add(cpb,BorderLayout.CENTER);
		p.add(b,BorderLayout.SOUTH);
		//显示面板
		GUIUtil.showPanel(p);
		
		//给按钮添加监听器
		b.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				new SwingWorker () {
					protected Object doInBackground() throws Exception{
						for(int i =0 ;i<100;i++) {
							cpb.setProgress(i+1);
							cpb.setForegroundColor(ColorUtil.getByPercentage(i +1));
							try {
								Thread.sleep(100);
							} catch (InterruptedException e2) {
								// TODO: handle exception
								e2.printStackTrace();
							}
						}
						return null;
					}
				}.execute();
			}
		});
		
	}
	
}
