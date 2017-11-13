// WeatherArrayAdapter.java
// An ArrayAdapter for displaying a List<News>'s elements in a ListView
package org.apps.ygalusha.weatherviewer;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class NewsArrayAdapter extends ArrayAdapter<News> {
   // class for reusing views as list items scroll off and onto the screen
   private static class ViewHolder {
      TextView titleTextView;
      TextView datePubView;
   }


   // constructor to initialize superclass inherited members
   public NewsArrayAdapter(Context context, List<News> forecast) {
      super(context, -1, forecast);
   }

   // creates the custom views for the ListView's items
   @Override
   public View getView(int position, View convertView, ViewGroup parent) {
      // get News object for this specified ListView position
      News day = getItem(position);

      ViewHolder viewHolder; // object that reference's list item's views

      // check for reusable ViewHolder from a ListView item that scrolled
      // offscreen; otherwise, create a new ViewHolder
      if (convertView == null) { // no reusable ViewHolder, so create one
         viewHolder = new ViewHolder();
         LayoutInflater inflater = LayoutInflater.from(getContext());
         convertView =
            inflater.inflate(R.layout.list_item, parent, false);
         viewHolder.titleTextView = convertView.findViewById(R.id.titleTextView);
         viewHolder.datePubView = convertView.findViewById(R.id.datePubView);
         convertView.setTag(viewHolder);
      }
      else { // reuse existing ViewHolder stored as the list item's tag
         viewHolder = (ViewHolder) convertView.getTag();
      }

      // get other data from News object and place into views
      Context context = getContext(); // for loading String resources
      viewHolder.titleTextView.setText(Html.fromHtml(day.title));
      viewHolder.titleTextView.setClickable(true);
      viewHolder.titleTextView.setMovementMethod(LinkMovementMethod.getInstance());
      viewHolder.datePubView.setText(
         context.getString(R.string.date_pub, day.publishDate));

      return convertView; // return completed list item to display
   }

}

/**************************************************************************
 * (C) Copyright 1992-2016 by Deitel & Associates, Inc. and               *
 * Pearson Education, Inc. All Rights Reserved.                           *
 *                                                                        *
 * DISCLAIMER: The authors and publisher of this book have used their     *
 * best efforts in preparing the book. These efforts include the          *
 * development, research, and testing of the theories and programs        *
 * to determine their effectiveness. The authors and publisher make       *
 * no warranty of any kind, expressed or implied, with regard to these    *
 * programs or to the documentation contained in these books. The authors *
 * and publisher shall not be liable in any event for incidental or       *
 * consequential damages in connection with, or arising out of, the       *
 * furnishing, performance, or use of these programs.                     *
 **************************************************************************/
