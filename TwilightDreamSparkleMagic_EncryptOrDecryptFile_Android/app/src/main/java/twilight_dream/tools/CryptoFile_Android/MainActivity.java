package twilight_dream.tools.CryptoFile_Android;

import android.app.*;
import android.content.*;
import android.os.*;
import android.os.Handler;
import android.os.Message;
import android.os.Bundle;
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import java.io.*;

//import java.io.*;
//import java.nio.channels.*;
//import java.nio.file.*;

public class MainActivity extends Activity implements OnClickListener
{
	private EditText sourceFile; //Enter you source file path string in the Android OS EditText Object
	private EditText targetFile; //Enter you target file path string in the Android OS EditText Object

	public EditText passwordText;
	public EditText passwordText2;
	public EditText passwordText3;
	public EditText passwordText4;

	private TextView tvProgressStatus;

	private Handler myHandler = new Handler()
	{
        @Override
        public void handleMessage(Message msg)
		{
            super.handleMessage(msg);
            tvProgressStatus.setText((String)msg.obj);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
		// Create a new one android appcation window
        super.onCreate(savedInstanceState);

		//Load XML file content to configure R.java
        setContentView(R.layout.activity_main);

		setMyView();
		setMyListener();
	}

	protected void setMyView()
	{
		sourceFile = findViewById(R.id.EditText0000);
		targetFile = findViewById(R.id.EditText0001);

		passwordText = findViewById(R.id.EditPassword0000);
		passwordText2 = findViewById(R.id.EditPassword0001);
		passwordText3 = findViewById(R.id.EditPassword0002);
		passwordText4 = findViewById(R.id.EditPassword0003);

        //encryptionSourceButton = (Button) findViewById(R.id.EncryptionButton);
        //decryptionTargetButton = (Button) findViewById(R.id.DecryptionButton);
		
        tvProgressStatus = findViewById(R.id.TV_Progress_Status);
    }
	
	protected void setMyListener()
	{
		findViewById(R.id.ClearTextButton0000).setOnClickListener(this);
		findViewById(R.id.ClearTextButton0001).setOnClickListener(this);
		findViewById(R.id.ClearTextButton0002).setOnClickListener(this);
		findViewById(R.id.ClearTextButton0003).setOnClickListener(this);
		findViewById(R.id.ClearTextButton0004).setOnClickListener(this);
		findViewById(R.id.ClearTextButton0005).setOnClickListener(this);

		findViewById(R.id.FileButton0000).setOnClickListener(this);
		findViewById(R.id.FileButton0001).setOnClickListener(this);
		findViewById(R.id.EncryptionButton).setOnClickListener(this);
		findViewById(R.id.DecryptionButton).setOnClickListener(this);
	}
	
	@Override
	public void onClick(View view_object)
	{
		/* To Do: Implement this method */
		
		//Clear text context for button
		EditText editText0 = findViewById(R.id.EditText0000);
		EditText editText1 = findViewById(R.id.EditPassword0000);
		EditText editText2 = findViewById(R.id.EditPassword0001);
		EditText editText3 = findViewById(R.id.EditPassword0002);
		EditText editText4 = findViewById(R.id.EditPassword0003);
		EditText editText5 = findViewById(R.id.EditText0001);
		
		if (view_object.getId() == R.id.ClearTextButton0000 && editText0.length() > 0)
		{
			editText0.getText().clear();
			editText0.setText("");
		}
		if (view_object.getId() == R.id.ClearTextButton0001 && editText1.length() > 0)
		{
			editText1.getText().clear();
			editText1.setText("");
		}
		if (view_object.getId() == R.id.ClearTextButton0002 && editText2.length() > 0)
		{
			editText2.getText().clear();
			editText2.setText("");
		}
		if (view_object.getId() == R.id.ClearTextButton0003 && editText3.length() > 0)
		{
			editText3.getText().clear();
			editText3.setText("");
		}
		if (view_object.getId() == R.id.ClearTextButton0004 && editText4.length() > 0)
		{
			editText4.getText().clear();
			editText4.setText("");
		}
		if (view_object.getId() == R.id.ClearTextButton0005 && editText5.length() > 0)
		{
			editText5.getText().clear();
			editText5.setText("");
		}
		
		//ShowFileChooser
		if(view_object.getId() == R.id.FileButton0000)
		{
			//Set new intent class the action event for get file content
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			
			intent.setType("*/*");      //Type access all files from new intent
			//intent.setType("text/xml");   //Type access XML file only from new intent
			intent.addCategory(Intent.CATEGORY_OPENABLE); //Can the file category be accessed ?

			try
			{
				startActivityForResult(intent,0); //Call new activity for android inside intent message function the send request code to onActivityResult();
				Toast.makeText(getApplicationContext(), "Opening file manger....... Select a file to encrypt", Toast.LENGTH_LONG).show();
			} 
			catch (android.content.ActivityNotFoundException exceptionActivity) 
			{
				// Potentially direct the user to the Market with a Dialog
				Toast.makeText(getApplicationContext(), "Please you must be install a any file manager.", Toast.LENGTH_LONG).show();
			}
		}
		//ShowFileChooser
		if(view_object.getId() == R.id.FileButton0001)
		{
			//Set new intent class the action event for get file content
			Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
			
			intent.setType("*/*");      //Type access all files from new intent
			//intent.setType("text/xml");   //Type access XML file only from new intent
			intent.addCategory(Intent.CATEGORY_OPENABLE); //Can the file category be accessed ?

			try
			{
				startActivityForResult(intent,0); // Call new activity for android inside intent message function the send request code to onActivityResult();
				Toast.makeText(getApplicationContext(), "Opening file manger....... Select a file to decrypt", Toast.LENGTH_LONG).show();
			} 
			catch (android.content.ActivityNotFoundException exceptionActivity) 
			{
				// Potentially direct the user to the Market with a Dialog
				Toast.makeText(getApplicationContext(), "Please you must be need install a any file manager.", Toast.LENGTH_LONG).show();
			}
		}
		
		if(view_object.getId() == R.id.EncryptionButton)
		{
			new Thread(new Runnable()
			{
                    @Override
                    public void run()
					{	
						/*
						final boolean Success = true;
						final boolean Fail = false;
                        boolean isResult =  FileCustomAlgorithmCipherUtil.dataToEncrypt(encryptSource.getText().toString(), new FileCustomAlgorithmCipherUtil.CipherListener()
						{
								@Override
								public void onProgress(long current, long total)
								{
									Message msg = myHandler.obtainMessage();
									msg.obj = "Encrypting progress：" + current + "/" + total + "(" + current * 100 / total + "%)";
									myHandler.sendMessage(msg);
								}
						});
                        Message msg = myHandler.obtainMessage();
                        if(isResult == Success)
						{
                            msg.obj = "Encrypted File Successful!";
                        }
						else if (isResult == Fail)
						{
                            msg.obj = "Encrypted File Failed";
                        }
                        myHandler.sendMessage(msg);
						*/
						
						final boolean Success = true;
						final boolean Fail = false;
						
						String passwordTextString = passwordText.getText().toString();
						String passwordTextString2 = passwordText2.getText().toString();
						String passwordTextString3 = passwordText3.getText().toString();
						String passwordTextString4 = passwordText4.getText().toString();
						
						if (passwordTextString.length() == 0 || passwordTextString2.length() == 0 || passwordTextString3.length() == 0 || passwordTextString4.length() == 0)
						{
							Toast.makeText(getApplicationContext(), "Oh no, You must be entered the four full password to application",Toast.LENGTH_LONG).show();
							//IOException ioExcept = null;
							//ioExcept.printStackTrace();
						}

						byte[] passwordTextBytes = passwordTextString.getBytes();
						byte[] passwordTextBytes2 = passwordTextString2.getBytes();
						byte[] passwordTextBytes3 = passwordTextString3.getBytes();
						byte[] passwordTextBytes4 = passwordTextString4.getBytes();
						
						//File sourceFileObject = new File(sourceFile.getText().toString());
						//File targetFileObject = new File(targetFile.getText().toString());
						
						try
						{
							boolean isResult =  FileCustomAlgorithmCipherUtil.runEncryptFile(sourceFile.getText().toString(), passwordTextBytes, passwordTextBytes2, passwordTextBytes3, passwordTextBytes4, targetFile.getText().toString(), new FileCustomAlgorithmCipherUtil.CipherListener()
								{
									@Override
									public void onProgress(long current, long total)
									{
										Message msg = myHandler.obtainMessage();
										msg.obj = "Encrypting progress：" + current + "/" + total + "(" + current * 100 / total + "%)";
										myHandler.sendMessage(msg);
									}

								});

							Message msg = myHandler.obtainMessage();
							if(isResult == Success)
							{
								msg.obj = "Encrypted File Successful!";
							}
							else if (isResult == Fail)
							{
								msg.obj = "Encrypted File Failed";
							}
							myHandler.sendMessage(msg);
						}
						catch (IOException IO_Exception)
						{
							IO_Exception.printStackTrace();
						}
					}
			}).start();
		}
		if(view_object.getId() == R.id.DecryptionButton)
		{
			new Thread(new Runnable()
			{
                    @Override
                    public void run()
					{
						/*
						final boolean Success = true;
						final boolean Fail = false;
                        boolean isResult =  FileCustomAlgorithmCipherUtil.dataToDecrypt(decryptTarget.getText().toString(), new FileCustomAlgorithmCipherUtil.CipherListener()
						{
								@Override
								public void onProgress(long current, long total)
								{
									Message msg = myHandler.obtainMessage();
									msg.obj = "Decrypting progress：" + current + "/" + total + "(" + current * 100 / total + "%)";
									myHandler.sendMessage(msg);
								}	
						});
                        Message msg = myHandler.obtainMessage();
                        if(isResult == Success)
						{
							
                            msg.obj = "Decrypted File Successful!";
                        }
						else if (isResult == Fail)
						{
                            msg.obj = "Decrypted File Failed";
                        }
                        myHandler.sendMessage(msg);
						*/
						
						final boolean Success = true;
						final boolean Fail = false;
						
						String passwordTextString = passwordText.getText().toString();
						String passwordTextString2 = passwordText2.getText().toString();
						String passwordTextString3 = passwordText3.getText().toString();
						String passwordTextString4 = passwordText4.getText().toString();
						
						if (passwordTextString.length() == 0 || passwordTextString2.length() == 0 || passwordTextString3.length() == 0 || passwordTextString4.length() == 0)
						{
							Toast.makeText(getApplicationContext(), "Oh no, You must be entered the four full password to application",Toast.LENGTH_LONG).show();
							//IOException ioExcept = null;
							//ioExcept.printStackTrace();
						}

						byte[] passwordTextBytes = passwordTextString.getBytes();
						byte[] passwordTextBytes2 = passwordTextString2.getBytes();
						byte[] passwordTextBytes3 = passwordTextString3.getBytes();
						byte[] passwordTextBytes4 = passwordTextString4.getBytes();
						
						//File sourceFileObject = new File(sourceFile.getText().toString());
						//File targetFileObject = new File(targetFile.getText().toString());

                        try
						{
							boolean isResult =  FileCustomAlgorithmCipherUtil.runDecryptFile(sourceFile.getText().toString(), passwordTextBytes, passwordTextBytes2, passwordTextBytes3, passwordTextBytes4, targetFile.getText().toString(), new FileCustomAlgorithmCipherUtil.CipherListener()
								{
									@Override
									public void onProgress(long current, long total)
									{
										Message msg = myHandler.obtainMessage();
										msg.obj = "Decrypting progress：" + current + "/" + total + "(" + current * 100 / total + "%)";
										myHandler.sendMessage(msg);
									}

								});

							Message msg = myHandler.obtainMessage();
							if(isResult == Success)
							{
								msg.obj = "Decrypted File Successful!";
							}
							else if (isResult == Fail)
							{
								msg.obj = "Decrypted File Failed";
							}
							myHandler.sendMessage(msg);
						}
						catch (IOException IO_Exception)
						{
							IO_Exception.printStackTrace();
						}
					}
			}).start();
		}
	}
	
	//Callback startActivityForResult(); the receive requestCode and resultCode of the function is passed to onActivityResult(); used as a parameter 
	@Override
	protected void onActivityResult(int requestCode,int resultCode,Intent fileData)
	{
		//TODO: AUTO-GENERATED
		
		EditText editFilePathText0 = findViewById(R.id.EditText0000);
		EditText editFilePathText1 = findViewById(R.id.EditText0001);
		//Button selectSourceFileButton = findViewById(R.id.FileButton0000);
		//Button selectTargetFileButton = findViewById(R.id.FileButton0001);
		
		switch(requestCode)
		{
			case 0:

				if(resultCode == RESULT_OK)
				{
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
					
					String FilePathHolder = fileData.getData().getPath();
					Toast.makeText(MainActivity.this, "You selected file is :" + " " + FilePathHolder , Toast.LENGTH_LONG).show();
					
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
					else if(FilePathHolder.length() != 0 && editFilePathText0.length() == 0 && editFilePathText1.length() == 0)
					{
						editFilePathText0.setText(FilePathHolder);
					}
					else if(FilePathHolder.length() == 0 && editFilePathText0.length() == 0 && editFilePathText1.length() == 0)
					{
						Toast.makeText(getApplicationContext(),"Erro: Oh, This File Path Is Empty!",Toast.LENGTH_SHORT).show();
					}
					
				}

			break;
		}
	}
	
}
