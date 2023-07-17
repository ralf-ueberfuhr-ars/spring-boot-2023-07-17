package de.huk.schulung.spring.blog;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {

    private Long id;
    private String title;
    private String content;
    private LocalDateTime creationDate;

}
