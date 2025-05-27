package innui.code_processor;

import edu.umd.cs.findbugs.annotations.SuppressFBWarnings;
import innui.Bases;
import innui.code_processor.java.Identifiers_table_rules;
import innui.modelos.Modelos;
import innui.modelos.configurations.Initials;
import innui.modelos.configurations.ResourceBundles;
import innui.modelos.errors.Oks;
import innui.modelos.internacionalization.Tr;
import innui.modelos.tests.Test_methods;
import org.apache.commons.cli.*;
import org.checkerframework.checker.fenum.qual.Fenum;
import org.checkerframework.checker.nullness.qual.Nullable;

import java.io.File;
import java.util.List;
import java.util.ResourceBundle;

import static innui.modelos.internacionalization.Tr.in;
import static java.lang.System.exit;

@SuppressFBWarnings({"MS_SHOULD_BE_FINAL", "MS_PKGPROTECT", "PA_PUBLIC_PRIMITIVE_ATTRIBUTE"})
public class Code_processor extends Initials {
    // Properties file for translactions
    private static final long serialVersionUID;
    public static @Fenum("file_path") String k_in_route;
    static {
        serialVersionUID = getSerial_version_uid();
        String paquete_tex = null;
        try {
            paquete_tex = Oks.valide(Code_processor.class.getPackage()).getName();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        if (paquete_tex == null) {
            paquete_tex = "..";
        } else {
            paquete_tex = paquete_tex.replace(".", File.separator);
        }
        Code_processor.k_in_route = Oks.no_fenum_cast("in/" + paquete_tex + "/in");
    }
    static {
        Test_methods.configure(false, false, false);
    }
    public static int k_line_width = 120;
    public static String k_cmdLineSyntax = "cmdLineSyntax";
    // Apache Commons CLI
    public Options options = new Options();
    @SuppressFBWarnings("SE_TRANSIENT_FIELD_NOT_RESTORED")
    public transient CommandLineParser parser = new DefaultParser();
    public org.apache.commons.cli.@Nullable Option analyse_file = null;
    public org.apache.commons.cli.@Nullable Option analyse_rules_download = null;
    public org.apache.commons.cli.@Nullable Option analyse_rules_upload = null;
    public @Nullable Option get_identifiers_table = null;
    public Code_scanners code_scanner;
    public Identifiers_table_rules identifiers_table_rule;
    public Identifiers_table_processors identifiers_table_processor;

    @SuppressWarnings("nullness:initialization.fields.uninitialized")
    public Code_processor() throws Exception {
        Oks ok = (Oks) Bases.objects_map.create_new(Oks.class);
        try {
            code_scanner = new Code_scanners();
            identifiers_table_rule = new Identifiers_table_rules(code_scanner);
            identifiers_table_processor = new Identifiers_table_processors(identifiers_table_rule);
            identifiers_table_processor.load_identifiers_table_rule(ok);
        } catch (Exception e) {
            ok.setTex(e);
        }
        if (ok.is == false) {
            throw new Exception(ok.getTex());
        }
    }

    /**
     * Creates the CLUI options
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public boolean create_options(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        ResourceBundle in = null;
        try {
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            // A
            analyse_file = Option.builder("af")
                    .longOpt("analyse_file")
                    .desc(in(in, "Analyses a java code file to find methods"))
                    .hasArg()
                    .numberOfArgs(1)
                    .argName(in(in, "File_name"))
                    .build();
            options.addOption(analyse_file);
            analyse_rules_download = Option.builder("ard")
                    .longOpt("analyse_rules_download")
                    .desc(in(in, "Download the rules use by the Analyser in YAML format"))
                    .hasArg()
                    .numberOfArgs(1)
                    .argName(in(in, "Download_file_name"))
                    .build();
            options.addOption(analyse_rules_download);
            analyse_rules_upload = Option.builder("aru")
                    .longOpt("analyse_rules_upload")
                    .desc(in(in, "Upload the rules to be used by the Analyser in YAML format"))
                    .hasArg()
                    .optionalArg(true)
                    .numberOfArgs(2)
                    .argName(in(in, "Upload_file_name [Canonical name of a Identifiers_table_after_successes derived class] "))
                    .build();
            options.addOption(analyse_rules_upload);
            // G
            get_identifiers_table = Option.builder("gitam")
                    .longOpt("get_identifiers_table_attributes_methods")
                    .desc(in(in, "Gets the identifiers table (package, attributes and methods) from a java code file"))
                    .hasArg()
                    .numberOfArgs(1)
                    .argName(in(in, "File_name"))
                    .build();
            options.addOption(get_identifiers_table);
        } catch (Exception e) {
            throw e;
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
    public boolean write_help(Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        if (ok.is == false) return false;
        this.write_line(ok.getTex(), ok);
        HelpFormatter helpFormatter = new HelpFormatter();
        String cmdLineSyntax = ok.valid(this.properties.getProperty(k_cmdLineSyntax));
        helpFormatter.setWidth(k_line_width);
        helpFormatter.printHelp(cmdLineSyntax, options);
        return ok.is;
    }

    /**
     * Application init
     * @param args 
     */
     public static void main(String[] args) {
        Oks ok = null;
        try {
            ok = (Oks) Bases.objects_map.create_new(Oks.class);
            ResourceBundle in = null;
            Code_processor code_processor;
            try {
                code_processor = new Code_processor();
                code_processor.run(ok, (java.lang.Object[]) args);
            } catch (Exception e) {
                ok.setTex(e);
            }
        } catch (Exception e) {
            if (ok == null) {
                throw new RuntimeException(e);
            }
            ok.setTex(e);
        }
        if (!ok.is) {
            System.err.println(ok.tex);
            exit(1);
        } else {
            exit(0);
        }
    }

    /**
     * Process CLUI options
     * @param args
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    public boolean process_options_received(String [] args, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok,args, ok, extras_array, this);
        if (ok.is == false) return false;
        ResourceBundle in = null;
        in = ok.valid(ResourceBundles.getBundle(k_in_route));
        switch (0) { default -> {
            try {
                CommandLine commandLine = parser.parse(options, args);
                if (commandLine.getArgList().isEmpty() == false) {
                    this.write_line(in(in, "Unrecognized args"), ok);
                    if (ok.is == false) break;
                    for (String text : commandLine.getArgList()) {
                        this.write_line(text, ok);
                        if (ok.is == false) break;
                    }
                    write_help(ok);
                    if (ok.is == false) break;
                } else {
                    if (commandLine.getOptions().length != 0) {
                        for (Option option : commandLine.getOptions()) {
                            process_option(commandLine, option, ok.init(), (java.lang.Object[]) args);
                            if (ok.is == false) {
                                break;
                            }
                        }
                    } else {
                        ok.setTex(in(in, "No options"));
                    }
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                ok.setTex(e, in(in, "Missing parameters"));
            } catch (MissingArgumentException e) {
                Option option = e.getOption();
                ok.setTex(e, in(in, "Missing some in argument: ")
                        + "--" + option.getLongOpt() + " <"
                        + option.getArgName() + ">");
            } catch (Exception e) {
                ok.setTex(e);
            }
        }}
        if (ok.is == false) {
            this.write_line(ok.getTex(), ok);
            ok.init();
            write_help(ok);
        }
        return ok.is;
    }

    /**
     * Process CLUI options
     * @param commandLine
     * @param option
     * @param ok
     * @param extras_array
     * @return
     * @throws Exception
     */
    @SuppressFBWarnings("SBSC_USE_STRINGBUFFER_CONCATENATION")
    public boolean process_option(CommandLine commandLine, Option option, Oks ok, Object ... extras_array) throws Exception {
        new Test_methods(ok, commandLine, option, ok, extras_array, this);
        if (ok.is == false) return false;
        final ResourceBundle in;
        in = ok.valid(ResourceBundles.getBundle(k_in_route));
        try {
            if (option.getOpt().equals(ok.valid(analyse_file).getOpt())) {
                write_line("--" + option.getLongOpt(), ok);
                if (ok.is == false) return false;
                String file_tex = commandLine.getOptionValues(ok.valid(analyse_file))[0];
                code_scanner.load_file(file_tex, ok, extras_array);
                code_scanner.start_scanner(ok, extras_array);
                ok.valid(this.write_line(Tr.in(in, "Analyse file done. "), ok));
                Yamls yamls = new Yamls();
                ok.valid(yamls.init(ok, extras_array));
                String token_list_yaml = ok.valid(yamls.objectMapper).writeValueAsString(code_scanner._valid_tokens_list);
                ok.valid(this.write_line(token_list_yaml, ok));
                String content = "";
                for (Scanner_rules.Tokens token : code_scanner._valid_tokens_list) {
                    content = content + token.token_tex;
                }
                ok.valid(this.write_line(content, ok));
            } if (option.getOpt().equals(ok.valid(analyse_rules_download).getOpt())) {
                write_line("--" + option.getLongOpt(), ok);
                if (ok.is == false) return false;
                String file_tex = commandLine.getOptionValues(ok.valid(analyse_rules_download))[0];
                File file = new File(file_tex);
                identifiers_table_processor.identifiers_table_rule.analyizer_rule.get_rules_list_yaml(file, ok, extras_array);
                if (ok.is == false) return false;
                write_line(Tr.in(in, "Analyze rules downloaded: ") + file_tex, ok);
            } if (option.getOpt().equals(ok.valid(analyse_rules_upload).getOpt())) {
                write_line("--" + option.getLongOpt(), ok);
                if (ok.is == false) return false;
                String file_tex = commandLine.getOptionValues(ok.valid(analyse_rules_upload))[0];
                String canonical_class_name_tex = null;
                if (commandLine.getOptionValues(ok.valid(analyse_rules_upload)).length > 1) {
                    canonical_class_name_tex = commandLine.getOptionValues(ok.valid(analyse_rules_upload))[1];
                }
                File file = new File(file_tex);
                identifiers_table_processor.identifiers_table_rule.set_rules_list_yaml(file, ok, extras_array);
                if (ok.is == false) return false;
                if (canonical_class_name_tex != null) {
                    identifiers_table_processor.identifiers_table_rule.set_after_success_calls(canonical_class_name_tex, ok, extras_array);
                    if (ok.is == false) return false;
                }
                write_line(Tr.in(in, "Analyze rules uploaded: ") + file_tex, ok);
            } else if (option.getOpt().equals(ok.valid(get_identifiers_table).getOpt())) {
                write_line("--" + option.getLongOpt(), ok);
                if (ok.is == false) return false;
                String file_tex = commandLine.getOptionValues(ok.valid(get_identifiers_table))[0];
                code_scanner.load_file(file_tex, ok, extras_array);
                if (ok.is == false) return false;
                identifiers_table_processor.start(ok, extras_array);
                if (ok.is == false) return false;
                List<Identifiers_tables.Identifiers> identifiers_list;
                identifiers_list = ok.valid(identifiers_table_processor.get_identifiers_table(ok, extras_array));
                if (ok.is == false) return false;
                write_line(Tr.in(in, "Get identifiers table done. "), ok);
                Yamls yamls = new Yamls();
                yamls.init(ok, extras_array);
                if (ok.is == false) return false;
                String token_list_yaml = ok.valid(yamls.objectMapper).writeValueAsString(identifiers_list);
                write_line(token_list_yaml, ok);
                if (ok.is == false) return false;
            } else {
                ok.setTex(in(in, "No options"));
            }
        } catch (Exception e) {
            ok.setTex(e);
        }
        return ok.is;
    }

    @Override
    public boolean run(Oks ok, Object ... extras_array) throws Exception {
        if (ok.is == false) return false;
        try {
            init(ok);
            if (ok.is == false) return false;
            new Test_methods(ok, ok, extras_array, this);
            ResourceBundle in = null;
            // Get internationalization bundle
            in = ok.valid(ResourceBundles.getBundle(k_in_route));
            switch (0) { default -> {
                String [] args = (String []) extras_array;
                create_options(ok);
                if (ok.is == false) break;
                process_options_received(args, ok);
                if (ok.is == false) break;
            }}
            Oks ok_fin = (Oks) Bases.objects_map.create_new(Oks.class);
            terminate(ok_fin);
            if (ok_fin.is == false) {
                ok.addTex(ok_fin.getTex());
            }
            return ok.is;
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public boolean init(Oks ok, Object... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        // Init models for the clases
        // Models: persistent properties (re), internationalization (in),...
        if (ok.is == false) { return ok.is; }
        _init_from_class(Modelos.class, this.getClass(), ok);
        if (ok.is == false) { return ok.is; }
        _init_from_class(this.getClass(), this.getClass(), ok);
        return ok.is;
    }

    @Override
    public boolean terminate(Oks ok, Object... extras_array) throws Exception {
        new Test_methods(ok, ok, extras_array, this);
        // Terminate models for the clases
        // Models: persistent properties (re), internationalization (in),...
        if (ok.is == false) { return ok.is; }
        _terminate(Modelos.class, ok);
        if (ok.is == false) { return ok.is; }
        _terminate(this.getClass(), ok);
        if (ok.is == false) { return ok.is; }
        return ok.is;
    }
    
}
