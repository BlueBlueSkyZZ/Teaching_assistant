package weixin.action;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Map;

import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.DocumentException;

import net.sf.json.JSONObject;
import weixin.entity.AccessToken;
import weixin.entity.message.News;
import weixin.util.CheckUtil;
import weixin.util.MessageUtil;
import weixin.util.WeixinUtil;

/**
 * Servlet implementation class WeixinServlet
 */
@WebServlet("/WeixinServlet")
public class WeixinServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public WeixinServlet() {
		super();
	}

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		try {
			AccessToken token = WeixinUtil.getAccessToken(); // ��ȡaccess_token
			System.out.println("Ʊ��  " + token.getToken());
			System.out.println("��Ч ʱ��  " + token.getExpiresIn());

			String menu = JSONObject.fromObject(WeixinUtil.initMenu()).toString();
			int result = WeixinUtil.createMenu(token.getToken(), menu);
			if (result == 0) {
				System.out.println("�����˵��ɹ�");
			} else {
				System.out.println("������: " + result);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String signature = request.getParameter("signature");
		String timestamp = request.getParameter("timestamp");
		String nonce = request.getParameter("nonce");
		String echostr = request.getParameter("echostr");

		PrintWriter out = response.getWriter();

		if (CheckUtil.checkSignature(signature, timestamp, nonce)) {
			out.print(echostr);
		}

		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();

		try {
			Map<String, String> map = MessageUtil.xmlToMap(request);
			String fromUserName = map.get("FromUserName"); // �û�weid
			String toUserName = map.get("ToUserName"); // ���ǵ�΢�ź�
			//System.out.println("fromusername:" + fromUserName);
			//System.out.println("tousername:" + toUserName);
			String msgType = map.get("MsgType");
			String content = map.get("Content");

			// ***************************************************************
			AccessToken token = WeixinUtil.getAccessToken(); // ��ȡaccess_token
			String message = null;

			// �ж���Ϣ����
			if(MessageUtil.MESSAGE_TEXT.equals(msgType)){
				String msgBack = MessageUtil.handleUserMsg(toUserName, fromUserName, content);
				/*String numRegex = "[0-9]{1,}";
				if(msgBack.matches(numRegex)){
					News news = MessageUtil.generateBookSearch(msgBack,fromUserName);
					message = MessageUtil.initNewsMessage(toUserName, fromUserName, news);
				}else{
					message = MessageUtil.initText(toUserName, fromUserName, msgBack);
				}*/
				//System.out.println(map.get("Content"));
				message = MessageUtil.initText(toUserName, fromUserName, "���");
				//System.out.println(message);
			}
			if (MessageUtil.MESSAGE_EVENT.equals(msgType)) {
				String eventType = map.get("Event");

				if (MessageUtil.MESSAGE_SUBSCRIBE.equals(eventType)) {
					// ��ʾ��ӭ����
					message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
				} else if (MessageUtil.MESSAGE_CLICK.equals(eventType)) {
					String key = map.get("EventKey");
					// ��ʾ���˵�
					if (key.equals("help")) {
						message = MessageUtil.initText(toUserName, fromUserName, MessageUtil.menuText());
					}
				} else if (MessageUtil.MESSAGE_SCANCODE.equals(eventType)) {
					String key = map.get("EventKey");
				}
			} else if (MessageUtil.MESSAGE_LOCATION.equals(msgType)) {
				String label = map.get("Label");
				message = MessageUtil.initText(toUserName, fromUserName, label);
			}

			out.print(message);

		} finally {
			out.close();
		}
	}

}
