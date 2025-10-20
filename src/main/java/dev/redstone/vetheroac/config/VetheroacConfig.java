package dev.redstone.vetheroac.config;

import me.fzzyhmstrs.fzzy_config.annotations.Comment;
import me.fzzyhmstrs.fzzy_config.api.FileType;
import me.fzzyhmstrs.fzzy_config.api.SaveType;
import me.fzzyhmstrs.fzzy_config.config.Config;
import me.fzzyhmstrs.fzzy_config.validation.number.ValidatedInt;
import net.minecraft.util.Identifier;

import static dev.redstone.vetheroac.Vetheroac.MOD_ID;

public class VetheroacConfig extends Config {

    public VetheroacConfig() {
        super(Identifier.of(MOD_ID, "vetheroac"));
    }


    @Comment("Enables physics-based TNT explosion behavior (idk why you would want to turn this off in this mod)")
    @Name("Physics TNT")
    public boolean Physic_Based_TNT = true;

    @Comment("If it trys and bugfixes on join (not needed cus exploding 1 tnt does the same thing)")
    @Name("Bugfix On Join")
    public boolean Do_Bugfix_At_Join = true;

    @Comment("Power Of TNT (0 will still explode just very weak, i would not change this unless you make it higher, and this is not size)")
    @Name("Power TNT: default is 5")
    public ValidatedInt PowerTNT = new ValidatedInt(5, 30, 0);

    @Override
    public int defaultPermLevel() {
        return 4;
    }

    @Override
    public FileType fileType() {
        return FileType.JSON5;
    }

    @Override
    public SaveType saveType() {
        return SaveType.SEPARATE;
    }
}