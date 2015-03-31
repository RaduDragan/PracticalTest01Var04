package ro.pub.cs.systems.pdsd.practicaltest01var04;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PracticalTest01Var04MainActivity extends Activity {

	private final static int SECONDARY_ACTIVITY_REQUEST_CODE = 1;
	EditText editText;
	Button doButton;
	Button miButton;
	Button solButton;
	Button doButton2;
	Button navigateButton;
	int total = 0;
	int rights = 0;
	int wrongs = 0;

	private ButtonClickListener buttonClickListener = new ButtonClickListener();

	private class ButtonClickListener implements Button.OnClickListener {

		@Override
		public void onClick(View v) {
			String text;
			if (editText.getText() == null)
				text = "";
			else
				text = editText.getText().toString();
			if (!text.isEmpty()) {
				text += ",";
			}
			switch (v.getId()) {
			case R.id.doButton:
				text = text + "Do";
				break;
			case R.id.miButton:
				text = text + "Mi";
				break;
			case R.id.solButton:
				text = text + "Sol";
				break;
			case R.id.doButton2:
				text = text + "Do'";
				break;
			case R.id.navigateButton:
				Intent intent = new Intent(
						"ro.pub.cs.systems.pdsd.intent.action.PracticalTest01Var04SecondaryActivity");
				text = editText.getText().toString();
				intent.putExtra("text", text);
				startActivityForResult(intent, SECONDARY_ACTIVITY_REQUEST_CODE);
				break;
			}
			editText.setText(text);
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_practical_test01_var04_main);

		// ButtonClickListener listener = new ButtonClickListener();
		editText = (EditText) findViewById(R.id.editText);
		editText.setText("");
		doButton = (Button) findViewById(R.id.doButton);
		doButton.setOnClickListener(buttonClickListener);
		miButton = (Button) findViewById(R.id.miButton);
		miButton.setOnClickListener(buttonClickListener);
		solButton = (Button) findViewById(R.id.solButton);
		solButton.setOnClickListener(buttonClickListener);
		doButton2 = (Button) findViewById(R.id.doButton2);
		doButton2.setOnClickListener(buttonClickListener);
		navigateButton = (Button) findViewById(R.id.navigateButton);
		navigateButton.setOnClickListener(buttonClickListener);

		if (savedInstanceState != null) {
			total = savedInstanceState.getInt("total");
			Log.d("", "total=" + total + "\n");
			rights = savedInstanceState.getInt("rights");
			Log.d("", "rights=" + rights + "\n");
			wrongs = savedInstanceState.getInt("wrongs");
			Log.d("", "wrongs=" + wrongs + "\n");
		}
	}

	@Override
	protected void onSaveInstanceState(Bundle savedInstanceState) {
		savedInstanceState.putInt("total", total);
		savedInstanceState.putInt("rights", rights);
		savedInstanceState.putInt("wrongs", wrongs);
	}

	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent intent) {
		String text = "";
		
		if(resultCode == RESULT_OK){
			rights++;
			text = "RESULT_OK";
		}else if(resultCode == RESULT_CANCELED){
			wrongs++;
			text = "RESULT_CANCELED";
		}
		total++;
		editText.setText("");
		Toast.makeText(this, "The activity returned with result " + text,
				Toast.LENGTH_LONG).show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.practical_test01_var04_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
