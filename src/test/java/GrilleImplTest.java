import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;

import static org.junit.jupiter.api.Assertions.*;

class GrilleImplTest {

    private GrilleImpl grille;
    @Rule
    private ExpectedException thrown = ExpectedException.none();

    @BeforeEach
    void setUp() {
        grille = new GrilleImpl(3);
        grille.setValue(0,0, '3');
        grille.setValue(0,1, '1');
    }

    @Test
    void getDimension() {
        assertEquals(3, grille.getDimension());
    }

    @Test
    void setValue() {
       grille.setValue(2,2, '2');
       assertEquals('2', grille.getValue(2, 2));
    }

    @Test
    void getValue() {
        assertEquals('1', grille.getValue(0, 1));
    }

    @Test
    void complete() {
        assertTrue(grille.complete());
    }

    @Test
    void should_return_false_when_grille_is_not_complete() {
        grille.setValue(2,1, Grille.EMPTY);
        assertFalse(grille.complete());
    }

    @Test
    void possible() {
        assertTrue(grille.possible(3, 3, '3'));
    }

    @Test
    void should_thrown_exception_when_is_not_possible() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> grille.possible(-10, 10, '3'));
    }

    @Test
    void should_thrown_exception_when_setting_value() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> grille.setValue(0,0, '3'));
    }

    @Test
    void should_thrown_exception_when_character_is_not_autorized() {
        grille.setValue(2,1, Grille.EMPTY);
        Assertions.assertThrows(IllegalArgumentException.class, () -> grille.possible(2, 1, '/'));
    }

}