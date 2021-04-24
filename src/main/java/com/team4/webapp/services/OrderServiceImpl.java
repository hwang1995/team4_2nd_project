package com.team4.webapp.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.team4.webapp.dao.CartsDAO;
import com.team4.webapp.dao.MembersDAO;
import com.team4.webapp.dao.OrderlistsDAO;
import com.team4.webapp.dao.OrdersDAO;
import com.team4.webapp.dao.ProductsDAO;
import com.team4.webapp.dto.CartListDTO;
import com.team4.webapp.dto.CartsDTO;
import com.team4.webapp.dto.CheckoutDTO;
import com.team4.webapp.dto.CheckoutListDTO;
import com.team4.webapp.dto.MembersDTO;
import com.team4.webapp.dto.MyPageDTO;
import com.team4.webapp.dto.OrderDetailsDTO;
import com.team4.webapp.dto.OrderlistsDTO;
import com.team4.webapp.dto.OrdersDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.PreOrdersDTO;
import com.team4.webapp.dto.ProductsDTO;

@Service
public class OrderServiceImpl implements IOrderService{
	
	private static final Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);
	
	@Autowired
	private ProductsDAO productsDAO;
	
	@Autowired
	private OrdersDAO ordersDAO;
	
	@Autowired
	private OrderlistsDAO orderlistsDAO;
	
	@Autowired
	private CartsDAO cartsDAO;
	
	@Autowired
	private MembersDAO membersDAO;
	
	/**
	 * 장바구니의 목록을 가져오기 위한 서비스
	 * @param Long member_id (회원의 번호)
	 * @return 장바구니 정보 (수량) / 회원 정보 / 상품 정보 (색상, 이미지, ..)
	 */
	@Override
	public List<CartListDTO> getCartList(Long member_id) {
		// 장바구니와 장바구니의 리스트 (상품과 장바구니를 합친 것)을 만들기 위함.
		List<CartsDTO> cartLists = cartsDAO.selectCartListByMemberId(member_id);
		List<CartListDTO> cartInfoLists = new ArrayList<CartListDTO>();
		
		// 장바구니 리스트를 만들기 위한 로직
		for(CartsDTO cart : cartLists) {
			CartListDTO cartInfoList = new CartListDTO();
			
			Long product_id = cart.getProduct_id();
			ProductsDTO product = productsDAO.selectByProductId(product_id);
			
			product.setImagePath();
			
			cartInfoList.setCartsInfo(cart);
			cartInfoList.setProductInfo(product);
			
			cartInfoLists.add(cartInfoList);
		}
		
		return cartInfoLists;
	}
	
	/**
	 * 장바구니에 상품을 추가하기 위한 서비스
	 * @param MembersDTO memberInfo (회원 정보)
	 * @param PreOrdersDTO cart (상품 정보, 수량)
	 * @return boolean (성공했는지의 여부)
	 */
	@Override
	public boolean addCart(MembersDTO memberInfo, PreOrdersDTO cart) {
		// 장바구니 DTO에 회원 정보와 장바구니 정보를 넣어준다.
		CartsDTO sendData = new CartsDTO();
		
		sendData.setMemberInfo(memberInfo);
		sendData.setCartInfo(cart);
		
		int row = cartsDAO.insertCarts(sendData);
		boolean result = isTransactionSuccess(row);
		
		return result;
	}

	/**
	 * 장바구니 ID로 장바구니의 상품을 삭제하는 서비스
	 * @param Long cart_id (장바구니 ID)
	 * @return boolean (성공 여부)
	 */
	@Override
	public boolean removeCart(Long cart_id) {
		int rows = cartsDAO.deleteByCartId(cart_id);
		boolean result = isTransactionSuccess(rows);
		
		return result;
	}
	
	/**
	 * 주문 리스트를 보여주기 위한 서비스
	 * @param List<PreOrdersDTO> preOrder (상품의 색상. 수량)
	 * @return List<CheckoutListDTO> -> ProductsDTO의 정보 (상품 이름, 가격) + PreOrdersDTO의 정보 (색상, 크기, 수량)
	 */
	@Override
	public List<CheckoutListDTO> showOrderlists(List<PreOrdersDTO> preOrder) {
		// 주문리스트의 정보를 보여주기 위해 존재.
		List<CheckoutListDTO> checkoutInfo = new ArrayList<>();
		
		// 주문 리스트를 생성하는 로직
		for(PreOrdersDTO order : preOrder) {
			CheckoutListDTO combinedData = new CheckoutListDTO();
			Long product_id = order.getProduct_id();
			
			ProductsDTO product = productsDAO.selectByProductId(product_id);
			product.setImagePath();
			
			combinedData.setProductsInfo(product);
			combinedData.setPreOrdersInfo(order);
			checkoutInfo.add(combinedData);
		}
		
		return checkoutInfo;
	}
	
	/**
	 * 실제 주문 프로세스를 진행하는 서비스
	 * @param List<CheckoutListDTO> -> ProductsDTO의 정보 (상품 이름, 가격) + PreOrdersDTO의 정보 (색상, 크기, 수량)
	 * @param CheckoutDTO (회원 ID, 주문 은행, 주문자 정보)
	 * @return boolean (성공 여부)
	 */
	@Override
	public boolean doCheckout(List<CheckoutListDTO> itemLists, CheckoutDTO orderInfo) {
		
		// 1. 주문을 생성합시다.
		OrdersDTO order = new OrdersDTO();
		order.setOrderInfo(orderInfo);
		order.setOrderStatus(0, "PAYMENT_FINISHED", "DELIVERY_PENDING");
		int row = ordersDAO.insertOrders(order);
		// 2. 주문이 생성이 잘 되었는지 체크한다.
		boolean result = isTransactionSuccess(row);
		
		// 3. 주문을 생성하고 나온 Long order_id; 를 저장합시다.
		Long order_id = order.getOrder_id();
		
		for(CheckoutListDTO item : itemLists) {
			OrderlistsDTO orderlist = new OrderlistsDTO();
			orderlist.setOrder_id(order_id);
			orderlist.setCheckoutInfo(item);
			int rows = orderlistsDAO.insertOrderlists(orderlist);
			result = isTransactionSuccess(rows);
		}
		
		return result;
	}
	
	/**
	 * 회원이 장바구니에서 결제 페이지로 가기 이전에 장바구니의 정보를 가져오는 서비스
	 * @param Long member_id (회원 ID)
	 * @return List<PreOrdersDTO> -> 상품의 색상, 크기, 수량
	 */
	@Override
	public List<PreOrdersDTO> getNewCartList(Long member_id) {
		// 멤버 ID를 이용하여 카트 정보를 가져온다.
		List<CartsDTO> cartList = cartsDAO.selectCartListByMemberId(member_id);
		
		// 값을 보낼 공간을 만든다.
		List<PreOrdersDTO> modifiedList = new ArrayList<PreOrdersDTO>();
		for(CartsDTO cart : cartList) {
			PreOrdersDTO preOrder = new PreOrdersDTO();
			preOrder.setCartInfo(cart);
			modifiedList.add(preOrder);
		}
		
		return modifiedList;
	}

	/**
	 * 회원이 상품 주문을 성공적으로 완료시 
	 * 가지고 있는 장바구니의 모든 상품을 삭제하는 서비스
	 * @param Long member_id (회원의 ID)
	 * @return boolean (성공 여부)
	 */
	@Override
	public boolean removeCarts(Long member_id) {
		int rows = cartsDAO.deleteByMemberId(member_id);
		boolean result = true;
		
		if(rows < 1) {
			result = false;
		}
		
		return result;
	}
	
	/**
	 * 서비스에서 삽입시 성공적으로 트랜잭션이 되었는지
	 * 판단해주는 메서드
	 * @param int rows
	 * @return boolean (성공 여부)
	 */
	public boolean isTransactionSuccess(int rows) {
		boolean result = true;
		
		if(rows != 1) {
			result = false;
		}
		
		return result;
	}

	@Override
	public List<OrdersDTO> getOrdersList(Pager pager) {
		List<OrdersDTO> orders = ordersDAO.selectAllByPage(pager);
		return orders;
	}

	@Override
	public List<OrdersDTO> getOrdersListByOrderId(Pager pager, Long order_id) {
		List<OrdersDTO> orders = ordersDAO.selectByPageAndOrderId(pager, order_id);
		return orders;
	}

	@Override
	public List<OrdersDTO> getOrdersListByDelivery(Pager pager, String delivery) {
		List<OrdersDTO> orders = ordersDAO.selectByPageAndDelivery(pager, delivery);
		return orders;
	}

	@Override
	public Map<String, Object> getOrderInfo(Long order_id) {
		OrdersDTO order = ordersDAO.selectByOrderId(order_id);
		MembersDTO member = membersDAO.selectByMemberId(order.getMember_id());
		List<OrderlistsDTO> orderLists = orderlistsDAO.selectByOrderId(order_id);
		List<MyPageDTO> orderInfoList = new ArrayList<>();
		//List<ProductsDTO> productLists = new ArrayList<>();
		
		for(OrderlistsDTO orderlist : orderLists) {
			ProductsDTO products = productsDAO.selectByProductId(orderlist.getProduct_id());
			MyPageDTO orderInfo = new MyPageDTO();
			orderInfo.setOrderInfo(orderlist);
			orderInfo.setProductsInfo(products);
			orderInfoList.add(orderInfo);
		}
		
		long totalPrice = 0;
		for(MyPageDTO list : orderInfoList) {
			long tempPrice = (long) list.getProduct_quantity() * (long) list.getProduct_price();
			totalPrice += tempPrice;
		}
		
		Map<String, Object> map = new HashMap<>();
		map.put("order", order);
		map.put("member", member);
		map.put("orderInfoList", orderInfoList);
		map.put("totalPrice", totalPrice);
		//map.put("orderLists", orderLists);
		//map.put("productsList", productLists);
		
		return map;
	}

	@Override
	public OrdersDTO modifyOrder(OrdersDTO orderInfo) {
		OrdersDTO order = new OrdersDTO();
		int row = ordersDAO.updateOrders(orderInfo);
		if(row == 1) {
			order = ordersDAO.selectByOrderId(orderInfo.getOrder_id());
		} else {
			logger.info("수정실패");
		}
		return order;
	}

	@Override
	public int getTotalOrdersCount() {
		return ordersDAO.count();
	}

	@Override
	public int getByDeliveryOrdersCount(String order_delivery_status) {
		return ordersDAO.countByDelivery(order_delivery_status);
	}
	
	
	
	
	

}
