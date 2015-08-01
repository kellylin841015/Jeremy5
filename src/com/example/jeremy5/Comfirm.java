package com.example.jeremy5;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Comfirm extends Activity {
	private Button btnEnd;   /*寫使用者名稱頁面按鈕-"完成"*/
	private SharedPreferences preference;
	private String sname,msg;
	private TextView txtName;
	private EditText edtName;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		//取得介面元件
		btnEnd=(Button)findViewById(R.id.button1);
		txtName=(TextView)findViewById(R.id.textView1);
		edtName=(EditText)findViewById(R.id.editText1);
		//為事件加入監聽
		btnEnd.setOnClickListener(listener);
		
		preference=getSharedPreferences("username",MODE_PRIVATE);
		sname=preference.getString("name", "");
		
		//如果未建立資料就顯示輸入欄位
		if(sname.equals("")){
			txtName.setVisibility(TextView.VISIBLE);
			edtName.setVisibility(EditText.VISIBLE);
			msg="Your name can be changed\nmany times.";
		}else{
			msg="Hi!"+sname+"!";
		}
		
		//彈出歡迎視窗
		new AlertDialog.Builder(Comfirm.this)
		.setTitle("Welcome")
		.setMessage(msg)
		.setPositiveButton("OK",new DialogInterface.OnClickListener()
		{
			public void onClick(DialogInterface dialoginterface,int i)
			{ 
				Intent intent=new Intent();
				intent.setClass(Comfirm.this,MainActivity.class);
				startActivity(intent);
				finish();
			}
		})
		.show();
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comfirm);
	}
	
	//按鈕處理事件
	private Button.OnClickListener listener=
			new Button.OnClickListener()
	{
		@Override
		public void onClick(View v)
		{
			Intent intent=new Intent();
			intent.setClass(Comfirm.this,MainActivity.class);
			startActivity(intent);
			finish();
		}
	};
	
	//儲存資料(使用者名稱)
	protected void onStop(){
		super.onStop();
		if(sname.equals("")){//如果未輸入資料就將輸入值存檔
			preference.edit()
			.putString("name",edtName.getText().toString())
			.commit();
		}
	}
	
			
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.comfirm, menu);
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
