import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        int a,b,c,first,second,howMuch = 0,key = 0,answer = 0,N = 30;
        int rigth = 0;
        String start ;
        String go = "yes";

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        System.out.println("欢迎使用小学生四则运算练习软件！！！！！");
        System.out.println("是否开始回答问题？（yes/no）");

        start = sc.nextLine();

        if(start.equals(go)==false){
            System.exit(0);
        }

        System.out.println("想要输出多少道题？");

        N = sc.nextInt();

        do {

            a = random.nextInt(100) + 1;
            b = random.nextInt(100) + 1;
            c = random.nextInt(100) + 1;
            first = random.nextInt(4) + 1;
            second = random.nextInt(4) + 1;

            System.out.printf("第%d题:",howMuch+1);

            switch (first) {
                case 1:
                    switch (second) {
                        case 1:
                            answer = a + b + c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d+%d+%d",a, b, c);

                            break;
                        case 2:
                            answer = a + b - c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d+%d-%d",a, b, c);
                            break;
                        case 3:
                            answer = a + b * c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d+%d*%d",a, b, c);
                            break;
                        case 4:
                            answer = a + b / c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d+%d/%d",a, b, c);
                            break;
                    }
                    howMuch++;
                    break;
                case 2:

                    switch (second) {
                        case 1:
                            answer = a - b + c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d-%d+%d",a, b, c);
                            break;
                        case 2:
                            answer = a - b - c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d-%d-%d",a, b, c);
                            break;
                        case 3:
                            answer = a - b * c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d-%d*%d",a, b, c);
                            break;
                        case 4:
                            answer = a - b / c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d-%d/%d",a, b, c);
                            break;
                    }
                    howMuch++;
                    break;
                case 3:

                    switch (second) {
                        case 1:
                            answer = a * b + c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d*%d+%d",a, b, c);
                            break;
                        case 2:
                            answer = a * b - c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d*%d-%d",a, b, c);
                            break;
                        case 3:
                            answer = a * b * c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d*%d*%d",a, b, c);
                            break;
                        case 4:
                            answer = a * b / c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d*%d/%d",a, b, c);
                            break;
                    }
                    howMuch++;
                    break;
                case 4:

                    switch (second) {
                        case 1:
                            answer = a / b + c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d/%d+%d",a, b, c);
                            break;
                        case 2:
                            answer = a / b - c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d/%d-%d",a, b, c);
                            break;
                        case 3:
                            answer = a / b * c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d/%d*%d",a, b, c);

                            break;
                        case 4:
                            answer = a / b / c;
                            if (answer<0){
                                continue;
                            }
                            System.out.printf("%d/%d/%d",a, b, c);

                            break;
                    }
                    howMuch++;
                    break;
            }

            System.out.printf("=");

            key = sc.nextInt();

            //System.out.println(key);
            //System.out.println(answer);

            if (key==answer){
                System.out.println("回答正确！！!");
                rigth++;
            }else if (key!=answer){
                System.out.println("回答错误！！！");
                System.out.printf("正确答案为：%d\n",answer);
            }


        }while (howMuch<N);
        if (howMuch==N){
            rigth=rigth*100/N;
            System.out.printf("答题结束。。。得分为%d\n",rigth);
        }
        System.exit(0);
    }

}
