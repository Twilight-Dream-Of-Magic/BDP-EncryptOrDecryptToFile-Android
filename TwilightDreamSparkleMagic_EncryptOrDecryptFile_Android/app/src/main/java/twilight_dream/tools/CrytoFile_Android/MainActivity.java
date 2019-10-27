package twilight_dream.tools.CrytoFile_Android;

import android.app.*;
import android.content.*;
import android.os.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.sql.*;

public class MainActivity extends Activity
{

	public void startActivityForResult(Intent createChooser)
	{
		// TODO: Implement this method
	}
	
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		// Create a new one android appcation window
        super.onCreate(savedInstanceState);
		
		//Load XML file content to configure R.java
        setContentView(R.layout.activity_main);
		
		myOnClickButtonToClearEditText();
		showFileObjectFromOnClickFileButton ();

	}
	
	public void myOnClickButtonToClearEditText ()
	{
		final EditText editText0 = findViewById(R.id.EditText0000);
		Button clearTextButton0 = findViewById(R.id.ClearButton0000);
		final EditText editText1 = findViewById(R.id.EditPassword0000);
		Button clearTextButton1 = findViewById(R.id.ClearButton0001);
		final EditText editText2 = findViewById(R.id.EditPassword0001);
		Button clearTextButton2 = findViewById(R.id.ClearButton0002);
		final EditText editText3 = findViewById(R.id.EditPassword0002);
		Button clearTextButton3 = findViewById(R.id.ClearButton0003);
		final EditText editText4 = findViewById(R.id.EditPassword0003);
		Button clearTextButton4 = findViewById(R.id.ClearButton0004);
		final EditText editText5 = findViewById(R.id.EditText0001);
		Button clearTextButton5 = findViewById(R.id.ClearButton0005);

		clearTextButton0.setOnClickListener(new OnClickListener()
		{

			@Override
			public void onClick(View view_object)
			{
				// TODO Auto-generated method stub
				if (view_object.getId() == R.id.ClearButton0000 && editText0.length() > 0)
				{
					editText0.getText().clear();
					editText0.setText("");
				}
			}
		});
		clearTextButton1.setOnClickListener(new OnClickListener()
		{
				@Override
				public void onClick(View view_object)
				{
					if (view_object.getId() == R.id.ClearButton0001 && editText1.length() > 0)
					{
						editText1.getText().clear();
						editText1.setText("");
					}
				}
			
		});
		clearTextButton2.setOnClickListener(new OnClickListener()
		{
				@Override
				public void onClick(View view_object)
				{
					if (view_object.getId() == R.id.ClearButton0002 && editText2.length() > 0)
					{
						editText2.getText().clear();
						editText2.setText("");
					}

				}

		});
		clearTextButton3.setOnClickListener(new OnClickListener()
		{
				@Override
				public void onClick(View view_object)
				{
					if (view_object.getId() == R.id.ClearButton0003 && editText3.length() > 0)
					{
						editText3.getText().clear();
						editText3.setText("");
					}
				}

		});
		clearTextButton4.setOnClickListener(new OnClickListener()
		{
				@Override
				public void onClick(View view_object)
				{
					if (view_object.getId() == R.id.ClearButton0004 && editText4.length() > 0)
					{
						editText4.getText().clear();
						editText4.setText("");
					}

				}

		});
		clearTextButton5.setOnClickListener(new OnClickListener()
		{
				@Override
				public void onClick(View view_object)
				{
					if (view_object.getId() == R.id.ClearButton0005 && editText5.length() > 0)
					{
						editText5.getText().clear();
						editText5.setText("");
					}

				}

		});
	}
	
	public void showFileObjectFromOnClickFileButton()
	{
		Button fileObjectButton0 = findViewById(R.id.FileButton0000);
		Button fileObjectButton1 = findViewById(R.id.FileButton0001);
		
		fileObjectButton0.setOnClickListener(new View.OnClickListener()
		{
			@Override
			public void onClick(View view_object)
			{
				//ShowFileChooser
				if(view_object.getId() == R.id.FileButton0000)
				{
					Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
					intent.setType("*/*");      //all files
					//intent.setType("text/xml");   //XML file only
					intent.addCategory(Intent.CATEGORY_OPENABLE);

					try
					{
						startActivityForResult(intent,0);
						Toast.makeText(getApplicationContext(), "Opening file manger....... Select a file to encrypt", Toast.LENGTH_LONG).show();
					} 
					catch (android.content.ActivityNotFoundException exception) 
					{
						// Potentially direct the user to the Market with a Dialog
						Toast.makeText(getApplicationContext(), "Please install a file manager.", Toast.LENGTH_LONG).show();
					}
				}
			}
			
		});
		
		fileObjectButton1.setOnClickListener(new View.OnClickListener()
		{
				@Override
				public void onClick(View view_object)
				{
					//ShowFileChooser
					if(view_object.getId() == R.id.FileButton0001)
					{
						Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
						intent.setType("*/*");      //all files
						//intent.setType("text/xml");   //XML file only
						intent.addCategory(Intent.CATEGORY_OPENABLE);

						try
						{
							startActivityForResult(intent,0);
							Toast.makeText(getApplicationContext(), "Opening file manger....... Select a file to decrypt", Toast.LENGTH_LONG).show();
						} 
						catch (android.content.ActivityNotFoundException exception) 
						{
							// Potentially direct the user to the Market with a Dialog
							Toast.makeText(getApplicationContext(), "Please install a file manager.", Toast.LENGTH_LONG).show();
						}
					}
				}

		});
	}
	
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent fileData)
	{
		//TODO: AUTO-GENERATED
		
		final EditText editFilePathText0 = findViewById(R.id.EditText0000);
		final EditText editFilePathText1 = findViewById(R.id.EditText0001);
		Button encryptionFileButton = findViewById(R.id.FileButton0000);
		Button decryptionFileButton = findViewById(R.id.FileButton0001);
		
		switch(requestCode)
		{
			case 0:

				if(resultCode == RESULT_OK)
				{
					String FilePathHolder = fileData.getData().getPath();
					Toast.makeText(MainActivity.this, "You selected file is :" + " " + FilePathHolder , Toast.LENGTH_LONG).show();
					/*
					encryptionFileButton.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View view_object)
						{
							if(FilePathHolder.length() != 0 && editFilePathText0.length() == 0 && editFilePathText1.length() == 0)
							{
								editFilePathText0.setText(FilePathHolder);
							}
						}

					});
					*/

					/*
					decryptionFileButton.setOnClickListener(new OnClickListener()
					{
						@Override
						public void onClick(View view_object)
						{
							if(FilePathHolder.length() != 0 && editFilePathText0.length() == 0 && editFilePathText1.length() == 0)
							{
								editFilePathText1.setText(FilePathHolder);
							}
						}

					});
					*/
					
					if(FilePathHolder.length() != 0 && editFilePathText0.length() > 0 && editFilePathText1.length() > 0)
					{
						editFilePathText0.getText().clear();
						editFilePathText0.setText("");
						editFilePathText1.getText().clear();
						editFilePathText1.setText("");
					}
					else if(FilePathHolder.length() != 0 && editFilePathText0.length() == 0 && editFilePathText1.length() > 0)
					{
						editFilePathText0.setText(FilePathHolder);
					}
					else if(FilePathHolder.length() != 0 && editFilePathText0.length() > 0 && editFilePathText1.length() == 0)
					{
						editFilePathText1.setText(FilePathHolder);
					}
					else if(FilePathHolder.length() == 0 && editFilePathText0.length() == 0 && editFilePathText1.length() == 0)
					{
						Toast.makeText(getApplicationContext(),"Erro: Oh, This File Path Is Empty!",Toast.LENGTH_SHORT).show();
					}
					if(FilePathHolder.length() != 0 && editFilePathText0.length() == 0 && editFilePathText1.length() == 0)
					{
						editFilePathText0.setText(FilePathHolder);
					}
					
				}

			break;
		}
	}
	
}
