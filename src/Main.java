import Core.Lexer;
import Core.Operation;

import java.util.List;

import static Core.Lexer.BrainFuckToLex;

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
        List<Operation> lex  = BrainFuckToLex(q);
        Lexer.outer(lex);
    }
}


