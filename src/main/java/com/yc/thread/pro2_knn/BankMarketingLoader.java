package com.yc.thread.pro2_knn;

import com.yc.thread.pro2_knn.bean.BankMarketing;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

/*
    数据加载器：读取数据文件data和测试文件test
 */
public class BankMarketingLoader {

    public static List<BankMarketing> load(String path){
        List<BankMarketing> list = new ArrayList<>();
        //读取 path 位置 的 文件 并解析成  BankMarketing 对象
        //  技术 ： java.io流  File类  InputStream 二进制流
        //              =》利用 InputStreamReader  包装成Reader =》 BufferedReader (装饰模式)
        //                                                    .readLine()读一行
        //  2.采用线程安全 的 文件操作类
        // BIO ： BufferedReader  reader = new BufferedReader( new InputStreamReader( new FileInputStream( path ) ) );
        // NIO :  new FileInputStream(  path ) => InputStream lis = Files.newInputStream( path );
        // 3. 异常处理 ： try ... catch ... finally
        //   转成jdk8以后  try...resource....
        Path p =  Paths.get( path );
        try(  InputStream lis =   Files.newInputStream( p ) ;
            BufferedReader reader =  new BufferedReader(  new InputStreamReader(lis) )
        ){     //  ()中加入的是可以close()的资源( 只要一个类实现Closable接口 ),比如我们的流
            String line = null;
            while ( (line=reader.readLine()) != null ){ //一行一行读取
                String data[] = line.split(";");
                BankMarketing bank = new BankMarketing();
                bank.setData(data);
                list.add(  bank  );
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return list;
    }
}
