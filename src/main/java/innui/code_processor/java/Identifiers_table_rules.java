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
    public Analizer_rules.@Nullable Rule_nodes define_rule_start(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        try {
            Analizer_rules.Rules_and_rule_nodes r_start_rule_node = new Analizer_rules.Rules_and_rule_nodes(analizer_rule);
            r_start_rule_node.configure_from_key_name("define_rule_start(): _define_rule_package()<optional><call> _define_rule_class_method_and_attribute()<repeat>");
            /* "define_rule_start(): _define_rule_package()<optional><call> _define_rule_class_method_and_attribute()<repeat>" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_start_rule_node_supplier = () -> {
                try {
                    r_start_rule_node.add_new(this::_define_rule_package, ok, extras_array);
                    if (ok.is == false) return null;
                    r_start_rule_node.add_new(this::_define_rule_class_method_and_attribute, ok, extras_array);
                    if (ok.is == false) return null;
                } catch (Exception e) {
                    return null;
                }
                return r_start_rule_node;
            };
            return r_start_rule_node_supplier.get();
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
    public Analizer_rules.@Nullable Rule_nodes _define_rule_package(Oks ok, Object ... extras_array) throws Exception {
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
            r_define_rule_package_rule_node.configure_from_key_name("_define_rule_package()<optional><call>: t_package r_identifier r_dot_identifier<optional><repeat> r_semi_colon");
            t_package_rule_node.configure_from_key_name("t_package: package ");
            t_identifier_rule_node.configure_from_key_name("t_identifier: identifier");
            r_dot_identifier_rule_node.configure_from_key_name("r_dot_identifier<optional><repeat>: t_dot t_identifier_1");
            t_semi_colon_rule_node.configure_from_key_name("t_semi_colon: semi_colon");
            t_dot_rule_node.configure_from_key_name("t_dot: dot ");
            t_identifier_1_rule_node.configure_from_key_name("t_identifier_1: identifier");
            /* "t_semi_colon: semi_colon" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_semi_colon_rule_node_supplier = () -> {
                try {
                    t_semi_colon_rule_node.add_new(semi_colon.name());
                    t_semi_colon_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                            (_first_part_tokens_list, _ok, _extras_array) -> {
                                identifiers_table.create_top_table(braces_num, _ok, _extras_array);
                                if (_ok.is == false) return;
                                identifiers_table.put_identifier(true, _ok, _extras_array);
                                if (_ok.is == false) return;
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return t_semi_colon_rule_node;
            };
            /* "t_identifier_1: identifier" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_identifier_1_rule_node_supplier = () -> {
                try {
                    t_identifier_1_rule_node.add_new(identifier.name());
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
                } catch (Exception e) {
                    return null;
                }
                return t_identifier_1_rule_node;
            };
            /* "t_dot: dot " */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_dot_rule_node_supplier = () -> {
                try {
                    t_dot_rule_node.add_new(dot.name());
                } catch (Exception e) {
                    return null;
                }
                return t_dot_rule_node;
            };
            /* "t_identifier: identifier" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_identifier_rule_node_supplier = () -> {
                try {
                    t_identifier_rule_node.add_new(identifier.name());
                    t_identifier_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                            (_tokens_list, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.name = _tokens_list.get(0).token_tex;
                                identifiers_table.new_identifier.type = Types.token_package.name();
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return t_identifier_rule_node;
            };
            /* "r_dot_identifier<optional><repeat>: t_dot t_identifier_1" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_dot_identifier_rule_node_supplier = () -> {
                try {
                    r_dot_identifier_rule_node.add_new(t_dot_rule_node_supplier);
                    r_dot_identifier_rule_node.add_new(t_identifier_1_rule_node_supplier);
                    r_dot_identifier_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
                    r_dot_identifier_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return r_dot_identifier_rule_node;
            };
            /* "t_package: package " */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_package_rule_node_supplier = () -> {
                try {
                    t_package_rule_node.add_new(token_package.name());
                    t_package_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                            (_tokens_list, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.init(_ok, _extras_array);
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return t_package_rule_node;
            };
            /* "_define_rule_package()<optional><call>: t_package r_identifier r_dot_identifier<optional><repeat> r_semi_colon" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_define_rule_package_rule_node_supplier = () -> {
                try {
                    r_define_rule_package_rule_node.add_new(t_package_rule_node_supplier);
                    r_define_rule_package_rule_node.add_new(t_identifier_rule_node_supplier);
                    r_define_rule_package_rule_node.add_new(r_dot_identifier_rule_node_supplier);
                    r_define_rule_package_rule_node.add_new(t_semi_colon_rule_node_supplier);
                    r_define_rule_package_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
                    r_define_rule_package_rule_node.defined_call_the_success_rules_list_if_success = true;
                } catch (Exception e) {
                    return null;
                }
                return r_define_rule_package_rule_node;
            };
            retorno = (Analizer_rules.Rules_and_rule_nodes) r_define_rule_package_rule_node_supplier.get();
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
            r_define_rule_class_method_and_attribute_rule_node.configure_from_key_name("_define_rule_class_method_and_attribute()<repeat>: " +
                    "r_class<ignore><call> _define_rule_method_or_attribute_or_class()");
            r_class_rule_node.configure_from_key_name("r_class<ignore><call>: " +
                    "t_class<ignore> t_class_1<optional><repeat> t_class_2 t_identifier");
            t_class_rule_node.configure_from_key_name("t_class<ignore>: [public|protected|private" +
                    "|static|abstract|sealed" +
                    "|strictfp" +
                    "|class|interface|enum|record]");
            t_class_1_rule_node.configure_from_key_name("t_class_1<optional><repeat>: [public|protected|private|static|abstract|sealed|strictfp]");
            t_class_2_rule_node.configure_from_key_name("t_class_2: [class|interface|enum|record]");
            t_identifier_rule_node.configure_from_key_name("t_identifier: identifier");
            /* t_class<ignore>: [public|protected|private|static|abstract|sealed|class|interface|enum]<ignore> r_class_2 */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_class_rule_node_supplier = () -> {
                try {
                    t_class_rule_node.add_new(token_public.name());
                    t_class_rule_node.add_new(token_protected.name());
                    t_class_rule_node.add_new(token_private.name());
                    t_class_rule_node.add_new(token_static.name());
                    t_class_rule_node.add_new(token_abstract.name());
                    t_class_rule_node.add_new(token_sealed.name());
                    t_class_rule_node.add_new(token_strictfp.name());
                    t_class_rule_node.add_new(token_class.name());
                    t_class_rule_node.add_new(token_interface.name());
                    t_class_rule_node.add_new(token_enum.name());
                    t_class_rule_node.add_new(token_record.name());
                    t_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return t_class_rule_node;
            };
            /* t_class_1<optional><repeat>: [public|protected|private|static|abstract|sealed] */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_class_1_rule_node_supplier = () -> {
                try {
                    t_class_1_rule_node.add_new(token_public.name());
                    t_class_1_rule_node.add_new(token_protected.name());
                    t_class_1_rule_node.add_new(token_private.name());
                    t_class_1_rule_node.add_new(token_static.name());
                    t_class_1_rule_node.add_new(token_abstract.name());
                    t_class_1_rule_node.add_new(token_sealed.name());
                    t_class_1_rule_node.add_new(token_strictfp.name());
                    t_class_1_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
                    t_class_1_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
                    t_class_1_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                            (_tokens_list, _ok, _extras_array) -> {
                                Scanner_rules.Basic_tokens basic_token = _tokens_list.get(0);
                                identifiers_table.new_identifier.properties_list.add(new Scanner_rules.Basic_tokens(basic_token.token_type
                                        , basic_token.token_tex));
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return t_class_1_rule_node;
            };
            /* t_class_2: [class|interface|enum|record] */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_class_2_rule_node_supplier = () -> {
                try {
                    t_class_2_rule_node.add_new(token_class.name());
                    t_class_2_rule_node.add_new(token_interface.name());
                    t_class_2_rule_node.add_new(token_enum.name());
                    t_class_2_rule_node.add_new(token_record.name());
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
                                    _ok.setTex(Tr.in(k_in_route, "Not recognized type. "));
                                }
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return t_class_2_rule_node;
            };
            /* t_identifier: identifier */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_identifier_rule_node_supplier = () -> {
                try {
                    t_identifier_rule_node.add_new(identifier.name());
                    t_identifier_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                            (_tokens_list, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.name = _tokens_list.get(0).token_tex;
                                Identifiers_tables.Identifiers identifier = _ok.valid(identifiers_table.put_identifier(_ok, _extras_array));
                                next_block_identifier = new Identifiers_tables.Identifiers(identifier);
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return t_identifier_rule_node;
            };
            /* r_class<ignore><call>: t_class<ignore> t_class_1<repeat> t_class_2<ignore> t_class_3 t_identifier */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_class_rule_node_supplier = () -> {
                try {
                    r_class_rule_node.add_new(t_class_rule_node_supplier);
                    r_class_rule_node.add_new(t_class_1_rule_node_supplier);
                    r_class_rule_node.add_new(t_class_2_rule_node_supplier);
                    r_class_rule_node.add_new(t_identifier_rule_node_supplier);
                    r_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
                    r_class_rule_node.defined_call_the_success_rules_list_if_success = true;
                } catch (Exception e) {
                    return null;
                }
                return r_class_rule_node;
            };
            /* "_define_rule_class_method_and_attribute()<repeat>: r_class _define_rule_method_or_attribute_or_class()" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_define_rule_class_method_and_attribute_rule_node_supplier = () -> {
                try {
                    r_define_rule_class_method_and_attribute_rule_node.add_new(r_class_rule_node_supplier);
                    r_define_rule_class_method_and_attribute_rule_node.add_new(this::_define_rule_method_or_attribute_or_class, ok, extras_array);
                    r_define_rule_class_method_and_attribute_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return r_define_rule_class_method_and_attribute_rule_node;
            };
            retorno = r_define_rule_class_method_and_attribute_rule_node_supplier.get();
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
            r_method_or_attribute_or_class_rule_node.configure_from_key_name("r_method_or_attribute_or_class<ignore><repeat><call>: " +
                    "t_method_or_attribute_or_class<ignore> o_method_or_attribute_or_class_1<optional><repeat> " +
                    "o_method_or_attribute_or_class");
            t_method_or_attribute_or_class_rule_node.configure_from_key_name("t_method_or_attribute_or_class<ignore>: [public|protected|private" +
                    "|static|volatile|final" +
                    "|strictfp" +
                    "|identifier|void|boolean|byte|char|short|int|long|float|double" +
                    "|sign_less" +
                    "|abstract|sealed|class|interface|enum|record" +
                    "|annotation]");
            o_method_or_attribute_or_class_1_rule_node.configure_from_key_name("o_method_or_attribute_or_class_1_rule_node<optional><repeat>: " +
                    "[t_method_or_attribute_or_class_1|r_annotation]");
            r_annotation_rule_node.configure_from_key_name("r_annotation: t_annotation r_annotation_begin_end<optional>");
            t_annotation_rule_node.configure_from_key_name("t_annotation: annotation");
            r_annotation_begin_end_rule_node.configure_from_key_name("r_annotation_begin_end<optional>: t_annotation_parenthesis_open t_annotation_parenthesis_close<ignore> t_annotation_parenthesis_close_1");
            t_annotation_parenthesis_open_rule_node.configure_from_key_name("t_annotation_parenthesis_open: parenthesis_open");
            t_annotation_parenthesis_close_rule_node.configure_from_key_name("t_annotation_parenthesis_close<ignore>: parenthesis_close");
            t_annotation_parenthesis_close_1_rule_node.configure_from_key_name("t_annotation_parenthesis_close_1: parenthesis_close");
            t_method_or_attribute_or_class_1_rule_node.configure_from_key_name("t_method_or_attribute_or_class_1: " +
                    "[public|protected|private" +
                    "|static|volatile|final" +
                    "|strictfp]");
            o_method_or_attribute_or_class_rule_node.configure_from_key_name("o_method_or_attribute_or_class: " +
                    "[r_method_or_attribute" +
                    "|r_class]");
            r_method_or_attribute_rule_node.configure_from_key_name("r_method_or_attribute: " +
                    "r_template<optional> o_identifier_type t_identifier o_parenthesis_open_or_semicolon");
            r_class_rule_node.configure_from_key_name("r_class: t_class t_class_identifier");
            t_class_rule_node.configure_from_key_name("t_class: [class|interface|enum|record]");
            t_class_identifier_rule_node.configure_from_key_name("t_class_identifier: identifier");
            r_template_rule_node.configure_from_key_name("r_template<optional>: t_sign_less t_sign_bigger<ignore> ");
            t_template_sign_less_rule_node.configure_from_key_name("t_sign_less: sign_less");
            t_template_sign_bigger_rule_node.configure_from_key_name("t_sign_bigger<ignore>: sign_bigger");
            o_identifier_type_rule_node.configure_from_key_name("o_identifier_type: [t_identifier_type|r_identifier_class]");
            r_identifier_class_rule_node.configure_from_key_name("r_identifier_class: " +
                    "t_identifier r_dot_identifier<optional><repeat>");
            r_dot_identifier_rule_node.configure_from_key_name("r_dot_identifier<optional><repeat>: " +
                    "t_dot r_annotation_in_identifier<optional><repeat> t_identifier_type_extra");
            t_dot_rule_node.configure_from_key_name("t_dot: dot");
            r_annotation_in_identifier_rule_node.configure_from_key_name("r_annotation_in_identifier<optional><repeat>: " +
                    "r_annotation");
            t_identifier_type_rule_node.configure_from_key_name("t_identifier_type: " +
                    "[void|boolean|byte|char|short|int|long|float|double]");
            t_identifier_type_extra_rule_node.configure_from_key_name("t_identifier_type_extra: identifier");
            t_identifier_rule_node.configure_from_key_name("t_identifier: identifier");
            o_parenthesis_open_or_semicolon_rule_node.configure_from_key_name("o_parenthesis_open_or_semicolon: [t_parenthesis_open|t_semicolon<ignore>]");
            r_parenthesis_rule_node.configure_from_key_name("r_parenthesis: t_parenthesis_open t_parenthesis_close<ignore>");
            t_parenthesis_open_rule_node.configure_from_key_name("t_parenthesis_open: parenthesis_open");
            t_parenthesis_close_rule_node.configure_from_key_name("t_parenthesis_close<ignore>: parenthesis_close");
            t_semicolon_rule_node.configure_from_key_name("t_semicolon<ignore>: semi_colon");
            /* "t_class: [class|interface|enum|record]" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_class_rule_node_supplier = () -> {
                try {
                    t_class_rule_node.add_new(token_class.name());
                    t_class_rule_node.add_new(token_interface.name());
                    t_class_rule_node.add_new(token_enum.name());
                    t_class_rule_node.add_new(token_record.name());
                } catch (Exception e) {
                    return null;
                }
                return t_class_rule_node;
            };
            /* "t_class_identifier: identifier" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_class_identifier_rule_node_supplier = () -> {
                try {
                    t_class_identifier_rule_node.add_new(identifier.name());
                } catch (Exception e) {
                    return null;
                }
                return t_class_identifier_rule_node;
            };
            /* "r_class: t_class t_class_identifier" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_class_rule_node_supplier = () -> {
                try {
                    r_class_rule_node.add_new(t_class_rule_node_supplier);
                    r_class_rule_node.add_new(t_class_identifier_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return r_class_rule_node;
            };
            /* "t_method_or_attribute_or_class<ignore>: [public|protected|private" +
                    "|static|volatile|final" +
                    "|identifier|void|boolean|byte|char|short|int|long|float|double" +
                    "|sign_less" +
                    "|abstract|sealed|class|interface|enum|record]" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_method_or_attribute_or_class_rule_node_supplier = () -> {
                try {
                    t_method_or_attribute_or_class_rule_node.add_new(token_public.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_protected.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_private.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_static.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_volatile.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_final.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_strictfp.name());
                    t_method_or_attribute_or_class_rule_node.add_new(identifier.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_void.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_boolean.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_byte.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_char.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_short.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_int.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_long.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_float.name());
                    t_method_or_attribute_or_class_rule_node.add_new(type_double.name());
                    t_method_or_attribute_or_class_rule_node.add_new(sign_less.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_abstract.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_sealed.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_class.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_interface.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_enum.name());
                    t_method_or_attribute_or_class_rule_node.add_new(token_record.name());
                    t_method_or_attribute_or_class_rule_node.add_new(annotation.name());
                    t_method_or_attribute_or_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return t_method_or_attribute_or_class_rule_node;
            };
            /* t_method_or_attribute_or_class_1<optional><repeat>: " +
                    "[public|protected|private" +
                    "|static]" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_method_or_attribute_or_class_1_rule_node_supplier = () -> {
                try {
                    t_method_or_attribute_or_class_1_rule_node.add_new(token_public.name());
                    t_method_or_attribute_or_class_1_rule_node.add_new(token_protected.name());
                    t_method_or_attribute_or_class_1_rule_node.add_new(token_private.name());
                    t_method_or_attribute_or_class_1_rule_node.add_new(token_static.name());
                    t_method_or_attribute_or_class_1_rule_node.add_new(token_volatile.name());
                    t_method_or_attribute_or_class_1_rule_node.add_new(token_final.name());
                    t_method_or_attribute_or_class_1_rule_node.add_new(token_strictfp.name());
                    t_method_or_attribute_or_class_1_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                            (_tokens_list, _ok, _extras_array) -> {
                                Scanner_rules.Basic_tokens basic_token = _tokens_list.get(0);
                                identifiers_table.new_identifier.properties_list.add(new Scanner_rules.Basic_tokens(basic_token.token_type
                                        , basic_token.token_tex));
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return t_method_or_attribute_or_class_1_rule_node;
            };
            /* "t_annotation: annotation" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_annotation_rule_node_supplier = () -> {
                try {
                    t_annotation_rule_node.add_new(annotation.name());
                } catch (Exception e) {
                    return null;
                }
                return t_annotation_rule_node;
            };
            /* "t_annotation_parenthesis_open: parenthesis_open" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_annotation_parenthesis_open_rule_node_supplier = () -> {
                try {
                    t_annotation_parenthesis_open_rule_node.add_new(parenthesis_open.name());
                } catch (Exception e) {
                    return null;
                }
                return t_annotation_parenthesis_open_rule_node;
            };
            /* "t_annotation_parenthesis_close<ignore>: parenthesis_close" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_annotation_parenthesis_close_rule_node_supplier = () -> {
                try {
                    t_annotation_parenthesis_close_rule_node.add_new(parenthesis_close.name());
                    t_annotation_parenthesis_close_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return t_annotation_parenthesis_close_rule_node;
            };
            /* "t_annotation_parenthesis_close_1: parenthesis_close" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_annotation_parenthesis_close_1_rule_node_supplier = () -> {
                try {
                    t_annotation_parenthesis_close_1_rule_node.add_new(parenthesis_close.name());
                } catch (Exception e) {
                    return null;
                }
                return t_annotation_parenthesis_close_1_rule_node;
            };
            /* "r_annotation_begin_end<optional>: t_annotation_parenthesis_open t_annotation_parenthesis_close<ignore>" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_annotation_begin_end_rule_node_supplier = () -> {
                try {
                    r_annotation_begin_end_rule_node.add_new(t_annotation_parenthesis_open_rule_node_supplier);
                    r_annotation_begin_end_rule_node.add_new(t_annotation_parenthesis_close_rule_node_supplier);
                    r_annotation_begin_end_rule_node.add_new(t_annotation_parenthesis_close_1_rule_node_supplier);
                    r_annotation_begin_end_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
                } catch (Exception e) {
                    return null;
                }
                return r_annotation_begin_end_rule_node;
            };
            /* "r_annotation: r_annotation_1 r_annotation_begin_end<optional>" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_annotation_rule_node_supplier = () -> {
                try {
                    r_annotation_rule_node.add_new(t_annotation_rule_node_supplier);
                    r_annotation_rule_node.add_new(r_annotation_begin_end_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return r_annotation_rule_node;
            };
            /* "o_method_or_attribute_or_class_1_rule_node<optional><repeat>: " +
                    "[t_method_or_attribute_or_class_1|r_annotation" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers o_method_or_attribute_or_class_1_rule_node_supplier = () -> {
                try {
                    o_method_or_attribute_or_class_1_rule_node.add_new(t_method_or_attribute_or_class_1_rule_node_supplier);
                    o_method_or_attribute_or_class_1_rule_node.add_new(r_annotation_rule_node_supplier);
                    o_method_or_attribute_or_class_1_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
                    o_method_or_attribute_or_class_1_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return o_method_or_attribute_or_class_1_rule_node;
            };
            /* "t_sign_less: sign_less" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_template_sign_less_rule_node_supplier = () -> {
                try {
                    t_template_sign_less_rule_node.add_new(sign_less.name());
                } catch (Exception e) {
                    return null;
                }
                return t_template_sign_less_rule_node;
            };
            /* "t_sign_bigger<ignore>: sign_bigger" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_template_sign_bigger_rule_node_supplier = () -> {
                try {
                    t_template_sign_bigger_rule_node.add_new(sign_bigger.name());
                    t_template_sign_bigger_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return t_template_sign_bigger_rule_node;
            };
            /* "r_template<optional>: t_sign_less t_sign_bigger<ignore> t_sign_bigger_1" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_template_rule_node_supplier = () -> {
                try {
                    r_template_rule_node.add_new(t_template_sign_less_rule_node_supplier);
                    r_template_rule_node.add_new(t_template_sign_bigger_rule_node_supplier);
                    r_template_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
                } catch (Exception e) {
                    return null;
                }
                return r_template_rule_node;
            };
            /* "t_identifier_type: " +
                    "[void|boolean|byte|char|short|int|long|float|double]" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_identifier_type_rule_node_supplier = () -> {
                try {
                    t_identifier_type_rule_node.add_new(type_void.name());
                    t_identifier_type_rule_node.add_new(type_boolean.name());
                    t_identifier_type_rule_node.add_new(type_byte.name());
                    t_identifier_type_rule_node.add_new(type_char.name());
                    t_identifier_type_rule_node.add_new(type_short.name());
                    t_identifier_type_rule_node.add_new(type_int.name());
                    t_identifier_type_rule_node.add_new(type_long.name());
                    t_identifier_type_rule_node.add_new(type_float.name());
                    t_identifier_type_rule_node.add_new(type_double.name());
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
                } catch (Exception e) {
                    return null;
                }
                return t_identifier_type_rule_node;
            };
            /* "t_identifier: identifier" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_identifier_rule_node_supplier = () -> {
                try {
                    t_identifier_rule_node.add_new(identifier.name());
                    t_identifier_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                            (_tokens_list, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.name = _tokens_list.get(0).token_tex;
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return t_identifier_rule_node;
            };
            /* "t_dot: dot" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_dot_rule_node_supplier = () -> {
                try {
                    t_dot_rule_node.add_new(dot.name());
                } catch (Exception e) {
                    return null;
                }
                return t_dot_rule_node;
            };
            /* "r_annotation_in_identifier<optional><repeat>: " +
                    "r_annotation" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_annotation_in_identifier_rule_node_supplier = () -> {
                try {
                    r_annotation_in_identifier_rule_node.add_new(r_annotation_rule_node_supplier);
                    r_annotation_in_identifier_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
                    r_annotation_in_identifier_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return r_annotation_in_identifier_rule_node;
            };
            /* "t_identifier_type_extra: identifier" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_identifier_type_extra_rule_node_supplier = () -> {
                try {
                    t_identifier_type_extra_rule_node.add_new(identifier.name());
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
                } catch (Exception e) {
                    return null;
                }
                return t_identifier_type_extra_rule_node;
            };
            /* "r_dot_identifier<optional><repeat>: " +
                    "t_dot r_annotation_in_identifier<optional> t_identifier_extra" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_dot_identifier_rule_node_supplier = () -> {
                try {
                    r_dot_identifier_rule_node.add_new(t_dot_rule_node_supplier);
                    r_dot_identifier_rule_node.add_new(r_annotation_in_identifier_rule_node_supplier);
                    r_dot_identifier_rule_node.add_new(t_identifier_type_extra_rule_node_supplier);
                    r_dot_identifier_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.optional;
                    r_dot_identifier_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return r_dot_identifier_rule_node;
            };
            /* "r_identifier_class: " +
                    "t_identifier r_dot_identifier<optional><repeat>" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_identifier_class_rule_node_supplier = () -> {
                try {
                    r_identifier_class_rule_node.add_new(t_identifier_rule_node_supplier);
                    r_identifier_class_rule_node.add_new(r_dot_identifier_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return r_identifier_class_rule_node;
            };
            /* "o_identifier_type: [t_identifier_type|r_identifier_class]" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers o_identifier_type_rule_node_supplier = () -> {
                try {
                    o_identifier_type_rule_node.add_new(t_identifier_type_rule_node_supplier);
                    o_identifier_type_rule_node.add_new(r_identifier_class_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return o_identifier_type_rule_node;
            };
            /* "r_parenthesis: t_parenthesis_open t_parenthesis_close<ignore>" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_parenthesis_rule_node_supplier = () -> {
                try {
                    r_parenthesis_rule_node.add_new(t_annotation_parenthesis_open_rule_node_supplier);
                    r_parenthesis_rule_node.add_new(t_annotation_parenthesis_close_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return r_parenthesis_rule_node;
            };
            /* "t_semicolon<ignore>: semi_colon" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_semicolon_rule_node_supplier = () -> {
                try {
                    t_semicolon_rule_node.add_new(semi_colon.name());
                    t_semicolon_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return t_semicolon_rule_node;
            };
            /* "o_parenthesis_open_or_semicolon: [t_parenthesis_open|t_semicolon<ignore>]" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers o_parenthesis_open_or_semicolon_rule_node_supplier = () -> {
                try {
                    o_parenthesis_open_or_semicolon_rule_node.add_new(r_parenthesis_rule_node_supplier);
                    o_parenthesis_open_or_semicolon_rule_node.add_new(t_semicolon_rule_node_supplier);
                    o_parenthesis_open_or_semicolon_rule_node.defined_rule_success = new Analizer_rules.Rule_success(
                            (_tokens_list, _ok, _extras_array) -> {
                                if (next_block_identifier != null) {
                                    Identifiers_tables.Temporary_identifiers_tables temporary_identifiers_table
                                            = _ok.valid(identifiers_table.get_top(_ok, _extras_array));
                                    if (_ok.is == false) return;
                                    temporary_identifiers_table.block_identifier = next_block_identifier;
                                    next_block_identifier = null;
                                }
                                if (_tokens_list.get(0).token_type.equals(parenthesis_open.name())) {
                                    identifiers_table.new_identifier.type = Types.method.name();
                                } else {
                                    identifiers_table.new_identifier.type = Types.attribute.name();
                                }
                                identifiers_table.put_identifier(_ok, _extras_array);
                                if (_ok.is == false) return;
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return o_parenthesis_open_or_semicolon_rule_node;
            };
            /* r_method_or_attribute_rule_node: " +
                    "t_volatile_or_final<optional><repeat> r_template<optional> t_identifier_type t_identifier t_parenthesis_open_or_semicolon<ignore>" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_method_or_attribute_rule_node_supplier = () -> {
                try {
                    r_method_or_attribute_rule_node.add_new(r_template_rule_node_supplier);
                    r_method_or_attribute_rule_node.add_new(o_identifier_type_rule_node_supplier);
                    r_method_or_attribute_rule_node.add_new(t_identifier_rule_node_supplier);
                    r_method_or_attribute_rule_node.add_new(o_parenthesis_open_or_semicolon_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return r_method_or_attribute_rule_node;
            };
            /* "o_method_or_attribute_or_class: " +
                    "[r_method_or_attribute" +
                    "|r_class]" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers o_method_or_attribute_or_class_rule_node_supplier = () -> {
                try {
                    o_method_or_attribute_or_class_rule_node.add_new(r_method_or_attribute_rule_node_supplier);
                    o_method_or_attribute_or_class_rule_node.add_new(r_class_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return o_method_or_attribute_or_class_rule_node;
            };
            /* "r_method_or_attribute_or_class<ignore><repeat><call>: " +
                    "t_method_or_attribute_or_class<ignore> t_method_or_attribute_or_class_1" +
                    "o_method_or_attribute_or_class" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers r_method_or_attribute_or_class_rule_node_supplier = () -> {
                try {
                    r_method_or_attribute_or_class_rule_node.add_new(t_method_or_attribute_or_class_rule_node_supplier);
                    r_method_or_attribute_or_class_rule_node.add_new(o_method_or_attribute_or_class_1_rule_node_supplier);
                    r_method_or_attribute_or_class_rule_node.add_new(o_method_or_attribute_or_class_rule_node_supplier);
                    r_method_or_attribute_or_class_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
                    r_method_or_attribute_or_class_rule_node.defined_repeat_mode = Analizer_rules.Repeat_mode.repeat_while_success;
                    r_method_or_attribute_or_class_rule_node.defined_call_the_success_rules_list_if_success = true;
                } catch (Exception e) {
                    return null;
                }
                return r_method_or_attribute_or_class_rule_node;
            };
            /* "t_parenthesis_open: parenthesis_open" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_parenthesis_open_rule_node_supplier = () -> {
                try {
                    t_parenthesis_open_rule_node.add_new(parenthesis_open.name());
                } catch (Exception e) {
                    return null;
                }
                return t_parenthesis_open_rule_node;
            };
            /* "t_parenthesis_close<ignore>: parenthesis_close" */
            final Analizer_rules.Rule_nodes.Rule_creator_suppliers t_parenthesis_close_rule_node_supplier = () -> {
                try {
                    t_parenthesis_close_rule_node.add_new(parenthesis_close.name());
                    t_parenthesis_close_rule_node.defined_optional_mode = Analizer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return t_parenthesis_close_rule_node;
            };
            retorno = r_method_or_attribute_or_class_rule_node_supplier.get();
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

}
