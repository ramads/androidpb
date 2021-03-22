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

public class CustomAdapterGrid extends BaseAdapter {
    private Context context;
    private ArrayList<Club> clubs = new ArrayList<>();

    CustomAdapterGrid(Context context) {
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
        View gridItem = view;
        if (gridItem == null) {
            gridItem = LayoutInflater.from(this.context).inflate(
                    R.layout.custom_grid_item,
                    viewGroup,
                    false
            );
        }

        ViewHolder viewHolder = new ViewHolder(gridItem);

        Club club = (Club) getItem(i);
        viewHolder.bind(club);

        return gridItem;
    }

    private class ViewHolder {
        ImageView itemImage;
        TextView itemName;

        ViewHolder(View view) {
            itemImage = view.findViewById(R.id.gridClubImage);
            itemName = view.findViewById(R.id.gridClubName);
        }

        void bind(Club club) {
            Glide.with(context)
                    .load(club.getImage())
                    .into(itemImage);
            itemName.setText(club.getName());
        }
    }
}
