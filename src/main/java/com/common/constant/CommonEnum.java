package com.common.constant;

import org.apache.commons.lang.StringUtils;

/**
 * 枚举相关配置
 * 
 * @author Yum
 */
public class CommonEnum {

	/**
	 * 简单状态标识
	 * 
	 * @author Yum
	 */
	public enum SIMPLESTATUS {

		Y("Y"), N("N"),VALID("valid"),INVALID("invalid");
		public final String value;

		SIMPLESTATUS(String value) {
			this.value = value;
		}
	}
	
	
	/**
	 * 简单状态标识
	 * 
	 * @author Yum
	 */
	public enum STATUS {

		ZERO(0),ONE(1),TWO(2),THREE(3),FOUR(4),FIVE(5),SIX(6),SEVEN(7),EIGHT(8),NINE(9);
		public final int value;

		STATUS(int value) {
			this.value = value;
		}
	}
	

	/**
	 * 保存类型
	 * @author   Yum
	 */
	public enum SAVETYPE {
		
		ADD("add", "添加"), 
		EDIT("edit", "编辑"), 
		;
		
		public final String name;
		public final String description;

		SAVETYPE(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public static String getDescription(String key) {
			for (SAVETYPE enumType : SAVETYPE.values()) {
				if (StringUtils.equals(key, enumType.name)) {
					return enumType.description;
				}
			}
			return null;
		}
	}

	/**
	 * 目标类型
	 * @author Yum
	 */
	public enum TARGETTYPE {
		
		/**page:网页*/
		PAGE("page", "网页"), 
		/**event:事件*/
		EVENT("event", "事件"), 
		;
		
		public final String name;
		public final String description;

		TARGETTYPE(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public static String getDescription(String key) {
			for (TARGETTYPE enumType : TARGETTYPE.values()) {
				if (StringUtils.equals(key, enumType.name)) {
					return enumType.description;
				}
			}
			return null;
		}
	}
	
	/**
	 * 用户自定义错误码
	 * @author   Yum
	 */
	public enum CODE {


		
		SUCCESS(1, "操作成功"),
		UNKNOWN_ERROR(-1, "系统繁忙，请稍后再试"),
		
		PARAM_MISSING(100,"参数不合法"),
		
		USER_NOT_EXIST(1000, "用户不存在"), 
		USER_EXIST(1001, "用户已存在"), 
		USER_UNAUTHORIZED(1002, "账号未授权"), 
		USERNAME_OR_PASSWORD_INCORRECT(1003, "用户名或密码不正确"), 
		USERNAME_INCORRECT(1004, "账号格式不正确"), 
		PASSWORD_INCORRECT(1005, "密码格式不正确"), 
		LOGIN_TIME_UNAUTHORIZED(1006, "登录时间未授权"), 
		WHITE_IP_UNAUTHORIZED(1007, "访问IP未授权"), 
		PASSWORD_EXPIRED(1008, "密码已过期"), 
		PASSWORD_DEFAULT(1009, "初始密码未修改"), 
		INVALID_LICENSE(1010, "无效的授权或授权已过期，请联系管理员"), 
		//USER_INFO_MISSING(1100, "用户信息异常"), 

	
		;

		public final int code;
		public final String description;

		CODE(int code, String description) {
			this.code = code;
			this.description = description;
		}

		public static String getDescription(int key) {
			for (CODE enumType : CODE.values()) {
				if (key == enumType.code) {
					return enumType.description;
				}
			}
			return null;
		}
	}
	
	/**
	 * 目标类型
	 * @author Yum
	 */
	public enum TIMETYPE {
		
		WEEK("week", "周"), 
		MONTH("month", "月"), 
		YEAR("year", "年"), 
		;
		
		public final String name;
		public final String description;

		TIMETYPE(String name, String description) {
			this.name = name;
			this.description = description;
		}

		public static String getDescription(String key) {
			for (TARGETTYPE enumType : TARGETTYPE.values()) {
				if (StringUtils.equals(key, enumType.name)) {
					return enumType.description;
				}
			}
			return null;
		}
	}
	
	public static void main(String[] args) {
		System.out.println("values:"+CommonEnum.TARGETTYPE.values());
		System.out.println("TEXT:"+CommonEnum.TARGETTYPE.PAGE);
		System.out.println("TEXT.name:"+CommonEnum.TARGETTYPE.PAGE.name);
		System.out.println("TEXT.description:"+CommonEnum.TARGETTYPE.PAGE.description);
		//System.out.println("TEXT.ordinal:"+MteduEnum.SMSTYPE.VOICE.ordinal());

		System.out.println("getDescription(name):"+CommonEnum.TARGETTYPE.getDescription("page"));
	}
}
