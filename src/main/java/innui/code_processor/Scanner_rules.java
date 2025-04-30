package innui.code_processor;

import innui.Bases;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.io.Serializable;
import java.util.ResourceBundle;

public abstract class Scanner_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Scanner_rules.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Scanner_rules.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }
    public static class Basic_tokens implements Serializable {
        public String token_tex = "";
        public String token_type = "";

        public Basic_tokens() throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        public Basic_tokens(Basic_tokens basic_token) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(basic_token, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }
        /**
         *
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public Oks init(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                this.token_tex = "";
                this.token_type = "";
            } catch (Exception e) {
                ok.setTex(e);
            }
            return ok;
        }

        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public Oks init(Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                this.token_tex = basic_token.token_tex;
                this.token_type = basic_token.token_type;
            } catch (Exception e) {
                ok.setTex(e);
            }
            return ok;
        }
    }
    public static class Tokens extends Basic_tokens {
        public int start_pos = 0;
        public int end_pos = 0;
        public int line_num = 1;
        public int col_num = 1;
    }
    public static @Fenum("error_id") String k_end_of_toker_out = (@Fenum("error_id") String) "end_of_token_out";
    public static @Fenum("error_id") String k_end_of_toker_in = (@Fenum("error_id") String)"end_of_token_in";

    public Tokens token = new Tokens();
    public Integer line_num = 1;
    public Integer col_num = 1;

    @Nullable
    public abstract Oks process_character(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception;

    @Nullable
    public abstract Oks reset_state(Oks ok, Object ... extras_array) throws Exception;

}
