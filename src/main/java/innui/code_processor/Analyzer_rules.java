package innui.code_processor;

import com.fasterxml.jackson.core.type.TypeReference;
import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.io.Serializable;
import java.util.*;


@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public class Analyzer_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Analyzer_rules.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Analyzer_rules.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }

    public enum Return_status {
        matched
        , not_matched
        , need_next_token
        , error
    }

    public enum Optional_mode {
        no_optional
        , optional
        , ignore_until_matches
    }
    public static @Fenum("optional_mode") String k_optional_tex = (@Fenum("optional_mode") String) "<optional>";
    public static @Fenum("optional_mode") String k_ignore_tex = (@Fenum("optional_mode") String) "<ignore>";

    public enum Repeat_mode {
        no_repeat
        , repeat_while_success
    }
    public static @Fenum("repeat_mode") String k_repeat_tex = (@Fenum("repeat_mode") String) "<repeat>";
    public static @Fenum("call_mode") String k_call_tex = (@Fenum("call_mode") String) "<call>";

    public static class Rule_success implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public Scanner_rules.@Nullable Basic_tokens basic_token = null;
        public Integer first_token_pos = -1;
        public Identifiers_table_after_successes.After_success_calls after_success;

        public Rule_success(Identifiers_table_after_successes.After_success_calls after_success) {
            this.after_success = after_success;
        }
    }

    public static class Name_based_rule_nodes implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public String _defined_name = "";
        public List<String> name_based_rule_nodes_list = new ArrayList<>();

    }
    public static abstract class Rule_nodes implements Serializable {
        @FunctionalInterface
        public interface Rule_creator_calls extends Serializable {
            @Nullable Rule_nodes call(Oks ok, Object ... extras_array) throws Exception;
        }
        @FunctionalInterface
        public interface Rule_creator_suppliers extends Serializable {
            @Nullable Rule_nodes get() throws Exception;
        }
        private static final long serialVersionUID = getSerial_version_uid();
        public static @Fenum("key_name_separator") String k_key_name_token_separator = (@Fenum("key_name_separator") String) "-'";
        public static @Fenum("key_name_separator") String k_key_name_and_separator = (@Fenum("key_name_separator") String) " -> ";
        public static @Fenum("key_name_separator") String k_key_name_or_separator = (@Fenum("key_name_separator") String) " | ";
        public static @Fenum("key_name_separator") String k_key_name_or_begin_separator = (@Fenum("key_name_separator") String) "[ ";
        public static @Fenum("key_name_separator") String k_key_name_or_end_separator = (@Fenum("key_name_separator") String) " ]";
        public String _defined_key_name = "";
        public String _defined_name = "";
        public Optional_mode defined_optional_mode = Optional_mode.no_optional;
        public Repeat_mode defined_repeat_mode = Repeat_mode.no_repeat;
        public boolean defined_call_the_success_rules_list_if_success = false;
        public Analyzer_rules _defined_analizer_rules;
        public @Nullable Rule_success defined_rule_success = null;
        public boolean _is_already_evaluated = false;
        public @Nullable Integer _first_token_pos = null;
        public @Nullable Boolean _is_once_successed = null;

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings({"nullness:method.invocation", "nullness:initialization.fields.uninitialized"})
        public Rule_nodes(Analyzer_rules analizer_rules) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(analizer_rules, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings({"nullness:method.invocation", "nullness:initialization.fields.uninitialized"})
        public Rule_nodes(Rule_nodes rule_node) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(rule_node, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        /**
         *
         * @param analizer_rules
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public void init(Analyzer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                _defined_analizer_rules = analizer_rules;
                _defined_key_name = "";
                _defined_name = "";
                defined_optional_mode = Optional_mode.no_optional;
                defined_repeat_mode = Repeat_mode.no_repeat;
                defined_call_the_success_rules_list_if_success = false;
                defined_rule_success = null;
                init_to_reuse_or_repeat(true, ok, extras_array);
            } catch (Exception e) {
                ok.setTex(e);
            }
        }
        /**
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init_to_reuse_or_repeat(boolean is_reuse, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                if (is_reuse) {
                    if (defined_call_the_success_rules_list_if_success == true) {
                        _defined_analizer_rules.process_clean(ok.valid(_first_token_pos), ok, extras_array);
                        if (ok.is == false) return;
                    }
                    _is_once_successed = null;
                }
                _first_token_pos = null;
                _is_already_evaluated = false;
                if (defined_rule_success != null) {
                    defined_rule_success.first_token_pos = -1;
                    defined_rule_success.basic_token = null;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         * @param basic_rule_node
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init(Rule_nodes basic_rule_node, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                _defined_key_name = basic_rule_node._defined_key_name;
                _defined_name = basic_rule_node._defined_name;
                _defined_analizer_rules = basic_rule_node._defined_analizer_rules;
                defined_optional_mode = basic_rule_node.defined_optional_mode;
                defined_repeat_mode = basic_rule_node.defined_repeat_mode;
                defined_call_the_success_rules_list_if_success = basic_rule_node.defined_call_the_success_rules_list_if_success;
                _is_already_evaluated = basic_rule_node._is_already_evaluated;
                _first_token_pos = basic_rule_node._first_token_pos;
                _is_once_successed = basic_rule_node._is_once_successed;
                if (basic_rule_node.defined_rule_success == null) {
                    defined_rule_success = null;
                } else {
                    defined_rule_success = new Rule_success(basic_rule_node.defined_rule_success.after_success);
                    defined_rule_success.first_token_pos = ok.valid(basic_rule_node
                            .defined_rule_success).first_token_pos;
                    ok.valid(defined_rule_success).basic_token = ok.valid(basic_rule_node
                            .defined_rule_success).basic_token;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        /**
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return true = success, false = fail, null = not totally evaluated or error
         * @throws Exception
         */
        public @Nullable Boolean evaluate(Scanner_rules.Basic_tokens basic_token, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            Boolean retorno = null;
            Return_status result;
            Integer pos = null;
            if (_is_already_evaluated) {
                if (_is_once_successed == null) {
                    ok.setTex(Tr.in(k_in_route, "Rule evaluated without success information. "));
                    return null;
                } else if (_is_once_successed) {
                    pos = _defined_analizer_rules.i_code_scanner.get_tokens_list_pos(ok, extras_array);
                    if (ok.is == false) return null;
                    // pos = pos - 1;
                    _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                    if (ok.is == false) return null;
                    return true;
                } else {
                    pos = _defined_analizer_rules.i_code_scanner.get_tokens_list_pos(ok, extras_array);
                    if (ok.is == false) return null;
                    // pos = pos - 1;
                    _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                    if (ok.is == false) return null;
                    return false;
                }
            }
            try {
                do {
                    if (_first_token_pos == null) {
                        _first_token_pos = _defined_analizer_rules.i_code_scanner.get_tokens_list_pos(ok, extras_array);
                        if (ok.is == false) return null;
                    }
                    retorno = null;
                    result = _evaluate(basic_token, ok, extras_array);
                    if (ok.is == false) return null;
                    _check_processing(result, ok, extras_array);
                    if (ok.is == false) return null;
                    result = change_status(result, ok, extras_array);
                    if (ok.is == false) return null;
                    if (result == Return_status.need_next_token) {
                        basic_token = ok.allow_null(_defined_analizer_rules.i_code_scanner.scan_next(ok, extras_array));
                        if (ok.is == false) return null;
                    }
                } while (result == Return_status.need_next_token);
                if (result == Return_status.error) {
                    retorno = null;
                } else if (result.equals(Return_status.matched)) {
                    retorno = true;
                } else {
                    retorno = false;
                }
            } catch (Exception e) {
                ok.setTex(e);
                return null;
            }
            return retorno;
        }

        /**
         *
         * @param return_status
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public void _check_processing(Return_status return_status, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            Integer pos;
            try {
                if (return_status.equals(Return_status.matched)) {
                    if (defined_call_the_success_rules_list_if_success == true) {
                        _defined_analizer_rules.process(ok.valid(_first_token_pos), ok, extras_array);
                        if (ok.is == false) return;
                    }
                } else {
                    if (defined_call_the_success_rules_list_if_success == true) {
                        _defined_analizer_rules.process_clean(ok.valid(_first_token_pos), ok, extras_array);
                        if (ok.is == false) return;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         * @param return_status
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public Return_status change_status(Return_status return_status, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return Return_status.error;
            Integer pos;
            try {
                if (return_status == Return_status.error) {
                    return Return_status.error;
                } else if (return_status == Return_status.not_matched) {
                    if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                        return Return_status.need_next_token;
                    }
                    if (defined_repeat_mode == Repeat_mode.repeat_while_success) {
                        if (_is_once_successed != null && _is_once_successed == true) {
                            _is_already_evaluated = true;
                            _is_once_successed = true;
                            pos = ok.valid(_first_token_pos);
                            _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                            if (ok.is == false) return Return_status.error;
                            return Return_status.matched;
                        }
                    }
                    if (defined_optional_mode == Optional_mode.optional) {
                        _is_already_evaluated = true;
                        _is_once_successed = true; // matched option: empty
                        pos = ok.valid(_first_token_pos);
                        _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                        if (ok.is == false) return Return_status.error;
                        return Return_status.matched;
                    }
                    _is_already_evaluated = true;
                    _is_once_successed = false;
                    pos = ok.valid(_first_token_pos);
                    _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                    if (ok.is == false) return Return_status.error;
                    return Return_status.not_matched;
                } else if (return_status == Return_status.matched) {
                    if (defined_rule_success != null) {
                        Rule_success rule_success = new Rule_success(ok.valid(defined_rule_success).after_success);
                        rule_success.first_token_pos = ok.valid(_first_token_pos);
                        rule_success.basic_token = ok.valid(defined_rule_success).basic_token;
                        _defined_analizer_rules.success_rules_list.add(rule_success);
                    }
                    _is_already_evaluated = true;
                    _is_once_successed = true;
                    if (defined_repeat_mode == Repeat_mode.repeat_while_success) {
                        init_to_reuse_or_repeat(false, ok, extras_array);
                        if (ok.is == false) return Return_status.error;
                        return Return_status.need_next_token;
                    }
                    return Return_status.matched;
                }
            } catch (Exception e) {
                ok.setTex(e);
                return Return_status.error;
            }
            return return_status;
        }

        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public abstract Return_status _evaluate(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception;

        public @Nullable Rule_nodes find_by_key_name(String key_name) throws Exception {
            if (_defined_analizer_rules.rule_nodes_map.containsKey(key_name)) {
                return _defined_analizer_rules.rule_nodes_map.get(key_name);
            } else {
                return null;
            }
        }

        /**
         * Add to map if was not added
         * @param rule_node
         * @throws Exception
         */
        public void _add_to_map(Rule_nodes rule_node) throws Exception {
            _defined_analizer_rules.rule_nodes_map.put(rule_node._defined_key_name, rule_node);
        }
        /**
         *
         * @param key_name
         * @throws Exception
         */
        public void add_by_key_name(String key_name) throws Exception {
            Rule_nodes rule_node = Oks.allow_nulle(find_by_key_name(key_name));
            if (rule_node != null) {
                _add_new(rule_node);
            }
        }

        /**
         * 
         * @return
         */
        public String get_key_name() {
            return _defined_key_name;
        }

        /**
         * 
         * @return
         */
        public String get_name() {
            return _defined_name;
        }

        /**
         * 
         * @param basic_token
         * @return
         */
        public String create_key_name(Scanner_rules.Basic_tokens basic_token) {
            return create_key_name(basic_token.token_type, basic_token.token_tex);
        }

        /**
         * 
         * @param token_type
         * @param token_tex
         * @return
         */
        public String create_key_name(String token_type, String token_tex) {
            String key_name;
            key_name = token_type + Oks.no_fenum_cast(k_key_name_token_separator) + token_tex + "'";
            if (defined_optional_mode.equals(Optional_mode.optional)) {
                key_name = key_name + k_optional_tex;
            } else if (defined_optional_mode.equals(Optional_mode.ignore_until_matches)) {
                key_name = key_name + k_ignore_tex;
            }
            if (defined_repeat_mode.equals(Repeat_mode.repeat_while_success)) {
                key_name = key_name + k_repeat_tex;
            }
            if (defined_call_the_success_rules_list_if_success == true) {
                key_name = key_name + k_call_tex;
            }
            return Oks.no_fenum_cast(key_name);
        }

        /**
         * 
         * @param token_type
         * @return
         */
        public String create_key_name(String token_type) {
            return create_key_name(token_type, "");
        }

        /**
         * Normalizes the key name existing and returns the new one
         * @return the new one
         */
        public String normalize_key_name() {
            String new_key_name;
            int pos = _defined_key_name.indexOf('<');
            if (pos >= 0) {
                new_key_name = create_key_name("", "");
                int new_pos = new_key_name.indexOf('<');
                if (new_pos >= 0) {
                    new_key_name = new_key_name.substring(new_pos);
                } else {
                    new_key_name = "";
                }
                new_key_name = _defined_key_name.substring(0, pos).trim()
                    + new_key_name;
            } else {
                new_key_name = _defined_key_name.trim();
            }
            _defined_key_name = new_key_name;
            return new_key_name;
        }

        /**
         * Normalizes the name (long name) of the rule.
         * @return
         */
        public abstract String normalize_name();

        /**
         * Set name, key_name and other attributes of the object
         * @param rule_name_with_attributes Format: "key_name [&lt;optional&gt; | &lt;ignore&gt;] [&lt;repeat&gt;]: rest of the name (i.e: my_rule&lt;optional&gt;: call_other_rule&lt;repeat&gt;)"
         */
        public void configure_from_key_name(String rule_name_with_attributes) {
            try {
                String old_defined_key_name = _defined_key_name;
                this._defined_name = rule_name_with_attributes.trim();
                int pos = rule_name_with_attributes.indexOf(":");
                if (pos >= 0) {
                    _defined_key_name = rule_name_with_attributes.substring(0, pos).trim();
                } else {
                    _defined_key_name = rule_name_with_attributes.trim();
                }
                pos = _defined_key_name.indexOf("<");
                if (pos >= 0) {
                    String new_name = _defined_key_name.substring(0, pos);
                    if (_defined_key_name.contains(Oks.no_fenum_cast(k_optional_tex))) {
                        if (_defined_key_name.contains(Oks.no_fenum_cast(k_ignore_tex))) {
                            throw new Exception(Tr.in(k_in_route, "<optional> and <ignore> cannot go together. ")
                                    + rule_name_with_attributes);
                        } else {
                            defined_optional_mode = Optional_mode.optional;
                            new_name = new_name + k_optional_tex;
                        }
                    } else if (_defined_key_name.contains(Oks.no_fenum_cast(k_ignore_tex))) {
                        defined_optional_mode = Optional_mode.ignore_until_matches;
                        new_name = new_name + k_ignore_tex;
                    }
                    if (_defined_key_name.contains(Oks.no_fenum_cast(k_repeat_tex))) {
                        defined_repeat_mode = Repeat_mode.repeat_while_success;
                        new_name = new_name + k_repeat_tex;
                    }
                    if (_defined_key_name.contains(Oks.no_fenum_cast(k_call_tex))) {
                        defined_call_the_success_rules_list_if_success = true;
                        new_name = new_name + k_call_tex;
                    }
                    _defined_key_name = Oks.no_fenum_cast(new_name);
                }
                normalize_key_name();
                Rule_nodes rule_node = find_by_key_name(old_defined_key_name);
                if (rule_node != null) {
                    _defined_analizer_rules.rule_nodes_map.remove(_defined_key_name);
                    _add_to_map(this);
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }

        /**
         *
         * @param key_names_list
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public void add_new(List<String> key_names_list, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            try {
                Rule_nodes rule_node;
                for (var item: key_names_list) {
                    if (item.contains(Oks.no_fenum_cast(k_key_name_token_separator)) == false) {
                        rule_node = ok.allow_null(find_by_key_name(item));
                        if (rule_node == null) {
                            ok.setTex(Tr.in(k_in_route, "Rule not stored yet in the repository: ")
                                    + item);
                            break;
                        } else {
                            add_new(rule_node);
                        }
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
        }
        /**
         *
         * @param rule_node_supplier
         * @throws Exception
         */
        public void add_new(Rule_creator_suppliers rule_node_supplier) throws Exception {
             add_new(Oks.valide(rule_node_supplier.get()));
        }

        /**
         *
         * @param rule_creator_call
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public void add_new(Rule_creator_calls rule_creator_call, Oks ok, Object ... extras_array) throws Exception {
            Rule_nodes rule_node = ok.allow_null(rule_creator_call.call(ok, extras_array));
            if (ok.is == false) return;
            add_new(rule_node);
        }

        /**
         *
         * @param extras_arrayRule_creator_call
         * @param rule_name_with_attributes Format: "key_name [&lt;optional&gt; | &lt;ignore&gt;] [&lt;repeat&gt;]: rest of the name (i.e: my_rule&lt;optional&gt;: call_other_rule&lt;repeat&gt;)"
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public void add_new(Rule_creator_calls extras_arrayRule_creator_call, String rule_name_with_attributes
                , Oks ok, Object ... extras_array) throws Exception {
            Rule_nodes rule_node = ok.allow_null(extras_arrayRule_creator_call.call(ok, extras_array));
            if (ok.is == false) return;
            add_new(rule_node);
        }

        /**
         *
         * @param rule_node
         * @throws Exception
         */
        public void add_new(Rule_nodes rule_node) throws Exception {
            add_new(rule_node, null);
        }

        /**
         *
         * @param rule_node
         * @param rule_name_with_attributes Format: "key_name [&lt;optional&gt; | &lt;ignore&gt;] [&lt;repeat&gt;]: rest of the name (i.e: my_rule&lt;optional&gt;: call_other_rule&lt;repeat&gt;)"
         * @throws Exception
         */
        public void add_new(Rule_nodes rule_node, @Nullable String rule_name_with_attributes) throws Exception {
            Rule_nodes new_rule_nodes = _add_new(rule_node);
            if (rule_name_with_attributes != null) {
                new_rule_nodes.configure_from_key_name(rule_name_with_attributes);
            }
            _add_to_map(new_rule_nodes);
        }

        /**
         *
         * @param basic_token
         * @throws Exception
         */
        public void add_new(Scanner_rules.Basic_tokens basic_token) throws Exception {
            add_new(basic_token, null);
        }

        /**
         *
         * @param basic_token
         * @param rule_name_with_attributes Format: "key_name [&lt;optional&gt; | &lt;ignore&gt;] [&lt;repeat&gt;]: rest of the name (i.e: my_rule&lt;optional&gt;: call_other_rule&lt;repeat&gt;)"
         * @throws Exception
         */
        public void add_new(Scanner_rules.Basic_tokens basic_token, @Nullable String rule_name_with_attributes) throws Exception {
            Rule_nodes new_rule_nodes = _add_new(basic_token);
            if (rule_name_with_attributes != null) {
                new_rule_nodes.configure_from_key_name(rule_name_with_attributes);
            }
            _add_to_map(new_rule_nodes);
        }

        /**
         *
         * @param token_type_tex
         * @throws Exception
         */
        public void add_new(String token_type_tex) throws Exception {
            add_new(new Scanner_rules.Basic_tokens(token_type_tex));
        }

        /**
         *
         * @param token_type_tex
         * @param rule_name_with_attributes Format: "key_name [&lt;optional&gt; | &lt;ignore&gt;] [&lt;repeat&gt;]: rest of the name (i.e: my_rule&lt;optional&gt;: call_other_rule&lt;repeat&gt;)"
         * @throws Exception
         */
        public void add_new(String token_type_tex, @Nullable String rule_name_with_attributes) throws Exception {
            add_new(new Scanner_rules.Basic_tokens(token_type_tex), rule_name_with_attributes);
        }

        /**
         *
         * @param token_type_tex
         * @param token_tex
         * @param rule_name_with_attributes Format: "key_name [&lt;optional&gt; | &lt;ignore&gt;] [&lt;repeat&gt;]: rest of the name (i.e: my_rule&lt;optional&gt;: call_other_rule&lt;repeat&gt;)"
         * @throws Exception
         */
        public void add_new(String token_type_tex, String token_tex, @Nullable String rule_name_with_attributes) throws Exception {
            add_new(new Scanner_rules.Basic_tokens(token_type_tex, token_tex), rule_name_with_attributes);
        }

        /**
         *
         * @param basic_tokens_list
         * @throws Exception
         */
        public void add_new(List<Scanner_rules.Basic_tokens> basic_tokens_list) throws Exception {
            for (var item: basic_tokens_list) {
                add_new(item);
            }
        }

        /**
         *
         * @param token_type_tex_array
         */
        public void add_new(String[] token_type_tex_array) throws Exception {
            for (var item: token_type_tex_array) {
                add_new(item);
            }
        }

        /**
         *
         * @return true if it has success
         * @throws Exception
         */
        public abstract boolean clear_add_new_list() throws Exception;

        /**
         *
         * @param token_type_tex
         * @throws Exception
         */
        public Rule_nodes _add_new(String token_type_tex) throws Exception {
            return _add_new(new Scanner_rules.Basic_tokens(token_type_tex));
        }

        /**
         *
         * @param token_type_tex
         * @param token_tex
         * @return
         * @throws Exception
         */
        public Rule_nodes _add_new(String token_type_tex, String token_tex) throws Exception {
            return _add_new(new Scanner_rules.Basic_tokens(token_type_tex, token_tex));
        }

        /**
         *
         * @param rule_node
         * @throws Exception
         */
        public abstract Rule_nodes _add_new(Rule_nodes rule_node) throws Exception;

        /**
         *
         * @param basic_token
         */
        public abstract Rule_nodes _add_new(Scanner_rules.Basic_tokens basic_token) throws Exception;

        public abstract List<String> get_add_new_key_names_list(Oks ok, Object ... extras_array) throws Exception;

    }

    public static class Rules_and_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Rule_nodes> _defined_rule_nodes_and_list = new ArrayList<>();
        public Integer _rule_part_num = 0;
        public Integer _defined_rule_part_tam = -1;

        public Rules_and_rule_nodes(Analyzer_rules analizer_rules) throws Exception {
            super(analizer_rules);
        }

        /**
         *
         * @param analizer_rules
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public void init(Analyzer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init(analizer_rules, ok, extras_array);
                if (ok.is == false) return;
                _defined_rule_nodes_and_list = new ArrayList<>();
                _defined_rule_part_tam = -1;
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings({"nullness:method.invocation", "nullness:initialization.fields.uninitialized"})
        public Rules_and_rule_nodes(Rules_and_rule_nodes rules_and_rule_node) throws Exception {
            super(Oks.valide(rules_and_rule_node._defined_analizer_rules));
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(rules_and_rule_node, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @Override
        public void init_to_reuse_or_repeat(boolean is_reuse, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            try {
                super.init_to_reuse_or_repeat(is_reuse, ok, extras_array);
                if (ok.is == false) return;
                if (_defined_rule_nodes_and_list != null) {
                    for (var node : _defined_rule_nodes_and_list) {
                        node.init_to_reuse_or_repeat(true, ok, extras_array);
                        if (ok.is == false) return;
                    }
                }
                _rule_part_num = 0;
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        /**
         *
         * @param rules_and_rule_node
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init(Rules_and_rule_nodes rules_and_rule_node, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init(rules_and_rule_node, ok, extras_array);
                if (ok.is == false) return;
                _rule_part_num = rules_and_rule_node._rule_part_num;
                _defined_rule_part_tam = rules_and_rule_node._defined_rule_part_tam;
                _defined_rule_nodes_and_list.clear();
                _defined_rule_nodes_and_list.addAll(rules_and_rule_node._defined_rule_nodes_and_list);
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        @Override
        public Return_status _evaluate(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return Return_status.error;
            ResourceBundle in = null;
            try {
                in = ok.valid(ResourceBundles.getBundle(k_in_route));
                if (_defined_rule_part_tam == -1) {
                    if (_defined_rule_nodes_and_list.size() == 0) {
                        ok.setTex(Tr.in(in, "No rules defined. "));
                        return Return_status.error;
                    }
                    _defined_rule_part_tam = _defined_rule_nodes_and_list.size();
                }
                Boolean result;
                if (_rule_part_num >= _defined_rule_nodes_and_list.size()) {
                    ok.setTex(Tr.in(k_in_route, "Reached the end of the nodes-and list"));
                    return Return_status.error;
                }
                Rule_nodes rule_node = _defined_rule_nodes_and_list.get(_rule_part_num);
                result = rule_node.evaluate(basic_token, ok, extras_array);
                if (ok.is == false) return Return_status.error;
                if (result != null) {
                    if (result == true) {
                        if (defined_rule_success != null) {
                            if (_rule_part_num == 0) {
                                defined_rule_success.first_token_pos = ok.valid(_first_token_pos);
                                ok.valid(defined_rule_success).basic_token = basic_token;
                            }
                        }
                        return Return_status.matched;
                    } else {
                        return Return_status.not_matched;
                    }
                } else {
                    return Return_status.need_next_token;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return Return_status.error;
        }

        @Override
        public String normalize_key_name() {
            super.normalize_key_name();
            if (_defined_key_name.startsWith("ra_") == false) {
                _defined_key_name = "ra_" + _defined_key_name;
            }
            return _defined_key_name;
        }

        @Override
        public String normalize_name() {
            String new_name = normalize_key_name();
            String long_name = "";
            for (var item: _defined_rule_nodes_and_list) {
                long_name = long_name
                        + Oks.no_fenum_cast(k_key_name_and_separator)
                        + item.normalize_key_name();
            }
            _defined_name = new_name + ":" + long_name;
            return _defined_name;
        }

        @Override
        public boolean clear_add_new_list() throws Exception {
            _defined_rule_nodes_and_list.clear();
            return true;
        }

        /**
         *
         * @param return_status
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        @Override
        public void _check_processing(Return_status return_status, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            Integer pos;
            try {
                Integer part_num = _rule_part_num;
                if (part_num < _defined_rule_part_tam) {
                    part_num = part_num + 1;
                }
                if (part_num >= _defined_rule_part_tam) {
                    // End of rule_and
                    super._check_processing(return_status, ok, extras_array);
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        @Override
        public Return_status change_status(Return_status return_status, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return Return_status.error;
            Integer pos;
            try {
                if (return_status == Return_status.error) {
                    return return_status;
                } else if (return_status == Return_status.not_matched) {
                    if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                        pos = ok.valid(_first_token_pos) + 1;
                        _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                        init_to_reuse_or_repeat(true, ok, extras_array);
                        if (ok.is == false) return Return_status.error;
                        return Return_status.need_next_token;
                    }
                    if (defined_optional_mode  == Optional_mode.optional) {
                        _is_already_evaluated = true;
                        _is_once_successed = true; // empty match
                        pos = ok.valid(_first_token_pos);
                        _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                        if (ok.is == false) return Return_status.error;
                        return Return_status.matched;
                    }
                    if (defined_repeat_mode == Repeat_mode.repeat_while_success) {
                        if (_is_once_successed == null || _is_once_successed == false) {
                            _is_already_evaluated = true;
                            _is_once_successed = false;
                            pos = ok.valid(_first_token_pos);
                            _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                            if (ok.is == false) return Return_status.error;
                            return Return_status.not_matched;
                        } else {
                            if (_rule_part_num < _defined_rule_part_tam) {
                                _rule_part_num = _rule_part_num + 1;
                                if (_rule_part_num < _defined_rule_part_tam) {
                                    _is_already_evaluated = true;
                                    _is_once_successed = false;
                                    pos = ok.valid(_first_token_pos);
                                    _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                                    if (ok.is == false) return Return_status.error;
                                    return Return_status.not_matched;
                                } else { // ending
                                    _is_already_evaluated = true;
                                    _is_once_successed = true;
                                    pos = _defined_analizer_rules.i_code_scanner.get_tokens_list_pos(ok, extras_array);
                                    if (ok.is == false) return Return_status.error;
                                    // pos = pos - 1;
                                    _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                                    if (ok.is == false) return Return_status.error;
                                    return Return_status.matched;
                                }
                            } else { // ending
                                ok.setTex(Tr.in(k_in_route, "End of token list exceeded. " ));
                                return Return_status.error;
                            }
                        }
                    }
                    _is_already_evaluated = true;
                    _is_once_successed = false;
                    pos = ok.valid(_first_token_pos) ;
                    _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                    if (ok.is == false) return Return_status.error;
                    return Return_status.not_matched;
                } else if (return_status == Return_status.matched) {
                    if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                        if (_rule_part_num == 0) {
                            _first_token_pos = ok.valid(_defined_rule_nodes_and_list.get(0)._first_token_pos);
                        }
                    }
                    if (_rule_part_num < _defined_rule_part_tam) {
                        _rule_part_num = _rule_part_num + 1;
                        if (_rule_part_num < _defined_rule_part_tam) {
                            return Return_status.need_next_token;
                        } else {
                            if (defined_rule_success != null) {
                                Rule_success rule_success = new Rule_success(ok.valid(defined_rule_success).after_success);
                                rule_success.first_token_pos = ok.valid(_first_token_pos);
                                rule_success.basic_token = ok.valid(defined_rule_success).basic_token;
                                _defined_analizer_rules.success_rules_list.add(rule_success);
                            }
                            _is_already_evaluated = true;
                            _is_once_successed = true;
                            if (defined_repeat_mode == Repeat_mode.repeat_while_success) {
                                init_to_reuse_or_repeat(false, ok, extras_array);
                                if (ok.is == false) return Return_status.error;
                                return Return_status.need_next_token;
                            }
                            return Return_status.matched;
                        }
                    } else {
                        ok.setTex(Tr.in(k_in_route, "End of token list exceeded. " ));
                        return Return_status.error;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
                return Return_status.error;
            }
            return return_status;
        }

        @Override
        public Rule_nodes _add_new(Rule_nodes rule_node) throws Exception {
            Rule_nodes new_rule_node;
            new_rule_node = Oks.no_fenum_cast(rule_node.getClass().getConstructor(rule_node.getClass()).newInstance(rule_node));
            _defined_rule_nodes_and_list.add(new_rule_node);
            return rule_node;
        }

        @Override
        public Rule_nodes _add_new(Scanner_rules.Basic_tokens basic_token) throws Exception {
            Rule_nodes rule_node = Oks.allow_nulle(find_by_key_name(create_key_name(basic_token)));
            Tokens_or_rule_nodes tokens_or_rule_node;
            if (rule_node == null) {
                // Create Tokens_or_rule_nodes for only one token
                tokens_or_rule_node = new Tokens_or_rule_nodes(_defined_analizer_rules);
                tokens_or_rule_node.configure_from_key_name(create_key_name(basic_token));
                tokens_or_rule_node.add_new(basic_token);
                _defined_rule_nodes_and_list.add(tokens_or_rule_node);
            } else {
                if (rule_node instanceof Tokens_or_rule_nodes) {
                    tokens_or_rule_node = new Tokens_or_rule_nodes((Tokens_or_rule_nodes) rule_node);
                    if (tokens_or_rule_node._defined_tokens_or_list.contains(basic_token) == false) {
                        throw new Exception(Tr.in(k_in_route, "Rule node found does not contain token: ")
                            + create_key_name(basic_token));
                    }
                    _defined_rule_nodes_and_list.add(tokens_or_rule_node);
                } else {
                    throw new Exception(Tr.in(k_in_route, "Rule node found is instance of another class. "));
                }
            }
            return tokens_or_rule_node;
        }

        @Override
        public List<String> get_add_new_key_names_list(Oks ok, Object... extras_array) throws Exception {
            List<String> retorno_list = new ArrayList<>();
            if (ok.is == false) return retorno_list;
            Oks noted_ok = ok.valid(ok.create_new(extras_array));
            for (var item: _defined_rule_nodes_and_list) {
                if (_defined_analizer_rules.rule_nodes_map.containsKey(item.get_key_name()) == false) {
                    ok.addTex(Tr.in(k_in_route, "Rule not found: ") + item.get_key_name());
                    noted_ok.init();
                }
                retorno_list.add(item.get_key_name());
            }
            if (ok.is == false) {
                retorno_list.clear();
            }
            return retorno_list;
        }

    }

    public static class Tokens_or_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Scanner_rules.Basic_tokens> _defined_tokens_or_list = new ArrayList<>();

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Tokens_or_rule_nodes(Analyzer_rules analizer_rules) throws Exception {
            super(analizer_rules);
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init_to_reuse_or_repeat(true, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings({"nullness:method.invocation", "nullness:initialization.fields.uninitialized"})
        public Tokens_or_rule_nodes(Tokens_or_rule_nodes tokens_or_rule_node) throws Exception {
            super(Oks.valide(tokens_or_rule_node._defined_analizer_rules));
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(tokens_or_rule_node, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @Override
        public void init(Analyzer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init(analizer_rules, ok, extras_array);
                if (ok.is == false) return;
                _defined_tokens_or_list = new ArrayList<>();
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         *
         * @param tokens_or_rule_node
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init(Tokens_or_rule_nodes tokens_or_rule_node, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init(tokens_or_rule_node, ok, extras_array);
                if (ok.is == false) return;
                _defined_tokens_or_list.clear();
                _defined_tokens_or_list.addAll(tokens_or_rule_node._defined_tokens_or_list);
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        @Override
        public Return_status _evaluate(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return Return_status.error;
            Return_status retorno = Return_status.not_matched;
            Integer pos;
            try {
                if (_defined_tokens_or_list.isEmpty()) {
                    ok.setTex(Tr.in(k_in_route, "No tokens defined. "));
                    return Return_status.error;
                }
                for (var to_match_basic_token: _defined_tokens_or_list) {
                    retorno = _evaluate_a_basic_token(basic_token, to_match_basic_token, ok, extras_array);
                    if (ok.is == false) return Return_status.error;
                    if (retorno != Return_status.not_matched) {
                        break;
                    }
                }
                if (retorno == Return_status.matched) {
                    if (defined_rule_success != null) {
                        defined_rule_success.first_token_pos = ok.valid(_first_token_pos);
                        ok.valid(defined_rule_success).basic_token = basic_token;
                    }
                    if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                        pos = _defined_analizer_rules.i_code_scanner.get_tokens_list_pos(ok, extras_array);
                        _first_token_pos = pos;
                        if (ok.is == false) return Return_status.error;
                        // pos = pos - 1;
                        _defined_analizer_rules.i_code_scanner.set_tokens_list_pos(pos, ok, extras_array);
                        if (ok.is == false) return Return_status.error;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
                return Return_status.error;
            }
            return retorno;
        }

        @Override
        public String normalize_key_name() {
            super.normalize_key_name();
            if (_defined_key_name.startsWith("to_") == false) {
                _defined_key_name =  "to_" + _defined_key_name;
            }
            return _defined_key_name;
        }

        @Override
        public String normalize_name() {
            String new_name = normalize_key_name();
            String long_name = "";
            for (var item: _defined_tokens_or_list) {
                if (long_name.isEmpty()) {
                    if (_defined_tokens_or_list.size() > 1) {
                        long_name = Oks.no_fenum_cast(k_key_name_or_begin_separator);
                    }
                } else {
                    long_name = long_name + Oks.no_fenum_cast(k_key_name_or_separator);
                }
                long_name = long_name + create_key_name(item);
            }
            if (_defined_tokens_or_list.size() > 1) {
                long_name = long_name + Oks.no_fenum_cast(k_key_name_or_end_separator);
            }
            _defined_name = new_name + ": " + long_name;
            return _defined_name;
        }

        /**
         *
         * @param basic_token
         * @param to_match_basic_token
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public Return_status _evaluate_a_basic_token(Scanner_rules.Basic_tokens basic_token
                , Scanner_rules.Basic_tokens to_match_basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return Return_status.error;
            ResourceBundle in = null;
            try {
                if (Objects.equals(basic_token.token_type, to_match_basic_token.token_type) == false) {
                    return Return_status.not_matched;
                }
                if (to_match_basic_token.token_tex != null && to_match_basic_token.token_tex.isEmpty() == false) {
                    return Return_status.not_matched;
                } else {
                    return Return_status.matched;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return Return_status.error;
        }

        @Override
        public Rule_nodes _add_new(Rule_nodes rule_node) throws Exception {
            throw new Exception(Tr.in(k_in_route, "This rule only accepts tokens. "));
        }

        @Override
        public void add_new(Scanner_rules.Basic_tokens basic_token, @Nullable String rule_name_with_attributes) throws Exception {
            _add_new(basic_token);
        }

        @Override
        public boolean clear_add_new_list() throws Exception {
            _defined_tokens_or_list.clear();
            return true;
        }

        @Override
        public Rule_nodes _add_new(Scanner_rules.Basic_tokens basic_token) throws Exception {
            // Does not create a new Rule_node.
            _defined_tokens_or_list.add(basic_token);
            return this;
        }

        @Override
        public List<String> get_add_new_key_names_list(Oks ok, Object... extras_array) throws Exception {
            List<String> retorno_list = new ArrayList<>();
            if (ok.is == false) return retorno_list;
            for (var item: _defined_tokens_or_list) {
                retorno_list.add(create_key_name(item));
            }
            return retorno_list;
        }

    }

    public static class Rules_or_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Rule_nodes> _defined_rule_nodes_or_list = new ArrayList<>();

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Rules_or_rule_nodes(Analyzer_rules analizer_rules) throws Exception {
            super(analizer_rules);
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings({"nullness:method.invocation", "nullness:initialization.fields.uninitialized"})
        public Rules_or_rule_nodes(Rules_or_rule_nodes rules_or_rule_nodes) throws Exception {
            super(Oks.valide(rules_or_rule_nodes._defined_analizer_rules));
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(rules_or_rule_nodes, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @Override
        public void init(Analyzer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            try {
                super.init(analizer_rules, ok, extras_array);
                if (ok.is == false) return;
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        @SuppressFBWarnings("UR_UNINIT_READ_CALLED_FROM_SUPER_CONSTRUCTOR")
        @Override
        public void init_to_reuse_or_repeat(boolean is_reuse, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init_to_reuse_or_repeat(is_reuse, ok, extras_array);
                if (ok.is == false) return;
                if (_defined_rule_nodes_or_list != null) {
                    for (var node : _defined_rule_nodes_or_list) {
                        node.init_to_reuse_or_repeat(true, ok);
                        if (ok.is == false) return;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         * @param rules_or_rule_node
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init(Rules_or_rule_nodes rules_or_rule_node, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init(rules_or_rule_node, ok, extras_array);
                if (ok.is == false) return;
                _defined_rule_nodes_or_list.clear();
                _defined_rule_nodes_or_list.addAll(rules_or_rule_node._defined_rule_nodes_or_list);
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        /**
         * Try to find the first rule matching from the rule_node_list
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        @Override
        public Return_status _evaluate(Scanner_rules.Basic_tokens basic_token, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return Return_status.error;
            try {
                Boolean result = null;
                if (_defined_rule_nodes_or_list.isEmpty()) {
                    ok.setTex(Tr.in(k_in_route, "No rules defined. "));
                    return Return_status.error;
                }
                for (var basic_rule_node : _defined_rule_nodes_or_list) {
                    result = basic_rule_node.evaluate(basic_token, ok, extras_array);
                    if (ok.is == false) return Return_status.error;
                    if (result == null) {
                        break;
                    } else if (result == true) {
                        if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                            _first_token_pos = basic_rule_node._first_token_pos;
                        }
                        break;
                    } else {
                        // Restore the token position
                        basic_token = ok.valid(_defined_analizer_rules.i_code_scanner.scan_next(ok, extras_array));
                        if (ok.is == false) return Return_status.error;
                    }
                }
                if (result == null) {
                    return Return_status.need_next_token;
                } else if (result == false) {
                    if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                        return Return_status.need_next_token;
                    }
                    return Return_status.not_matched;
                } else {
                    if (defined_rule_success != null) {
                        defined_rule_success.first_token_pos = ok.valid(_first_token_pos);
                        ok.valid(defined_rule_success).basic_token = basic_token; // Token in the list of positively evaluated tokens
                    }
                    return Return_status.matched;
                }
            } catch (Exception e) {
                ok.setTex(e);
                return Return_status.error;
            }
        }

        @Override
        public String normalize_key_name() {
            super.normalize_key_name();
            if (_defined_key_name.startsWith("ro_") == false) {
                _defined_key_name =  "ro_" + _defined_key_name;
            }
            return _defined_key_name;
        }

        @Override
        public String normalize_name() {
            String new_name = normalize_key_name();
            String long_name = "";
            for (var item: _defined_rule_nodes_or_list) {
                if (long_name.isEmpty()) {
                    if (_defined_rule_nodes_or_list.size() > 1) {
                        long_name = Oks.no_fenum_cast(k_key_name_or_begin_separator);
                    }
                } else {
                    long_name = long_name + Oks.no_fenum_cast(k_key_name_or_separator);
                }
                long_name = long_name + item.normalize_key_name();
            }
            if (_defined_rule_nodes_or_list.size() > 1) {
                long_name = long_name + Oks.no_fenum_cast(k_key_name_or_end_separator);
            }
            _defined_name = new_name + ": " + long_name;
            return _defined_name;
        }

        @Override
        public boolean clear_add_new_list() throws Exception {
            _defined_rule_nodes_or_list.clear();
            return true;
        }

        @Override
        public Rule_nodes _add_new(Rule_nodes rule_node) throws Exception {
            Rule_nodes new_rule_node;
            new_rule_node = Oks.no_fenum_cast(rule_node.getClass().getConstructor(rule_node.getClass()).newInstance(rule_node));
            _defined_rule_nodes_or_list.add(new_rule_node);
            return rule_node;
        }

        @Override
        public Rule_nodes _add_new(Scanner_rules.Basic_tokens basic_token) throws Exception {
            Tokens_or_rule_nodes tokens_or_rule_node;
            Rule_nodes rule_node = Oks.allow_nulle(find_by_key_name(create_key_name(basic_token)));
            if (rule_node == null) {
                // Create Tokens_or_rule_nodes for only one token
                tokens_or_rule_node = new Tokens_or_rule_nodes(_defined_analizer_rules);
                tokens_or_rule_node.configure_from_key_name(create_key_name(basic_token));
                tokens_or_rule_node.add_new(basic_token);
                _defined_rule_nodes_or_list.add(tokens_or_rule_node);
            } else {
                if (rule_node instanceof Tokens_or_rule_nodes) {
                    tokens_or_rule_node = new Tokens_or_rule_nodes((Tokens_or_rule_nodes) rule_node);
                    if (tokens_or_rule_node._defined_tokens_or_list.contains(basic_token) == false) {
                        throw new Exception(Tr.in(k_in_route, "Rule node found does not contain token: ")
                                + create_key_name(basic_token));
                    }
                    _defined_rule_nodes_or_list.add(tokens_or_rule_node);
                } else {
                    throw new Exception(Tr.in(k_in_route, "Rule node found is instance of another class. "));
                }
            }
            return tokens_or_rule_node;
        }

        @Override
        public List<String> get_add_new_key_names_list(Oks ok, Object... extras_array) throws Exception {
            List<String> retorno_list = new ArrayList<>();
            if (ok.is == false) return retorno_list;
            Oks noted_ok = ok.valid(ok.create_new(extras_array));
            for (var item: _defined_rule_nodes_or_list) {
                if (_defined_analizer_rules.rule_nodes_map.containsKey(item.get_key_name()) == false) {
                    ok.addTex(Tr.in(k_in_route, "Rule not found: ") + item.get_key_name());
                    noted_ok.init();
                }
                retorno_list.add(item.get_key_name());
            }
            if (ok.is == true) {
                retorno_list.clear();
            }
            return retorno_list;
        }

    }

    public @Nullable Rule_nodes start_rule_node = null;
    public I_code_scanners i_code_scanner;
    public Deque<Rule_success> success_rules_list = new LinkedList<>();
    public Map<String, Rule_nodes> rule_nodes_map = new HashMap<>();

    public Analyzer_rules(I_code_scanners i_code_scanner) {
        this.i_code_scanner = i_code_scanner;
    }

    /**
     *
     * @param first_token_pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process(Integer first_token_pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            Deque<Rule_success> to_delete_success_rules_list = new LinkedList<>();
            boolean is_processed = false;
            for(var rule_success: success_rules_list) {
                if (rule_success.first_token_pos >= first_token_pos) {
                    is_processed = true;
                    rule_success.after_success.call(ok.valid(rule_success.basic_token)
                            , ok, extras_array);
                    if (ok.is == false) return;
                    to_delete_success_rules_list.add(rule_success);
                }
            }
            for(var rule_success: to_delete_success_rules_list) {
                success_rules_list.remove(rule_success);
            }
            if (is_processed == false) {
                ok.setTex(Tr.in(k_in_route, "No rule to process (might be cause by set defined_is_to_process_the_success_rules_list_if_success to true in an inner rule. "));
                return;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param first_token_pos
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void process_clean(Integer first_token_pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            Deque<Rule_success> to_delete_success_rules_list = new LinkedList<>();
            for(var rule_success: success_rules_list) {
                if (rule_success.first_token_pos >= first_token_pos) {
                    to_delete_success_rules_list.add(rule_success);
                }
            }
            for(var rule_success: to_delete_success_rules_list) {
                success_rules_list.remove(rule_success);
            }
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
    public Map <String, Name_based_rule_nodes> get_rules_list_yaml(Oks ok, Object ... extras_array) throws Exception {
        Map <String, Name_based_rule_nodes> name_based_rule_nodes_map = new HashMap<>();
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return name_based_rule_nodes_map;
        try {
            Name_based_rule_nodes name_based_rule_node;
            String key_name;
            String name;
            List<String> add_new_key_names_list;
            for (var entry: rule_nodes_map.entrySet()) {
                name_based_rule_node = new Name_based_rule_nodes();
                key_name = entry.getValue().normalize_key_name();
                name = entry.getValue().normalize_name();
                name_based_rule_node._defined_name = name;
                add_new_key_names_list = entry.getValue().get_add_new_key_names_list(ok, extras_array);
                if (ok.is == false) return name_based_rule_nodes_map;
                name_based_rule_node.name_based_rule_nodes_list.addAll(add_new_key_names_list);
                name_based_rule_nodes_map.put(key_name, name_based_rule_node);
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        return name_based_rule_nodes_map;
    }

    /**
     *
     * @param file
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void get_rules_list_yaml(File file, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            Map <String, Name_based_rule_nodes> name_based_rule_nodes_map = get_rules_list_yaml(ok, extras_array);
            if (ok.is == false) return;
            Yamls yaml = new Yamls();
            yaml.init(ok, extras_array);
            if (ok.is == false) return;
            yaml.open_and_write_file(file, name_based_rule_nodes_map, ok, extras_array);
            if (ok.is == false) return;
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param file
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void set_rules_list_yaml(File file, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            Map <String, Name_based_rule_nodes> name_based_rule_nodes_map = new HashMap<>();
            Yamls yaml = new Yamls();
            yaml.init(ok, extras_array);
            if (ok.is == false) return;
            byte []  bytes_array = ok.valid(yaml.open_and_read_file(file, ok, extras_array));
            if (ok.is == false) return;
            name_based_rule_nodes_map = ok.valid(yaml.objectMapper).readValue(bytes_array
                    , new TypeReference<Map<String, Name_based_rule_nodes>>() {});
            Rule_nodes rule_node;
            for (var entry: name_based_rule_nodes_map.entrySet()) {
                if (entry.getValue()._defined_name.contains(Oks.no_fenum_cast(Rule_nodes.k_key_name_token_separator))) {
                    rule_node = new Tokens_or_rule_nodes(this);
                } else if (entry.getValue()._defined_name.contains(Oks.no_fenum_cast(Rule_nodes.k_key_name_and_separator))) {
                    rule_node = new Rules_and_rule_nodes(this);
                } else {
                    rule_node = new Rules_or_rule_nodes(this);
                }
                rule_node.configure_from_key_name(entry.getKey());
                rule_node.add_new(entry.getValue().name_based_rule_nodes_list, ok, extras_array);
                if (ok.is == false) return;
                rule_nodes_map.put(entry.getKey(), rule_node);
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        return;
    }

}
