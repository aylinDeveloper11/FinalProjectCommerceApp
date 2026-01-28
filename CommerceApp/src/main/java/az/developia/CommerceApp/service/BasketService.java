package az.developia.CommerceApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.CommerceApp.dto.BasketProductResponseDto;
import az.developia.CommerceApp.entity.Basket;
import az.developia.CommerceApp.entity.Product;
import az.developia.CommerceApp.entity.UserEntity;
import az.developia.CommerceApp.repository.BasketRepository;
import az.developia.CommerceApp.repository.ProductRepository;
import az.developia.CommerceApp.repository.UserRepository;

@Service
public class BasketService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private BasketRepository basketRepository;

    private UserEntity getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void addToBasket(Long productId) {
        UserEntity user = getCurrentUser();
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Basket basket = basketRepository.findByUserIdAndProductId(user.getId(), productId).orElse(null);

        if (basket == null) {
            Basket newBasket = new Basket();
            newBasket.setUserId(user.getId());
            newBasket.setProductId(productId);
            newBasket.setQuantity(1);
            basketRepository.save(newBasket);
        } else {
            basket.setQuantity(basket.getQuantity() + 1);
            basketRepository.save(basket);
        }
    }

    public List<BasketProductResponseDto> myBasket() {
        UserEntity user = getCurrentUser();
        List<Basket> baskets = basketRepository.findByUserId(user.getId());

        return baskets.stream().map(basket -> {
            Product product = productRepository.findById(basket.getProductId())
                    .orElseThrow(() -> new RuntimeException("Product not found"));

            BasketProductResponseDto dto = new BasketProductResponseDto();
            dto.setBasketId(basket.getId());
            dto.setBrand(product.getBrand());
            dto.setImgUrl(product.getImageUrl());
            dto.setPrice(product.getPrice());
            dto.setQuantity(basket.getQuantity());
            return dto;
        }).toList();
    }

    public void removeFromBasket(Long productId) {
        UserEntity user = getCurrentUser();
        Basket basket = basketRepository.findByUserIdAndProductId(user.getId(), productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        basketRepository.delete(basket);
    }
}
