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

public class Identifiers_table_processor extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Identifiers_table_processor.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_table_processor.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }

    @Nullable
    public Code_scanners code_scanner = null;
    @Nullable
    public Identifiers_table_rules identifiers_table_rule = null;

    public Oks init(Code_scanners code_scanner, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return ok;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            this.code_scanner = code_scanner;
            this.code_scanner.analizer = (_token, _ok, _extras_array) -> {
                return analize_token(_token, _ok, _extras_array);
            };
        } catch (Exception e) {
            ok.setTex(e);
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
                identifiers_table_rule = new Identifiers_table_rules();
                identifiers_table_rule.load(ok, extras_array);
                if (ok.is == false) return null;
            }
        } catch (Exception e) {
            ok.setTex(e);
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
            retorno = identifiers_table_rule.start(token, ok, extras_array);
            if (ok.is == false) return null;
        } catch (Exception e) {
            ok.setTex(e);
        }
        return retorno;
    }

    @Nullable
    public Oks identifiers_table_start(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            return code_scanner.scanner_start(ok , extras_array);
        } catch (Exception e) {
            ok.setTex(e);
        }
        return ok;
    }

}
