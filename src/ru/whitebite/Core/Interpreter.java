package ru.whitebite.Core;


import lombok.extern.slf4j.Slf4j;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import static ru.whitebite.Core.Lexer.*;
import static ru.whitebite.Core.Optimizer.*;

@Slf4j
public class Interpreter {

    private static final int STACK_LENGHT = 10000;
    private static short[] arr = new short[STACK_LENGHT];
    public static String run(String strCommand) {
        StringBuilder retString = new StringBuilder();
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        List<Operation> lexems = optimize(strCommand);

        int pointer = 0;        //memory pointer
        List<Integer> queueLoop = new ArrayList<>();

        for (int i = 0; i < lexems.size(); i++) {
            Operation command = lexems.get(i);
            printOperation(command);
            try {

                switch (command.getType()) {
                    case ADD: {
                        if (command.getArg() > 0) {
                            if ((arr[pointer] + 1) > 255) {
                                arr[pointer] = 0;
                            } else {
                                arr[pointer] += command.getArg();
                            }
                        } else if ((arr[pointer] - 1) < 0) {
                            arr[pointer] = 255;
                        } else {
                            arr[pointer] += command.getArg();
                        }
                        break;
                    }
                    case SHIFT: {
                        pointer += command.getArg();
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
                        for (int k = 0; k < command.getArg(); k++) {
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
                }
            } catch (Exception ex) {
                log.error(ex.getMessage());
            }
        }
        return retString.toString();
    }
}
