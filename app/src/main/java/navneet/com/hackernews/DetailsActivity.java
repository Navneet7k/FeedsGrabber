package navneet.com.hackernews;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.HashMap;
import java.util.Map;

import navneet.com.hackernews.model.Post;

public class DetailsActivity extends AppCompatActivity {

    public static final String EXTRA_POST_KEY = "post_key";
    public static final String EXTRA_AUTHOR = "author";
    public static final String EXTRA_DESC = "desc";
    public static final String EXTRA_EXP = "exp";
    public static final String EXTRA_IM = "image";
    public static final String EXTRA_URL = "url";
    private TextView textView,text,dat,share,author,more;
    private ImageView imageView;
    private DatabaseReference mDatabase;
   // private WebView webView;
    private Button button;
    private String mPostKey,desc,date,image,auth,read;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
//        getSupportActionBar().setDisplayShowHomeEnabled(true);
//        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        textView=(TextView)findViewById(R.id.textView);
        author=(TextView)findViewById(R.id.textView8);
        text=(TextView)findViewById(R.id.textView2);
        more=(TextView)findViewById(R.id.textView9);
        share=(TextView)findViewById(R.id.textView7);
        button=(Button)findViewById(R.id.button);
        dat=(TextView)findViewById(R.id.textView4);
    //    webView=(WebView)findViewById(R.id.webView);
        imageView=(ImageView)findViewById(R.id.imageView1);


        mDatabase = FirebaseDatabase.getInstance().getReference();



        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse("https://newsapi.org"));
                startActivity(i);
            }
        });

        more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(read));
                startActivity(i);

             //   webView.loadUrl("http://www.google.com");
            }
        });
        share.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent what = new Intent();
                what.setAction(Intent.ACTION_SEND);
                what.putExtra(Intent.EXTRA_TEXT, "\nEvent:" + mPostKey + "\nJob Description:" +desc+ "\nExperience:" + date + "\nCTC:INR 12L - 1NR36L");
                what.setType("text/plain");
 /*               what.putExtra(Intent.EXTRA_STREAM, uri);
                what.setType("image/jpeg");
                */
                startActivity(Intent.createChooser(what, "Share with"));
            }
        });
        read=getIntent().getStringExtra(EXTRA_URL);
        auth= getIntent().getStringExtra(EXTRA_AUTHOR);
        mPostKey = getIntent().getStringExtra(EXTRA_POST_KEY);
        desc = getIntent().getStringExtra(EXTRA_DESC);
        date = getIntent().getStringExtra(EXTRA_EXP);
        image = getIntent().getStringExtra(EXTRA_IM);
        Picasso.with(this).load(image).into(imageView);
        textView.setText(mPostKey);
        text.setText(desc);
        dat.setText(date);
        author.setText(auth);



    }

    private void writeNewPost(String userId, String username, String title, String body) {
        // Create new post at /user-posts/$userid/$postid and at
        // /posts/$postid simultaneously
        String key = mDatabase.child("posts").push().getKey();
        Post post = new Post(userId, username, title, body);
        Map<String, Object> postValues = post.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/posts/" + key, postValues);
        childUpdates.put("/user-posts/" + userId + "/" + key, postValues);

        mDatabase.updateChildren(childUpdates);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                onBackPressed();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
