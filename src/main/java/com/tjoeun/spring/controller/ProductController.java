package com.tjoeun.spring.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.tjoeun.spring.dto.ProductDTO;
import com.tjoeun.spring.service.ProductService;


//상품게시판 역할 
@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	//상품전체리스트 
	@GetMapping("/product_by_category")
	public String product_by_category(@RequestParam("category_idx") int category_idx, Model model) {
		model.addAttribute("category_idx", category_idx); 
		
		List<ProductDTO> productListByCategory = productService.getProductListByCategory(category_idx);
		model.addAttribute("productListByCategory", productListByCategory);	
		
		return "product/product_by_category";
	}
	
	//상품상세보기(게시글 읽기)
	@GetMapping("/product_detail")
	public String product_detail(@RequestParam("product_idx") int product_idx, Model model) {
		
		model.addAttribute("product_idx", product_idx); 
		
		ProductDTO productDetail = productService.getProductDetail(product_idx);
		model.addAttribute("productDetail", productDetail);	
		
		return "product/product_detail";
	}
	
	//상품삭제
	@GetMapping("/delete")
	public String delete(
			int product_idx, 
			@RequestParam("category_idx") int category_idx, 
			Model model) {
		
		model.addAttribute("category_idx",category_idx);
		
		productService.delete(product_idx);
		
		return "redirect:/product/product_by_category?category_idx={category_idx}";
	}
	
	
	
	
	//상품을 등록하는 그 페이지(글쓰기 페이지)
	@GetMapping("/upload")
	public String upload(
	@ModelAttribute("newProductDTO") ProductDTO newProductDTO) {
		

		return "product/upload"; 
	}
	
	//상품등록(사진도 등록하기)
	@PostMapping("/upload_proc")
	public String addProduct
	(@Valid @ModelAttribute("newProductDTO") ProductDTO newProductDTO, BindingResult result, 
	MultipartFile product_image_file){
	
		if(result.hasErrors()) {
			return "product/upload";  
			}
	
		
		productService.addProduct(newProductDTO); 
			
		return "redirect:/product/product_by_category?category_idx="+newProductDTO.getCategory_idx(); 
	}
	
	

	//수정하기
	@GetMapping("/modify")
	public String modify
	(@RequestParam("category_idx") int category_idx,
	 @RequestParam("product_idx") int product_idx, 
	 @ModelAttribute("modifyProductDTO") ProductDTO modifyProductDTO, 
	 Model model) {
		
		model.addAttribute("category_idx", category_idx);
		model.addAttribute("product_idx", product_idx);

		ProductDTO fromDBProductDTO = productService.getProductDetail(product_idx); //수정하려는 상품정보
		
		//수정하는 그 과정
		modifyProductDTO.setProduct_name(fromDBProductDTO.getProduct_name()); 
		modifyProductDTO.setProduct_price(fromDBProductDTO.getProduct_price());
		modifyProductDTO.setStorage_method(fromDBProductDTO.getStorage_method());
		modifyProductDTO.setExpiration_date(fromDBProductDTO.getExpiration_date());
		modifyProductDTO.setProduct_img(fromDBProductDTO.getProduct_img());
		modifyProductDTO.setCategory_idx(category_idx);

			return "product/modify";
	}
	
	@PostMapping("/modify_proc")
	public String modifyProc
	(@Valid @ModelAttribute("modifyProductDTO") ProductDTO modifyProductDTO, BindingResult result,
	Model model) {
		
		if(result.hasErrors()) {
			
			return "product/modify";
		}
		productService.modify(modifyProductDTO); 
		
		return "redirect:/product/product_by_category?category_idx="+modifyProductDTO.getCategory_idx();
	}
	
	
	

	
	
	
	
		
}
