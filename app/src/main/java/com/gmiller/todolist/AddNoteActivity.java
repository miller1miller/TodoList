package com.gmiller.todolist;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

public class AddNoteActivity extends AppCompatActivity {

    private EditText editTextNote;
    private RadioButton radioButtonLowPriority;
    private RadioButton radioButtonMediumPriority;
    private Button buttonSave;

    private Database database = Database.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        initViews();
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveNote();
            }
        });
    }

    private void initViews(){
        editTextNote = findViewById(R.id.editTextNote);
        radioButtonLowPriority = findViewById(R.id.radioButtonLowPriority);
        radioButtonMediumPriority = findViewById(R.id.radioButtonMediumPriority);
        buttonSave = findViewById(R.id.buttonSave);
    }

    private void saveNote(){
        if (editTextNote != null) {
            String text = editTextNote.getText().toString().trim();
            int priority = getPriority();
            int id = database.getNotes().size();
            Note note = new Note(id, text, priority);
            database.add(note);

            finish();
        } else{
            Toast.makeText(this, "Поле не может быть пустым!", Toast.LENGTH_SHORT).show();
        }
    }
    private int getPriority(){
        int priority;
        if (radioButtonLowPriority.isChecked()){
            priority = 0;
        } else if(radioButtonMediumPriority.isChecked()){
            priority = 1;
        } else{
            priority = 2;
        }
        return priority;
    }

    public static Intent newIntent(Context context){
        return new Intent(context, AddNoteActivity.class);
    }
}






















