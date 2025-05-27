package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

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

    public Analyzer_rules analyizer_rule;
    public Identifiers_tables identifiers_table;
    public Identifiers_tables. @Nullable Identifiers next_block_identifier = null;
    public Integer braces_num = 0;
    public List<Identifiers_tables.Identifiers> all_identifiers_list = new ArrayList<>();

    public Identifiers_table_rules(Code_scanners code_scanner) throws Exception {
        analyizer_rule = new Analyzer_rules(code_scanner);
        identifiers_table = new Identifiers_tables();
    }

    public abstract void load(Oks ok, Object... extras_array) throws Exception;

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Analyzer_rules.@Nullable Rule_nodes get_start_rule(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Integer retorno = null;
        try {
            return ok.valid(analyizer_rule.start_rule_node);
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
    }

    /**
     *
     * @param class_name
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void set_after_success_calls(String class_name, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            Object object = Class.forName(class_name).getConstructor().newInstance();
            if ((object instanceof innui.code_processor.Identifiers_table_after_successes) == false) {
                ok.setTex(Tr.in(k_in_route, "Class loaded is not instance of ") + innui.code_processor.Identifiers_table_after_successes.class.getCanonicalName());
                return;
            }
            innui.code_processor.Identifiers_table_after_successes after_success = (Identifiers_table_after_successes) Oks.no_fenum_cast(object);
            after_success.set_identifiers_table(this);
            Analyzer_rules.Rule_nodes rule_node;
            Oks noted_ok = ok.create_new();
            for (var entry: after_success.get_after_success_calls_map().entrySet()) {
                rule_node = analyizer_rule.rule_nodes_map.get(entry.getKey());
                if (rule_node != null) {
                    rule_node.defined_rule_success = new Analyzer_rules.Rule_success(entry.getValue());
                } else {
                    ok.addTex(Tr.in(k_in_route, "Rule not found: ") + entry.getKey());
                    noted_ok.init();
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param file
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void set_rules_list_yaml(File file,  Oks ok, Object ... extras_array) throws Exception {
        analyizer_rule.set_rules_list_yaml(file, ok, extras_array);
    }
}
