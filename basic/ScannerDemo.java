package basic;

import java.util.Scanner;

public class ScannerDemo {
        public static void main(String[] args) {
                String line = null;
                Scanner sc = new Scanner(System.in);
                //ֻҪ������һ��
                while (sc.hasNext()) {
                        //��ȡɨ��������һ���������
                        line = sc.next();
                        //�жϽ������
                        if ("over".equals(line))
                                break; 
                        System.out.println(line); 
                }
                sc.close();
        }
}