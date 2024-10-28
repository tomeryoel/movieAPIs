package tomash.project.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private MongoTemplate mongoTemplate;

    public Review createReview(String reviewBody, String imdbId) {
//        Review review = new Review(reviewBody);
//        reviewRepository.insert(review);
       Review review = reviewRepository.insert( new Review(reviewBody));


//        mongoTemplate.update(Movie.class)
//                .matching(Criteria.where("imdbId").is(imdbId))
//                .apply(new Update().push("reviewIds").value(review))
//                .first();
//
//        return review;
        // Use MongoTemplate to add the review ID to the Movie document's reviewIds array
        mongoTemplate.updateFirst(
                Query.query(Criteria.where("imdbId").is(imdbId)), // Find movie by imdbId
                new Update().push("reviewIds", review.getId()), // Add review ID to reviewIds array
                Movie.class // Target collection
        );

        return review;
    }
}
