package a1;

public class Hello {
    public static void main(String[] args) {
        int a = 1;
        int b = 2;
        int c = (a + b) * 3;
        int d = c / 3;

        for (int i = 0; i < 10; i++) {
            if (i % 2 == 0) {
                System.out.println("偶数");
            } else {
                System.out.println("基数");
            }
        }
    }
}
/*
    字节码如下：
        1。 初始化父类（栈的最大深度1）：
            0 aload_0  加载对象放到栈上
            1 invokespecial #1 <java/lang/Object.<init>>   init object
            4 return  返回，方法结束
        2。执行main方法（栈的最大深度2）：
             0 iconst_1   常量1放到栈上
             1 istore_1   常量1放到本地变量表
             2 iconst_2   常量2放到栈上
             3 istore_2   常量2放到本地变量表
             4 iload_1    加载本地变量表1到栈上
             5 iload_2    加载本地变量表2到栈上
             6 iadd       栈上相加，结果放在栈顶
             7 iconst_3   加载常量3到栈上
             8 imul       栈上相乘放到栈顶
             9 istore_3   栈顶元素放回本地变量表3
            10 iload_3    加载本地变量表3放到栈上，也就是c
            11 iconst_3   加载常量3到栈上
            12 idiv       相除放到栈顶
            13 istore 4   栈顶放到本地变量表4上
            15 iconst_0   加载0到栈上
            16 istore 5   0放到本地变量表5
            18 iload 5    加载0到栈上
            20 bipush 10  10压到栈上
            22 if_icmpge 57 (+35)  栈上元素进行比较，也就是现在的0和10
            25 iload 5    加载本地变量表5到栈上
            27 iconst_2   加载常量2到栈上
            28 irem       取余
            29 ifne 43 (+14)  栈顶不为0跳转
            32 getstatic #7 <java/lang/System.out>  获取静态字段的值
            35 ldc #13 <偶数>  常量池中的常量偶数入栈
            37 invokevirtual #15 <java/io/PrintStream.println>  加载公共方法println
            40 goto 51 (+11)   跳转到51，也就是iinc 5 by 1
            43 getstatic #7 <java/lang/System.out>  获取静态字段的值
            46 ldc #21 <基数>  常量池中的常量偶数入栈
            48 invokevirtual #15 <java/io/PrintStream.println> 加载公共方法println打印
            51 iinc 5 by 1   本地变量表5自加一
            54 goto 18 (-36)  跳转到18
            57 return  返回
 */
