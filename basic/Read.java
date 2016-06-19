package basic;

import java.util.Scanner;

public class Read{
 public static void main(String args[]) throws Exception{
  //查看jdk,在java.util.Scanner下面，里面有具体讲解
  Scanner stdin = new Scanner(System.in);  
  System.out.println("请输入你的用户名:");
  //String nextLine()方法:此扫描器执行当前行，并返回跳过的输入信息
  String username = stdin.nextLine();
  System.out.println("你的名字是:"+username) ;
  System.out.println("请输入你的成绩:");
  //double nextDouble()方法:将输入信息的下一个标记扫描为一个 double
  double score = stdin.nextDouble();
  System.out.println("你的成绩是:"+score) ;
 }
 }