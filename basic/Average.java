package basic;

import java.util.Scanner;

public class Average {
	public static void main(String[] args)
	{
		double sum = 0.0;
		int cnt = 0;
		double temp;
		Scanner stdin = new Scanner(System.in);
		while(stdin.hasNext()){
			if("over".equals(stdin.next()))
				break;
			sum += stdin.nextDouble();
//			System.out.println(sum);
			cnt++;
//			System.out.println(StdIn.isEmpty());
		}	
//		stdin.close();
		double avg = sum / cnt;
		System.out.println(avg);
	}
}