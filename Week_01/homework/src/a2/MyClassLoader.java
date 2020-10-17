package a2;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MyClassLoader extends ClassLoader {

    public static void main(String[] args) throws ClassNotFoundException,
            IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException {
        // 反射内容
        Class<?> hello = new MyClassLoader().findClass("Hello");
        Object newInstance = hello.getDeclaredConstructor().newInstance();
        Method hello1 = hello.getMethod("hello");
        hello1.invoke(newInstance);

    }

    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException {
        // 读取xlass字节，然后使用255减去进行还原，文件经过255减处理过
        String path = MyClassLoader.class.getResource("Hello.xlass").getPath();
        File file = new File(path);
        byte[] bytes = new byte[(int) file.length()];
        try {
            new FileInputStream(path).read(bytes);
        } catch (IOException e) {
            e.printStackTrace();
            return super.findClass(name);
        }
        for (int i = 0; i < bytes.length; i++) {
            bytes[i] = (byte) ((byte)255 - bytes[i]);
        }
        return defineClass(name, bytes, 0, bytes.length);
    }
}
