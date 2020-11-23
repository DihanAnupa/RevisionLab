package com.example.revisionlab;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.revisionlab.Database.Message;

import java.util.List;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.MessageViewHolder> {

    private List<Message> messageList;
    private Context context;

   public RecyclerAdapter(Context applicatonContext, List<Message> messageList){
       this.context = applicatonContext;
       this.messageList = messageList;
   }


    @NonNull
    @Override
    public RecyclerAdapter.MessageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //inflating recycler item view
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.layout_list, parent, false);
        return  new MessageViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerAdapter.MessageViewHolder holder, int position) {
            holder.sub.setText(messageList.get(position).getSubject());
            holder.msg.setText(messageList.get(position).getMessage());
            holder.uName.setText(messageList.get(position).getUn());

            holder.sub.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MessageView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("subject", messageList.get(position).getSubject());
                    intent.putExtra("message", messageList.get(position).getMessage());
                    context.startActivity(intent);
                }
            });

            holder.msg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context, MessageView.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent.putExtra("subject", messageList.get(position).getSubject());
                    intent.putExtra("message", messageList.get(position).getMessage());
                    context.startActivity(intent);
                }
            });
    }

    @Override
    public int getItemCount() {
        return messageList.size();
    }

    //view holder class
    public class MessageViewHolder extends RecyclerView.ViewHolder{
       TextView sub, msg, uName;

       public MessageViewHolder(View view){
           super(view);
           sub = view.findViewById(R.id.textViewSubject2);
           msg = view.findViewById(R.id.textViewMessage2);
           uName = view.findViewById(R.id.textViewTUsername);
       }

    }

}
