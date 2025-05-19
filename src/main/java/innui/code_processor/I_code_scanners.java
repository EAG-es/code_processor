package innui.code_processor;

import innui.modelos.errors.Oks;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.Serializable;

public interface I_code_scanners extends Serializable {
    interface Validators extends Serializable {
        boolean validate_token(boolean is_new_token, Scanner_rules.Basic_tokens token, Oks ok, Object ... extras_array) throws Exception;
    }

    interface Analyzer extends Serializable {
        void analyze_token(Scanner_rules.Basic_tokens token, Oks ok, Object ... extras_array) throws Exception;
    }
    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    Scanner_rules.@Nullable Basic_tokens scan_next(Oks ok, Object ... extras_array) throws Exception;
    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    Integer get_tokens_list_pos(Oks ok, Object ... extras_array) throws Exception;

    /**
     *
     * @param token_list_pos
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    void set_tokens_list_pos(Integer token_list_pos, Oks ok, Object ... extras_array) throws Exception;

    /**
     *
     * @param validator
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    void set_tokens_validator(I_code_scanners.Validators validator, Oks ok, Object ... extras_array) throws Exception;

    /**
     *
     * @param analizer
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    void set_tokens_analizer(Analyzer analizer, Oks ok, Object ... extras_array) throws Exception;

    /**
     *
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void start_scanner(Oks ok, Object ... extras_array) throws Exception;

}
