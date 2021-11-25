package Scanner;

import java.util.Iterator;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;

public class Scanner extends JPanel {

    static int X0;
    static int Y0;
    static int X1;
    static int Y1;
    static int a[]=new int [10];        //��������10��x����
    static int b[]=new int [10];        //��������10��y����
    static int index=0;
    static int time=0;    
    static int time2=0;    
    static boolean add;
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        this.addMouseListener(new MouseAdapter() {    //����������¼�     
            public void mouseExited(MouseEvent e) {   //������Ƴ�ʱ���������
                    time++;
                    repaint();    
                
            }      
            public void mouseClicked(MouseEvent e) {   //�����ִ���¼�  
            
                if(e.getButton() == e.BUTTON1) {
                    add=true;
                    if(index!=0)
                    {
                            for(int i=0;i<index;i++)
                        {
                            if(a[i]==e.getX()&&b[i]==e.getY())
                                add=false;
                        }
                    }
                    if(add)    
                        {    a[index]=e.getX(); 
                            b[index]=e.getY();  //�����������x��yֵ�洢��a��b������
                        
                        System.out.println("����Ϊ("+a[index]+","+b[index]+")");//��������������Ϣ
                        index++; //�����������һ
                        
                        //frame.setVisible(true);
                        repaint();     
                        System.out.print(time2);
                        if(time2==0)
                            time2++;
                        }
                    }
           
            }
      
           
    });

        Graphics2D g2d = (Graphics2D)g;
        int Ymax=0;
       for(int i=0;i<b.length;i++)
       {
           if(Ymax<b[i])
               Ymax=b[i];    //�ҳ����������������yֵ
       }
       // System.out.println("Ymax"+Ymax);

        /*
         * ���������
         */
       if(time2>0)
       { 
           System.out.print("��ʼ");
           int Sum=0;
             for(;Sum<=index;Sum++) {
                 if(Sum==index-1)
                    {
                         g2d.drawLine(a[Sum], b[Sum], a[0],b[0]);  //���һ�������һ���������γɶ����
                         break;
                    }
                 else     
                     { 
                     	g2d.drawLine(a[Sum], b[Sum], a[Sum+1],b[Sum+1]);  //ǰһ�������һ��������
                     }
                 
                  
             }
       }
             
