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
    public static @Fenum("error_id") String k_is_bad_way = Oks.no_fenum_cast("is_bad_way");


    public interface After_success_calls extends Serializable {
        Oks call(int repetition_num, List<Scanner_rules.Basic_tokens> first_part_tokens_list, Oks ok, Object ... extras_array) throws Exception;
    }

    public static class Rule_success implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public int repetition_num = 0;
        public @Nullable ArrayList<Scanner_rules.Basic_tokens> first_part_tokens_list = new ArrayList<>();
        public After_success_calls after_success;

        public Rule_success(After_success_calls after_success) {
            this.after_success = after_success;
        }
    }

    public static abstract class Basic_rule_nodes implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public String defined_name = "";
        public boolean defined_is_optional = false;
        public boolean defined_is_repeatable = false;
        public boolean defined_is_to_process_the_success_rules_list_if_sucess = false;
        public @Nullable Analizer_rules _defined_analizer_rules = null;
        public @Nullable Rule_success defined_to_put_in_list_rule_success = null;
        public Analizer_rules.@Nullable Basic_rule_nodes defined_second_part_basic_rule_node = null;
        public boolean _is_a_repeat_success_previous_not_now = false;
        public boolean _is_already_evaluated = false;
        public boolean _is_bad_way = false;
        public boolean _is_first_part_evaluated = false;
        public @Nullable Integer _first_token_pos = null;
        public @Nullable Boolean _is_once_successed = null;

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Basic_rule_nodes(Analizer_rules analizer_rules) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                _defined_analizer_rules = analizer_rules;
                defined_name = "";
                defined_is_optional = false;
                defined_is_repeatable = false;
                defined_is_to_process_the_success_rules_list_if_sucess = false;
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
        public Basic_rule_nodes(Basic_rule_nodes basic_rule_node) throws Exception {
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
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init_to_reuse(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                _first_token_pos = null;
                _is_a_repeat_success_previous_not_now = false;
                if (defined_to_put_in_list_rule_success != null) {
                    defined_to_put_in_list_rule_success.repetition_num = 0;
                    ok.valid(defined_to_put_in_list_rule_success.first_part_tokens_list).clear();
                }
                init_for_repeat(ok, extras_array);
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         * Allows a repetition in same initial conditions
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
                _is_already_evaluated = false;
                _is_first_part_evaluated = false;
                _is_once_successed = null;
                if (defined_second_part_basic_rule_node != null) {
                    defined_second_part_basic_rule_node.init_to_reuse(ok, extras_array);
                    if (ok.is == false) return;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }
        /**
         *
         * @param basic_rule_node
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init(Basic_rule_nodes basic_rule_node, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                this.defined_name = basic_rule_node.defined_name;
                this._defined_analizer_rules = basic_rule_node._defined_analizer_rules;
                this.defined_is_optional = basic_rule_node.defined_is_optional;
                this.defined_is_repeatable = basic_rule_node.defined_is_repeatable;
                this.defined_is_to_process_the_success_rules_list_if_sucess = basic_rule_node.defined_is_to_process_the_success_rules_list_if_sucess;
                this.defined_to_put_in_list_rule_success = basic_rule_node.defined_to_put_in_list_rule_success;
                this._is_a_repeat_success_previous_not_now = basic_rule_node._is_a_repeat_success_previous_not_now;
                this._is_already_evaluated = basic_rule_node._is_already_evaluated;
                this._is_bad_way = basic_rule_node._is_bad_way;
                this._is_first_part_evaluated = basic_rule_node._is_first_part_evaluated;
                this._first_token_pos = basic_rule_node._first_token_pos;
                this._is_once_successed = basic_rule_node._is_once_successed;
                if (this.defined_second_part_basic_rule_node != null) {
                    this.defined_second_part_basic_rule_node.init(ok.valid(basic_rule_node.defined_second_part_basic_rule_node), ok, extras_array);
                    if (ok.is == false) return;
                } else {
                    defined_second_part_basic_rule_node = ok.valid(basic_rule_node).defined_second_part_basic_rule_node;
                }
                if (basic_rule_node.defined_to_put_in_list_rule_success != null) {
                    ok.valid(defined_to_put_in_list_rule_success).repetition_num
                            = ok.valid(basic_rule_node.defined_to_put_in_list_rule_success).repetition_num;
                    ok.valid(ok.valid(defined_to_put_in_list_rule_success).first_part_tokens_list).clear();
                    ok.valid(ok.valid(defined_to_put_in_list_rule_success).first_part_tokens_list).addAll(ok.valid(ok.valid(basic_rule_node
                            .defined_to_put_in_list_rule_success).first_part_tokens_list));
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }
        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return true = success, false = fail, null = not totally evaluated or error
         * @throws Exception
         */
        public abstract @Nullable Boolean _evaluate_first_part(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception;

        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return true = success, false = fail, null = not totally evaluated or error
         * @throws Exception
         */
        public @Nullable Boolean evaluate(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                Boolean is;
                boolean is_pending = false;
                in = ok.valid(ResourceBundles.getBundle(k_in_route));
                if (_is_already_evaluated == true) {
                    if (defined_is_repeatable == false) {
                        ok.setTex(Tr.in(in, "Rule already evaluated. Must do init to reevaluate. "));
                        return null;
                    } else {
                        init_for_repeat(ok, extras_array);
                        if (ok.is == false) return null;
                        if (defined_to_put_in_list_rule_success != null) {
                            defined_to_put_in_list_rule_success.repetition_num = defined_to_put_in_list_rule_success.repetition_num + 1;
                        }
                    }
                }
                if (_is_first_part_evaluated == false) {
                    if (_first_token_pos == null) {
                        _first_token_pos = ok.valid(_defined_analizer_rules).code_scanner.tokens_list.size();
                    }
                    is = _evaluate_first_part(basic_token, ok, extras_array);
                    if (ok.is == false) return false;
                    if (is != null) {
                        if (is == false) {
                            if (_is_once_successed == null) {
                                _is_once_successed = false;
                            }
                            _first_token_pos = null;
                            return false;
                        }
                    } else {
                        is_pending = true;
                    }
                    if (defined_second_part_basic_rule_node == null) {
                        _is_first_part_evaluated = false; // for repeating
                        _is_already_evaluated = true;
                        _is_once_successed = true;
                        if (defined_to_put_in_list_rule_success != null) {
                            ok.valid(_defined_analizer_rules).success_rules_list.addFirst(ok.valid(defined_to_put_in_list_rule_success));
                        }
                        if (defined_is_to_process_the_success_rules_list_if_sucess) {
                            ok.valid(_defined_analizer_rules).process(ok, extras_array);
                            if (ok.is == false) return false;
                        }
                        return true;
                    } else {
                        if (is_pending == false) {
                            _is_first_part_evaluated = true;
                        }
                        return null;
                    }
                } else {
                    return evaluate_second_part(basic_token, ok, extras_array);
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return null;
        }

        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public @Nullable Boolean evaluate_second_part(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                Boolean is;
                boolean is_pending = false;
                in = ok.valid(ResourceBundles.getBundle(k_in_route));
                if (_is_already_evaluated == true && defined_is_repeatable == false) {
                    ok.setTex(Tr.in(in, "Rule already evaluated. Must do init to reevaluate. "));
                    return null;
                }
                _is_a_repeat_success_previous_not_now = false;
                if (defined_second_part_basic_rule_node == null) {
                    ok.setTex(Tr.in(in, "Rule is null. "));
                    return null;
                }
                if (defined_second_part_basic_rule_node._is_once_successed == null
                 || (defined_second_part_basic_rule_node._is_once_successed
                  && defined_second_part_basic_rule_node.defined_is_repeatable)) {
                    is = defined_second_part_basic_rule_node.evaluate(basic_token, ok, extras_array);
                    if (ok.is == false) return false;
                    if (is != null) {
                        if (is == false) {
                            if (_is_once_successed == null || _is_once_successed == false) {
                                if (ok.valid(defined_second_part_basic_rule_node).defined_is_repeatable) {
                                    if (ok.valid(defined_second_part_basic_rule_node)._is_once_successed == null) {
                                        ok.setTex(Tr.in(in, "Not null return evaluating a node whose success state is null . "));
                                        return null;
                                    }
                                    if (ok.valid(ok.valid(defined_second_part_basic_rule_node)._is_once_successed) == false) {
                                        // Previous repeat was failed (not to consider)
                                        _is_once_successed = false;
                                    } else {
                                        // Previous repeat was failed but this basic_token does not match
                                        _is_a_repeat_success_previous_not_now = true;
                                    }
                                } else {
                                    _is_once_successed = false;
                                }
                            }
                            return false;
                        } else {
                            if (ok.valid(defined_second_part_basic_rule_node)._is_already_evaluated) {
                                _is_first_part_evaluated = false; // for repeating
                                _is_already_evaluated = true;
                                _is_once_successed = true;
                                if (defined_to_put_in_list_rule_success != null) {
                                    ok.valid(_defined_analizer_rules).success_rules_list.addFirst(ok.valid(defined_to_put_in_list_rule_success));
                                }
                                if (defined_is_to_process_the_success_rules_list_if_sucess) {
                                    ok.valid(_defined_analizer_rules).process(ok, extras_array);
                                    if (ok.is == false) return false;
                                }
                                return true;
                            } else {
                                ok.setTex(Tr.in(in, "Return true for a not already evaluated rule. "));
                                return null;
                            }
                        }
                    } else {
                        return null;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return null;
        }
    }

    public static class Tokens_or_rule_nodes extends Basic_rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public  ArrayList<Scanner_rules.Basic_tokens> defined_first_part_evaluation_tokens_list = new ArrayList<>();
        public  boolean is_ignore_not_matched = false;

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
        public void init_to_reuse(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init_to_reuse(ok, extras_array);
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
                is_ignore_not_matched = false;
                if (defined_first_part_evaluation_tokens_list != null) {
                    for (var token: defined_first_part_evaluation_tokens_list) {
                        token.init(ok, extras_array);
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
                this.is_ignore_not_matched = tokens_or_rule_node.is_ignore_not_matched;
                if (defined_first_part_evaluation_tokens_list != null) {
                    for (var token: defined_first_part_evaluation_tokens_list) {
                        token.init(token, ok);
                        if (ok.is == false) return;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        @Override
        public @Nullable Boolean _evaluate_first_part(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                boolean is;
                in = ok.valid(ResourceBundles.getBundle(k_in_route));
                if (defined_first_part_evaluation_tokens_list != null) {
                    for (var token: defined_first_part_evaluation_tokens_list) {
                        if (Objects.equals(basic_token.token_type, token.token_type) == false) {
                            if (defined_second_part_basic_rule_node == null || defined_second_part_basic_rule_node._is_already_evaluated) {
                                init_to_reuse(ok, extras_array);
                                if (ok.is == false) return null;
                                _is_already_evaluated = true;
                                ok.valid(_defined_analizer_rules).backtrack_pos = ok.valid(_first_token_pos);
                                _is_bad_way = true;
                                ok.setTex(Oks.no_fenum_cast(k_is_bad_way));
                                ok.id = k_is_bad_way;
                            }
                            if (is_ignore_not_matched) {
                                _is_first_part_evaluated = false;
                                return null;
                            }
                        }
                        if (token.token_tex != null) {
                            if (Objects.equals(basic_token.token_tex, token.token_tex) == false) {
                                if (defined_second_part_basic_rule_node == null || defined_second_part_basic_rule_node._is_already_evaluated) {
                                    init_to_reuse(ok, extras_array);
                                    if (ok.is == false) return null;
                                    _is_already_evaluated = true;
                                    ok.valid(_defined_analizer_rules).backtrack_pos = ok.valid(_first_token_pos);
                                    _is_bad_way = true;
                                    ok.setTex(Oks.no_fenum_cast(k_is_bad_way));
                                    ok.id = k_is_bad_way;
                                }
                                if (is_ignore_not_matched) {
                                    _is_first_part_evaluated = false;
                                    return null;
                                }
                            } else {
                                break;
                            }
                        } else {
                            break;
                        }
                    }
                    if (defined_to_put_in_list_rule_success != null) {
                        ok.valid(defined_to_put_in_list_rule_success.first_part_tokens_list).add(basic_token); // Token in the list of positively evaluated tokens
                    }
                    _is_first_part_evaluated = true;
                } else {
                    if (defined_second_part_basic_rule_node == null) {
                        ok.setTex(Tr.in(in, "All content is null in node. "));
                        return null;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return null;
        }
    }

    public static class Find_way_rule_nodes extends Basic_rule_nodes {
        private static final long serialVersionUID = getSerial_version_uid();
        public ArrayList<Basic_rule_nodes> defined_first_part_basic_rule_nodes_list = new ArrayList<>();

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Find_way_rule_nodes(Analizer_rules analizer_rules) throws Exception {
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
        public Find_way_rule_nodes(Find_way_rule_nodes rule_node_find_way) throws Exception {
            super(Oks.valide(rule_node_find_way._defined_analizer_rules));
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(rule_node_find_way, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @SuppressFBWarnings("UR_UNINIT_READ_CALLED_FROM_SUPER_CONSTRUCTOR")
        @Override
        public void init_to_reuse(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init_for_repeat(ok, extras_array);
                if (ok.is == false) return;
                if (defined_first_part_basic_rule_nodes_list != null) {
                    for (var node : defined_first_part_basic_rule_nodes_list) {
                        node.init_to_reuse(ok);
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
                if (defined_first_part_basic_rule_nodes_list != null) {
                    for (var node : defined_first_part_basic_rule_nodes_list) {
                        node.init_for_repeat(ok);
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
         * @param find_way_rule_node
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public void init(Find_way_rule_nodes find_way_rule_node, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            ResourceBundle in = null;
            try {
                super.init(find_way_rule_node, ok, extras_array);
                if (ok.is == false) return;
                if (find_way_rule_node != null) {
                    this.defined_first_part_basic_rule_nodes_list.clear();
                    for (var node : find_way_rule_node.defined_first_part_basic_rule_nodes_list) {
                        this.defined_first_part_basic_rule_nodes_list.add(Oks.no_fenum_cast(node.getClass().getConstructor(node.getClass()).newInstance(node)));
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return;
        }

        /**
         * Try to find the first rule matching from the rule_node_list
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        @Override
        public @Nullable Boolean _evaluate_first_part(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                Boolean is;
                boolean is_evaluated = true;
                if (defined_first_part_basic_rule_nodes_list != null && defined_first_part_basic_rule_nodes_list.isEmpty() == false) {
                    for (var node : defined_first_part_basic_rule_nodes_list) {
                        if (node._is_already_evaluated == false
                         && (node._is_once_successed == null
                          || (node._is_once_successed && node.defined_is_repeatable))) {
                            is_evaluated = true;
                            is = node.evaluate(basic_token, ok, extras_array);
                            if (ok.is == false) return null;
                            if (is != null) {
                                if (is == false) {
                                    if (node.defined_is_repeatable) {
                                        if (node._is_a_repeat_success_previous_not_now) {
                                            if (defined_second_part_basic_rule_node != null) {
                                                _is_first_part_evaluated = true;
                                                is = evaluate_second_part(basic_token, ok, extras_array);
                                                if (ok.is == false) return null;
                                                if (is != null) {
                                                    if (is) {
                                                        _is_already_evaluated = true;
                                                        return true;
                                                    } else {
                                                        ok.valid(defined_second_part_basic_rule_node).init_to_reuse(ok, extras_array);
                                                        if (ok.is == false) return null;
                                                    }
                                                } else {
                                                    return null;
                                                }
                                            } else {
                                                _is_already_evaluated = true;
                                                return true;
                                            }
                                        }
                                    }
                                    if (node.defined_is_optional == false) {
                                        if (defined_second_part_basic_rule_node != null) {
                                            _is_first_part_evaluated = true;
                                            is = evaluate_second_part(basic_token, ok, extras_array);
                                            if (ok.is == false) return null;
                                            if (is != null) {
                                                if (is) {
                                                    _is_already_evaluated = true;
                                                    return true;
                                                } else {
                                                    ok.valid(defined_second_part_basic_rule_node).init_to_reuse(ok, extras_array);
                                                    if (ok.is == false) return null;
                                                }
                                            } else {
                                                return null;
                                            }
                                        } else {
                                            _is_already_evaluated = true;
                                            return true;
                                        }
                                    }
                                } else {
                                    _is_first_part_evaluated = true;
                                    return true; // A rule has matched
                                }
                            } else {
                                return null;
                            }
                        }
                    }
                    if (is_evaluated) {
                        _is_first_part_evaluated = true;
                        return false; // No rule matching
                    } else {
                        if (defined_second_part_basic_rule_node == null || defined_second_part_basic_rule_node._is_already_evaluated) {
                            init_to_reuse(ok, extras_array);
                            if (ok.is == false) return null;
                            _is_already_evaluated = true;
                            ok.valid(_defined_analizer_rules).backtrack_pos = ok.allow_null(_first_token_pos);
                            _is_bad_way = true;
                            ok.setTex(Oks.no_fenum_cast(k_is_bad_way));
                            ok.id = k_is_bad_way;
                            return false; // No rule matching
                        }
                    }
                } else {
                    if (defined_second_part_basic_rule_node == null) {
                        ok.setTex(Tr.in(ok.valid(in), "All the requirements are null in node. "));
                        return null;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return null;
        }
    }

    public @Nullable Basic_rule_nodes start_rule_node = null;
    public @Nullable Integer backtrack_pos = null;
    public Code_scanners code_scanner;
    public Deque<Rule_success> success_rules_list = new LinkedList<>();

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
                rule_success.after_success.call(rule_success.repetition_num, ok.valid(rule_success.first_part_tokens_list)
                        , ok, extras_array);
                if (ok.is == false) return;
            }
            success_rules_list.clear();
        } catch (Exception e) {
            ok.setTex(e);
        }
    }
}
