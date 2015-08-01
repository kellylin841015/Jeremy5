package com.example.jeremy5;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainActivity extends Activity {
	ListView lstPrefer;
	int[] rexIds=new int[]{R.drawable.pictitle01,R.drawable.pictitle02,
			R.drawable.pictitle03};
	String[] roomName=new String[]{"Animal","Human","Food"};
	String[] ans=new String[]{"giraffe","hero","rice"};
	Button btnoutside;
	 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		//取得資源類別檔中介面元件
		lstPrefer=(ListView)findViewById(R.id.listView1);
		
		//取得按鈕介面元件
		btnoutside=(Button)findViewById(R.id.button2);
		//觸發事件
		btnoutside.setOnClickListener(gooutside);
		
		
		//建立自訂adapter
		MyAdapter adapter=new MyAdapter(this);
		
		//設定listview的資料來源
		lstPrefer.setAdapter(adapter);
	}
	
	//go outside 按鈕觸發事件
	private OnClickListener gooutside=new OnClickListener(){
		public void onClick(View v){
			Intent intent=new Intent();
			intent.setClass(MainActivity.this,Outside.class);
			startActivity(intent);
			finish();
		}
	};
	
	public class MyAdapter extends BaseAdapter{
		private LayoutInflater myInflater;
		public MyAdapter(Context c){
			myInflater=LayoutInflater.from(c);
		}
		
		@Override
		public int getCount(){
			return ans.length;
		}
		
		@Override
		public Object getItem(int position){
			return ans[position];
		}
		
		@Override
		public long getItemId(int position){
			return position;
		}
		
		@Override
		public View getView(int position,View convertView,
				ViewGroup parent){
			
			//取得自訂介面
			convertView=myInflater.inflate(R.layout.mylayout,null);
			
			//取得 mylayout.xml 中的文件
			ImageView pictitle=(ImageView)
					convertView.findViewById(R.id.imageview1);
			TextView txtName=(TextView)
					convertView.findViewById(R.id.textview3);
			TextView txtengName=(TextView)
					convertView.findViewById(R.id.textview4);
			
			
			//設定元件內容
			pictitle.setImageResource(rexIds[position]);
			txtName.setText(roomName[position]);
			txtengName.setText(ans[position]);
			
			return convertView;
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
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
