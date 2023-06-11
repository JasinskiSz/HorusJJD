# Recruitment task based on Composite Pattern

Recruitment project/task for Java Developer position.

## Task description (Polish)

"(...) zaimplementowanie metod findBlockByColor, findBlocksByMaterial, count w klasie Wall - najchętniej unikając
powielania kodu i umieszczając całą logikę w klasie Wall. (...)"

```interface Structure {
// zwraca dowolny element o podanym kolorze
Optional<Block> findBlockByColor(String color);

// zwraca wszystkie elementy z danego materiału
List<Block> findBlocksByMaterial(String material);

//zwraca liczbę wszystkich elementów tworzących strukturę
int count();
}

public class Wall implements Structure {
private List<Block> blocks;
}

interface Block {
String getColor();
String getMaterial();
}

interface CompositeBlock extends Block {
List<Block> getBlocks();
}
```

## Author

- Szymon Jasińsi - [@JasinskiSz](https://www.github.com/JasinskiSz)

