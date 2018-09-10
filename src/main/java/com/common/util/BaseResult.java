package com.common.util;

import java.io.Serializable;

/**
 * 
 * @author   Yum
 */
public class BaseResult<T> implements Serializable{

	private static final long serialVersionUID = 1L;
	private boolean success = true;
	private String message;
	private int code;
	private T data;


	public BaseResult(boolean success, int code) {
		super();
		this.success = success;
		this.code = code;
	}
	
	public BaseResult(int code,String message) {
		this.code = code;
		this.message = message;
	}
	
	public BaseResult(int code, T data, String message) {
		this.code = code;
		this.data = data;
		this.message = message;
	}
	
	public BaseResult(boolean success, int code, String message) {
		super();
		this.success = success;
		this.code = code;
		this.message = message;
	}

	public BaseResult() {
		super();
	}


	
	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
	
	/** 
     * @return Result<T> 
     */  
    public static <T> BaseResult<T> create() {  
        return new BaseResult<T>();  
    }  
  
    /**
     * 成功
     * @author   Yum
     */
    public static <T> BaseResult<Object> success(T data) {  
    	BaseResult<Object> result = BaseResult.create();  
        result.setSuccess(true);  
        result.setData(data);  
        return result;  
    }  
  
    /** 
     * 成功需要code、message的场景
     * @author   Yum
     */
    public static <T> BaseResult<Object> success(T data, int code, String message) {  
    	BaseResult<Object> result = BaseResult.create();  
        result.setSuccess(true);  
        result.setData(data);  
        result.setCode(code);  
        result.setMessage(message);  
        return result;  
    }  
	
    
    /**
     * 不成功
     * @author   Yum
     */
    public static <T> BaseResult<Object> unsuccess() {  
    	BaseResult<Object> result = BaseResult.create();  
        result.setSuccess(false);  
        return result;  
    }  
  
    /** 
     * 不成功需要code、message的场景
     * @author   Yum
     */
    public static <T> BaseResult<Object> unsuccess(String message) {  
    	BaseResult<Object> result = BaseResult.create();  
        result.setSuccess(false);  
        result.setMessage(message);  
        return result;  
    }  
    /** 
     * 不成功需要code、message的场景
     * @author   Yum
     */
    public static <T> BaseResult<Object> unsuccess(int code, String message) {  
    	BaseResult<Object> result = BaseResult.create();  
    	result.setSuccess(false);  
    	result.setCode(code);  
    	result.setMessage(message);  
    	return result;  
    }  

}
