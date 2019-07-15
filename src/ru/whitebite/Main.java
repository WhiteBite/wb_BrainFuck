package ru.whitebite;

import lombok.extern.slf4j.Slf4j;
import ru.whitebite.Core.Interpreter;
import ru.whitebite.ArgHandler.ArgHandler;

@Slf4j
public class Main {
    public static void main(String[] args) {
        String code = ArgHandler.starter(args);
        if (code != "error")
            log.info(Interpreter.run(code));
        else
            log.error("Invalid argument");

//		log.info(Interpreter.run("++++++++++[>+++++++>++++++++++>+++>+<<<<-]>++\n" +
//				".>+.+++++++..+++.>++.<<+++++++++++++++.>.+++.\n" +
//				"------.--------.>+.>."));
    }

}


