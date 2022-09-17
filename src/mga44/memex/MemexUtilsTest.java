package mga44.memex;

import mga44.io.ndtl.MemexEntity;
import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.assertEquals;

class MemexUtilsTest {

    @Test
    public void savingSingleLineEntityShouldReturnSimpleEntity() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Method m = getPrepareEntityToSavingMethod();
        MemexEntity e = new MemexEntity.MemexEntityBuilder("TestTitle").withNote("TestNote").build();
        String expected = "TestTitle" + System.lineSeparator() + "  NOTE : TestNote" + System.lineSeparator() + "  DONE : false" + System.lineSeparator() + "  REVI : false" + System.lineSeparator();

        assertEquals(expected, m.invoke(new MemexUtils(), e));

    }

    @Test
    public void savingMultilineEntityShouldReturnMultilineEntity() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        String expected =
                """
                        TestTitle
                          NOTE :\s
                            > firstLine
                            > secondLine
                          DONE : false
                          REVI : false
                        """;
        MemexEntity e = new MemexEntity.MemexEntityBuilder("TestTitle").withNote("firstLine" + System.lineSeparator() + "secondLine").build();

        assertEquals(expected.replace("\n", System.lineSeparator()), getPrepareEntityToSavingMethod().invoke(new MemexUtils(), e));
    }


    private Method getPrepareEntityToSavingMethod() throws NoSuchMethodException {
        Method method = MemexUtils.class.getDeclaredMethod("prepareEntityToSaving", MemexEntity.class);
        method.setAccessible(true);

        return method;
    }
}