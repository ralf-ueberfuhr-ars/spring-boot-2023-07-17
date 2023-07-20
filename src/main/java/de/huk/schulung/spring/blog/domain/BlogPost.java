package de.huk.schulung.spring.blog.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    // TODO JsonProperty ist Boundary, NICHT Domain --> separate Klasse BlogPostDTO in Boundary und Mapping, z.B. mit MapStruct
    // Beispiel: https://github.com/ralf-ueberfuhr-ars/spring-2023-05-04/blob/main/src/main/java/de/sample/spring/customers/boundary/rest/CustomerController.java


    // serverseitig generiert, nicht Teil des Requests
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Title
    private String title;
    private String content;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

}
