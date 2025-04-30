package innui.code_processor;

import innui.Bases;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

public class Analizer_rules extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Analizer_rules.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Analizer_rules.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }

    public static abstract class Basic_rule_nodes implements Serializable {
        public boolean is_optional = false;
        public boolean is_repeatable = false;
        public boolean is_a_repeat_success_previous_not_now = false;
        public boolean is_already_evaluated = false;
        public boolean is_bad_way = true;
        public @Nullable boolean is_first_part_evaluated = false;
        public @Nullable Boolean is_once_successed = null;
        public  Analizer_rules.@Nullable Basic_rule_nodes basic_rule_node = null;

        public Basic_rule_nodes() throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

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
        public Oks init(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                is_optional = false;
                is_repeatable = false;
                is_a_repeat_success_previous_not_now = false;
                is_already_evaluated = false;
                is_first_part_evaluated = false;
                is_once_successed = null;
                if (basic_rule_node != null) {
                    basic_rule_node.init(ok, extras_array);
                    if (ok.is == false) return null;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return ok;
        }

        /**
         *
         * @param basic_rule_node
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public Oks init(Basic_rule_nodes basic_rule_node, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                this.is_optional = basic_rule_node.is_optional;
                this.is_repeatable = basic_rule_node.is_repeatable;
                this.is_a_repeat_success_previous_not_now = basic_rule_node.is_a_repeat_success_previous_not_now;
                this.is_already_evaluated = basic_rule_node.is_already_evaluated;
                this.is_first_part_evaluated = basic_rule_node.is_first_part_evaluated;
                this.is_once_successed = basic_rule_node.is_once_successed;
                if (this.basic_rule_node != null) {
                    this.basic_rule_node.init(basic_rule_node.basic_rule_node, ok, extras_array);
                    if (ok.is == false) return null;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return ok;
        }
        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return true = success, false = fail, null = not totally evaluated or error
         * @throws Exception
         */
        public abstract Boolean evaluate_first_part(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception;

        /**
         *
         * @param basic_token
         * @param ok
         * @param extras_array
         * @return true = success, false = fail, null = not totally evaluated or error
         * @throws Exception
         */
        @Nullable
        public Boolean evaluate(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return false;
            ResourceBundle in = null;
            try {
                Boolean is;
                boolean is_pending = false;
                in = ok.valid(ResourceBundles.getBundle(k_in_route));
                if (is_already_evaluated == true && is_repeatable == false) {
                    ok.setTex(Tr.in(in, "Rule already evaluated. Must do init to reevaluate. "));
                    return null;
                }
                is_a_repeat_success_previous_not_now = false;
                if (is_first_part_evaluated == false) {
                    is = evaluate_first_part(basic_token, ok, extras_array);
                    if (ok.is == false) return false;
                    if (is != null) {
                        if (is == false) {
                            if (is_once_successed == null) {
                                is_once_successed = false;
                            }
                            return false;
                        }
                    } else {
                        is_pending = true;
                    }
                    if (basic_rule_node == null) {
                        is_first_part_evaluated = false; // for repeating
                        is_already_evaluated = true;
                        is_once_successed = true;
                        return true;
                    } else {
                        if (is_pending == false) {
                            is_first_part_evaluated = true;
                        }
                        return null;
                    }
                } else {
                    return evaluate_rule(basic_token, ok, extras_array);
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
        public Boolean evaluate_rule(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return false;
            ResourceBundle in = null;
            try {
                Boolean is;
                boolean is_pending = false;
                in = ok.valid(ResourceBundles.getBundle(k_in_route));
                if (is_already_evaluated == true && is_repeatable == false) {
                    ok.setTex(Tr.in(in, "Rule already evaluated. Must do init to reevaluate. "));
                    return null;
                }
                is_a_repeat_success_previous_not_now = false;
                if (basic_rule_node == null) {
                    ok.setTex(Tr.in(in, "Rule is null. "));
                    return null;
                }
                if (basic_rule_node.is_once_successed == null
                 || (basic_rule_node.is_once_successed
                  && basic_rule_node.is_repeatable)) {
                    is = basic_rule_node.evaluate(basic_token, ok, extras_array);
                    if (ok.is == false) return false;
                    if (is != null) {
                        if (is == false) {
                            if (is_once_successed == null || is_once_successed == false) {
                                if (basic_rule_node.is_repeatable) {
                                    if (basic_rule_node.is_once_successed == null) {
                                        ok.setTex(Tr.in(in, "Not null return evaluating a node whose success state is null . "));
                                        return null;
                                    }
                                    if (basic_rule_node.is_once_successed == false) {
                                        // Previous repeat was failed (not to consider)
                                        is_once_successed = false;
                                    } else {
                                        // Previous repeat was failed but this basic_token does not match
                                        is_a_repeat_success_previous_not_now = true;
                                    }
                                } else {
                                    is_once_successed = false;
                                }
                            }
                            return false;
                        } else {
                            if (basic_rule_node.is_already_evaluated) {
                                is_first_part_evaluated = false; // for repeating
                                is_already_evaluated = true;
                                is_once_successed = true;
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

    public static class Rule_nodes extends Basic_rule_nodes {
        public  Scanner_rules.@Nullable Basic_tokens token = null;

        public Rule_nodes() throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        public Rule_nodes(Basic_rule_nodes basic_rule_node) throws Exception {
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

        @Nullable
        @Override
        public Oks init(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                super.init(ok, extras_array);
                if (ok.is == false) return null;
                if (token != null) {
                    token.init(ok, extras_array);
                    if (ok.is == false) return null;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return ok;
        }

        /**
         *
         * @param rule_node
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        @Nullable
        public Oks init(Rule_nodes rule_node, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                super.init(rule_node, ok, extras_array);
                if (ok.is == false) return null;
                if (token != null) {
                    token.init(rule_node.token, ok);
                    if (ok.is == false) return null;
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return ok;
        }

        @Override
        @Nullable
        public Boolean evaluate_first_part(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return false;
            ResourceBundle in = null;
            try {
                boolean is;
                in = ok.valid(ResourceBundles.getBundle(k_in_route));
                if (is_already_evaluated == true && is_repeatable == false) {
                    ok.setTex(Tr.in(in, "Rule already evaluated. Must do init to reevaluate. "));
                    return null;
                }
                is_first_part_evaluated = true;
                if (token != null) {
                    if (Objects.equals(basic_token.token_type, token.token_type) == false) {
                        if (basic_rule_node == null || basic_rule_node.is_already_evaluated) {
                            is_already_evaluated = true;
                            is_bad_way = true;
                        }
                        return false;
                    }
                    if (token.token_tex != null) {
                        if (Objects.equals(basic_token.token_tex, token.token_tex) == false) {
                            if (basic_rule_node == null || basic_rule_node.is_already_evaluated) {
                                is_already_evaluated = true;
                                is_bad_way = true;
                            }
                            return false;
                        }
                    }
                } else {
                    if (basic_rule_node == null) {
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

    public static class Rule_node_find_ways extends Basic_rule_nodes {
        public List<Basic_rule_nodes> rule_node_list = new ArrayList<>();

        public Rule_node_find_ways() throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        public Rule_node_find_ways(Rule_node_find_ways rule_node_list) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(rule_node_list, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.tex);
            }
        }

        @Nullable
        @Override
        public Oks init(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                super.init(ok, extras_array);
                if (ok.is == false) return null;
                if (rule_node_list != null) {
                    for (var node : rule_node_list) {
                        node.init(ok);
                        if (ok.is == false) return null;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return ok;
        }

        /**
         *
         * @param rule_node_list
         * @param ok
         * @param extras_array
         * @return
         * @throws Exception
         */
        public Oks init(Rule_node_find_ways rule_node_list, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                super.init(rule_node_list, ok, extras_array);
                if (ok.is == false) return null;
                if (rule_node_list != null) {
                    this.rule_node_list.clear();
                    for (var node : rule_node_list.rule_node_list) {
                        this.rule_node_list.add(node.getClass().getConstructor(node.getClass()).newInstance(node));
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return ok;
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
        @Nullable
        public Boolean evaluate_first_part(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return null;
            ResourceBundle in = null;
            try {
                Boolean is;
                boolean is_pending = false;
                boolean is_evaluated = true;
                if (rule_node_list != null && rule_node_list.isEmpty() == false) {
                    for (var node : rule_node_list) {
                        if (node.is_already_evaluated == false
                         && (node.is_once_successed == null
                          || (node.is_once_successed && node.is_repeatable))) {
                            is_evaluated = true;
                            is = node.evaluate(basic_token, ok, extras_array);
                            if (ok.is == false) return null;
                            if (is != null) {
                                if (is == false) {
                                    if (node.is_repeatable) {
                                        if (node.is_a_repeat_success_previous_not_now) {
                                            if (basic_rule_node != null) {
                                                Basic_rule_nodes copy_basic_rule_node
                                                        = basic_rule_node.getClass()
                                                        .getConstructor(basic_rule_node.getClass())
                                                        .newInstance(basic_rule_node);
                                                is_first_part_evaluated = true;
                                                is = evaluate_rule(basic_token, ok, extras_array);
                                                if (ok.is == false) return null;
                                                if (is != null) {
                                                    if (is) {
                                                        is_already_evaluated = true;
                                                        return true;
                                                    } else {
                                                        basic_rule_node = copy_basic_rule_node;
                                                    }
                                                } else {
                                                    is_pending = true;
                                                }
                                            } else {
                                                is_already_evaluated = true;
                                                return true;
                                            }
                                        }
                                    }
                                    if (node.is_optional == false) {
                                        if (basic_rule_node != null) {
                                            Basic_rule_nodes copy_basic_rule_node
                                                    = basic_rule_node.getClass()
                                                    .getConstructor(basic_rule_node.getClass())
                                                    .newInstance(basic_rule_node);
                                            is_first_part_evaluated = true;
                                            is = evaluate_rule(basic_token, ok, extras_array);
                                            if (ok.is == false) return null;
                                            if (is != null) {
                                                if (is) {
                                                    is_already_evaluated = true;
                                                    return true;
                                                } else {
                                                    basic_rule_node = copy_basic_rule_node;
                                                }
                                            } else {
                                                is_pending = true;
                                            }
                                        } else {
                                            is_already_evaluated = true;
                                            return true;
                                        }
                                    }
                                } else {
                                    is_first_part_evaluated = true;
                                    return true; // A rule has matched
                                }
                            } else {
                                is_pending = true;
                            }
                        }
                    }
                    if (is_evaluated) {
                        if (is_pending == false) {
                            is_first_part_evaluated = true;
                            return false; // No rule matching
                        }
                    } else {
                        if (basic_rule_node == null || basic_rule_node.is_already_evaluated) {
                            is_already_evaluated = true;
                            is_bad_way = true;
                            return false; // No rule matching
                        }
                    }
                } else {
                    if (basic_rule_node == null) {
                        ok.setTex(Tr.in(in, "All the requirements are null in node. "));
                        return null;
                    }
                }
            } catch (Exception e) {
                ok.setTex(e);
            }
            return null;
        }
    }

    @Nullable
    public Basic_rule_nodes start_rule_node = null;

}
