package com.example.mall.web;

import com.example.mall.util.ImageUtil;
import com.example.mall.dao.CategoryMapper;
import com.example.mall.pojo.Category;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired CategoryMapper categoryMapper;

    @GetMapping("/categories")
    public List<Category> get(){
        PageHelper.offsetPage(0, 5);
        return categoryMapper.getAllCategory();
    }
    @PostMapping("/categories")
    public Object post( Category categoryOnHtml, MultipartFile image, HttpServletRequest request) throws Exception{
        saveOrUpdateImageFile(categoryOnHtml, image, request);
        return categoryMapper.post(categoryOnHtml);
    }
    public void saveOrUpdateImageFile(Category categoryOnHtml, MultipartFile image, HttpServletRequest request)
            throws IOException {
        File imageFolder= new File(request.getServletContext().getRealPath("img/category"));
        File file = new File(imageFolder,categoryOnHtml.getId()+".jpg");
        if(!file.getParentFile().exists())
            file.getParentFile().mkdirs();
        image.transferTo(file);
        BufferedImage img = ImageUtil.changeToJpg(file);
        ImageIO.write(img, "jpg", file);
    }
    @DeleteMapping("categories/{id}")
    public void delete(@PathVariable int id, HttpServletRequest request){
        categoryMapper.deleteById(id);
        File imageFolder=new File(request.getServletContext().getRealPath("img/category"));
        File file=new File(imageFolder,id+".jpg");
        file.delete();
    }

    @GetMapping("categories/{id}")
    public Category get(@PathVariable int id){
        return categoryMapper.getById(id);
    }

    @PutMapping("categories/{id}")
    public Category put(Category categoryOnHtml, MultipartFile image, HttpServletRequest request) throws Exception{
        String name=request.getParameter("name");
        categoryOnHtml.setName(name);
        if (null!=image)
            saveOrUpdateImageFile(categoryOnHtml, image, request);
        return categoryMapper.put(categoryOnHtml);
    }
}
