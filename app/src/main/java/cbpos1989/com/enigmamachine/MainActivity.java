package cbpos1989.com.enigmamachine;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {

    private Rotor rotorRight;
    private Rotor rotorMiddle;
    private Rotor rotorLeft;
    private boolean hasRotors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        createRotors(1, 2, 3);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;


    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onButtonClicked(View view){
        EditText messageField = (EditText)findViewById(R.id.message_field);
        String message = messageField.getText().toString().trim();
        message = message.toUpperCase();

        TextView code_field = (TextView)findViewById(R.id.code_field);

        code_field.setText(codeMessage(message));
    }

    private String codeMessage(String message){
        char[] messageChar = new char[message.length()];
        String codedMessage = "";

        int index = 0;
        for(char c: messageChar){
            c = message.charAt(index++);
            c = codeLetter(c);
            codedMessage = codedMessage.concat(c + "");
        }

        return codedMessage;
    }

    private char codeLetter(char codingLetter){

        //First Pass through the rotor's
        //System.out.print("//1st Pass//");
        codingLetter = rotorRight.codeLetter(codingLetter);
        //System.out.print(codingLetter + " ");

        codingLetter = rotorMiddle.codeLetter(codingLetter);
       // System.out.print(codingLetter + " ");

        codingLetter = rotorLeft.codeLetter(codingLetter);
       // System.out.print(codingLetter + " ");

        //Second pass back through the rotor's in opposite direction
        //System.out.print("//2nd Pass//");
        codingLetter = rotorLeft.codeLetter(codingLetter);
       // System.out.print(codingLetter + " ");

        codingLetter = rotorMiddle.codeLetter(codingLetter);
        //System.out.print(codingLetter + " ");

        codingLetter = rotorRight.codeLetter(codingLetter);
       // System.out.print(codingLetter + " ");

        return codingLetter;
    }

    public void createRotors(int rightRtr, int middleRtr, int leftRtr){
        rotorRight = new Rotor(1,rightRtr);
        rotorMiddle = new Rotor(2,middleRtr);
        rotorLeft = new Rotor(3,leftRtr);
        hasRotors = true;
    }
}
