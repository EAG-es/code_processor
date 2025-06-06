package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.LinkedList;

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

    public static class Identifiers implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public String name = "";
        public String type = "";
        public String namespace = "";
        public ArrayList<Scanner_rules.Basic_tokens> properties_list = new ArrayList<>();
        public String return_class = "";

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Identifiers() throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.getTex());
            }
        }

        @SuppressFBWarnings("CT_CONSTRUCTOR_THROW")
        @SuppressWarnings("nullness:method.invocation")
        public Identifiers(Identifiers identifier) throws Exception {
            Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
            try {
                init(identifier, ok);
            } catch (Exception e) {
                ok.setTex(e);
            }
            if (ok.is == false) {
                throw new Exception(ok.getTex());
            }
        }

        public void init(Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            try {
                name = "";
                type = "";
                namespace = "";
                properties_list.clear();
                return_class = "";
            } catch (Exception e) {
                ok.setTex(e);
            }
        }

        /**
         *
         * @param identifier
         * @param ok
         * @param extras_array
         * @throws Exception
         */
        public void init(Identifiers identifier, Oks ok, Object ... extras_array) throws Exception {
            new Test_methods(ok, ok, extras_array, this);
            if (ok.is == false) return;
            try {
                name = identifier.name;
                type = identifier.type;
                namespace = identifier.namespace;
                properties_list.clear();
                properties_list.addAll(identifier.properties_list);
                return_class = identifier.return_class;
            } catch (Exception e) {
                ok.setTex(e);
            }
        }
    }

    public static class Temporary_identifiers_tables implements Serializable {
        private static final long serialVersionUID = getSerial_version_uid();
        public Integer braces_num = -1;
        public @Nullable Identifiers block_identifier = null;
        public LinkedHashMap<String, Identifiers> current_identifiers_map = new LinkedHashMap<>();
    }
    public LinkedList<Temporary_identifiers_tables> _identifiers_maps_list = new LinkedList<>();
    public Identifiers new_identifier = new Identifiers();

    public Identifiers_tables() throws Exception {
    }

    /**
     * @param braces_num
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public void create_top_table(Integer braces_num, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return;
        try {
            Temporary_identifiers_tables temporary_identifiers_table = new Temporary_identifiers_tables();
            temporary_identifiers_table.braces_num = braces_num;
            _identifiers_maps_list.addFirst(temporary_identifiers_table);
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
    public @Nullable Temporary_identifiers_tables get_top(Oks ok, Object ... extras_array) throws Exception {
        if (this._identifiers_maps_list.isEmpty()) {
            return null;
        }
        return this._identifiers_maps_list.getFirst();
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @throws Exception
     */
    public @Nullable Identifiers put_identifier(Oks ok, Object ... extras_array) throws Exception {
        return put_identifier(false, ok, extras_array);
    }
    /**
     *
     * @param is_block_identifier
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public @Nullable Identifiers put_identifier(boolean is_block_identifier, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        try {
            Identifiers identifier = null;
            identifier = new Identifiers(new_identifier);
            Temporary_identifiers_tables temporaryIdentifiersTables = _identifiers_maps_list.getFirst();
            if (is_block_identifier) {
                temporaryIdentifiersTables.block_identifier = new Identifiers_tables.Identifiers(new_identifier);
            } else {
                if (temporaryIdentifiersTables.block_identifier != null) {
                    identifier.namespace = ok.valid(temporaryIdentifiersTables.block_identifier).namespace;
                    if (ok.valid(temporaryIdentifiersTables.block_identifier).name.isEmpty() == false) {
                        if (identifier.namespace.isEmpty()) {
                            identifier.namespace = ok.valid(temporaryIdentifiersTables.block_identifier).name;
                        } else {
                            identifier.namespace = identifier.namespace
                                    + "." + ok.valid(temporaryIdentifiersTables.block_identifier).name;
                        }
                    }
                }
            }
            _identifiers_maps_list.getFirst().current_identifiers_map.put(identifier.name, identifier);
            new_identifier.init(ok, extras_array);
            if (ok.is == false) return null;
            return identifier;
        } catch (Exception e) {
            ok.setTex(e);
            return null;
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
