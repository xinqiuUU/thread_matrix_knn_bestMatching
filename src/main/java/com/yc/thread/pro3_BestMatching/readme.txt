目标: 开发一个单词匹配系统，给定一个单词，输出与这个单词编辑距离最短的n个词，考虑性能

技术点:
    1.实现编辑距离算法:  a)递归    b)动态规划****
    2.以多核开发方式实现距离的计算
        1个词和词库中所有的词的计算距离  找最小的

开发步骤:
    1.读取词库，将词库的词存一个集合
        List<String>  load( String path);
       a) 放在  home 目录  System.getProperty("xxx");
       b) 字符流读取  按行读取   Reader
            按行读取:  BufferedReader  readerLine();
    2.实现编辑距离算法类  ( 动态规划  )
        int   distance( word1, word2);
    3.任务调度
        XxxTask任务类   计算一个单词 与 词汇表(25w) 中单词的距离
        Xxx线程池的控制类
    4.计算结果的类   =》  编辑距离最小的单词列表
        distance
        List<String>
    5.主程序调度