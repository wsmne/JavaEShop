package com.wsm.proj;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Order {
    private Integer uid;
    private Integer pid;
    private Integer quantity;
    private Integer price;
}
