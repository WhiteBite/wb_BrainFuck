package ru.whitebite.ArgHandler;

import lombok.extern.slf4j.Slf4j;

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
                case "-c":
                    return args[1];

                case "-f":
                    String fileName = args[1];
                    try {
                        String content = Files.lines(Paths.get(fileName)).reduce("", String::concat);
                        log.info(content);
                        return content;
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        log.error("Ошибка считывания файла");
                        log.error("Ops! ", e);
                    }
                    break;
                default:
                    return "Unknown argument!";
            }
        }
        return "error";
    }
}
