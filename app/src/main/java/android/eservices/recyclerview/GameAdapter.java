package android.eservices.recyclerview;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class GameAdapter extends RecyclerView.Adapter<GameAdapter.GameViewHolder> {

    private List<GameViewModel> gameViewModels;
    private LayoutInflater mInflater;
    private GameActionInterface gameActionInterface;

    @NonNull
    @Override
    public GameViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        mInflater = LayoutInflater.from(parent.getContext());
        View v = mInflater
                .inflate(R.layout.item_recyclerview, parent, false);
        return new GameViewHolder(v, gameActionInterface);
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

    public GameAdapter(GameActionInterface gameActionInterface){
        this.gameActionInterface = gameActionInterface;
    }

    ///////////////////////// GAMEVIEWHOLDER ///////////////////////////

    public static class GameViewHolder extends RecyclerView.ViewHolder{

        private TextView gameTitleTextView;
        private TextView descriptionTextView;
        private ImageView gameImageView;
        private View view;
        private GameActionInterface gameActionInterface;

        // Buttons
        private ImageButton infoButton;

        public GameViewHolder(View view, GameActionInterface gameActionInterface){
            super(view);
            this.view = view;
            this.gameTitleTextView = view.findViewById(R.id.title_textview);
            this.descriptionTextView = view.findViewById(R.id.description_textview);
            this.gameImageView = view.findViewById(R.id.icon_imageview);
            this.infoButton = view.findViewById(R.id.info_button);
            this.gameActionInterface = gameActionInterface;
        }

        public void bind(final GameViewModel gameViewModel) {
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

            infoButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    gameActionInterface.onGameInfoClicked(gameViewModel.getTitle());
                }
            });

        }
    }
}
