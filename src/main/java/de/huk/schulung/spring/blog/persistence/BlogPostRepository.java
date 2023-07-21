package de.huk.schulung.spring.blog.persistence;

import de.huk.schulung.spring.blog.domain.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {

    // Suche nach Titel
    List<BlogPost> findBlogPostsByTitleContainingIgnoreCase(String searchtext);
    List<BlogPost> findBlogPostsByCreationDateBeforeOrderByCreationDateDesc(LocalDateTime time);

    // ansonsten: @Query
    @Query("select p from BlogPost p where p.title = :title")
    List<BlogPost> anotherFindByTitle(@Param("title") String title);
}
