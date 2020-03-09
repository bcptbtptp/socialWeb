package com.socialWeb.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.socialWeb.sms.utils.SmsUtil;
import java.util.Map;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @ClassName SmsListener
 * @Description TODO
 * @Author 42
 * @Date 2020/3/9 上午 10:55
 * @Version 1.0
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener
{

	@Autowired
	private SmsUtil smsUtil;

	@Autowired
	private Environment env;

//	@Value("${aliyun.sms.template_code}")
//	private String template_code;
//
//	@Value("${aliyun.sms.sign_name}")
//	private String sign_name;

	@RabbitHandler
	public void executeSms(Map<String, String> map){
		String mobile = map.get("mobile");
		String checkCode = map.get("checkCode");
		String templateCode = "SMS_185241587";
		String signName = "blitz社交网络";
//		System.out.println("手机号：" + map.get("mobile"));
//		System.out.println("验证码：" + map.get("checkCode"));
		try {
			smsUtil.sendSms(mobile, templateCode, signName, "{\"checkcode\":\"" + checkCode + "\"}");
		} catch (ClientException e) {
			e.printStackTrace();
		}
	}
}
