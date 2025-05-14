package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.apache.commons.io.FilenameUtils;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public class Code_scanners extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Code_scanners.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Code_scanners.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }
    public static @Fenum("error_id") String k_need_token_backtrack = Oks.no_fenum_cast("need_token_backtrack");
    public static @Fenum("error_id") String k_need_token_last = Oks.no_fenum_cast("need_token_last");

    public Code_scanners() throws Exception {
    }

    public interface Validators extends Serializable {
        boolean validate_token(Scanner_rules.Basic_tokens token, Oks ok, Object ... extras_array) throws Exception;
    }

    @FunctionalInterface
    public interface Analizers extends Serializable {
        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return The next position of the tokens stack to use, or null (if you use the next token to read)
         * @throws Exception
         */
        @Nullable Integer analize(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception;
    }

    public Code_scanners.@Nullable Validators tokens_validator = null;
    public Code_scanners.@Nullable Analizers analizer_from_start_rule = null;
    public @Nullable String code_tex = null;
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
    public void load_file(String file_tex, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            File file = new File(FilenameUtils.normalize(file_tex));
            code_tex = Files.readString(file.toPath(), StandardCharsets.UTF_8);
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    public void start_scanner(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            pos = 0;
            scan_resume(ok , extras_array);
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    public void scan_resume(Oks ok, Object ... extras_array) throws Exception {
        _scan_resume(false, ok, extras_array);
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Scanner_rules.@Nullable Basic_tokens scan_next(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Scanner_rules.Basic_tokens retorno;
        try {
            boolean is;
            do {
                retorno = ok.valid(_scan_resume(true, ok, extras_array));
                if (ok.is == false) return null;
                is = ok.valid(tokens_validator).validate_token(retorno, ok, extras_array);
                if (ok.is == false) return null;
            } while (is == false);
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
     * @return
     * @throws Exception
     */
    public Scanner_rules.@Nullable Basic_tokens _scan_resume(boolean is_just_read, Oks ok, Object ... extras_array) throws Exception {
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
            boolean is_token_ready;
            while (true) {
                is_token_ready = false;
                if (pos >= tam) {
                    break;
                }
                letra = ok.valid(code_tex).charAt(pos);
                scanner_rules.process_character(letra, pos, noted_ok, extras_array);
                if (Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_out)
                  || Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_in)) {
                    is_token_ready = true;
                    if (Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_in)) {
                        pos = pos + 1;
                    }
                    noted_ok.init();
                    tokens_list.add(scanner_rules.token);
                    if (is_just_read == false) {
                        analize_token_with_token_analizer(scanner_rules.token, noted_ok, extras_array);
                        if (noted_ok.is == false) {
                            ok.addTex(noted_ok.getTex());
                            noted_ok.init();
                        }
                    } else {
                        return scanner_rules.token;
                    }
                } else {
                    pos = pos + 1;
                }
                if (noted_ok.is == false) {
                    scanner_rules.reset_state(ok, extras_array);
                    ok.addTex(noted_ok.getTex());
                    noted_ok.init();
                }
                if (is_token_ready) {
                    if (is_just_read) {
                        return scanner_rules.token;
                    }
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        return null;
    }

    /**
     *
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     *
     */
    public @Nullable Boolean analize_token_with_token_analizer(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        return analize_token_with_rule(basic_token, null, ok, extras_array);
    }

    /**
     *
     * @param basic_token
     * @param rule_node if null will use the token_analizer attribute
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public @Nullable Boolean analize_token_with_rule(Scanner_rules.Basic_tokens basic_token, Analizer_rules.@Nullable Rule_nodes rule_node, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Boolean retorno = null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            Integer backtrack_pos = null;
            boolean is;
            Analizer_rules.@Nullable Rule_nodes rule;
            int tam = tokens_list.size();
            int i = tam - 1;
            if (rule_node != null) {
                if (rule_node.defined_analizer_rules.backtrack_pos != null) {
                    i = rule_node.defined_analizer_rules.backtrack_pos;
                    if (i < 0) {
                        ok.setTex(Tr.in(in, "Reached the previous position to the beginning of the tokens list. "));
                        return null;
                    }
                    basic_token = tokens_list.get(i);
                }
            }
            while (true) {
                is = ok.valid(tokens_validator).validate_token(basic_token, ok, extras_array);
                if (ok.is == false) break;
                if (is == false) {
                    continue;
                }
                if (rule_node == null) {
                    if (analizer_from_start_rule != null) {
                        backtrack_pos = ok.valid(analizer_from_start_rule).analize(basic_token, ok, extras_array);
                        if (ok.is == false) break;
                        retorno = true;
                    } else {
                        break;
                    }
                } else {
                    rule = ok.valid(rule_node);
                    retorno = rule.evaluate(basic_token, ok, extras_array);
                    if (ok.is == false) break;
                    backtrack_pos = rule.defined_analizer_rules.backtrack_pos;
                }
                if (Oks.equals(ok.id, k_need_token_backtrack)) {
                    ok.init();
                    if (backtrack_pos == null) {
                        ok.setTex(Tr.in(in, "Needed backtrack not defined. "));
                        return null;
                    }
                    i = backtrack_pos;
                    if (i < 0) {
                        ok.setTex(Tr.in(in, "Reached the previous position to the beginning of the tokens list. "));
                        return null;
                    }
                    basic_token = tokens_list.get(i);
                } else if (Oks.equals(ok.id, k_need_token_last)) {
                    ok.init();
                } else {
                    i = i + 1;
                }
                if (i >= tam) {
                    break;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        return retorno;
    }
}
