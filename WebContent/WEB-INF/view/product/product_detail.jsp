<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<c:set var='root' value="${pageContext.request.contextPath }/"/>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>팡야팡야</title>
<link rel="icon" type="image/x-icon" href="${root}image/favicon.png">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/umd/popper.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
<script type="text/javascript">
$(document).ready(function() {
	var product_idx = $("#product_idx").val();
	var member_id = $("#member_id").val();

	$.getJSON("/admin/product/getAttaches/"+product_idx, function(result) {
		var str = '';
		$(result).each(function() {
			var data = this;
			str += makeHtmlcode_read(data);
		});
	});
	
	$("#select_count").on('blur', function() {
		var amount = $(this).val();
		var price = $("#price").val();
		var shipping = 3000; //배송비
		var finalPrice = (amount*price) + shipping;

		var str = '';
		
		str += '<p><strong>수량: </strong><span>&nbsp;'+amount+'개</span>&nbsp;&nbsp;&nbsp;';	
		str += '<strong>배송비: </strong><span>&nbsp;'+shipping+'</span>&nbsp;&nbsp;&nbsp;';
		str	+= '<strong>가격: </strong><span>&nbsp;' + price + ' 원</span></p>';
		str += '<h5><strong>총 결제금액:<span>&nbsp;' + finalPrice + '원</strong></span></h5>'; 
		str += '<span class="glyphicon glyphicon-exclamation-remove"></span>';
		
		$(".selected_option").html(str);
	});
	
	//장바구니에 넣기
	$(".btn-cart").click(function(){
			
		if(member_id.length == 0){ alert('로그인을 해주셔야 합니다.'); return; }
		
		var product_idx = $("#product_idx").val();
		var amount = $(".numBox").val();
		      
		var data = {
		     product_idx : product_idx,
		     amount : amount
		     };
		
		$.ajax({
			type : "post",
			url : "${root}mypage/cart/",
			data : data,
			success : function(result) {
				if (result.trim() == 'add_success') {
					var check = confirm("카트에 등록되었습니다.");
						if (check) {//내 장바구니로 이동! 
							location.assign("${root}mypage/cart/cartlist/"+member_id);
						}
				} else if (result.trim() == 'already_existed') {
						alert("이미 카트에 등록된 상품입니다. 장바구니를 확인해주세요!");
				}
			}
		}); //아작스 끝
	}); //장바구니 넣기 부분 
		
});
</script>
</head>
<style>
.entire{border: 1px solid lightgray; font-size: 15px; }
</style>
<body>
<c:import url="/WEB-INF/view/include/head_meta.jsp" />
<c:import url="/WEB-INF/view/include/top_menu.jsp"/>
<!-- 내용 -->
<div align="center">
<div class="container" style="margin-top:50px; margin-bottom: 200px; ">
<!-- 시작 -->
<table class="entire">
	<tr><!-- 상품 사진 출력 영역 -->
		<td>
			<img src="${root }image/product/${productDetail.product_img}" width="500px" height="450px" alt="${productDetail.product_name}">
		</td>
		<td align="center">
			<table style=" width:500px; height:450px;">
				<tr align="center">
					<td width="80">&nbsp;&nbsp;상품일련번호: </td>
					<td width="160">${productDetail.product_idx}</td>
				</tr>
				<tr align="center">
					<td width="80">&nbsp;&nbsp;상품명: </td>
					<td width="160">${productDetail.product_name}</td>
				</tr>
				<tr align="center">
					<td width="80">&nbsp;&nbsp;상품가격: </td>
					<td width="160"><fmt:formatNumber value="${productDetail.product_price}" pattern="###,###,###"/>원</td>
				</tr>
				<tr align="center">
					<td width="80">&nbsp;&nbsp;배송비: </td>
					<td width="160">3,000 원</td>
					
				</tr>
				<tr align="center">
					<td width="80">&nbsp;&nbsp;보관방법: </td>
					<td width="160">${productDetail.storage_method }</td>
				</tr>
				<tr align="center">
					<td width="80">&nbsp;&nbsp;유통기한: </td>
					<td width="160">${productDetail.expiration_date }</td>
				</tr>
				<tr align="center">
					<td width="80">&nbsp;&nbsp;구매수량: </td>
					<td width="160">
						<!-- 해당 제품 장바구니 담기 -->
						<input type="number" name="amount" value="${amount}" placeholder="0" min="1" max="999" class="numBox" id="select_count">개
						<input type="hidden" name="product_idx" value="${productDetail.product_idx }" id="product_idx">
						<input type="hidden" name="price" value="${productDetail.product_price }" id="price">	
						<input type="hidden" name="member_id" value="${loginMemberDTO.member_id }" id="member_id">
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center" width="240"><hr>
						<div class="selected_option" style="height:90px; text-align: right;"></div> 
						<div class="btn-group-justified" style="text-align: center;" >
						 	<div class="btn btn-group">
								<button type="button" class="btn btn-danger btn-cart">장바구니에 넣기</button>
							</div>
						</div>
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
<!-- 끝 -->

</div>
<!-- 상품삭제버튼과 정보수정버튼은 관리자에게만 보인다. -->
<c:choose>
	<c:when test="${loginMemberDTO.member_id eq 'admin'}"><%--관리자가 로그인을 한 경우(공지사항 게시판의 경우는 관리자만 사용가능하다. ) --%>
		<a href="${root }product/modify?category_idx=${productDetail.category_idx}&product_idx=${productDetail.product_idx}" class="btn btn-secondary btn-sm" style="color:white">해당상품 정보 수정하기</a>
		<a href="${root }product/delete?category_idx=${productDetail.category_idx}&product_idx=${productDetail.product_idx}" class="btn btn-dark btn-sm" style="color:white">해당상품 삭제하기</a>
	</c:when>
	<c:otherwise></c:otherwise>
</c:choose>
</div>
<!-- 하단정보 -->
<c:import url="/WEB-INF/view/include/bottom_info.jsp" />
</body>
</html>