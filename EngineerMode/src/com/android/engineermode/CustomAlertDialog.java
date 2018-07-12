package com.android.engineermode;

import android.app.AlertDialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class CustomAlertDialog extends AlertDialog {

	protected CustomAlertDialog(Context context) {
		super(context);
	}

	public static class Builder extends android.app.AlertDialog.Builder {

		private Context context;

		public Builder(Context context) {
			super(context);
			this.context = context;
		}

		public Builder setTitle2(int titleId, int buttonId,
				View.OnClickListener listener) {
			return setTitle2(context.getText(titleId), context
					.getText(buttonId), listener);
		}

		public Builder setTitle2(int titleId, CharSequence buttonText,
				View.OnClickListener listener) {
			return setTitle2(context.getText(titleId), buttonText,
					listener);
		}

		public Builder setTitle2(CharSequence title, int buttonId,
				View.OnClickListener listener) {
			return setTitle2(title, context.getText(buttonId),
					listener);
		}

		public Builder setTitle2(CharSequence title,
				CharSequence buttonText, View.OnClickListener listener) {
			View view = LayoutInflater.from(context).inflate(
					R.layout.custom_dialog_title, null);
			ImageView iconView = (ImageView) view.findViewById(R.id.icon);
			iconView.setImageResource(android.R.drawable.ic_dialog_info);
			TextView textView = (TextView) view.findViewById(R.id.text);
			textView.setText(title);
			Button button = (Button) view.findViewById(R.id.button);
			button.setText(buttonText);
			button.setOnClickListener(listener);
			return (Builder) super.setCustomTitle(view);
		}
		
	}

}
