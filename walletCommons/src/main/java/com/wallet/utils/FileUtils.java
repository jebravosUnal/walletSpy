package com.wallet.utils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by EBR3556 on 04/11/2017.
 */
public class FileUtils {

    public static int MAX_LEVEL_DEEP = Integer.MAX_VALUE;
    public static int MINIMUN_LEVEL_DEEP = Integer.MAX_VALUE;


    public static void listFiles(String path) throws IOException {
        listFiles(path, MAX_LEVEL_DEEP);
    }

    public static void listFiles(String path, int maxDeep) throws IOException {
        try(Stream<Path> paths = streamFilesPath(path, maxDeep)){
            paths.forEach(System.out::println);
        }
    }

    public static Stream<Path> streamFilesPath(String path) throws IOException {
       return streamFilesPath(path, MAX_LEVEL_DEEP);
    }

    public static List<Path> getPathList(String path) throws IOException {
        return getPathList(path, MAX_LEVEL_DEEP);
    }

    public static List<Path> getPathList(String path, int maxDeep) throws IOException {
        try(Stream<Path> paths = streamFilesPath(path, maxDeep)){
                return paths.collect(Collectors.toList());
        }
    }

    public static Stream<Path> streamFilesPath(String path, int maxDeep) throws IOException {
        return Files.walk(Paths.get(path), maxDeep).filter(Files::isRegularFile);
    }

    public static void main(String[] args) throws IOException {
//        listFiles("C:/Users/ebr3556/Desktop/Esteban/Documents/Personnel/transactiones/");
//        System.out.println("-------------------");
//        try(Stream<Path> p = streamFilesPath("C:/Users/ebr3556/Desktop/Esteban/Documents/Personnel/transactiones/")){
//            p.forEach(path -> System.out.println(path.toString()));
//        }
    }
}
