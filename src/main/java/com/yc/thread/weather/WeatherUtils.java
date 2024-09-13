package com.yc.thread.weather;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class WeatherUtils {

    public static void main(String[] args) throws IOException, ParserConfigurationException, SAXException {
        WeatherUtils.getWeatherList("北京");
    }

    /**
     * 获取指定城市的天气信息列表
     *
     * @param cityName 城市名称
     * @return 包含天气信息的字符串列表
     * @throws IOException                  如果发生 I/O 错误
     * @throws SAXException                 如果 XML 解析错误
     * @throws ParserConfigurationException 如果配置解析器错误
     */
    public static List<String> getWeatherList(String cityName) throws IOException, SAXException, ParserConfigurationException {
        List<String> weatherList = new ArrayList<>();
        String xml = getWeather(cityName);

        // 解析 XML 文件
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();//创建解析器工厂
        DocumentBuilder builder = factory.newDocumentBuilder();//创建解析器
        InputStream inputStream = new ByteArrayInputStream(xml.getBytes(StandardCharsets.UTF_8));//将字符串转换为输入流
        Document doc = builder.parse(inputStream);//将输入流转换为 Document 对象

        Element root = doc.getDocumentElement();//获取根元素
        NodeList nodeList = root.getElementsByTagName("string");//获取所有 string 元素列表
        if (nodeList != null && nodeList.getLength() > 0) {
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                weatherList.add(node.getTextContent().trim());
            }
        }
        System.out.println("list大小:"+weatherList.size());
        System.out.println("内容:"+weatherList);
        return weatherList;
    }

    /**
     * 向指定 URL 发送 GET 请求获取天气信息
     *
     * @param cityName 城市名称
     * @return 返回服务器响应的字符串
     * @throws IOException 如果发生 I/O 错误
     */
    public static String getWeather(String cityName) throws IOException {
        String urlStr = "http://ws.webxml.com.cn/WebServices/WeatherWebService.asmx/getWeatherbyCityName?theCityName=" + cityName;
        URL url = new URL( urlStr );
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Host", "ws.webxml.com.cn");

        try (InputStream isr = con.getInputStream()) {
            byte[] buf = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = isr.read(buf)) != -1) {
                sb.append(new String(buf, 0, len, StandardCharsets.UTF_8));
            }
            String response = sb.toString();
//            System.out.println(response);
            return response;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            con.disconnect();
        }
        return null;
    }
}
