package az.developia.CommerceApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import az.developia.CommerceApp.dto.BasketProductResponseDto;
import az.developia.CommerceApp.service.BasketService;

@RestController
@RequestMapping("/basket")
@CrossOrigin("*")
public class BasketController {
    
    @Autowired
    private BasketService basketService;

    @PostMapping("/{productId}")
    public void addToBasket(@PathVariable Long productId) {
        basketService.addToBasket(productId);
    }

    @GetMapping
    public List<BasketProductResponseDto> myBaskets() {
        return basketService.myBasket();
    }

    @DeleteMapping("/{productId}")
    public void removeFromBasket(@PathVariable Long productId) {
        basketService.removeFromBasket(productId);
    }
}
