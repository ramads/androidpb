package unram.psti.week5_listview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

public class CustomAdapterList extends BaseAdapter {
    private Context context;
    private ArrayList<Club> clubs = new ArrayList<>();

    CustomAdapterList(Context context) {
        this.context = context;
    }

    public void setClubs(ArrayList<Club> clubs) {
        this.clubs = clubs;
    }

    @Override
    public int getCount() {
        return clubs.size();
    }

    @Override
    public Object getItem(int i) {
        return clubs.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View listItem = view;
        if (listItem == null) {
            listItem = LayoutInflater.from(this.context).inflate(
                    R.layout.custom_list_item,
                    viewGroup,
                    false
            );
        }

        ViewHolder viewHolder = new ViewHolder(listItem);

        Club club = (Club) getItem(i);
        viewHolder.bind(club);

        return listItem;
    }

    private class ViewHolder {
        ImageView itemImage;
        TextView itemName;
        TextView itemInfo;

        ViewHolder(View view) {
            itemImage = view.findViewById(R.id.item_image);
            itemName = view.findViewById(R.id.item_name);
            itemInfo = view.findViewById(R.id.item_info);
        }

        void bind(Club club) {
            Glide.with(context)
                    .load(club.getImage())
                    .into(itemImage);
            itemName.setText(club.getName());
            itemInfo.setText(club.getInfo());
        }
    }
}
