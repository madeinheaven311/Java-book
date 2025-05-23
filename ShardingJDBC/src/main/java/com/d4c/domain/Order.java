package com.d4c.domain;


import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Data
@Getter
@Setter
@NoArgsConstructor
public class Order {


    @TableId
    private Long order_id;

    private BigDecimal price;

    private Long user_id;

    private String status;
}


