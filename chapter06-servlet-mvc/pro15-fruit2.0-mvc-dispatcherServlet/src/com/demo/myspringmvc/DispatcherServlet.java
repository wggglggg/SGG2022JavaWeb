package com.demo.myspringmvc;

import com.demo.util.StringUtil;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

/**
 * ClassName: DispatcherServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/11 21:14
 * @Version 1.0
 */
@WebServlet("*.do")
public class DispatcherServlet extends HttpServlet {

    // 存放id 与 类对象，也就是xml文件里bean的id与class
    private Map<String, Object> beanMap = new HashMap<>();

    // init初始化作用就是从xml读取id与之对应的class，并存入Map中，下面的service会读取Map中的值
    @Override
    public void init() {
        System.out.println("init....");
        InputStream is = getClass().getClassLoader().getResourceAsStream("applicationContext.xml");
        try {
            //1.创建DocumentBuilderFactory
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            //2.创建DocumentBuilder对象
            DocumentBuilder documentBuilder = documentBuilderFactory.newDocumentBuilder();
            //3.创建Document对象
            Document document = documentBuilder.parse(is);

            //4.获取所有的bean节点
            NodeList beanNodeList = document.getElementsByTagName("bean");
            for (int i = 0; i < beanNodeList.getLength(); i++) {
                Node beanNode = beanNodeList.item(i);
                if (beanNode.getNodeType()==Node.ELEMENT_NODE){//是元素类型也就是有元素
                    //那就转成元素通过元素能获取里面的数据
                    Element beanElement = (Element) beanNode;
                    //获取bean元素标签内的两个属性id class值，下面开始使用反射获取class对应的类
                    String id = beanElement.getAttribute("id");
                    String className = beanElement.getAttribute("class");

                    // 反射获取到bean类， 例如FruitController
                    Class controllerBeanClass = Class.forName(className);
                    // 获取到bean类实例对象
                    Object beanObj = controllerBeanClass.getDeclaredConstructor().newInstance();

                    //由于FruitController去除了@WebServlet,导致它不再是一个Servlet,也就无法自动调用init server destroy
                    // 也就无法调用getServletContext,导致ServletContext上下文为null.
//                    Method setServletContextMethod = controllerBeanClass.getDeclaredMethod("setServletContext", ServletContext.class);
//                    setServletContextMethod.invoke(beanObj , this.getServletContext());
                    // id与之对应的class，并存入Map中
                    beanMap.put(id, beanObj);
                }
            }
        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
        request.setCharacterEncoding("utf-8");

        //假设url是：  http://localhost:8080/pro15/hello.do
        //那么servletPath是：    /hello.do
        // 我的思路是：
        // 第1步： /hello.do ->   hello   或者  /fruit.do  -> fruit
        // 第2步： hello -> HelloController 或者 fruit -> FruitController
        String servletPath = request.getServletPath();
        servletPath = servletPath.substring(1);
        int lastDotIndex = servletPath.lastIndexOf(".do");
        servletPath = servletPath.substring(0, lastDotIndex);

        // 通过去除头与尾后的servletPath也就是id,从Map中获取对应的class
        Object controllersBeanObj = beanMap.get(servletPath);

        // 从网页请求端获取operate, 为空时就自动给上Index,这样就会去调动index
        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }

        try {
            // 获取beanController中的与operatec对应的方法，operate会从网页端拿到del update add等值，
            Method beanMethod = controllersBeanObj.getClass().getDeclaredMethod(operate, HttpServletRequest.class, HttpServletResponse.class);

            if (beanMethod != null){
                // 有些方法是private, 所以拿到public权限 才能操作它
                beanMethod.setAccessible(true);
                // 调用类 中的方法，第一参数为哪个bean类，后面是方法的参数
                beanMethod.invoke(controllersBeanObj, request, response);
            } else {
                throw new RuntimeException("operate值 非法");
            }

        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }


    }
}
