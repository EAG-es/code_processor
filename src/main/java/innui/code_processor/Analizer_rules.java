package innui.code_processor;

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

import static innui.code_processor.Code_scanners.k_need_token_backtrack;
import static innui.code_processor.Code_scanners.k_need_token_last;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public class Analizer_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Analizer_rules.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Analizer_rules.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
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

    public enum Repeat_mode {
        no_repeat
        , repeat_while_success
    }

    @FunctionalInterface
    public interface After_success_calls extends Serializable {
        void call(int tokens_list_pos, List<Scanner_rules.Basic_tokens> tokens_list, Oks ok, Object ... extras_array) throws Exception;
    }

    public static class Rule_success implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public int tokens_list_pos = 0;
        public ArrayList<Scanner_rules.Basic_tokens> tokens_list = new ArrayList<>();
        public After_success_calls after_success;

        public Rule_success(After_success_calls after_success) {
            this.after_success = after_success;
        }
    }

    public static abstract class Rule_nodes implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public String defined_name = "";
        public Optional_mode defined_optional_mode = Optional_mode.no_optional;
        public Repeat_mode defined_repeat_mode = Repeat_mode.no_repeat;
        public boolean defined_is_to_process_the_success_rules_list_if_success = false;
        public Analizer_rules defined_analizer_rules;
        public @Nullable Rule_success defined_rule_success = null;
        public boolean _is_already_evaluated = false;
        public @Nullable Integer _first_token_pos = null;
        public @Nullable Boolean _is_once_successed = null;

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings({"nullness:method.invocation", "nullness:initialization.fields.uninitialized"})
        public Rule_nodes(Analizer_rules analizer_rules) throws Exception {
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
        public Rule_nodes(Rule_nodes basic_rule_node) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(basic_rule_node, ok);
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
        public void init(Analizer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                defined_analizer_rules = analizer_rules;
                defined_name = "";
                defined_optional_mode = Optional_mode.no_optional;
                defined_repeat_mode = Repeat_mode.no_repeat;
                defined_is_to_process_the_success_rules_list_if_success = false;
                defined_rule_success = null;
                init_to_reuse(ok, extras_array);
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
        public void init_to_reuse(Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                _is_once_successed = null;
                _first_token_pos = null;
                if (defined_rule_success != null) {
                    defined_rule_success.tokens_list_pos = 0;
                    defined_rule_success.tokens_list.clear();
                }
                init_for_repeat(ok, extras_array);
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         * Allows a repetition in same initial conditions
         *
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init_for_repeat(Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                _is_already_evaluated = false;
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
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
                defined_name = basic_rule_node.defined_name;
                defined_analizer_rules = basic_rule_node.defined_analizer_rules;
                defined_optional_mode = basic_rule_node.defined_optional_mode;
                defined_repeat_mode = basic_rule_node.defined_repeat_mode;
                defined_is_to_process_the_success_rules_list_if_success = basic_rule_node.defined_is_to_process_the_success_rules_list_if_success;
                _is_already_evaluated = basic_rule_node._is_already_evaluated;
                _first_token_pos = basic_rule_node._first_token_pos;
                _is_once_successed = basic_rule_node._is_once_successed;
                if (basic_rule_node.defined_rule_success == null) {
                    defined_rule_success = null;
                } else {
                    defined_rule_success = new Rule_success(basic_rule_node.defined_rule_success.after_success);
                    ok.valid(defined_rule_success).tokens_list_pos
                            = ok.valid(basic_rule_node.defined_rule_success).tokens_list_pos;
                    ok.valid(defined_rule_success).tokens_list.clear();
                    ok.valid(defined_rule_success).tokens_list.addAll(ok.valid(basic_rule_node
                            .defined_rule_success).tokens_list);
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
            Return_status result = Return_status.error;
            if (_is_already_evaluated) {
                if (_is_once_successed == null) {
                    ok.setTex(Tr.in(k_in_route, "Rule evaluated without success information. "));
                    return null;
                } else if (_is_once_successed) {
                    ok.id = k_need_token_last; // Rule ends, no next token
                    return true;
                } else {
                    ok.id = k_need_token_last; // Rule ends, no next token
                    return false;
                }
            }
            try {
                defined_analizer_rules.rules_nodes_list.addFirst(this);
                if (_first_token_pos == null) {
                    _first_token_pos = defined_analizer_rules.code_scanner.tokens_list.size() - 1;
                }
                switch (0) {default -> {
                    do {
                        retorno = null;
                        result = _evaluate(basic_token, ok, extras_array);
                        if (ok.is == false) break;
                        result = change_status(result, ok, extras_array);
                        if (ok.is == false) break;
                        if (result == Return_status.need_next_token) {
                            if (ok.equals(ok.id, k_need_token_last)) {
                                ok.init();
                            } else if (ok.equals(ok.id, k_need_token_backtrack)) {
                                ok.setTex(Tr.in(k_in_route, "Need token backtrack is not compatible with need next token. "));
                                break;
                            } else {
                                basic_token = ok.valid(defined_analizer_rules.code_scanner.scan_next(ok, extras_array));
                                if (ok.is == false) break;
                            }
                        }
                    } while (result == Return_status.need_next_token);
                    if (ok.equals(ok.id, k_need_token_last)) {
                        ok.init();
                        defined_analizer_rules.rules_nodes_list.pollFirst();
                        if (defined_analizer_rules.rules_nodes_list.isEmpty()) {
                            ok.setTex(Tr.in(k_in_route, "Rules list is empty."));
                            break;
                        }
                        retorno = defined_analizer_rules.rules_nodes_list.getFirst().evaluate(basic_token, ok, extras_array);
                        if (ok.is == false) break;
                    } else if (ok.equals(ok.id, k_need_token_backtrack)) {
                        ok.init();
                        defined_analizer_rules.rules_nodes_list.pollFirst();
                        if (defined_analizer_rules.rules_nodes_list.isEmpty()) {
                            ok.setTex(Tr.in(k_in_route, "Rules list is empty."));
                            break;
                        }
                        retorno = defined_analizer_rules.code_scanner.analize_token_with_rule(basic_token
                                , defined_analizer_rules.rules_nodes_list.getFirst(), ok, extras_array);
                        if (ok.is == false) break;
                    } else {
                        if (result.equals(Return_status.error)) {
                            break;
                        } else if (result.equals(Return_status.matched)) {
                            if (defined_is_to_process_the_success_rules_list_if_success == true) {
                                defined_analizer_rules.process(ok, extras_array);
                                if (ok.is == false) break;
                            }
                            retorno = true;
                            break;
                        } else {
                            retorno = false;
                            break;
                        }
                    }
                }}
            } catch (Exception e) {
                ok.setTex(e);
                return null;
            } finally {
                defined_analizer_rules.rules_nodes_list.pollFirst();
            }
            return retorno;
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
            ResourceBundle in = null;
            try {
                if (return_status == Return_status.error) {
                    _is_already_evaluated = true;
                    _is_once_successed = false;
                    defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                    ok.id = k_need_token_backtrack; // Try another rule
                    return Return_status.error;
                } else if (return_status == Return_status.not_matched) {
                    if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                        init_to_reuse(ok, extras_array);
                        if (ok.is == false) return Return_status.error;
                        return Return_status.need_next_token;
                    }
                    if (defined_repeat_mode != Repeat_mode.no_repeat) {
                        if (_is_once_successed != null && _is_once_successed == true) {
                            _is_already_evaluated = true;
                            _is_once_successed = false;
                            ok.id = k_need_token_last;
                            return Return_status.matched;
                        } else {
                            _is_once_successed = false;
                            if (defined_optional_mode == Optional_mode.no_optional) {
                                _is_already_evaluated = true;
                                _is_once_successed = false;
                                ok.id = k_need_token_backtrack; // Try another rule
                                defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                                return Return_status.not_matched;
                            }
                        }
                    }
                    if (defined_optional_mode == Optional_mode.optional) {
                        _is_already_evaluated = true;
                        _is_once_successed = true; // matched option: empty
                        ok.id = k_need_token_backtrack; // Try another rule
                        defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                        return Return_status.matched;
                    }
                    _is_already_evaluated = true;
                    _is_once_successed = false;
                    ok.id = k_need_token_backtrack; // Try another rule
                    defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                    return Return_status.not_matched;
                } else if (return_status == Return_status.matched) {
                    if (defined_rule_success != null) {
                        Rule_success rule_success = new Rule_success(ok.valid(defined_rule_success).after_success);
                        rule_success.tokens_list.addAll(ok.valid(defined_rule_success).tokens_list);
                        rule_success.tokens_list_pos = ok.valid(defined_rule_success).tokens_list_pos;
                        defined_analizer_rules.success_rules_list.add(rule_success);
                    }
                    _is_already_evaluated = true;
                    _is_once_successed = true;
                    if (defined_repeat_mode != Repeat_mode.no_repeat) {
                        init_for_repeat(ok, extras_array);
                        if (ok.is == false) return Return_status.error;
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

    }

    public static class Rules_and_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Rule_nodes> defined_rule_nodes_and_list = new ArrayList<>();
        public Integer _rule_part_num = 0;
        public Integer _defined_rule_part_tam = -1;

        public Rules_and_rule_nodes(Analizer_rules analizer_rules) throws Exception {
            super(analizer_rules);
        }

        /**
         *
         * @param analizer_rules
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public void init(Analizer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init(analizer_rules, ok, extras_array);
                if (ok.is == false) return;
                defined_rule_nodes_and_list = new ArrayList<>();
                _defined_rule_part_tam = 0;
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        public Rules_and_rule_nodes(Rules_and_rule_nodes rules_and_rule_node) throws Exception {
            super(Oks.valide(rules_and_rule_node.defined_analizer_rules));
        }

        @Override
        public void init_to_reuse(Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init_to_reuse(ok, extras_array);
                if (ok.is == false) return;
                _defined_rule_part_tam = -1;
                if (defined_rule_nodes_and_list != null) {
                    for (var node : defined_rule_nodes_and_list) {
                        node.init_to_reuse(ok, extras_array);
                        if (ok.is == false) return;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         * Allows a repetition in same initial conditions
         *
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init_for_repeat(Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                _rule_part_num = 0;
                super.init_for_repeat(ok, extras_array);
                if (ok.is == false) return;
                if (defined_rule_nodes_and_list != null) {
                    for (var node : defined_rule_nodes_and_list) {
                        node.init_for_repeat(ok, extras_array);
                        if (ok.is == false) return;
                    }
                }
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
                defined_rule_nodes_and_list.clear();
                defined_rule_nodes_and_list.addAll(rules_and_rule_node.defined_rule_nodes_and_list);
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
                    if (defined_rule_nodes_and_list.size() == 0) {
                        ok.setTex(Tr.in(in, "No rules defined. "));
                        return Return_status.error;
                    }
                    _defined_rule_part_tam = defined_rule_nodes_and_list.size();
                }
                Boolean result;
                if (_rule_part_num >= defined_rule_nodes_and_list.size()) {
                    ok.setTex(Tr.in(k_in_route, "Reached the end of the nodes-and list"));
                    return Return_status.error;
                }
                Rule_nodes rule_node = defined_rule_nodes_and_list.get(_rule_part_num);
                result = rule_node.evaluate(basic_token, ok, extras_array);
                if (ok.is == false) return Return_status.error;
                if (result != null) {
                    if (result == true) {
                        if (defined_rule_success != null) {
                            if (_rule_part_num == 0) {
                                ok.valid(defined_rule_success).tokens_list_pos = ok.valid(defined_rule_success).tokens_list.size();
                                ok.valid(defined_rule_success).tokens_list.add(basic_token); // Token in the list of positively evaluated tokens
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
        public Return_status change_status(Return_status return_status, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return Return_status.error;
            ResourceBundle in = null;
            try {
                if (return_status == Return_status.error) {
                    _is_already_evaluated = true;
                    _is_once_successed = false;
                    defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                    ok.id = k_need_token_backtrack; // Try another rule
                    return return_status;
                } else if (return_status == Return_status.not_matched) {
                    if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                        init_to_reuse(ok, extras_array);
                        if (ok.is == false) return Return_status.error;
                        return Return_status.need_next_token;
                    }
                    if (defined_optional_mode  == Optional_mode.optional) {
                        ok.id = k_need_token_backtrack; // Try another rule
                        _is_already_evaluated = true;
                        _is_once_successed = true;
                        defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                        return Return_status.matched;
                    }
                    if (defined_repeat_mode == Repeat_mode.repeat_while_success) {
                        if (_is_once_successed == null) {
                            ok.setTex(Tr.in(k_in_route, "Not matched rule without success information"));
                            return Return_status.error;
                        } else if (_is_once_successed == true) {
                            if (_rule_part_num < _defined_rule_part_tam) {
                                _rule_part_num = _rule_part_num + 1;
                                if (_rule_part_num < _defined_rule_part_tam) {
                                    _is_already_evaluated = true;
                                    _is_once_successed = false;
                                    ok.id = k_need_token_backtrack; // Try another rule
                                    defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                                    return Return_status.not_matched;
                                } else { // ending
                                    ok.id = k_need_token_last;
                                    _is_already_evaluated = true;
                                    _is_once_successed = true;
                                    ok.id = k_need_token_last; // Go on
                                    return Return_status.matched;
                                }
                            } else { // ending
                                ok.id = k_need_token_last;
                                _is_already_evaluated = true;
                                _is_once_successed = true;
                                ok.id = k_need_token_last; // Go on
                                return Return_status.matched;
                            }
                        } else {
                            _is_already_evaluated = true;
                            _is_once_successed = false;
                            ok.id = k_need_token_backtrack; // Try another rule
                            defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                            return Return_status.not_matched;
                        }
                    }
                    _is_already_evaluated = true;
                    _is_once_successed = false;
                    ok.id = k_need_token_backtrack; // Try another rule
                    defined_analizer_rules.backtrack_pos = ok.valid(_first_token_pos);
                    return Return_status.not_matched;
                } else if (return_status == Return_status.matched) {
                    if (_rule_part_num < _defined_rule_part_tam) {
                        _rule_part_num = _rule_part_num + 1;
                        if (_rule_part_num < _defined_rule_part_tam) {
                            return Return_status.need_next_token;
                        } else {
                            if (defined_rule_success != null) {
                                Rule_success rule_success = new Rule_success(ok.valid(defined_rule_success).after_success);
                                rule_success.tokens_list.addAll(ok.valid(defined_rule_success).tokens_list);
                                rule_success.tokens_list_pos = ok.valid(defined_rule_success).tokens_list_pos;
                                defined_analizer_rules.success_rules_list.addFirst(rule_success);
                            }
                            _is_already_evaluated = true;
                            _is_once_successed = true;
                            if (defined_repeat_mode != Repeat_mode.no_repeat) {
                                init_for_repeat(ok, extras_array);
                                if (ok.is == false) return Return_status.error;
                            }
                            return Return_status.matched;
                        }
                    } else {
                        _is_already_evaluated = true;
                        _is_once_successed = true;
                        if (defined_repeat_mode != Repeat_mode.no_repeat) {
                            init_for_repeat(ok, extras_array);
                            if (ok.is == false) return Return_status.error;
                        }
                        return Return_status.matched;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
                return Return_status.error;
            }
            return return_status;
        }
    }

    public static class Tokens_or_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Scanner_rules.Basic_tokens> defined_tokens_or_list = new ArrayList<>();

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Tokens_or_rule_nodes(Analizer_rules analizer_rules) throws Exception {
            super(analizer_rules);
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init_to_reuse(ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Tokens_or_rule_nodes(Tokens_or_rule_nodes tokens_or_rule_node) throws Exception {
            super(Oks.valide(tokens_or_rule_node.defined_analizer_rules));
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
        public void init(Analizer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init(analizer_rules, ok, extras_array);
                if (ok.is == false) return;
                defined_tokens_or_list = new ArrayList<>();
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        @Override
        public void init_to_reuse(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init_to_reuse(ok, extras_array);
                if (ok.is == false) return;
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        /**
         *
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init_for_repeat(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init_for_repeat(ok, extras_array);
                if (ok.is == false) return;
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
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
                defined_tokens_or_list.clear();
                defined_tokens_or_list.addAll(tokens_or_rule_node.defined_tokens_or_list);
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
            try {
                if (defined_tokens_or_list.isEmpty()) {
                    ok.setTex(Tr.in(k_in_route, "No tokens defined. "));
                    return Return_status.error;
                }
                for (var to_match_basic_token: defined_tokens_or_list) {
                    retorno = _evaluate_a_basic_token(basic_token, to_match_basic_token, ok, extras_array);
                    if (ok.is == false) return Return_status.error;
                    if (retorno != Return_status.not_matched) {
                        break;
                    }
                }
                if (retorno == Return_status.matched) {
                    if (defined_rule_success != null) {
                        ok.valid(defined_rule_success).tokens_list_pos = ok.valid(defined_rule_success).tokens_list.size();
                        ok.valid(defined_rule_success).tokens_list.add(basic_token); // Token in the list of positively evaluated tokens
                        if (defined_optional_mode == Optional_mode.ignore_until_matches) {
                            ok.id = k_need_token_last;
                        }
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
                return Return_status.error;
            }
            return retorno;
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
    }

    public static class Rules_or_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Rule_nodes> defined_rule_nodes_or_list = new ArrayList<>();

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Rules_or_rule_nodes(Analizer_rules analizer_rules) throws Exception {
            super(analizer_rules);
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Rules_or_rule_nodes(Rules_or_rule_nodes rule_node_find_way) throws Exception {
            super(Oks.valide(rule_node_find_way.defined_analizer_rules));
        }

        @Override
        public void init(Analizer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            try {
                super.init(analizer_rules, ok, extras_array);
                if (ok.is == false) return;
                defined_rule_nodes_or_list = new ArrayList<>();
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        @SuppressFBWarnings("UR_UNINIT_READ_CALLED_FROM_SUPER_CONSTRUCTOR")
        @Override
        public void init_to_reuse(Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init_to_reuse(ok, extras_array);
                if (ok.is == false) return;
                for (var node : defined_rule_nodes_or_list) {
                    node.init_to_reuse(ok);
                    if (ok.is == false) return;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        /**
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init_for_repeat(Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init_for_repeat(ok, extras_array);
                if (ok.is == false) return;
                for (var node : defined_rule_nodes_or_list) {
                    node.init_for_repeat(ok);
                    if (ok.is == false) return;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
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
                defined_rule_nodes_or_list.clear();
                defined_rule_nodes_or_list.addAll(rules_or_rule_node.defined_rule_nodes_or_list);
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
                if (defined_rule_nodes_or_list.isEmpty()) {
                    ok.setTex(Tr.in(k_in_route, "No rules defined. "));
                    return Return_status.error;
                }
                for (var basic_rule_node : defined_rule_nodes_or_list) {
                    result = basic_rule_node.evaluate(basic_token, ok, extras_array);
                    if (ok.is == false) return Return_status.error;
                    if (result == null || result == true) {
                        break;
                    }
                }
                if (result == null) {
                    return Return_status.need_next_token;
                }
                if (defined_rule_success != null) {
                    ok.valid(defined_rule_success).tokens_list_pos = ok.valid(defined_rule_success).tokens_list.size();
                    ok.valid(defined_rule_success).tokens_list.add(basic_token); // Token in the list of positively evaluated tokens
                }
                return Return_status.matched;
            } catch (Exception e) {
                ok.setTex(e);
                return Return_status.error;
            }
        }
    }

    public Analizer_rules.@Nullable Rule_nodes start_rule_node = null;
    public @Nullable Integer backtrack_pos = null;
    public Code_scanners code_scanner;
    public Deque<Rule_success> success_rules_list = new LinkedList<>();
    public Deque<Rule_nodes> rules_nodes_list = new LinkedList<>();

    public Analizer_rules(Code_scanners code_scanner) {
        this.code_scanner = code_scanner;
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        Boolean retorno = null;
        try {
            for(var rule_success: success_rules_list) {
                rule_success.after_success.call(rule_success.tokens_list_pos, ok.valid(rule_success.tokens_list)
                        , ok, extras_array);
                if (ok.is == false) return;
            }
            success_rules_list.clear();
        } catch (Exception e) {
            ok.setTex(e);
        }
    }
}
