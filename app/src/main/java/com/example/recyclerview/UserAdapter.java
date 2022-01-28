package com.example.recyclerview;

import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

class UserItemViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener{
    private TextView name;
    private TextView followers;
    private TextView contributions;
    private UserAdapter.OnItemClickListener mItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClicked(int position);
    }

    public UserItemViewHolder(@NonNull View itemView, UserAdapter.OnItemClickListener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.user_name);
        followers = itemView.findViewById(R.id.followers);
        contributions = itemView.findViewById(R.id.contributions);
        mItemClickListener = listener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this::onLongClick);
    }

    public void bind(@NonNull UserModel model) {
        name.setText(model.getName());
        followers.setText(model.getFollowers());
        contributions.setText(model.getContributions());
    }

    @Override
    public void onClick(View v) {
//        mItemClickListener.onItemClicked(getBindingAdapterPosition());
        Toast.makeText(v.getContext(), "Type#a: followers:"+ followers.getText()+", contributions:"+contributions.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setTitle("Delete?");
        alertDialogBuilder.setMessage("Delete " + name.getText() + "?");
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                onItemLongClickListener.onItemLongClicked(getBindingAdapterPosition());
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                AlertDialog mDialog =alertDialogBuilder.create();
                mDialog.cancel();
            }
        });
        AlertDialog mDialog =alertDialogBuilder.create();
        mDialog.show();
        return false;
    }
}

class UserItemWithLocationViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener,View.OnLongClickListener {
    private TextView name;
    private TextView followers;
    private TextView contributions;
    private TextView locations;
    private UserAdapter.OnItemClickListener mItemClickListener;
    private OnItemLongClickListener onItemLongClickListener;

    public interface OnItemLongClickListener {
        void onItemLongClicked(int position);
    }

    public UserItemWithLocationViewHolder(@NonNull View itemView, UserAdapter.OnItemClickListener listener) {
        super(itemView);
        name = itemView.findViewById(R.id.user_name);
        followers = itemView.findViewById(R.id.followers);
        contributions = itemView.findViewById(R.id.contributions);
        locations = itemView.findViewById(R.id.location);
        mItemClickListener = listener;
        itemView.setOnClickListener(this);
        itemView.setOnLongClickListener(this::onLongClick);
    }

    public void bind(@NonNull UserModel model) {
        name.setText(model.getName());
        followers.setText(model.getFollowers());
        contributions.setText(model.getContributions());
        locations.setText(model.getLocations());
    }

    @Override
    public void onClick(View v) {
//        mItemClickListener.onItemClicked(getBindingAdapterPosition());
        Toast.makeText(v.getContext(), "Type#B: followers:"+ followers.getText()+", contributions:"+contributions.getText(),Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onLongClick(View view) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(view.getContext());
        alertDialogBuilder.setTitle("Delete?");
        alertDialogBuilder.setMessage("Delete "+ name.getText() + "?");
        alertDialogBuilder.setCancelable(true);

        alertDialogBuilder.setPositiveButton("Delete", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface arg0, int arg1)
            {
                onItemLongClickListener.onItemLongClicked(getBindingAdapterPosition());
            }
        });

        alertDialogBuilder.setNegativeButton("Cancel", new DialogInterface.OnClickListener()
        {
            @Override
            public void onClick(DialogInterface dialog, int which)
            {
                AlertDialog mDialog = alertDialogBuilder.create();
                mDialog.cancel();
            }
        });
        AlertDialog mDialog =alertDialogBuilder.create();
        mDialog.show();
        return false;
    }
}



public class UserAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements UserItemViewHolder.OnItemLongClickListener,UserItemWithLocationViewHolder.OnItemLongClickListener{
    private List<UserModel> userModelList;
    private Context context;
    OnItemClickListener mItemClickListener;

    private static final int VIEW_TYPE_WITHOUT_LOCATION = 0;
    private static final int VIEW_TYPE_WITH_LOCATION = 1;


    public UserAdapter(MainActivity mainActivity, List<UserModel> userModelList) {
        this.context = mainActivity;
        this.userModelList = userModelList;
    }

    @Override
    public int getItemViewType(int position) {
        if(position%3 == 0)
            return VIEW_TYPE_WITHOUT_LOCATION;
        else
            return VIEW_TYPE_WITH_LOCATION;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if(viewType == VIEW_TYPE_WITHOUT_LOCATION) {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.github_user_view, parent, false);
            return new UserItemViewHolder(view, mItemClickListener);
        } else {
            final View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.github_user_view_with_location, parent, false);
            return new UserItemWithLocationViewHolder(view, mItemClickListener);
        }
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        if (holder.getItemViewType() == VIEW_TYPE_WITHOUT_LOCATION) {
            ((UserItemViewHolder) holder).bind(userModelList.get(position));
        } else {
            ((UserItemWithLocationViewHolder) holder).bind(userModelList.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return userModelList.size();
    }

    @Override
    public void onItemLongClicked(int position) {
        userModelList.remove(position);
        notifyDataSetChanged();
    }

    public interface OnItemClickListener {
        void onItemClicked(int position);
    }
}
