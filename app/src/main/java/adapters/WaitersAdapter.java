package adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.elpaisanomateus.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import java.util.ArrayList;
import java.util.Locale;
import bussiness.Waiter;

public class WaitersAdapter extends RecyclerView.Adapter<WaitersAdapter.ViewHolder> implements View.OnClickListener{

    private final int resources;
    private final ArrayList<Waiter> waiters;
    private View.OnClickListener listener;
    public FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
    public DatabaseReference databaseReference;

    public WaitersAdapter(ArrayList<Waiter> waiters, int resources){
        this.waiters = waiters;
        this.resources = resources;

    }
    private final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private static final FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder().build();
    private static final StorageReference mStorageRef = FirebaseStorage.getInstance().getReference();

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(resources,parent,false);
        view.setOnClickListener(this);
        return new ViewHolder(view);

    }

    @SuppressLint("NotifyDataSetChanged")
    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int i) {
        Waiter waiter = waiters.get(i);
        holder.textViewName.setText(waiter.getName());
        holder.eliminateWaiterButton.setOnClickListener(v -> {
            databaseReference = firebaseDatabase.getReference("waiters/" + waiter.getName().toLowerCase(Locale.ROOT));
            databaseReference.removeValue();
            waiters.remove(i);
            notifyItemRemoved(i);
            notifyDataSetChanged();
        });
        //downloadPhoto("waiters/" + waiter.getName() + ".jpg", holder.imageViewWaiter);

    }

    @Override
    public int getItemCount() {
        return waiters.size();
    }

    public void setOnClickListener(View.OnClickListener listener){
        this.listener = listener;
    }

    @Override
    public void onClick(View v) {
        if(listener != null){
            listener.onClick(v);
        }
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView textViewName;
        private ImageView imageViewWaiter;
        private final Button eliminateWaiterButton;

        public View view;

        private ViewHolder(View view){
            super(view);
            this.view = view;
            this.textViewName = view.findViewById(R.id.textViewName);
            this.imageViewWaiter = view.findViewById(R.id.imageViewWaiter);
            this.eliminateWaiterButton = view.findViewById(R.id.eliminateWaiterButton);
        }
    }

    /*private void downloadPhoto(String ruta, final ImageView iv) {
        db.setFirestoreSettings(settings);
        StorageReference photoRef = mStorageRef.child(ruta);
        final long ONE_MEGABYTE = 1024 * 1024 * 10; //(1024 bytes = 1 KB) x (1024 = 1 MB) x 1 = 1 MB
        photoRef.getBytes(ONE_MEGABYTE).addOnSuccessListener(new OnSuccessListener<byte[]>()
        {
            @Override
            public void onSuccess(byte[] bytes) {
                iv.setImageBitmap(BitmapFactory.decodeByteArray(bytes, 0, bytes.length));
            }
        }).addOnFailureListener(exception -> {
            // Handle any errors
        });
    }*/
}
