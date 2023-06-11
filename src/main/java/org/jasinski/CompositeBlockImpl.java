package org.jasinski;

import lombok.Data;
import lombok.NonNull;

import java.util.List;

@Data
public class CompositeBlockImpl implements CompositeBlock {
    private final List<Block> blocks;
    private final String color = "composite";
    private final String material = "composite";

    public CompositeBlockImpl(@NonNull List<Block> blocks) {
        this.blocks = blocks;
    }
}
