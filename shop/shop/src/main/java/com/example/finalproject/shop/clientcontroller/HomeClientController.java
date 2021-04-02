package com.example.finalproject.shop.clientcontroller;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.finalproject.shop.model.AdminUserDTO;
import com.example.finalproject.shop.model.CategoriesDTO;
import com.example.finalproject.shop.model.CouponDTO;
import com.example.finalproject.shop.model.OrderDTO;
import com.example.finalproject.shop.model.OrderDetailDTO;
import com.example.finalproject.shop.model.ProductDTO;
import com.example.finalproject.shop.model.SubCategoriesDTO;
import com.example.finalproject.shop.service.AdminUserService;
import com.example.finalproject.shop.service.CategoriesService;
import com.example.finalproject.shop.service.CouponService;
import com.example.finalproject.shop.service.MailService;
import com.example.finalproject.shop.service.OrderDetailService;
import com.example.finalproject.shop.service.OrderService;
import com.example.finalproject.shop.service.ProductService;
import com.example.finalproject.shop.service.SubCategoriesService;
import com.lowagie.text.DocumentException;


@Controller
@RequestMapping("/client")
public class HomeClientController {
	@Autowired
	private ProductService productService;
	@Autowired
	private CategoriesService categoriesService;
	@Autowired
	private SubCategoriesService subCategoriesService;
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private MailService mailService;
	@Autowired
	private OrderDetailService orderDetailService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private CouponService couponService;
	
	@GetMapping("/productdetail/{id}")
	public String productDetail(HttpServletRequest request, @PathVariable(name = "id")int id) {
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
	
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		
		ProductDTO productDTO = productService.getById(id);
		request.setAttribute("product", productDTO);
		int x = productDTO.getId_categories();
		request.setAttribute("x", x);
		List<ProductDTO> productDTOs = productService.getAll();
		request.setAttribute("productlist", productDTOs);
		return "user/productdetail";
	}
	
	@GetMapping("/product/list/{id_categories}")
	public String productCategories(HttpServletRequest request, @PathVariable(name="id_categories")int id_categories) {
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
		CategoriesDTO categoriesDTO = categoriesService.getListProduct(id_categories);
		
		request.setAttribute("listproduct", categoriesDTO.getProductDTOs());
		
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		return "user/product-grid";
	}
	@GetMapping("/productlist/{id_subcategories}")
	public String productSubcate(HttpServletRequest request, @PathVariable(name="id_subcategories")int id_subcategories) {
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
		
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		
		SubCategoriesDTO subCategoriesDTO = subCategoriesService.getListProduct(id_subcategories);
		request.setAttribute("listproductsub", subCategoriesDTO.getProductDTOs());
		return "user/product-grid";
	}
	
	@GetMapping("/cart")
	public String shopCart(HttpServletRequest request, HttpSession httpSession, @RequestParam(name ="b", required = false) String b, @RequestParam(name ="c", required = false) String c) {
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
		
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		
		Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) httpSession.getAttribute("cart");
		
		if(c != null) {
			request.setAttribute("c", c);
		}
		if(b != null) {
			request.setAttribute("b", b);
		}
		
		request.setAttribute("cart", map);
		
		/*
		 * CouponDTO couponDTO = (CouponDTO) httpSession.getAttribute("coupon");
		 * 
		 * if(couponDTO != null) { request.setAttribute("coupon", couponDTO); }
		 */
		float total =  0;
		
		if(map != null) {
			for(Map.Entry<Integer, OrderDTO> entry : map.entrySet()) {
				total += entry.getValue().getQty() * entry.getValue().getPrice();
			}
		}
		
		httpSession.setAttribute("total", total);
		
