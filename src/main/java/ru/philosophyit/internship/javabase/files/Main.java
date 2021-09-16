package ru.philosophyit.internship.javabase.files;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class Main {
    public static void main(String ...args) throws IOException {
        System.out.println(Files.readString(Path.of("src/main/resources/hello.txt")));
        FileTree("src/main/");
        // Отобразите рекурсивно дерево директорий src/main/java/ru/philosophyit/internship/javabase
        // например 
        // src/main/java/ru/philosophyit/internship/javabase/files/Main.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/DeadLock.java
        // src/main/java/ru/philosophyit/internship/javabase/locks/LiveLock.java
        // src/main/java/ru/philosophyit/internship/javabase/threads/Completable.java
        // и т.д.
        /// Более удачные оформления вывода в консоль приветствуются
    }

    public static void FileTree(String path){
        try {
            File file = new File(path);
            File[] listFile = file.listFiles();

            for (File f :
                    listFile) {
                if (f.isDirectory())
                    FileTree(f.toString());
                else
                    System.out.println(f);
            }
        }catch (NullPointerException e){
            System.out.println("Проверьте рпвильность ввода пути.");
        }
    }
}
