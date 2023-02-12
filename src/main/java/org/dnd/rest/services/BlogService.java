package org.dnd.rest.services;

import org.dnd.rest.entities.Blog;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BlogService {

    //created
    Blog saveBlog(Blog blog) throws Exception;

    //get
    List<Blog> getAllBlogs(int pageNo, int pagesize, String sortBy, String sortDir) throws Exception;
    Blog getBlogById(Long id) throws Exception;

    //update
    Blog updateBlog(Long id, Blog blog) throws Exception;

    //delete
    Boolean deleteBlogById(Long id) throws Exception;

}
