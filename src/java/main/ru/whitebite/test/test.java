package test;

import java.io.IOException;

public class test {
    public void test() throws IOException { //исключение от read()
        char[] arr = new char[30000];
        int i = 15000;

        i += 1111;

        arr[i] += 2222;

        arr[i] = 0;

        System.out.print(arr[i]);

        arr[i] = (char) System.in.read();
    }
}
