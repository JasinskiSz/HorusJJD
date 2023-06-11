package org.jasinski;

import lombok.Data;
import lombok.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Data
public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall(@NonNull List<Block> blocks) {
        this.blocks = blocks;
    }

    /**
     * Searching recursively for all leaf blocks - blocks that are not CompositeBlocks
     *
     * @param blocks - list of blocks to search
     * @return list of leaf blocks
     * @see CompositeBlock
     * @see Block
     */
    private List<Block> findLeafBlocks(List<Block> blocks) {
        List<Block> leaves = new ArrayList<>();
        for (Block block : blocks) {
            if (block instanceof CompositeBlock) {
                leaves.addAll(findLeafBlocks(((CompositeBlock) block).getBlocks()));
            } else {
                // might need to be else if instead of else in case there are
                // other implementations of Block interface
                leaves.add(block);
            }
        }
        return leaves;
    }

    /**
     * Searching for any block matching the given color.
     * Null objects are filtered out.
     *
     * @param color - color of the searched block
     * @return any block matching the color
     */
    @Override
    public Optional<Block> findBlockByColor(String color) {
        return findLeafBlocks(blocks).stream()
                // filter out null blocks
                .filter(Objects::nonNull)
                // filter out blocks with different color
                .filter(block -> block.getColor().equals(color))
                // return any block
                .findAny();
    }

    /**
     * Searching for all blocks matching the given material.
     * Null objects are filtered out.
     *
     * @param material - material of the searched blocks
     * @return list of blocks matching the material
     * @see Block
     */
    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return findLeafBlocks(blocks).stream()
                // filter out null blocks
                .filter(Objects::nonNull)
                // filter out blocks with different material
                .filter(block -> block.getMaterial().equals(material))
                // return list of blocks
                .collect(Collectors.toList());
    }

    @Override
    public int count() {
        return findLeafBlocks(blocks).size();
    }

    /**
     * Adds blocks to the list of blocks.
     *
     * @param blocks - list of blocks to add
     */
    public void addBlocks(List<Block> blocks) {
        this.blocks.addAll(blocks);
    }
}
