package app.revanced.patches.youtube.video.speed.custom

import app.revanced.patcher.fingerprint.methodFingerprint
import app.revanced.util.literal
import com.android.tools.smali.dexlib2.AccessFlags
import com.android.tools.smali.dexlib2.Opcode

internal val getOldPlaybackSpeedsFingerprint = methodFingerprint {
    parameters("[L", "I")
    strings("menu_item_playback_speed")
}

internal val showOldPlaybackSpeedMenuFingerprint = methodFingerprint {
    literal { speedUnavailableId }
}

internal val showOldPlaybackSpeedMenuIntegrationsFingerprint = methodFingerprint {
    custom { method, _ -> method.name == "showOldPlaybackSpeedMenu" }
}

internal val speedArrayGeneratorFingerprint = methodFingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.STATIC)
    returns("[L")
    parameters("Lcom/google/android/libraries/youtube/innertube/model/player/PlayerResponseModel;")
    opcodes(
        Opcode.IF_NEZ,
        Opcode.SGET_OBJECT,
        Opcode.GOTO_16,
        Opcode.INVOKE_INTERFACE,
        Opcode.MOVE_RESULT_OBJECT,
        Opcode.IGET_OBJECT,
    )
    strings("0.0#")
}

internal val speedLimiterFingerprint = methodFingerprint {
    accessFlags(AccessFlags.PUBLIC, AccessFlags.FINAL)
    returns("V")
    parameters("F")
    opcodes(
        Opcode.INVOKE_STATIC,
        Opcode.MOVE_RESULT,
        Opcode.IF_EQZ,
        Opcode.CONST_HIGH16,
        Opcode.GOTO,
        Opcode.CONST_HIGH16,
        Opcode.CONST_HIGH16,
        Opcode.INVOKE_STATIC,
    )
}