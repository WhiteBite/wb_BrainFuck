package ru.whitebite.Core;

import java.util.List;
import java.util.Stack;

import static ru.whitebite.Core.Lexer.brainFuckToLex;
import static ru.whitebite.Core.Lexer.outer;

public abstract class Optimizer {
    public static List<Operation> optimize(String code) {
        return optimize(brainFuckToLex(code));
    }

    public static List<Operation> optimize(List<Operation> tokens) {
        Stack<Operation> LexStack = new Stack<>();

        //Приходимся по всем командам
        for (Operation token : tokens) {
            switch (token.getType()){
                case SHIFT:
                case ADD:
                case OUT:
                case IN:
                case ZERO:
                    //Если это первая итерация, добавляем элемент и переходим к следующему
                    if(LexStack.size() == 0) {
                        LexStack.push(token.clone());
                        continue;
                    }

                    //Если последняя команда не совпадает с текущей, значит мы закончили сжатие
                    if(LexStack.peek().getType() != token.getType()) {
                        if(LexStack.peek().getArg() == 0) //если в результате сжатия команда "исчезла"
                            LexStack.pop(); //то просто убираем ее

                        if(LexStack.peek().getType() == Operation.Type.ZERO) //если это команда ZERO
                            LexStack.peek().setArg(1) ; //то убираем возможные повторы, ибо они не имеют смысла

                        LexStack.push(token.clone()); //добавляем текущую команду
                        continue;
                    }

                    //сюда мы попадет при условии, если команда дальше повторяется
                    //мы просто дополняем текущую команду вместо добавления новой
                    LexStack.peek().incArg(token.getArg());
                    break;

                case BEGIN_WHILE:
                case END_WHILE:
                    //циклы объединять не надо
                    LexStack.add(token.clone());
                    break;
            }
        }
        //outer(LexStack);
        return LexStack;
    }
}