package innui.code_processor.java;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.code_processor.Analyzer_rules;
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
            analyizer_rule.start_rule_node = ra_define_rule_start(ok, extras_array);
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
    public Analyzer_rules.@Nullable Rule_nodes ra_define_rule_start(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        try {
            Analyzer_rules.Rules_and_rule_nodes ra_start_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            ra_start_rule_node.configure_from_key_name("ra_define_rule_start(): ra_define_rule_package()<optional><call> ra_define_rule_class_method_and_attribute()<repeat>");
            /* "ra_define_rule_start(): ra_define_rule_package()<optional><call> ra_define_rule_class_method_and_attribute()<repeat>" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_start_rule_node_supplier = () -> {
                try {
                    ra_start_rule_node.clear_add_new_list();
                    ra_start_rule_node.add_new(this::ra_define_rule_package, ok, extras_array);
                    if (ok.is == false) return null;
                    ra_start_rule_node.add_new(this::ra_define_rule_class_method_and_attribute, ok, extras_array);
                    if (ok.is == false) return null;
                } catch (Exception e) {
                    return null;
                }
                return ra_start_rule_node;
            };
            Analyzer_rules.Rule_nodes rule_node = ok.valid(ra_start_rule_node_supplier.get());
            analyizer_rule.rule_nodes_map.put(rule_node.get_key_name(), rule_node);
            return rule_node;
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
    public Analyzer_rules.@Nullable Rule_nodes ra_define_rule_package(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analyzer_rules.Rules_and_rule_nodes retorno = null;
        try {
            Analyzer_rules.Rules_and_rule_nodes ra_define_rule_package_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_package_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_package_identifier_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_doto_identifier_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_doto_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_package_identifier_1_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_semi_colon_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            ra_define_rule_package_rule_node.configure_from_key_name("ra_define_rule_package()<optional><call>: to_package ra_identifier ra_doto_identifier<optional><repeat> ra_semi_colon");
            to_package_rule_node.configure_from_key_name("to_package: package ");
            to_package_identifier_rule_node.configure_from_key_name("to_package_identifier: identifier");
            ra_doto_identifier_rule_node.configure_from_key_name("ra_doto_identifier<optional><repeat>: to_dot to_identifier_1");
            to_semi_colon_rule_node.configure_from_key_name("to_semi_colon: semi_colon");
            to_doto_rule_node.configure_from_key_name("to_dot: dot ");
            to_package_identifier_1_rule_node.configure_from_key_name("to_package_identifier_1: identifier");
            /* "to_semi_colon: semi_colon" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_semi_colon_rule_node_supplier = () -> {
                try {
                    to_semi_colon_rule_node.clear_add_new_list();
                    to_semi_colon_rule_node.add_new(semi_colon.name());
                    to_semi_colon_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                identifiers_table.create_top_table(braces_num, _ok, _extras_array);
                                if (_ok.is == false) return;
                                identifiers_table.put_identifier(true, _ok, _extras_array);
                                if (_ok.is == false) return;
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_semi_colon_rule_node;
            };
            /* "to_package_identifier_1: identifier" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_identifier_1_rule_node_supplier = () -> {
                try {
                    to_package_identifier_1_rule_node.clear_add_new_list();
                    to_package_identifier_1_rule_node.add_new(identifier.name());
                    to_package_identifier_1_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                if (identifiers_table.new_identifier.namespace.isEmpty()) {
                                    identifiers_table.new_identifier.namespace
                                            = identifiers_table.new_identifier.name;
                                } else {
                                    identifiers_table.new_identifier.namespace
                                            = identifiers_table.new_identifier.namespace
                                            + "."
                                            + identifiers_table.new_identifier.name;
                                }
                                identifiers_table.new_identifier.name = _basic_token.token_tex;
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_package_identifier_1_rule_node;
            };
            /* "to_dot: dot " */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_doto_rule_node_supplier = () -> {
                try {
                    to_doto_rule_node.clear_add_new_list();
                    to_doto_rule_node.add_new(dot.name());
                } catch (Exception e) {
                    return null;
                }
                return to_doto_rule_node;
            };
            /* "to_package_identifier: identifier" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_identifier_rule_node_supplier = () -> {
                try {
                    to_package_identifier_rule_node.clear_add_new_list();
                    to_package_identifier_rule_node.add_new(identifier.name());
                    to_package_identifier_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.name = _basic_token.token_tex;
                                identifiers_table.new_identifier.type = Types.token_package.name();
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_package_identifier_rule_node;
            };
            /* "ra_doto_identifier<optional><repeat>: to_dot to_identifier_1" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_doto_identifier_rule_node_supplier = () -> {
                try {
                    ra_doto_identifier_rule_node.clear_add_new_list();
                    ra_doto_identifier_rule_node.add_new(to_doto_rule_node_supplier);
                    ra_doto_identifier_rule_node.add_new(to_identifier_1_rule_node_supplier);
                    ra_doto_identifier_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.optional;
                    ra_doto_identifier_rule_node.defined_repeat_mode = Analyzer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return ra_doto_identifier_rule_node;
            };
            /* "to_package: package " */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_package_rule_node_supplier = () -> {
                try {
                    to_package_rule_node.clear_add_new_list();
                    to_package_rule_node.add_new(token_package.name());
                    to_package_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.init(_ok, _extras_array);
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_package_rule_node;
            };
            /* "ra_define_rule_package()<optional><call>: to_package ra_identifier ra_doto_identifier<optional><repeat> ra_semi_colon" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_define_rule_package_rule_node_supplier = () -> {
                try {
                    ra_define_rule_package_rule_node.clear_add_new_list();
                    ra_define_rule_package_rule_node.add_new(to_package_rule_node_supplier);
                    ra_define_rule_package_rule_node.add_new(to_identifier_rule_node_supplier);
                    ra_define_rule_package_rule_node.add_new(ra_doto_identifier_rule_node_supplier);
                    ra_define_rule_package_rule_node.add_new(to_semi_colon_rule_node_supplier);
                    ra_define_rule_package_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.optional;
                    ra_define_rule_package_rule_node.defined_call_the_success_rules_list_if_success = true;
                } catch (Exception e) {
                    return null;
                }
                return ra_define_rule_package_rule_node;
            };
            retorno = (Analyzer_rules.Rules_and_rule_nodes) ra_define_rule_package_rule_node_supplier.get();
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
            retorno = counto_braces(is_new_token, basic_token, ok, extras_array);
            if (ok.is == false) return false;
            if (retorno) {
                return false;
            }
            retorno = filtera_token(is_new_token, basic_token, ok, extras_array);
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
    public boolean filtera_token(boolean is_new_token, Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
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
    public boolean counto_braces(boolean is_new_token, Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
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
    public Analyzer_rules.@Nullable Rule_nodes ra_define_rule_class_method_and_attribute(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analyzer_rules.Rule_nodes retorno = null;
        try {
            Analyzer_rules.Rules_and_rule_nodes ra_define_rule_class_method_and_attribute_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_class_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_class_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_class_1_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_class_2_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_class_identifier_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            ra_define_rule_class_method_and_attribute_rule_node.configure_from_key_name("ra_define_rule_class_method_and_attribute()<repeat>: " +
                    "ra_class<ignore><call> ra_define_rule_method_or_attribute_or_class()");
            ra_class_rule_node.configure_from_key_name("ra_class<ignore><call>: " +
                    "to_class<ignore> to_class_1<optional><repeat> to_class_2 to_identifier");
            to_class_rule_node.configure_from_key_name("to_class<ignore>: [public|protected|private" +
                    "|static|abstract|sealed" +
                    "|strictfp|transient" +
                    "|class|interface|enum|record]");
            to_class_1_rule_node.configure_from_key_name("to_class_1<optional><repeat>: " +
                    "[public|protected|private|static|abstract|sealed|strictfp|transient]");
            to_class_2_rule_node.configure_from_key_name("to_class_2: [class|interface|enum|record]");
            to_class_identifier_rule_node.configure_from_key_name("to_class_identifier: identifier");
            /* to_class<ignore>: [public|protected|private|static|abstract|sealed|strictfp|transient|class|interface|enum]<ignore> ra_class_2 */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_class_rule_node_supplier = () -> {
                try {
                    to_class_rule_node.clear_add_new_list();
                    to_class_rule_node.add_new(token_public.name());
                    to_class_rule_node.add_new(token_protected.name());
                    to_class_rule_node.add_new(token_private.name());
                    to_class_rule_node.add_new(token_static.name());
                    to_class_rule_node.add_new(token_abstract.name());
                    to_class_rule_node.add_new(token_sealed.name());
                    to_class_rule_node.add_new(token_strictfp.name());
                    to_class_rule_node.add_new(token_transient.name());
                    to_class_rule_node.add_new(token_class.name());
                    to_class_rule_node.add_new(token_interface.name());
                    to_class_rule_node.add_new(token_enum.name());
                    to_class_rule_node.add_new(token_record.name());
                    to_class_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return to_class_rule_node;
            };
            /* to_class_1<optional><repeat>: [public|protected|private|static|abstract|sealed|strictfp|transient] */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_class_1_rule_node_supplier = () -> {
                try {
                    to_class_1_rule_node.clear_add_new_list();
                    to_class_1_rule_node.add_new(token_public.name());
                    to_class_1_rule_node.add_new(token_protected.name());
                    to_class_1_rule_node.add_new(token_private.name());
                    to_class_1_rule_node.add_new(token_static.name());
                    to_class_1_rule_node.add_new(token_abstract.name());
                    to_class_1_rule_node.add_new(token_sealed.name());
                    to_class_1_rule_node.add_new(token_strictfp.name());
                    to_class_1_rule_node.add_new(token_transient.name());
                    to_class_1_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.optional;
                    to_class_1_rule_node.defined_repeat_mode = Analyzer_rules.Repeat_mode.repeat_while_success;
                    to_class_1_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.properties_list.add(new Scanner_rules.Basic_tokens(_basic_token.token_type
                                        , _basic_token.token_tex));
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_class_1_rule_node;
            };
            /* to_class_2: [class|interface|enum|record] */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_class_2_rule_node_supplier = () -> {
                try {
                    to_class_2_rule_node.clear_add_new_list();
                    to_class_2_rule_node.add_new(token_class.name());
                    to_class_2_rule_node.add_new(token_interface.name());
                    to_class_2_rule_node.add_new(token_enum.name());
                    to_class_2_rule_node.add_new(token_record.name());
                    to_class_2_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                String name = _basic_token.token_type;
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
                return to_class_2_rule_node;
            };
            /* to_class_identifier: identifier */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_identifier_rule_node_supplier = () -> {
                try {
                    to_class_identifier_rule_node.clear_add_new_list();
                    to_class_identifier_rule_node.add_new(identifier.name());
                    to_class_identifier_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.name = _basic_token.token_tex;
                                Identifiers_tables.Identifiers identifier = _ok.valid(identifiers_table.put_identifier(_ok, _extras_array));
                                next_block_identifier = new Identifiers_tables.Identifiers(identifier);
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_class_identifier_rule_node;
            };
            /* ra_class<ignore><call>: to_class<ignore> to_class_1<repeat> to_class_2<ignore> to_class_3 to_identifier */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_class_rule_node_supplier = () -> {
                try {
                    ra_class_rule_node.clear_add_new_list();
                    ra_class_rule_node.add_new(to_class_rule_node_supplier);
                    ra_class_rule_node.add_new(to_class_1_rule_node_supplier);
                    ra_class_rule_node.add_new(to_class_2_rule_node_supplier);
                    ra_class_rule_node.add_new(to_identifier_rule_node_supplier);
                    ra_class_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.ignore_until_matches;
                    ra_class_rule_node.defined_call_the_success_rules_list_if_success = true;
                } catch (Exception e) {
                    return null;
                }
                return ra_class_rule_node;
            };
            /* "ra_define_rule_class_method_and_attribute()<repeat>: ra_class ra_define_rule_method_or_attribute_or_class()" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_define_rule_class_method_and_attribute_rule_node_supplier = () -> {
                try {
                    ra_define_rule_class_method_and_attribute_rule_node.clear_add_new_list();
                    ra_define_rule_class_method_and_attribute_rule_node.add_new(ra_class_rule_node_supplier);
                    ra_define_rule_class_method_and_attribute_rule_node.add_new(this::ra_define_rule_method_or_attribute_or_class, ok, extras_array);
                    ra_define_rule_class_method_and_attribute_rule_node.defined_repeat_mode = Analyzer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return ra_define_rule_class_method_and_attribute_rule_node;
            };
            retorno = ra_define_rule_class_method_and_attribute_rule_node_supplier.get();
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
    public Analyzer_rules.@Nullable Rule_nodes ra_define_rule_method_or_attribute_or_class(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Analyzer_rules.Rule_nodes retorno = null;
        try {
            Analyzer_rules.Rules_and_rule_nodes ra_method_or_attribute_or_class_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_method_or_attribute_or_class_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_or_rule_nodes ro_method_or_attribute_or_class_1_rule_node = new Analyzer_rules.Rules_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_method_or_attribute_or_class_1_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_or_rule_nodes ro_method_or_attribute_or_class_rule_node = new Analyzer_rules.Rules_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_identifier_type_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_identifier_type_class_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_identifier_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_or_rule_nodes ro_parenthesis_open_or_semicolon_rule_node = new Analyzer_rules.Rules_or_rule_nodes(analyizer_rule);
//            Analizera_rules.Rules_and_rule_nodes ra_parenthesis_rule_node = new Analizera_rules.Rules_and_rule_nodes(analizera_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_parenthesis_open_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
//            Analizera_rules.Tokens_or_rule_nodes to_parenthesis_close_rule_node = new Analizera_rules.Tokens_or_rule_nodes(analizera_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_semicolon_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_template_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_template_sign_less_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_template_sign_biggera_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_or_rule_nodes ro_identifier_type_rule_node = new Analyzer_rules.Rules_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_identifier_class_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_doto_identifier_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_doto_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_annotation_in_identifier_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_annotation_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_annotation_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_annotation_begin_end_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_annotation_parenthesis_open_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_annotation_parenthesis_close_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_annotation_parenthesis_close_1_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_method_or_attribute_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Rules_and_rule_nodes ra_class_rule_node = new Analyzer_rules.Rules_and_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_class_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            Analyzer_rules.Tokens_or_rule_nodes to_class_identifier_rule_node = new Analyzer_rules.Tokens_or_rule_nodes(analyizer_rule);
            ra_method_or_attribute_or_class_rule_node.configure_from_key_name("ra_method_or_attribute_or_class<ignore><repeat><call>: " +
                    "to_method_or_attribute_or_class<ignore> ro_method_or_attribute_or_class_1<optional><repeat> " +
                    "ro_method_or_attribute_or_class");
            to_method_or_attribute_or_class_rule_node.configure_from_key_name("to_method_or_attribute_or_class<ignore>: [public|protected|private" +
                    "|static|volatile|final" +
                    "|strictfp|transient" +
                    "|identifier|void|boolean|byte|char|short|int|long|float|double" +
                    "|sign_less" +
                    "|abstract|sealed|class|interface|enum|record" +
                    "|annotation]");
            ro_method_or_attribute_or_class_1_rule_node.configure_from_key_name("ro_method_or_attribute_or_class_1<optional><repeat>: " +
                    "[to_method_or_attribute_or_class_1|ra_annotation]");
            ra_annotation_rule_node.configure_from_key_name("ra_annotation: to_annotation ra_annotation_begin_end<optional>");
            to_annotation_rule_node.configure_from_key_name("to_annotation: annotation");
            ra_annotation_begin_end_rule_node.configure_from_key_name("ra_annotation_begin_end<optional>: to_annotation_parenthesis_open to_annotation_parenthesis_close<ignore> to_annotation_parenthesis_close_1");
            to_annotation_parenthesis_open_rule_node.configure_from_key_name("to_annotation_parenthesis_open: parenthesis_open");
            to_annotation_parenthesis_close_rule_node.configure_from_key_name("to_annotation_parenthesis_close<ignore>: parenthesis_close");
            to_annotation_parenthesis_close_1_rule_node.configure_from_key_name("to_annotation_parenthesis_close_1: parenthesis_close");
            to_method_or_attribute_or_class_1_rule_node.configure_from_key_name("to_method_or_attribute_or_class_1: " +
                    "[public|protected|private" +
                    "|static|volatile|final" +
                    "|strictfp|transient]");
            ro_method_or_attribute_or_class_rule_node.configure_from_key_name("ro_method_or_attribute_or_class: " +
                    "[ra_method_or_attribute" +
                    "|ra_class]");
            ra_method_or_attribute_rule_node.configure_from_key_name("ra_method_or_attribute: " +
                    "ra_template<optional> ro_identifier_type to_identifier ro_parenthesis_open_or_semicolon");
            ra_class_rule_node.configure_from_key_name("ra_class: to_class to_class_identifier");
            to_class_rule_node.configure_from_key_name("to_class: [class|interface|enum|record]");
            to_class_identifier_rule_node.configure_from_key_name("to_class_identifier: identifier");
            ra_template_rule_node.configure_from_key_name("ra_template<optional>: to_sign_less to_sign_bigger<ignore> ");
            to_template_sign_less_rule_node.configure_from_key_name("to_sign_less: sign_less");
            to_template_sign_biggera_rule_node.configure_from_key_name("to_sign_bigger<ignore>: sign_bigger");
            ro_identifier_type_rule_node.configure_from_key_name("ro_identifier_type: [to_identifier_type|ra_identifier_class]");
            ra_identifier_class_rule_node.configure_from_key_name("ra_identifier_class: " +
                    "to_identifier_type_class ra_doto_identifier<optional><repeat>");
            ra_doto_identifier_rule_node.configure_from_key_name("ra_doto_identifier<optional><repeat>: " +
                    "to_dot ra_annotation_in_identifier<optional><repeat> to_identifier_type_class");
            to_doto_rule_node.configure_from_key_name("to_dot: dot");
            ra_annotation_in_identifier_rule_node.configure_from_key_name("ra_annotation_in_identifier<optional><repeat>: " +
                    "ra_annotation");
            to_identifier_type_rule_node.configure_from_key_name("to_identifier_type: " +
                    "[void|boolean|byte|char|short|int|long|float|double]");
            to_identifier_type_class_rule_node.configure_from_key_name("to_identifier_type_class: identifier");
            to_identifier_rule_node.configure_from_key_name("to_identifier: identifier");
            ro_parenthesis_open_or_semicolon_rule_node.configure_from_key_name("ro_parenthesis_open_or_semicolon: [to_parenthesis_open|to_semicolon<ignore>]");
//            ra_parenthesis_rule_node.configure_from_key_name("ra_parenthesis: to_parenthesis_open to_parenthesis_close<ignore>");
            to_parenthesis_open_rule_node.configure_from_key_name("to_parenthesis_open: parenthesis_open");
//            to_parenthesis_close_rule_node.configure_from_key_name("to_parenthesis_close<ignore>: parenthesis_close");
            to_semicolon_rule_node.configure_from_key_name("to_semicolon<ignore>: semi_colon");
            /* "to_class: [class|interface|enum|record]" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_class_rule_node_supplier = () -> {
                try {
                    to_class_rule_node.clear_add_new_list();
                    to_class_rule_node.add_new(token_class.name());
                    to_class_rule_node.add_new(token_interface.name());
                    to_class_rule_node.add_new(token_enum.name());
                    to_class_rule_node.add_new(token_record.name());
                } catch (Exception e) {
                    return null;
                }
                return to_class_rule_node;
            };
            /* "to_class_identifier: identifier" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_class_identifier_rule_node_supplier = () -> {
                try {
                    to_class_identifier_rule_node.clear_add_new_list();
                    to_class_identifier_rule_node.add_new(identifier.name());
                } catch (Exception e) {
                    return null;
                }
                return to_class_identifier_rule_node;
            };
            /* "ra_class: to_class to_class_identifier" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_class_rule_node_supplier = () -> {
                try {
                    ra_class_rule_node.clear_add_new_list();
                    ra_class_rule_node.add_new(to_class_rule_node_supplier);
                    ra_class_rule_node.add_new(to_class_identifier_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return ra_class_rule_node;
            };
            /* "to_method_or_attribute_or_class<ignore>: [public|protected|private" +
                    "|static|volatile|final" +
                    "|identifier|void|boolean|byte|char|short|int|long|float|double" +
                    "|sign_less" +
                    "|abstract|sealed|class|interface|enum|record]" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_method_or_attribute_or_class_rule_node_supplier = () -> {
                try {
                    to_method_or_attribute_or_class_rule_node.clear_add_new_list();
                    to_method_or_attribute_or_class_rule_node.add_new(token_public.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_protected.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_private.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_static.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_volatile.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_final.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_strictfp.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_transient.name());
                    to_method_or_attribute_or_class_rule_node.add_new(identifier.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_void.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_boolean.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_byte.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_char.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_short.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_int.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_long.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_float.name());
                    to_method_or_attribute_or_class_rule_node.add_new(type_double.name());
                    to_method_or_attribute_or_class_rule_node.add_new(sign_less.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_abstract.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_sealed.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_class.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_interface.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_enum.name());
                    to_method_or_attribute_or_class_rule_node.add_new(token_record.name());
                    to_method_or_attribute_or_class_rule_node.add_new(annotation.name());
                    to_method_or_attribute_or_class_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return to_method_or_attribute_or_class_rule_node;
            };
            /* to_method_or_attribute_or_class_1<optional><repeat>: " +
                    "[public|protected|private" +
                    "|static]" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_method_or_attribute_or_class_1_rule_node_supplier = () -> {
                try {
                    to_method_or_attribute_or_class_1_rule_node.clear_add_new_list();
                    to_method_or_attribute_or_class_1_rule_node.add_new(token_public.name());
                    to_method_or_attribute_or_class_1_rule_node.add_new(token_protected.name());
                    to_method_or_attribute_or_class_1_rule_node.add_new(token_private.name());
                    to_method_or_attribute_or_class_1_rule_node.add_new(token_static.name());
                    to_method_or_attribute_or_class_1_rule_node.add_new(token_volatile.name());
                    to_method_or_attribute_or_class_1_rule_node.add_new(token_final.name());
                    to_method_or_attribute_or_class_1_rule_node.add_new(token_strictfp.name());
                    to_method_or_attribute_or_class_1_rule_node.add_new(token_transient.name());
                    to_method_or_attribute_or_class_1_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.properties_list.add(new Scanner_rules.Basic_tokens(_basic_token.token_type
                                        , _basic_token.token_tex));
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_method_or_attribute_or_class_1_rule_node;
            };
            /* "to_annotation: annotation" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_annotation_rule_node_supplier = () -> {
                try {
                    to_annotation_rule_node.clear_add_new_list();
                    to_annotation_rule_node.add_new(annotation.name());
                } catch (Exception e) {
                    return null;
                }
                return to_annotation_rule_node;
            };
            /* "to_annotation_parenthesis_open: parenthesis_open" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_annotation_parenthesis_open_rule_node_supplier = () -> {
                try {
                    to_annotation_parenthesis_open_rule_node.clear_add_new_list();
                    to_annotation_parenthesis_open_rule_node.add_new(parenthesis_open.name());
                } catch (Exception e) {
                    return null;
                }
                return to_annotation_parenthesis_open_rule_node;
            };
            /* "to_annotation_parenthesis_close<ignore>: parenthesis_close" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_annotation_parenthesis_close_rule_node_supplier = () -> {
                try {
                    to_annotation_parenthesis_close_rule_node.clear_add_new_list();
                    to_annotation_parenthesis_close_rule_node.add_new(parenthesis_close.name());
                    to_annotation_parenthesis_close_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return to_annotation_parenthesis_close_rule_node;
            };
            /* "to_annotation_parenthesis_close_1: parenthesis_close" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_annotation_parenthesis_close_1_rule_node_supplier = () -> {
                try {
                    to_annotation_parenthesis_close_1_rule_node.clear_add_new_list();
                    to_annotation_parenthesis_close_1_rule_node.add_new(parenthesis_close.name());
                } catch (Exception e) {
                    return null;
                }
                return to_annotation_parenthesis_close_1_rule_node;
            };
            /* "ra_annotation_begin_end<optional>: to_annotation_parenthesis_open to_annotation_parenthesis_close<ignore>" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_annotation_begin_end_rule_node_supplier = () -> {
                try {
                    ra_annotation_begin_end_rule_node.clear_add_new_list();
                    ra_annotation_begin_end_rule_node.add_new(to_annotation_parenthesis_open_rule_node_supplier);
                    ra_annotation_begin_end_rule_node.add_new(to_annotation_parenthesis_close_rule_node_supplier);
                    ra_annotation_begin_end_rule_node.add_new(to_annotation_parenthesis_close_1_rule_node_supplier);
                    ra_annotation_begin_end_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.optional;
                } catch (Exception e) {
                    return null;
                }
                return ra_annotation_begin_end_rule_node;
            };
            /* "ra_annotation: to_annotation ra_annotation_begin_end<optional>" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_annotation_rule_node_supplier = () -> {
                try {
                    ra_annotation_rule_node.clear_add_new_list();
                    ra_annotation_rule_node.add_new(to_annotation_rule_node_supplier);
                    ra_annotation_rule_node.add_new(ra_annotation_begin_end_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return ra_annotation_rule_node;
            };
            /* "ro_method_or_attribute_or_class_1<optional><repeat>: " +
                    "[to_method_or_attribute_or_class_1|ra_annotation" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ro_method_or_attribute_or_class_1_rule_node_supplier = () -> {
                try {
                    ro_method_or_attribute_or_class_1_rule_node.clear_add_new_list();
                    ro_method_or_attribute_or_class_1_rule_node.clear_add_new_list();
                    ro_method_or_attribute_or_class_1_rule_node.add_new(to_method_or_attribute_or_class_1_rule_node_supplier);
                    ro_method_or_attribute_or_class_1_rule_node.add_new(ra_annotation_rule_node_supplier);
                    ro_method_or_attribute_or_class_1_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.optional;
                    ro_method_or_attribute_or_class_1_rule_node.defined_repeat_mode = Analyzer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return ro_method_or_attribute_or_class_1_rule_node;
            };
            /* "to_sign_less: sign_less" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_template_sign_less_rule_node_supplier = () -> {
                try {
                    to_template_sign_less_rule_node.clear_add_new_list();
                    to_template_sign_less_rule_node.clear_add_new_list();
                    to_template_sign_less_rule_node.add_new(sign_less.name());
                } catch (Exception e) {
                    return null;
                }
                return to_template_sign_less_rule_node;
            };
            /* "to_sign_bigger<ignore>: sign_bigger" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_template_sign_biggera_rule_node_supplier = () -> {
                try {
                    to_template_sign_biggera_rule_node.clear_add_new_list();
                    to_template_sign_biggera_rule_node.clear_add_new_list();
                    to_template_sign_biggera_rule_node.add_new(sign_bigger.name());
                    to_template_sign_biggera_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return to_template_sign_biggera_rule_node;
            };
            /* "ra_template<optional>: to_sign_less to_sign_bigger<ignore> to_sign_biggera_1" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_template_rule_node_supplier = () -> {
                try {
                    ra_template_rule_node.clear_add_new_list();
                    ra_template_rule_node.clear_add_new_list();
                    ra_template_rule_node.add_new(to_template_sign_less_rule_node_supplier);
                    ra_template_rule_node.add_new(to_template_sign_biggera_rule_node_supplier);
                    ra_template_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.optional;
                } catch (Exception e) {
                    return null;
                }
                return ra_template_rule_node;
            };
            /* "to_identifier_type: " +
                    "[void|boolean|byte|char|short|int|long|float|double]" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_identifier_type_rule_node_supplier = () -> {
                try {
                    to_identifier_type_rule_node.clear_add_new_list();
                    to_identifier_type_rule_node.add_new(type_void.name());
                    to_identifier_type_rule_node.add_new(type_boolean.name());
                    to_identifier_type_rule_node.add_new(type_byte.name());
                    to_identifier_type_rule_node.add_new(type_char.name());
                    to_identifier_type_rule_node.add_new(type_short.name());
                    to_identifier_type_rule_node.add_new(type_int.name());
                    to_identifier_type_rule_node.add_new(type_long.name());
                    to_identifier_type_rule_node.add_new(type_float.name());
                    to_identifier_type_rule_node.add_new(type_double.name());
                    to_identifier_type_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                if (identifiers_table.new_identifier.return_class.isEmpty()) {
                                    identifiers_table.new_identifier.return_class = _basic_token.token_tex;
                                } else {
                                    identifiers_table.new_identifier.return_class = identifiers_table.new_identifier.return_class
                                            + "." + _basic_token.token_tex;
                                }
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_identifier_type_rule_node;
            };
            /* "to_identifier: identifier" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_identifier_rule_node_supplier = () -> {
                try {
                    to_identifier_rule_node.clear_add_new_list();
                    to_identifier_rule_node.add_new(identifier.name());
                    to_identifier_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                identifiers_table.new_identifier.name = _basic_token.token_tex;
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_identifier_rule_node;
            };
            /* "to_dot: dot" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_doto_rule_node_supplier = () -> {
                try {
                    to_doto_rule_node.clear_add_new_list();
                    to_doto_rule_node.add_new(dot.name());
                } catch (Exception e) {
                    return null;
                }
                return to_doto_rule_node;
            };
            /* "ra_annotation_in_identifier<optional><repeat>: " +
                    "ra_annotation" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_annotation_in_identifier_rule_node_supplier = () -> {
                try {
                    ra_annotation_in_identifier_rule_node.clear_add_new_list();
                    ra_annotation_in_identifier_rule_node.add_new(ra_annotation_rule_node_supplier);
                    ra_annotation_in_identifier_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.optional;
                    ra_annotation_in_identifier_rule_node.defined_repeat_mode = Analyzer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return ra_annotation_in_identifier_rule_node;
            };
            /* "to_identifier_type_class: identifier" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_identifier_type_class_rule_node_supplier = () -> {
                try {
                    to_identifier_type_class_rule_node.clear_add_new_list();
                    to_identifier_type_class_rule_node.add_new(identifier.name());
                    to_identifier_type_class_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                if (identifiers_table.new_identifier.return_class.isEmpty()) {
                                    identifiers_table.new_identifier.return_class = _basic_token.token_tex;
                                } else {
                                    identifiers_table.new_identifier.return_class = identifiers_table.new_identifier.return_class
                                            + "." + _basic_token.token_tex;
                                }
                            }
                    );
                } catch (Exception e) {
                    return null;
                }
                return to_identifier_type_class_rule_node;
            };
            /* "ra_doto_identifier<optional><repeat>: " +
                    "to_dot ra_annotation_in_identifier<optional> to_identifier_extra" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_doto_identifier_rule_node_supplier = () -> {
                try {
                    ra_doto_identifier_rule_node.clear_add_new_list();
                    ra_doto_identifier_rule_node.add_new(to_doto_rule_node_supplier);
                    ra_doto_identifier_rule_node.add_new(ra_annotation_in_identifier_rule_node_supplier);
                    ra_doto_identifier_rule_node.add_new(to_identifier_type_class_rule_node_supplier);
                    ra_doto_identifier_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.optional;
                    ra_doto_identifier_rule_node.defined_repeat_mode = Analyzer_rules.Repeat_mode.repeat_while_success;
                } catch (Exception e) {
                    return null;
                }
                return ra_doto_identifier_rule_node;
            };
            /* "ra_identifier_class: " +
                    "to_identifier_type_class ra_doto_identifier<optional><repeat>" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_identifier_class_rule_node_supplier = () -> {
                try {
                    ra_identifier_class_rule_node.clear_add_new_list();
                    ra_identifier_class_rule_node.add_new(to_identifier_type_class_rule_node_supplier);
                    ra_identifier_class_rule_node.add_new(ra_doto_identifier_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return ra_identifier_class_rule_node;
            };
            /* "ro_identifier_type: [to_identifier_type|ra_identifier_class]" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ro_identifier_type_rule_node_supplier = () -> {
                try {
                    ro_identifier_type_rule_node.clear_add_new_list();
                    ro_identifier_type_rule_node.add_new(to_identifier_type_rule_node_supplier);
                    ro_identifier_type_rule_node.add_new(ra_identifier_class_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return ro_identifier_type_rule_node;
            };
            /* "to_parenthesis_close<ignore>: parenthesis_close" */
//            final Analizera_rules.Rule_nodes.Rule_creator_suppliers to_parenthesis_close_rule_node_supplier = () -> {
//                try {
//                    to_parenthesis_close_rule_node.clear_add_new_list();
//                    to_parenthesis_close_rule_node.add_new(parenthesis_close.name());
//                    to_parenthesis_close_rule_node.defined_optional_mode = Analizera_rules.Optional_mode.ignore_until_matches;
//                } catch (Exception e) {
//                    return null;
//                }
//                return to_parenthesis_close_rule_node;
//            };
            /* "to_parenthesis_open: parenthesis_open" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_parenthesis_open_rule_node_supplier = () -> {
                try {
                    to_parenthesis_open_rule_node.clear_add_new_list();
                    to_parenthesis_open_rule_node.add_new(parenthesis_open.name());
                } catch (Exception e) {
                    return null;
                }
                return to_parenthesis_open_rule_node;
            };
            /* "ra_parenthesis: to_parenthesis_open to_parenthesis_close<ignore>" */
//            final Analizera_rules.Rule_nodes.Rule_creator_suppliers ra_parenthesis_rule_node_supplier = () -> {
//                try {
//                    ra_parenthesis_rule_node.clear_add_new_list();
//                    ra_parenthesis_rule_node.add_new(to_parenthesis_open_rule_node_supplier);
//                    ra_parenthesis_rule_node.add_new(to_parenthesis_close_rule_node_supplier);
//                } catch (Exception e) {
//                    return null;
//                }
//                return ra_parenthesis_rule_node;
//            };
            /* "to_semicolon<ignore>: semi_colon" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers to_semicolon_rule_node_supplier = () -> {
                try {
                    to_semicolon_rule_node.clear_add_new_list();
                    to_semicolon_rule_node.add_new(semi_colon.name());
                    to_semicolon_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.ignore_until_matches;
                } catch (Exception e) {
                    return null;
                }
                return to_semicolon_rule_node;
            };
            /* "ro_parenthesis_open_or_semicolon: [to_parenthesis_open|to_semicolon<ignore>]" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ro_parenthesis_open_or_semicolon_rule_node_supplier = () -> {
                try {
                    ro_parenthesis_open_or_semicolon_rule_node.clear_add_new_list();
                    ro_parenthesis_open_or_semicolon_rule_node.add_new(to_parenthesis_open_rule_node_supplier);
                    ro_parenthesis_open_or_semicolon_rule_node.add_new(to_semicolon_rule_node_supplier);
                    ro_parenthesis_open_or_semicolon_rule_node.defined_rule_success = new Analyzer_rules.Rule_success(
                            (_basic_token, _ok, _extras_array) -> {
                                if (next_block_identifier != null) {
                                    Identifiers_tables.Temporary_identifiers_tables temporary_identifiers_table
                                            = _ok.valid(identifiers_table.get_top(_ok, _extras_array));
                                    if (_ok.is == false) return;
                                    temporary_identifiers_table.block_identifier = next_block_identifier;
                                    next_block_identifier = null;
                                }
                                if (_basic_token.token_type.equals(parenthesis_open.name())) {
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
                return ro_parenthesis_open_or_semicolon_rule_node;
            };
            /* ra_method_or_attribute_rule_node: " +
                    "to_volatile_or_final<optional><repeat> ra_template<optional> to_identifier_type to_identifier to_parenthesis_open_or_semicolon<ignore>" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_method_or_attribute_rule_node_supplier = () -> {
                try {
                    ra_method_or_attribute_rule_node.clear_add_new_list();
                    ra_method_or_attribute_rule_node.add_new(ra_template_rule_node_supplier);
                    ra_method_or_attribute_rule_node.add_new(ro_identifier_type_rule_node_supplier);
                    ra_method_or_attribute_rule_node.add_new(to_identifier_rule_node_supplier);
                    ra_method_or_attribute_rule_node.add_new(ro_parenthesis_open_or_semicolon_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return ra_method_or_attribute_rule_node;
            };
            /* "ro_method_or_attribute_or_class: " +
                    "[ra_method_or_attribute" +
                    "|ra_class]" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ro_method_or_attribute_or_class_rule_node_supplier = () -> {
                try {
                    ro_method_or_attribute_or_class_rule_node.clear_add_new_list();
                    ro_method_or_attribute_or_class_rule_node.add_new(ra_method_or_attribute_rule_node_supplier);
                    ro_method_or_attribute_or_class_rule_node.add_new(ra_class_rule_node_supplier);
                } catch (Exception e) {
                    return null;
                }
                return ro_method_or_attribute_or_class_rule_node;
            };
            /* "ra_method_or_attribute_or_class<ignore><repeat><call>: " +
                    "to_method_or_attribute_or_class<ignore> to_method_or_attribute_or_class_1" +
                    "ro_method_or_attribute_or_class" */
            final Analyzer_rules.Rule_nodes.Rule_creator_suppliers ra_method_or_attribute_or_class_rule_node_supplier = () -> {
                try {
                    ra_method_or_attribute_or_class_rule_node.clear_add_new_list();
                    ra_method_or_attribute_or_class_rule_node.add_new(to_method_or_attribute_or_class_rule_node_supplier);
                    ra_method_or_attribute_or_class_rule_node.add_new(ro_method_or_attribute_or_class_1_rule_node_supplier);
                    ra_method_or_attribute_or_class_rule_node.add_new(ro_method_or_attribute_or_class_rule_node_supplier);
                    ra_method_or_attribute_or_class_rule_node.defined_optional_mode = Analyzer_rules.Optional_mode.ignore_until_matches;
                    ra_method_or_attribute_or_class_rule_node.defined_repeat_mode = Analyzer_rules.Repeat_mode.repeat_while_success;
                    ra_method_or_attribute_or_class_rule_node.defined_call_the_success_rules_list_if_success = true;
                } catch (Exception e) {
                    return null;
                }
                return ra_method_or_attribute_or_class_rule_node;
            };
            retorno = ra_method_or_attribute_or_class_rule_node_supplier.get();
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }

}
