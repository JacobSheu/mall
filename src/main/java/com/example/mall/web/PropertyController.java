package com.example.mall.web;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mall.dao.PropertyMapper;
import com.example.mall.pojo.Property;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
public class PropertyController {
    @Autowired PropertyMapper propertyMapper;

    @GetMapping("/categories/{categoryIdOnHtml}/properties")
    public List<Property> getPropertyByCategory(@PathVariable int categoryIdOnHtml){
        return propertyMapper.selectList(
                new QueryWrapper<Property>()
                        .lambda()
                        .eq(Property::getCategoryId, categoryIdOnHtml)
        );
    }

    @GetMapping("/properties/{id}")
    public Property getPropertyById(@PathVariable int id){
        return propertyMapper.selectById(id);
    }

    @PostMapping("/properties")
    public int post(@RequestBody Property propertyOnHtml){
        return propertyMapper.insert(propertyOnHtml);
    }

    @DeleteMapping("/properties/{id}")
    public int delete(@PathVariable int id){
        return propertyMapper.deleteById(id);
    }

    @PutMapping("/properties")
    public int put(@RequestBody Property property){
        return propertyMapper.updateById(property);
    }
}
