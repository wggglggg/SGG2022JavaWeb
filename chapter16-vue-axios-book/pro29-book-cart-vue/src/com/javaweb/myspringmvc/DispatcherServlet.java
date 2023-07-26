package com.javaweb.myspringmvc;

import com.javaweb.exceps.DispatcherServletException;
import com.javaweb.ioc.BeanFactory;
import com.javaweb.ioc.ClassPathXmlApplicationContext;
import com.javaweb.util.StringUtil;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.Method;
import java.lang.reflect.Parameter;

/**
 * ClassName: DispatcherServlet
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/11 21:14
 * @Version 1.0
 */
@WebServlet("*.do")
public class DispatcherServlet extends ViewBaseServlet {

    private BeanFactory beanFactory;

    // init初始化作用就是从xml读取id与之对应的class，并存入Map中，下面的service会读取Map中的值
    @Override
    public void init() throws ServletException {
        super.init();
        System.out.println("init....");
//        beanFactory =  new ClassPathXmlApplicationContext();
        ServletContext servletContext = getServletContext();
        Object beanFactoryObj = servletContext.getAttribute("beanFactory");
        if (beanFactoryObj != null){
            beanFactory = (BeanFactory) beanFactoryObj;
        }else {
            throw new RuntimeException("IOC窗口获取失败");
        }


    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //设置编码
//        request.setCharacterEncoding("utf-8");

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
        Object controllersBeanObj = beanFactory.getBean(servletPath);

        // 从网页请求端获取operate, 为空时就自动给上Index,这样就会去调动index
        String operate = request.getParameter("operate");
        if (StringUtil.isEmpty(operate)){
            operate = "index";
        }

        try {
            // 获取beanController中的与operatec对应的方法，operate会从网页端拿到del update add等值，
            Method[] methods = controllersBeanObj.getClass().getDeclaredMethods();

            for (Method beanMethod : methods){
                if (operate.equals(beanMethod.getName())){
                    //1.统一获取请求参数

                    //1-1.获取当前方法的参数，返回参数数组
                    Parameter[] parameters = beanMethod.getParameters();
                    //1-2.parameterValues 用来承载参数的值
                    Object[] parameterValues = new Object[parameters.length];
                    for (int i = 0; i < parameters.length; i++) {
                        Parameter parameter = parameters[i];
                        //(Integer fid,HttpSession session),parameter(就是HttpSession session)
                        // .getName就是获取到session和fid 这个"fid", "session"字符串
                        String parameterName = parameter.getName();
                        //如果参数名是request,response,session
                        // 那么就不是通过请求中获取参数的方式了

                        if ("request".equals(parameterName)){
                            parameterValues[i] = request;
                        } else if ("response".equals(parameterName)) {
                            parameterValues[i] = response;
                        } else if ("session".equals(parameterName)) {
                            parameterValues[i] = request.getSession();
                        } else {
                            //从请求中获取参数值,以前是在FruitController里index add del方法中获取
                            //request.getParameter("fid")
                            String parameterValue = request.getParameter(parameterName);
                            // 处理Integer类型的数据，request.getParameter("fid")获取到String,会转成Integer4
                            if (parameterValue != null){
                                //如果parameter也就是(Integer fid,HttpSession session)
                                //.getType()获取到Intege HttpSession,
                                //.getName()获取到"Integer" "HttpSession"
                                if ("java.lang.Integer".equals(parameter.getType().getName())){
                                    Object parameterInteger = Integer.parseInt(parameterValue);
                                    parameterValues[i] = parameterInteger;
                                    continue;
                                }

                            }
                            parameterValues[i] = parameterValue;
                        }

                    }
                    //2.controller组件中的方法调用
                    beanMethod.setAccessible(true);
                    // controllersBeanObj为bean类  也就是FruitController.class
                    // parameterValues 也就是beanMethod对就的方法 add edit index这些方法里的参数值
                    Object returnObj = beanMethod.invoke(controllersBeanObj, parameterValues);
                    String methodReturnStr = (String) returnObj;

                    // 为空就不做处理
                    if (StringUtil.isEmpty((methodReturnStr))){
                        return;
                    }

                    // 3.视图处理

                    if (methodReturnStr.startsWith("redirect:")){  //比如：  redirect:fruit.do
                        String redirectStr = methodReturnStr.substring("redirect:".length());
                        response.sendRedirect(redirectStr);
                    } else if (methodReturnStr.startsWith("json:")) {
                        response.setCharacterEncoding("UTF-8");
                        response.setContentType("application/json;charset=utf-8");
                        String jsonStr = methodReturnStr.substring("json:".length());
                        PrintWriter writer = response.getWriter();
                        writer.print(jsonStr);
                        writer.flush();
                    }
                    else {
                        super.processTemplate(methodReturnStr, request, response);
                    }
                }

            }

//            if (beanMethod != null){
//                // 有些方法是private, 所以拿到public权限 才能操作它
//                //2.controller组件中的方法调用
//                // 调用类 中的方法，第一参数为哪个bean类，后面是方法的参数
//
//            } else {
//                throw new RuntimeException("operate值 非法");
//            }

        } catch (Exception e) {
            e.printStackTrace();
            throw new DispatcherServletException("DispatcherServlet service 错误");
        }
    }
}