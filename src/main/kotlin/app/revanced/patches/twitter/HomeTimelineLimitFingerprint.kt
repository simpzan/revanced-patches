package app.revanced.patches.twitter

import app.revanced.patcher.fingerprint.MethodFingerprint

internal object HomeTimelineLimitFingerprint : MethodFingerprint(
    returnType = "Lokhttp3/Call;",
    customFingerprint = { methodDef, _ ->
        methodDef.definingClass == "Lokhttp3/OkHttpClient;" && methodDef.name == "newCall"
    }
)
