/**
 * @Author 想去外太空的
 * @Date 2023/6/3 14:56
 * @Version 1.0 （版本号）
 */
public class test {

    public static void main(String[] args) {
        String str="1,2,3,4,5";
        String[] split = str.split(",");
        for (String s : split) {
            System.out.println(s);
        }
    }
}
