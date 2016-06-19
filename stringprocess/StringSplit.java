package stringprocess;
public class StringSplit {
    public static void main(String[] args) {
        String sourceStr = "2,1,2,3,4,5";
        String[] sourceStrArray = sourceStr.split(",");
        for (int i = 0; i < sourceStrArray.length; i++) {
            System.out.println(sourceStrArray[i]);
        }
        System.out.println("---------");
        for(String str: sourceStrArray){
        	System.out.println(str);
        }

        // 最多分割出3个字符串
        int maxSplit = 3;
        sourceStrArray = sourceStr.split(",", maxSplit);
        for (int i = 0; i < sourceStrArray.length; i++) {
            System.out.println(sourceStrArray[i]);
        }
    }
}