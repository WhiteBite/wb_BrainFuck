package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static Core.Lexer.lexer;
import static Core.Lexer.outer;

public class Interpreter {
    private static final int STACK_LENGHT = 30000;
    private static short[] arr = new short[STACK_LENGHT];
    private static String strCommand = "";

    public static void main(String[] args) {
        System.out.println(run(" ++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++\n" +
                " .>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.\n" +
                " ------.--------.>+.>."));
    }
    public static String run(String strCommand) {
        StringBuilder retString = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        char[] cmd_stack = strCommand.toCharArray();
        List<Operation> arrayList  =lexer(strCommand);
        outer(arrayList); //  вывод лексем
        System.out.println(strCommand);
        int cmd_pointer = 0;     //command pointer
        int pointer = 0;        //memory pointer
        ArrayList<Integer> queueLoop = new ArrayList<Integer>();

        while (cmd_pointer < cmd_stack.length) {
            switch (cmd_stack[cmd_pointer]) {
                case '+':
                    if ((arr[pointer]+1) > 255) arr[pointer] = 0;
                    else arr[pointer]++;
                    cmd_pointer++;
                    break;
                case '-':
                    if ((arr[pointer]-1) < 0) arr[pointer] = 255;
                    else arr[pointer]--;
                    cmd_pointer++;
                    break;
                case '>':
                    pointer++;
                    cmd_pointer++;
                    break;
                case '<':
                    pointer--;
                    cmd_pointer++;
                    break;
                case '.':
                    retString.append((char) arr[pointer]);
                    cmd_pointer++;
                    break;
                case ',':
                    try {
                        arr[pointer] = (short) Integer.parseInt(reader.readLine());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    cmd_pointer++;
                    break;
                case '[':
                    queueLoop.add(cmd_pointer);
                    cmd_pointer++;
                    break;
                case ']':
                    if ((arr[pointer]) > 0)
                        cmd_pointer = queueLoop.remove(queueLoop.size()-1);
                    else cmd_pointer++;
                    break;
                default:
                    cmd_pointer++;
                    break;

            }
        }
        return retString.toString();
    }
}
