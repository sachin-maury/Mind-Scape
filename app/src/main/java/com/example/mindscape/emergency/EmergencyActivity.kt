package com.example.mindscape.emergency

import android.content.Intent
import android.os.Bundle
import android.widget.ImageButton
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.pm.ShortcutInfoCompat
import androidx.core.content.pm.ShortcutManagerCompat
import androidx.core.graphics.drawable.IconCompat
import com.example.mindscape.R
import com.example.mindscape.WelcomeActivity
import com.google.firebase.auth.FirebaseAuth

class EmergencyActivity : AppCompatActivity() {

    companion object {
        private const val SHORTCUT_ID = "shortcut_emergency_activity"
    }

    private val firebaseAuth = FirebaseAuth.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_emergency)

        if (!isUserLoggedIn()) {
            val loginIntent = Intent(this, EmergencyLoginActivity::class.java)
            startActivity(loginIntent)
            finish()
            return
        }

        createShortcut()

        val forButton11: ImageButton = findViewById(R.id.img11)
        val forButton12: TextView = findViewById(R.id.my_profile11)
        val forButton21: ImageButton = findViewById(R.id.imageBu2)
        val forButton22: TextView = findViewById(R.id.my_pr1)
        val forButton31: ImageButton = findViewById(R.id.ima2)
        val forButton32: TextView = findViewById(R.id.m1)
        val exitButton: TextView = findViewById(R.id.my_pro13)

        forButton11.setOnClickListener {
            val intent = Intent(this@EmergencyActivity, EmergencyCallActivity::class.java)
            startActivity(intent)
        }

        forButton12.setOnClickListener {
            val intent = Intent(this@EmergencyActivity, EmergencyCallActivity::class.java)
            startActivity(intent)
            finish()
        }

        forButton21.setOnClickListener {
            val intent = Intent(this@EmergencyActivity, EmergencyAmbulanceActivity::class.java)
            startActivity(intent)
            finish()
        }

        forButton22.setOnClickListener {
            val intent = Intent(this@EmergencyActivity, EmergencyAmbulanceActivity::class.java)
            startActivity(intent)
            finish()
        }

        forButton31.setOnClickListener {
            val intent = Intent(this@EmergencyActivity, EmergencyProcedureActivity::class.java)
            startActivity(intent)
        }

        forButton32.setOnClickListener {
            val intent = Intent(this@EmergencyActivity, EmergencyProcedureActivity::class.java)
            startActivity(intent)
            finish()
        }

        exitButton.setOnClickListener {
            val intent = Intent(this@EmergencyActivity, WelcomeActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createShortcut() {
        val shortcutInfo = ShortcutInfoCompat.Builder(this, SHORTCUT_ID)
            .setShortLabel(getString(R.string.shortcut_label))
            .setIcon(IconCompat.createWithResource(this, R.mipmap.ic_shortcut))
            .setIntent(Intent(this, EmergencyActivity::class.java).apply {
                action = Intent.ACTION_MAIN
            })
            .build()

        val shortcuts = ShortcutManagerCompat.getDynamicShortcuts(this)
        val shortcutExists = shortcuts.any { it.id == SHORTCUT_ID }

        if (!shortcutExists) {
            ShortcutManagerCompat.requestPinShortcut(this, shortcutInfo, null)
        }
    }

    private fun isUserLoggedIn(): Boolean {
        return firebaseAuth.currentUser != null
    }
}