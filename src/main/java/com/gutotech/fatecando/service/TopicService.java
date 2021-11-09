package com.gutotech.fatecando.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Service;

import com.gutotech.fatecando.model.Question;
import com.gutotech.fatecando.model.Review;
import com.gutotech.fatecando.model.Topic;

@Service
public class TopicService {

	@Autowired
	private CustomRestTemplate restTemplate;

	private final String URL;

	public TopicService(@Value("${fatecando.api.base-url}") String url) {
		URL = url + "/topics";
	}

	public List<Topic> findApproved() {
		return restTemplate.getForObjects(URL, new ParameterizedTypeReference<List<Topic>>() {
		});
	}

	public List<Topic> findAll() {
		return restTemplate.getForObjects(URL + "/admin", new ParameterizedTypeReference<List<Topic>>() {
		});
	}
	
	public Topic findById(Long id) {
		return restTemplate.getForObject(URL + "/" + id, Topic.class);
	}

	public Topic findByIdWithPreviousAndNext(Long id) {
		return restTemplate.getForObject(URL + "/{id}?previous-next=true", Topic.class, id);
	}

	public Topic save(Topic topic) {
		return restTemplate.postForObject(URL, topic, Topic.class);
	}

	public void update(Topic topic) {
		restTemplate.put(URL + "/{id}", topic, topic.getId());
	}

	public void toggleFinished(Topic topic) {
		restTemplate.put(URL + "/{id}/finished", null, topic.getId());
	}

	public void toggleFavorite(Topic topic) {
		restTemplate.put(URL + "/{id}/favorite", null, topic.getId());
	}

	public void saveAnnotation(Topic topic, String annotation) {
		restTemplate.put(URL + "/{id}/annotation", annotation, topic.getId());
	}
	
	public void deleteAnnotation(Topic topic) {
		restTemplate.delete(URL + "/{id}/annotation", topic.getId());
	}

	public void saveReview(Topic topic, Review review) {
		restTemplate.put(URL + "/{id}/review", review, topic.getId());
	}
	
	public List<Review> findReviews(Topic topic) {
		return restTemplate.getForObjects(URL + "/{id}/reviews", new ParameterizedTypeReference<List<Review>>() {
		}, topic.getId());
	}

	public void dragTopic(Long draggedTopicId, Long relatedTopicId) {
		restTemplate.put(URL + "/drag/{draggedTopicId}/{relatedTopicId}", null, draggedTopicId, relatedTopicId);
	}

	public List<Question> getQuiz(Topic topic) {
		return restTemplate.getForObjects(URL + "/{id}/quiz", new ParameterizedTypeReference<List<Question>>() {
		}, topic.getId());
	}
	
	public List<Question> findQuestions(Topic topic) {
		return restTemplate.getForObjects(URL + "/{id}/questions", new ParameterizedTypeReference<List<Question>>() {
		}, topic.getId());
	}
	
	public Map<String, List<Topic>> groupBySubject(List<Topic> topics) {
		return topics.stream().collect(Collectors.groupingBy(topic -> topic.getSubject().getName()));
	}

}
