package org.dnd.rest.controllers;

import com.sun.org.apache.xpath.internal.operations.Bool;
import org.dnd.rest.entities.Blog;
import org.dnd.rest.models.AppConstants;
import org.dnd.rest.services.BlogService;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/blogs")
//@Validated
public class BlogController {

    private BlogService blogService;

    BlogController(BlogService blogService){
        this.blogService = blogService;
    }

    @PostMapping(value = "/blog", produces = "application/json", consumes = "application/json")
    public ResponseEntity<Blog> saveBlog(@Valid @RequestBody Blog blog) throws Exception {
        return new ResponseEntity<>(blogService.saveBlog(blog), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Blog>> getAllBlog(
            @RequestParam( value = "PageNo", defaultValue = AppConstants.DEFAULT_PAGE_NUMBER, required = false)int pageNo,
            @RequestParam(value = "pageSize", defaultValue = AppConstants.DEFAULT_PAGE_SIZE, required = false) int pagesize,
            @RequestParam(value = "sortBy", defaultValue = AppConstants.DEFAULT_SORT_BY, required = false) String sortBy,
            @RequestParam (value = "sortDir", defaultValue = AppConstants.DEFAULT_SORT_DIRECTION, required = false) String sortDir
    ) throws Exception{

        return new ResponseEntity<>(blogService.getAllBlogs(pageNo, pagesize, sortBy, sortDir),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Blog> getBlogById(@PathVariable("id") Long id) throws Exception{
        return new ResponseEntity<>(blogService.getBlogById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Blog> updateBlog(@RequestBody Blog blog, @PathVariable("id") Long id) throws Exception {
        return new ResponseEntity<Blog>(blogService.updateBlog(id, blog),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBlog(@PathVariable("id") Long id) throws Exception{
        return new ResponseEntity<>(blogService.deleteBlogById(id), HttpStatus.OK);
    }


}
