package ru.whitebite.Core;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.Callable;
import java.util.function.Consumer;
import java.util.function.Supplier;
// 0) Maven or Gradle
// 1) CodeStyle (Google)
// 2) Lombok (Annotations -> getters/setters etc.)
// 3) SonarLint plugin
// 4) Java8+ (lambda (functional interfaces), streams)

// Optional:
// 1) DI -> Spring
@Slf4j
public abstract class Lexer {


    public static List<Operation> brainFuckToLex(String code) {

        //Создаем массив лексем (которые уже являются опкодами и готовы к исполнению)
        List<Operation> retValue = new ArrayList<>();
        int pos = 0;
//        commands.put("get", () -> "some value");


        List<Operation> retValue2 = new ArrayList<>();

        Map<Character, > activityMap = new HashMap<>();

        activityMap.put('+', (state) -> {
            retValue2.add(new Operation(Operation.Type.ADD, +1));
        });
        activityMap.put('-', (state) -> {
            retValue2.add(new Operation(Operation.Type.ADD, -1));
        });
        activityMap.put('>', (state) -> {
            retValue2.add(new Operation(Operation.Type.SHIFT, +1));
        });
        activityMap.put('<', (state) -> {
            retValue2.add(new Operation(Operation.Type.SHIFT, -1));
        });
        activityMap.put('.', (state) -> {
            retValue2.add(new Operation(Operation.Type.OUT));
        });


        try {
            //Приходимся по всем символам
            while (pos < code.length()) {
                char c = code.charAt(pos);

                // Callable<String> q = activityMap.get(String.valueOf(c));

                activityMap.getOrDefault(c, (state) -> {}).    accept(pos);
              //  activityMap.get(c);


                switch (c) {
                    //Как и говорилось ранее, некоторые команды эквивалентны
                    case '>':
                        retValue.add(new Operation(Operation.Type.SHIFT, +1, pos));
                        break;
                    case '<':
                        retValue.add(new Operation(Operation.Type.SHIFT, -1, pos));
                        break;

                    case '+':

                        retValue.add(new Operation(Operation.Type.ADD, +1, pos));
                        break;
                    case '-':
                        retValue.add(new Operation(Operation.Type.ADD, -1, pos));
                        break;
                    case '.':
                        retValue.add(new Operation(Operation.Type.OUT));
                        break;
                    case ',':
                        retValue.add(new Operation(Operation.Type.IN));
                        break;
                    case '[':
                        char next = code.charAt(pos);

                        //проверяем, является ли это обнулением ячейки ([+] или [-])
                        if ((next == '+' || next == '-') && code.charAt(pos + 1) == ']') {
                            retValue.add(new Operation(Operation.Type.ZERO));
                            pos += 2;
                        } else
                            retValue.add(new Operation(Operation.Type.BEGIN_WHILE));
                        break;
                    case ']': {
                        retValue.add(new Operation(Operation.Type.END_WHILE));
                        break;
                    }
                    case ' ': //случай с пустым символом
                        break;
                    default:
                        throw new NullPointerException("Invalid symbols");

                }
                pos++;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(1);
        }
        System.out.println(activityMap);
        log.info("|The end of the analysis|");
        return retValue;
    }

    public static void printOperation(Operation x) {
        log.info(x.getPosition() + " " + x.getType() + ":" + x.getArg());
    }

    public static void outer(List<Operation> retValue) {
        for (Operation x : retValue) {
            printOperation(x);
        }
    }

    public static String lexToBrainFuck(List<Operation> retValue) {
        StringBuilder bfCode = new StringBuilder();
        for (Operation x : retValue) {
            switch (x.getType()) {
                case SHIFT: {
                    if (x.getArg() > 0)
                        bfCode.append('>');
                    else
                        bfCode.append('<');
                    break;
                }
                case ADD: {
                    if (x.getArg() > 0)
                        bfCode.append('+');
                    else
                        bfCode.append('-');
                    break;
                }
                case IN: {
                    bfCode.append(',');
                    break;
                }
                case OUT: {
                    bfCode.append('.');
                    break;
                }
                case ZERO: {
                    bfCode.append("[-]");
                    break;
                }
                case BEGIN_WHILE: {
                    bfCode.append('[');
                    break;
                }
                case END_WHILE: {
                    bfCode.append(']');
                    break;
                }
            }
        }
        return bfCode.toString();
    }
}