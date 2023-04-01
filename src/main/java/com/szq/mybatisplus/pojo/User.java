package com.szq.mybatisplus.pojo;


import com.baomidou.mybatisplus.annotation.*;
import com.szq.mybatisplus.enums.SexEnum;
import lombok.Data;

/*@NoArgsConstructor 无参构造
@AllArgsConstructor  所有有参构造
@Setter  set方法
@Getter  get方法
@EqualsAndHashCode  equal和hsahcode方法
@Data 包括以上所有方法
*/
@Data
//设置实体类所对应的表名
//@TableName("t_user")
public class User {

    //@TableId value属性所对应的字段指定为主键
    //@TableId type属性设置主键生成策略
    @TableId(value = "uid",type = IdType.AUTO)
    private Long id;

    //指定属性设置字段名
    @TableField("user_name")
    private String name;

    private Integer age;

    private String email;

    private SexEnum sex;
    //逻辑删除数据
    @TableLogic
    private Integer isDelete;


}
