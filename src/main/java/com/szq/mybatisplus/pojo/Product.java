package com.szq.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

@Data
public class Product {
        private Long id;

        private String name;

        private Integer price;

        @Version//标识乐观锁版本号总字段
        private Integer version;
}
