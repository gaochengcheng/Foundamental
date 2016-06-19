package basic;

import java.util.Scanner;

public class ScannerDemo {
        public static void main(String[] args) {
                String line = null;
                Scanner sc = new Scanner(System.in);
                //只要还有下一个
                while (sc.hasNext()) {
                        //获取扫描器的下一个完整标记
                        line = sc.next();
                        //判断结束标记
                        if ("over".equals(line))
                                break; 
                        System.out.println(line); 
                }
                sc.close();
        }
}