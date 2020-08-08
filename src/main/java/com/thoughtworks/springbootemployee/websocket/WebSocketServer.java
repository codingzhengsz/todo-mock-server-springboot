package com.thoughtworks.springbootemployee.config;

import org.springframework.stereotype.Component;

import javax.websocket.*;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;

@ServerEndpoint("/websocket/{userId}")
@Component
public class WebSocketServer {

  private Session session;

  /** 连接建立成功调用的方法 */
  @OnOpen
  public void onOpen(Session session, @PathParam("userId") String userId) {
    System.out.println("用户连接:" + userId);
  }

  /** 连接关闭调用的方法 */
  @OnClose
  public void onClose() {
    System.out.println("用户退出");
  }

  /**
   * 收到客户端消息后调用的方法
   *
   * @param message 客户端发送过来的消息
   */
  @OnMessage
  public void onMessage(String message, Session session) {
    System.out.println("用户消息: " + message);
  }

  /**
   * 发生错误时调用
   *
   * @param session
   * @param error
   */
  @OnError
  public void onError(Session session, Throwable error) {
    error.printStackTrace();
    System.out.println("Error" + error.getMessage());
  }

  /** 实现服务器主动推送 */
  public void sendMessage(String message) throws IOException {
    this.session.getBasicRemote().sendText(message);
  }

}
