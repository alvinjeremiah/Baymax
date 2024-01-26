package com.example.baymax;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.ValueEventListener;

import java.util.Calendar;

public class reminder extends AppCompatActivity {

    private TimePicker timePickerMedicine;
    private DatabaseReference mDatabase;
    private int lastSelectedHour = -1;
    private int lastSelectedMinute = -1;
    private int lastSelectedYear = -1;
    private int lastSelectedMonth = -1;
    private int lastSelectedDay = -1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reminder);

        Toolbar toolbar = findViewById(R.id.Toolbar);
        setSupportActionBar(toolbar);

        mDatabase = FirebaseDatabase.getInstance().getReference();

        timePickerMedicine = new TimePicker(this);
        timePickerMedicine.setIs24HourView(true);
        timePickerMedicine.setId(View.generateViewId());

        FloatingActionButton fabAddMedicine = findViewById(R.id.fabAddMedication);
        fabAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showAddMedicineDialog();
            }
        });
//        fetchAndDisplayMedications();
    }

    public void showAddMedicineDialog() {
        // Create a custom dialog
        final Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.add_reminder);

        EditText editTextTime = dialog.findViewById(R.id.editText_time);
        Button buttonTime = dialog.findViewById(R.id.button_time);
        Button buttonDate = dialog.findViewById(R.id.button_date);
        Button buttonAddMedicine = dialog.findViewById(R.id.button4);
        EditText editTextMedicineName = dialog.findViewById(R.id.editTextText2);
        EditText editTextDate = dialog.findViewById(R.id.editText_date);

        // Retrieve the linear layout container from the custom dialog
        LinearLayout linearLayoutContainer = dialog.findViewById(R.id.linearLayoutContainer);

        int lastSelectedHour = -1;
        int lastSelectedMinute = -1;

        // Handle the "Select Time" button click
        buttonTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Add the TimePicker to the linear layout container
                LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.WRAP_CONTENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                );
                linearLayoutContainer.addView(timePickerMedicine, layoutParams);

                // Show the TimePicker
                timePickerMedicine.setVisibility(View.VISIBLE);

                // Optional: You may want to set initial values for the TimePicker
                if (lastSelectedHour != -1 && lastSelectedMinute != -1) {
                    timePickerMedicine.setCurrentHour(lastSelectedHour);
                    timePickerMedicine.setCurrentMinute(lastSelectedMinute);
                }
            }
        });

        // Handle the "Select Date" button click
        buttonDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDatePickerDialog(editTextDate, lastSelectedYear, lastSelectedMonth, lastSelectedDay);
            }
        });

        // Handle the "Add Medicine" button click
        buttonAddMedicine.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Retrieve user input from the dialog
                String medicineName = editTextMedicineName.getText().toString();
                int hour = timePickerMedicine.getCurrentHour();
                int minute = timePickerMedicine.getCurrentMinute();

                String medicineId = mDatabase.child("medicines").push().getKey();
//
//                Medicine medicine = new Medicine(medicineName, hour, minute);
//                mDatabase.child("medicines").child(medicineId).setValue(medicine);
//
                Toast.makeText(getApplicationContext(), "Added Sucessfully", Toast.LENGTH_SHORT).show();
//                // Dismiss the dialog
                dialog.dismiss();
            }
        });

        // Show the dialog
        dialog.show();
    }

//    private void fetchAndDisplayMedications() {
//        LinearLayout linearLayoutContainer = findViewById(R.id.linearLayoutContainer);
//
//        if (linearLayoutContainer != null) {
//            mDatabase.child("medicines").addValueEventListener(new ValueEventListener() {
//                @Override
//                public void onDataChange(DataSnapshot dataSnapshot) {
//                    // Clear existing views before updating with new data
//                    linearLayoutContainer.removeAllViews();
//
//                    // Iterate through each medication in the database
//                    for (DataSnapshot medicationSnapshot : dataSnapshot.getChildren()) {
//                        Medicine medicine = medicationSnapshot.getValue(Medicine.class);
//                        if (medicine != null) {
//                            // Create a TextView for each medicine and add it to the layout
//                            TextView textView = new TextView(reminder.this);
//                            textView.setText("Medicine Name: " + medicine.getName() +
//                                    "\nTime: " + medicine.getHour() + ":" + medicine.getMinute() +
//                                    "\n-------------------");
//                            linearLayoutContainer.addView(textView);
//                        }
//                    }
//                }
//
//                @Override
//                public void onCancelled(DatabaseError databaseError) {
//                    // Handle errors, if any
//                    Toast.makeText(getApplicationContext(), "Failed to fetch medications", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } else {
//            Toast.makeText(getApplicationContext(), "Linear Layout is null provide ID", Toast.LENGTH_SHORT).show();
//        }
//    }
//
//
//    public class Medicine {
//        private String name;
//        private int hour;
//        private int minute;
//
//        // Required default constructor for Firebase
//        public Medicine() {
//            // Default constructor required for calls to DataSnapshot.getValue(Medicine.class)
//        }
//
//        public Medicine(String name, int hour, int minute) {
//            this.name = name;
//            this.hour = hour;
//            this.minute = minute;
//        }
//
//        public String getName() {
//            return name;
//        }
//
//        public int getHour() {
//            return hour;
//        }
//
//        public int getMinute() {
//            return minute;
//        }
//    }


