package innui.code_processor;

import innui.Bases;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.ResourceBundle;

import static innui.code_processor.java.Scanner_rules.Token_types.*;

public abstract class Identifiers_table_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;

    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Identifiers_table_rules.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_table_rules.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }

    public Analizer_rules analizer_rules;
    public Identifiers_tables identifiers_table;

    public Identifiers_table_rules(Code_scanners code_scanner) {
        analizer_rules = new Analizer_rules(code_scanner);
        identifiers_table = new Identifiers_tables();
    }

    @Nullable
    public abstract Oks load(Oks ok, Object... extras_array) throws Exception;

    /**
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public abstract Boolean process(Scanner_rules.Basic_tokens basic_token, Oks ok, Object... extras_array) throws Exception;

}
