package org.jasinski;

import java.util.List;
import java.util.Optional;

public interface Structure {
    /**
     * returns any element of given color
     *
     * @param color {@link String}
     * @return {@link Optional Optional} of {@link Block Block}
     */
    Optional<Block> findBlockByColor(String color);

    /**
     * returns all elements of given material
     *
     * @param material {@link String}
     * @return {@link List List} of {@link Block Blocks}
     * matching given material
     */
    List<Block> findBlocksByMaterial(String material);

    /**
     * @return number of elements creating the structure
     */
    int count();
}
