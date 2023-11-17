package com.dineshwayaman.rxjava.Adapters;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.dineshwayaman.rxjava.Models.Repo;
import com.dineshwayaman.rxjava.R;

import java.util.List;

public class RepoRecyclerAdapter extends RecyclerView.Adapter<RepoRecyclerAdapter.RepoViewHolder> {

    private List<Repo> repoList;
    private LayoutInflater inflater;

    public RepoRecyclerAdapter(Context context){
        inflater = LayoutInflater.from(context);

    }

    public void setData(List<Repo> repos){
        repoList = repos;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public RepoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View repoItemView = inflater.inflate(R.layout.repo_items, parent, false);
        return new RepoViewHolder(repoItemView);
    }

    @Override
    public void onBindViewHolder(@NonNull RepoRecyclerAdapter.RepoViewHolder holder, int position) {
            if (repoList != null){
                Repo repoModel = repoList.get(position);
                holder.txtID.setText(String.valueOf(repoModel.getId()));
                holder.txtName.setText(repoModel.getName());
            }
    }

    @Override
    public int getItemCount() {
        return (repoList != null) ? repoList.size() :  0;
    }

    public class RepoViewHolder extends RecyclerView.ViewHolder {
        private final TextView txtID, txtName;
        public RepoViewHolder(@NonNull View itemView) {
            super(itemView);

            txtID = itemView.findViewById(R.id.txtID);
            txtName = itemView.findViewById(R.id.txtRepoName);

        }
    }
}
