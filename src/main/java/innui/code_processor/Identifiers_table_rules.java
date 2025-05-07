package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.modelos.errors.Oks;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public abstract class Identifiers_table_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;

    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Identifiers_table_rules.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_table_rules.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }

    public Analizer_rules analizer_rules;
    public Identifiers_tables identifiers_table;
    public Integer braces_num = 0;

    public Identifiers_table_rules(Code_scanners code_scanner) throws Exception {
        analizer_rules = new Analizer_rules(code_scanner);
        identifiers_table = new Identifiers_tables();
    }

    public abstract void load(Oks ok, Object... extras_array) throws Exception;

    /**
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return The backtrack position (null if no need to backtrack), or null if there is an error without backtrack info
     * @throws Exception
     */
    public abstract @Nullable Integer process(Scanner_rules.Basic_tokens basic_token, Oks ok, Object... extras_array) throws Exception;

}
