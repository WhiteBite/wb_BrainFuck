package Core;

import java.util.List;
import java.util.Stack;

import static Core.Lexer.brainFuckToLex;
import static Core.Lexer.outer;

public abstract class Optimizer {
    public static List<Operation> optimize(String code) {
        return optimize(brainFuckToLex(code));
    }

    public static List<Operation> optimize(List<Operation> tokens) {
        Stack<Operation> LexStack = new Stack<Operation>();

        //Приходимся по всем командам
        for (Operation token : tokens) {
            switch (token.type){
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
                    if(LexStack.peek().type != token.type) {
                        if(LexStack.peek().arg == 0) //если в результате сжатия команда "исчезла"
                            LexStack.pop(); //то просто убираем ее

                        if(LexStack.peek().type == Operation.Type.ZERO) //если это команда ZERO
                            LexStack.peek().arg = 1; //то убираем возможные повторы, ибо они не имеют смысла

                        LexStack.push(token.clone()); //добавляем текущую команду
                        continue;
                    }

                    //сюда мы попадет при условии, если команда дальше повторяется
                    //мы просто дополняем текущую команду вместо добавления новой
                    LexStack.peek().arg += token.arg;
                    break;

                case BEGIN_WHILE:
                case END_WHILE:
                    //циклы объединять не надо
                    LexStack.add(token.clone());
                    break;
            }
        }
        outer(LexStack);
        return LexStack;
    }
}