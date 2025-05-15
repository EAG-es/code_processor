package innui.code_processor.java;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.regex.qual.Regex;

import java.io.File;
import java.util.ResourceBundle;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE", "NM_SAME_SIMPLE_NAME_AS_SUPERCLASS"})
public class Scanner_rules extends innui.code_processor.Scanner_rules {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Scanner_rules.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Scanner_rules.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }

    public Scanner_rules() throws Exception {
    }

    public enum States {
        initial,
        keyword_or_identifier,
        number,
        number_operator_or_asignment_or_comment,
        comment_block,
        comment_line,
        compare_operator_or_asignment,
        bitwise_operator_or_asignment,
        anotation,
        not_or_not_equal,
        string,
        character,
        logic_bitwise_operator_or_asignment,
        equal_operator_or_asignment,
        space,
    }

    public enum Token_types {
        token_abstract, token_continue, token_for, token_new, token_switch
        , token_assert, token_default, token_goto, token_package, token_synchronized
        , token_do, token_if, token_private, token_this
        , token_break, token_implements, token_protected, token_throw
        , token_else, token_import, token_public, token_throws
        , token_case, token_enum, token_instanceof, token_return, token_transient
        , token_catch, token_extends, token_try
        , token_final, token_interface, token_static
        , token_class, token_record, token_finally, token_strictfp, token_volatile
        , token_const, token_native, token_super, token_while
        , token_sealed, token_permits
        , type_void, type_boolean, type_char, type_byte, type_short, type_int, type_long, type_float, type_double
        , operator_plus_plus, operator_minus_minus, operator_plus, operator_minus, operator_divide, operator_multiply, operator_module
        , operator_lambda
        , comment_block_begin, comment_block, comment_line_begin, comment_line
        , parenthesis_open, parenthesis_close, brace_open, brace_close, bracket_open, bracket_close
        , dot, comma, semi_colon, colon, question, space
        , constant_integer, constant_integer_long, constant_decimal
        , constant_string, constant_character
        , logic_or, logic_and, logic_not
        , bitwise_or, bitwise_and, bitwise_xor, bitwise_not
        , assignment
        , assignment_xor, assignment_not, assignment_and, assignment_or
        , assignment_bitwise_left, assignment_bitwise_right
        , assignment_plus, assignment_minus, assignment_multiply, assignment_divided, assignment_module
        , sign_less, compare_less_equal, sign_bigger, compare_bigger_equal
        , equal, not_equal
        , identifier, anotation
    }

    public States state = States.initial;

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void reset_state(Oks ok, Object ... extras_array) throws Exception {
        state = States.initial;
    }
    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            if (character == '\n') {
                line_num = line_num + 1;
                col_num = 0;
            }
            switch (state) {
                case initial -> {
                    init_token(pos, ok, extras_array);
                    process_character_initial(character, pos, ok, extras_array);
                }
                case keyword_or_identifier -> {
                    process_character_keyword_or_identifier(character, pos, ok, extras_array);
                }
                case number -> {
                    process_character_number(character, pos, ok, extras_array);
                }
                case number_operator_or_asignment_or_comment -> {
                    process_character_number_operator_or_asignment_or_comment(character, pos, ok, extras_array);
                }
                case comment_line -> {
                    process_character_comment_line(character, pos, ok, extras_array);
                }
                case comment_block -> {
                    process_character_comment_block(character, pos, ok, extras_array);
                }
                case anotation -> {
                    process_character_anotation(character, pos, ok, extras_array);
                }
                case not_or_not_equal -> {
                    process_character_not_or_not_equal(character, pos, ok, extras_array);
                }
                case string -> {
                    process_character_string(character, pos, ok, extras_array);
                }
                case character -> {
                    process_character_character(character, pos, ok, extras_array);
                }
                case logic_bitwise_operator_or_asignment -> {
                    process_character_logic_bitwise_operator_or_asignment(character, pos, ok, extras_array);
                }
                case bitwise_operator_or_asignment -> {
                    process_character_bitwise_operator_or_asignment(character, pos, ok, extras_array);
                }
                case compare_operator_or_asignment -> {
                    process_character_compare_operator_or_asignment(character, pos, ok, extras_array);
                }
                case equal_operator_or_asignment -> {
                    process_character_equal_operator_or_asignment(character, pos, ok, extras_array);
                }
                case space -> {
                    process_character_space(character, pos, ok, extras_array);
                }
                default -> {
                    ok.setTex(Tr.in(ok.valid(in), "Unexpected state before ") + character);
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        col_num = col_num + 1;
        if (Oks.equals(ok.id, k_end_of_toker_out)) {
            col_num = col_num - 1;
        }
    }

    /**
     *
     * @param pos
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public void init_token(Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            token = new Tokens();
            token.start_pos = pos;
            token.col_num = col_num;
            token.line_num = line_num;
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_initial(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            String char_tex = character.toString();
            token.token_tex = token.token_tex + char_tex;
            if (character.compareTo('0') >= 0
                    && character.compareTo('9') <= 0) {
                state = States.number;
            } else if ("+-*/%".contains(char_tex)) {
                state = States.number_operator_or_asignment_or_comment;
            } else if ("<>".contains(char_tex)) {
                state = States.compare_operator_or_asignment;
            } else if ("~^".contains(char_tex)) {
                state = States.bitwise_operator_or_asignment;
            } else if ("&|".contains(char_tex)) {
                state = States.logic_bitwise_operator_or_asignment;
            } else if (character == '"') {
                state = States.string;
            } else if (character == '\'') {
                state = States.character;
            } else if ("=".contains(char_tex)) {
                state = States.equal_operator_or_asignment;
            } else if (character == '@') {
                state = States.anotation;
            } else if (character == '!') {
                state = States.not_or_not_equal;
            } else if (char_tex.isBlank()) {
                state = States.space;
            } else if (character == '(') {
                token.token_type = Token_types.parenthesis_open.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == ')') {
                token.token_type = Token_types.parenthesis_close.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == '{') {
                token.token_type = Token_types.brace_open.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == '}') {
                token.token_type = Token_types.brace_close.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == '[') {
                token.token_type = Token_types.bracket_open.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == ']') {
                token.token_type = Token_types.bracket_close.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == '.') {
                token.token_type = Token_types.dot.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == ',') {
                token.token_type = Token_types.comma.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == ';') {
                token.token_type = Token_types.semi_colon.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == ':') {
                token.token_type = Token_types.colon.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else if (character == '?') {
                token.token_type = Token_types.question.name();
                token.end_pos = pos + 1;
                ok.id = k_end_of_toker_in;
                state = States.initial;
                return;
            } else {
                state = States.keyword_or_identifier;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_keyword_or_identifier(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            States old_state = state;
            States new_state;
            process_character_initial(character, pos, ok, extras_array);
            if (ok.is == false) return;
            new_state = state;
            state = old_state;
            if (new_state != States.keyword_or_identifier && new_state != States.number) {
                token.token_tex = token.token_tex.substring(0, token.token_tex.length()-1);
                identify_keyword(ok, extras_array);
                if (ok.is == false) return;
                token.end_pos = pos - 1;
                state = States.initial;
                ok.id = k_end_of_toker_out;
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
    public void identify_keyword(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            switch (token.token_tex) {
                case "package" -> {
                    token.token_type = Token_types.token_package.name();
                }
                case "import" -> {
                    token.token_type = Token_types.token_import.name();
                }
                case "if" -> {
                    token.token_type = Token_types.token_if.name();
                }
                case "else" -> {
                    token.token_type = Token_types.token_else.name();
                }
                case "while" -> {
                    token.token_type = Token_types.token_while.name();
                }
                case "do" -> {
                    token.token_type = Token_types.token_do.name();
                }
                case "for" -> {
                    token.token_type = Token_types.token_for.name();
                }
                case "switch" -> {
                    token.token_type = Token_types.token_switch.name();
                }
                case "case" -> {
                    token.token_type = Token_types.token_case.name();
                }
                case "default" -> {
                    token.token_type = Token_types.token_default.name();
                }
                case "continue" -> {
                    token.token_type = Token_types.token_continue.name();
                }
                case "break" -> {
                    token.token_type = Token_types.token_break.name();
                }
                case "goto" -> {
                    token.token_type = Token_types.token_goto.name();
                }
                case "try" -> {
                    token.token_type = Token_types.token_try.name();
                }
                case "catch" -> {
                    token.token_type = Token_types.token_catch.name();
                }
                case "finally" -> {
                    token.token_type = Token_types.token_finally.name();
                }
                case "throw" -> {
                    token.token_type = Token_types.token_throw.name();
                }
                case "throws" -> {
                    token.token_type = Token_types.token_throws.name();
                }
                case "return" -> {
                    token.token_type = Token_types.token_return.name();
                }
                case "class" -> {
                    token.token_type = Token_types.token_class.name();
                }
                case "interface" -> {
                    token.token_type = Token_types.token_interface.name();
                }
                case "enum" -> {
                    token.token_type = Token_types.token_enum.name();
                }
                case "record" -> {
                    token.token_type = Token_types.token_record.name();
                }
                case "extends" -> {
                    token.token_type = Token_types.token_extends.name();
                }
                case "implements" -> {
                    token.token_type = Token_types.token_implements.name();
                }
                case "sealed" -> {
                    token.token_type = Token_types.token_sealed.name();
                }
                case "permits" -> {
                    token.token_type = Token_types.token_permits.name();
                }
                case "public" -> {
                    token.token_type = Token_types.token_public.name();
                }
                case "private" -> {
                    token.token_type = Token_types.token_private.name();
                }
                case "protected" -> {
                    token.token_type = Token_types.token_protected.name();
                }
                case "abstract" -> {
                    token.token_type = Token_types.token_abstract.name();
                }
                case "static" -> {
                    token.token_type = Token_types.token_static.name();
                }
                case "final" -> {
                    token.token_type = Token_types.token_final.name();
                }
                case "volatile" -> {
                    token.token_type = Token_types.token_volatile.name();
                }
                case "transient" -> {
                    token.token_type = Token_types.token_transient.name();
                }
                case "native" -> {
                    token.token_type = Token_types.token_native.name();
                }
                case "const" -> {
                    token.token_type = Token_types.token_const.name();
                }
                case "strictfp" -> {
                    token.token_type = Token_types.token_strictfp.name();
                }
                case "synchronized" -> {
                    token.token_type = Token_types.token_synchronized.name();
                }
                case "assert" -> {
                    token.token_type = Token_types.token_assert.name();
                }
                case "new" -> {
                    token.token_type = Token_types.token_new.name();
                }
                case "instanceof" -> {
                    token.token_type = Token_types.token_instanceof.name();
                }
                case "this" -> {
                    token.token_type = Token_types.token_this.name();
                }
                case "super" -> {
                    token.token_type = Token_types.token_super.name();
                }
                case "void" -> {
                    token.token_type = Token_types.type_void.name();
                }
                case "boolean" -> {
                    token.token_type = Token_types.type_boolean.name();
                }
                case "byte" -> {
                    token.token_type = Token_types.type_byte.name();
                }
                case "char" -> {
                    token.token_type = Token_types.type_char.name();
                }
                case "short" -> {
                    token.token_type = Token_types.type_short.name();
                }
                case "int" -> {
                    token.token_type = Token_types.type_int.name();
                }
                case "long" -> {
                    token.token_type = Token_types.type_long.name();
                }
                case "float" -> {
                    token.token_type = Token_types.type_float.name();
                }
                case "double" -> {
                    token.token_type = Token_types.type_double.name();
                }
                default -> {
                    token.token_type = Token_types.identifier.name();
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_number(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            String char_tex = character.toString();
            if ("1234567890.+-eElL".contains(char_tex)) {
                token.token_tex = token.token_tex + character;
            } else {
                token.end_pos = pos - 1;
                state = States.initial;
                final @Regex String integer_reg = "^[+-]?\\d{1,10}$";
                final @Regex String long_reg = "^[+-]?\\d{1,19}[Ll]$";
                final @Regex String decimal_reg = "^[+-]?\\d{1,19}(?:\\.\\d{1,19})?(?:[eE][+-]?\\d{1,19})?$";
                if (token.token_tex.matches(integer_reg)) {
                    token.token_type = Token_types.constant_integer.name();
                } else if (token.token_tex.matches(long_reg)) {
                    token.token_type = Token_types.constant_integer_long.name();
                } else if (token.token_tex.matches(decimal_reg)) {
                    token.token_type = Token_types.constant_decimal.name();
                } else {
                    ok.setTex(Tr.in(in, "Number format not valid. "));
                    return;
                }
                ok.id = k_end_of_toker_out;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_number_operator_or_asignment_or_comment(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            if (character == '+') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("++")) {
                    token.token_type = Token_types.operator_plus_plus.name();
                    token.end_pos = pos;
                    ok.id = k_end_of_toker_in;
                    state = States.initial;
                } else {
                    ok.setTex(Tr.in(in, "Operator format not valid. "));
                    return;
                }
            } else if (character == '-') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("--")) {
                    token.token_type = Token_types.operator_minus_minus.name();
                    token.end_pos = pos;
                    ok.id = k_end_of_toker_in;
                    state = States.initial;
                } else {
                    ok.setTex(Tr.in(in, "Operator format not valid. "));
                    return;
                }
            } else if (character == '*') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("/*")) {
                    token.token_type = Token_types.comment_block_begin.name();
                    state = States.comment_block;
                } else {
                    ok.setTex(Tr.in(in, "Operator format not valid. "));
                    return;
                }
            } else if (character == '/') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("//")) {
                    token.token_type = Token_types.comment_line_begin.name();
                    state = States.comment_line;
                } else {
                    ok.setTex(Tr.in(in, "Operator format not valid. "));
                    return;
                }
            } else if (character == '>') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("->")) {
                    token.token_type = Token_types.operator_lambda.name();
                    token.end_pos = pos;
                    ok.id = k_end_of_toker_in;
                    state = States.initial;
                } else {
                    ok.setTex(Tr.in(in, "Operator format not valid. "));
                    return;
                }
            } else if (character == '=') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("+=")) {
                    token.token_type = Token_types.assignment_plus.name();
                } else if (token.token_tex.equals("-=")) {
                    token.token_type = Token_types.assignment_minus.name();
                } else if (token.token_tex.equals("*=")) {
                    token.token_type = Token_types.assignment_multiply.name();
                } else if (token.token_tex.equals("/=")) {
                    token.token_type = Token_types.assignment_divided.name();
                } else if (token.token_tex.equals("%=")) {
                    token.token_type = Token_types.assignment_module.name();
                } else {
                    ok.setTex(Tr.in(in, "Operator format not valid. "));
                    return;
                }
                token.end_pos = pos;
                ok.id = k_end_of_toker_in;
                state = States.initial;
            } else {
                if (token.token_tex.equals("+")) {
                    token.token_type = Token_types.operator_plus.name();
                } else if (token.token_tex.equals("-")) {
                    token.token_type = Token_types.operator_minus.name();
                } else if (token.token_tex.equals("*")) {
                    token.token_type = Token_types.operator_multiply.name();
                } else if (token.token_tex.equals("/")) {
                    token.token_type = Token_types.operator_divide.name();
                } else if (token.token_tex.equals("%")) {
                    token.token_type = Token_types.operator_module.name();
                } else {
                    ok.setTex(Tr.in(in, "Operator format not valid. "));
                    return;
                }
                token.end_pos = pos - 1;
                ok.id = k_end_of_toker_out;
                state = States.initial;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_comment_line(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            token.token_tex = token.token_tex + character;
            if (character == '\n') {
                token.token_type = Token_types.comment_line.name();
                token.end_pos = pos;
                ok.id = k_end_of_toker_in;
                state = States.initial;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_comment_block(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            token.token_tex = token.token_tex + character;
            if (character == '/') {
                if (token.token_tex.endsWith("*/")) {
                    token.token_type = Token_types.comment_block.name();
                    token.end_pos = pos;
                    ok.id = k_end_of_toker_in;
                    state = States.initial;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_anotation(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            States old_state = state;
            States new_state;
            process_character_initial(character, pos, ok, extras_array);
            if (ok.is == false) return;
            new_state = state;
            state = old_state;
            if (new_state != States.keyword_or_identifier && new_state != States.number) {
                token.token_tex = token.token_tex.substring(0, token.token_tex.length()-1);
                token.token_type = Token_types.anotation.name();
                token.end_pos = pos - 1;
                state = States.initial;
                ok.id = k_end_of_toker_out;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_string(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            token.token_tex = token.token_tex + character;
            if (character == '"') {
                if (token.token_tex.endsWith("\\\"") == false) {
                    token.token_type = Token_types.constant_string.name();
                    token.end_pos = pos;
                    state = States.initial;
                    ok.id = k_end_of_toker_in;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_character(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            token.token_tex = token.token_tex + character;
            if (character == '\'') {
                @Regex String character_reg = "^'(.|\\\\[tbnrf'\"\\\\]|\\\\u[0-9ABCDEF]{4})'$";
                token.token_type = Token_types.constant_character.name();
                if (token.token_tex.matches(character_reg)) {
                    token.end_pos = pos;
                    state = States.initial;
                    ok.id = k_end_of_toker_in;
                } else {
                    ok.setTex(Tr.in(in, "Character format not valid. "));
                    return;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_space(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            if (character.toString().isBlank() == false) {
                token.token_type = Token_types.space.name();
                token.end_pos = pos - 1;
                state = States.initial;
                ok.id = k_end_of_toker_out;
            } else {
                token.token_tex = token.token_tex + character;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_logic_bitwise_operator_or_asignment(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            String char_tex = character.toString();
            token.token_tex = token.token_tex + character;
            if ("&|".contains(char_tex)) {
                if (token.token_tex.equals("||")) {
                    token.token_type = Token_types.logic_or.name();
                    token.end_pos = pos;
                    state = States.initial;
                    ok.id = k_end_of_toker_in;
                } else {
                    token.token_type = Token_types.logic_and.name();
                    token.end_pos = pos;
                    state = States.initial;
                    ok.id = k_end_of_toker_in;
                }
            } else {
                if (character == '=') {
                    if (token.token_tex.equals("|=")) {
                        token.token_type = Token_types.assignment_or.name();
                        token.end_pos = pos;
                        state = States.initial;
                        ok.id = k_end_of_toker_in;
                    } else if (token.token_tex.equals("&=")) {
                        token.token_type = Token_types.assignment_and.name();
                        token.end_pos = pos;
                        state = States.initial;
                        ok.id = k_end_of_toker_in;
                    }
                    token.end_pos = pos;
                    state = States.initial;
                    ok.id = k_end_of_toker_in;
                } else {
                    token.token_tex = token.token_tex.substring(0, token.token_tex.length()-1);
                    if (token.token_tex.equals("|")) {
                        token.token_type = Token_types.bitwise_or.name();
                    } else {
                        token.token_type = Token_types.bitwise_and.name();
                    }
                    token.end_pos = pos - 1;
                    state = States.initial;
                    ok.id = k_end_of_toker_out;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_bitwise_operator_or_asignment(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            if (character == '=') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("^=")) {
                    token.token_type = Token_types.assignment_xor.name();
                    token.end_pos = pos;
                    state = States.initial;
                    ok.id = k_end_of_toker_in;
                } else if (token.token_tex.equals("~=")) {
                    token.token_type = Token_types.assignment_not.name();
                    token.end_pos = pos;
                    state = States.initial;
                    ok.id = k_end_of_toker_in;
                } else {
                    ok.setTex(Tr.in(in, "Bitwise format not valid. "));
                    return;
                }
            } else {
                if (token.token_tex.equals("^")) {
                    token.token_type = Token_types.bitwise_xor.name();
                } else if (token.token_tex.equals("~")) {
                    token.token_type = Token_types.bitwise_not.name();
                } else {
                    ok.setTex(Tr.in(in, "Bitwise format not valid. "));
                    return;
                }
                token.end_pos = pos - 1;
                state = States.initial;
                ok.id = k_end_of_toker_out;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_not_or_not_equal(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            if (character == '=') {
                token.token_tex = token.token_tex + character;
                token.token_type = Token_types.not_equal.name();
                token.end_pos = pos;
                state = States.initial;
                ok.id = k_end_of_toker_in;
            } else {
                token.token_type = Token_types.logic_not.name();
                token.end_pos = pos - 1;
                state = States.initial;
                ok.id = k_end_of_toker_out;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_compare_operator_or_asignment(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            if (character == '=') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("<<=")) {
                    token.token_type = Token_types.assignment_bitwise_left.name();
                } else if (token.token_tex.equals(">>=")) {
                    token.token_type = Token_types.assignment_bitwise_right.name();
                } else if (token.token_tex.equals(">=")) {
                    token.token_type = Token_types.compare_bigger_equal.name();
                } else if (token.token_tex.equals("<=")) {
                    token.token_type = Token_types.compare_less_equal.name();
                } else {
                    ok.setTex(Tr.in(in, "Bitwise asignment or compare format not valid. "));
                    return;
                }
                token.end_pos = pos;
                state = States.initial;
                ok.id = k_end_of_toker_in;
            } else {
                if (character != '<' && character != '>') {
                    if (token.token_tex.equals(">")) {
                        token.token_type = Token_types.sign_bigger.name();
                    } else if (token.token_tex.equals("<")) {
                        token.token_type = Token_types.sign_less.name();
                    }
                    token.end_pos = pos - 1;
                    state = States.initial;
                    ok.id = k_end_of_toker_out;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param character
     * @param pos
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void process_character_equal_operator_or_asignment(Character character, Integer pos, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            if (character == '=') {
                token.token_tex = token.token_tex + character;
                if (token.token_tex.equals("==")) {
                    token.token_type = Token_types.equal.name();
                } else {
                    ok.setTex(Tr.in(in, "Equal operator or asignment format not valid. "));
                    return;
                }
                token.end_pos = pos;
                state = States.initial;
                ok.id = k_end_of_toker_in;
            } else {
                token.token_type = Token_types.assignment.name();
                token.end_pos = pos - 1;
                state = States.initial;
                ok.id = k_end_of_toker_out;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

}


