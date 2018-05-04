package com.gcf.latte_core.app;

import java.util.WeakHashMap;

/**
 * Created by guchenfei
 * on 04/05/18
 */
public class Configurator {

    /*WeakHashMap当里边的键值对不用的时候，自动回收，防止爆满（特别是存储大量东西的时候）*/
    private static final WeakHashMap<String,Object> LATTE_CONFIGS = new WeakHashMap<>();

    private Configurator(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),false);
    }

    private static class Holder{
        private static final Configurator INSTANCE = new Configurator();
    }

    public final void configure(){
        LATTE_CONFIGS.put(ConfigType.CONFIG_READY.name(),true);
    }

    public static Configurator getInstance(){
        return Holder.INSTANCE;
    }

    final WeakHashMap<String,Object> getLatteConfigs(){
        return LATTE_CONFIGS;
    }

    public final Configurator withApiHost(String host){
        LATTE_CONFIGS.put(ConfigType.API_HOST.name(),host);
        return this;
    }

    private void checkConfiguration(){
        final boolean isReady = (boolean) LATTE_CONFIGS.get(ConfigType.CONFIG_READY.name());
        if (!isReady){
            throw new RuntimeException("Configuration is not ready,call configure");
        }
    }

    final <T> T getConfiguration(Enum<ConfigType> key){
        checkConfiguration();
        return (T)LATTE_CONFIGS.get(key.name());
    }
}
