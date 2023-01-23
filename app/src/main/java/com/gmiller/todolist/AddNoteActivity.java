package com.gmiller.todolist;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
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

    private AddNoteViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note);
        viewModel = new ViewModelProvider(this).get(AddNoteViewModel.class);
        viewModel.getShouldCloseScreen().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(Boolean shouldClose) {
              if(shouldClose){
                  finish();
              }
            }
        });
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
            Note note = new Note(text, priority);
            viewModel.saveNote(note);

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






