		return "user/cart";
	}
	
	@PostMapping("/add-to-cart")
	public String cart(@RequestParam("id") int id,@RequestParam("quantityBill") int quantityBill, HttpSession httpSession, HttpServletRequest request) {
		ProductDTO productDTO = productService.getById(id);
		if(quantityBill > productDTO.getQty()) {
			return "redirect:/client/productdetail?id=" + id + "&e=error" ;
		}else {
			Object object = httpSession.getAttribute("cart");
			if(object == null) {
				Map<Integer, OrderDTO> map = new HashMap<Integer, OrderDTO>();
				
				OrderDTO orderDTO = new OrderDTO();
				
				orderDTO.setProductDTO(productDTO);
				orderDTO.setQty(quantityBill);
				orderDTO.setPrice(productDTO.getPrice());
				
				map.put(productDTO.getId(), orderDTO);
				
				httpSession.setAttribute("cart", map);
			}else{
				Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) object;
				
				OrderDTO orderDTO = map.get(id);
				
				if(orderDTO == null) {
					orderDTO = new OrderDTO();
					
					orderDTO.setProductDTO(productDTO);
					orderDTO.setQty(quantityBill);
					orderDTO.setPrice(productDTO.getPrice());
					
					map.put(productDTO.getId(), orderDTO);
			}else {
				orderDTO.setQty(orderDTO.getQty() + quantityBill);
				
				map.put(productDTO.getId(), orderDTO);
			}
			
			httpSession.setAttribute("cart", map);
			}
		}
		return "redirect:/client/cart";
	}
	
	@GetMapping("/delete-item-cart/{id_order}")
	public String deleteCart(@PathVariable(name="id_order" ,required = false)int id_order, HttpSession session ) {
		Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) session.getAttribute("cart");
		
		if(map != null) {
				map.remove(id_order);
		}
		
		session.setAttribute("cart", map);
		return "redirect:/client/cart";
	}
	
	@GetMapping("/check-out")
	public String checkOutCart(HttpServletRequest request , HttpSession session) {
		if(session.getAttribute("user") != null) {
			String name = (String) session.getAttribute("user");
			AdminUserDTO adminUserDTO = adminUserService.getAdminByUserName(name);
			Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) session.getAttribute("cart");
			
		}else {
			return "redirect:/login";
		}
		
		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
	
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		
		
		
		
		return "user/checkout";
	}
	
	@GetMapping("/myorder")
	public String myOrder(HttpServletRequest request,HttpSession session) {

		List<CategoriesDTO> categoriesDTOs = categoriesService.getAll();
		request.setAttribute("categories", categoriesDTOs);
	
		List<SubCategoriesDTO> subCategoriesDTOs = subCategoriesService.getAll();
		request.setAttribute("subcate", subCategoriesDTOs);
		if(session.getAttribute("user") != null) {
			String name = (String) session.getAttribute("user");
			AdminUserDTO adminUserDTO = adminUserService.getAdminByUserName(name);
			List<OrderDetailDTO> orderDetailDTOs = orderDetailService.getOrderDetailByIdUser(adminUserDTO.getId());
			request.setAttribute("orderDeatil", orderDetailDTOs);
		}		
		
		return "user/myorder";
	}
	
	@PostMapping("add-order")
	public String addOrder(HttpServletRequest request,HttpSession session,@RequestParam(name = "city")String city, @RequestParam(name = "address")String address) {
		
		Map<Integer, OrderDTO> map = (Map<Integer, OrderDTO>) session.getAttribute("cart");
		CouponDTO couponDTO = (CouponDTO) session.getAttribute("coupon");
		
		String name = (String) session.getAttribute("user");
		AdminUserDTO adminUserDTO = adminUserService.getAdminByUserName(name);
		OrderDetailDTO orderDetailDTO = new OrderDetailDTO();
		orderDetailDTO.setUser(adminUserDTO);
		orderDetailDTO.setAddress(address);
		orderDetailDTO.setCity(city);
		float total =  0;
		
		if(map != null) {
			for(Map.Entry<Integer, OrderDTO> entry : map.entrySet()) {
				total += entry.getValue().getQty() * entry.getValue().getPrice();
			}
		}
		orderDetailDTO.setTotal_price(total);
		if(couponDTO != null) {
			orderDetailDTO.setCouponDTO(couponDTO);
		}
		
		try {
			orderDetailService.add(orderDetailDTO);
				for(Map.Entry<Integer, OrderDTO> entry : map.entrySet()) {
					OrderDTO orderDTO = entry.getValue();
					orderDTO.setOrderDetailDTO(orderDetailDTO);
					orderService.addOrder(orderDTO);
					//System.out.println(orderDTO.getOrderDetailDTO().getId());
					ProductDTO productDTO = orderDTO.getProductDTO();
					productDTO.setQty(productDTO.getQty() - orderDTO.getQty());
					productService.update(productDTO);
				}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		mailService.sendSimpleMessage(adminUserDTO.getEmail(),
				"Mail Bill",
				"Cam on ban da mua hang tai WebSite chung toi!!\r\n Giao dich ngay: "
						+ orderDetailService.dateToString(new Date()) +"Tai dia chi :"+orderDetailDTO.getAddress()+" Ten nguoi nhan: "+orderDetailDTO.getUser().getName() +" Tong tien thanh toan: "
						+ orderDetailDTO.getTotal_price() );

		session.removeAttribute("cart");
		session.removeAttribute("coupon");
	
		return	"redirect:/client/myorder";
	}
	
	@GetMapping("/chitiet-order/{id_orderdetail}")
	public String chitietOrder(@PathVariable(name = "id_orderdetail")int id_orderdetail, HttpServletRequest request) {
		List<OrderDTO> orderDTOs = orderService.getOrderById_OrderDeatil(id_orderdetail);
		request.setAttribute("order", orderDTOs);
		return "user/orderdetail";
	}
	
	@PostMapping("/add-user")
	public String addUser(@ModelAttribute()AdminUserDTO adminUserDTO) {
		adminUserDTO.setRole("ROLE_USER");
		adminUserService.add(adminUserDTO);
		mailService.sendSimpleMessage(adminUserDTO.getEmail(), "Mail Register", "Cam on ban "+adminUserDTO.getName() +" da su dung WebSite cua chung toi!!!\r\n"
				+ "Tai khoan cua ban la:\r\nUserName: " + adminUserDTO.getUsername() + "\r\nPassword : " + adminUserDTO.getPassword());
		return "redirect:/login";
	}
	
	@GetMapping("/export_pdf")
	public void exportPdf(HttpServletResponse response, HttpSession session) throws DocumentException, IOException {
		response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());
         
        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=Order_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);
         
        if(session.getAttribute("user") != null) {
			String name = (String) session.getAttribute("user");
			AdminUserDTO adminUserDTO = adminUserService.getAdminByUserName(name);
			
			
			List<OrderDetailDTO> orderDetailDTOs = orderDetailService.getOrderDetailByIdUser(adminUserDTO.getId());
			/*
			 * for(OrderDetailDTO orderDetailDTO : orderDetailDTOs) { List<OrderDTO>
			 * orderDTOs = orderService.getOrderById_OrderDeatil(orderDetailDTO.getId()); }
			 */
			OrderPDFExporter exporter = new OrderPDFExporter(orderDetailDTOs );
	        exporter.export(response);
	        
	        System.out.println(headerValue);
		}		
         
       
	}
}
