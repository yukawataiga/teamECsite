package com.internousdev.florida.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.florida.dao.CartInfoDAO;
import com.internousdev.florida.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class CartAction extends ActionSupport implements SessionAware {
	private Map<String, Object> session;
	private String Message;
	CartInfoDAO cartInfoDAO = new CartInfoDAO();
	private int totalPrice;
	private ArrayList<CartInfoDTO> cartInfo = new ArrayList<>();

	public String execute() {
		if (!session.containsKey("tempUserId") && !session.containsKey("userId")) {
			return "sessionTimeout";
		}

		// 本ユーザーか仮ユーザーかでカート情報TBに送るuserIDを変更
		String userId;
		if (Integer.parseInt(session.get("logined").toString()) == 1) {
			userId = session.get("userId").toString();
		} else {
			userId = String.valueOf(session.get("tempUserId"));
		}
		cartInfo = cartInfoDAO.getCartInfo(userId);

		// カート情報がなかった場合、メッセージを追加してcart.jspに飛ばす。
		if (cartInfo == null || cartInfo.isEmpty()) {
			Message = "カート情報がありません";
			return SUCCESS;
		}

		// カート情報があった場合
		totalPrice = cartInfoDAO.getTotalPrice(userId);
		return SUCCESS;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public String getMessage() {
		return Message;
	}

	public void setMessage(String message) {
		Message = message;
	}

	public int getTotalPrice() {
		return totalPrice;
	}

	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}

	public ArrayList<CartInfoDTO> getCartInfo() {
		return cartInfo;
	}

	public void setCartInfo(ArrayList<CartInfoDTO> cartInfo) {
		this.cartInfo = cartInfo;
	}

}
