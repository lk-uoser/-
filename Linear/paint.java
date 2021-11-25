import java.awt.event.*;
import javax.swing.*;
import java.awt.*;


public class paint extends JFrame implements ActionListener {

	JButton b1,b2,b3,b4;
	JLabel lbl1, lbl2, lbl3, lbl4, l1, l2, l3, l5, l6;
	JTextField t1, t2, t3, t4, t01, t02, t03, t12, t13;
	JPanel p1, p2;

	public paint() {  //Frame���幹��
		super();
		this.setTitle("����ͼԪ����");
		this.setLayout(new BorderLayout());
		b1 = new JButton("DDA����");  //���Ӱ�ť�������Ӧ�¼�
		b1.addActionListener(this);
		b2 = new JButton("Bre����");
		b2.addActionListener(this);
		b3 = new JButton("�е�Բ����");
		b3.addActionListener(this);
		b4 = new JButton("�е���Բ����");
		b4.addActionListener(this);
		l1=new JLabel("������Բ�İ뾶��");  //���Label��ʾ����
		l2=new JLabel("������Բ/��Բ�ĺ����꣺");
		l3=new JLabel("������Բ/��Բ�������꣺");
		l5=new JLabel("��������Բ��aֵ��");
		l6=new JLabel("��������Բ��bֵ��");
		t01=new JTextField(3);  //TextField����������Ϣ
		t02=new JTextField(3);
		t03=new JTextField(3);
		t12=new JTextField(3);
		t13=new JTextField(3);
		lbl1 = new JLabel("������������:");  //���Label��ʾ����
		lbl2 = new JLabel("�������������:");
		lbl3 = new JLabel("�����յ������:");
		lbl4 = new JLabel("�����յ�������:");
		t1 = new JTextField(3);   //TextField����������Ϣ
		t2 = new JTextField(3);
		t3 = new JTextField(3);
		t4 = new JTextField(3);
		p1 = new JPanel();
		p1.setLayout(new GridLayout(4, 6));  //Panel p1����GridLayout����4��*6��
		p1.add(lbl1);               //������Щ�ؼ�����Panel p1
		p1.add(t1);
		p1.add(lbl2);
		p1.add(t2);
		p1.add(new JLabel());
		p1.add(new JLabel());
		p1.add(lbl3);
		p1.add(t3);
		p1.add(lbl4);
		p1.add(t4);
		p1.add(new JLabel());
		p1.add(new JLabel());
		p1.add(l1);
		p1.add(t01);
		p1.add(l2);
		p1.add(t02);
		p1.add(l3);
		p1.add(t03);
		p1.add(new JLabel());
		p1.add(new JLabel());
		p1.add(l5);
		p1.add(t12);
		p1.add(l6);
		p1.add(t13);
		this.add(p1, BorderLayout.SOUTH);  //��Panel p1����Frame���ϲ���SOUTH��

		p2 = new JPanel();
		p2.setLayout(new GridLayout(4, 1));  //Panel p2����GridLayout����4��*1��
		p2.add(b1);                          //������Щ�ؼ�����Panel p2
		p2.add(b2);
		p2.add(b3);
		p2.add(b4);
		this.add(p2, BorderLayout.EAST);  //��Panel p1����Frame�Ķ�����EAST��
		this.setBackground(Color.WHITE);  //����Frame����Ϊ��ɫ
	}

 

