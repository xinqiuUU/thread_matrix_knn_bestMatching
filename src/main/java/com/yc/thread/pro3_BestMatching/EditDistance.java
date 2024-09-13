package com.yc.thread.pro3_BestMatching;

public class EditDistance {
    /*
        计算从word1变为word2要编辑几次  =>  编辑距离
     */
    public static int calculate( String word1 , String word2 ){
        //先去掉最后几个相等的字母
//        String [] words = trimEqualSuffix(word1,word2);
//        word1 = words[0];
//        word2 = words[1];
        //初始化一个矩阵 以保存两个单词之间的距离
        int[][] distances = new int[word1.length()+1][word2.length()+1];
        //初始化  第一列 即""-> 第一个单词所需的最少编辑次数
        for (int i=1;i<=word1.length();i++){
            distances[i][0] = i;
        }
        //初始化第一行
        for (int j=1;j<=word2.length();j++){
            distances[0][j] = j;
        }
        for (int i=1; i<=word1.length();i++){
            for (int j=1;j<=word2.length();j++){
                //如果字符串中的第 i 个字符和第 j 个字符不相等，将编辑距离的值加1：否则，保存不变
                if (word1.charAt(i-1) == word2.charAt(j-1)){
                    //相等，所以将左上角的值设为 [i][j]的值
                    distances[i][j] = distances[i-1][j-1];
                }else {
                    //不相等，则从[i][j]位置左、左上、上三个位置的值中取最小值，这个最小值加1，填充到[i][j]位置
                    //[i-1][j] 上边代表删除   [i][j-1]左边代表插入字符  [i-1][j-1]左上边代表替换字符
                    distances[i][j] = minimum( distances[i-1][j] ,  distances[i][j-1] ,  distances[i-1][j-1]  ) +  1;
                }
            }
        }
        //矩阵右下角的就是计算结果
        return distances[word1.length()][word2.length()];
    }

    //取三个中的最小值
    private static int minimum(int i, int i1, int i2) {
        return Math.min(i,Math.min(i1,i2));
    }

    // 去掉两个字符串末尾相同的字符
    // 去掉两个字符串末尾相同的字符
    private static String[] trimEqualSuffix(String s, String p) {
        int minLength = Math.min(s.length(), p.length());
        int i = s.length() - 1;
        int j = p.length() - 1;

        // 从末尾开始找不同字符，最多执行minLength次
        while (minLength > 0 && s.charAt(i) == p.charAt(j)) {
            i--;
            j--;
            minLength--;
        }
        return new String[] { s.substring(0, i + 1), p.substring(0, j + 1) };
    }
}
