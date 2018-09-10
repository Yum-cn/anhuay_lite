package com.common.util;

import com.common.constant.CommonEnum;

public class BaseResultHelper {

	public static BaseResult<Object> success() {
		return new BaseResult<Object>(CommonEnum.CODE.SUCCESS.code, CommonEnum.CODE.SUCCESS.description);
	}
	
	public static BaseResult<Object> success(Object object) {
		return new BaseResult<Object>(CommonEnum.CODE.SUCCESS.code, object, CommonEnum.CODE.SUCCESS.description);
	}

	public static BaseResult<Object> success(int code,Object object) {
		return new BaseResult<Object>(code, object, CommonEnum.CODE.SUCCESS.description);
	}
	
	public static BaseResult<Object> error(String message) {
		return new BaseResult<Object>(CommonEnum.CODE.UNKNOWN_ERROR.code, null, message);
	}
	
	public static BaseResult<Object> error(int code,String message) {
		return new BaseResult<Object>(code, null, message);
	}
	
	public static BaseResult<Object> error(int code,Object object,String message) {
		return new BaseResult<Object>(code, object, message);
	}
	
	public static BaseResult<Object> error() {
		return new BaseResult<Object>( CommonEnum.CODE.UNKNOWN_ERROR.code, null, CommonEnum.CODE.UNKNOWN_ERROR.description);
	}
}
