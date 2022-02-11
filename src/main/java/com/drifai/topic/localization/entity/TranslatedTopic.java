package com.drifai.topic.localization.entity;

import com.sun.istack.NotNull;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "translations")
public class TranslatedTopic {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private Long id;

    private Long associatedId;

    @NotNull
    private String msgKey;
    private String language;
    private String content;
    @Column(name = "created_at")
    @CreatedDate
    @NotNull
    private Timestamp createdAt;
    @Column(name = "updated_at")
    @LastModifiedDate
    @NotNull
    private Timestamp updatedAt;
}

