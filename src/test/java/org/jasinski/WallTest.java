package org.jasinski;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WallTest {
    // single blocks - non-composite
    private final Block block1 = new BlockImpl("brown", "wood");
    private final Block block2 = new BlockImpl("red", "wood");
    private final Block block3 = new BlockImpl("brown", "stone");
    private final Block block4 = new BlockImpl("grey", "stone");
    private final Block block5 = new BlockImpl("red", "stone");

    // blocks to put into composite blocks
    private final Block blockInsideComposite1 = new BlockImpl("black", "wood");
    private final Block blockInsideComposite2 = new BlockImpl("white", "stone");
    private final Block blockInsideComposite3 = new BlockImpl("red", "wood");
    private final Block blockInsideComposite4 = new BlockImpl("black", "wood");
    private final Block blockInsideComposite5 = new BlockImpl("blue", "wood");
    private final Block blockInsideComposite6 = new BlockImpl("red", "stone");

    // composite blocks
    private final CompositeBlock compositeBlock1 = new CompositeBlockImpl(List.of(
            blockInsideComposite1,
            blockInsideComposite2,
            blockInsideComposite3));

    private final CompositeBlock compositeBlock2 = new CompositeBlockImpl(List.of(
            blockInsideComposite4,
            blockInsideComposite5));

    private final CompositeBlock compositeBlock3 = new CompositeBlockImpl(List.of(
            compositeBlock2,
            blockInsideComposite6));

    private Wall wall = new Wall(List.of());

    @BeforeEach
    void setUp() {
        wall = new Wall(new ArrayList<>());
    }

    @Test
    void findBlockByColor_ShouldNotFindBlock_WhenBlockNotOnList() {
        // given
        // empty list of blocks

        // when
        Optional<Block> result = wall.findBlockByColor("black");

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void findBlockByColor_ShouldFindBlock_WhenBlockOnList() {
        // given
        wall.addBlocks(List.of(block1, block2, block3, block4, block5,
                compositeBlock1, compositeBlock3));
        String color = "grey";

        // when
        Optional<Block> result = wall.findBlockByColor(color);

        // then
        assertTrue(result.isPresent());
        assertEquals(result.get().getColor(), color);
    }

    @ParameterizedTest
    @ValueSource(strings = {"black", "white", "blue"})
    void findBlockByColor_ShouldFindBlock_WhenBlockOnCompositeBlockList(String color) {
        // given
        wall.addBlocks(List.of(block1, block2, block3, block4, block5,
                compositeBlock1, compositeBlock3));

        // when
        Optional<Block> result = wall.findBlockByColor(color);

        // then
        assertTrue(result.isPresent());
        assertEquals(color, result.get().getColor());
    }

    @Test
    void findBlockByColor_ShouldReturnEmptyOptional_WhenColorNull() {
        // given
        wall.addBlocks(List.of(block1, block2, block3, block4, block5,
                compositeBlock1, compositeBlock3));
        String color = null;

        // when
        Optional<Block> result = wall.findBlockByColor(color);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void findBlocksByMaterial_ShouldReturnEmptyList_WhenBlocksNotOnList() {
        // given
        // empty list of blocks
        String material = "non-existing material";

        // when
        List<Block> result = wall.findBlocksByMaterial(material);

        // then
        assertTrue(result.isEmpty());
    }

    @Test
    void findBlocksByMaterial_ShouldReturnEmptyList_WhenMaterialNull() {
        // given
        wall.addBlocks(List.of(block1, block2, block3, block4, block5,
                compositeBlock1, compositeBlock3));
        String material = null;

        // when
        List<Block> result = wall.findBlocksByMaterial(material);

        // then
        assertTrue(result.isEmpty());
    }

    @ParameterizedTest
    @ValueSource(strings = {"stone", "wood"})
    void findBlocksByMaterial_ShouldReturnAllBlocks_WhenMaterialOnList(String material) {
        // given material on list
        wall.addBlocks(List.of(block1, block2, block3, block4, block5,
                compositeBlock1, compositeBlock3));

        // when
        List<Block> result = wall.findBlocksByMaterial(material);

        // then
        assertTrue(result.size() > 0);
        assertTrue(result.stream()
                .allMatch(block -> block.getMaterial().equals(material)));
    }

    @Test
    void count_ShouldReturnZero_WhenNoBlocksOnList() {
        // given
        // empty list of blocks

        // when
        int result = wall.count();

        // then
        assertEquals(0, result);
    }

    @Test
    void count_ShouldReturnNumberOfBlocks_WhenBlocksOnList() {
        // given
        wall.addBlocks(List.of(block1, block2, block3, block4, block5,
                compositeBlock1, compositeBlock3));

        // when
        int result = wall.count();

        // then
        assertEquals(11, result);
    }
}
