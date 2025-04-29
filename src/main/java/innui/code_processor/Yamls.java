package innui.code_processor;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.dataformat.yaml.YAMLGenerator;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import innui.Bases;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.NonNull;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.*;
import java.nio.file.Files;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;

/**
 *
 * @author emilio
 */
public class Yamls extends Bases {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = ((@NonNull Package) Yamls.class.getPackage()).getName();
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Yamls.k_in_route = (@Fenum("file_path") String) ("in/" + paquete_tex + "/in");
    }
    @Nullable
    public transient ObjectMapper objectMapper = null;

    /**
     *
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public boolean init(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        String retorno = null;
//        ResourceBundle in;
//        in = ResourceBundles.getBundle(k_in_route);
        try {
            if (objectMapper == null) {
                _default_builder(ok);
                if (ok.is == false) return false;
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        return ok.is;
    }

    /**
     *
      * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public boolean _default_builder(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        objectMapper = YAMLMapper.builder()
                .disable(YAMLGenerator.Feature.WRITE_DOC_START_MARKER)
                .disable(YAMLGenerator.Feature.ALWAYS_QUOTE_NUMBERS_AS_STRINGS)
                .enable(YAMLGenerator.Feature.INDENT_ARRAYS)
                .enable(YAMLGenerator.Feature.INDENT_ARRAYS_WITH_INDICATOR)
                .build();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ"); // "yyyy-MM-dd'T'HH:mm:ssZ (+S'ms')");
        objectMapper.setDateFormat(dateFormat);
        return ok.is;
    }

    /**
     *
     * @param file
     * @param _class
     * @param ok
     * @param extras_array
     * @return
     * @param <T>
     * @throws Exception
     */
    @Nullable
    public <T> T open_and_read_file(File file, Class<T> _class, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, file, _class, ok, extras_array, this);
        if (ok.is == false) return null;
        T retorno = null;
        ResourceBundle in;
        try {            
            in = ResourceBundles.getBundle(Oks.no_fenum_cast(k_in_route));
            if (objectMapper == null) {
                _default_builder(ok);
                if (!ok.is) { return null; }
            }
            if (!file.exists()) {
                ok.setTex(Tr.in(ok.valid(in), "File does not exists: ")
                  + file.getCanonicalPath());
            } else {
                if (!file.canRead()) {
                    ok.setTex(Tr.in(ok.valid(in), "File does not have read permit: ")
                      + file.getCanonicalPath());
                } 
            }
            if (!ok.is) { return null; }
            try (InputStream inputStream = new FileInputStream(file)) {
                retorno = ok.valid(objectMapper).readValue(inputStream, _class);
            }
        } catch (Exception e) {
            ok.setTex(e);            
        }
        return retorno;
    }

    /**
     *
     * @param file
     * @param data
     * @param ok
     * @param extras_array
     * @return
     * @param <T>
     * @throws Exception
     */
    public <T extends Object> boolean open_and_write_file(File file, T data, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, file, data, ok, extras_array, this);
        if (!ok.is) { return false; }
        ResourceBundle in;
        try {
            in = ResourceBundles.getBundle(Oks.no_fenum_cast(k_in_route));
            if (objectMapper == null) {
                _default_builder(ok);
                if (!ok.is) { return false; }
            }
            if (!file.exists()) {
                ok.setTex(Tr.in(ok.valid(in), "File does not exists: ")
                  + file.getCanonicalPath());
            } else {
                if (!file.canWrite()) {
                    ok.addTex(Tr.in(ok.valid(in), "File does not have write permit: ")
                      + file.getCanonicalPath());
                }
            }
            if (!ok.is) { return false; }
            try (OutputStream outputStream = new FileOutputStream(file)) {
                ok.valid(objectMapper).writeValue(outputStream, data);
            }
        } catch (Exception e) {
            ok.setTex(e);            
        }
        return ok.is;
    }

    /**
     *
     * @param data
     * @param ok
     * @param extras_array
     * @return
     * @param <T>
     * @throws Exception
     */
    @Nullable
    public <T extends Object> String do_to_string(T data, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, data, ok, extras_array, this);
        if (!ok.is) { return null; }
        String retorno = null;
//        ResourceBundle in;
//        in = ResourceBundles.getBundle(k_in_route);
        try {
            if (objectMapper == null) {
                _default_builder(ok);
                if (!ok.is) { return null; }
            }
            retorno = ok.valid(objectMapper).writeValueAsString(data);
        } catch (Exception e) {
            ok.setTex(e);            
        }
        return retorno;
    }

    /**
     *
     * @param file
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public byte @Nullable [] open_and_read_file(File file, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, file, ok, extras_array, this);
        if (ok.is == false) return null;
        byte [] retorno = null;
        ResourceBundle in;
        try {
            in = ResourceBundles.getBundle(Oks.no_fenum_cast(k_in_route));
            if (objectMapper == null) {
                _default_builder(ok);
                if (!ok.is) { return null; }
            }
            if (!file.exists()) {
                ok.setTex(Tr.in(ok.valid(in), "File does not exists: ")
                        + file.getCanonicalPath());
            } else {
                if (!file.canRead()) {
                    ok.setTex(Tr.in(ok.valid(in), "File does not have read permit: ")
                            + file.getCanonicalPath());
                }
            }
            if (!ok.is) { return null; }
            retorno = Files.readAllBytes(file.toPath());
        } catch (Exception e) {
            ok.setTex(e);
        }
        return retorno;
    }

}
