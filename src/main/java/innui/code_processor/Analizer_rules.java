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
        void call(List<Scanner_rules.Basic_tokens> tokens_list, Oks ok, Object ... extras_array) throws Exception;
    }

    public static class Rule_success implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Scanner_rules.Basic_tokens> tokens_list = new ArrayList<>();
        public Integer first_token_pos = -1;
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
        public Analizer_rules _defined_analizer_rules;
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
                _defined_analizer_rules = analizer_rules;
                defined_name = "";
                defined_optional_mode = Optional_mode.no_optional;
                defined_repeat_mode = Repeat_mode.no_repeat;
                defined_is_to_process_the_success_rules_list_if_success = false;
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
                    if (defined_is_to_process_the_success_rules_list_if_success == true) {
                        _defined_analizer_rules.process_clean(ok.valid(_first_token_pos), ok, extras_array);
                        if (ok.is == false) return;
                    }
                    _is_once_successed = null;
                }
                _first_token_pos = null;
                _is_already_evaluated = false;
                if (defined_rule_success != null) {
                    defined_rule_success.first_token_pos = -1;
                    defined_rule_success.tokens_list.clear();
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
                defined_name = basic_rule_node.defined_name;
                _defined_analizer_rules = basic_rule_node._defined_analizer_rules;
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
                    defined_rule_success.first_token_pos = ok.valid(basic_rule_node
                            .defined_rule_success).first_token_pos;
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
                    if (defined_is_to_process_the_success_rules_list_if_success == true) {
                        _defined_analizer_rules.process(ok.valid(_first_token_pos), ok, extras_array);
                        if (ok.is == false) return;
                    }
                } else {
                    if (defined_is_to_process_the_success_rules_list_if_success == true) {
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
                        rule_success.tokens_list.addAll(ok.valid(defined_rule_success).tokens_list);
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

        /**
         *
         * @param rule_node
         * @throws Exception
         */
        public abstract void add(Rule_nodes rule_node) throws Exception;
        /**
         *
         * @param basic_token
         */
        public abstract void add(Scanner_rules.Basic_tokens basic_token) throws Exception;

        /**
         *
         * @param token_type_tex
         * @throws Exception
         */
        public abstract void add(String token_type_tex) throws Exception;

        /**
         *
         * @param token_tex
         * @param token_type_tex
         * @throws Exception
         */
        public abstract void add(String token_type_tex, String token_tex) throws Exception;

    }

    public static class Rules_and_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Rule_nodes> _defined_rule_nodes_and_list = new ArrayList<>();
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
                _defined_rule_nodes_and_list = new ArrayList<>();
                _defined_rule_part_tam = -1;
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        public Rules_and_rule_nodes(Rules_and_rule_nodes rules_and_rule_node) throws Exception {
            super(Oks.valide(rules_and_rule_node._defined_analizer_rules));
        }

        @Override
        public void init_to_reuse_or_repeat(boolean is_reuse, Oks ok, Object... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
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
                                rule_success.tokens_list.addAll(ok.valid(defined_rule_success).tokens_list);
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
        public void add(Rule_nodes rule_node) throws Exception {
            Rule_nodes new_rule_node;
            new_rule_node = Oks.no_fenum_cast(rule_node.getClass().getConstructor(rule_node.getClass()).newInstance(rule_node));
            _defined_rule_nodes_and_list.add(new_rule_node);
        }

        @Override
        public void add(Scanner_rules.Basic_tokens basic_token) throws Exception {
            Tokens_or_rule_nodes tokens_or_rule_node = new Tokens_or_rule_nodes(_defined_analizer_rules);
            tokens_or_rule_node.add(basic_token);
            _defined_rule_nodes_and_list.add(tokens_or_rule_node);
        }

        @Override
        public void add(String token_type_tex) throws Exception {
            Tokens_or_rule_nodes tokens_or_rule_node = new Tokens_or_rule_nodes(_defined_analizer_rules);
            tokens_or_rule_node.add(token_type_tex);
            _defined_rule_nodes_and_list.add(tokens_or_rule_node);
        }

        @Override
        public void add(String token_type_tex, String token_tex) throws Exception {
            Tokens_or_rule_nodes tokens_or_rule_node = new Tokens_or_rule_nodes(_defined_analizer_rules);
            tokens_or_rule_node.add(token_type_tex, token_tex);
            _defined_rule_nodes_and_list.add(tokens_or_rule_node);
        }
    }

    public static class Tokens_or_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Scanner_rules.Basic_tokens> _defined_tokens_or_list = new ArrayList<>();

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Tokens_or_rule_nodes(Analizer_rules analizer_rules) throws Exception {
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
        @SuppressWarnings("nullness:method.invocation")
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
        public void init(Analizer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
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
                        ok.valid(defined_rule_success).tokens_list.add(basic_token); // Token in the list of positively evaluated tokens
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
        public void add(Rule_nodes rule_node) throws Exception {
            throw new Exception(Tr.in(k_in_route, "This rule only accepts tokens. "));
        }

        @Override
        public void add(Scanner_rules.Basic_tokens basic_token) throws Exception {
            _defined_tokens_or_list.add(basic_token);
        }

        @Override
        public void add(String token_type_tex) throws Exception {
            _defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_type_tex));
        }

        @Override
        public void add(String token_type_tex, String token_tex) throws Exception {
            _defined_tokens_or_list.add(new Scanner_rules.Basic_tokens(token_type_tex, token_tex));
        }
    }

    public static class Rules_or_rule_nodes extends Rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Rule_nodes> _defined_rule_nodes_or_list = new ArrayList<>();

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Rules_or_rule_nodes(Analizer_rules analizer_rules) throws Exception {
            super(analizer_rules);
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Rules_or_rule_nodes(Rules_or_rule_nodes rule_node_find_way) throws Exception {
            super(Oks.valide(rule_node_find_way._defined_analizer_rules));
        }

        @Override
        public void init(Analizer_rules analizer_rules, Oks ok, Object... extras_array) throws Exception {
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
                        ok.valid(defined_rule_success).tokens_list.add(basic_token); // Token in the list of positively evaluated tokens
                    }
                    return Return_status.matched;
                }
            } catch (Exception e) {
                ok.setTex(e);
                return Return_status.error;
            }
        }

        @Override
        public void add(Rule_nodes rule_node) throws Exception {
            Rule_nodes new_rule_node;
            new_rule_node = Oks.no_fenum_cast(rule_node.getClass().getConstructor(rule_node.getClass()).newInstance(rule_node));
            _defined_rule_nodes_or_list.add(new_rule_node);
        }

        @Override
        public void add(Scanner_rules.Basic_tokens basic_token) throws Exception {
            Tokens_or_rule_nodes tokens_or_rule_node = new Tokens_or_rule_nodes(_defined_analizer_rules);
            tokens_or_rule_node.add(basic_token);
            _defined_rule_nodes_or_list.add(tokens_or_rule_node);
        }

        @Override
        public void add(String token_type_tex) throws Exception {
            Tokens_or_rule_nodes tokens_or_rule_node = new Tokens_or_rule_nodes(_defined_analizer_rules);
            tokens_or_rule_node.add(token_type_tex);
            _defined_rule_nodes_or_list.add(tokens_or_rule_node);
        }

        @Override
        public void add(String token_type_tex, String token_tex) throws Exception {
            Tokens_or_rule_nodes tokens_or_rule_node = new Tokens_or_rule_nodes(_defined_analizer_rules);
            tokens_or_rule_node.add(token_type_tex, token_tex);
            _defined_rule_nodes_or_list.add(tokens_or_rule_node);
        }
    }

    public @Nullable Rule_nodes start_rule_node = null;
    public I_code_scanners i_code_scanner;
    public Deque<Rule_success> success_rules_list = new LinkedList<>();

    public Analizer_rules(I_code_scanners i_code_scanner) {
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
                    rule_success.after_success.call(ok.valid(rule_success.tokens_list)
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
}
