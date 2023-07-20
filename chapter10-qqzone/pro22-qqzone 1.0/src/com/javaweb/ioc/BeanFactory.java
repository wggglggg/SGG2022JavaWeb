package com.javaweb.ioc;

import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * ClassName: BeanFactory
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/14 21:14
 * @Version 1.0
 */
public interface BeanFactory {
    Object getBean(String id);

}
