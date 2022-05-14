package music.controllers;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import music.models.Product;
import music.repositories.ProductRepository;

@Controller
@RequestMapping("/products")
public class ProductController {

	@Autowired
	private ProductRepository productRepo;

	@GetMapping
	public String showProducts(Model model) {
		model.addAttribute("products", productRepo.findAll());
		return "products";
	}

	@GetMapping("/updateProduct")
	public String showAddProducts(Model model) {
		model.addAttribute("product", new Product());
		return "updateProduct";
	}
	@GetMapping("/updateProduct/{id}")
	public String showEditProducts(@PathVariable String id,Model model) {
		Optional<Product> product = productRepo.findById(id);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
			return "updateProduct";
		} else {
			return "notfound";
		}
	}
	@PostMapping("/updateProduct")
	public String addProducts(@Valid Product product, Errors errors,RedirectAttributes ra) {
		if (errors.hasErrors()) {
			return "updateProduct";
		}
		System.out.println(product);
		productRepo.save(product);
		ra.addFlashAttribute("success", "Cập nhật thành công!");
		
		return "redirect:/products";
	}

	@GetMapping("/deleteProduct/{id}")
	public String showDeleteProduct(@PathVariable String id, Model model) {
		Optional<Product> product = productRepo.findById(id);
		if (product.isPresent()) {
			model.addAttribute("product", product.get());
			return "deleteProduct";
		} else {
			return "notfound";
		}

	}

	@PostMapping("/deleteProduct/{id}")
	public String deleteProduct(@PathVariable String id, @RequestParam String ans, RedirectAttributes ra) {
		if (ans.equals("yes")) {
			productRepo.deleteById(id);
			ra.addFlashAttribute("success", "Xóa thành công!");

		}
		return "redirect:/products";

	}
}
