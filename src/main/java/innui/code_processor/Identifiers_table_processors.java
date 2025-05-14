package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.code_processor.java.Identifiers_table_rules;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.ResourceBundle;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public class Identifiers_table_processors extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Identifiers_table_processors.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_table_processors.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }

    public Identifiers_table_rules identifiers_table_rule = Oks.allow_nulle(null);

    @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
    @SuppressWarnings("nullness:method.invocation")
    public Identifiers_table_processors(Identifiers_table_rules identifiers_table_rule) throws Exception {
        Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
        try {
            init(identifiers_table_rule, ok);
        } catch (Exception e) {
            ok.setTex(e);
        }
        if (ok.is == false) {
            throw new Exception(ok.tex);
        }
    }

    /**
     *
     * @param identifiers_table_rule
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void init(Identifiers_table_rules identifiers_table_rule, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            this.identifiers_table_rule = identifiers_table_rule;
            this.identifiers_table_rule.analizer_rules.code_scanner.tokens_validator
                    = (_token, _ok, _extras_array) -> {
                return validate_token(_token, _ok, _extras_array);
            };
            this.identifiers_table_rule.analizer_rules.code_scanner.analizer_from_start_rule
             = (_token, _ok, _extras_array) -> {
                return analize_tokens_from_start_rule(_token, _ok, _extras_array);
            };
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param file_name If null uses de default one
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void load_identifiers_table_rule(@Nullable String file_name, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            if (file_name != null) {
                ok.setTex(Tr.in(in, "Not implemented."));
            } else {
                identifiers_table_rule.load(ok, extras_array);
                if (ok.is == false) return;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param token
     * @param ok
     * @param extras_array
     * @return true if the tokes is valid for the analisys
     * @throws Exception
     */
    public boolean validate_token(Scanner_rules.Basic_tokens token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        boolean retorno = false;
        try {
            retorno = identifiers_table_rule.validate_token(token, ok, extras_array);
            if (ok.is == false) return false;
        } catch (Exception e) {
            ok.setTex(e);
            return false;
        }
        return retorno;
    }

    /**
     * Analizes tokens
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return true if success, false if fails, null if not finished or there is na error.
     * @throws Exception
     */
    public @Nullable Integer analize_tokens_from_start_rule(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Integer retorno = null;
        try {
            retorno = identifiers_table_rule.start_rule_processing(basic_token, ok, extras_array);
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void start(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            identifiers_table_rule.analizer_rules.code_scanner.start_scanner(ok , extras_array);
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

}
