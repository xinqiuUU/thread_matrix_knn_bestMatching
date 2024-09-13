package com.yc.thread.pro3_BestMatching;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
    读取 词汇表
 */
public class WordsLoader {
    // 读取path指定的词汇表
    public static List<String> load(String path){
        List<String> data = new ArrayList<>();
        Path file = Paths.get(  path );
        try(
            InputStream iis = Files.newInputStream(file);//按字节流
            Reader r = new InputStreamReader(iis);//将字节流转为字符流 按字符流
            BufferedReader br = new BufferedReader(r);//可以按行读取
        ){
            String line =null;
            while (  (line= br.readLine()) != null){
                data.add(line); //一行一个单词
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return data;
    }

}