package navigation.drawer.below.action.bar.with.tab.inside.fragment;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.List;

public class MenuItemAdapter extends BaseAdapter
{
    private Activity activity;
    private LayoutInflater layoutInflater;
    private List<DrawerMenuItem> drawerMenuItems;

    public MenuItemAdapter(Activity activity, List<DrawerMenuItem> itemList)
    {
        this.drawerMenuItems = itemList;
        this.activity = activity;
        layoutInflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount()
    {
        return drawerMenuItems.size();
    }

    @Override
    public DrawerMenuItem getItem(int position)
    {
        return drawerMenuItems.get(position);
    }

    @Override
    public long getItemId(int position)
    {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        DrawerViewHolder drawerViewHolder;
        if (convertView == null)
        {
            convertView = layoutInflater.inflate(R.layout.navigation_drawer_menu,null);
            drawerViewHolder = new DrawerViewHolder(convertView);
            convertView.setTag(drawerViewHolder);
        }
        else
        {
            drawerViewHolder = (DrawerViewHolder) convertView.getTag();
        }

        DrawerMenuItem currentListItem= getItem(position);
        drawerViewHolder.title.setText(currentListItem.getTitle());
        drawerViewHolder.subTitle.setText(currentListItem.getSubTitle());
        drawerViewHolder.icon.setImageResource(currentListItem.getIcon());
        return convertView;
    }

    private class DrawerViewHolder
    {
        ImageView icon;
        TextView title,subTitle;

        DrawerViewHolder(View item)
        {
            title = (TextView) item.findViewById(R.id.title);
            subTitle = (TextView) item.findViewById(R.id.subTitle);
            icon = (ImageView) item.findViewById(R.id.icon);
        }
    }
}