       /*
        * Node�ౣ��ɨ�����Ϣʵ������ṹ�洢
        */
           if(time!=0) {
             
             Node [] head =new Node [Ymax];        //������Ӧɨ��������ȵ�����
             for(int i=0;i<index-1;i++)            
             {
                 
                 if(b[i]<b[i+1])                    //�ӵ�һ���㿪ʼ��ǰһ����С�ں�һ����
                 {
                     if(head[b[i]]==null)
                         head[b[i]]=new Node(0,0,0);
                         head[b[i]].ymin=b[i];
                     
                         if(head[b[i]].next==null)        //�õ��ǵ�һ������Ľڵ�
                             {    
                                 head[b[i]].next=new Node(0,0,0);
                                 head[b[i]].next.x=a[i];
                                 head[b[i]].next.dx=(float)(a[i]-a[i+1])/(b[i]-b[i+1]);
                                 head[b[i]].next.yMax=b[i+1];        //ymaxΪ��һ���y
                             }
                         else {                                //�õ㲻�ǵ�һ������Ľڵ�
                                     if(head[b[i]].next.next==null)
                                     head[b[i]].next.next=new Node(0,0,0);
                                     if((float)(a[i]-a[i+1])/(b[i]-b[i+1])<head[b[i]].next.dx)    //��ǰ����x��֮ǰ���ڵĽڵ�xС
                                     {
                                         head[b[i]].next.next.x=head[b[i]].next.x;
                                         head[b[i]].next.next.dx=head[b[i]].next.dx;
                                         head[b[i]].next.next.yMax=head[b[i]].next.yMax;
                                         head[b[i]].next.x=a[i];
                                         head[b[i]].next.dx=(float)(a[i]-a[i+1])/(b[i]-b[i+1]);
                                         head[b[i]].next.yMax=b[i+1];
                                     }
                                     else
                                     {
                                         head[b[i]].next.next.x=a[i];
                                         head[b[i]].next.next.dx=(float)(a[i]-a[i+1])/(b[i]-b[i+1]);
                                         head[b[i]].next.next.yMax=b[i+1];
                                     }
                             }
                 }
                 else
                 {    
                     if(head[b[i+1]]==null)
                     head[b[i+1]]=new Node(0,0,0);
                     head[b[i+1]].ymin=b[i+1];
                     if(head[b[i+1]].next==null)        //�õ��ǵ�һ������Ľڵ�
                     {    
                         head[b[i+1]].next=new Node(0,0,0);
                         head[b[i+1]].next.x=a[i+1];
                         head[b[i+1]].next.dx=(float)(a[i]-a[i+1])/(b[i]-b[i+1]);
                         head[b[i+1]].next.yMax=b[i];        //ymaxΪ��һ���y
                     }
                     else {                                //�õ㲻�ǵ�һ������Ľڵ�
                             if(head[b[i+1]].next.next==null)
                                 head[b[i+1]].next.next=new Node(0,0,0);
                             if((float)(a[i]-a[i+1])/(b[i]-b[i+1])<head[b[i+1]].next.dx)    //��ǰ����x��֮ǰ���ڵĽڵ�xС
                             {
                                 head[b[i+1]].next.next.x=head[b[i+1]].next.x;
                                 head[b[i+1]].next.next.dx=(float)head[b[i+1]].next.dx;
                                 head[b[i+1]].next.next.yMax=head[b[i+1]].next.yMax;
                                 head[b[i+1]].next.x=a[i+1];
                                 head[b[i+1]].next.dx=(float)(a[i]-a[i+1])/(b[i]-b[i+1]);
                                 head[b[i+1]].next.yMax=b[i];
                             }
                             else
                             {
                                 head[b[i+1]].next.next.x=a[i+1];
                                 head[b[i+1]].next.next.dx=(float)(a[i]-a[i+1])/(b[i]-b[i+1]);
                                 head[b[i+1]].next.next.yMax=b[i];
                             }
                     }
                 }
                 
             }
             if(index>0)
             {    if(b[0]<b[index-1])                    //�ӵ�һ���㵽���һ����
                 {
                     if(head[b[0]]==null)
                         head[b[0]]=new Node(0,0,0);
                         head[b[0]].ymin=b[0];
                     
                         if(head[b[0]].next==null)        //�õ��ǵ�һ������Ľڵ�
                             {    
                                 head[b[0]].next=new Node(0,0,0);
                                 head[b[0]].next.x=a[0];
                                 head[b[0]].next.dx=(float)(a[0]-a[index-1])/(b[0]-b[index-1]);
                                 head[b[0]].next.yMax=b[index-1];        //ymaxΪ��һ���y
                             }
                         else {                                //�õ㲻�ǵ�һ������Ľڵ�
                                 if(head[b[0]].next.next==null)
                                     head[b[0]].next.next=new Node(0,0,0);
                                     if((float)(a[0]-a[index-1])/(b[0]-b[index-1])<head[b[0]].next.dx)    //��ǰ����x��֮ǰ���ڵĽڵ�xС
                                     {
                                         head[b[0]].next.next.x=head[b[0]].next.x;
                                         head[b[0]].next.next.dx=head[b[0]].next.dx;
                                         head[b[0]].next.next.yMax=head[b[0]].next.yMax;
                                         head[b[0]].next.x=a[0];
                                         head[b[0]].next.dx=(float)(a[0]-a[index-1])/(b[0]-b[index-1]);
                                         head[b[0]].next.yMax=b[index-1];
                                     }
                                     else
                                     {
                                         head[b[0]].next.next.x=a[0];
                                         head[b[0]].next.next.dx=(float)(a[0]-a[index-1])/(b[0]-b[index-1]);
                                         head[b[0]].next.next.yMax=b[index-1];
                                     }
                             }
                 }
                 else
                 {    
                     if(head[b[index-1]]==null)
                     head[b[index-1]]=new Node(0,0,0);
                     head[b[index-1]].ymin=b[index-1];
                     if(head[b[index-1]].next==null)        //�õ��ǵ�һ������Ľڵ�
                     {    
                         head[b[index-1]].next=new Node(0,0,0);
                         head[b[index-1]].next.x=a[index-1];
                         head[b[index-1]].next.dx=(float)(a[0]-a[index-1])/(b[0]-b[index-1]);
                         head[b[index-1]].next.yMax=b[0];        //ymaxΪ��һ���y
                     }
                     else {                                //�õ㲻�ǵ�һ������Ľڵ�
                             if(head[b[index-1]].next.next==null)
                             head[b[index-1]].next.next=new Node(0,0,0);
                             if((float)(a[0]-a[index-1])/(b[0]-b[index-1])<head[b[index-1]].next.dx)    //��ǰ����x��֮ǰ���ڵĽڵ�xС
                             {
                                 head[b[index-1]].next.next.x=head[b[index-1]].next.x;
                                 head[b[index-1]].next.next.dx=head[b[index-1]].next.dx;
                                 head[b[index-1]].next.next.yMax=head[b[index-1]].next.yMax;
                                 head[b[index-1]].next.x=a[index-1];
                                 head[b[index-1]].next.dx=(float)(a[0]-a[index-1])/(b[0]-b[index-1]);
                                 head[b[index-1]].next.yMax=b[0];
                             }
                             else
                             {
                                 head[b[index-1]].next.next.x=a[index-1];
                                 head[b[index-1]].next.next.dx=(float)(a[0]-a[index-1])/(b[0]-b[index-1]);
                                 head[b[index-1]].next.next.yMax=b[0];
                             }
                     }
                 }
             }
                 
         for(int i=0;i<Ymax;i++)
             if(head[i]!=null)
                 while(head[i].next!=null)
                 {    System.out.println("�±��y"+head[i].ymin+"�±��x"+head[i].next.x+"�±��dx"+head[i].next.dx+"�±��yMax"+head[i].next.yMax);
                     if(head[i].next.next!=null)
                     {
                 
                         System.out.println("���"+"�±��y"+head[i].ymin+"�±��x"+head[i].next.next.x+"�±��dx"+head[i].next.next.dx+"�±��yMax"+head[i].next.next.yMax);
                     }
                     break;
                 }
         int YMIN=b[0];
        for(int i=0;i<b.length;i++)
        {
            if(YMIN>b[i]&&b[i]!=0)
                YMIN=b[i];
         
        }

        classAndArray [] ca=new classAndArray [Ymax];
       for(int i=YMIN;i<Ymax;i++)    
           ca[i]=new classAndArray();
       //һ����һ�����ȫװ��ca���������ӡ����
           for(int i=0;i<Ymax;i++)
             {
                   if(head[i]!=null)
                     if(head[i].next!=null)
                     {    
                         //System.out.println("�±��y"+head[i].ymin+"�±��x"+head[i].next.x+"�±��dx"+head[i].next.dx+"�±��yMax"+head[i].next.yMax);
                             for(int j=head[i].ymin;j<head[i].next.yMax;j++)
                             {
                                 
                                 ca[i+j-head[i].ymin].list.add(head[i].next.x+(int)(0.5+((j-head[i].ymin)*head[i].next.dx)));
                                 //System.out.print("ca[i+j-head[i].ymin]Ϊ"+(i+j-head[i].ymin)+"ֵΪ"+ca[i+j-head[i].ymin].list.toString());
                                 //System.out.println("YminΪ"+i+" xΪ"+(head[i].next.x+(j-head[i].ymin)*head[i].next.dx));
                             }
                         
                         
                         if(head[i].next.next!=null) //�����е����¸���Ϊ��
                         {
                     
                             for(int j=head[i].ymin;j<head[i].next.next.yMax;j++)
                             {
                                 
                                 ca[i+j-head[i].ymin].list.add(head[i].next.next.x+(int)(0.5+(j-head[i].ymin)*head[i].next.next.dx));
                                 //System.out.print("next��ca[i+j-head[i].ymin]Ϊ"+(i+j-head[i].ymin)+"ֵΪ"+ca[i+j-head[i].ymin].list.toString());
                                 //System.out.println("YminΪ"+i+" xΪ"+head[i].next.next.x+(j-head[i].ymin)*head[i].next.next.dx);
                             }
                             //System.out.println("���"+"�±��y"+head[i].ymin+"�±��x"+head[i].next.next.x+"�±��dx"+head[i].next.next.dx+"�±��yMax"+head[i].next.next.yMax);
                         }
                         
                     }
             }

            for(int i=YMIN;i<Ymax;i++)    //��ͼ�����Ϸ�����ɨ��
            {
                ca[i].listSort();  //������
              for (int j = 0; j < ca[i].list.size(); j++) {
                          if(j%2==0||(j==0))
                          {
                              g2d.drawLine(ca[i].list.get(j), i, ca[i].list.get(j+1), i);  //ÿ��ɨ���ߵ�������䣨���ߣ�
                          }
                     }
               // System.out.println(ca[i].list.toString());
           }         
     }
    }    
    
    

    
    private static void createAndShowGUI() {//������ͼ����
        JFrame frame = new JFrame();  

        frame.setLocationRelativeTo(null);
      
        frame.setLayout(null); 
        
    
        frame.setVisible(true);

        frame.setContentPane(new Scanner()); //�ô�����ʾPanel��new Scanner()

        frame.setSize(600, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void main(String[] args) throws IOException {

        createAndShowGUI();           //���ô����������ں���
    
    }
}
