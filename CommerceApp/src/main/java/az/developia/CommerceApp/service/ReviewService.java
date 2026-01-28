package az.developia.CommerceApp.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import az.developia.CommerceApp.dto.ReviewDto;
import az.developia.CommerceApp.entity.Product;
import az.developia.CommerceApp.entity.Review;
import az.developia.CommerceApp.entity.UserEntity;
import az.developia.CommerceApp.repository.ProductRepository;
import az.developia.CommerceApp.repository.ReviewRepository;
import az.developia.CommerceApp.repository.UserRepository;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    private UserEntity getCurrentUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new RuntimeException("User not found"));
    }

    public Review create(ReviewDto reviewDto) {
        UserEntity user = getCurrentUser();

        // Проверяем, что продукт существует
        productRepository.findById(reviewDto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Review review = new Review();
        review.setProductId(reviewDto.getProductId());
        review.setComment(reviewDto.getComment());
        review.setUserId(user.getId());
        review.setRating(reviewDto.getRating());

        return reviewRepository.save(review);
    }

    public List<Review> getByProduct(Long productId) {
        return reviewRepository.findByProductId(productId);
    }

    public void delete(Long id) {
        Review review = reviewRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Review not found"));

        UserEntity user = getCurrentUser();
        if (!review.getUserId().equals(user.getId())) {
            throw new RuntimeException("You are not allowed to delete this review");
        }

        reviewRepository.deleteById(id);
    }

    public List<Review> getByUser(Long userId) {
        return reviewRepository.findByUserId(userId);
    }
}
