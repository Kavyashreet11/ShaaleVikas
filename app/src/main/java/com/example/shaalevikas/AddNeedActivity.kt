package com.example.shaalevikas

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.shaalevikas.models.Need
import com.google.firebase.database.FirebaseDatabase
import android.widget.TextView

class AddNeedActivity : AppCompatActivity() {

    lateinit var etTitle: EditText
    lateinit var etCost: EditText
    lateinit var etProgress: EditText
    lateinit var btnSave: Button

    lateinit var txtAi: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_need)

        // Initialize Views
        etTitle = findViewById(R.id.etTitle)
        etCost = findViewById(R.id.etCost)
        etProgress = findViewById(R.id.etProgress)
        btnSave = findViewById(R.id.btnSave)

        txtAi = findViewById(R.id.txtAi)

        // Save Button Click
        btnSave.setOnClickListener {

            val title = etTitle.text.toString().trim()
            if (title.contains("Painting", true)) {

                txtAi.text =
                    "Gemini AI: Classroom repainting improves learning atmosphere."

            } else if (title.contains("Toilet", true)) {

                txtAi.text =
                    "Gemini AI: Better sanitation improves student hygiene."

            } else {

                txtAi.text =
                    "Gemini AI: This improvement supports better education."
            }
            val cost = etCost.text.toString().trim()
            val progressText = etProgress.text.toString().trim()

            // Validation
            if (title.isEmpty() || cost.isEmpty() || progressText.isEmpty()) {

                Toast.makeText(
                    this,
                    "Please fill all fields",
                    Toast.LENGTH_SHORT
                ).show()

                return@setOnClickListener
            }
            if (title.isEmpty()) {

                etTitle.error = "Enter Need Title"
                return@setOnClickListener
            }

            if (cost.isEmpty()) {

                etCost.error = "Enter Cost"
                return@setOnClickListener
            }

            if (progressText.isEmpty()) {

                etProgress.error = "Enter Progress"
                return@setOnClickListener
            }

            val progress = progressText.toInt()

            // Firebase Database Reference
            val database = FirebaseDatabase
                .getInstance("https://shaalevikas-5061d-default-rtdb.asia-southeast1.firebasedatabase.app")
                .getReference("Needs")

            // Unique ID
            val id = database.push().key!!

            // Need Object
            val need = Need(
                title = title,
                cost = cost,
                progress = progress,
                image = R.drawable.classroom
            )

            // Save to Firebase
            database.child(id).setValue(need)

                .addOnSuccessListener {

                    Toast.makeText(
                        this,
                        "Need Added Successfully",
                        Toast.LENGTH_SHORT
                    ).show()

                    // Clear fields
                    etTitle.text.clear()
                    etCost.text.clear()
                    etProgress.text.clear()


                }

                .addOnFailureListener {

                    Toast.makeText(
                        this,
                        "Failed To Save",
                        Toast.LENGTH_SHORT
                    ).show()
                }
        }
    }
}