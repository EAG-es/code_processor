package innui.code_processor.java;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.code_processor.Identifiers_table_after_successes;
import innui.code_processor.Identifiers_tables;
import innui.code_processor.Scanner_rules;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;

import java.io.File;

import static innui.Serial_version.getSerial_version_uid;
import static innui.code_processor.java.Scanner_rules.Token_types.parenthesis_open;

@SuppressFBWarnings({"CT_CONSTRUCTOR_THROW", "MS_SHOULD_BE_FINAL"})
public class Identifiers_table_after_sucesses extends Identifiers_table_after_successes {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Identifiers_table_after_sucesses.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_table_after_sucesses.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }

    @SuppressWarnings({"nullness:method.invocation", "nullness:initialization.fields.uninitialized"})
    public Identifiers_table_after_sucesses() throws Exception {
        Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
        try {
            init(ok);
        } catch (Exception e) {
            ok.setTex(ok.allow_null(e.getMessage()));
        }
        if (ok.is == false) {
            throw new Exception(ok.getTex());
        }
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void init(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            put_once("to_semi_colon"
                , (_basic_token, _ok, _extras_array) -> {
                        ok.valid(_identifiers_table_rule).identifiers_table.create_top_table(ok.valid(_identifiers_table_rule).braces_num, _ok, _extras_array);
                        if (_ok.is == false) return;
                        ok.valid(_identifiers_table_rule).identifiers_table.put_identifier(true, _ok, _extras_array);
                        if (_ok.is == false) return;
                    }
                    ,ok, extras_array);
            put_once("to_package_identifier_1"
                , (_basic_token, _ok, _extras_array) -> {
                        if (ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.namespace.isEmpty()) {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.namespace
                                    = ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.name;
                        } else {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.namespace
                                    = ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.namespace
                                    + "."
                                    + ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.name;
                        }
                        ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.name = _basic_token.token_tex;
                    }
                    ,ok, extras_array);
            put_once("to_package_identifier"
                    , (_basic_token, _ok, _extras_array) -> {
                        ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.name = _basic_token.token_tex;
                        ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.type = Identifiers_table_rules.Types.token_package.name();
                    }
                    ,ok, extras_array);
            put_once("to_package"
                    , (_basic_token, _ok, _extras_array) -> {
                        ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.init(_ok, _extras_array);
                    }
                    ,ok, extras_array);
            put_once("to_class_1<optional><repeat>"
                    , (_basic_token, _ok, _extras_array) -> {
                        ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.properties_list.add(new Scanner_rules.Basic_tokens(_basic_token.token_type
                                , _basic_token.token_tex));
                    }
                    ,ok, extras_array);
            put_once("to_class_2"
                    , (_basic_token, _ok, _extras_array) -> {
                        String name = _basic_token.token_type;
                        if (Identifiers_table_rules.Types.token_class.name().equals(name)) {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.type = Identifiers_table_rules.Types.token_class.name();
                        } else if (Identifiers_table_rules.Types.token_interface.name().equals(name)) {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.type = Identifiers_table_rules.Types.token_interface.name();
                        } else if (Identifiers_table_rules.Types.token_enum.name().equals(name)) {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.type = Identifiers_table_rules.Types.token_enum.name();
                        } else if (Identifiers_table_rules.Types.token_record.name().equals(name)) {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.type = Identifiers_table_rules.Types.token_record.name();
                        } else {
                            _ok.setTex(Tr.in(k_in_route, "Not recognized type. "));
                        }
                    }
                    ,ok, extras_array);
            put_once("to_class_identifier"
                    , (_basic_token, _ok, _extras_array) -> {
                        ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.name = _basic_token.token_tex;
                        Identifiers_tables.Identifiers identifier = _ok.valid(ok.valid(_identifiers_table_rule).identifiers_table.put_identifier(_ok, _extras_array));
                        ok.valid(_identifiers_table_rule).next_block_identifier = new Identifiers_tables.Identifiers(identifier);
                    }
                    ,ok, extras_array);
            put_once("to_identifier_type"
                    , (_basic_token, _ok, _extras_array) -> {
                        if (ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.return_class.isEmpty()) {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.return_class = _basic_token.token_tex;
                        } else {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.return_class = ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.return_class
                                    + "." + _basic_token.token_tex;
                        }
                    }
                    ,ok, extras_array);
            put_once("to_identifier"
                    , (_basic_token, _ok, _extras_array) -> {
                        ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.name = _basic_token.token_tex;
                    }
                    ,ok, extras_array);
            put_once("to_identifier_type_class"
                    , (_basic_token, _ok, _extras_array) -> {
                        if (ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.return_class.isEmpty()) {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.return_class = _basic_token.token_tex;
                        } else {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.return_class = ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.return_class
                                    + "." + _basic_token.token_tex;
                        }
                    }
                    ,ok, extras_array);
            put_once("ro_parenthesis_open_or_semicolon"
                    , (_basic_token, _ok, _extras_array) -> {
                        if (ok.valid(_identifiers_table_rule).next_block_identifier != null) {
                            Identifiers_tables.Temporary_identifiers_tables temporary_identifiers_table
                                    = _ok.valid(ok.valid(_identifiers_table_rule).identifiers_table.get_top(_ok, _extras_array));
                            if (_ok.is == false) return;
                            temporary_identifiers_table.block_identifier = ok.valid(_identifiers_table_rule).next_block_identifier;
                            ok.valid(_identifiers_table_rule).next_block_identifier = null;
                        }
                        if (_basic_token.token_type.equals(parenthesis_open.name())) {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.type = Identifiers_table_rules.Types.method.name();
                        } else {
                            ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.type = Identifiers_table_rules.Types.attribute.name();
                        }
                        ok.valid(_identifiers_table_rule).identifiers_table.put_identifier(_ok, _extras_array);
                        if (_ok.is == false) return;
                    }
                    ,ok, extras_array);
            put_once("to_method_or_attribute_or_class<ignore>"
                    , (_basic_token, _ok, _extras_array) -> {
                        ok.valid(_identifiers_table_rule).identifiers_table.new_identifier.properties_list.add(new Scanner_rules.Basic_tokens(_basic_token.token_type
                                , _basic_token.token_tex));
                    }
            ,ok, extras_array);
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     * 
     * @param key
     * @param value
     */
    public void put_once(String key, Identifiers_table_after_successes.After_success_calls value, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            if (_after_success_calls_map.containsKey(key)) {
                ok.setTex(Tr.in(k_in_route, "Success call already setup. ") + key);
            }
            _after_success_calls_map.put(key, value);
        } catch (Exception e) {
            ok.setTex(e);
        }
    }
}
