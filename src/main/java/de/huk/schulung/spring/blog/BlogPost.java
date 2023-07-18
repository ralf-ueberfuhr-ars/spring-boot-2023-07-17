package de.huk.schulung.spring.blog;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    // serverseitig generiert, nicht Teil des Requests
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private Long id;
    @Title
    private String title;
    private String content;
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private LocalDateTime creationDate;

}
