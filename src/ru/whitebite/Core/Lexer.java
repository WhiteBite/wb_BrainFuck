package ru.whitebite.Core;

import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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


        List<Operation> retValue = new ArrayList<>();
        Map<Character, Operation> activityMap = new HashMap<>();

        activityMap.put('+', new Operation(Operation.Type.ADD, 1));
        activityMap.put('-', new Operation(Operation.Type.ADD, -1));
        activityMap.put('<', new Operation(Operation.Type.SHIFT, -1));
        activityMap.put('>', new Operation(Operation.Type.SHIFT, 1));
        activityMap.put('.', new Operation(Operation.Type.OUT));
        activityMap.put(',', new Operation(Operation.Type.IN));
        activityMap.put('[', new Operation(Operation.Type.BEGIN_WHILE));
        activityMap.put(']', new Operation(Operation.Type.END_WHILE));

        int pos = 0;
        try {
            //Приходимся по всем символам
            while (pos < code.length()) {
                char c = code.charAt(pos);
                if (activityMap.containsKey(c))
                    retValue.add(activityMap.get(c));
                pos++;
            }
        } catch (Exception e) {
            log.error(e.getMessage());
            System.exit(1);
        }
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
    //TODO Здесь декомпилер
    /*public static String lexToBrainFuck(List<Operation> retValue) {
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
    }*/
}