package ru.whitebite;

import lombok.extern.slf4j.Slf4j;
import ru.whitebite.Core.Interpreter;
import ru.whitebite.ArgHandler.ArgHandler;

import java.util.function.Consumer;

@Slf4j
public class Main {
    public static void main(String[] args) {
        log.info("qqqqq");
        log.info("q");
        log.info("q2");
        log.info("q3");
        log.info("q41");
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


