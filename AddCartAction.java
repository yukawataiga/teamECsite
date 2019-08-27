package com.internousdev.florida.action;

import java.util.ArrayList;
import java.util.Map;

import org.apache.struts2.interceptor.SessionAware;

import com.internousdev.florida.dao.CartInfoDAO;
import com.internousdev.florida.dto.CartInfoDTO;
import com.opensymphony.xwork2.ActionSupport;

public class AddCartAction extends ActionSupport implements SessionAware{

	private Map<String, Object> session;
	private int productCount;
	private int productId;
	private int totalPrice;
	CartInfoDAO dao = new CartInfoDAO();
	private ArrayList<CartInfoDTO> cartInfo = new ArrayList<>();

	public String execute() {
		if(!session.containsKey("tempUserId") && !session.containsKey("userId")) {
			return "sessionTimeout";
		}

		String result = ERROR;
		//本ユーザーか仮ユーザーかでカート情報TBに送るuserIDを変更
		String userId;
		if(Integer.parseInt(session.get("logined").toString()) == 1){
			userId = session.get("userId").toString();
		}else{
			userId = String.valueOf(session.get("tempUserId"));
		}

		int count = 0;
		if(dao.isExistCartInfo(userId,productId)) {
			//存在してる場合、既存のカートに個数を足す。
			count = dao.updateCartInfo(userId,productId,productCount);
		}else {
			//存在していない場合、新しく挿入する。
			count = dao.addCartInfo(userId, productId,productCount);
		}

		if(count > 0) {
			cartInfo = dao.getCartInfo(userId);
			totalPrice = dao.getTotalPrice(userId);
			result=SUCCESS;
		}
		return result;
	}

	@Override
	public void setSession(Map<String, Object> session) {
		this.session = session;
	}

	public Map<String, Object> getSession() {
		return session;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public int getProductCount() {
		return productCount;
	}

	public void setProductCount(int productCount) {
		this.productCount = productCount;
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
