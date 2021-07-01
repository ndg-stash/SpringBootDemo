package springboot.example.demo.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.example.demo.entity.Product;
import springboot.example.demo.repository.ProductRepo;

@Controller
@RequiredArgsConstructor
@RequestMapping("/products_list")
public class ProductController {

    private final ProductRepo productRepo;

    @GetMapping
    public String startPage(Model model){
        model.addAttribute("products", productRepo.findAll());
        return "index";
    }

    @PostMapping
    public String addProduct(Product product){
        //model.addAttribute("products", productRepo.findAll());
        return "redirect:/products_list";
    }

//    @PostMapping
//    public String addProductVals(String val){
//        //model.addAttribute("products", productRepo.findAll());
//        System.out.println(val);
//        return "redirect:/products_list";
//    }

}
