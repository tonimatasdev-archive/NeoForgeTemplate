package dev.tonimatas.ethylene;

import com.mojang.logging.LogUtils;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.common.Mod;
import org.slf4j.Logger;

@SuppressWarnings("unused")
@Mod(Ethylene.MODID)
public class Ethylene {
    public static final String MODID = "ethylene";
    private static final Logger LOGGER = LogUtils.getLogger();

    public Ethylene(IEventBus modEventBus, ModContainer modContainer) {
        LOGGER.info("ethylene initialized.");
    }
}
