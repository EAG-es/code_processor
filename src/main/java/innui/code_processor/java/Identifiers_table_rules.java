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
import java.util.LinkedHashMap;

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
    public Analizer_rules.@Nullable Rules_and_rule_nodes define_rule_start(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        try {
            Analizer_rules.Rules_and_rule_nodes r_start_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rules);
            r_start_rule_node.defined_name= "r_start: r_package? r_class<repeat><ignore>";
            r_start_rule_node.defined_rule_nodes_and_list.add(ok.valid(define_rule_package(ok, extras_array)));
            r_start_rule_node.defined_rule_nodes_and_list.add(ok.valid(define_rule_class(ok, extras_array)));
            return r_start_rule_node;
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
    public Analizer_rules.@Nullable Rules_and_rule_nodes define_rule_package(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analizer_rules.Rules_and_rule_nodes retorno = null;
        try {
            Analizer_rules.Rules_and_rule_nodes r_package_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_package_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Rules_and_rule_nodes r_dot_identifier_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_dot_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_1_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_semi_colon_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            r_package_rule_node.defined_name = "r_package<optional>: t_package r_identifier r_dot_identifier<optional><repeat> r_semi_colon";
            t_package_rule_node.defined_name = "t_package: package ";
            t_identifier_rule_node.defined_name = "t_identifier: identifier";
            r_dot_identifier_rule_node.defined_name = "r_dot_identifier<optional><repeat>: t_dot t_identifier_1";
            t_semi_colon_rule_node.defined_name = "t_semi_colon: ;";
            t_dot_rule_node.defined_name = "t_dot: . ";
            t_identifier_1_rule_node.defined_name = "t_identifier_1: identifier";
            /* r_package */
            r_package_rule_node.defined_rule_nodes_and_list.add(t_package_rule_node);
            r_package_rule_node.defined_rule_nodes_and_list.add(t_identifier_rule_node);
            r_package_rule_node.defined_rule_nodes_and_list.add(r_dot_identifier_rule_node);
            r_package_rule_node.defined_rule_nodes_and_list.add(t_semi_colon_rule_node);
            r_package_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            r_package_rule_node.defined_is_to_process_the_success_rules_list_if_success = true;
            r_package_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                (_tokens_list_pos, _tokens_list, _ok, _extras_array) -> {
                    Identifiers_table_rules.this.identifiers_table.new_identifier = new Identifiers_tables.Identifiers();
                }
            );
            /* t_package */
            t_package_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_package.name()));
            /* t_identifier */
            t_identifier_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(identifier.name()));
            t_identifier_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                (_token_list_pos, _tokens_list, _ok, _extras_array) -> {
                    if (Identifiers_table_rules.this.identifiers_table.new_identifier != null) {
                        Identifiers_table_rules.this.identifiers_table.new_identifier.name = _tokens_list.get(_token_list_pos).token_tex;
                        Identifiers_table_rules.this.identifiers_table.new_identifier.declaration_scope_identifier = null;
                        Identifiers_table_rules.this.identifiers_table.new_identifier.namespace = "";
                        Identifiers_table_rules.this.identifiers_table.new_identifier.type = type_package.name();
                        Identifiers_table_rules.this.identifiers_table.new_identifier.parameters_list.clear();
                        Identifiers_table_rules.this.identifiers_table.new_identifier.properties_list.clear();
                    }
                }
            );
            /* r_dot_identifier */
            r_dot_identifier_rule_node.defined_rule_nodes_and_list.add(t_dot_rule_node);
            r_dot_identifier_rule_node.defined_rule_nodes_and_list.add(t_identifier_1_rule_node);
            r_dot_identifier_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            r_dot_identifier_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            /* t_semi_colon */
            t_semi_colon_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(semi_colon.name()));
            t_semi_colon_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_repetition_num, _first_part_tokens_list, _ok, _extras_array) -> {
                        Identifiers_table_rules.this.identifiers_table.create_top_table(braces_num, ok, extras_array);
                        Identifiers_table_rules.this.identifiers_table
                                .put_identifier(Identifiers_table_rules.this.identifiers_table.new_identifier, ok, extras_array);
                    }
            );
            /* t_dot */
            t_dot_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(dot.name()));
            /* t_identifier_1 */
            t_identifier_1_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(identifier.name()));
            t_identifier_1_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                (_token_list_pos, _tokens_list, _ok, _extras_array) -> {
                    if (Identifiers_table_rules.this.identifiers_table.new_identifier.namespace.isEmpty()) {
                        Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                            = Identifiers_table_rules.this.identifiers_table.new_identifier.name;
                    } else {
                        Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                            = Identifiers_table_rules.this.identifiers_table.new_identifier.namespace
                                + "."
                                + Identifiers_table_rules.this.identifiers_table.new_identifier.name;
                    }
                    Identifiers_table_rules.this.identifiers_table.new_identifier.name = _tokens_list.get(_token_list_pos).token_tex;
                }
            );
            retorno = r_package_rule_node;
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
            if (basic_token.token_type.equals(comment_block_begin.name())) {
                return true;
            }
            if (basic_token.token_type.equals(comment_block.name())) {
                return true;
            }
            if (basic_token.token_type.equals(comment_line_begin.name())) {
                return true;
            }
            if (basic_token.token_type.equals(comment_line.name())) {
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
                Identifiers_table_rules.this.identifiers_table.create_top_table(braces_num, ok, extras_array);
                return true;
            } else if (basic_token.token_type.equals(brace_close.name())) {
                if (Identifiers_table_rules.this.identifiers_table.be_to_delete_top_table(braces_num, ok, extras_array)) {
                    LinkedHashMap<String, Identifiers_tables.Identifiers> map;
                    if (ok.is == false) return false;
                    map = Identifiers_table_rules.this.identifiers_table.get_top(ok, extras_array);
                    all_identifiers_list.addAll(map.values());
                    Identifiers_table_rules.this.identifiers_table.delete_top_table(ok, extras_array);
                    if (ok.is == false) return false;
                }
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
    public Analizer_rules.@Nullable Rule_nodes define_rule_class(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analizer_rules.Rule_nodes retorno = null;
        try {
            Analizer_rules.Rules_and_rule_nodes r_class_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_class_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_class_1_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_class_2_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rules);
            r_class_rule_node.defined_name = "r_class<ignore><repeat>: t_class<ignore> t_class_1<optional><repeat> t_class_2 t_identifier";
            t_class_rule_node.defined_name = "t_class<ignore>: [public|protected|private|static|abstract|sealed|class|interface|enum|record]";
            t_class_1_rule_node.defined_name = "t_class_1<optional><repeat>: [public|protected|private|static|abstract|sealed]";
            t_class_2_rule_node.defined_name = "t_class_2: [class|interface|enum|record]";
            t_identifier_rule_node.defined_name = "t_identifier: identifier";
            /* r_class<repeat>: t_class<ignore> t_class_1<repeat> t_class_2<ignore> t_class_3 t_identifier */
            r_class_rule_node.defined_rule_nodes_and_list.add(t_class_rule_node);
            r_class_rule_node.defined_rule_nodes_and_list.add(t_class_1_rule_node);
            r_class_rule_node.defined_rule_nodes_and_list.add(t_class_2_rule_node);
            r_class_rule_node.defined_rule_nodes_and_list.add(t_identifier_rule_node);
            r_class_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            r_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            /* t_class<ignore>: [public|protected|private|static|abstract|sealed|class|interface|enum]<ignore> r_class_2 */
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_public.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_protected.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_private.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_static.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_abstract.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_sealed.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_class.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_interface.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_enum.name()));
            t_class_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_record.name()));
            t_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            /* t_class_1<optional><repeat>: [public|protected|private|static|abstract|sealed] */
            t_class_1_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_public.name()));
            t_class_1_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_protected.name()));
            t_class_1_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_private.name()));
            t_class_1_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_static.name()));
            t_class_1_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_abstract.name()));
            t_class_1_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_sealed.name()));
            t_class_1_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            t_class_1_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            t_class_1_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list_pos, _tokens_list, _ok, _extras_array) -> {
                        Identifiers_table_rules.this.identifiers_table.new_identifier = new Identifiers_tables.Identifiers();
                    }
            );
            /* t_class_2<ignore>: [class|interface|enum|record] */
            t_class_2_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_class.name()));
            t_class_2_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_interface.name()));
            t_class_2_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_enum.name()));
            t_class_2_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_record.name()));
            /* t_identifier: identifier */
            t_identifier_rule_node.defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_class.name()));
            t_identifier_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list_pos, _tokens_list, _ok, _extras_array) -> {
                        identifiers_table.new_identifier.properties_list.add(_tokens_list.get(_tokens_list_pos));
                    }
            );
            retorno = r_class_rule_node;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }
}
