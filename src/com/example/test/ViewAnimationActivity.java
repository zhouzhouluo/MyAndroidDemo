package com.example.test;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class ViewAnimationActivity extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.list_layout2);
		setupListView();
		setupButton();
	}
	private void setupListView(){
		String[] listItems = new String[]{
				"Item 1","Item 2","Item 3",
				"Item 4","Item 5","Item 6",
				"Item 4","Item 5","Item 6",
				"Item 4","Item 5","Item 6",
				"Item 4","Item 5","Item 6"
		};
		ArrayAdapter listItemAdapter= new ArrayAdapter(this, android.R.layout.simple_list_item_1,listItems);
		ListView lv = (ListView)findViewById(R.id.list_view_id);
		lv.setAdapter(listItemAdapter);
	}
	private void setupButton(){
		Button b = (Button)findViewById(R.id.btn_animate);
		b.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				animateListView();
			}
		});
	}
	private void animateListView(){
		ListView lv = (ListView)findViewById(R.id.list_view_id);
		System.out.println("来了吗？点哦idufoadsu 哦idasugioadsuy");
		lv.startAnimation(new ViewAnimation());
	}
}
