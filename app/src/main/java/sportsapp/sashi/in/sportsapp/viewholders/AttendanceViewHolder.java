package sportsapp.sashi.in.sportsapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import de.hdodenhof.circleimageview.CircleImageView;
import sportsapp.sashi.in.sportsapp.R;

public class AttendanceViewHolder extends RecyclerView.ViewHolder {

    public CircleImageView circleImageView;
    public CheckBox attendanceCheckBox;
    public TextView playerNameTV;

    public AttendanceViewHolder(View itemView) {
        super(itemView);

        this.setIsRecyclable(false);

        circleImageView = itemView.findViewById(R.id.player_img);
        attendanceCheckBox = itemView.findViewById(R.id.attendanceCheckBox);
        playerNameTV = itemView.findViewById(R.id.playerNameTV);

    }
}
