package com.javaweb.exceps;

/**
 * ClassName: DispatcherServletException
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 15:54
 * @Version 1.0
 */
public class DispatcherServletException extends RuntimeException{
    public DispatcherServletException(String message) {
        super(message);
    }
}
