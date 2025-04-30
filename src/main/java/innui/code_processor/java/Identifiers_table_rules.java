package innui.code_processor.java;

import innui.Bases;
import innui.code_processor.Analizer_rules;
import innui.code_processor.Scanner_rules;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.ResourceBundle;

public class Identifiers_table_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Identifiers_table_rules.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_table_rules.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }

    @Nullable
    public Analizer_rules analizer_rules = null;

    public Oks load(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return ok;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            analizer_rules = new Analizer_rules();
            analizer_rules.start_rule_node = new Analizer_rules.Rule_node_find_ways();
        } catch (Exception e) {
            ok.setTex(e);
        }
        return ok;
    }

    @Nullable
    public Boolean start(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Boolean retorno = null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            retorno = ok.valid(ok.valid(analizer_rules).start_rule_node).evaluate(basic_token, ok, extras_array);

        } catch (Exception e) {
            ok.setTex(e);
        }
        return retorno;
    }
}
