package ibf2022.batch1.csf.assessment.server.repositories;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import ibf2022.batch1.csf.assessment.server.models.Comment;

@Repository
public class MovieRepository {
	private static final Logger logger = LoggerFactory.getLogger(MovieRepository.class);

	private static final String COMMENTS_COL = "comments";

	@Autowired
    private MongoTemplate mongoTemplate;

	// TODO: Task 5
	// You may modify the parameter but not the return type
	// Write the native mongo database query in the comment below
	/* db.comments.count({ title: "Godfather" }) */
	public int countComments(String movieTitle) {
		logger.info("movieTitle: " + movieTitle);
		Criteria criteria = Criteria.where("title").is(movieTitle);
		Query query = Query.query(criteria);
		long count = mongoTemplate.count(query, "comments");
		logger.info("count = " + count);
		return (int) count;
	}

	// TODO: Task 8
	// Write a method to insert movie comments comments collection
	// Write the native mongo database query in the comment below
	/* 
		db.comments.insertOne({
		title: "The Godfather",
		comment: "This was a great movie!"
		}); 
	*/
	public Comment insertComment(Comment c){
        return this.mongoTemplate.insert(c, COMMENTS_COL);
    }

}
