package com.drifai.topic.localization.repository;

import com.drifai.topic.localization.entity.TranslatedTopic;
import org.springframework.data.repository.CrudRepository;

public interface TranslatedTopicRepository extends CrudRepository<TranslatedTopic, Integer> {

}
