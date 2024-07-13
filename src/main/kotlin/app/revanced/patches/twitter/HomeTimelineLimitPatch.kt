package app.revanced.patches.twitter

import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.extensions.InstructionExtensions.addInstructions
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotation.CompatiblePackage
import app.revanced.patcher.patch.annotation.Patch
import app.revanced.patches.twitter.HomeTimelineLimitFingerprint
import app.revanced.util.exception

@Patch(
    name = "limit the home timeline",
    description = "limit for you tab",
    compatiblePackages = [CompatiblePackage("com.twitter.android")],
)
@Suppress("unused")
object HomeTimelineLimitPatch : BytecodePatch(
    setOf(HomeTimelineLimitFingerprint),
) {
    private const val METHOD_REFERENCE =
        "Lapp/revanced/integrations/twitter/OkHttpClientLimiter;->" +
                "shouldSkipRequest(Lokhttp3/Request;)Z"

    override fun execute(context: BytecodeContext) {
        HomeTimelineLimitFingerprint.result?.mutableMethod?.addInstructions(
            0,
            """
                invoke-static { p1 }, $METHOD_REFERENCE
                move-result v0
                if-eqz v0, :cond_0

                const/4 v1, 0x0
                return-object v1

                :cond_0
            """,
        ) ?: throw HomeTimelineLimitFingerprint.exception
    }
}
