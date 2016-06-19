package regularexp;
import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by Spicywolf on 2016/5/3.
 */
public class Utils {
    public static void readFile(String path) throws IOException{
        FileInputStream fis = new FileInputStream(path);
        InputStreamReader isr = new InputStreamReader(fis, "ISO-8859-1");
        BufferedReader br = new BufferedReader(isr);

        String s = "";
        //新建文件，读入数据
        int index = 1;
        PrintWriter out = new PrintWriter("D:/author.txt");

        int i = 0;
        while((s=br.readLine()) != null){
            //System.out.println(s);

            //读取一行，正则表达式匹配字符串
            String pattern_art = "^<article.*";
            String pattern_end = "^</article.*";

            String pattern_author = "(?<=<author>).*(?=</author>)";

            Pattern pattern_article = Pattern.compile(pattern_art);
            Pattern pattern_aut = Pattern.compile(pattern_author);
            Pattern pattern_en = Pattern.compile(pattern_end);

            Matcher matcher_A = pattern_article.matcher(s);
            Matcher matcher_Au;
            Matcher matcher_end;

            StringBuilder stringBuilder = new StringBuilder();

            //若匹配到一个article，表示找到新文献，则摘取作者
            if (matcher_A.find()){
                //stringBuilder.append(index);

                while((s=br.readLine()) != null){
                    matcher_Au = pattern_aut.matcher(s);
                    matcher_end = pattern_en.matcher(s);
                    if(matcher_Au.find()){
                        stringBuilder.append("," + matcher_Au.group());
                    }

                    if (matcher_end.find()){
                        if(stringBuilder.length() > 0){
                            stringBuilder.deleteCharAt(0);
                            //stringBuilder.deleteCharAt(1);
                            stringBuilder.append("\n");

                        }
                        out.write(stringBuilder.toString());
                        System.out.print(stringBuilder.toString());
                        index++;
                        break;
                    }
                }
            }

        }

    }

    public static void main(String[] args){
        String path = "E:/dblp.xml";
        try{
            Utils.readFile(path);
        }catch (IOException e){
            e.printStackTrace();
        }

    }
}
