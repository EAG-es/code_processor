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

    public Code_scanners() throws Exception {
    }

    public Code_scanners.@Nullable Validators tokens_validator = null;
    public @Nullable Analyzer tokens_analizer = null;
    public @Nullable String code_tex = null;
    public Integer _pos = 0;
    public Integer _tokens_list_pos = -1;
    public Scanner_rules scanner_rules = new innui.code_processor.java.Scanner_rules();
    public List<Scanner_rules.Tokens> _tokens_list = new ArrayList<>();

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
            if (_tokens_list_pos < 0) {
                ok.setTex(Tr.in(k_in_route, "New token position is below of the token list aize. "));
                return -1;
            }
            return _tokens_list_pos;
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
            if (tokens_list_pos >= _tokens_list.size()) {
                ok.setTex(Tr.in(k_in_route, "New token position is over of the token list aize. "));
                return;
            }
            if (tokens_list_pos < 0) {
                ok.setTex(Tr.in(k_in_route, "New token position is below of the token list aize. "));
                return;
            }
            this._tokens_list_pos = tokens_list_pos;
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
            scan_resume(ok , extras_array);
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
            boolean is;
            int tam = _tokens_list.size();
            while (true) {
                _tokens_list_pos = _tokens_list_pos + 1;
                if (_tokens_list_pos >= tam) {
                    retorno = ok.valid(_scan_resume(true, ok, extras_array));
                    if (ok.is == false) return null;
                } else {
                    retorno = _tokens_list.get(_tokens_list_pos);
                }
                is = ok.valid(tokens_validator).validate_token(retorno, ok, extras_array);
                if (ok.is == false) break;
                if (is) {
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
                    break;
                }
                letra = ok.valid(code_tex).charAt(_pos);
                scanner_rules.process_character(letra, _pos, noted_ok, extras_array);
                if (Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_out)
                  || Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_in)) {
                    is_token_ready = true;
                    if (Oks.equals(noted_ok.id, Scanner_rules.k_end_of_toker_in)) {
                        _pos = _pos + 1;
                    }
                    noted_ok.init();
                    _tokens_list_pos = _tokens_list.size();
                    _tokens_list.add(scanner_rules.token);
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

    /**
     *
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     *
     */
    public void analize_token_with_token_analizer(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            boolean is;
            int tam = _tokens_list.size();
            while (true) {
                is = ok.valid(tokens_validator).validate_token(basic_token, ok, extras_array);
                if (ok.is == false) break;
                if (is == false) {
                    continue;
                }
                ok.valid(tokens_analizer).analyze_token(basic_token, ok, extras_array);
                if (ok.is == false) break;
                _tokens_list_pos = _tokens_list_pos + 1;
                if (_tokens_list_pos >= tam) {
                    break;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        return;
    }
}
