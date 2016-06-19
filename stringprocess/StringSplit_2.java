package stringprocess;
public class StringSplit_2 {
    public static void main(String[] args) {
        String value = "192.168.128.33";
        // 注意要加\\,要不出不来,yeah
        String[] names = value.split("\\.");
        for (int i = 0; i < names.length; i++) {
            System.out.println(names[i]);
        }
    }
}