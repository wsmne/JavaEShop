package com.wsm.proj;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Data
public class Good {


    private Integer id;
    private String name;
    private String kind;
    private String description;
    private Integer price;
    private Integer inventory;

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public void setInventory(Integer inventory) {
        this.inventory = inventory;
    }
}
