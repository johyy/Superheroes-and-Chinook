import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class Kata8Test {

    @Test
    public void validateCard_tooShort_ShouldReturnFalse() {
        Kata8 kata8 = new Kata8();
        assertEquals(false, kata8.validateCard("79927398714"));
    }

}