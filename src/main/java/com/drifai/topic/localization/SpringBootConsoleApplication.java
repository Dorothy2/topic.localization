package com.drifai.topic.localization;

import com.drifai.topic.localization.entity.TranslatedTopic;
import com.drifai.topic.localization.repository.TranslatedTopicRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@SpringBootApplication
public class SpringBootConsoleApplication implements CommandLineRunner {

	@Autowired
	TranslatedTopicRepository translatedTopicRepository;

	public static void main(String[] args) {
		log.info("STARTING THE APPLICATION");
		SpringApplication.run(SpringBootConsoleApplication.class, args);
		log.info("APPLICATION FINISHED.");
	}

	@Override
	public void run(String... args) {
		log.info("EXECUTING : command line runner");
		if(args.length > 0) {
			for(int i = 0; i < args.length; ++i) {
				log.info("args[{}]", i, args[i]);
			}
		} else {
			log.info("No args were passed.");
		}

		Timestamp ts = Timestamp.from(Instant.now());
		List<TranslatedTopic> listToInsert = new ArrayList<>();
		long counter = 0;
		String messageKey = "program_bank_topics_1";

		TranslatedTopic t = TranslatedTopic.builder()
				.id(++counter)
				.msgKey(messageKey)
				.language("en")
				.content("English version")
				.build();
		TranslatedTopic saved = createInsert(t, ts);
		listToInsert.add(saved);

		t = TranslatedTopic.builder()
				.id(++counter)
				.associatedId(1L)
				.msgKey(messageKey)
				.language("sp")
				.content("Spanish version")
				.build();
		saved = createInsert(t, ts);
		listToInsert.add(saved);

		t = TranslatedTopic.builder()
				.id(++counter)
				.associatedId(1L)
				.msgKey(messageKey)
				.language("ht")
				.content("Haitian-Creole version")
				.build();
		saved = createInsert(t, ts);
		listToInsert.add(saved);

		System.out.println(formatSQL(listToInsert));

	}

	private TranslatedTopic createInsert(TranslatedTopic t, Timestamp ts) {
		t.setCreatedAt(ts);
		t.setUpdatedAt(ts);
		TranslatedTopic saved = translatedTopicRepository.save(t);
		log.info(String.format("Topic %s,  %s version saved.", saved.getMsgKey(), saved.getLanguage()));
        return(saved);
	}

	private String formatSQL(List<TranslatedTopic> list) {
		StringBuffer sb = new StringBuffer("INSERT INTO `translations`\n\t  VALUES\n" );
		for(TranslatedTopic topic: list) {
			sb.append(String.format("\t\t(%d,%d, '%s','%s','%s','%s','%s'),",
					topic.getId(),
					topic.getAssociatedId(), topic.getContent(), topic.getCreatedAt(),
					topic.getLanguage(), topic.getMsgKey(), topic.getUpdatedAt()));

			sb.append("\n");
		}
		// Remove final comma and replace line feed
        String finished = sb.toString().substring(0, sb.length()-2);
		finished = finished + "\n);";
		return finished;
	}
}
