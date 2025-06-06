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
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public class Code_scanners extends Bases implements I_code_scanners{
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
    public static @Fenum("error_id") String k_end_of_text = (@Fenum("error_id") String) "end_of_text";

    public Code_scanners() throws Exception {
    }

    public Code_scanners.@Nullable Validators tokens_validator = null;
    public @Nullable Analyzer tokens_analizer = null;
    public @Nullable String code_tex = null;
    public Integer _pos = 0;
    public Integer _valid_tokens_list_pos = 0;
    public Scanner_rules scanner_rules = new innui.code_processor.java.Scanner_rules();
    public List<Scanner_rules.Tokens> _valid_tokens_list = new ArrayList<>();

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Override
    public Integer get_tokens_list_pos(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return -1;
        try {
            if (_valid_tokens_list_pos < 0) {
                ok.setTex(Tr.in(k_in_route, "New token position is below of the token list aize. "));
                return -1;
            }
            return _valid_tokens_list_pos - 1;
        } catch (Exception e) {
            ok.setTex(e);
        }
        return -1;
    }

    /**
     *
     * @param tokens_list_pos
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    @Override
    public void set_tokens_list_pos(Integer tokens_list_pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            if (tokens_list_pos > _valid_tokens_list.size()) {
                ok.setTex(Tr.in(k_in_route, "New token position is over of the token list size. "));
                return;
            }
            if (tokens_list_pos < 0) {
                ok.setTex(Tr.in(k_in_route, "New token position is below of the token list size. "));
                return;
            }
            this._valid_tokens_list_pos = tokens_list_pos;
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    @Override
    public void set_tokens_validator(Validators validator, Oks ok, Object... extras_array) throws Exception {
        this.tokens_validator = validator;
    }

    @Override
    public void set_tokens_analizer(Analyzer analizer, Oks ok, Object... extras_array) throws Exception {
        this.tokens_analizer = analizer;
    }
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

    /**
     *
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    @Override
    public void start_scanner(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            _pos = 0;
            _analyze_tokens(ok, extras_array);
            if (Oks.equals(ok.id, k_end_of_text)) {
                ok.init();
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @throws Exception
     */
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
    @Override
    public Scanner_rules.@Nullable Basic_tokens scan_next(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Scanner_rules.Basic_tokens retorno;
        try {
            boolean is_valid;
            boolean is_new_token = false;
            int tam = _valid_tokens_list.size();
            while (true) {
                if (_valid_tokens_list_pos >= tam) {
                    retorno = ok.allow_null(_scan_resume(true, ok, extras_array));
                    if (ok.is == false) return null;
                    is_new_token = true;
                } else {
                    retorno = _valid_tokens_list.get(_valid_tokens_list_pos);
                    _valid_tokens_list_pos = _valid_tokens_list_pos + 1;
                    is_new_token = false;
                }
                is_valid = ok.valid(tokens_validator).validate_token(is_new_token, retorno, ok, extras_array);
                if (ok.is == false) break;
                if (is_valid) {
                    if (is_new_token) {
                        _valid_tokens_list.add(scanner_rules.token);
                        _valid_tokens_list_pos = _valid_tokens_list.size();
                    }
                    break;
                }
            }
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
                if (_pos >= tam) {
                    ok.id = k_end_of_text;
                    ok.setTex(Oks.no_fenum_cast(k_end_of_text));
                    break;
                }
                letra = noted_ok.valid(code_tex).charAt(_pos);
                scanner_rules.process_character(letra, _pos, noted_ok, extras_array);
                if (Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_out)
                  || Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_in)) {
                    is_token_ready = true;
                    if (Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_in)) {
                        noted_ok.init();
                        scanner_rules.process_token_end(false, letra, noted_ok, extras_array);
                        if (noted_ok.is == false) {
                            ok.addTex(noted_ok.getTex());
                            noted_ok.init();
                        }
                        _pos = _pos + 1;
                    } else {
                        noted_ok.init();
                        scanner_rules.process_token_end(true, letra, noted_ok, extras_array);
                        if (noted_ok.is == false) {
                            ok.addTex(noted_ok.getTex());
                            noted_ok.init();
                        }
                    }
                    if (is_just_read == false) {
                        process_new_token_scanned(scanner_rules.token, noted_ok, extras_array);
                        if (noted_ok.is == false) {
                            ok.addTex(noted_ok.getTex());
                            noted_ok.init();
                        }
                    } else {
                        return scanner_rules.token;
                    }
                } else {
                    _pos = _pos + 1;
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

    public void process_new_token_scanned(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {

    }
    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     *
     */
    public void _analyze_tokens(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            Scanner_rules.Basic_tokens basic_token = ok.allow_null(scan_next(ok , extras_array));
            if (Oks.equals(ok.id, k_end_of_text)) {
                ok.init();
                return;
            };
            if (ok.is == false) return;
            ok.valid(tokens_analizer).analyze_token(basic_token, ok, extras_array);
            if (ok.is == false) return;
        } catch (Exception e) {
            ok.setTex(e);
        }
    }
}
