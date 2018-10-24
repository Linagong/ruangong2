package com.company;


//实战界面初级版整数与整数型
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.*;

public class jieMian {
    float sum;//记录当前答题的答案
    int dadui=0;//记录总答对题数
    int dacuo=0;//记录总答错题数
    int tishu;//记录总题数
    int noanswer=0;//记录未答题数
    int summary=0;//记录当前的答题题号
    //设置答案的输出形式
    String answer = "0.00";
    DecimalFormat decimal = new DecimalFormat(answer);
    String str;
    //设计计时器
    static Timer timer = null;
    static SimpleDateFormat df=new SimpleDateFormat("mm:ss");//设置计时器的格式
    long startTime;//记录每题的开始时间
    long finishTime;//记录每题的结束时间
    long duration;//记录每题的答题时间
    long allDuration =0;//记录总共答题时间
    boolean backgroundNumber=true;
    private JFrame frame;
    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    jieMian window = new jieMian();
                    window.frame.setVisible(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
    public jieMian() {
        initialize();
    }
    private void initialize() {

        //设置界面的布局
        frame = new JFrame();
        frame.setTitle("简易四则运算");
        frame.setSize(850,230);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JLabel L1=new JLabel("总题数:",JLabel.RIGHT);
        JLabel L2=new JLabel("题目:",JLabel.RIGHT);
        JLabel L3=new JLabel("答案",JLabel.RIGHT);
        JLabel L4=new JLabel("评分:",JLabel.RIGHT);
        JLabel L5=new JLabel("计时:",JLabel.RIGHT);

        final JButton B1=new JButton("开始");
        final JButton B2=new JButton("下一题");
        final JButton B3=new JButton("退出");
        final JButton B4=new JButton("背景");
        final JTextField T1=new JTextField(10);
        final JTextField T2=new JTextField(10);
        final JTextField T3=new JTextField(5);
        final JTextField T4=new JTextField(20);
        final JTextField T5=new JTextField(10);
        JPanel panel=new JPanel();
        JPanel panel0=new JPanel();
        JPanel panel1=new JPanel();
        JPanel panel2=new JPanel();

        panel.add(panel0);
        panel0.add(panel2);

        panel.add(panel1);
        frame.getContentPane().add(panel,"Center");

        L1.setFont(new Font("宋体", 1, 14));
        L2.setFont(new Font("宋体", 1, 14));
        L3.setFont(new Font("宋体", 1, 14));
        L4.setFont(new Font("宋体", 1, 14));
        L5.setFont(new Font("宋体", 1, 14));
        panel.setLayout(new FlowLayout());


        panel0.setLayout(new GridLayout(1,0));

        panel2.add(L1);
        panel2.add(T1);
        panel2.add(L2);
        panel2.add(T2);
        panel2.add(L3);
        panel2.add(T3);
        panel2.add(L4);
        panel2.add(T4);
        panel2.add(L5);
        panel2.add(T5);
        panel2.setLayout(new GridLayout(5,3,5,10));


        panel1.add(B1);
        panel1.add(B2);
        panel1.add(B3);
        panel1.add(B4);
        panel1.setLayout(new GridLayout(4,1,5,10));
        //更改背景图片
        panel.setBackground(Color.white);
        panel2.setBackground(Color.white);
        panel1.setBackground(Color.white);


        B4.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (backgroundNumber==true){
                    panel.setBackground(Color.CYAN);
                    panel2.setBackground(Color.CYAN);
                    panel1.setBackground(Color.cyan);
                    backgroundNumber=false;
                }else {
                    panel.setBackground(Color.red);
                    panel2.setBackground(Color.red);
                    panel1.setBackground(Color.red);
                    backgroundNumber=true;
                }
            }
        });
        //当按退出按钮时，弹出对话框(答题总题数、答对题数、答错题数以及未答题数)，把当前窗口设置成不可见性，再返回到每天一练初级版题型选择
        B3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent arg0) {
                JOptionPane.showMessageDialog(null,
                        "答题总题数为"+tishu+",答对题数为"+dadui+"," +
                                "答错题数为"+dacuo+",未答题题数为"+noanswer+"。");
                frame.setVisible(false);

            }
        });

        //当按开始按钮时，执行以下步骤
        B1.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                //限制只能输入数字


                tishu=Integer.parseInt(T1.getText());//获取总题数
                if(tishu>5){
                    JOptionPane.showMessageDialog(new JFrame().getContentPane(),
                            "题数不能大于5",
                            "四则运算", JOptionPane.INFORMATION_MESSAGE);

                    T1.setText("");
                    tishu=Integer.parseInt(T1.getText());//获取总题数
                }
                noanswer=tishu-dadui-dacuo;//算出未答题数
                summary++;//记录当前答题题号
                //设置每题的计时，时间为120s，超时会提示时间到，弹出对话框(答题总题数、答对题数、答错题数、未答题数以及答题时间)
                timer = new Timer();
                finishTime =System.currentTimeMillis();
                startTime = finishTime;
                timer.schedule(new TimerTask()
                {
                    public void run() {
                        finishTime =System.currentTimeMillis();
                        duration= finishTime - startTime;
                        if(duration/1000==120 && summary<tishu)
                        {
                            JOptionPane.showMessageDialog(null,
                                    "答题时间已到。答题总题数为"+(dadui+dacuo)+"," +
                                            "答对题数为"+dadui+",答错题数为"+dacuo+"," +
                                            "未答题题数为"+(tishu-dacuo-dadui)+"," +
                                            "答题使用时间:"+(allDuration +duration)/1000+"秒。");
                            frame.setVisible(false);

                        }
                        String str=df.format(new Date(duration));
                        T5.setText(str);
                    }
                }, 1000, 1000);
                allDuration = allDuration +duration;//记录全程算法所用的时间
                //生成一个随机算术
                 Random random = new Random();
                int a =random.nextInt(200) - 100;
                int b=random.nextInt(200) - 100;
                int c=random.nextInt(200) - 100;
                int d=(int)(Math.random()*9)+1;
                int first=(int)(Math.random()*5);
                int second=(int)(Math.random()*4);
                String suanshi=null;

                switch (first){
                    case 0:
                        switch (second){
                            case 0:

                                sum=a+b+c;
                                suanshi=fushu(a,suanshi);
                                suanshi=suanshi+"+";
                                suanshi=suanshi+fushu(b,suanshi);
                                suanshi=suanshi+"+";
                                suanshi=suanshi+fushu(c,suanshi);
                                T2.setText(suanshi);

                                break;
                            case 1:
                                sum= a + b - c;
                                suanshi=fushu(a,suanshi);
                                suanshi=suanshi+"+";
                                suanshi=suanshi+fushu(b,suanshi);
                                suanshi=suanshi+"-";
                                suanshi=suanshi+fushu(c,suanshi);
                                T2.setText(suanshi);

                                break;
                            case 2:
                                sum= a + (b * c);
                                suanshi=fushu(a,suanshi);
                                suanshi=suanshi+"+";
                                suanshi=suanshi+fushu(b,suanshi);
                                suanshi=suanshi+"*";
                                suanshi=suanshi+fushu(c,suanshi);
                                T2.setText(suanshi);

                                break;
                            case 3:
                                sum= a + (b / c);
                                suanshi=fushu(a,suanshi);
                                suanshi=suanshi+"+";
                                suanshi=suanshi+fushu(b,suanshi);
                                suanshi=suanshi+"/";
                                suanshi=suanshi+fushu(c,suanshi);
                                T2.setText(suanshi);
                                //T2.setText(a+"+"+b+"/"+c);
                                break;
                        }
                        break;
                    case 1:
                        switch (second){
                            case 0:
                                sum= a - b + c;
                                suanshi=fushu(a,suanshi);
                                suanshi=suanshi+"-";
                                suanshi=suanshi+fushu(b,suanshi);
                                suanshi=suanshi+"+";
                                suanshi=suanshi+fushu(c,suanshi);
                                T2.setText(suanshi);
                                //T2.setText(a+"-"+b+"+"+c);
                                break;
                            case 1:
                                sum= a - b * c;
                                suanshi=fushu(a,suanshi);
                                suanshi=suanshi+"-";
                                suanshi=suanshi+fushu(b,suanshi);
                                suanshi=suanshi+"/";
                                suanshi=suanshi+fushu(c,suanshi);
                                T2.setText(suanshi);
                                //T2.setText(a+"-"+b+"*"+c);
                                break;
                            case 2:
                                sum= a - (b / c);
                                suanshi=fushu(a,suanshi);
                                suanshi=suanshi+"-";
                                suanshi=suanshi+fushu(b,suanshi);
                                suanshi=suanshi+"/";
                                suanshi=suanshi+fushu(c,suanshi);
                                T2.setText(suanshi);
                                //T2.setText(a+"-"+b+"/"+c);
                                break;
                            case 3:
                                sum= a - b - c;
                                suanshi=fushu(a,suanshi);
                                suanshi=suanshi+"-";
                                suanshi=suanshi+fushu(b,suanshi);
                                suanshi=suanshi+"-";
                                suanshi=suanshi+fushu(c,suanshi);
                                T2.setText(suanshi);
                                //T2.setText(a+"-"+b+"-"+c);
                                break;
                        }
                        break;
                     case 2:
                         switch (second){
                             case 0:
                                 sum= a * b + c;
                                 suanshi=fushu(a,suanshi);
                                 suanshi=suanshi+"*";
                                 suanshi=suanshi+fushu(b,suanshi);
                                 suanshi=suanshi+"+";
                                 suanshi=suanshi+fushu(c,suanshi);
                                 T2.setText(suanshi);
                                 //T2.setText(a+"*"+b+"+"+c);
                                 break;
                             case 1:
                                 sum= a * b - c;
                                 suanshi=fushu(a,suanshi);
                                 suanshi=suanshi+"*";
                                 suanshi=suanshi+fushu(b,suanshi);
                                 suanshi=suanshi+"-";
                                 suanshi=suanshi+fushu(c,suanshi);
                                 T2.setText(suanshi);
                                 break;

                             case 2:
                                 sum= a * b * c;
                                 suanshi=fushu(a,suanshi);
                                 suanshi=suanshi+"*";
                                 suanshi=suanshi+fushu(b,suanshi);
                                 suanshi=suanshi+"*";
                                 suanshi=suanshi+fushu(c,suanshi);
                                 T2.setText(suanshi);
                                 break;
                             case 3:
                                 sum= a * b / c;
                                 suanshi=fushu(a,suanshi);
                                 suanshi=suanshi+"*";
                                 suanshi=suanshi+fushu(b,suanshi);
                                 suanshi=suanshi+"/";
                                 suanshi=suanshi+fushu(c,suanshi);
                                 T2.setText(suanshi);
                                 break;
                         }
                        break;
                     case 3:
                         switch (second){
                             case 0:
                                 sum= a / b + c;
                                 suanshi=fushu(a,suanshi);
                                 suanshi=suanshi+"/";
                                 suanshi=suanshi+fushu(b,suanshi);
                                 suanshi=suanshi+"+";
                                 suanshi=suanshi+fushu(c,suanshi);
                                 T2.setText(suanshi);
                                 break;
                             case 1:
                                 sum= a / b - c;
                                 suanshi=fushu(a,suanshi);
                                 suanshi=suanshi+"/";
                                 suanshi=suanshi+fushu(b,suanshi);
                                 suanshi=suanshi+"-";
                                 suanshi=suanshi+fushu(c,suanshi);
                                 T2.setText(suanshi);
                                 break;
                             case 2:
                                 sum= a / b * c;
                                 suanshi=fushu(a,suanshi);
                                 suanshi=suanshi+"/";
                                 suanshi=suanshi+fushu(b,suanshi);
                                 suanshi=suanshi+"*";
                                 suanshi=suanshi+fushu(c,suanshi);
                                 T2.setText(suanshi);
                                 break;
                             case 3:
                                 sum= a / b / c;
                                 suanshi=fushu(a,suanshi);
                                 suanshi=suanshi+"/";
                                 suanshi=suanshi+fushu(b,suanshi);
                                 suanshi=suanshi+"/";
                                 suanshi=suanshi+fushu(c,suanshi);
                                 T2.setText(suanshi);
                                 break;
                         }
                        break;
                    case 4:
                        sum=1;
                        T2.setText(d+"!");
                        for (int i = 1; i<=d;i++){
                            sum=sum*i;
                        }
                        break;
                }

            }
        });
        //监听答题者的答案是否与正确答案一致，并作出相应的判断
        T3.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                str= decimal.format(sum);
                if(Float.parseFloat(str)==Float.parseFloat(T3.getText()))
                {
                    T4.setText("恭喜你！答对了！");
                    dadui++;
                }
                else
                {
                    T4.setText("真遗憾！答错了！正确答案是"+Float.parseFloat(str));
                    dacuo++;
                }
                noanswer=tishu-dacuo-dadui;//及时更新未答题题数
            }
        });
        //当按下一题按钮时，先判断是否到最后一题，是则弹出对话框，否则清空各框的内容
        B2.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e) {
                if(summary==tishu)
                {
                    JOptionPane.showMessageDialog(null, "答题已完成。答题总题数为"+tishu+",答对题数为"+dadui+",答错题数为"+dacuo+",未答题题数为"+noanswer+",答题使用时间:"+ allDuration /1000+"秒。");
                    frame.setVisible(false);

                }
                else
                {
                    T2.setText(null);
                    T3.setText(null);
                    T4.setText(null);
                }
            }
        });
        //设置窗口的位置
        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        int w1 = screenSize.width;
        int h = screenSize.height;
        int x1 = w1/3;
        int y1 = h/5;
        frame.setSize(800, 250);
        frame.setLocation(x1,y1);
        frame.setVisible(true);//设置窗口的可见性
        frame.setLocationRelativeTo(frame.getOwner());//JFrame打开后窗体居中
        frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);//界面关闭后程序的内存没有清空
        frame.setResizable(true);//设置窗口大小的不可变性


    }
    private static String fushu(int a,String suanshi) {

        if (a<0) {
            suanshi ="(" + a + ")";
        }
        else {
            suanshi = "" + a;
        }
        return suanshi;
    }


}
