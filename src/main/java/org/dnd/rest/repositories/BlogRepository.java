package org.dnd.rest.repositories;

import org.dnd.rest.entities.Blog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Map;

@Repository
public interface BlogRepository extends JpaRepository<Blog, Long> {

}
