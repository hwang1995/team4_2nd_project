package com.team4.webapp.services;

import java.util.List;
import java.util.Map;

import com.team4.webapp.dao.OrdersDAO;
import com.team4.webapp.dto.CartListDTO;
import com.team4.webapp.dto.CartsDTO;
import com.team4.webapp.dto.CheckoutDTO;
import com.team4.webapp.dto.CheckoutListDTO;
import com.team4.webapp.dto.MembersDTO;
import com.team4.webapp.dto.MyPageDTO;
import com.team4.webapp.dto.OrderlistsDTO;
import com.team4.webapp.dto.OrdersDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.PreOrdersDTO;
import com.team4.webapp.dto.ProductsDTO;
public interface IOrderService {
	
	/**
	 * 회원이 결제를 하기 이전에 회원의 주소, 상품들, 상품의 총 가격을 알려주기 위해 작성한 인터페이스
	 * @param Long member_id
	 * @param List<ProductDetailsDTO> products
	 * @return PreCheckoutDTO (주문전의 데이터를 넘기기 위한 DTO)
	 */
	List<CheckoutListDTO> showOrderlists(List<PreOrdersDTO> preorder);
	
	
	/**
	 * 회원이 주문을 진행하기 위해 필요한 인터페이스 
	 * @param CheckoutDTO checkout 
	 * @return true or false
	 */
	boolean doCheckout(List<CheckoutListDTO> itemList, CheckoutDTO orderInfo);
	
	
	/**
	 * 회원이 가지고 있는 장바구니의 상품들을 보여주기 위해 작성한 인터페이스
	 * @param Long member_id
	 * @return List<CartListDTO>
	 */
	List<CartListDTO> getCartList(Long member_id);
	
	/**
	 * 회원이 상품 상세 페이지에서 자신이 주문하고 싶은 상품을 장바구니에 넣기 위한 인터페이스
	 * @param CartsDTO cart
	 * @return int (결과 값으로 쿼리 수행결과를 알기 위해)
	 */
	boolean addCart(MembersDTO memberInfo ,PreOrdersDTO cart);
	
	/**
	 * 회원이 장바구니에서 자신이 추가한 상품을 제거할 수 있는 인터페이스
	 * @param CartsDTO cart
	 * @return int
	 */
	boolean removeCart(Long cart_id);
	
	/**
	 * 회원이 장바구니에서 결제 페이지로 가기 이전에 장바구니의 정보를 가져와 전달하기 위한 인터페이스
	 * @param Long member_id
	 * @return List<PreOrdersDTO>
	 * product_id, product_color, product_size, product_quantity
	 */
	List<PreOrdersDTO> getNewCartList(Long member_id);
	
	/**
	 * 회원이 주문을 끝내고 장바구니에 존재하는 상품들을 다 지우기 위해 필요한 인터페이스
	 * @param Long member_id
	 * @return true or false
	 */
	boolean removeCarts(Long member_id);
	
	/**HERE**
	 * 주문 정보 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @return List<OrdersDTO>
	 */
	List<OrdersDTO> getOrdersList(Pager pager);
	
	/**
	 * 주문 정보 + 주문 번호 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @param Long order_id
	 * @return List<OrdersDTO>
	 */
	List<OrdersDTO> getOrdersListByOrderId(Pager pager, Long order_id);
	
	/**
	 * 주문 정보 + 배송 정보 + 페이저로 리스트를 받기 위해 제공하는 인터페이스
	 * @param Pager pager
	 * @param String delivery
	 * @return List<OrdersDTO>
	 */
	List<OrdersDTO> getOrdersListByDelivery(Pager pager, String delivery);
	
	/**
	 * 상세 주문 조회를 위해 제공하는 인터페이스
	 * @param Long order_id
	 * @return Map<String, Object> -> ordersDTO, membersDTO, List<OrderlistsDTO>, List<MyPageDTO>
	 */
	Map<String, Object> getOrderInfo(Long order_id);
	
	/**
	 * 주문 수정을 위해 제공하는 인터페이스
	 * @param OrdersDTO orderInfo
	 * @return OrdersDTO
	 */
	OrdersDTO modifyOrder(OrdersDTO orderInfo);
	
	/**
	 * 전체 주문의 갯수를 얻기 위해 제공하는 인터페이스
	 * @return
	 */
	int getTotalOrdersCount();
	
	/**
	 * 배송상태로 검색시 주문의 갯수를 얻기 위해 제공하는 인터페이스
	 * @return
	 */
	int getByDeliveryOrdersCount(String order_delivery_status);
	
	
}
