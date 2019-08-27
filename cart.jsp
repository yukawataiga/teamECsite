<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="./css/style.css">
<link rel="stylesheet" href="./css/cart.css">
<title>カート</title>
</head>
<body>
	<script src="./js/cart.js"></script>
	<jsp:include page="header.jsp" />
	<div id="contents">
		<h1>カート画面</h1>
		<s:if test="cartInfo != null && cartInfo.size() > 0">
			<s:form id="cartForm">
				<table class="horizontal-list-table">
					<thead>
						<tr>
							<th><s:label value="#" /></th>
							<th><s:label value="商品名" /></th>
							<th><s:label value="商品名ふりがな" /></th>
							<th><s:label value="商品画像" /></th>
							<th><s:label value="値段" /></th>
							<th><s:label value="発売会社名" /></th>
							<th><s:label value="発売年月日" /></th>
							<th><s:label value="購入個数" /></th>
							<th><s:label value="合計金額" /></th>
						</tr>
					</thead>
					<tbody>
						<s:iterator value="cartInfo">
							<tr>
								<!-- ここでsタグを使用しない理由：教材の範囲内の内容で作成するため。 -->
								<td><input type="checkbox" name="checkList"
									class="checkList" value='<s:property value="productId" />'
									onchange="checkValue(this)"></td>
								<td><s:property value="productName" /></td>
								<td><s:property value="productNameKana" /></td>
								<td><img
									src='<s:property value="imageFilePath"/>/<s:property value="imageFileName"/>'
									width="50px" height="50px" /></td>
								<td><s:property value="price" />円</td>
								<td><s:property value="releaseCompany" /></td>
								<td><s:property value="releaseDate" /></td>
								<td><s:property value="productCount" /></td>
								<td><s:property value="subtotal" />円</td>
							</tr>
						</s:iterator>
					</tbody>
				</table>
				<div class="cart_total">
					<h2 >
							<s:label value="カート合計金額 :" />
							<s:property value="totalPrice" />円
					</h2>
				</div>
				<br>
				<div class="cart_btn">
					<div class="submit_btn_leftbox">
						<s:submit value="決済" class="submit_btn"
							onclick="goSettlementConfirmAction()" />
					</div>

					<div class="submit_btn_rightbox">
						<s:submit value="削除" id="deleteButton" class="submit_btn"
							onclick="goDeleteCartAction()" disabled="true" />
					</div>
				</div>

			</s:form>
		</s:if>
		<s:else>
			<div class="info">カート情報がありません。</div>
		</s:else>
	</div>
</body>
</html>