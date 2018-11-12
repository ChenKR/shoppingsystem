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
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.AdapterView.OnItemClickListener;

public class signal extends Activity {
	private String array[]={"airwaves 清香薄荷","extra木糖醇","易口舒","枇杷潤口喉糖","青賤口香糖","龍角散喉糖"};
	private String array2[]={"79","39","65","35","12","59"};
	private String array3[];
	private static int array4[]={79,39,65,35,12,59};
	private ResultSet rs = null; 
	private PreparedStatement pst = null; 
	private Connection conn;
	private Statement stmt = null; 
	String str2="",s1="",sql,str1;
	public static int sum=0;
	final Interface2 pos=new Interface2();
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signalproduct);
        ListView list = (ListView) findViewById(R.id.list);
        final String ID_TITLE = "TITLE", ID_SUBTITLE = "SUBTITLE";
       // array=new String[8];
        //array2=new String[8];
        array3=new String[8];
        //sql();
         
        ArrayList<HashMap<String,String>> myListData = new ArrayList<HashMap<String,String>>();
        
       /* for( int i=0;i<array.length ; i++) {
            HashMap<String,String> item = new HashMap<String,String>();
            item.put(ID_TITLE,array[i]);
            item.put(ID_SUBTITLE,array2[i]);
            myListData.add(item);
    }*/
        //list.setChoiceMode(ListView.CHOICE_MODE_MULTIPLE);
        MyCheckBoxAdapter2 adapter = new MyCheckBoxAdapter2(this,getData()); 
        list.setAdapter( adapter);
       // list.setOnItemClickListener(list1);
	}
	private List<Map<String, String>> getData() {  
	    // 组织数据源  
	    List<Map<String, String>> mylist = new ArrayList<Map<String, String>>();  
	    for (int i = 0; i < array.length; i++) {  
	    	HashMap<String,String> item = new HashMap<String,String>();
	        item.put("ID_TITLE",array[i]);
	        item.put("ID_SUBTITLE",array2[i]); 
	        mylist.add(item);  
	    }  
	    return mylist;  
	}  
	/*private ListView.OnItemClickListener list1 = new OnItemClickListener() {

		public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
				long arg3) {
			// TODO Auto-generated method stub
			int ins=arg2;
			
			//insert(ins);
		}};*/
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
		        stmt.executeUpdate("INSERT INTO incart (goods_id,order_id) " + 
		                "VALUES ("+array2[i]+"," +array3[i]+")");
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
		        sql="select shop_price,goods_id,goods_name from rfid_goods ";
		        rs=stmt.executeQuery(sql);
		        StringBuilder str=new StringBuilder();
		        int i=0;
		        while(rs.next()){
		        	array[i]=rs.getString("goods_name");
		        	array2[i]=rs.getString("goods_id");
		        	array3[i]=rs.getString("shop_price");
		        	i=i+1;
		           }
		        
		        
		        
		        
		        } catch (Exception e) {
		      	e.printStackTrace();  
		        StringWriter sw = new StringWriter();  
		        e.printStackTrace(new PrintWriter(sw, true));  
		        String str = sw.toString();  
		  	  
		      }
		    
			}
	public void sum(int position) {
		// TODO Auto-generated method stub
		
		pos.init(array4[position]);
	}
	public void Minus(int position) {
		// TODO Auto-generated method stub
		pos.init2(array4[position]);
	}
}
