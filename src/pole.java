
import javax.swing.*;
import java.awt.*;
import javax.imageio.*;
import java.awt.event.*;
import java.io.*;

public class pole {

	public static void main(String[] args) {
		
		myFrame okno = new myFrame();
	}
}
	
	class myFrame extends JFrame
	{
		public myFrame()
		{
			// �������� ������� ������ � ����������� �� � ����
			myPanel p = new myPanel();
			Container cont = getContentPane();
			cont.add(p);
			// ��������� ����
			setTitle("Tetris");
			// ������� ����: ������������ � �������
			setBounds(0,0,1000,700);
			// ��� �������� ���� - ���������� ����������
			setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			// ������ ��������� �������� ����
			setResizable(false);
			// ����������� (�����) ����
			setVisible(true);
		}
	}
	
	class myPanel extends JPanel
	{
		 // ���������� ��� ���������� ������ ����
		 private game myGame;	 
		 // ��� �������: ��������� � ��������� ������ ����
		 private Timer tmDraw, tmUpdate;	 
		 // �����������, ������������ � ����
		 public Image color1,color2,color3,color4,color5,color6,color7,fon,endg;	 
		 // ������� ��� ���������� �����
		 private JLabel lb;	 
		 // ��� ������
		 private JButton btn1,btn2;	
		 // ������ �� ������
		 private myPanel pan;
		 private class myKey implements KeyListener  
		 {
			 // ����� ��� ������� �� �������
			 public void keyPressed(KeyEvent e)
			 {
				// ��������� ���� ������� �������
			   	int key = e.getKeyCode();	
			   	
			   	// ���� ������� ����� �� ������� ���������, �� ��������� ����������� �����
			   	if (key==KeyEvent.VK_LEFT) myGame. napr = 3;
			   	else if (key==KeyEvent.VK_UP) myGame. napr = 1;
			   	else if (key==KeyEvent.VK_RIGHT) myGame. napr = 2;
			   	else if (key==KeyEvent.VK_DOWN) myGame. napr = 5;
			 }
			 public void keyReleased(KeyEvent e) {}
			 public void keyTyped(KeyEvent e) {}
		 }  
		// ����������� ������ 
			public myPanel()
			{
		 	  // �������� ������ �� ���� ������ � ����������
			      pan = this;
				
				// ����������� ����������� ������� ��� ���������� � ������
			      this.addKeyListener(new myKey());		  
			      // ������ ������ � ������ - ��� ������ ������� �� ����������	
		         this.setFocusable(true);           
							
		       // ������� �������� ���� ����������� ��� ����
		       try
		       {
		      	 color1 = ImageIO.read(this.getClass().getResource("1.png"));
		      	 color2 = ImageIO.read(this.getClass().getResource("2.png"));
		      	 color3 = ImageIO.read(this.getClass().getResource("3.png"));
		      	 color4 = ImageIO.read(this.getClass().getResource("4.png"));
		      	 color5 = ImageIO.read(this.getClass().getResource("5.png"));
		      	 color6 = ImageIO.read(this.getClass().getResource("6.png"));
		      	 color7 = ImageIO.read(this.getClass().getResource("7.png"));
		      	 endg = ImageIO.read(this.getClass().getResource("endg.png")); 
		      	 fon = ImageIO.read(this.getClass().getResource("fon.png"));
		       }
		       catch (Exception ex) {}
		       
		       // ������� ������ ����� ����
		       myGame = new game();
		       myGame.start();
		                 
		       // �������, ����������� � ��������� ������ 
		       // ��� ��������� �������� ����  
		       tmDraw = new Timer(20,new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// �������� ����������� - paintComponent()
						repaint();
					}
				});          
		       tmDraw.start();
		       
		       // �������, ����������� � ��������� ������ 
		       // ��� ��������� ������ ����          
		       tmUpdate = new Timer(100,new ActionListener() {			
					@Override
					public void actionPerformed(ActionEvent arg0) {
						// ���� �� ����� ����, �� ���������� ������
						if (myGame.endg==false)
						{
						  myGame.perem();
						}				
						// ������� ���������� � ���������� �����
						lb.setText("����: "+myGame.kol);				
					}
				});
		       tmUpdate.start();
		       
		       // �������� ����������� ������������� ����������
		       // ��������� ���������� �� ������
		       setLayout(null);
		       
		       // ������� ��������� �������
		       lb = new JLabel("����: "+myGame.kol);
		       lb.setForeground(Color.BLACK);
		       lb.setFont(new Font("serif",0,40));
		       lb.setBounds(500, 150, 200, 100);
		       add(lb);
		       
		       // ������� ������ ����� ����          
		       btn1 = new JButton();
		       btn1.setText("����� ����");
		       btn1.setForeground(Color.BLUE);
		       btn1.setFont(new Font("serif",0,20));
		       btn1.setBounds(700, 70, 200, 100);
		       btn1.addActionListener(new ActionListener() {
		     	// ���������� ������� ��� ������� �� ������ ����� ����
					public void actionPerformed(ActionEvent arg0) {
						// ������ ����
						myGame.start();
						// �������� ����� � ������ ����� ����
						btn1.setFocusable(false);
						// �������� ����� � ������ �����				
						btn2.setFocusable(false);
						// ������ ����� ������
						pan.setFocusable(true);				
					}
				});
		       add(btn1);
		       
		       // ������� ������ �����
		       btn2 = new JButton();
		       btn2.setText("�����");
		       btn2.setForeground(Color.RED);
		       btn2.setFont(new Font("serif",0,20));
		       btn2.setBounds(700, 220, 200, 100);
		       btn2.addActionListener(new ActionListener() {
		       	// ���������� ������� ��� ������� �� ������ ����� ����        	  
					public void actionPerformed(ActionEvent arg0) {
					// ����� �� ���� - ���������� ������ ����������
						System.exit(0);				
					}
				});
		       add(btn2);
		                 
			}

			// ����� ���������
			public void paintComponent(Graphics gr)
			{			
				// �������� �������� ����
				super.paintComponent(gr);
						
				// ��������� ����
				gr.drawImage(fon,0,0,1000,750,null);
												
				// ��������� �������� ���� �� ��������� �������
				for (int i = 0; i < 30; i++) {
					for (int j = 0; j < 20; j++) {
						if (myGame.mas[i][j]!=0)
						{
						   if (myGame.mas[i][j]==1)
						   {
							   // ������� �������� ���������� ����� � �����
							   if (myGame.color==0) gr.drawImage(color1,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==1) gr.drawImage(color2,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==2) gr.drawImage(color3,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==3) gr.drawImage(color4,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==4) gr.drawImage(color5,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==5) gr.drawImage(color6,20+j*20, 10+i*20,20,20,null);
							   if (myGame.color==6) gr.drawImage(color7,20+j*20, 10+i*20,20,20,null);
						   }
						// ������� �������� ���������� ����� � ������
						   else if (myGame.mas[i][j]==2) gr.drawImage(color1,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==3) gr.drawImage(color2,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==4) gr.drawImage(color3,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==5) gr.drawImage(color4,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==6) gr.drawImage(color5,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==7) gr.drawImage(color6,20+j*20, 10+i*20,20,20,null);
						   else if (myGame.mas[i][j]==8) gr.drawImage(color7,20+j*20, 10+i*20,20,20,null);
						}
						//gr.setColor(Color.YELLOW);
						//gr.setFont(new Font("arial",0,22));
						//gr.drawString(""+myGame.mas[i][j], 12+j*20, 30+i*20);
					}
					
				}
				
				
				// ��������� ����� �������� ���� �� �����
				gr.setColor(Color.gray);		
				for (int i = 0; i <= 20; i++)
				{
					for (int j = 0; j <= 30; j++)
					{
					// ��������� ����� �����
					gr.drawLine(20+i*20, 10, 20+i*20, 610);
					gr.drawLine(20, 10+j*20, 420, 10+j*20);			
					}
				}	
				
				// ����� ����������� ����� ���� - ��� ��������� ����
				
				if (myGame.endg==true)
				{
					gr.drawImage(endg,250,300,400,200,null);
				}
				
			}	
	}

