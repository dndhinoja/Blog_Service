package org.dnd.rest.services;

import org.dnd.rest.entities.Blog;
import org.dnd.rest.exceptions.BlogAlreadyExist;
import org.dnd.rest.exceptions.BlogNotFound;
import org.dnd.rest.repositories.BlogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BlogServiceImpl implements BlogService{

    @Autowired
    private BlogRepository blogRepository;

    public BlogServiceImpl(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Blog saveBlog(Blog blog) throws Exception {
        //Convertor should be there for Model to Entity and Entity to Model
        if(blogRepository.existsById(blog.getId())){
            throw new BlogAlreadyExist("Already exist");
        }

        Blog savedBlog  = blogRepository.save(blog);
        return savedBlog;
    }

    @Override
    public List<Blog> getAllBlogs(int pageNo, int pagesize, String sortBy, String sortDir) throws Exception {
        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending()
                : Sort.by(sortBy).descending();
        Pageable pageable  = PageRequest.of(pageNo, pagesize, sort);
        //Convertor should be there for Model to Entity and Entity to Model
        if(blogRepository.findAll(pageable).isEmpty()){
            throw new Exception();
        }
        return blogRepository.findAll(pageable).getContent();
    }

    @Override
    public Blog getBlogById(Long id) throws Exception {
        //Convertor should be there for Model to Entity and Entity to Model
        Blog blog;
        if(blogRepository.findById(id).isPresent()){
            blog = blogRepository.findById(id).get();
        }
        else {
            throw new BlogNotFound("Blog not exist");
        }
        return blog;
    }

    @Override
    @Transactional(propagation = Propagation.NOT_SUPPORTED)
    public Blog updateBlog(Long id, Blog blog) throws Exception {
        //Convertor should be there for Model to Entity and Entity to Model
        Blog originalBlog = blogRepository.findById(id).orElseThrow(() -> new Exception());
        originalBlog.setBlogTitle(blog.getBlogTitle());
        originalBlog.setBlogCreator(blog.getBlogCreator());
        originalBlog.setYearOfPost(blog.getYearOfPost());

        return  blogRepository.save(originalBlog);
    }

    @Override
    public Boolean deleteBlogById(Long id) throws Exception {
        //Convertor should be there for Model to Entity and Entity to Model
        Blog blogToBeDeleted = blogRepository.findById(id).orElseThrow(()-> new Exception());
        blogRepository.delete(blogToBeDeleted);
        if(!blogRepository.existsById(id)){
            return true;
        }
        return false;
    }


}
