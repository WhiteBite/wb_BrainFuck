package Core;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public abstract class Lexer {

    private static Logger Lexical_analysis = Logger.getLogger(Lexer.class.getName());


    public static List<Operation> lexer(String code) {
        //Создаем массив лексем (которые уже являются опкодами и готовы к исполнению)
        List<Operation> retValue = new ArrayList<Operation>();
        int pos = 0;

        //Приходимся по всем символам
        while (pos < code.length()) {
            switch (code.charAt(pos++)) {
                //Как и говорилось ранее, некоторые команды эквивалентны
                case '>': retValue.add(new Operation(Operation.Type.SHIFT, +1)); break;
                case '<': retValue.add(new Operation(Operation.Type.SHIFT, -1)); break;

                case '+': retValue.add(new Operation(Operation.Type.ADD, +1)); break;
                case '-': retValue.add(new Operation(Operation.Type.ADD, -1)); break;

                case '.': retValue.add(new Operation(Operation.Type.OUT)); break;
                case ',': retValue.add(new Operation(Operation.Type.IN)); break;
                case '[':
                    char next = code.charAt(pos);

                    //проверяем, является ли это обнулением ячейки ([+] или [-])
                    if((next == '+' || next == '-') && code.charAt(pos + 1) == ']') {
                        retValue.add(new Operation(Operation.Type.ZERO));
                        pos += 2;
                    } else
                        retValue.add(new Operation(Operation.Type.BEGIN_WHILE));
                    break;
                case ']': retValue.add(new Operation(Operation.Type.END_WHILE)); break;
            }
        }
        //Lexical_analysis.info("Logger Name: "+Lexical_analysis.getName());
        Lexical_analysis.info("|The end of the analysis|");
        return retValue;
    }
    public static void outer(List<Operation> retValue){
        for (Operation x:retValue) {
            System.out.println(x.type + " " +  x.arg);
        }
    }

}