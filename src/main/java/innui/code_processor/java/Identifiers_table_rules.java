package innui.code_processor.java;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.code_processor.Analizer_rules;
import innui.code_processor.Code_scanners;
import innui.code_processor.Identifiers_tables;
import innui.code_processor.Scanner_rules;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;

import static innui.code_processor.java.Identifiers_table_rules.Types.type_package;
import static innui.code_processor.java.Scanner_rules.Token_types.*;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE", "NM_SAME_SIMPLE_NAME_AS_SUPERCLASS"})
public class Identifiers_table_rules extends innui.code_processor.Identifiers_table_rules {
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

    public Identifiers_table_rules(Code_scanners code_scanner) throws Exception {
        super(code_scanner);
    }

    /**
     * Load the rules by default to creaate the identifiers_table
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void load(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            analizer_rules.start_rule_node = define_rule_start(ok, extras_array);
            if (ok.is == false) return;
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Analizer_rules.@Nullable Rules_or_rule_nodes define_rule_start(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        try {
            Analizer_rules.Basic_rule_nodes basic_rule_node;
            Analizer_rules.Rules_or_rule_nodes start_find_way_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rules);
            Analizer_rules.Rules_or_rule_nodes start_1_class_find_way_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rules);
            if (ok.is == false) return null;
            start_find_way_rule_node.defined_name= "r_start: r_package? r_start_1*";
            start_1_class_find_way_rule_node.defined_name= "r_start_1*: r_class <end>";
            /* 1 */
            basic_rule_node = ok.valid(define_rule_package(ok, extras_array));
            start_find_way_rule_node.defined_first_part_basic_rule_nodes_list.add(basic_rule_node);
            start_find_way_rule_node.defined_second_part_basic_rule_node = start_1_class_find_way_rule_node;
            /* 2 */
            basic_rule_node = ok.valid(define_rule_class(ok, extras_array));
            start_1_class_find_way_rule_node.defined_is_optional = true;
            start_1_class_find_way_rule_node.defined_is_repeatable = true;
            start_1_class_find_way_rule_node.defined_first_part_basic_rule_nodes_list.add(basic_rule_node);
            start_1_class_find_way_rule_node.defined_second_part_basic_rule_node = null;
            return start_find_way_rule_node;
        } catch (Exception e) {
            ok.setTex(e);
        }
        return null;
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Analizer_rules.@Nullable Tokens_or_rule_nodes define_rule_package(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analizer_rules.Tokens_or_rule_nodes retorno = null;
        try {
            Analizer_rules.Tokens_or_rule_nodes package_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes package_1_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Rules_or_rule_nodes package_2_rules_or_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes package_3_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes package_4_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes package_5_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            package_tokens_or_rule_node.defined_name = "r_package?: package r_package_1";
            package_1_tokens_or_rule_node.defined_name = "r_package_1: identifier r_package_2";
            package_2_rules_or_rule_node.defined_name = "r_package_2: r_package_3*? r_package_4";
            package_3_tokens_or_rule_node.defined_name = "r_package_3*?: . r_package_4";
            package_4_tokens_or_rule_node.defined_name = "r_package_4: identifier <null>";
            package_5_tokens_or_rule_node.defined_name = "r_package_5: ; <end>";
            /* 0 */
            ok.valid(package_tokens_or_rule_node.defined_first_part_evaluation_tokens_list).add(new Scanner_rules.Basic_tokens(token_package.name()));
            package_tokens_or_rule_node.defined_second_part_basic_rule_node = package_1_tokens_or_rule_node;
            package_tokens_or_rule_node.defined_is_optional = true;
            package_tokens_or_rule_node.defined_is_to_process_the_success_rules_list_if_sucess = true;
            package_tokens_or_rule_node.defined_to_be_called_rule_success = new Analizer_rules.Rule_success(
                (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                    Identifiers_table_rules.this.identifiers_table.new_identifier = new Identifiers_tables.Identifiers();
                }
            );
            /* 1 */
            package_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(identifier.name()));
            package_1_tokens_or_rule_node.defined_second_part_basic_rule_node = package_2_rules_or_rule_node;
            package_1_tokens_or_rule_node.defined_to_be_called_rule_success = new Analizer_rules.Rule_success(
                (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                    if (Identifiers_table_rules.this.identifiers_table.new_identifier != null) {
                        Identifiers_table_rules.this.identifiers_table.new_identifier.name = _first_part_tokens_list.get(_repetition_num).token_tex;
                        Identifiers_table_rules.this.identifiers_table.new_identifier.declaration_scope_identifier = null;
                        Identifiers_table_rules.this.identifiers_table.new_identifier.namespace = "";
                        Identifiers_table_rules.this.identifiers_table.new_identifier.type = type_package.name();
                        Identifiers_table_rules.this.identifiers_table.new_identifier.parameters_list.clear();
                        Identifiers_table_rules.this.identifiers_table.new_identifier.properties_list.clear();
                    }
                }
            );
            /* 2 */
            package_2_rules_or_rule_node.defined_first_part_basic_rule_nodes_list.add(package_3_tokens_or_rule_node);
            package_2_rules_or_rule_node.defined_first_part_basic_rule_nodes_list.add(package_4_tokens_or_rule_node);
            /* 3 */
            package_3_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(dot.name()));
            package_3_tokens_or_rule_node.defined_second_part_basic_rule_node = package_4_tokens_or_rule_node;
            package_3_tokens_or_rule_node.defined_is_optional = true;
            package_3_tokens_or_rule_node.defined_is_repeatable = true;
            /* 4 */
            package_4_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(identifier.name()));
            package_4_tokens_or_rule_node.defined_to_be_called_rule_success = new Analizer_rules.Rule_success(
                (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                    if (Identifiers_table_rules.this.identifiers_table.new_identifier.namespace.isEmpty()) {
                        Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                            = Identifiers_table_rules.this.identifiers_table.new_identifier.name;
                    } else {
                        Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                            = Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                                + "."
                                + Identifiers_table_rules.this.identifiers_table.new_identifier.name;
                    }
                    Identifiers_table_rules.this.identifiers_table.new_identifier.name = _first_part_tokens_list.get(_repetition_num).token_tex;
                }
            );
            /* 5 */
            package_5_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(semi_colon.name()));
            package_5_tokens_or_rule_node.defined_to_be_called_rule_success = new Analizer_rules.Rule_success(
                (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                    Identifiers_table_rules.this.identifiers_table.create_top_table(braces_num, ok, extras_array);
                    Identifiers_table_rules.this.identifiers_table
                     .put_identifier(Identifiers_table_rules.this.identifiers_table.new_identifier, ok, extras_array);
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
    public boolean validate_token(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        boolean retorno = false;
        try {
            retorno = filter_token(basic_token, ok, extras_array);
            if (ok.is == false) return false;
            if (retorno) {
                return false;
            }
            retorno = count_braces(basic_token, ok, extras_array);
            if (ok.is == false) return false;
            if (retorno) {
                return false;
            }
        } catch (Exception e) {
            ok.setTex(e);
            return false;
        }
        return true;
    }

    /**
     *
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public @Nullable Integer process_rules(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Integer retorno = null;
        try {
            ok.valid(analizer_rules.start_rule_node).evaluate(basic_token, ok, extras_array);
            retorno = analizer_rules.backtrack_pos;
            analizer_rules.backtrack_pos = null;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

    /**
     * Filters tokens
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return true if there is to get a new token, false otherwise
     * @throws Exception
     */
    public boolean filter_token(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        try {
            if (basic_token.token_type.equals(space.name())) {
                return true;
            }
        } catch (Exception e) {
            ok.setTex(e);
            return false;
        }
        return false;
    }

    /**
     *
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return true if count braces was done, false otherwise
     * @throws Exception
     */
    public boolean count_braces(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        try {
            if (basic_token.token_type.equals(brace_open.name())) {
                braces_num = braces_num + 1;
                return true;
            } else if (basic_token.token_type.equals(brace_close.name())) {
                braces_num = braces_num - 1;
                return true;
            }
        } catch (Exception e) {
            ok.setTex(e);
            return false;
        }
        return false;
    }

    /**
     * Rules for clases, interfaces and enums
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Analizer_rules.@Nullable Basic_rule_nodes define_rule_class(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analizer_rules.Basic_rule_nodes retorno = null;
        try {
            Analizer_rules.Rules_or_rule_nodes class_rules_or_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes class_1_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Rules_or_rule_nodes class_2_rules_or_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes class_3_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes class_4_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes class_5_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes class_6_tokens_or_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            class_rules_or_rule_node.defined_name = "r_class: r_class_1<ignored> <end>";
            class_1_tokens_or_rule_node.defined_name = "r_class_1<ignored>: [public|protected|private|static|abstract|sealed|class|interface|enum]<ignored> r_class_2";
            class_2_rules_or_rule_node.defined_name = "r_class_2: r_class_3*? r_class_4<ignored>";
            class_3_tokens_or_rule_node.defined_name = "r_class_3*?: [public|protected|private|static|abstract|sealed] <end>";
            class_4_tokens_or_rule_node.defined_name = "r_class_4<ignored>: [class|interface|enum] r_class_5";
            class_5_tokens_or_rule_node.defined_name = "r_class_5: [class|interface|enum] r_class_6";
            class_6_tokens_or_rule_node.defined_name = "r_class_6: identifier <end>";
            /* 0 */
            class_rules_or_rule_node.defined_first_part_basic_rule_nodes_list.add(class_1_tokens_or_rule_node);
            class_rules_or_rule_node.defined_second_part_basic_rule_node = null;
            /* 1 */
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_public.name()));
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_protected.name()));
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_private.name()));
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_static.name()));
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_abstract.name()));
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_sealed.name()));
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_class.name()));
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_interface.name()));
            class_1_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_enum.name()));
            class_1_tokens_or_rule_node.defined_second_part_basic_rule_node = class_2_rules_or_rule_node;
            class_1_tokens_or_rule_node.defined_is_ignore_til_matches = true;
            /* 2 */
            class_2_rules_or_rule_node.defined_first_part_basic_rule_nodes_list.add(class_3_tokens_or_rule_node);
            class_2_rules_or_rule_node.defined_second_part_basic_rule_node = class_4_tokens_or_rule_node;
            class_2_rules_or_rule_node.defined_to_be_called_rule_success = new Analizer_rules.Rule_success(
                    (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                        Identifiers_table_rules.this.identifiers_table.new_identifier = new Identifiers_tables.Identifiers();
                    }
            );
            /* 3 */
            class_3_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_public.name()));
            class_3_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_protected.name()));
            class_3_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_private.name()));
            class_3_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_static.name()));
            class_3_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_abstract.name()));
            class_3_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_sealed.name()));
            class_3_tokens_or_rule_node.defined_second_part_basic_rule_node = null;
            class_3_tokens_or_rule_node.defined_is_optional = true;
            class_3_tokens_or_rule_node.defined_is_repeatable = true;
            class_3_tokens_or_rule_node.defined_to_be_called_rule_success = new Analizer_rules.Rule_success(
                    (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                        identifiers_table.new_identifier.properties_list.add(_first_part_tokens_list.get(_repetition_num));
                    }
            );
            /* 4 */
            class_4_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_class.name()));
            class_4_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_interface.name()));
            class_4_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_enum.name()));
            class_4_tokens_or_rule_node.defined_second_part_basic_rule_node = class_5_tokens_or_rule_node;
            class_4_tokens_or_rule_node.defined_is_ignore_til_matches = true;
            /* 5*/
            class_5_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_class.name()));
            class_5_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_interface.name()));
            class_5_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(token_enum.name()));
            class_5_tokens_or_rule_node.defined_second_part_basic_rule_node = class_6_tokens_or_rule_node;
            class_5_tokens_or_rule_node.defined_to_be_called_rule_success = new Analizer_rules.Rule_success(
                    (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                        identifiers_table.new_identifier.type = _first_part_tokens_list.get(_repetition_num).token_type;
                    }
            );
            /* 6 */
            class_6_tokens_or_rule_node.defined_first_part_evaluation_tokens_list.add(new Scanner_rules.Basic_tokens(identifier.name()));
            class_6_tokens_or_rule_node.defined_second_part_basic_rule_node = null;
            class_6_tokens_or_rule_node.defined_to_be_called_rule_success = new Analizer_rules.Rule_success(
                    (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                        identifiers_table.new_identifier.name = _first_part_tokens_list.get(_repetition_num).token_tex;
                    }
            );
            retorno = class_rules_or_rule_node;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }
}
