package de.huk.schulung.spring.blog.persistence;

import de.huk.schulung.spring.blog.domain.BlogPost;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BlogPostRepository extends JpaRepository<BlogPost, Long> {
}
