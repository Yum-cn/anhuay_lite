package com.anhuay.test.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 策略模板表
 * 
 * @author Yum
 * @email wtuada@126.com
 * @date 2018-07-07 14:05:30
 */

@Controller
@RequestMapping("/test")
public class TestController {

	 /**
     * 缓存的key
     */
    public static final String CACHE_KEY   = "taskLog";

	@GetMapping("")
	String StrategyTemplet() {
		return "test/test";
	}

	@CachePut(value = CACHE_KEY, key = "#message")
	@GetMapping("/abc")
	String abc(String type,String message,final HttpServletRequest request, final HttpServletResponse response) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");

		PrintWriter writer;
		try {
			writer = response.getWriter();
			
			if(StringUtils.isNotBlank(type)&&StringUtils.isNotBlank(message)){
				System.out.println("Date:" + dateFormat.format(date)+" type:"+type+" message:"+message);
				
				if(StringUtils.equals(type, "1")){
					writer.write("data: " + message+"进入了直播间！");
				}
				
			}
			
			
			writer.flush();
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return "test/test";
	}

	/**
	 * 未行通406
	 */
	@ResponseBody
	@GetMapping("/abc2")
	String abcV2(final HttpServletRequest request, final HttpServletResponse response) {

		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		Date date = new Date();

		response.setContentType("text/event-stream");
		response.setCharacterEncoding("UTF-8");

		System.out.println("Date:" + dateFormat.format(date));

		return "Date:" + dateFormat.format(date);
	}

}
