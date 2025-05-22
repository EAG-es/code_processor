package innui.code_processor.java;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.code_processor.Analizer_rules;
import innui.code_processor.Code_scanners;
import innui.code_processor.Identifiers_tables;
import innui.code_processor.Scanner_rules;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;

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
        token_package
        , token_class
        , token_interface
        , token_enum
        , token_record
        , attribute
        , method
    }

    public Identifiers_tables. @Nullable Identifiers next_block_identifier = null;

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
            analizer_rule.start_rule_node = define_rule_start(ok, extras_array);
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
            Analizer_rules.Rules_and_rule_nodes r_start_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            r_start_rule_node.defined_name= "define_rule_start(): _define_rule_package()<optional> _define_rule_class_method_and_attribute()<repeat>";
            r_start_rule_node.add(ok.valid(_define_rule_package(ok, extras_array)));
            r_start_rule_node.add(ok.valid(_define_rule_class_method_and_attribute(ok, extras_array)));
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
    public Analizer_rules.@Nullable Rules_and_rule_nodes _define_rule_package(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analizer_rules.Rules_and_rule_nodes retorno = null;
        try {
            Analizer_rules.Rules_and_rule_nodes r_define_rule_package_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_package_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_dot_identifier_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_dot_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_1_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_semi_colon_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            r_define_rule_package_rule_node.defined_name = "_define_rule_package()<optional>: t_package r_identifier r_dot_identifier<optional><repeat> r_semi_colon";
            t_package_rule_node.defined_name = "t_package: package ";
            t_identifier_rule_node.defined_name = "t_identifier: identifier";
            r_dot_identifier_rule_node.defined_name = "r_dot_identifier<optional><repeat>: t_dot t_identifier_1";
            t_semi_colon_rule_node.defined_name = "t_semi_colon: semi_colon";
            t_dot_rule_node.defined_name = "t_dot: dot ";
            t_identifier_1_rule_node.defined_name = "t_identifier_1: identifier";
            /* "_define_rule_package()<optional>: t_package r_identifier r_dot_identifier<optional><repeat> r_semi_colon" */
            r_define_rule_package_rule_node.add(t_package_rule_node);
            r_define_rule_package_rule_node.add(t_identifier_rule_node);
            r_define_rule_package_rule_node.add(r_dot_identifier_rule_node);
            r_define_rule_package_rule_node.add(t_semi_colon_rule_node);
            r_define_rule_package_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            r_define_rule_package_rule_node.defined_is_to_process_the_success_rules_list_if_success = true;
            /* "t_package: package " */
            t_package_rule_node.add(innui.code_processor.java.Scanner_rules.Token_types.token_package.name());
            t_package_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        identifiers_table.new_identifier.init(ok, extras_array);
                    }
            );
            /* "t_identifier: identifier" */
            t_identifier_rule_node.add(identifier.name());
            t_identifier_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                (_tokens_list, _ok, _extras_array) -> {
                    identifiers_table.new_identifier.name = _tokens_list.get(0).token_tex;
                    identifiers_table.new_identifier.type = Types.token_package.name();
                }
            );
            /* "r_dot_identifier<optional><repeat>: t_dot t_identifier_1" */
            r_dot_identifier_rule_node.add(t_dot_rule_node);
            r_dot_identifier_rule_node.add(t_identifier_1_rule_node);
            r_dot_identifier_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            r_dot_identifier_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            /* "t_semi_colon: semi_colon" */
            t_semi_colon_rule_node.add(semi_colon.name());
            t_semi_colon_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_first_part_tokens_list, _ok, _extras_array) -> {
                        identifiers_table.create_top_table(braces_num, ok, extras_array);
                        if (ok.is == false) return;
                        identifiers_table.put_identifier(true, ok, extras_array);
                        if (ok.is == false) return;
                    }
            );
            /* "t_dot: dot " */
            t_dot_rule_node.add(dot.name());
            /* "t_identifier_1: identifier" */
            t_identifier_1_rule_node.add(identifier.name());
            t_identifier_1_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                (_tokens_list, _ok, _extras_array) -> {
                    if (identifiers_table.new_identifier.namespace.isEmpty()) {
                        identifiers_table.new_identifier.namespace
                            = identifiers_table.new_identifier.name;
                    } else {
                        identifiers_table.new_identifier.namespace
                            = identifiers_table.new_identifier.namespace
                                + "."
                                + identifiers_table.new_identifier.name;
                    }
                    identifiers_table.new_identifier.name = _tokens_list.get(0).token_tex;
                }
            );
            retorno = r_define_rule_package_rule_node;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

    /**
     *
     * @param is_new_token
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public boolean validate_token(boolean is_new_token, Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        boolean retorno = false;
        try {
            retorno = count_braces(is_new_token, basic_token, ok, extras_array);
            if (ok.is == false) return false;
            if (retorno) {
                return false;
            }
            retorno = filter_token(is_new_token, basic_token, ok, extras_array);
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
     * @param is_new_token
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return true if there is to get a new token, false otherwise
     * @throws Exception
     */
    public boolean filter_token(boolean is_new_token, Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
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
            Identifiers_tables.Temporary_identifiers_tables temporary_identifiers_table;
            temporary_identifiers_table = identifiers_table.get_top(ok, extras_array);
            if (ok.is == false) return false;
            if (temporary_identifiers_table != null) {
                Identifiers_tables.Identifiers identifier = ok.allow_null(temporary_identifiers_table.block_identifier);
                if (temporary_identifiers_table.braces_num > 1 && identifier == null && next_block_identifier == null
                        || (identifier != null && (identifier.type.equals(token_package.name()) == false
                        && identifier.type.equals(token_class.name()) == false
                        && identifier.type.equals(token_interface.name()) == false
                        && identifier.type.equals(token_enum.name()) == false
                        && identifier.type.equals(token_record.name()) == false))
                ) {
                    return true;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
            return false;
        }
        return false;
    }

    /**
     *
     * @param is_new_token
     * @param basic_token
     * @param ok
     * @param extras_array
     * @return true if count braces was done, false otherwise
     * @throws Exception
     */
    public boolean count_braces(boolean is_new_token, Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        try {
            if (basic_token.token_type.equals(brace_open.name())) {
                if (is_new_token) {
                    braces_num = braces_num + 1;
                    identifiers_table.create_top_table(braces_num, ok, extras_array);
                }
                return true;
            } else if (basic_token.token_type.equals(brace_close.name())) {
                if (is_new_token) {
                    if (identifiers_table.be_to_delete_top_table(braces_num, ok, extras_array)) {
                        if (ok.is == false) return false;
                        Identifiers_tables.Temporary_identifiers_tables temporary_identifiers_table;
                        temporary_identifiers_table = ok.allow_null(identifiers_table.get_top(ok, extras_array));
                        if (ok.is == false) return false;
                        if (temporary_identifiers_table != null) {
                            all_identifiers_list.addAll(temporary_identifiers_table.current_identifiers_map.values());
                            identifiers_table.delete_top_table(ok, extras_array);
                            if (ok.is == false) return false;
                        }
                    }
                    braces_num = braces_num - 1;
                }
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
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void close(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            Identifiers_tables.Temporary_identifiers_tables temporary_identifiers_table;
            while (true) {
                temporary_identifiers_table = ok.allow_null(identifiers_table.get_top(ok, extras_array));
                if (temporary_identifiers_table == null) {
                    break;
                }
                if (ok.is == false) return;
                all_identifiers_list.addAll(temporary_identifiers_table.current_identifiers_map.values());
                identifiers_table.delete_top_table(ok, extras_array);
                if (ok.is == false) return;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }
    /**
     * Rules for clases, interfaces and enums
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public Analizer_rules.@Nullable Rule_nodes _define_rule_class_method_and_attribute(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analizer_rules.Rule_nodes retorno = null;
        try {
            Analizer_rules.Rules_and_rule_nodes r_define_rule_class_method_and_attribute_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_class_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_class_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_class_1_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_class_2_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            r_define_rule_class_method_and_attribute_rule_node.defined_name = "_define_rule_class_method_and_attribute()<repeat>: " +
                    "r_class<ignore> _define_rule_method_or_attribute_or_class()";
            r_class_rule_node.defined_name = "r_class<ignore>: " +
                    "t_class<ignore> t_class_1<optional><repeat> t_class_2 t_identifier";
            t_class_rule_node.defined_name = "t_class<ignore>: [public|protected|private" +
                    "|static|abstract|sealed" +
                    "|strictfp" +
                    "|class|interface|enum|record]";
            t_class_1_rule_node.defined_name = "t_class_1<optional><repeat>: [public|protected|private|static|abstract|sealed|strictfp]";
            t_class_2_rule_node.defined_name = "t_class_2: [class|interface|enum|record]";
            t_identifier_rule_node.defined_name = "t_identifier: identifier";
            /* "_define_rule_class_method_and_attribute()<repeat>: r_class _define_rule_method_or_attribute_or_class()" */
            r_define_rule_class_method_and_attribute_rule_node.add(r_class_rule_node);
            r_define_rule_class_method_and_attribute_rule_node.add(ok.valid(_define_rule_method_or_attribute_or_class(ok, extras_array)));
            r_define_rule_class_method_and_attribute_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            /* r_class<ignore>: t_class<ignore> t_class_1<repeat> t_class_2<ignore> t_class_3 t_identifier */
            r_class_rule_node.add(t_class_rule_node);
            r_class_rule_node.add(t_class_1_rule_node);
            r_class_rule_node.add(t_class_2_rule_node);
            r_class_rule_node.add(t_identifier_rule_node);
            r_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            r_class_rule_node.defined_is_to_process_the_success_rules_list_if_success = true;
            /* t_class<ignore>: [public|protected|private|static|abstract|sealed|class|interface|enum]<ignore> r_class_2 */
            t_class_rule_node.add(token_public.name());
            t_class_rule_node.add(token_protected.name());
            t_class_rule_node.add(token_private.name());
            t_class_rule_node.add(token_static.name());
            t_class_rule_node.add(token_abstract.name());
            t_class_rule_node.add(token_sealed.name());
            t_class_rule_node.add(token_strictfp.name());
            t_class_rule_node.add(token_class.name());
            t_class_rule_node.add(token_interface.name());
            t_class_rule_node.add(token_enum.name());
            t_class_rule_node.add(token_record.name());
            t_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            /* t_class_1<optional><repeat>: [public|protected|private|static|abstract|sealed] */
            t_class_1_rule_node.add(token_public.name());
            t_class_1_rule_node.add(token_protected.name());
            t_class_1_rule_node.add(token_private.name());
            t_class_1_rule_node.add(token_static.name());
            t_class_1_rule_node.add(token_abstract.name());
            t_class_1_rule_node.add(token_sealed.name());
            t_class_1_rule_node.add(token_strictfp.name());
            t_class_1_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            t_class_1_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            t_class_1_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        Scanner_rules.Basic_tokens basic_token = _tokens_list.get(0);
                        identifiers_table.new_identifier.properties_list.add(new Scanner_rules.Basic_tokens(basic_token.token_type
                        , basic_token.token_tex));
                    }
            );
            /* t_class_2: [class|interface|enum|record] */
            t_class_2_rule_node.add(token_class.name());
            t_class_2_rule_node.add(token_interface.name());
            t_class_2_rule_node.add(token_enum.name());
            t_class_2_rule_node.add(token_record.name());
            t_class_2_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        String name = _tokens_list.get(0).token_type;
                        if (Types.token_class.name().equals(name)) {
                            identifiers_table.new_identifier.type = Types.token_class.name();
                        } else if (Types.token_interface.name().equals(name)) {
                            identifiers_table.new_identifier.type = Types.token_interface.name();
                        } else if (Types.token_enum.name().equals(name)) {
                            identifiers_table.new_identifier.type = Types.token_enum.name();
                        } else if (Types.token_record.name().equals(name)) {
                            identifiers_table.new_identifier.type = Types.token_record.name();
                        } else {
                            ok.setTex(Tr.in(k_in_route, "Not recognized type. "));
                        }
                    }
            );
            /* t_identifier: identifier */
            t_identifier_rule_node.add(identifier.name());
            t_identifier_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        identifiers_table.new_identifier.name = _tokens_list.get(0).token_tex;
                        Identifiers_tables.Identifiers identifier = ok.valid(identifiers_table.put_identifier(ok, extras_array));
                        next_block_identifier = new Identifiers_tables.Identifiers(identifier);
                    }
            );
            retorno = r_define_rule_class_method_and_attribute_rule_node;
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
    public Analizer_rules.@Nullable Rule_nodes _define_rule_method_or_attribute_or_class(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analizer_rules.Rule_nodes retorno = null;
        try {
            Analizer_rules.Rules_and_rule_nodes r_method_or_attribute_or_class_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_method_or_attribute_or_class_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_or_rule_nodes o_method_or_attribute_or_class_1_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_method_or_attribute_or_class_1_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_or_rule_nodes o_method_or_attribute_or_class_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_type_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_type_extra_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_identifier_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_or_rule_nodes o_parenthesis_open_or_semicolon_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_parenthesis_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_parenthesis_open_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_parenthesis_close_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_semicolon_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_template_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_template_sign_less_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_template_sign_bigger_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_or_rule_nodes o_identifier_type_rule_node = new Analizer_rules.Rules_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_identifier_class_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_dot_identifier_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_dot_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_annotation_in_identifier_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_annotation_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_annotation_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_annotation_begin_end_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_annotation_parenthesis_open_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_annotation_parenthesis_close_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_annotation_parenthesis_close_1_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_method_or_attribute_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Rules_and_rule_nodes r_class_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_class_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            Analizer_rules.Tokens_or_rule_nodes t_class_identifier_rule_node = new Analizer_rules.Tokens_or_rule_nodes(analizer_rule);
            r_method_or_attribute_or_class_rule_node.defined_name = "r_method_or_attribute_or_class<ignore><repeat>: " +
                    "t_method_or_attribute_or_class<ignore> o_method_or_attribute_or_class_1<optional><repeat> " +
                    "o_method_or_attribute_or_class";
            t_method_or_attribute_or_class_rule_node.defined_name = "t_method_or_attribute_or_class<ignore>: [public|protected|private" +
                    "|static|volatile|final" +
                    "|strictfp" +
                    "|identifier|void|boolean|byte|char|short|int|long|float|double" +
                    "|sign_less" +
                    "|abstract|sealed|class|interface|enum|record" +
                    "|annotation]";
            o_method_or_attribute_or_class_1_rule_node.defined_name = "o_method_or_attribute_or_class_1_rule_node<optional><repeat>: " +
                    "[t_method_or_attribute_or_class_1|r_annotation]";
            r_annotation_rule_node.defined_name = "r_annotation: t_annotation r_annotation_begin_end<optional>";
            t_annotation_rule_node.defined_name = "t_annotation: annotation";
            r_annotation_begin_end_rule_node.defined_name = "r_annotation_begin_end<optional>: t_annotation_parenthesis_open t_annotation_parenthesis_close<ignore> t_annotation_parenthesis_close_1";
            t_annotation_parenthesis_open_rule_node.defined_name = "t_annotation_parenthesis_open: parenthesis_open";
            t_annotation_parenthesis_close_rule_node.defined_name = "t_annotation_parenthesis_close<ignore>: parenthesis_close";
            t_annotation_parenthesis_close_1_rule_node.defined_name = "t_annotation_parenthesis_close_1: parenthesis_close";
            t_method_or_attribute_or_class_1_rule_node.defined_name = "t_method_or_attribute_or_class_1: " +
                    "[public|protected|private" +
                    "|static|volatile|final" +
                    "|strictfp]";
            o_method_or_attribute_or_class_rule_node.defined_name = "o_method_or_attribute_or_class: " +
                    "[r_method_or_attribute" +
                    "|r_class]";
            r_method_or_attribute_rule_node.defined_name = "r_method_or_attribute: " +
                    "r_template<optional> o_identifier_type t_identifier o_parenthesis_open_or_semicolon";
            r_class_rule_node.defined_name = "r_class: t_class t_class_identifier";
            t_class_rule_node.defined_name = "t_class: [class|interface|enum|record]";
            t_class_identifier_rule_node.defined_name = "t_class_identifier: identifier";
            r_template_rule_node.defined_name = "r_template<optional>: t_sign_less t_sign_bigger<ignore> ";
            t_template_sign_less_rule_node.defined_name = "t_sign_less: sign_less";
            t_template_sign_bigger_rule_node.defined_name = "t_sign_bigger<ignore>: sign_bigger";
            o_identifier_type_rule_node.defined_name = "o_identifier_type: [t_identifier_type|r_identifier_class]";
            r_identifier_class_rule_node.defined_name = "r_identifier_class: " +
                    "t_identifier r_dot_identifier<optional><repeat>";
            r_dot_identifier_rule_node.defined_name = "r_dot_identifier<optional><repeat>: " +
                    "t_dot r_annotation_in_identifier<optional><repeat> t_identifier_type_extra";
            t_dot_rule_node.defined_name = "t_dot: dot";
            r_annotation_in_identifier_rule_node.defined_name = "r_annotation_in_identifier<optional><repeat>: " +
                    "r_annotation";
            t_identifier_type_rule_node.defined_name = "t_identifier_type: " +
                    "[void|boolean|byte|char|short|int|long|float|double]";
            t_identifier_type_extra_rule_node.defined_name = "t_identifier_type_extra: identifier";
            t_identifier_rule_node.defined_name = "t_identifier: identifier";
            o_parenthesis_open_or_semicolon_rule_node.defined_name = "o_parenthesis_open_or_semicolon: [t_parenthesis_open|t_semicolon<ignore>]";
            r_parenthesis_rule_node.defined_name = "r_parenthesis: t_parenthesis_open t_parenthesis_close<ignore>";
            t_parenthesis_open_rule_node.defined_name = "t_parenthesis_open: parenthesis_open";
            t_parenthesis_close_rule_node.defined_name = "t_parenthesis_close<ignore>: parenthesis_close";
            t_semicolon_rule_node.defined_name = "t_semicolon<ignore>: semi_colon";
            /* "r_class: t_class t_class_identifier" */
            r_class_rule_node.add(t_class_rule_node);
            r_class_rule_node.add(t_class_identifier_rule_node);
            /* "t_class: [class|interface|enum|record]" */
            t_class_rule_node.add(innui.code_processor.java.Scanner_rules.Token_types.token_class.name());
            t_class_rule_node.add(innui.code_processor.java.Scanner_rules.Token_types.token_interface.name());
            t_class_rule_node.add(innui.code_processor.java.Scanner_rules.Token_types.token_enum.name());
            t_class_rule_node.add(innui.code_processor.java.Scanner_rules.Token_types.token_record.name());
            /* "t_class_identifier: identifier" */
            t_class_identifier_rule_node.add(identifier.name());
            /* "r_method_or_attribute_or_class<repeat>: " +
                    "t_method_or_attribute_or_class<ignore> t_method_or_attribute_or_class_1" +
                    "o_method_or_attribute_or_class" */
            r_method_or_attribute_or_class_rule_node.add(t_method_or_attribute_or_class_rule_node);
            r_method_or_attribute_or_class_rule_node.add(o_method_or_attribute_or_class_1_rule_node);
            r_method_or_attribute_or_class_rule_node.add(o_method_or_attribute_or_class_rule_node);
            r_method_or_attribute_or_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            r_method_or_attribute_or_class_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            r_method_or_attribute_or_class_rule_node.defined_is_to_process_the_success_rules_list_if_success = true;
            /* "t_method_or_attribute_or_class<ignore>: [public|protected|private" +
                    "|static|volatile|final" +
                    "|identifier|void|boolean|byte|char|short|int|long|float|double" +
                    "|sign_less" +
                    "|abstract|sealed|class|interface|enum|record]" */
            t_method_or_attribute_or_class_rule_node.add(token_public.name());
            t_method_or_attribute_or_class_rule_node.add(token_protected.name());
            t_method_or_attribute_or_class_rule_node.add(token_private.name());
            t_method_or_attribute_or_class_rule_node.add(token_static.name());
            t_method_or_attribute_or_class_rule_node.add(token_volatile.name());
            t_method_or_attribute_or_class_rule_node.add(token_final.name());
            t_method_or_attribute_or_class_rule_node.add(token_strictfp.name());
            t_method_or_attribute_or_class_rule_node.add(identifier.name());
            t_method_or_attribute_or_class_rule_node.add(type_void.name());
            t_method_or_attribute_or_class_rule_node.add(type_boolean.name());
            t_method_or_attribute_or_class_rule_node.add(type_byte.name());
            t_method_or_attribute_or_class_rule_node.add(type_char.name());
            t_method_or_attribute_or_class_rule_node.add(type_short.name());
            t_method_or_attribute_or_class_rule_node.add(type_int.name());
            t_method_or_attribute_or_class_rule_node.add(type_long.name());
            t_method_or_attribute_or_class_rule_node.add(type_float.name());
            t_method_or_attribute_or_class_rule_node.add(type_double.name());
            t_method_or_attribute_or_class_rule_node.add(sign_less.name());
            t_method_or_attribute_or_class_rule_node.add(token_abstract.name());
            t_method_or_attribute_or_class_rule_node.add(token_sealed.name());
            t_method_or_attribute_or_class_rule_node.add(token_class.name());
            t_method_or_attribute_or_class_rule_node.add(token_interface.name());
            t_method_or_attribute_or_class_rule_node.add(token_enum.name());
            t_method_or_attribute_or_class_rule_node.add(token_record.name());
            t_method_or_attribute_or_class_rule_node.add(annotation.name());
            t_method_or_attribute_or_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            /* "o_method_or_attribute_or_class_1_rule_node<optional><repeat>: " +
                    "[t_method_or_attribute_or_class_1|r_annotation" */
            o_method_or_attribute_or_class_1_rule_node.add(t_method_or_attribute_or_class_1_rule_node);
            o_method_or_attribute_or_class_1_rule_node.add(r_annotation_rule_node);
            o_method_or_attribute_or_class_1_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            o_method_or_attribute_or_class_1_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            /* "r_annotation: r_annotation_1 r_annotation_begin_end<optional>" */
            r_annotation_rule_node.add(t_annotation_rule_node);
            r_annotation_rule_node.add(r_annotation_begin_end_rule_node);
            /* "t_annotation: annotation" */
            t_annotation_rule_node.add(annotation.name());
            /* "r_annotation_begin_end<optional>: t_annotation_parenthesis_open t_annotation_parenthesis_close<ignore>" */
            r_annotation_begin_end_rule_node.add(t_annotation_parenthesis_open_rule_node);
            r_annotation_begin_end_rule_node.add(t_annotation_parenthesis_close_rule_node);
            r_annotation_begin_end_rule_node.add(t_annotation_parenthesis_close_1_rule_node);
            r_annotation_begin_end_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            /* "t_annotation_parenthesis_open: parenthesis_open" */
            t_annotation_parenthesis_open_rule_node.add(parenthesis_open.name());
            /* "t_annotation_parenthesis_close<ignore>: parenthesis_close" */
            t_annotation_parenthesis_close_rule_node.add(parenthesis_close.name());
            t_annotation_parenthesis_close_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            /* "t_annotation_parenthesis_close_1: parenthesis_close" */
            t_annotation_parenthesis_close_1_rule_node.add(parenthesis_close.name());
            /* t_method_or_attribute_or_class_1<optional><repeat>: " +
                    "[public|protected|private" +
                    "|static]" */
            t_method_or_attribute_or_class_1_rule_node.add(token_public.name());
            t_method_or_attribute_or_class_1_rule_node.add(token_protected.name());
            t_method_or_attribute_or_class_1_rule_node.add(token_private.name());
            t_method_or_attribute_or_class_1_rule_node.add(token_static.name());
            t_method_or_attribute_or_class_1_rule_node.add(token_volatile.name());
            t_method_or_attribute_or_class_1_rule_node.add(token_final.name());
            t_method_or_attribute_or_class_1_rule_node.add(token_strictfp.name());
            t_method_or_attribute_or_class_1_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        Scanner_rules.Basic_tokens basic_token = _tokens_list.get(0);
                        identifiers_table.new_identifier.properties_list.add(new Scanner_rules.Basic_tokens(basic_token.token_type
                                , basic_token.token_tex));
                    }
            );
            /* "o_method_or_attribute_or_class: " +
                    "[r_method_or_attribute" +
                    "|r_class]" */
            o_method_or_attribute_or_class_rule_node.add(r_method_or_attribute_rule_node);
            o_method_or_attribute_or_class_rule_node.add(r_class_rule_node);
            /* r_method_or_attribute_rule_node: " +
                    "t_volatile_or_final<optional><repeat> r_template<optional> t_identifier_type t_identifier t_parenthesis_open_or_semicolon<ignore>" */
            r_method_or_attribute_rule_node.add(r_template_rule_node);
            r_method_or_attribute_rule_node.add(o_identifier_type_rule_node);
            r_method_or_attribute_rule_node.add(t_identifier_rule_node);
            r_method_or_attribute_rule_node.add(o_parenthesis_open_or_semicolon_rule_node);
            /* "r_template<optional>: t_sign_less t_sign_bigger<ignore> t_sign_bigger_1" */
            r_template_rule_node.add(t_template_sign_less_rule_node);
            r_template_rule_node.add(t_template_sign_bigger_rule_node);
            r_template_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            /* "t_sign_less: sign_less" */
            t_template_sign_less_rule_node.add(sign_less.name());
            /* "t_sign_bigger<ignore>: sign_bigger" */
            t_template_sign_bigger_rule_node.add(sign_bigger.name());
            t_template_sign_bigger_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            /* "o_identifier_type: [t_identifier_type|r_identifier_class]" */
            o_identifier_type_rule_node.add(t_identifier_type_rule_node);
            o_identifier_type_rule_node.add(r_identifier_class_rule_node);
            /* "r_identifier_class: " +
                    "t_identifier r_dot_identifier<optional><repeat>" */
            r_identifier_class_rule_node.add(t_identifier_rule_node);
            r_identifier_class_rule_node.add(r_dot_identifier_rule_node);
            /* "r_dot_identifier<optional><repeat>: " +
                    "t_dot r_annotation_in_identifier<optional> t_identifier_extra" */
            r_dot_identifier_rule_node.add(t_dot_rule_node);
            r_dot_identifier_rule_node.add(r_annotation_in_identifier_rule_node);
            r_dot_identifier_rule_node.add(t_identifier_type_extra_rule_node);
            r_dot_identifier_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            r_dot_identifier_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            /* "t_dot: dot" */
            t_dot_rule_node.add(dot.name());
            /* "r_annotation_in_identifier<optional><repeat>: " +
                    "r_annotation" */
            r_annotation_in_identifier_rule_node.add(r_annotation_rule_node);
            r_annotation_in_identifier_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
            r_annotation_in_identifier_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
            /* "t_identifier_type: " +
                    "[void|boolean|byte|char|short|int|long|float|double]" */
            t_identifier_type_rule_node.add(type_void.name());
            t_identifier_type_rule_node.add(type_boolean.name());
            t_identifier_type_rule_node.add(type_byte.name());
            t_identifier_type_rule_node.add(type_char.name());
            t_identifier_type_rule_node.add(type_short.name());
            t_identifier_type_rule_node.add(type_int.name());
            t_identifier_type_rule_node.add(type_long.name());
            t_identifier_type_rule_node.add(type_float.name());
            t_identifier_type_rule_node.add(type_double.name());
            t_identifier_type_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        if (identifiers_table.new_identifier.return_class.isEmpty()) {
                            identifiers_table.new_identifier.return_class = _tokens_list.get(0).token_tex;
                        } else {
                            identifiers_table.new_identifier.return_class = _tokens_list.get(0).token_tex
                                    + "." + _tokens_list.get(0).token_tex;
                        }
                    }
            );
            /* "t_identifier_type_extra: identifier" */
            t_identifier_type_extra_rule_node.add(identifier.name());
            t_identifier_type_extra_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        if (identifiers_table.new_identifier.return_class.isEmpty()) {
                            identifiers_table.new_identifier.return_class = _tokens_list.get(0).token_tex;
                        } else {
                            identifiers_table.new_identifier.return_class = _tokens_list.get(0).token_tex
                              + "." + _tokens_list.get(0).token_tex;
                        }
                    }
            );
            /* "t_identifier: identifier" */
            t_identifier_rule_node.add(identifier.name());
            t_identifier_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        identifiers_table.new_identifier.name = _tokens_list.get(0).token_tex;
                    }
            );
            /* "o_parenthesis_open_or_semicolon: [t_parenthesis_open|t_semicolon<ignore>]" */
            o_parenthesis_open_or_semicolon_rule_node.add(r_parenthesis_rule_node);
            o_parenthesis_open_or_semicolon_rule_node.add(t_semicolon_rule_node);
            o_parenthesis_open_or_semicolon_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                    (_tokens_list, _ok, _extras_array) -> {
                        if (next_block_identifier != null) {
                            Identifiers_tables.Temporary_identifiers_tables temporary_identifiers_table = ok.valid(identifiers_table.get_top(ok, extras_array));
                            if (ok.is == false) return;
                            temporary_identifiers_table.block_identifier = next_block_identifier;
                            next_block_identifier = null;
                        }
                        if (_tokens_list.get(0).token_type.equals(parenthesis_open.name())) {
                            identifiers_table.new_identifier.type = Types.method.name();
                        } else {
                            identifiers_table.new_identifier.type = Types.attribute.name();
                        }
                        identifiers_table.put_identifier(ok, extras_array);
                    }
            );
            /* "r_parenthesis: t_parenthesis_open t_parenthesis_close<ignore>" */
            r_parenthesis_rule_node.add(t_parenthesis_open_rule_node);
            r_parenthesis_rule_node.add(t_parenthesis_close_rule_node);
            /* "t_parenthesis_open: parenthesis_open" */
            t_parenthesis_open_rule_node.add(parenthesis_open.name());
            /* "t_parenthesis_close<ignore>: parenthesis_close" */
            t_parenthesis_close_rule_node.add(parenthesis_close.name());
            t_parenthesis_close_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            /* "t_semicolon<ignore>: semi_colon" */
            t_semicolon_rule_node.add(semi_colon.name());
            t_semicolon_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
            retorno = r_method_or_attribute_or_class_rule_node;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

}
