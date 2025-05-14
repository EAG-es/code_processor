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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.ResourceBundle;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public class Identifiers_tables extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Identifiers_tables.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_tables.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }

    public Identifiers_tables() throws Exception {
    }

    public static class Identifiers implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public String name = "";
        public String type = "";
        public String namespace = "";
        public ArrayList<Scanner_rules.Basic_tokens> properties_list = new ArrayList<>();
        public ArrayList<Identifiers> parameters_list = new ArrayList<>();
        public @Nullable Identifiers declaration_scope_identifier = null;

    }

    public static class Temporary_identifiers_tables implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public @Nullable Integer braces_num = null;
        public LinkedHashMap<String, Identifiers> current_identifiers_map = new LinkedHashMap<>();
    }
    public LinkedList<Temporary_identifiers_tables> _identifiers_maps_list = new LinkedList<>();
    public Identifiers new_identifier = Oks.allow_nulle(null);
    public @Nullable Temporary_identifiers_tables current_temporary_identifiers_table = null;
    public int _current_temporary_identifiers_table_pos = 0;

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void create_top_table(Integer braces_num, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            current_temporary_identifiers_table = new Temporary_identifiers_tables();
            current_temporary_identifiers_table.braces_num = braces_num;
            _identifiers_maps_list.addFirst(current_temporary_identifiers_table);
            _current_temporary_identifiers_table_pos = _identifiers_maps_list.size() - 1;
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param braces_num If braces_num equals top.braces_num then delete top map
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void delete_top_table(Integer braces_num, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            if (braces_num.equals(_identifiers_maps_list.getFirst().braces_num)) {
                delete_top_table(ok, extras_array);
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param braces_num
     * @param ok
     * @param extras_array
     * @return true if the condition to deleto top table are asserted
     * @throws Exception
     */
    public boolean be_to_delete_top_table(Integer braces_num, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        try {
            if (braces_num.equals(_identifiers_maps_list.getFirst().braces_num)) {
                return true;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        return false;
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void delete_top_table(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            _identifiers_maps_list.pollFirst();
            current_temporary_identifiers_table = _identifiers_maps_list.getFirst();
            _current_temporary_identifiers_table_pos = _identifiers_maps_list.size() - 1;
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
    public LinkedHashMap<String, Identifiers_tables.Identifiers> get_top(Oks ok, Object ... extras_array) throws Exception {
        return this._identifiers_maps_list.getFirst().current_identifiers_map;
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void set_current_up(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            _current_temporary_identifiers_table_pos = _current_temporary_identifiers_table_pos + 1;
            if (_current_temporary_identifiers_table_pos >= _identifiers_maps_list.size()) {
                _current_temporary_identifiers_table_pos = _current_temporary_identifiers_table_pos - 1;
                ok.setTex(Tr.in(in, "Top reached"));
                return;
            }
            current_temporary_identifiers_table = _identifiers_maps_list.get(_current_temporary_identifiers_table_pos);
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
    public void set_current_down(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            _current_temporary_identifiers_table_pos = _current_temporary_identifiers_table_pos - 1;
            if (_current_temporary_identifiers_table_pos < 0) {
                _current_temporary_identifiers_table_pos = _current_temporary_identifiers_table_pos + 1;
                ok.setTex(Tr.in(in, "Bottom reached"));
                return;
            }
            current_temporary_identifiers_table = _identifiers_maps_list.get(_current_temporary_identifiers_table_pos);
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param identifier
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void put_identifier(Identifiers identifier, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            ok.valid(current_temporary_identifiers_table).current_identifiers_map.put(identifier.name, identifier);
        } catch (Exception e) {
            ok.setTex(e);
        }
    }

    /**
     *
     * @param name
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public @Nullable Identifiers get_identifier(String name, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Identifiers retorno = null;
        try {
            for (var identifiers_map: _identifiers_maps_list) {
                retorno = identifiers_map.current_identifiers_map.get(name);
                if (retorno != null) {
                    break;
                }
            }
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return retorno;
    }
}
