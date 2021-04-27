package com.team4.webapp.services;

import java.io.File;
import java.util.Base64;
import java.util.HashMap;
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

	/**
	 * 상품의 총 갯수를 반환하는 서비스
	 * @return int
	 */
	@Override
	public int getCount() {
		return productDAO.count();
	}

	/**
	 * 상품의 상세 카테고리의 총 갯수를 반환하는 서비스
	 * @param Long subcategory_id
	 * @return int
	 */
	@Override
	public int getSpecificCount(Long subcategory_id) {
		return productDAO.specificCount(subcategory_id);
	}

	/**
	 * 상품의 리스트를 반환하는 서비스
	 * @param Pager pager
	 * @return List<ProductsDTO>
	 */
	@Override
	public List<ProductsDTO> getProductLists(Pager pager) {
		return productDAO.selectByPage(pager);
	}

	/**
	 * 상품의 상세 카테고리의 해당하는 리스트를 반환하는 서비스
	 * @param Pager pager
	 * @param Long subcategory_id
	 * @return List<ProductsDTO>
	 */
	@Override
	public List<ProductsDTO> getProductListsBySubCategory(Pager pager, Long subcategory_id) {
		return productDAO.selectByPageAndSubCategory(pager, subcategory_id);
	}

	/**
	 * 상품의 모든 카테고리를 가져와 반환하는 서비스
	 * @return Map<String, Object>
	 */
	@Override
	public Map<String, Object> getAllCategories() {
		Map<String, Object> categoriesList = new HashMap<String, Object>();
		
		// 카테고리 1 - Outer, 2 - Top, 3 - Bottom
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

	/**
	 * 상품의 시퀀스 번호를 반환하는 서비스
	 * @return Long
	 */
	@Override
	public Long getSequence() {
		return productDAO.getSequence();
	}
	
	/**
	 * 상품의 메인 이미지를 업로드 하기 위한 서비스
	 * @param MultipartFile uploadFile
	 * @return String returnFile
	 */
	@Override
	public String uploadMainImage(MultipartFile uploadFile) {
		// 파일의 경로와 카테고리 폴더, UUID 생성을 한다.
		String filePath = System.getProperty("user.home") + "/images";
		String categoryFolder = "/main/";
		String uuid = UUID.randomUUID().toString();
		String returnFile = null;
		
		try {
			String[] fileFragments = uploadFile.getOriginalFilename().split("\\.");
			String ext = fileFragments[fileFragments.length - 1];
			returnFile = categoryFolder + uuid + "." + ext;
			File file = new File(filePath + returnFile); 
			uploadFile.transferTo(file);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return returnFile;
	}

	/**
	 * 상품을 추가하기 위해 제공하는 서비스
	 * @param ProductsDTO product
	 * @param JSONArray colors
	 * @param JSONArray sizes
	 * @return boolean
	 */
	@Override
	public boolean addProduct(ProductsDTO product, JSONArray colors, JSONArray sizes) {
		boolean result = true;
		// ProductsDTO에 있는 상품 번호를 가져다가 productID로 세팅
		Long productID = product.getProduct_id();
		logger.info(product.toString());

		// 상품을 삽입한다.
		int row = productDAO.insertProducts(product);

		// 만약 상품 삽입시에 영향받은 행의 수가 1이 아니면? false
		if (row != 1) {
			result = false;
		}
		
		// 색상 배열을 받아서 넣어주는 로직
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
		
		// 크기 배열을 받아서 넣어주는 로직
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

	/**
	 * 상품의 이미지 (캐러셀, 디테일) 이미지를 업로드하기 위한 서비스
	 * @param ImageSaveDTO data
	 * @return ImageSaveDTO (ignore base64Data)
	 */
	@Override
	public ImageSaveDTO uploadImage(ImageSaveDTO data) {
		// base64에 붙어있는 파일 정보들을 때어서 저장하기 위해 선언
		String[] base64Str = data.getBase64().split(",");
		
		// base64로 인코딩되어 있는 데이터를 디코딩하여 byte[]로 받음
		byte[] decodedBytes = Base64.getDecoder().decode(base64Str[1]);
		
		String defaultPath = System.getProperty("user.home") + "/images";
		String filePath = "";
		
		// JSON에서 받은 type의 정보에 따라 파일 경로가 달라짐.
		if (data.getType().equals("carousel")) {
			filePath = "/carousel/" + data.getFilename();
		} else if (data.getType().equals("detail")) {
			filePath = "/detail/" + data.getFilename();
		}
		try {
			// 지정된 경로에 byte 배열로 받은 이미지를 만들어준다.
			FileUtils.writeByteArrayToFile(new File(defaultPath + filePath), decodedBytes);
			
			// 상품 이미지의 정보를 보내기 위해 DTO와 정보를 넣어준다.
			ProductImgsDTO productImg = new ProductImgsDTO();
			productImg.setProduct_id(data.getProduct_id());
			productImg.setProduct_img_type("image/jpeg");
			productImg.setProduct_img_name(filePath);
			productImg.setProduct_img_category(data.getType());
			
			// 테이블의 정보를 삽입하는 과정을 거친다.
			productImgsDAO.insertProductImg(productImg);
			
		} catch(Exception e) {
			e.printStackTrace();
		}
		
		// 모든 작업이 끝났다면 base64 인코딩 스트링이 필요 없으므로 빈칸으로 set
		data.setBase64("");
		
		return data;
	}
	
	/**
	 * 상품 상세 정보를 반환하는 서비스
	 * @param Long product_id
	 * @return ProductDetailsDTO
	 */
	@Override
	public ProductDetailsDTO getProductDetails(Long product_id) {
		// ProductDetailsDTO를 만들어 보내기 위해 객체 생성
		ProductDetailsDTO productDetailInfo = new ProductDetailsDTO();
		try {
			// JSON에서 받아온 product_id로 상품 정보와, 이미지 정보, 색상 정보, 크기 정보등을 받아온다.
			ProductsDTO productInfo = productDAO.selectByProductId(product_id);
			List<ProductImgCarouselDTO> carouselList = productImgsDAO.selectCarouselImgsByProductId(product_id);
			List<ProductImgDetailDTO> detailList = productImgsDAO.selectDetailImgsByProductId(product_id);
			List<ColorsDTO> colorsList = colorsDAO.selectByProductId(product_id);
			List<SizesDTO> sizesList = sizesDAO.selectByProductId(product_id);
			
			// ProductDetailsDTO에 받아온 정보들을 넣어주고 보내준다.
			productDetailInfo.setProductDetailsInfo(productInfo, carouselList, detailList, colorsList, sizesList);

			return productDetailInfo;
		} catch (NullPointerException e) {
			return null;
		}

	}
	
	/**
	 * 상품 정보를 업데이트 하기 위한 서비스
	 * @param ProductsDTO
	 * @return ProductsDTO
	 */
	@Override
	public ProductsDTO updateProductInfo(ProductsDTO product) {
		productDAO.updateProducts(product);
		
		return product;
	}
	
	/**
	 * 상품을 삭제하기 위한 서비스
	 * @param Long product_id
	 * @return boolean
	 */
	@Override
	public boolean removeProduct(Long product_id) {
		int rows = productDAO.deleteProduct(product_id);
		
		if(rows != 1) {
			return false;
		}
		
		return true;
	}

}
