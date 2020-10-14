package android.eservices.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<GameViewModel> gameViewModels;

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recyclerview, parent, false);
        return new GameViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull GameViewHolder holder, int position) {
        holder.bind(gameViewModels.get(position));
    }

    @Override
    public int getItemCount() {
        return this.gameViewModels.size();
    }

    public void bindGameViewModelList(List<GameViewModel> gameViewModels){
        this.gameViewModels = gameViewModels;
        notifyDataSetChanged();
    }

    ///////////////////////// GAMEVIEWHOLDER ///////////////////////////

    public static class GameViewHolder extends RecyclerView.ViewHolder{

        private TextView gameTitleTextView;
        private TextView descriptionTextView;
        private ImageView gameImageView;
        private View view;

        public GameViewHolder(View view){
            super(view);
            this.view = view;
            this.gameTitleTextView = view.findViewById(R.id.title_textview);
            this.descriptionTextView = view.findViewById(R.id.description_textview);
            this.gameImageView = view.findViewById(R.id.icon_imageview);
        }

        public void bind(GameViewModel gameViewModel) {
            this.gameTitleTextView.setText(gameViewModel.getTitle());
            this.descriptionTextView.setText(gameViewModel.getDescription());

            /*
            GlideApp.with(this)
                .load("http://via.placeholder.com/300.png")
                .transform(new CircleCrop())
                .into(ivImg);
             */

            Glide.with(this.view)
                    .load(gameViewModel.getImageUrl())
                    .into(gameImageView);
        }

    }
}
