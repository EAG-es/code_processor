package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.modelos.errors.Oks;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

@SuppressFBWarnings("PA_PUBLIC_PRIMITIVE_ATTRIBUTE")
public class Identifiers_table_after_successes implements Serializable {

    @FunctionalInterface
    public interface After_success_calls extends Serializable {
        void call(Scanner_rules.Basic_tokens basic_token, Oks ok, Object ... extras_array) throws Exception;
    }

    public @Nullable Identifiers_table_rules _identifiers_table_rule;

    public Map<String, After_success_calls> _after_success_calls_map = new HashMap<>();

    public void set_identifiers_table(Identifiers_table_rules _identifiers_table_rule) {
        this._identifiers_table_rule = _identifiers_table_rule;
    }

    public Map<String, After_success_calls> get_after_success_calls_map() {
        return _after_success_calls_map;
    }
}
