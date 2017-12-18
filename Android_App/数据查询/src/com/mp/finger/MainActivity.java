package com.mp.finger;

import android.app.*;
import android.os.*;
import android.view.*;
import android.widget.*;
import android.content.*;
import android.view.View.*;
import java.util.*;

public class MainActivity extends Activity
{

    private String str2;
    private String[] sju;
    private List<String> list;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
	{
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
		final show ts = new show(this);
        final EditText edittext = (EditText)findViewById(R.id.mainEditText1);
        final Context ppp=this;
        Button button = (Button)findViewById(R.id.mainButton);
        Button button1 = (Button)findViewById(R.id.mainButton1);
        
        button1.setOnClickListener(new OnClickListener(){

                @Override
                public void onClick(View p1)
                {
                    int ijk = 0;
                    for(String tt : sju){
                        String[] pp= tt.split("\\.");
                        ijk+=Integer.parseInt(pp[1],10);
                    }
                    TextView textView = (TextView)findViewById(R.id.mainTextView1);
                    textView.setText("数量:"+ijk);
                    // TODO: Implement this method
                }
        });
        button.setOnClickListener(new OnClickListener(){
                @Override
                public void onClick(View p1)
                {
                    list = new ArrayList<String>();
                    ClipboardManager cm = (ClipboardManager)getSystemService(CLIPBOARD_SERVICE);
                    ClipData cd2 = cm.getPrimaryClip();
                    str2 = cd2.getItemAt(0).getText().toString();
                    String[] str3= str2.split("\n");
                    if(str3.length==0){ts.textts("请复制后再尝试！");return;}
                    String bi = edittext.getText().toString().equals("") ? "*.*.*.*" : edittext.getText().toString();
                    String[] bp = bi.split("\\.");
                    int ip=0;
                    
                    if(bp.length<4){ts.textts("格式错误！");return;}
                    for(int i=0;i<str3.length;i++){
                        for(int ii=0;ii<bp.length;ii++){
                            String[] k= str3[i].split("\\.");
                            
                            if(k[ii].equals(bp[ii])){
                                ip++;
                            }else if (bp[ii].equals("*")){
                                ip++;
                            }
                        }
                        if(bp.length==ip){
                            list.add(str3[i]);
                        }
                        ip=0;
                    }
                    sju=new String[list.size()];
                    
                    for(int i=0;i<list.size();i++){
                        sju[i]=list.get(i);
                    }
                    ListAdapter adapter = new MyAdapter(ppp,sju);

                    ListView listView = (ListView) findViewById(R.id.mainListView1);
                    listView.setAdapter(adapter);
                    // TODO: Implement this method
                }
        });
        
    }
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
    public View getView(int position, View convertView, ViewGroup parent)
    {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.eext, parent, false);
        String txt= getItem(position);
        String[] text=txt.split("\\.");

        TextView textView = (TextView) view.findViewById(R.id.TextView);
        TextView textView1 = (TextView) view.findViewById(R.id.eextTextView1);
        TextView textView2 = (TextView) view.findViewById(R.id.eextTextView2);
        TextView textView3 = (TextView) view.findViewById(R.id.eextTextView3);
        textView.setText(text[0]);
        textView1.setText("数量:"+text[1]);
        textView2.setText("大小类型:"+text[2]);
        textView3.setText("颜色:"+text[3]);
        return view;
    }
}


class show {
	private static Context sc;
	show(Context thiss){
		this.sc=thiss;
	}
    public void textts(String Text){
        Toast.makeText(sc,Text,Toast.LENGTH_SHORT).show();
    }
}
