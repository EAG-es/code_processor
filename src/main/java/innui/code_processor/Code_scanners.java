package innui.code_processor;

import innui.Bases;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;

public class Code_scanners extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Code_scanners.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Code_scanners.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }
    public static @Fenum("error_id") String k_need_previous_token = (@Fenum("error_id") String) "need_previous_token";

    public Code_scanners() throws Exception {
    }

    public interface Analizers {
        public Boolean analize(Scanner_rules.Basic_tokens token, Oks ok, Object ... extras_array) throws Exception;
    }

    public Code_scanners.@Nullable Analizers analizer = null;
    @Nullable
    public String code_tex = null;
    public int pos = 0;
    public Scanner_rules scanner_rules = new innui.code_processor.java.Scanner_rules();
    public List<Scanner_rules.Tokens> tokens_list = new ArrayList<>();
    /**
     *
     * @param file_tex
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Oks load_file(String file_tex, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return ok;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            File file = new File(file_tex);
            code_tex = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }

    @Nullable
    public Oks start_scanner(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            pos = 0;
            return scan_resume(ok , extras_array);
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public Oks scan_resume(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Oks noted_ok = ok.create_new(extras_array);
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            if (code_tex == null) {
                ok.setTex(Tr.in(in, "Not loaded file. "));
                return null;
            }
            int tam = code_tex.length();
            char letra;
            while (true) {
                if (pos >= tam) {
                    break;
                }
                letra = ok.valid(code_tex).charAt(pos);
                ok.allow_null(scanner_rules.process_character(letra, pos, noted_ok, extras_array));
                if (Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_out)
                  || Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_in)) {
                    analize_token(scanner_rules, noted_ok, extras_array);
                    if (noted_ok.is == false) {
                        ok.addTex(noted_ok.getTex());
                        noted_ok.init();
                    }
                    if (Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_out)) {
                        pos = pos - 1;
                    }
                }
                if (noted_ok.is == false) {
                    ok.valid(scanner_rules.reset_state(ok, extras_array));
                    ok.addTex(noted_ok.getTex());
                }
                noted_ok.init();
                pos = pos + 1;
            }
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }

    /**
     *
     * @param scanner_rules
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public Oks analize_token(Scanner_rules scanner_rules, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            tokens_list.add(scanner_rules.token);
            if (analizer != null) {
                int tam = tokens_list.size();
                int i = tam - 1;
                Scanner_rules.Tokens token = scanner_rules.token;
                while (true) {
                    analizer.analize(token, ok, extras_array);
                    if (ok.is == false) {
                        if (ok.equals(ok.id, k_need_previous_token)) {
                            i = i - 1;
                            if (i < 0) {
                                ok.setTex(Tr.in(in, "Reached the previous position to the beginning of the tokens list. "));
                                return null;
                            }
                            token = tokens_list.get(i);
                        } else {
                            return null;
                        }
                    } else {
                        i = i + 1;
                    }
                    if (i >= tam) {
                        break;
                    }
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }
}
