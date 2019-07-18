package ru.whitebite;

import lombok.extern.slf4j.Slf4j;
import ru.whitebite.Core.Interpreter;
import ru.whitebite.ArgHandler.ArgHandler;

@Slf4j
public class Main {
    public static void main(String[] args) {
        String code = ArgHandler.starter(args);
        if (code.equals("error"))
            log.error("Invalid argument");
        else
            log.info(Interpreter.run(code));
    }

}


