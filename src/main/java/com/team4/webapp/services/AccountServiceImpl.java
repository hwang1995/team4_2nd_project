package com.team4.webapp.services;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.team4.webapp.dao.MembersDAO;
import com.team4.webapp.dao.OrderlistsDAO;
import com.team4.webapp.dao.OrdersDAO;
import com.team4.webapp.dao.ProductsDAO;
import com.team4.webapp.dto.MembersDTO;
import com.team4.webapp.dto.MyPageDTO;
import com.team4.webapp.dto.MyPageListDTO;
import com.team4.webapp.dto.OrderlistsDTO;
import com.team4.webapp.dto.OrdersDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.ProductsDTO;

@Service
public class AccountServiceImpl implements IAccountService {
	private static final Logger logger = LoggerFactory.getLogger(AccountServiceImpl.class);
	
	@Autowired
	private OrdersDAO ordersDAO;
	@Autowired
	private OrderlistsDAO orderlistsDAO;
	@Autowired
	private ProductsDAO productsDAO;
	@Autowired
	private MembersDAO membersDAO;

	/**
	 * 서비스 목적
	 * - 회원의 정보를 보여주기 위한 서비스
	 * - 회원 자신이 주문한 최근 내역 (상품 리스트(상품, 주문기록))을 보여주기 위한 서비스
	 * @return MyPageListDTO (주문 정보와 상품 정보의 리스트)
	 */
	@Override
	public List<MyPageListDTO> showMyInfo(Pager pager) {
		List<MyPageListDTO> mypageLists = new ArrayList<>();
		List<OrdersDTO> orders = ordersDAO.selectByPage(pager);
		
		//1.MyPageListDTO는 주문번호, 주문날짜, MyPageDTO 리스트를 필드로 가지고 있음.
		//2.주문번호와 주문날짜를 MyPageListDTO에 세팅해줌.
		//3.주문번호로 주문리스트 정보를 받아옴.
		for(OrdersDTO order : orders) {
			List<MyPageDTO> mypageList = new ArrayList<MyPageDTO>();
			MyPageListDTO mypageInfoList = new MyPageListDTO();
			mypageInfoList.setOrder_id(order.getOrder_id());
			mypageInfoList.setOrder_date(order.getOrder_date());
			List<OrderlistsDTO> orderlists = orderlistsDAO.selectByOrderId(order.getOrder_id());
			
			//1.MyPageDTO는 OrderList와 Product에 대한 정보를 가지고 있음.
			//2.주문리스트의 상품정보를 받아옴.
			//3.MyPageDTO에 OrderList와 Product 정보를 세팅해줌.
			//4.DB에 저장되어 있는 이미지명을 새로운 Path로 설정해줌.
			//5.MyPageDTO를 리스트로 추가해줌.
			for(OrderlistsDTO orderlist : orderlists) {
				ProductsDTO products = productsDAO.selectByProductId(orderlist.getProduct_id());
				MyPageDTO mypage = new MyPageDTO();
				mypage.setOrderInfo(orderlist);
				mypage.setProductsInfo(products);
				String filePath = "/webapp/image?path="+ mypage.getProduct_image();
				mypage.setProduct_image(filePath);
				mypageList.add(mypage);
			}
			
			//1.MyPageDTO List를 MyPageListDTO에 세팅해줌.
			//2.MyPageListDTO를 리스트로 추가해줌.
			mypageInfoList.setMyPageList(mypageList);
			mypageLists.add(mypageInfoList);
		}
		
		return mypageLists;
	}
	/**
	 * 서비스 목적
	 * - 회원이 자신이 주문한 상세 내역 (주문자, 수취인, 상품 리스트(상품, 주문기록))을 보여주기 위한 서비스
	 * - 컨트롤러에 MyPageDTO 리스트를 전달해 줘야 한다.
	 * @return MyPageDTO (상품 정보)
	 */
	@Override
	public List<MyPageDTO> showMyOrderInfo(Long order_id) {
		List<MyPageDTO> orderInfoList = new ArrayList<>();
		List<OrderlistsDTO> orderLists = orderlistsDAO.selectByOrderId(order_id);
		
		//1.주문번호로 주문리스트 정보를 받아옴.
		//2.주문리스트의 상품정보를 받아옴.
		//3.MyPageDTO에 OrderList와 Product 정보를 세팅해줌.
		//4.DB에 저장되어 있는 이미지명을 새로운 Path로 설정해줌.
		//5.MyPageDTO를 리스트로 추가해줌.
		for(OrderlistsDTO orderlist : orderLists) {
			ProductsDTO products = productsDAO.selectByProductId(orderlist.getProduct_id());
			MyPageDTO orderInfo = new MyPageDTO();
			orderInfo.setOrderInfo(orderlist);
			orderInfo.setProductsInfo(products);
			String filePath = "/webapp/image?path="+ orderInfo.getProduct_image();
			orderInfo.setProduct_image(filePath);
			orderInfoList.add(orderInfo);
		}
		
		return orderInfoList;
	}
	/**
	 * 서비스 목적
	 * - 주문번호의 주문정보를 (수취인 정보, 결제현황, 배송현황)을 보여주기 위한 서비스
	 * - 컨트롤러에 OrdersDTO를 전달해 줘야 한다.
	 * @return OrdersDTO (주문 정보, 은행, 주문자 정보)
	 */
	@Override
	public OrdersDTO findOrderbyOrderId(Long order_id) {
		//1.주문번호로 주문정보를 가져옴.
		//2.주문정보의 결제현황과 배송현황 상태를 비교하여 해당정보로 세팅해줌.
		OrdersDTO order = ordersDAO.selectByOrderId(order_id);
		if(order.getOrder_payment_status().equals("PAYMENT_FINISHED")) {
			order.setOrder_payment_status("결제 완료");
		}
		if(order.getOrder_delivery_status().equals("DELIVERY_PENDING")) {
			order.setOrder_delivery_status("배송 준비중");
		}
		
		return order;
	}
	/**
	 * 서비스 목적
	 * - 회원이 자신의 정보 (비밀번호, 전화번호, 주소)를 바꾸기 위한 서비스
	 * - 컨트롤러에 영향받은 행의 수를 반환해야 한다.
	 * @return 행 수 (정상적으로 되었는지 확인하기 위해)
	 */
	@Override
	public int editMyInfo(MembersDTO member) {
		//1.받아온 비밀번호가 공백이 아니라면, 해당 비밀번호를 암호화 하여 업데이트 해줌.
		//2.받아온 비밀번호가 공백이라면, DB에서 회원의 원래 비밀번호를 가져와서 다시 업데이트 해줌.
		if(!member.getMember_pw().equals("")) {
			PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
			String encodedPassword = passwordEncoder.encode(member.getMember_pw());
			member.setMember_pw(encodedPassword);
		} else {
			MembersDTO memberInfo =  membersDAO.selectByEmailId(member.getMember_email());
			member.setMember_pw(memberInfo.getMember_pw());
		}
		int row = membersDAO.updateMembers(member);
		
		return row;
	}
	
