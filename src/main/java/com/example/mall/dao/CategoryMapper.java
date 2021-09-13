package com.example.mall.dao;

import com.example.mall.pojo.Category;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CategoryMapper {
    @Select("select * from category")
    List<Category> getAllCategory();

    @Insert("insert into category (name) values(#{name})")
    Category post(Category category);

    @Delete("delete from category where id = #{id}")
    void deleteById(int id);

    @Select("select * from category where id=#{id}")
    Category getById(int id);

    @Update("update category set name=#{name} where id=#{id}")
    Category put (Category category);
}