	public void DDAdrawline(Graphics g) {  //DDA�㷨��ֱ�ߣ�����ʵ������б��

		/*
		 * 700*800�������γ�70*80�ı��
		 */
		for(int i=0;i<700;i+=10)    
		{
			g.drawLine(0, i, 800, i);
		}
		for(int i=0;i<800;i+=10)
		{
			g.drawLine(i, 0, i, 700);
		}
		/*
		 * ��ȡ��ʼ���x��y�������ֹ���x��y����
		 */
		int x0, x1, y0, y1;
		String s1, s2, s3, s4;
		float dx, dy, k, x,y;
		s1 = t1.getText();
		s2 = t2.getText();
		s3 = t3.getText();
		s4 = t4.getText();
		x0 = Integer.parseInt(s1);
		y0 = Integer.parseInt(s2);
		x1 = Integer.parseInt(s3);
		y1 = Integer.parseInt(s4);
		x0=x0*10;    //���������ֵ��10��������ӵĵ�λ������10��
		y0=y0*10;
		x1=x1*10;
		y1=y1*10;
		int op=0;
		if((x1-x0) >= 0 && (y1-y0) >= 0)           //���ݿ�ʼ�����ֹ��������ж�ֱ�߻��ߵķ���
			op=1;
		else if((x1-x0) <= 0 && (y1-y0) >= 0)
			op=2;
		else if((x1-x0) <= 0 && (y1-y0) <= 0)
			op=3;
		else if((x1-x0) >= 0 && (y1-y0) <= 0)
			op=4;
		dy = Math.abs(y1 - y0);        //ȡ�����x�仯��y�仯�ľ���ֵ
		dx = Math.abs(x1 - x0);
		if (dx != 0) {              //��֤����б��ʱ��ĸ��Ϊ0
			k = dy / dx;
			if(k<1){               //б��С��1ʱ
				y = y0;
				/*
				 * ����op�ֳ��ĸ����򣬷ֱ��������
				 */
				if(op==1)
				{
					for (int xx = x0; xx <= x1; xx+=10) {   //x����ÿ�μ�10��λ
						//g.drawString(".", xx, (int) (y));
						if(y%10>=5)//�Ը�����y������������
						{
							g.fillRect(xx, (int)(y-y%10)+10, 10, 10);
						}
						else
						g.fillRect(xx, (int)(y-y%10), 10, 10);
						y = y + k*10;                      //yÿ�μ� k*10��λ
						try
						{
							Thread.currentThread().sleep(100);  //�����߳�����0.1s
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==2)
				{
					for (int xx = x0; xx >= x1; xx-=10) {
						//g.drawString(".", xx, (int) (y));
						if(y%10>=5)
						{
							g.fillRect(xx, (int)(y-y%10)+10, 10, 10);
						}
						else
						g.fillRect(xx, (int)(y-y%10), 10, 10);
						y = y + k*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==3)
				{
					for (int xx = x0; xx >= x1; xx-=10) {
						//g.drawString(".", xx, (int) (y));
						if(y%10>=5)
						{
							g.fillRect(xx, (int)(y-y%10)+10, 10, 10);
						}
						else
						g.fillRect(xx, (int)(y-y%10), 10, 10);
						y = y - k*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==4)
				{
					for (int xx = x0; xx <= x1; xx+=10) {
						//g.drawString(".", xx, (int) (y));
						if(y%10>=5)
						{
							g.fillRect(xx, (int)(y-y%10)+10, 10, 10);
						}
						else
						g.fillRect(xx, (int)(y-y%10), 10, 10);
						y = y - k*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				
			}
			else{  //б�ʴ���1ʱ
				x = x0;
				if(op==1)
				{
					for(int yy=y0;yy<=y1;yy+=10){  //y����ÿ�μ�10��λ
						//g.drawString(".", (int)(x),  yy);
						if(x%10>=5)  //�Ը�����x������������
						{
							g.fillRect((int) (x-x%10)+10, yy , 10, 10);
						}
						else
						g.fillRect((int) (x-x%10), yy , 10, 10);
						x = x + (dx/dy)*10;
						try
						{
							Thread.currentThread().sleep(100); //ͬ��
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==2)
				{
					for(int yy=y0;yy<=y1;yy+=10){
						//g.drawString(".", (int)(x),  yy);
						if(x%10>=5)
						{
							g.fillRect((int) (x-x%10)+10, yy , 10, 10);
						}
						else
						g.fillRect((int) (x-x%10), yy , 10, 10);
						x = x - (dx/dy)*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==3)
				{
					for(int yy=y0;yy>=y1;yy-=10){
						//g.drawString(".", (int)(x),  yy);
						if(x%10>=5)
						{
							g.fillRect((int) (x-x%10)+10, yy , 10, 10);
						}
						else
						g.fillRect((int) (x-x%10), yy , 10, 10);
						x = x - (dx/dy)*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				if(op==4)
				{
					for(int yy=y0;yy>=y1;yy-=10){
						//g.drawString(".", (int)(x),  yy);
						if(x%10>=5)
						{
							g.fillRect((int) (x-x%10)+10, yy , 10, 10);
						}
						else
						g.fillRect((int) (x-x%10), yy , 10, 10);
						x = x + (dx/dy)*10;
						try
						{
							Thread.currentThread().sleep(100);
						}
						catch(Exception e)
						{
							
						}
					}
				}
				
			}
		} 
		else {  //dx����0ʱ��x������y���������½�
			for (int i = y0; i <= y1; i+=10) {
				//g.drawString(".", x0, i);
				g.fillRect(x0, i, 10, 10);
			}
		}
	}
	
	public void Bresenhamdrawline(Graphics g) {  //Bresenham�㷨ʵ������б�ʻ���

		/*
		 * ��������ϵ���
		 */
		for(int i=0;i<700;i+=10)  
		{
			g.drawLine(0, i, 800, i);
		}
		for(int i=0;i<800;i+=10)
		{
			g.drawLine(i, 0, i, 700);
		}
		
		/*
		 * ��ȡ��������Լ��յ�������л���
		 */
		int x0, x1, y0, y1;
		String s1, s2, s3, s4;
		float dx, dy, k, x,y;
		s1 = t1.getText();
		s2 = t2.getText();
		s3 = t3.getText();
		s4 = t4.getText();
		x0 = Integer.parseInt(s1);
		y0 = Integer.parseInt(s2);
		x1 = Integer.parseInt(s3);
		y1 = Integer.parseInt(s4);
		x0=x0*10;  //���Ա��10
		y0=y0*10;
		x1=x1*10;
		y1=y1*10;
		int op=0;
		if((x1-x0) >= 0 && (y1-y0) >= 0)      //���ĸ�������
			op=1;
		else if((x1-x0) <= 0 && (y1-y0) >= 0)
			op=2;
		else if((x1-x0) <= 0 && (y1-y0) <= 0)
			op=3;
		else if((x1-x0) >= 0 && (y1-y0) <= 0)
			op=4;
		dy = Math.abs(y1 - y0);  //dx��dyȡ����ֵ
		dx = Math.abs(x1 - x0);
		float p,pa,pb;
		if (dx != 0) {  //ͬ�Ͻ���dx����0����0������
			k = dy / dx;
			if(k<1){  //б��С��1��ͬʱ����p�������趨
				pa=2*dy;
				pb=2*dy - 2*dx;
				p=2*dy - dx;
				y=y0;
				g.fillRect(x0, y0, 10, 10); //����������
				if(op==1)  //����op�ֲ�ͬ����������
				{
					for(int xx = x0+10; xx <= x1; xx += 10)  //��x�ݽ�����
					{
						if(p>0)             //������p����0��x�ͣ�y������һ��Ԫ��10��
						{
							p+=pb;          //p����0ʱ��p��2*dy - 2*dx�����򣬼�2*dy
							y+=10;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}                 
						else               //������pС�ڵ���0��x����һ��Ԫ��10������y����
						{
							p+=pa;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);  //ͬ��˯��
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==2)
				{
					for(int xx = x0-10; xx >= x1; xx -= 10)
					{
						if(p>0)
						{
							p+=pb;
							y+=10;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==3)
				{
					for(int xx = x0-10; xx >= x1; xx -= 10)
					{
						if(p>0)
						{
							p+=pb;
							y-=10;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==4)
				{
					for(int xx = x0+10; xx <= x1; xx += 10)
					{
						if(p>0)
						{
							p+=pb;
							y-=10;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect(xx, (int)(y), 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
			}
			else{      //б�ʴ��ڵ���1ʱ��p�������趨�Լ�pa��pb�����趨�����仯
				pa=2*dx;
				pb=2*dx - 2*dy;
				p=2*dx - dy;
				x=x0;
				g.fillRect(x0, y0, 10, 10);
				if(op==1)           //����op��ֵ�жϻ��߷���
				{
					for(int yy = y0+10; yy <= y1; yy += 10) //б�ʴ���1������y�ݽ���λ��
					{
						if(p>0)  //p��������0ʱ��x��y����һ��λ��
						{
							p+=pb;  //������p����0������2*dx - 2*dy�����򣬼�2*dx
							x+=10;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
							
							}
						}
						else  //p����С�ڵ�0ʱ��y��һ��λ��x����
						{
							p+=pa;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==2)
				{
					for(int yy = y0+10; yy <= y1; yy += 10)
					{
						if(p>0)
						{
							p+=pb;
							x-=10;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==3)
				{
					for(int yy = y0-10; yy >= y1; yy -= 10)
					{
						if(p>0)
						{
							p+=pb;
							x-=10;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				if(op==4)
				{
					for(int yy = y0-10; yy >= y1; yy -= 10)
					{
						if(p>0)
						{
							p+=pb;
							x+=10;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
						else
						{
							p+=pa;
							g.fillRect((int)(x), yy, 10, 10);
							try
							{
								Thread.currentThread().sleep(100);
							}
							catch(Exception e)
							{
								
							}
						}
					}
				}
				
				
			}
		} 
		else {  //��dx����0�����仯y����
			for (int i = y0; i <= y1; i+=10)
				g.fillRect(x0, i, 10, 10);
		}
	}

	public void cirpaint(Graphics g)  //�е㷨��Բ
	{
		/*
		 * ��������
		 */
		for(int i=0;i<500;i+=10)
		{
			g.drawLine(0, i, 500, i);
		}
		for(int i=0;i<500;i+=10)
		{
			g.drawLine(i, 0, i, 500);
		}
		/*
		 * ��ȡ�뾶r���е�����x��yֵ
		 */
		int x, y, r ,x0, y0;
		String sx,sy,sr;
		sr=t01.getText();
		r=Integer.parseInt(sr);
		sx=t02.getText();
		x=Integer.parseInt(sx);
		sy=t03.getText();
		y=Integer.parseInt(sy);
		x=x*10;  //�˵�λ��
		y=y*10;
		r=r*10;
		
		double p=5.0/4.0-r/10;  //���ó�ʼ���߲���pֵ
		x0=0;
		y0=r/10;  //����Բ���ڣ�0��0������Բ��ʼ��Ϊ��0��r��
		/*
		 * ���˷�֮һԲ������ͨ��Բ�İ˶Գƻ�����Բ
		 */
		while(x0<=y0)
		{
			/*
			 * һ�°˸�fillRect�����˸������໥�ԳƵİ˷�Բ
			 */
			g.fillRect(x0*10+x, y0*10+y, 10, 10);  //����Բ��Ϊ��0��0���Ļ��ߺ����ʵ��ԲԲ��ƽ��
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(y0*10+x, x0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(y0*10+x, -x0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-y0*10+x, -x0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-y0*10+x, x0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			/*
			 * �е㷨��Բ�Ƶ��ľ��߲���p�ı仯��
			 * ��p����0��p��2*(x0+1)+1��y���䣻
			 * ����p��2*(x0+1)-2*(y0)+1������y0��1
			 */
			if(p<0)
			{
				p+=2*(x0+1)+1;
			}
			else
			{
				y0=y0-1;
				p+=2*(x0+1)-2*(y0)+1;
			}
			x0++;  //��һ���˷�֮һԲ������б�ʾ�С��1����ʼ����x0�ݽ�����
			
		}
		
		
		
	}
	
	public void cirtpaint(Graphics g)  //�е㷨����Բ
	{
		/*
		 * ���������50*50������λ��10��
		 */
		for(int i=0;i<500;i+=10)
		{
			g.drawLine(0, i, 500, i);
		}
		for(int i=0;i<500;i+=10)
		{
			g.drawLine(i, 0, i, 500);
		}
		/*
		 * ��ȡ��Բ�ĳ���a������bֵ���Լ�Բ������x����yֵ
		 */
		int x, y, x0, y0, a, b;
		String sx,sy,sr,sa,sb;
		sx=t02.getText();
		x=Integer.parseInt(sx);
		sy=t03.getText();
		y=Integer.parseInt(sy);
		sa=t12.getText();
		a=Integer.parseInt(sa);
		sb=t13.getText();
		b=Integer.parseInt(sb);
		x=x*10;  //�˵�λ��10
		y=y*10;
		a=a*10;
		b=b*10;
		
		double p=(b/10)*(b/10)-(a/10)*(a/10)*(b/10)+(a/10)*(a/10)/4.0;//���þ��߲�����ʼֵ
		x0=0;
		y0=b/10;  //���ó�ʼ����λ�ã�0��b��������Բ�ļ����ڣ�0��0��
		/*
		 * ���ķ�֮һ��Բ����б��С�ڵ���1��һ���ֻ���
		 */
		while((b/10)*(b/10)*x0<=(a/10)*(a/10)*y0)  //�ж�����б��С�ڵ���1����ִ��ѭ��
		{
			/*
			 * �����ĸ�fillRect�ֱ��ķ�֮һ��Բ����б�ʾ���ֵС��1��һ���ֻ��ߣ�������Բ���ĶԳ��ԣ�
			 */
			g.fillRect(x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			/*
			 * �����е㻭��Բ�㷨�Ƶ����߲���p�ı仯��
			 * ��pС��0��x0��1��y0���䣬p��2*(b/10)*(b/10)*x0+(b/10)*(b/10)
			 * ����x0��1��y0��1��p��2*(b/10)*(b/10)*x0+(b/10)*(b/10)-2*(a/10)*(a/10)*y0
			 */
			if(p<0)
			{
				x0++;         //����б��С��1��ʼ����x0�ݽ�
				p+=2*(b/10)*(b/10)*x0+(b/10)*(b/10);
			}
			else
			{
				x0++;
				y0--;
				p+=2*(b/10)*(b/10)*x0+(b/10)*(b/10)-2*(a/10)*(a/10)*y0;
			}
			
		}
		p=(b/10)*(b/10)*(x0+0.5)*(x0+0.5)+(a/10)*(a/10)*(y0-1)-(b/10)*(b/10)*(a/10)*(a/10); //��ʼ�㼴��һ�λ��ߵ����һ���㣨x0��y0���������ó�ʼ����p
		/*
		 * ���ķ�֮һ��Բ����б�ʴ��ڵ���1��һ���ֻ���
		 */
		while(y0>=0)
		{
			/*
			 * �����ĸ�fillRect�ֱ��ķ�֮һ��Բ����б�ʾ���ֵ���ڵ���1��һ���ֻ��ߣ�������Բ���ĶԳ��ԣ�
			 */
			g.fillRect(x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, -y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			g.fillRect(-x0*10+x, y0*10+y, 10, 10);
			try
			{
				Thread.currentThread().sleep(100);
			}
			catch(Exception e)
			{
				
			}
			/*
			 * �����е㻭��Բ�㷨�Ƶ����߲���p�ı仯��
			 * ��pС��0��x0��1��y0��1��p��2*(b/10)*(b/10)*x0+(a/10)*(a/10)-2*(a/10)*(a/10)*y0
			 * ����x0���䣬y0��1��p��-2*(a/10)*(a/10)*y0+(a/10)*(a/10)
			 */
			if(p<0)
			{
				y0--;  //б�ʴ���1��ʼ��y0�ݽ�
				x0++;
				p+=2*(b/10)*(b/10)*x0+(a/10)*(a/10)-2*(a/10)*(a/10)*y0;
				
			}
			else
			{
				y0--;
				p+=-2*(a/10)*(a/10)*y0+(a/10)*(a/10);
			}
		}
		
		
		
		
	}
	
	public void actionPerformed(ActionEvent e) {  //�ĸ����ư�ť����¼�
		if (e.getSource()==b1) {            //DDA���ƣ�����DDAdrawline(g)ʵ��DDA����
			Graphics g = this.getGraphics();
			g.clearRect(0, 0, 800, 700);
			DDAdrawline(g);
			
		}
		if (e.getSource()==b2) {          //Bresenham���ƣ�����Bresenhamdrawline(g)ʵ��Bresenham����
			Graphics g = this.getGraphics();
			g.clearRect(0, 0, 800, 700);
			Bresenhamdrawline(g);
			
		}
		if (e.getSource()==b3) {          //�е㻭Բ������cirpaint(g)ʵ�ֻ�Բ
			Graphics g = this.getGraphics();
			g.clearRect(0, 0, 800, 700);
			cirpaint(g);
			
		}
		if (e.getSource()==b4) {         //�е㻭��Բ������cirtpaint(g)ʵ�ֻ���Բ
			Graphics g = this.getGraphics();
			g.clearRect(0, 0, 800, 700);
			cirtpaint(g);
			
		}
	}
 

	public static void main(String[] args) {

		paint frame = new paint();         //����paint�����
		frame.setSize(1000, 800);           //����Frame��С��λ�ã��Լ���ͼ�ɼ���
		frame.setLocation(400, 100);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);

	}

}