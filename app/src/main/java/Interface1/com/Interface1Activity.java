package Interface1.com;



import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import cn.domob.android.ads.DomobAdEventListener;
import cn.domob.android.ads.DomobAdManager.ErrorCode;
import cn.domob.android.ads.DomobAdView;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;


public class Interface1Activity extends Activity {
	public static final String PUBLISHER_ID = "56OJyM1ouMGoaSnvCK";
	RelativeLayout mAdContainer;
	DomobAdView mAdview320x50;

	//建立全域變數
	private EditText CellPhone,editText1;
    private Button btnEND;
    private Button btnOK;
    String str2="",s1="",sql,str1;
    private Connection conn;
    private Statement stmt = null; 
    String row = null;
    String row1 = null;
    //執行,傳入之sql為完整字串 
    private ResultSet rs = null; 
    public int tru;
    public int inuse=1;
    private PreparedStatement pst = null; 
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mAdContainer = (RelativeLayout) findViewById(R.id.adcontainer);

		mAdview320x50 = new DomobAdView(this, PUBLISHER_ID, DomobAdView.INLINE_SIZE_320X50);
		mAdview320x50.setKeyword("game");
		mAdview320x50.setUserGender("male");
		mAdview320x50.setUserBirthdayStr("2000-08-08");
		mAdview320x50.setUserPostcode("123456");

		mAdview320x50.setAdEventListener(new DomobAdEventListener() {
			
			@Override
			public void onDomobAdReturned(DomobAdView adView) {
				// TODO Auto-generated method stub
				Log.i("DomobSDKDemo", "onDomobAdReturned");				
			}

			@Override
			public void onDomobAdOverlayPresented(DomobAdView adView) {
				// TODO Auto-generated method stub
				Log.i("DomobSDKDemo", "overlayPresented");
			}

			@Override
			public void onDomobAdOverlayDismissed(DomobAdView adView) {
				// TODO Auto-generated method stub
				Log.i("DomobSDKDemo", "Overrided be dismissed");				
			}

			@Override
			public void onDomobAdClicked(DomobAdView arg0) {
				// TODO Auto-generated method stub
				Log.i("DomobSDKDemo", "onDomobAdClicked");				
			}

			@Override
			public void onDomobAdFailed(DomobAdView arg0, ErrorCode arg1) {
				// TODO Auto-generated method stub
				Log.i("DomobSDKDemo", "onDomobAdFailed");
			}

			@Override
			public void onDomobLeaveApplication(DomobAdView arg0) {
				// TODO Auto-generated method stub
				Log.i("DomobSDKDemo", "onDomobLeaveApplication");
			}

			
			
		});

		mAdContainer.addView(mAdview320x50);
        
          
        //取得介面元件
        CellPhone=(EditText)findViewById(R.id.CellPhone);
        editText1=(EditText)findViewById(R.id.editText1);
        btnEND=(Button)findViewById(R.id.btnEND);
        btnOK=(Button)findViewById(R.id.btnOK);
        //設定共用事件
        CellPhone.setOnClickListener(listener);
        editText1.setOnClickListener(listener);
        btnEND.setOnClickListener(listener);
        btnOK.setOnClickListener(listener);
        
    }
    
    private void mSetText(String str){
    	TextView txt=(TextView)findViewById(R.id.textView1);
        
        txt.setText(str);
      }

	private Button.OnClickListener listener=new Button.OnClickListener() {
    	 public void onClick(View v) {
           switch(v.getId())
           {
        
           case R.id.btnOK:  //按 清除 鈕
            str1=CellPhone.getText().toString();
            str2=editText1.getText().toString();
            sql(str1,str2);
           
           insert(str1, str1, inuse);
           	if(str1.length()>0 && str2.length()>0) {
           		Toast toast=Toast.makeText(getApplicationContext(), "welcome!", Toast.LENGTH_LONG);
           		toast.setGravity(Gravity.CENTER, 0, 0);
           		toast.show();
           		Intent intent=new Intent();
        		Bundle bundle = new Bundle();
        		bundle.putString("user_id", row1);
        		intent.putExtras(bundle);
        		intent.setClass(Interface1Activity.this,Interface2.class); 
        		startActivity(intent);
           	}else{
           		Toast toast=Toast.makeText(getApplicationContext(), "資料不齊全、輸入電話號碼或車號錯誤", Toast.LENGTH_LONG);
           		toast.setGravity(Gravity.CENTER, 0, 0);
           		toast.show();
           	}                                                         
           	
 	            break;
           case R.id.btnEND:  //按 確定 鈕
        	   new AlertDialog.Builder(Interface1Activity.this)
           	.setTitle("確認")
           	.setIcon(R.drawable.ic_launcher)
           	.setMessage("確定要結束shopping嗎？")
       		.setPositiveButton("確定", new DialogInterface.OnClickListener()
       		{
       			public void onClick(DialogInterface dialoginterface, int i)
       			{
       				finish();
       			}
       		})
       		.setNegativeButton("取消", new DialogInterface.OnClickListener()
       		{
       			public void onClick(DialogInterface dialoginterface, int i)
       			{
       				
       			}
       		})
       		
           	.show();
             	break;
           	
           
          }
       }

		
 	};
 	private void insert(String mobilephone,String cartnumber,int inuse) {
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
		        stmt.executeUpdate("UPDATE  user_cart " + 
		                "SET cart_number="+str2+" WHERE mobile_phone = "+str1);
		    } 
		    catch(SQLException e) 
		    { 
		    	e.printStackTrace();  
		        StringWriter sw = new StringWriter();  
		        e.printStackTrace(new PrintWriter(sw, true));  
		        String str = sw.toString();  
		  	  //mSetText(str); 
		    } 
		    finally 
		    { 
		      Close(); 
		    } 
		}
 	 private void sql(String str1, String str2) {
		// TODO Auto-generated method stub
 		
    	try {
            Class.forName("net.sourceforge.jtds.jdbc.Driver"); 
     } catch (Exception e) {
   	  s1="Error loading Mssql Driver!";
     }
     try {
   	  
    	 String UserName = "wendy";//用户名  
         String Password = "1234";//密码  ;          
        conn= (Connection) DriverManager.getConnection("jdbc:jtds:sqlserver://140.129.26.90:2000/gomarket;charset=Big5", UserName,  
                Password);           
        stmt=(Statement) conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_UPDATABLE);
        sql="select mobile_phone,user_id from cart_phone where mobile_phone="+str1;
        rs=stmt.executeQuery(sql);
      
        int i=0;
        while(rs.next()){
            row=rs.getString("mobile_phone"); 
            row1=rs.getString("user_id");
            i=i+1;
           }
      /* rs.last();
      int j= rs.getRow();*/
      mSetText(row+"");
	        if(str1.equals(row))
	        {
	       		tru=0;
	        }
	        else
	        {
	        	tru=1;
	        }
        } 
     catch (Exception e) {
      	e.printStackTrace();  
        StringWriter sw = new StringWriter();  
        e.printStackTrace(new PrintWriter(sw, true));  
        String str = sw.toString();  
  	  //mSetText(str);
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
}