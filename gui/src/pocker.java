
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.Timer;
class MyPanel extends JPanel{
	
	public BufferedImage img;
	private Timer timer;
	public void paintComponent(Graphics g)
{
	super.paintComponent(g);
	Color back=new Color(70,0,21);
	setBackground(back);
	
	 setPreferredSize(new Dimension(100,50));
	  setDoubleBuffered(true);
	  File input=new File("뒷장.PNG");
	  
	  try {
			 img=ImageIO.read(input);
		 }
		 catch(IOException e)
		 {
			e.printStackTrace();
			 
		 }
	  
	  g.drawImage(img,0,0,this);
	 
	  File money=new File("칩.PNG");
	  try {
			 img=ImageIO.read(money);
		 }
		 catch(IOException e)
		 {
			e.printStackTrace();
			 
		 }
	  g.drawImage(img,280,0,this);
	  
	  
	 File yourCard=new File("a.PNG");
	  try {
			 img=ImageIO.read(yourCard);
		 }
		 catch(IOException e)
		 {
			e.printStackTrace();
			 
		 }
	  g.drawImage(img,500,0,this);
	  
	 
}
	
}
/*
public class pocker extends JFrame implements ActionListener{
	private JPanel down_panel; 
	public int myremain=30;
	public int yourRemain=25;
	private JButton batting,giveUp;
private  JTextField remainchip, yourremainchip;
	public pocker()
	{ 
		setSize(850,600);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("indian pocker");
		
		MyPanel panel =new MyPanel();
		panel.add(new JLabel("게임 진행중"));
		this.remainchip=new JTextField();
		remainchip.setEditable(false);
		 remainchip.setBounds(0,0,150,50);
		remainchip.setFont(new Font("Serfi",Font.BOLD,14));
		remainchip.setText("  내 남은 칩 개수   "+myremain);
		panel.add(this.remainchip);
	
		this.yourremainchip=new JTextField();
		yourremainchip.setEditable(false);
		yourremainchip.setText("  상대 남은 칩 개수    "+yourRemain);
		panel.add(this.yourremainchip);
		
		this.batting=new JButton("배팅");
		this.batting.addActionListener(this);
		this.giveUp=new JButton("포기");
		this.giveUp.addActionListener(this);
		
		this.remainchip=new JTextField();
		remainchip.setEditable(false);
		down_panel=new JPanel();
		down_panel.add(this.batting);
		down_panel.add(this.giveUp);
		
	 this.setLayout(new BorderLayout());
	this.add(panel,BorderLayout.CENTER);
	this.add(down_panel,BorderLayout.SOUTH);
timer T=new timer();
// panel.add(this.T);
		//this.add(T,BorderLayout.SOUTH);

//setContentPane(new timer()); 
	 setVisible(true);
	}
	
public static void main(String[] args) {
		// TODO Auto-generated method stub
pocker p=new pocker();

}

	@Override	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	} 
}
*/