	/**
	 * 회원의 총 주문의 행 수를 반환하는 메서드
	 * @return rows (행 수)
	 */
	@Override
	public int getTotalOrderRows(Long member_id) {
		int rows = ordersDAO.countOrders(member_id);
		return rows;
	}
	
	/**
	 * 관리자가 페이저로 전체 회원 리스트를 받기 위해 제공하는 서비스
	 * @param Pager pager
	 * @return List<MembersDTO>
	 */
	@Override
	public List<MembersDTO> getAccountsList(Pager pager) {
		List<MembersDTO>list = membersDAO.selectByPage(pager);
		return list;
	}
	
	/**
	 * 관리자가 이메일 주소 + 페이저로 회원 리스트를 받기 위해 제공하는 서비스
	 * @param Pager pager
	 * @param String email
	 * @return List<MembersDTO>
	 */
	@Override
	public List<MembersDTO> getAccountsListByEmail(Pager pager, String email) {
		List<MembersDTO>list = membersDAO.selectByPageAndEmail(pager,email);
		return list;
	}
	
	/**
	 * 관리자가 이름 + 페이저로 회원 리스트를 받기 위해 제공하는 서비스
	 * @param pager
	 * @param name
	 * @return List<MembersDTO>
	 */
	@Override
	public List<MembersDTO> getAccountsListByName(Pager pager, String name) {
		List<MembersDTO>list = membersDAO.selectByPageAndName(pager,name);
		return list;
	}
	
	/**
	 * 회원 정보를 수정하기 위해 제공하는 서비스
	 * @param MembersDTO memberInfo
	 * @return boolean
	 */
	@Override
	public boolean modifyAccount(MembersDTO memberInfo) {
		int row = membersDAO.updateMembersByAdmin(memberInfo);
		if(row != 0) {
			return true;
		}else {
			return false;
		}
	}
	/**
	 * 회원을 삭제하기 위해 제공하는 서비스
	 * @param Long member_id
	 * @return boolean
	 */
	@Override
	public boolean deleteAccount(Long member_id) {
		int row = membersDAO.deleteByMemberId(member_id);
		if(row != 0) {
			return true;
		}else {
			return false;
		}
	}
	
	/**
	 * 페이징에 필요한 회원 전체 행의 count를 구하기 위해 제공하는 서비스
	 * @return int (카운트 값을 얻기 위해)
	 */
	@Override
	public int getTotalMemberRows() {
		int totalRow = membersDAO.count();
		return totalRow;
	}
	
	/**
	 * 페이징에서 이메일로 검색한 회원 행의 count를 구하기 위해 제공하는 서비스
	 * @param String email
	 * @return int (카운트 값을 얻기 위해)
	 */
	@Override
	public int getMemberRowsByEmail(String email) {
		int countByEmail = membersDAO.getCountByEmail(email);
		return countByEmail;
	}
	
	/**
	 * 페이징에 이름으로 검색한 회원 행의 count를 구하기 위해 제공하는 서비스
	 * @param String name
	 * @return int (카운트 값을 얻기 위해)
	 */
	@Override
	public int getMemberRowsByName(String name) {
		int countByName = membersDAO.getCountByName(name);
		return countByName;
	}
}
