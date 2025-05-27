package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.modelos.tests.Test_methods;
import org.junit.Ignore;
import org.junit.Test;

import static org.junit.Assert.fail;

/**
 *
 * @author emilio
 */
@SuppressWarnings("nullness")
@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public class Code_processorTest {
    static {
        Test_methods.configure(false, true, false);
    }

    public Code_processorTest() {
    }

    @Ignore
    public void testMain_analyse_file() {
        System.out.println("main analyse file");
        String[] args = {
                "-af",
                "/home/emilio/proyectos/java/code_processor/src/main/java/innui/code_processor/Code_processor.java"
        };
        Code_processor.main(args);
    }

    @Test
    public void testMain_get_identifiers_table() {
        System.out.println("main get identifiers table");
        String[] args = {
                "-gitam",
                "/home/emilio/proyectos/java/code_processor/src/main/java/innui/code_processor/Code_processor.java"
        };
        Code_processor.main(args);
    }

    @Test
    public void testMain_analize_rules_download() {
        System.out.println("main analyze rules download");
        String[] args = {
                "-ard",
                "/home/emilio/proyectos/java/code_processor/src/main/resources/analyze_rules_download.yaml"
        };
        Code_processor.main(args);
    }

    @Test
    public void testMain_analize_rules_upload() {
        System.out.println("main analyze rules upload");
        String[] args = {
                "-aru",
                "/home/emilio/proyectos/java/code_processor/src/main/resources/analyze_rules_download.yaml",
                "innui.code_processor.java.Identifiers_table_after_sucesses"
        };
        Code_processor.main(args);
    }
}
