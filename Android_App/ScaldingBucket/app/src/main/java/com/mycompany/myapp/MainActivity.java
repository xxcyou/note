package com.mycompany.myapp;

import android.app.*;
import android.os.*;
import java.util.*;
import android.widget.*;
import android.view.*;
import android.content.*;
import android.widget.AdapterView.*;
import android.widget.CompoundButton.*;
import android.util.*;

public class MainActivity extends Activity 
{
	private List<String> list = new ArrayList<String>();
	private ArrayAdapter<String> adapter;
	@Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		list.add("未知？--------------------------");
		list.add("S-码");
		list.add("M-码");
		list.add("L-码");
		list.add("XL-码");
		list.add("XXL-码");
		Spinner 下拉菜单 = (Spinner)findViewById(R.id.mainSpinner1);
		adapter = new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, list);
        // 第三步：为适配器设置下拉列表下拉时的菜单样式。
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // 第四步：将适配器添加到下拉列表上
        下拉菜单.setAdapter(adapter);
        // 第五步：为下拉列表设置各种事件的响应，这个事响应菜单被选中
		too(10);
		
		
    }
	public void too(int i){
		String[] companies = new String[i];

        ListAdapter adapter = new MyAdapter(this, companies);

		ListView listView = (ListView) findViewById(R.id.mainListView1);
		listView.setAdapter(adapter);
	}
	class MyAdapter extends ArrayAdapter<String>
    {
		private Context vir;
		public MyAdapter(Context context, String[] values) 
		{
			super(context, R.layout.eext, values);
			this.vir=context;
		}

		@Override
		public View getView( final int position, View convertView, ViewGroup parent)
		{
			LayoutInflater inflater = LayoutInflater.from(getContext());
			View view = inflater.inflate(R.layout.eext, parent, false);

			TextView textView = (TextView) view.findViewById(R.id.TextView);
			TextView textView1 = (TextView) view.findViewById(R.id.eextTextView1);
			TextView textView2 = (TextView) view.findViewById(R.id.eextTextView2);
			TextView textView3 = (TextView) view.findViewById(R.id.eextTextView3);
			textView.setText("123456");
			ImageButton checkbox = (ImageButton)view.findViewById(R.id.eextImageButton1);
			checkbox.setOnClickListener (new OnClickListener (){

					@Override
					public void onClick(View p1)
					{
						// TODO: Implement this method
						Log.i("i","删除:"+position);
						too(5);
					}
					
			});
			return view;
		}
    }
}
