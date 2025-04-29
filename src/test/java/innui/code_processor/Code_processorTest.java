package innui.code_processor;

import innui.modelos.tests.Test_methods;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 *
 * @author emilio
 */
@SuppressWarnings("nullness")
public class Code_processorTest {
    static {
        Test_methods.configure(false, true, false);
    }

    public Code_processorTest() {
    }

    @Test
    public void testMain_analyse_file() {
        System.out.println("main analyse file");
        String[] args = {
                "-af",
                "/home/emilio/proyectos/java/code_processor/src/main/java/innui/code_processor/Code_processor.java"
        };
        Code_processor.main(args);
    }
}
