package innui.code_processor;

import innui.Bases;
import innui.code_processor.java.Identifiers_table_rules;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.ResourceBundle;

import static innui.code_processor.Analizer_rules.k_is_bad_way;
import static innui.code_processor.Code_scanners.k_need_previous_token;

public class Identifiers_table_processors extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Identifiers_table_processors.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_table_processors.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }

    @Nullable
    public Identifiers_table_rules identifiers_table_rule = null;

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
    public Oks init(Identifiers_table_rules identifiers_table_rule, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return ok;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            this.identifiers_table_rule = identifiers_table_rule;
            this.identifiers_table_rule.analizer_rules.code_scanner.analizer
             = (_token, _ok, _extras_array) -> {
                return analize_token(_token, _ok, _extras_array);
            };
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }

    /**
     *
     * @param file_name If null uses de default one
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public Oks load_identifiers_table_rule(@Nullable String file_name, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            if (file_name != null) {
                ok.setTex(Tr.in(in, "Not implemented."));
                return null;
            } else {
                identifiers_table_rule.load(ok, extras_array);
                if (ok.is == false) return null;
            }
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }

    /**
     * Analizes tokens
     * @param token
     * @param ok
     * @param extras_array
     * @return true if success, false if fails, null if not finished or there is na error.
     * @throws Exception
     */
    @Nullable
    public Boolean analize_token(Scanner_rules.Basic_tokens token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Boolean retorno = null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            retorno = identifiers_table_rule.process(token, ok, extras_array);
            if (ok.is == false) {
                if (ok.equals(ok.id, k_is_bad_way)) {
                    ok.setTex(k_need_previous_token);
                    ok.id = k_need_previous_token;
                }
                return null;
            }
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

    @Nullable
    public Oks start(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            return identifiers_table_rule.analizer_rules.code_scanner.start_scanner(ok , extras_array);
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
    }

}
