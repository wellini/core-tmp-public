package io.roadmaps.core.trash;

public interface HashGenerator {

    int randomInt(int min, int max);

    /**
     * Возвращает int от Integer.MIN_VALUE до Integer.MAX_VALUE
     * @return
     */
    int randomInt();

    /**
     * Случайная строка из цифр и латинских букв
     * @param maxLength
     * @return
     */
    String randomAlphanumeric(int maxLength);

    /**
     * Случайная строка из латинских букв
     * @param maxLength
     * @return
     */
    String randomAlphabetic(int maxLength);

    /**
     * Необходимо вернуть случайный цвет. Возвращаемое значение должно формироваться так: 0xAABBCCDD,
     * где AA, BB, CC, DD – уровни каналов соответствующие порядку RGBA (red, green, blue, alpha). Канал прозрачности можно принять константным и всегда равным 255
     * @return
     */
    int randomColor();

    /**
     * Меняет значение сида в хэш-функции так, чтобы в дельнейшем генерировались новые значения
     */
    void shake();
}
