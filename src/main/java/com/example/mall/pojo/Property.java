package com.example.mall.pojo;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("property")
public class Property {
    int id;
    String name;
    int categoryId;
}
