package com.team4.webapp.api;

import java.io.File;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.team4.webapp.dto.ImageSaveDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.ProductDetailsDTO;
import com.team4.webapp.dto.ProductImgsDTO;
import com.team4.webapp.dto.ProductsDTO;
import com.team4.webapp.services.ProductServiceImpl;

@RestController
@RequestMapping("/api/products")
public class ProductsController {
	
	private static final Logger logger = LoggerFactory.getLogger(ProductsController.class);
	
	@Autowired
	private ProductServiceImpl productService;
	
	@GetMapping("")
	public Map<String, Object> getProductList(@RequestParam(defaultValue = "1") int pageNo, Long subcategoryId ){
		List<ProductsDTO> productList = null;
		int totalRows = productService.getCount();
		
		Pager pager = null;
		
		
		if(subcategoryId == null) {
			pager = new Pager(5,5, totalRows, pageNo);
			productList = productService.getProductLists(pager);
		} else {
			int specificRows = productService.getSpecificCount(subcategoryId);
			pager = new Pager(5,5, specificRows, pageNo);
			logger.info(specificRows + "");
			productList = productService.getProductListsBySubCategory(pager, subcategoryId);
		}
		Map<String, Object> categoryList = productService.getAllCategories();
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("pager", pager);
		map.put("category", categoryList);
		map.put("products", productList);
		
		
		
		return map;
	}
	
	@GetMapping("/categories")
	public Map<String, Object> getCategories(){
		Map<String, Object> categoryList = productService.getAllCategories();
		return categoryList;
	}

	
	@GetMapping("/{product_id}")
	public ProductDetailsDTO getProduct(@PathVariable Long product_id) {
		ProductDetailsDTO productDetailInfo = productService.getProductDetails(product_id);
		return productDetailInfo;
	}
	

	
	@PostMapping("")
	public Map<String, String> addProduct(@RequestBody String receivedData) throws JsonMappingException, JsonProcessingException{
		
		JSONObject jsonObj = new JSONObject(receivedData);
		ObjectMapper objectMapper = new ObjectMapper();
		
		JSONObject parseProduct = jsonObj.getJSONObject("products");
		JSONArray parseColors = jsonObj.getJSONArray("colors");
		JSONArray parseSizes = jsonObj.getJSONArray("sizes");
		
		ProductsDTO product = objectMapper.readValue(parseProduct.toString(), ProductsDTO.class);
		logger.info(product.toString());
		
		boolean result = productService.addProduct(product, parseColors, parseSizes);
		
		Map<String, String> map = new HashMap<String, String>();
		if(result) {
			map.put("result", "success");
		} else {
			map.put("result", "success");
		}

		return map;
		
	}
	
	@PutMapping("")
	public void modifyProduct(@RequestBody Map<String, Object> productInfo) {
		
	}
	
	@DeleteMapping("/{product_id}")
	public void deleteProduct(@PathVariable Long product_id) {
		
	}
	
	@PostMapping("/upload")
	public ImageSaveDTO uploadImage(@RequestBody ImageSaveDTO data) {
		
		ImageSaveDTO returnData = productService.uploadImage(data);
		logger.info(data.toString());

		return returnData;

	}
	
	@PostMapping("/upload/main")
	public Map<String, String> uploadMainImage(@RequestParam MultipartFile uploadFile){
		Map<String, String> map = new HashMap<String, String>();
		String filePath = System.getProperty("user.home") + "/images";
		String categoryFolder = "/main/";
		String uuid = UUID.randomUUID().toString();
		String returnFile = null;
		// 서비스로 옮길 예정 우선 테스트
		try {
			String[] fileFragments = uploadFile.getOriginalFilename().split("\\.");
			String ext = fileFragments[fileFragments.length - 1];
			returnFile = categoryFolder + uuid + "." + ext;
			File file = new File(filePath + categoryFolder + uuid + "." + ext);
			uploadFile.transferTo(file);
		} catch(Exception e) {
			e.printStackTrace();
		}
		map.put("product_image", returnFile);
		return map;
	}
	
	@GetMapping("/sequence")
	public Map<String, Long> getSequence() {
		Map<String, Long> map = new HashMap<String, Long>();
		map.put("product_id", productService.getSequence());
		return map;
	}
	


}
