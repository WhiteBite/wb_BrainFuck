import Core.Lexer;
import Core.Operation;

import java.util.List;

import static Core.Lexer.lexer;

public class Main {

    public static void main(String[] args) {
        String q = "+++++++++++++++++++++++++++++++++++++++++++++\n" +
                " +++++++++++++++++++++++++++.+++++++++++++++++\n" +
                " ++++++++++++.+++++++..+++.-------------------\n" +
                " ---------------------------------------------\n" +
                " ---------------.+++++++++++++++++++++++++++++\n" +
                " ++++++++++++++++++++++++++.++++++++++++++++++\n" +
                " ++++++.+++.------.--------.------------------\n" +
                " ---------------------------------------------\n" +
                " ----.-----------------------.";
        System.out.println("Starting main");
        List<Operation> lex  =lexer(q);
        Lexer.outer(lex);
    }
}


