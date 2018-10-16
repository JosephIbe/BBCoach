package sportsapp.sashi.in.sportsapp.viewholders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import sportsapp.sashi.in.sportsapp.R;


public class BatchFooterViewHolder extends RecyclerView.ViewHolder {

    public Button proceedBtn;

    public BatchFooterViewHolder(View itemView) {
        super(itemView);

        proceedBtn = itemView.findViewById(R.id.proceedBtn);

    }
}
