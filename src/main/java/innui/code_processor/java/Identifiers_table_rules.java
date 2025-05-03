package innui.code_processor.java;

import innui.Bases;
import innui.code_processor.Analizer_rules;
import innui.code_processor.Code_scanners;
import innui.code_processor.Identifiers_tables;
import innui.code_processor.Scanner_rules;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.*;

import static innui.code_processor.java.Identifiers_table_rules.Types.type_package;
import static innui.code_processor.java.Scanner_rules.Token_types.*;

public class Identifiers_table_rules extends innui.code_processor.Identifiers_table_rules {
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
    public enum Types {
        type_package
        , type_class
        , type_interface
        , type_annotation
        , type_attribute
        , type_method
        , type_parameter
        , type_identifier
    }

    public Analizer_rules analizer_rules;

    public Identifiers_table_rules(Code_scanners code_scanner) {
        super(code_scanner);
    }

    @Nullable
    public Oks load(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            Analizer_rules.Find_way_rule_nodes start_find_way_rule_node = new Analizer_rules.Find_way_rule_nodes(analizer_rules);
            Analizer_rules.Find_way_rule_nodes start_1_find_way_rule_node = null;
            Analizer_rules.Tokens_or_rule_nodes package_tokens_or_rule_node = define_rule_package(ok, extras_array);
            if (ok.is == false) return null;
            analizer_rules.start_rule_node = start_find_way_rule_node;
            start_find_way_rule_node.defined_name= "start";
            start_find_way_rule_node.defined_first_part_basic_rule_nodes_list.add(package_tokens_or_rule_node);
            start_find_way_rule_node.defined_second_part_basic_rule_node = start_1_find_way_rule_node;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Analizer_rules.Tokens_or_rule_nodes define_rule_package(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analizer_rules.Tokens_or_rule_nodes retorno = null;
        ResourceBundle in = null;
        try {
            Analizer_rules.Tokens_or_rule_nodes package_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes package_1_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            package_tokens_or_rule_node.defined_name = "package";
            package_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_package.name()));
            package_tokens_or_rule_node.defined_second_part_basic_rule_node = package_1_tokens_or_rule_node;
            package_tokens_or_rule_node.defined_is_optional = true;
            package_tokens_or_rule_node.defined_is_to_process_the_success_rules_list_if_sucess = true;
            package_tokens_or_rule_node.defined_to_put_in_list_rule_success = new Analizer_rules.Rule_success(
                (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                    Identifiers_table_rules.this.identifiers_table.new_identifier = new Identifiers_tables.Identifiers();
                    return ok;
                }
            );
            Analizer_rules.Tokens_or_rule_nodes package_2_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            package_1_tokens_or_rule_node.defined_name = "package_1";
            package_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(identifier.name()));
            package_1_tokens_or_rule_node.defined_second_part_basic_rule_node = package_2_tokens_or_rule_node;
            package_1_tokens_or_rule_node.defined_to_put_in_list_rule_success = new Analizer_rules.Rule_success(
                (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                    Identifiers_table_rules.this.identifiers_table.new_identifier.name = _first_part_tokens_list.get(_repetition_num).token_tex;
                    Identifiers_table_rules.this.identifiers_table.new_identifier.declaration_scope_identifier = null;
                    Identifiers_table_rules.this.identifiers_table.new_identifier.namespace = "";
                    Identifiers_table_rules.this.identifiers_table.new_identifier.type = type_package.name();
                    Identifiers_table_rules.this.identifiers_table.new_identifier.parameters_list = null;
                    Identifiers_table_rules.this.identifiers_table.new_identifier.properties_list = null;
                    return ok;
                }
            );
            Analizer_rules.Tokens_or_rule_nodes package_3_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            package_2_tokens_or_rule_node.defined_name = "package_2";
            package_2_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(dot.name()));
            package_2_tokens_or_rule_node.defined_second_part_basic_rule_node = package_3_tokens_or_rule_node;
            package_2_tokens_or_rule_node.defined_is_repeatable = true;
            Analizer_rules.Tokens_or_rule_nodes package_4_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            package_3_tokens_or_rule_node.defined_name = "package_3";
            package_3_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(identifier.name()));
            package_3_tokens_or_rule_node.defined_second_part_basic_rule_node = package_4_tokens_or_rule_node;
            package_3_tokens_or_rule_node.defined_to_put_in_list_rule_success = new Analizer_rules.Rule_success(
                (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                    if (Identifiers_table_rules.this.identifiers_table.new_identifier.namespace.isEmpty()) {
                        Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                            = _first_part_tokens_list.get(_repetition_num).token_tex;
                    } else {
                        Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                            = Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                                + "."
                                + _first_part_tokens_list.get(_repetition_num).token_tex;
                    }
                    Identifiers_table_rules.this.identifiers_table.new_identifier.name = _first_part_tokens_list.get(_repetition_num).token_tex;
                    return ok;
                }
            );
            package_4_tokens_or_rule_node.defined_name = "package_4_end";
            package_4_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(identifier.name()));
            package_4_tokens_or_rule_node.defined_to_put_in_list_rule_success = new Analizer_rules.Rule_success(
                (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                    ok.valid(Identifiers_table_rules.this.identifiers_table
                            .put_identifier(Identifiers_table_rules.this.identifiers_table.new_identifier, ok, extras_array));
                    return ok;
                }
            );
            retorno = package_tokens_or_rule_node;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

    /**
     *
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public Boolean process(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Boolean retorno = null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            retorno = ok.valid(ok.valid(analizer_rules).start_rule_node).evaluate(basic_token, ok, extras_array);

        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }
}
