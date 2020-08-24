package ir.logicfan.core.data.sharedpreferences

import android.annotation.SuppressLint
import android.content.Context
import android.content.SharedPreferences
import android.provider.Settings
import android.util.Base64
import java.nio.charset.Charset
import javax.crypto.Cipher
import javax.crypto.SecretKeyFactory
import javax.crypto.spec.PBEKeySpec
import javax.crypto.spec.PBEParameterSpec

/**
 * Warning, this gives a false sense of security.  If an attacker has enough access to
 * acquire your password store, then he almost certainly has enough access to acquire your
 * source binary and figure out your encryption key.  However, it will prevent casual
 * investigators from acquiring passwords, and thereby may prevent undesired negative
 * publicity.
 * Don't use anything you wouldn't want to
 * get out there if someone decompiled
 * your app.
 */
abstract class SecureSharedPreferences(
    private val cipherSecret: CharArray,
    private val context: Context,
    private val delegate: SharedPreferences
) :
    SharedPreferences {

    companion object {
        private const val UTF8 = "utf-8"
        private const val SECRET_KEY_FACTORY = "PBEWithMD5AndDES"
    }

    inner class Editor @SuppressLint("CommitPrefEdits")
    internal constructor() : SharedPreferences.Editor {

        private val delegate: SharedPreferences.Editor = this@SecureSharedPreferences.delegate.edit()

        override fun putBoolean(key: String, value: Boolean): Editor {
            delegate.putString(key, encrypt(java.lang.Boolean.toString(value)))
            return this
        }

        override fun putFloat(key: String, value: Float): Editor {
            delegate.putString(key, encrypt(java.lang.Float.toString(value)))
            return this
        }

        override fun putInt(key: String, value: Int): Editor {
            delegate.putString(key, encrypt(Integer.toString(value)))
            return this
        }

        override fun putLong(key: String, value: Long): Editor {
            delegate.putString(key, encrypt(java.lang.Long.toString(value)))
            return this
        }

        override fun putString(key: String, value: String?): Editor {
            delegate.putString(key, encrypt(value))
            return this
        }

        override fun putStringSet(s: String, set: Set<String>?): SharedPreferences.Editor? {
            return null
        }

        override fun apply() {
            delegate.apply()
        }

        override fun clear(): Editor {
            delegate.clear()
            return this
        }

        override fun commit(): Boolean {
            return delegate.commit()
        }

        override fun remove(s: String): Editor {
            delegate.remove(s)
            return this
        }
    }

    override fun edit(): Editor {
        return Editor()
    }

    override fun getAll(): Map<String, *> {
        throw UnsupportedOperationException() // left as an exercise to the reader
    }

    override fun getBoolean(key: String, defValue: Boolean): Boolean {
        val v = delegate.getString(key, null)
        return if (v != null) java.lang.Boolean.parseBoolean(decrypt(v)) else defValue
    }

    override fun getFloat(key: String, defValue: Float): Float {
        val v = delegate.getString(key, null)
        return if (v != null) java.lang.Float.parseFloat(decrypt(v)) else defValue
    }

    override fun getInt(key: String, defValue: Int): Int {
        val v = delegate.getString(key, null)
        return if (v != null) Integer.parseInt(decrypt(v)) else defValue
    }

    override fun getLong(key: String, defValue: Long): Long {
        val v = delegate.getString(key, null)
        return if (v != null) java.lang.Long.parseLong(decrypt(v)) else defValue
    }

    override fun getString(key: String, defValue: String?): String {
        val v = delegate.getString(key, null)
        return v?.let { decrypt(it) } ?: defValue ?: "null" // fixme: use better approach
    }

    override fun getStringSet(s: String, set: Set<String>?): Set<String>? {
        return null
    }

    override fun contains(s: String): Boolean {
        return delegate.contains(s)
    }

    override fun registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        delegate.registerOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }

    override fun unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener: SharedPreferences.OnSharedPreferenceChangeListener) {
        delegate.unregisterOnSharedPreferenceChangeListener(onSharedPreferenceChangeListener)
    }


    @SuppressLint("HardwareIds")
    private fun encrypt(value: String?): String {

        try {
            val bytes = value?.toByteArray(charset(UTF8)) ?: ByteArray(0)
            val keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY)
            val key = keyFactory.generateSecret(PBEKeySpec(cipherSecret))
            val pbeCipher = Cipher.getInstance(SECRET_KEY_FACTORY)
            pbeCipher.init(
                Cipher.ENCRYPT_MODE, key, PBEParameterSpec(
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID).toByteArray(
                        charset(
                            UTF8
                        )
                    ), 20
                )
            )
            return String(Base64.encode(pbeCipher.doFinal(bytes), Base64.NO_WRAP), Charset.forName(UTF8))

        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

    @SuppressLint("HardwareIds")
    private fun decrypt(value: String?): String {
        try {
            val bytes = if (value != null) Base64.decode(value, Base64.DEFAULT) else ByteArray(0)
            val keyFactory = SecretKeyFactory.getInstance(SECRET_KEY_FACTORY)
            val key = keyFactory.generateSecret(PBEKeySpec(cipherSecret))
            val pbeCipher = Cipher.getInstance(SECRET_KEY_FACTORY)
            pbeCipher.init(
                Cipher.DECRYPT_MODE, key, PBEParameterSpec(
                    Settings.Secure.getString(context.contentResolver, Settings.Secure.ANDROID_ID).toByteArray(
                        charset(
                            UTF8
                        )
                    ), 20
                )
            )
            return String(pbeCipher.doFinal(bytes), Charset.forName(UTF8))

        } catch (e: Exception) {
            throw RuntimeException(e)
        }

    }

}