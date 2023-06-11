package org.jasinski;

import lombok.Data;

@Data
public class BlockImpl implements Block {
    private final String color;
    private final String material;

    public BlockImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }
}
