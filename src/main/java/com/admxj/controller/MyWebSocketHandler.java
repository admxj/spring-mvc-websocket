package com.admxj.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.*;

import java.util.ArrayList;

/**
 * Socket处理器
 *
 * @author Goofy
 * @Date 2015年6月11日 下午1:19:50
 */
@Component
public class MyWebSocketHandler implements WebSocketHandler {

	private static final Logger log = Logger.getLogger(MyWebSocketHandler.class);

	public static final ArrayList<WebSocketSession> userSocketSessionMap;

	static {
		userSocketSessionMap = new ArrayList<WebSocketSession>();
	}


	//建立连接后
	@Override
	public void afterConnectionEstablished(WebSocketSession session) throws Exception{
		userSocketSessionMap.add(session);
	}

	//消息处理，在客户端通过Websocket API发送的消息会经过这里，然后进行相应的处理
	@Override
	public void handleMessage(WebSocketSession session, WebSocketMessage<?> message) throws Exception {

		String msg=message.getPayload().toString();
		System.out.println(msg);
	}

	//消息传输错误处理
	@Override
	public void handleTransportError(WebSocketSession session, Throwable exception) throws Exception {

	}

	//关闭连接后
	@Override
	public void afterConnectionClosed(WebSocketSession session,
									  CloseStatus closeStatus) throws Exception {
		log.info("connect websocket closed.......");
		userSocketSessionMap.remove(session);
	}

	@Override
	public boolean supportsPartialMessages() {
		return false;
	}

}
