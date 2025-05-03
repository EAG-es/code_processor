package innui.code_processor;

import innui.Bases;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.io.Serializable;
import java.util.*;

public class Identifiers_tables extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Identifiers_tables.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Identifiers_tables.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }
    public static class Identifiers implements Serializable {
        public String name = "";
        public String type = "";
        public String namespace = "";
        public List<Identifiers> properties_list = new ArrayList<>();
        public List<Identifiers> parameters_list = new ArrayList<>();
        @Nullable
        public Identifiers declaration_scope_identifier = null;

    }

    public Identifiers new_identifier;
    public Deque<LinkedHashMap<String, Identifiers>> identifiers_maps_list = new LinkedList<>();

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public Oks create_top_map(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            identifiers_maps_list.addFirst(new LinkedHashMap<>());
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public Oks delete_top_map(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            identifiers_maps_list.pollFirst();
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }

    /**
     *
     * @param identifier
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public Oks put_identifier(Identifiers identifier, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            identifiers_maps_list.peek().put(identifier.name, identifier);
        } catch (Exception e) {
            ok.setTex(e);
            return null;
        }
        return ok;
    }

    /**
     *
     * @param name
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @Nullable
    public Identifiers get_identifier(String name, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return null;
        Identifiers retorno = null;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            for (var identifiers_map: identifiers_maps_list) {
                retorno = identifiers_map.get(name);
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
