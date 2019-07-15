package ru.whitebite.ArgHandler;

import lombok.extern.slf4j.Slf4j;
import ru.whitebite.Core.Interpreter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
@Slf4j
public class ArgHandler {
    public static String starter(String[] args) {

        if (args.length == 0)
            log.info(" U don`t use any args!\n" +
                    " Use -f for read from file\n" +
                    " ==============================\n " +
                    " example: -f filename.txt\n" +
                    " ==============================\n " +
                    "             OR\n" +
                    " -c for paste code here\n" +
                    " ==============================\n " +
                    " example: -c +-+-+-+++++-\n" +
                    " ==============================\n " +
                    "           Thanks!");

        else if (args.length == 2) {
            switch (args[0]) {
                case "-f": {
                    String fileName = args[1];
                    try {
                        String content = Files.lines(Paths.get(fileName)).reduce("", String::concat);
                        log.info(content);
                        log.info(Interpreter.run(content));
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        System.err.println("Ошибка считывания файла");
                        e.printStackTrace();
                    }
                    break;
                }
                case "-c": {
                    log.info(Interpreter.run(args[1]));
                }
                default:
                    return "Unknown argument!";
            }
        }

        return "error";
    }
}
