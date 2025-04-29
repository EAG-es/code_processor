package innui.code_processor;

import innui.Bases;
import innui.modelos.errors.Oks;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;

public abstract class Scanner_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path_name") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Scanner_rules.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Scanner_rules.k_in_route = (@Fenum("file_path_name") String) ("in/" + paquete_tex + "/in");
    }
    public static class Tokens {
        public String token_type = "";
        public int start_pos = 0;
        public int end_pos = 0;
        public int line_num = 0;
        public int col_num = 0;
    }
    public static @Fenum("error_id") String k_end_of_toker_out = "end_of_token_out";
    public static @Fenum("error_id") String k_end_of_toker_in = "end_of_token_in";

    public String token_tex = "";
    @Nullable
    public Tokens token = null;
    public Integer line_num = 0;
    public Integer col_num = 0;

    @Nullable
    public abstract Oks process_character(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception;

    @Nullable
    public abstract Oks reset_state(Oks ok, Object ... extras_array) throws Exception;

}
