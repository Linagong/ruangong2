package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {

    public static void main(String[] args) {



        //创建窗口
        JFrame frameLogin=new JFrame();
        frameLogin.setTitle("四则运算");
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frameLogin.setLocationRelativeTo(frameLogin.getOwner());//JFrame打开后窗体居中
        frameLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//界面关闭后程序的内存没有清空
        frameLogin.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frameLogin.setVisible(true);

        //设置窗口的大小和位置
        frameLogin.setSize(400,300);
        frameLogin.setLocation(200,200);

        Container con=frameLogin.getContentPane();//生成一个容器
        con.setLayout(new GridLayout(7,2));

        JPanel pan1 =new JPanel();
        JLabel title=new JLabel("欢迎登陆四则运算答题系统");
        title.setFont(new Font("宋体",Font.BOLD, 20));
        pan1.add(title);
        con.add(pan1);
        //生成一个新的版
        JPanel pan2 = new JPanel();
        JPanel pan3 = new JPanel();
        JPanel pan4 = new JPanel();
        JPanel pan5 = new JPanel();

        con.add(pan2);
        con.add(pan3);
        con.add(pan4);
        con.add(pan5);

        JLabel name=new JLabel("用户名",JLabel.RIGHT);
        pan2.add(name);
        TextField tf_name=new TextField(20);
        pan2.add(tf_name);

        JLabel pass = new JLabel("密   码",JLabel.RIGHT);
        pan3.add(pass);
        TextField password=new TextField(20);
        password.setEchoChar('*');
        pan3.add(password);

        JButton b_log=new JButton("登陆");
        b_log.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                //获取用户名和密码，进行校验
                String myUsername=tf_name.getText();
                String myPassword=password.getText();
                if(myUsername.equals("root")&& myPassword.equals("123456")){
                    JOptionPane.showMessageDialog(null, "登陆成功!");
                    frameLogin.dispose();
                    jieMian CK=new jieMian();

                }
                else{
                    JOptionPane.showMessageDialog(null, "账号或密码错误!");
                    name.setText( "");
                    password.setText( "");
                }

            }
        });
        pan4.add(b_log);

        JButton b_exit=new JButton("退出");
        b_exit.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // TODO Auto-generated method stub
                JOptionPane.showMessageDialog(null, "谢谢使用！");
                frameLogin.setVisible(false);//隐藏窗体
                System.exit(0);//退出程序
            }
        });
        pan4.add(b_exit);
        //登陆和退出这两个按钮放在第四个版面上


    }
}





