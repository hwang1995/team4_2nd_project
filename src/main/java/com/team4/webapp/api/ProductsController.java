package com.team4.webapp.api;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.webapp.dto.ImageSaveDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.ProductDetailsDTO;
import com.team4.webapp.dto.ProductsDTO;
import com.team4.webapp.services.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductsController {

	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);

	@Autowired
	private ProductServiceImpl productService;
	
	/**
	 * 상품의 리스트를 제공하기 위한 컨트롤러
	 * @param int pageNo
	 * @param Long subcategoryId
	 * @return Map<String, Object>
	 */
	@GetMapping("")
	public Map<String, Object> getProductList(
			@RequestParam(defaultValue = "1") int pageNo, 
			Long subcategoryId) {
		
		// 상품의 리스트, 페이저 객체를 초기화한다.
		List<ProductsDTO> productList = null;
		Pager pager = null;

		// 페이저를 구현하기 위해 총 행수를 받아온다.
		int totalRows = productService.getCount();
		
		// 만약 subcategoryId가 없다면? 모든 상품의 리스트를 반환한다.
		if (subcategoryId == null) {
			pager = new Pager(10, 5, totalRows, pageNo);
			productList = productService.getProductLists(pager);
		} else {
			// 존재한다면? 카테고리의 해당하는 상품의 리스트를 반환한다.
			int specificRows = productService.getSpecificCount(subcategoryId);
			pager = new Pager(10, 5, specificRows, pageNo);
			productList = productService.getProductListsBySubCategory(pager, subcategoryId);
		}
		
		// 카테고리의 정보를 가져온다.
		Map<String, Object> categoryList = productService.getAllCategories();

		// 클라이언트로 객체를 반환하기 위해 Map 객체를 선언한다.
		Map<String, Object> map = new HashMap<String, Object>();

		// Map 객체에 pager, category, product를 넣어주고 반환한다.
		map.put("pager", pager);
		map.put("category", categoryList);
		map.put("products", productList);

		return map;
	}

	/**
	 * 상품의 카테고리 목록을 제공하기 위한 컨트롤러
	 * @return Map<String, Object>
	 */
	@GetMapping("/categories")
	public Map<String, Object> getCategories() {
		Map<String, Object> categoryList = productService.getAllCategories();
		
		return categoryList;
	}

	/**
	 * 상품의 캐러셀, 디테일 이미지를 업로드 하기 위해 제공하는 컨트롤러
	 * @param ImageSaveDTO data
	 * @return ImageSaveDTO (ignore base64Data)
	 */
	@PostMapping("/upload")
	public ImageSaveDTO uploadImage(@RequestBody ImageSaveDTO data) {
		ImageSaveDTO returnData = productService.uploadImage(data);

		return returnData;

	}

	/**
	 * 상품의 메인 이미지를 업로드 하기 위해 제공하는 컨트롤러
	 * @param MultipartFile uploadFile
	 * @return Map<String, String>
	 */
	@PostMapping("/upload/main")
	public Map<String, String> uploadMainImage(@RequestParam MultipartFile uploadFile) {
		Map<String, String> map = new HashMap<String, String>();
		
		String result = productService.uploadMainImage(uploadFile);
		
		map.put("product_image", result);
		
		return map;
	}
	
	/**
	 * 상품의 시퀀스를 제공하기 위한 컨트롤러
	 * @return Map<String, Long>
	 */
	@GetMapping("/sequence")
	public Map<String, Long> getSequence() {
		Map<String, Long> map = new HashMap<String, Long>();
		
		map.put("product_id", productService.getSequence());
		
		return map;
	}
	
	/**
	 * 상품 추가를 하기 위해 제공하는 컨트롤러
	 * @param String receivedData
	 * @return Map<String, String>
	 * @throws JsonMappingException
	 * @throws JsonProcessingException
	 */
	@PostMapping("")
	public Map<String, String> addProduct(@RequestBody String receivedData)
			throws JsonMappingException, JsonProcessingException {
		
		// 문자열을 JSON 객체로 파싱한다.
		JSONObject jsonObj = new JSONObject(receivedData);
		
		// 자동으로 해당하는 DTO와 매핑이 되지 않기때문에 ObjectMapper를 선언
		ObjectMapper objectMapper = new ObjectMapper();

		// 상품 객체와 색상 배열, 사이즈 배열을 파싱
		JSONObject parseProduct = jsonObj.getJSONObject("products");
		JSONArray parseColors = jsonObj.getJSONArray("colors");
		JSONArray parseSizes = jsonObj.getJSONArray("sizes");
		
		// parseProduct에 들어있는 JSON 객체를 ProductsDTO 객체로 매핑
		ProductsDTO product = objectMapper.readValue(parseProduct.toString(), ProductsDTO.class);

		// 상품 추가시에 필요한 상품 객체, 색상 배열, 사이즈 배열을 넣고 결과 값을 반환 받는다.
		boolean result = productService.addProduct(product, parseColors, parseSizes);

		Map<String, String> map = new HashMap<String, String>();
		if (result) {
			map.put("result", "success");
		} else {
			map.put("result", "success");
		}

		return map;

	}

	/**
	 * 상품 상세 정보를 제공하기 위한 컨트롤러
	 * @param String product_id
	 * @return ProductDetailsDTO
	 */
	@GetMapping("/{product_id}")
	public ProductDetailsDTO getProduct(@PathVariable String product_id) {

		try {
			Long convertProductId = Long.parseLong(product_id);
			ProductDetailsDTO productDetailInfo = productService.getProductDetails(convertProductId);
			return productDetailInfo;
		} catch (NumberFormatException e) {
			return null;
		}

	}

	

	/**
	 * 상품 정보를 수정하기 위해 제공하는 컨트롤러
	 * @param ProductsDTO product
	 * @return ProductsDTO
	 */
	@PutMapping("")
	public ProductsDTO modifyProduct(@RequestBody ProductsDTO product) {
		ProductsDTO result = productService.updateProductInfo(product);
		return result;

	}

	/**
	 * 상품을 삭제하기 위해 제공하는 컨트롤러
	 * @param Long product_id
	 */
	@PutMapping("/{product_id}")
	public void deleteProduct(@PathVariable Long product_id) {
		productService.removeProduct(product_id);

	}


}
