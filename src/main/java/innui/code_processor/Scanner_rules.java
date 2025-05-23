package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;

import java.io.File;
import java.io.Serializable;
import java.util.ResourceBundle;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public abstract class Scanner_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Scanner_rules.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Scanner_rules.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }

    protected Scanner_rules() throws Exception {
    }

    public static class Basic_tokens implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public String token_tex = "";
        public String token_type = "";

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
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

        /**
         *
         * @param token_type
         * @throws Exception
         */
        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Basic_tokens(String token_type) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(ok);
                this.token_type = token_type;
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        /**
         *
         * @param token_type
         * @param token_tex
         * @throws Exception
         */
        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Basic_tokens(String token_type, String token_tex) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(ok);
                this.token_type = token_type;
                this.token_tex = token_tex;
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
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
        public void init(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                this.token_tex = "";
                this.token_type = "";
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init(Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                this.token_tex = basic_token.token_tex;
                this.token_type = basic_token.token_type;
            } catch (Exception e) {
                ok.setTex(e);
            }
        }
    }
    public static class Tokens extends Basic_tokens {
        public int start_pos = 0;
        public int end_pos = 0;
        public int line_num = 1;
        public int col_num = 1;

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Tokens() throws Exception {
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

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Tokens(Tokens token) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(token, ok);
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
        public void init(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                start_pos = 0;
                end_pos = 0;
                line_num = 1;
                col_num = 1;
                super.init(ok, extras_array);
                if (ok.is == false) return;
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        /**
         *
         * @param token
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init(Tokens token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                start_pos = token.start_pos;
                end_pos = token.end_pos;
                line_num = token.line_num;
                col_num = token.col_num;
                super.init(token, ok, extras_array);
                if (ok.is == false) return;
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }
    }
    public static @Fenum("error_id") String k_end_of_toker_out = Oks.no_fenum_cast("end_of_token_out");
    public static @Fenum("error_id") String k_end_of_toker_in = Oks.no_fenum_cast("end_of_token_in");

    public Tokens token = new Tokens();
    public Integer line_num = 1;
    public Integer col_num = 1;

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public abstract void process_character(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception;

    /**
     * @param is_pos_out
     * @param character
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public abstract void process_token_end(boolean is_pos_out, Character character, Oks ok, Object ... extras_array) throws Exception;

    /**
     *
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public abstract void reset_state(Oks ok, Object ... extras_array) throws Exception;

}
