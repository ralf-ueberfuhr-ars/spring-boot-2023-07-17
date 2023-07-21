package de.huk.schulung.spring.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "posts")
public class BlogPost {

    // TODO JsonProperty ist Boundary, NICHT Domain --> separate Klasse BlogPostDTO in Boundary und Mapping, z.B. mit MapStruct
    // Beispiel: https://github.com/ralf-ueberfuhr-ars/spring-2023-05-04/blob/main/src/main/java/de/sample/spring/customers/boundary/rest/CustomerController.java
    // TODO Entity in Persistence mit Mapper

    // serverseitig generiert, nicht Teil des Requests
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Title
    private String title;
    private String content;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    @Column(name = "created_at")
    private LocalDateTime creationDate;

}
