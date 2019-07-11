package Core;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static Core.Lexer.printOperation;

public class Interpreter {

  private static final int STACK_LENGHT = 10000;
  private static short[] arr = new short[STACK_LENGHT];
  private static String strCommand = "";

  public static void main(String[] args) {
    System.out.println(run("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++\n" +
        ".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.\n" +
        "------.--------.>+.>."));
  }

  public static String run(String strCommand) {
    StringBuilder retString = new StringBuilder();
    BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    List<Operation> Lexems = Optimizer.optimize(strCommand);

    int pointer = 0;        //memory pointer
    List<Integer> queueLoop = new ArrayList<Integer>();

    //char[] cmd_stack = strCommand.toCharArray();
    for (int i = 0; i < Lexems.size(); i++) {
      System.out.print("NOW: ");
      Operation command = Lexems.get(i);
      printOperation(command);

      switch (command.type) {
        case ADD: {
          if (command.arg > 0) {
            if ((arr[pointer] + 1) > 255) {
              arr[pointer] = 0;
            } else {
              arr[pointer] += command.arg;
            }
          } else if ((arr[pointer] - 1) < 0) {
            arr[pointer] = 255;
          } else {
            arr[pointer] += command.arg;
          }
          break;
        }
        case SHIFT: {
          pointer += command.arg;
          break;
        }
        case IN: {
          try {
            arr[pointer] = (short) Integer.parseInt(reader.readLine());
          } catch (IOException e) {
            e.printStackTrace();
          }
          break;
        }
        case OUT: {
          for(int k =0;k<command.arg;k++){
          retString.append((char) arr[pointer]);
          }
          break;
        }
        case ZERO: {
          arr[pointer] = 0;
          break;
        }
        case BEGIN_WHILE: {
          queueLoop.add(i);
          break;
        }
        case END_WHILE: {
          if (arr[pointer] > 0) {
            i = queueLoop.remove(queueLoop.size() - 1);
            i--; // из-за прибавления при итерации цикла нужно вычесть
          }
          break;
        }
        default:
          break;

      }
    }
    return retString.toString();
  }
}
