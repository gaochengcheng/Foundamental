package basic;

import java.util.Scanner;

public class Read{
 public static void main(String args[]) throws Exception{
  //�鿴jdk,��java.util.Scanner���棬�����о��彲��
  Scanner stdin = new Scanner(System.in);  
  System.out.println("����������û���:");
  //String nextLine()����:��ɨ����ִ�е�ǰ�У�������������������Ϣ
  String username = stdin.nextLine();
  System.out.println("���������:"+username) ;
  System.out.println("��������ĳɼ�:");
  //double nextDouble()����:��������Ϣ����һ�����ɨ��Ϊһ�� double
  double score = stdin.nextDouble();
  System.out.println("��ĳɼ���:"+score) ;
 }
 }