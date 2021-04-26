package com.team4.webapp.services;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Base64;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.apache.commons.io.FileUtils;
import org.json.JSONArray;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.collect.Lists;
import com.team4.webapp.dao.ColorsDAO;
import com.team4.webapp.dao.ProductImgsDAO;
import com.team4.webapp.dao.ProductsDAO;
import com.team4.webapp.dao.SizesDAO;
import com.team4.webapp.dao.SubCategoriesDAO;
import com.team4.webapp.dto.CategoriesDTO;
import com.team4.webapp.dto.ColorsDTO;
import com.team4.webapp.dto.ImageSaveDTO;
import com.team4.webapp.dto.Pager;
import com.team4.webapp.dto.ProductDetailsDTO;
import com.team4.webapp.dto.ProductImgCarouselDTO;
import com.team4.webapp.dto.ProductImgDetailDTO;
import com.team4.webapp.dto.ProductImgsDTO;
import com.team4.webapp.dto.ProductsDTO;
import com.team4.webapp.dto.SizesDTO;
import com.team4.webapp.dto.SubCategoriesDTO;

@Service
public class ProductServiceImpl implements IProductService {

	private static final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);

	@Autowired
	private ProductsDAO productDAO;
	@Autowired
	private SubCategoriesDAO subCategoriesDAO;
	@Autowired
	private ProductImgsDAO productImgsDAO;
	@Autowired
	private ColorsDAO colorsDAO;
	@Autowired
	private SizesDAO sizesDAO;

	/**
	 * 서비스 목적 - 모든 사용자들이 모든 상품을 보기 위해 제공하는 서비스 - order = "desc", "high", "low" -
	 * desc : product_id 기준으로 desc 정렬 - high : product_price 기준으로 desc 정렬 - low :
	 * product_price 기준으로 asc 정렬
	 * 
	 * @return List<List<ProductsDTO>> -> Google의 Guava를 사용해서 List Partitioning 해서
	 *         리턴
	 */
	@Override
	public List<List<ProductsDTO>> showAllProduct(String order) {

		List<ProductsDTO> products;

		if (order.equals("desc")) {
			products = productDAO.selectProductsList();
		} else if (order.equals("high")) {
			products = productDAO.selectProdutsListOrderByHighPrice();
		} else {
			products = productDAO.selectProductsListOrderByLowPrice();
		}
		// 상품의 이미지 경로를 설정하기 위한 for문
		for (ProductsDTO product : products) {
			String filePath = "/webapp/image?path=" + product.getProduct_image();
			product.setProduct_image(filePath);
		}

		// 한 row당 4개의 상품을 보여주기 위해 => productLists안에 하나의 리스트 당 상품 4개 담긴 리스트(구아바 라이브러리)
		List<List<ProductsDTO>> productLists = Lists.partition(products, 4);

		return productLists;
	}

	/**
	 * 서비스 목적 - 모든 사용자들이 해당 카테고리의 상품을 보기 위해 제공하는 서비스 - subcategory = "Jacket, Coat,
	 * Cardigan", "Knit, Shirt, Tee", "Pants, Skirt" - order = "desc", "high", "low"
	 * - desc : product_id 기준으로 desc 정렬 - high : product_price 기준으로 desc 정렬 - low :
	 * product_price 기준으로 asc 정렬
	 * 
	 * @return List<ProductsDTO> 상품들의 리스트를 반환
	 */
	@Override
	public List<ProductsDTO> showProductByCategory(String subcategory, String order) {

		SubCategoriesDTO subCategoriesDTO = subCategoriesDAO.selectBySubCategoryName(subcategory);
		Long subCategory_id = subCategoriesDTO.getSubcategory_id();
		List<ProductsDTO> products;

		if (order.equals("desc")) {
			products = productDAO.selectBySubCategoryId(subCategory_id);
		} else if (order.equals("high")) {
			products = productDAO.selectBySubCategoryIdOrderByHighPrice(subCategory_id);
		} else {
			products = productDAO.selectBySubCategoryIdOrderByLowPrice(subCategory_id);
		}

		// 상품의 이미지 경로를 설정하기 위한 for문
		for (ProductsDTO product : products) {
			String filePath = "/webapp/image?path=" + product.getProduct_image();
			product.setProduct_image(filePath);
		}

		return products;
	}

	/**
	 * 서비스 목적 - 모든 사용자들이 상품의 상세 정보를 보기 위해 제공하는 서비스
	 * 
	 * @param Long member_id (상품의 ID)
	 * @return ProductDetailsDTO (상품 상세 정보)
	 */
	@Override
	public ProductDetailsDTO showProductDetail(Long product_id) {

		ProductDetailsDTO productDetailsDTO = new ProductDetailsDTO();
		ProductsDTO productsDTO = new ProductsDTO();

		productsDTO = productDAO.selectByProductId(product_id);
		List<ProductImgCarouselDTO> carouselList = productImgsDAO.selectCarouselImgsByProductId(product_id);
		List<ProductImgDetailDTO> detailList = productImgsDAO.selectDetailImgsByProductId(product_id);
		List<ColorsDTO> colorsList = colorsDAO.selectByProductId(product_id);
		List<SizesDTO> sizesList = sizesDAO.selectByProductId(product_id);

		// 상품의 이미지 경로를 설정하기 위한 for문
		for (ProductImgCarouselDTO carousel : carouselList) {
			carousel.setProduct_img_name("/webapp/image?path=" + carousel.getProduct_img_name());
		}

		for (ProductImgDetailDTO detail : detailList) {
			detail.setProduct_img_name("/webapp/image?path=" + detail.getProduct_img_name());
		}

		String filePath = "/webapp/image?path=" + productsDTO.getProduct_image();
		productDetailsDTO.setProduct_image(filePath);
		productDetailsDTO.setProductDetailsInfo(productsDTO, carouselList, detailList, colorsList, sizesList);

		return productDetailsDTO;
	}

	@Override
	public boolean removeProduct(Long product_id) {
		return false;
	}

	@Override
	public List<ProductsDTO> getProductLists(Pager pager) {
		return productDAO.selectByPage(pager);
	}

	@Override
	public List<ProductsDTO> getProductListsBySubCategory(Pager pager, Long subcategory_id) {
		return productDAO.selectByPageAndSubCategory(pager, subcategory_id);
	}

	@Override
	public int getCount() {
		return productDAO.count();
	}

	@Override
	public Map<String, Object> getAllCategories() {
		Map<String, Object> categoriesList = new HashMap<String, Object>();
		Long[] categoriesId = { (long) 1, (long) 2, (long) 3 };

		for (Long categoryId : categoriesId) {
			List<SubCategoriesDTO> categoryData = subCategoriesDAO.selectByCategoryId(categoryId);
			if (categoryId == 1) {
				categoriesList.put("Outer", categoryData);
			} else if (categoryId == 2) {
				categoriesList.put("Top", categoryData);
			} else {
				categoriesList.put("Bottom", categoryData);
			}
		}

		return categoriesList;
	}

	@Override
	public ProductDetailsDTO getProductDetails(Long product_id) {
		ProductDetailsDTO productDetailInfo = new ProductDetailsDTO();

		// 1. ProductsDTO를 가져온다.
		ProductsDTO productInfo = productDAO.selectByProductId(product_id);
		List<ProductImgCarouselDTO> carouselList = productImgsDAO.selectCarouselImgsByProductId(product_id);
		List<ProductImgDetailDTO> detailList = productImgsDAO.selectDetailImgsByProductId(product_id);
		List<ColorsDTO> colorsList = colorsDAO.selectByProductId(product_id);
		List<SizesDTO> sizesList = sizesDAO.selectByProductId(product_id);
		productDetailInfo.setProductDetailsInfo(productInfo, carouselList, detailList, colorsList, sizesList);

		logger.info(productInfo.getProduct_image());
		return productDetailInfo;
	}

	@Override
	public int getSpecificCount(Long subcategory_id) {
		return productDAO.specificCount(subcategory_id);
	}

	@Override
	public Long getSequence() {
		return productDAO.getSequence();
	}

	@Override
	public String uploadMainImage(MultipartFile uploadFile) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean addProduct(ProductsDTO product, JSONArray colors, JSONArray sizes) {
		boolean result = true;
		// Get Product ID
		Long productID = product.getProduct_id();
		logger.info(product.toString());
		// Insert Product Data
		
		int row = productDAO.insertProducts(product);

		if (row != 1) {
			result = false;
		}
		// Colors
		for (int index = 0; index < colors.length(); index++) {
			String colorName = (String) colors.get(index);
			ColorsDTO color = new ColorsDTO();
			color.setColor_name(colorName);
			color.setProduct_id(productID);
			logger.info(color.toString());
			int rows = colorsDAO.insertColor(color);
			if (rows < 1) {
				result = false;
			}
		}

		for (int index = 0; index < sizes.length(); index++) {
			String sizeName = (String) sizes.get(index);
			SizesDTO size = new SizesDTO();
			size.setSize_name(sizeName);
			size.setProduct_id(productID);
			logger.info(size.toString());
			int rows = sizesDAO.insertSizes(size);
			if (rows < 1) {
				result = false;
			}

		}

		return result;
	}

	@Override
	public ImageSaveDTO uploadImage(ImageSaveDTO data) {
		String[] base64Str = data.getBase64().split(",");
		byte[] decodedBytes = Base64.getDecoder().decode(base64Str[1]);
		String defaultPath = System.getProperty("user.home") + "/images";
		String filePath = "";
		if (data.getType().equals("carousel")) {
			filePath = "/carousel/" + data.getFilename();
		} else if (data.getType().equals("detail")) {
			filePath = "/detail/" + data.getFilename();
		}
		try {
			FileUtils.writeByteArrayToFile(new File(defaultPath + filePath), decodedBytes);
			
			ProductImgsDTO productImg = new ProductImgsDTO();
			productImg.setProduct_id(data.getProduct_id());
			productImg.setProduct_img_type("image/jpeg");
			productImg.setProduct_img_name(filePath);
			productImg.setProduct_img_category(data.getType());
			
			productImgsDAO.insertProductImg(productImg);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		data.setBase64("");
		return data;
	}

}
