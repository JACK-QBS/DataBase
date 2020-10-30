package exercises;

public class DataTest {
    public static void main(String[] args) {
        // 传入 long 类型的数字，表示从 1970 开始，经历 10000 毫秒之后的时间
        java.util.Date t1 = new java.util.Date(10000);
        java.sql.Date t2 = new java.sql.Date(10000);

        // 无参构造方法：表示代码执行到这时的 当前时间
        java.util.Date t3 = new java.util.Date();

    }
}
