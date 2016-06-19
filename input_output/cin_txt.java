package input_output;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;

public class cin_txt {
	static void main(String args[]) {
		try { // 防止文件建立或读取失败，用catch捕捉错误并打印，也可以throw

			/* 读入TXT文件 */
			// 绝对路径或相对路径都可以，这里是绝对路径，写入文件时演示相对路径
			String pathname = "D:\\twitter\\13_9_6\\dataset\\en\\input.txt";
			// 要读取以上路径的input。txt文件
			File filename = new File(pathname);
			// 建立一个输入流对象reader
			InputStreamReader reader = new InputStreamReader(new FileInputStream(filename));
			// 建立一个对象，它把文件内容转成计算机能读懂的语言
			BufferedReader br = new BufferedReader(reader);
			String line = "";
			line = br.readLine();
			while (line != null) {
				// 一次读入一行数据
				line = br.readLine();
			}

			/* 写入Txt文件 */
			// 相对路径，如果没有则要建立一个新的output。txt文件
			File writename = new File(".\\result\\en\\output.txt");
			// 创建新文件
			writename.createNewFile();
			BufferedWriter out = new BufferedWriter(new FileWriter(writename));
			// \r\n即为换行
			out.write("我会写入文件啦\r\n");
			out.flush(); // 把缓存区内容压入文件
			out.close(); // 最后记得关闭文件

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}