package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public abstract class Identifiers_table_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;

    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Identifiers_table_rules.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_table_rules.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }

    public Analizer_rules analizer_rules;
    public Identifiers_tables identifiers_table;
    public Integer braces_num = 0;

    public Identifiers_table_rules(Code_scanners code_scanner) throws Exception {
        analizer_rules = new Analizer_rules(code_scanner);
        identifiers_table = new Identifiers_tables();
    }

    public abstract void load(Oks ok, Object... extras_array) throws Exception;

    /**
     *
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public @Nullable Integer start_rule_processing(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Integer retorno = null;
        try {
            ok.valid(analizer_rules.start_rule_node).evaluate(basic_token, ok, extras_array);
            if (ok.is == false) return null;
            retorno = analizer_rules.backtrack_pos;
            analizer_rules.backtrack_pos = null;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

}
