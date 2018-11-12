package Interface1.com;


import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

public class Interface2 extends Activity {
protected static Object list;
private String note_array[]={"台堂香醇蜆精","化陀十全雞精","白爛氏旭沛蜆精","玫瑰四物飲","青木瓜四物鐵","奧利奧","可口夾心","營養口糧","張均雅小妹妹","一美小泡芙"};
private String note_array2[]={"65","45","68","50","75","49","39","16","22","28"};

private String note_array3[];
private String note_array4[];
private static int note_arra[]={65,45,68,50,75,49,39,16,22,28};
private ResultSet rs = null;
ResultSet rs2; 
private PreparedStatement pst = null; 
private Connection conn;
private Statement stmt = null; 
public int usid,ins=0;
public static int sum,po;
String str2="",s1="",sql,userid,sql2;
ArrayAdapter<String> MyArrayAdapter;
HashMap<Integer, Boolean> isSelected;
public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page2);
        ListView list = (ListView) findViewById(R.id.list);
        //Bundle bundle = this.getIntent().getExtras();
      // userid = bundle.getString("user_id");
      // usid=Integer.parseInt(userid);
        final String ID_TITLE = "TITLE", ID_SUBTITLE = "SUBTITLE";
        //note_array2=new String [5];
        note_array3=new String [5];
        note_array4=new String [5];
        sum=0;
        
        //sql();
        Button button_add = (Button) findViewById(R.id.button1);
        button_add.setOnClickListener(myListner2); 
        Button btnHome=(Button)findViewById(R.id.btnHome);  
        btnHome.setOnClickListener(myListner); 
        
        //list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        MyCheckBoxAdapter adapter = new MyCheckBoxAdapter(this,getData()); 
        list.setAdapter(adapter);
        /*list.setOnItemClickListener( new  OnItemClickListener() {  
            @Override  
            public  void  onItemClick(AdapterView<?> arg0, View arg1,  int  arg2,  
                    long  arg3) {  

            sum(arg2);
            }  
        });*/
        
        //list.setOnItemClickListener(list1);
        //init(ins);
        
}
private List<Map<String, String>> getData() {  
    // 组织数据源  
    List<Map<String, String>> mylist = new ArrayList<Map<String, String>>();  
    for (int i = 0; i < note_array.length; i++) {  
    	HashMap<String,String> item = new HashMap<String,String>();
        item.put("ID_TITLE",note_array[i]);
        item.put("ID_SUBTITLE",note_array2[i]); 
        mylist.add(item);  
    }  
    return mylist;  
}  
public static void init(int ins2) { 
	
	sum=sum+ins2;
	
} 
private Button.OnClickListener myListner2=new Button.OnClickListener(){
	public void onClick(View v){
		
		mSetText(sum+""); 
	}
};

	private void sql() {
	// TODO Auto-generated method stub
		
	 		
	    	try {
	            Class.forName("net.sourceforge.jtds.jdbc.Driver"); 
	     } catch (Exception e) {
	   	  String s1 = "Error loading Mssql Driver!";
	     }
	     try {
	   	  
	    	 String UserName = "wendy";//用户名  
	         String Password = "1234";//密码  ;          
	        conn= (Connection) DriverManager.getConnection("jdbc:jtds:sqlserver://140.129.26.90:2000/gomarket;charset=Big5", UserName,  
	                Password);           
	        
	        stmt=(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
	        
	        sql="select goods_name,shop_price,goods_id from WantyBuy WHERE user_id="+usid;
	        
	        rs=stmt.executeQuery(sql);
	        rs.last();
	        int r=rs.getRow();
	        note_array=new String [r];
	        note_array2=new String [r];
	        note_array3=new String [r];
	        note_array4=new String [r];
	       
	        
	        int i=0;
	        while(rs.next()){
	        	note_array[i]=rs.getString("goods_name");
	        	note_array2[i]=rs.getString("goods_id");
	        	note_array4[i]=rs.getString("order_id");
	        	note_array3[i]=rs.getString("shop_price");
	        	i=i+1;
	           }
	        
	        
	        
	        
	        } catch (Exception e) {
	      	e.printStackTrace();  
	        StringWriter sw = new StringWriter();  
	        e.printStackTrace(new PrintWriter(sw, true));  
	        String str = sw.toString();  
	  	  
	      }
	    
		}
	 private void mSetText(String string){
	    	TextView txt=(TextView)findViewById(R.id.txtShow);
	        
	        txt.setText(string);
	      }

		private void insert(int j) {
			// TODO Auto-generated method stub
 		
 		try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); 
     } catch (Exception e) {
   	  s1="Error loading Mysql Driver!";
     }
			try 
		    { 
		       
				
				String UserName = "wendy";//用户名  
			    String Password = "1234";//密码  
		        Connection conn = DriverManager.getConnection(  
		                "jdbc:jtds:sqlserver://140.129.26.90:2000/gomarket;charset=Big5", UserName,  
		                Password);
		        stmt=(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
		        int i=j;
		        stmt.executeUpdate("INSERT INTO incart (goods_id,order_id,user_id,shop_price) " + 
		                "VALUES ("+note_array2[i]+"," +note_array3[i]+")");
		    } 
		    catch(SQLException e) 
		    { 
		    	e.printStackTrace();  
		        StringWriter sw = new StringWriter();  
		        e.printStackTrace(new PrintWriter(sw, true));  
		        String str = sw.toString();  
		  	 
		    } 
		    finally 
		    { 
		      Close(); 
		    } 
		}
		private void Close() 
	 	  { 
	 	    try 
	 	    { 
	 	      if(rs!=null) 
	 	      { 
	 	        rs.close(); 
	 	        rs = null; 
	 	      } 
	 	      if(stmt!=null) 
	 	      { 
	 	        stmt.close(); 
	 	        stmt = null; 
	 	      } 
	 	      if(pst!=null) 
	 	      { 
	 	        pst.close(); 
	 	        pst = null; 
	 	      } 
	 	    } 
	 	    catch(SQLException e) 
	 	    { 
	 	      System.out.println("Close Exception :" + e.toString()); 
	 	    } 
	 	  } 
	private Button.OnClickListener myListner=new Button.OnClickListener(){
	    	public void onClick(View v){
	    		
	    		finish();
	    	}
	};
	
    protected static final int MENU_SINGAL = Menu.FIRST+1;

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
	    menu.add(0, MENU_SINGAL, 0, "周邊商品");
		return super.onCreateOptionsMenu(menu);
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		switch(item.getItemId()) {
        case MENU_SINGAL:
        	Intent intent1=new Intent();
    		intent1.setClass(Interface2.this,signal.class); 
    		startActivity(intent1);
             break;
		}return super.onOptionsItemSelected(item);
	}
	public void sum(int position) {
		sum=sum+note_arra[position];
		po=position;
	}
	public void Minus(int position) {
		// TODO Auto-generated method stub
		sum=sum-note_arra[position];
		
	}
	public void init2(int i) {
		// TODO Auto-generated method stub
		sum=sum-i;
	}
	

}