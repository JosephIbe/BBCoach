package sportsapp.sashi.in.sportsapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import sportsapp.sashi.in.sportsapp.R;

public class DrawerHeaderItemViewHolder extends RecyclerView.ViewHolder {

    public ImageView iconImg;
    public TextView profileText;
    public RelativeLayout headerProfileLayout;

    public DrawerHeaderItemViewHolder(View itemView) {
        super(itemView);

        iconImg = itemView.findViewById(R.id.headerIconImg);
        profileText = itemView.findViewById(R.id.profileHeaderText);
        headerProfileLayout = itemView.findViewById(R.id.headerProfileLayout);

    }
}
