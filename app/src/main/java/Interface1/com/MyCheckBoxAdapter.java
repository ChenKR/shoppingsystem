package Interface1.com;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MyCheckBoxAdapter extends BaseAdapter {

	private LayoutInflater mInflater;

	private List<Map<String, String>> listData;

	private Map<Integer, Map<String, String>> selectMap = new HashMap<Integer, Map<String, String>>();

	private class ViewHolder {
		public TextView price;
		public TextView title;
		public CheckBox checkBox;
	}

	public MyCheckBoxAdapter(Context context, List<Map<String, String>> listData) {
		this.mInflater = LayoutInflater.from(context);
		this.listData = listData;
	}

	@Override
	public int getCount() {
		return listData.size();
		
	}

	@Override
	public Object getItem(int position) {
		return listData.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		ViewHolder holder = null;
		final Interface2 pos=new Interface2();
		if (convertView == null) {
			holder = new ViewHolder();
			convertView = mInflater.inflate(R.layout.list, null);
			final View view = convertView;
			holder.price = (TextView) convertView.findViewById(R.id.textView1);
			holder.title = (TextView) convertView.findViewById(R.id.itemTitle);
			holder.checkBox = (CheckBox) convertView.findViewById(R.id.cb);
			holder.checkBox.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					if (selectMap.get(position) != null) {
						selectMap.remove(position);pos.Minus(position);
						
					} else {
						selectMap.put(position, listData.get(position));
						pos.sum(position);
					}
					 
				}
			});
			//init(ins);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		
		holder.title.setText(listData.get(position).get("ID_TITLE"));
		holder.price.setText(listData.get(position).get("ID_SUBTITLE"));
		if (selectMap.get(position) != null) {
			holder.checkBox.setChecked(true);
		} else {
			holder.checkBox.setChecked(false);
		}

		return convertView;
	}
}
