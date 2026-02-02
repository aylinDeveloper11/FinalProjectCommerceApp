package az.developia.CommerceApp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;

import az.developia.CommerceApp.dto.ReviewDto;
import az.developia.CommerceApp.entity.Review;
import az.developia.CommerceApp.service.ReviewService;

@RestController
@RequestMapping("/reviews")
public class ReviewController {

    @Autowired
    private ReviewService reviewService;

    @PostMapping
    @PreAuthorize("hasAnyRole('USER','ADMIN')")
    public Review create(@Valid @RequestBody ReviewDto reviewDto) {
        return reviewService.create(reviewDto);
    }

    @GetMapping("/product/{productId}") 
    public List<Review> getByProduct(@PathVariable Long productId) {
        return reviewService.getByProduct(productId);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')") 
    public void delete(@PathVariable Long id) {
        reviewService.delete(id);
    }

}
