package com.javaweb.exceps;

/**
 * ClassName: DAOException
 * Description:
 *
 * @Author wggglggg
 * @Create 2023/7/15 15:46
 * @Version 1.0
 */
public class DAOException extends RuntimeException{
    public DAOException(String message) {
        super(message);
    }
}
