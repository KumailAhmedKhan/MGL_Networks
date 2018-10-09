package com.app.sample.social.MGLNetworks.Adapter;

import android.app.DownloadManager;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.app.sample.social.MGLNetworks.ActivityClass.DetailCompanyActivity;
import com.app.sample.social.MGLNetworks.Interface.ItemClickListener;
import com.app.sample.social.MGLNetworks.Model.CompanyDataModel;
import com.app.sample.social.R;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;
import com.squareup.picasso.Transformation;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class CompanyDataAdapter extends RecyclerView.Adapter<CompanyDataAdapter.MyViewHolder> implements Filterable
{
    private List<CompanyDataModel> companyDataModelList;
    private List<CompanyDataModel> filtered_items = new ArrayList<>() ;
    private ItemFilter mFilter = new ItemFilter();
    private Context context;
    private ItemClickListener clickListener;


    public CompanyDataAdapter() {
    }


    public CompanyDataAdapter(Context ctx,List<CompanyDataModel> data){
        this.companyDataModelList = data;
        this.filtered_items = data;
        this.context = ctx;
    }



    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view;
        view = LayoutInflater.from(context).inflate(R.layout.companydata,parent,false);
        final MyViewHolder myViewHolder = new MyViewHolder(view);
        myViewHolder.membership.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // Toast.makeText(context,"Click"+String.valueOf(myViewHolder.companyname),Toast.LENGTH_LONG).show();

                String ID= String.valueOf(myViewHolder.companyid.getText());
                String Name=String.valueOf(myViewHolder.companyname.getText());
                String imageurl=myViewHolder.imageurl;
                Intent intent=new Intent(context,DetailCompanyActivity.class);
                intent.putExtra("CompanyID",ID);
                intent.putExtra("CompanyName",Name);
                intent.putExtra("CompanyLogoURL",imageurl);
                v.setOnClickListener(null);
                notifyDataSetChanged();
                context.startActivity(intent);


            }
        });
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position)
    {
        if(position %2 == 1)
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#FFFFFF"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFFFF"));
        }
        else
        {
            holder.itemView.setBackgroundColor(Color.parseColor("#e6f7ff"));
            //  holder.imageView.setBackgroundColor(Color.parseColor("#FFFAF8FD"));
        }
           final CompanyDataModel temp = filtered_items.get(position);

        holder.companyname.setText(temp.getCompanyName());
        if (temp.getCompanyCountry().equals("null"))
        {
            holder.companyCountry.setText("N/A");
        }else
            {
            holder.companyCountry.setText(temp.getCompanyCountry());
        }
        if(temp.getCompanyPhoneNo().equals("null"))
        {
            holder.companyphoneno.setText("N/A");
        }else{
            holder.companyphoneno.setText(temp.getCompanyPhoneNo());
        }
        if(temp.getCompanyEmail().equals("null") )
        {
            holder.companyemail.setText("N/A");
        }else
            {
            holder.companyemail.setText(temp.getCompanyEmail());
        }
        holder.companyid.setText(temp.getCompanyId());
        holder.companyid.setVisibility(TextView.INVISIBLE);
        holder.imageurl= temp.getCompanyImageURL();
        Picasso.get().load(temp.getCompanyImageURL().replace(" ", "%20")).fit().into(holder.companylogo);
        //imageDownload(context,temp.getCompanyImageURL());

    }

    @Override
    public int getItemCount() {
        return filtered_items.size();
    }
    public void  setfchat(List<CompanyDataModel> data)throws Exception{
        try{
            filtered_items.clear();
            filtered_items.addAll(data);
            notifyDataSetChanged();
        }catch (Exception e)
        {
            e.printStackTrace();
        }

    }
    public List<CompanyDataModel> getdata(){
        return filtered_items;
    }
    public Filter getFilter() {
        return mFilter;
    }



    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener
    {
        private TextView companyname;
        private TextView companyid;
        private TextView companyCountry;
        private TextView companyemail;
        private TextView companyphoneno;
        private ImageView companylogo;
        LinearLayout membership;
        String imageurl;



        public MyViewHolder(View itemView)
        {
            super(itemView);
            membership = (LinearLayout) itemView.findViewById(R.id.lyt_parent) ;
            companyname =(TextView)  itemView.findViewById(R.id.title);
            companyCountry = (TextView) itemView.findViewById(R.id.Country);
            companyemail = (TextView) itemView.findViewById(R.id.email);
            companyphoneno = (TextView) itemView.findViewById(R.id.phoneno);
            companyid = (TextView) itemView.findViewById(R.id.companyid);
            companylogo = (ImageView) itemView.findViewById(R.id.image);

        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(itemView, getAdapterPosition());
        }
    }
    private class ItemFilter extends Filter {
        @Override
        protected FilterResults performFiltering(CharSequence constraint) {
            String query = constraint.toString().toLowerCase();

            FilterResults results = new FilterResults();
            final List<CompanyDataModel> list = companyDataModelList;
            final List<CompanyDataModel> result_list = new ArrayList<>(list.size());

            for (int i = 0; i < list.size(); i++) {
                String str_title = list.get(i).getCompanyName();
                String str_snippet = list.get(i).getCompanyCountry();
                if (str_title.toLowerCase().contains(query) )
                {
                    result_list.add(list.get(i));
                    //Log.d("word Search","result_list");
                }
                else if (str_snippet.toLowerCase().contains(query))
                {
                    result_list.add(list.get(i));
                }
            }

            results.values = result_list;
            results.count = result_list.size();
           // Log.d("Result", String.valueOf(results.count));
            return results;
        }

        @SuppressWarnings("unchecked")
        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {
            filtered_items = (List<CompanyDataModel>) results.values;
            notifyDataSetChanged();
        }

    }
    public static Bitmap getBitmapFromURL(String src) {
        try {
            Log.e("src", src);
            URL url = new URL(src);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setDoInput(true);
            connection.connect();
            InputStream input = connection.getInputStream();
            Bitmap myBitmap = BitmapFactory.decodeStream(input);
            Log.e("Bitmap", "returned");
            return myBitmap;
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("Exception", e.getMessage());
            return null;
        }
    }

    public void setClickListener(ItemClickListener itemClickListener) {
        this.clickListener = itemClickListener;
    }
    //save image
    public static void imageDownload(Context ctx,String Url){
        Picasso.get()
                .load(Url)
                .into(getTarget(Url));
    }

    //target to save
    private static Target getTarget(final String url)
    {
        Target target = new Target(){

            @Override
            public void onBitmapLoaded(final Bitmap bitmap, Picasso.LoadedFrom from) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        //Log.i("PRODUTOS_FOLDER", CreateAppFolder.getProdutosFolder());
                        File path = Environment.getExternalStoragePublicDirectory(
                                Environment.DIRECTORY_PICTURES);
                        File file = new File(path, "mglnnetworks");
                        //File file = new File(Environment.getExternalStorageDirectory(Environment.DIRECTORY_PICTURES) + url);
                            Log.d("Environment path",path.toString());
                        try {
                            path.mkdirs();
                            file.createNewFile();
                            FileOutputStream ostream = new FileOutputStream(file);
                            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, ostream);
                            ostream.flush();
                            ostream.close();
                        }
                        catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

            }

            @Override
            public void onBitmapFailed(Exception e, Drawable errorDrawable) {

            }


            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        };
        return target;
    }

}