//    public void showAddMedicineDialog() {
//        // Create a custom dialog
//        final Dialog dialog = new Dialog(this);
//        dialog.setContentView(R.layout.add_reminder);
//
//        EditText editTextTime = dialog.findViewById(R.id.editText_time);
//        Button buttonTime = dialog.findViewById(R.id.button_time);
//        Button buttonDate = dialog.findViewById(R.id.button_date);
//        Button buttonAddMedicine = dialog.findViewById(R.id.button4);
//        EditText editTextMedicineName = dialog.findViewById(R.id.editTextText2);
//        EditText editTextDate = dialog.findViewById(R.id.editText_date);
//
//        // Dynamically add TimePicker to the custom dialog
//        LinearLayout linearLayoutContainer = dialog.findViewById(R.id.linearLayoutContainer);
//        TimePicker timePickerMedicine = new TimePicker(this);
//        timePickerMedicine.setIs24HourView(true);
//        timePickerMedicine.setId(View.generateViewId()); // Generate a unique ID
//
//// Define layout parameters
//        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        );
//        linearLayoutContainer.addView(timePickerMedicine, layoutParams);
//
//        int lastSelectedHour = -1;
//        int lastSelectedMinute = -1;
//
//        // Handle the "Select Time" button click
//        buttonTime.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Retrieve the dynamically added TimePicker
//                TimePicker dynamicTimePicker = dialog.findViewById(timePickerMedicine.getId());
//
//                showTimePickerDialog(editTextTime, dynamicTimePicker, lastSelectedHour, lastSelectedMinute);
//            }
//        });
//
//        // Handle the "Select Date" button click
//        buttonDate.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                showDatePickerDialog(editTextDate, lastSelectedYear, lastSelectedMonth, lastSelectedDay);
//            }
//        });
//
//        // Handle the "Add Medicine" button click
//        buttonAddMedicine.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                // Retrieve user input from the dialog
//                String medicineName = editTextMedicineName.getText().toString();
//                int hour = timePickerMedicine.getCurrentHour();
//                int minute = timePickerMedicine.getCurrentMinute();
//
//                // TODO: Save medicine details or schedule reminder as needed
//
//                // Dismiss the dialog
//                dialog.dismiss();
//            }
//        });
//
//        // Show the dialog
//        dialog.show();
//    }


    private void showTimePickerDialog(final EditText editText, final TimePicker timePicker, int hour, int minute) {
        // Get Current Time
        final Calendar c = Calendar.getInstance();
        int currentHour = c.get(Calendar.HOUR_OF_DAY);
        int currentMinute = c.get(Calendar.MINUTE);

        // Create TimePickerDialog
        TimePickerDialog timePickerDialog = new TimePickerDialog(this,
                (view, hourOfDay, minuteOfDay) -> {
                    editText.setText(String.format("%02d:%02d", hourOfDay, minuteOfDay));
                    // Set the selected time in the TimePicker
                    timePicker.setCurrentHour(hourOfDay);
                    timePicker.setCurrentMinute(minuteOfDay);
                }, hour == -1 ? currentHour : hour, minute == -1 ? currentMinute : minute, true);

        // Show TimePickerDialog
        timePickerDialog.show();
    }

    private void showDatePickerDialog(final EditText editText, int year, int month, int day) {
        // Get Current Date
        final Calendar c = Calendar.getInstance();
        int currentYear = c.get(Calendar.YEAR);
        int currentMonth = c.get(Calendar.MONTH);
        int currentDay = c.get(Calendar.DAY_OF_MONTH);

        // Create DatePickerDialog
        DatePickerDialog datePickerDialog = new DatePickerDialog(this,
                (view, year1, monthOfYear, dayOfMonth) -> {
                    editText.setText(String.format("%02d/%02d/%d", dayOfMonth, monthOfYear + 1, year1));
                    lastSelectedYear = year1;
                    lastSelectedMonth = monthOfYear;
                    lastSelectedDay = dayOfMonth;
                }, year == -1 ? currentYear : year, month == -1 ? currentMonth : month, day == -1 ? currentDay : day);

        // Show DatePickerDialog
        datePickerDialog.show();
    }
}
