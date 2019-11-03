package twilight_dream.tools.CrytoFile_Android;


import android.os.*;
import android.widget.*;
import java.io.*;
import java.nio.channels.*;
import java.nio.file.*;

public class FileCustomAlgorithmCipherUtil
{
	public static int bytesToIntager(byte[] value) {
        int intager = 0;
        for (int i = 0; i < value.length; i++) {
            intager += (value[value.length - i - 1] & 0xFF) << (i * 8);
        }
        return intager;
    }
	
    /**
     * Encrypt has been done to set this file extend name
     */
    public static final String CIPHER_TEXT_SUFFIX = ".protectedFile-byTDOM";

    /**
     * Working of 32 kilo-byte then set file data group to operation
     */
    private static final int CIPHER_BUFFER_LENGTH = 32 * 1024;
	
	public static boolean runEncryptFile (String sourceFile, byte[] encryptKey, byte[] encryptKey2, byte[] encryptKey3, byte[] encryptKey4, String targetFile, CipherListener listener)
	{
		try
		{
			//byte[] encByteKey = encryptKey.getBytes();
			//byte[] encByteKey2 = encryptKey2.getBytes();
			//byte[] encByteKey3 = encryptKey3.getBytes();
			//byte[] encByteKey4 = encryptKey4.getBytes();

			FileInputStream fis = new FileInputStream(sourceFile);
			FileOutputStream fos = new FileOutputStream(targetFile);

			if(fis != null && fos != null)
			{
				for(int start_module = 0, stop_module = 512 ; start_module <= stop_module ; start_module++)
				{
					/*
					Path sourceFilePath = Paths.get(sourceFile.getText().toString());
					Path targetFilePath = Paths.get(targetFile.getText().toString());
					try
					{
						FileChannel targetFilePathChannel = FileChannel.open(targetFilePath);
						FileChannel sourceFilePathChannel = FileChannel.open(sourceFilePath);

						long currentSize = targetFilePathChannel.size();
						long totalSize = sourceFilePathChannel.size();

						for(;;)
						{

							Message msg = myHandler.obtainMessage();
							msg.obj = "Decrypting progress：" + currentSize + "/" + totalSize + "(" + currentSize * 100 / totalSize + "%)";
							myHandler.sendMessage(msg);

							if (currentSize == totalSize)
							{
								targetFilePathChannel.close();
								sourceFilePathChannel.close();
								break;
							}
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					*/
					
					if (listener != null)
					{
                        listener.onProgress(targetFile.length(), sourceFile.length());
                    }
					
					for(int start_group = 0, stop_group = 256 ; start_group <= stop_group ; start_group++)
					{
						byte[] temporaryBuffer = Files.readAllBytes(Paths.get(sourceFile.toString()));

						int readFileToGetBytesSize = fis.read(temporaryBuffer);
						while( readFileToGetBytesSize != -1 )
						{
							for( int count = 0, index = 0; count < readFileToGetBytesSize; count++, index++ )
							{
								temporaryBuffer[count] ^= encryptKey[index % encryptKey.length];
							}
							fos.write(temporaryBuffer);
							readFileToGetBytesSize = 0;
						}
						fos.flush();
						fos.close();
						fis.close();
					}
					for(int start_group = 0, stop_group = 256 ; start_group <= stop_group ; start_group++)
					{
						byte[] temporaryBuffer = Files.readAllBytes(Paths.get(sourceFile.toString()));
						
						int readFileToGetBytesSize = fis.read(temporaryBuffer);
						while( readFileToGetBytesSize != -1 )
						{
							for( int count = 0, index = 0; count < readFileToGetBytesSize; count++, index++ )
							{
								temporaryBuffer[count] ^= encryptKey2[index % encryptKey2.length];
							}
							fos.write(temporaryBuffer);
							readFileToGetBytesSize = 0;
						}
						fos.flush();
						fos.close();
						fis.close();
					}
					for(int start_group = 0, stop_group = 256 ; start_group <= stop_group ; start_group++)
					{
						byte[] temporaryBuffer = Files.readAllBytes(Paths.get(sourceFile.toString()));

						int readFileToGetBytesSize = fis.read(temporaryBuffer);
						while( readFileToGetBytesSize != -1 )
						{
							for( int count = 0, index = 0; count < readFileToGetBytesSize; count++, index++ )
							{
								temporaryBuffer[count] ^= encryptKey3[index % encryptKey3.length];
							}
							fos.write(temporaryBuffer);
							readFileToGetBytesSize = 0;
						}
						fos.flush();
						fos.close();
						fis.close();
					}
					for(int start_group = 0, stop_group = 256 ; start_group <= stop_group ; start_group++)
					{
						byte[] temporaryBuffer = Files.readAllBytes(Paths.get(sourceFile.toString()));

						int readFileToGetBytesSize = fis.read(temporaryBuffer);
						while( readFileToGetBytesSize != -1 )
						{
							for( int count = 0, index = 0; count < readFileToGetBytesSize; count++, index++ )
							{
								temporaryBuffer[count] ^= encryptKey4[index % encryptKey4.length];
							}
							fos.write(temporaryBuffer);
							readFileToGetBytesSize = 0;
						}
						fos.flush();
						fos.close();
						fis.close();
					}
				}
				//对加密后的文件重命名，增加.protectedFile-byTDOM后缀
				File fileobject = new File(targetFile);
				fileobject.renameTo(new File(fileobject.getPath() + CIPHER_TEXT_SUFFIX));
				return true;
			}
			else if(fis == null || fos == null)
			{
				System.out.println("sourceFile or targetFile opening the failed");
				return false;
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
		System.out.println("Unknow error");
		return false;
	}

	public static boolean runDecryptFile (String sourceFile, byte[] decryptKey, byte[] decryptKey2, byte[] decryptKey3, byte[] decryptKey4, String targetFile, CipherListener listener)
	{
		try
		{
			//byte[] decByteKey = decryptKey.getBytes();
			//byte[] decByteKey2 = decryptKey2.getBytes();
			//byte[] decByteKey3 = decryptKey3.getBytes();
			//byte[] decByteKey4 = decryptKey4.getBytes();

			FileInputStream fis = new FileInputStream(sourceFile);
			FileOutputStream fos = new FileOutputStream(targetFile);
			
			if(fis != null || fos != null)
			{
				for(int start_module = 0, stop_module = 512 ; start_module <= stop_module ; start_module++)
				{
					/*
					Path sourceFilePath = Paths.get(sourceFile.getText().toString());
					Path targetFilePath = Paths.get(targetFile.getText().toString());
					try
					{
						FileChannel targetFilePathChannel = FileChannel.open(targetFilePath);
						FileChannel sourceFilePathChannel = FileChannel.open(sourceFilePath);

						long currentSize = targetFilePathChannel.size();
						long totalSize = sourceFilePathChannel.size();

						for(;;)
						{

							Message msg = myHandler.obtainMessage();
							msg.obj = "Decrypting progress：" + currentSize + "/" + totalSize + "(" + currentSize * 100 / totalSize + "%)";
							myHandler.sendMessage(msg);

							if (currentSize == totalSize)
							{
								targetFilePathChannel.close();
								sourceFilePathChannel.close();
								break;
							}
						}
					}
					catch (IOException e)
					{
						e.printStackTrace();
					}
					*/
					
					if (listener != null)
					{
                        listener.onProgress(targetFile.length(), sourceFile.length());
                    }
					
					for(int start_group = 0, stop_group = 256 ; start_group <= stop_group ; start_group++)
					{
						byte[] temporaryBuffer = Files.readAllBytes(Paths.get(sourceFile.toString()));

						int readFileToGetBytesSize = fis.read(temporaryBuffer);
						while( readFileToGetBytesSize != -1 )
						{
							for( int count = 0, index = 0; count < readFileToGetBytesSize; count++, index++)
							{
								temporaryBuffer[count] ^= decryptKey[index % decryptKey.length];
							}
							fos.write(temporaryBuffer);
							readFileToGetBytesSize = 0;
						}
						fos.flush();
						fos.close();
						fis.close();
					}
					for(int start_group = 0, stop_group = 256 ; start_group <= stop_group ; start_group++)
					{
						byte[] temporaryBuffer = Files.readAllBytes(Paths.get(sourceFile.toString()));

						int readFileToGetBytesSize = fis.read(temporaryBuffer);
						while( readFileToGetBytesSize != -1 )
						{
							for( int count = 0, index = 0; count < readFileToGetBytesSize; count++, index++)
							{
								temporaryBuffer[count] ^= decryptKey2[index % decryptKey2.length];
							}
							fos.write(temporaryBuffer);
							readFileToGetBytesSize = 0;
						}
						fos.flush();
						fos.close();
						fis.close();
					}
					for(int start_group = 0, stop_group = 256 ; start_group <= stop_group ; start_group++)
					{
						byte[] temporaryBuffer = Files.readAllBytes(Paths.get(sourceFile.toString()));

						int readFileToGetBytesSize = fis.read(temporaryBuffer);
						while( readFileToGetBytesSize != -1 )
						{
							for( int count = 0, index = 0; count < readFileToGetBytesSize; count++, index++)
							{
								temporaryBuffer[count] ^= decryptKey3[index % decryptKey3.length];
							}
							fos.write(temporaryBuffer);
							readFileToGetBytesSize = 0;
						}
						fos.flush();
						fos.close();
						fis.close();
					}
					for(int start_group = 0, stop_group = 256 ; start_group <= stop_group ; start_group++)
					{
						byte[] temporaryBuffer = Files.readAllBytes(Paths.get(sourceFile.toString()));

						int readFileToGetBytesSize = fis.read(temporaryBuffer);
						while( readFileToGetBytesSize != -1 )
						{
							for( int count = 0, index = 0; count < readFileToGetBytesSize; count++, index++)
							{
								temporaryBuffer[count] ^= decryptKey4[index % decryptKey4.length];
							}
							fos.write(temporaryBuffer);
							readFileToGetBytesSize = 0;
						}
						fos.flush();
						fos.close();
						fis.close();
					}
				}
				//对解密后的文件重命名，减去.protectedFile-byTDOM后缀//
				File fileobject = new File(targetFile);
				fileobject.renameTo(new File(fileobject.getPath().substring(fileobject.getPath().indexOf(CIPHER_TEXT_SUFFIX))));
				return true;
			}
			else if(fis == null || fos == null)
			{
				System.out.println("sourceFile or targetFile opening the failed");
				return false;
			}
		}
		catch(Exception exception)
		{
			exception.printStackTrace();
			return false;
		}
		System.out.println("Unknow error");
		return false;
	}

    /**
     * 加密，这里主要是演示加密的原理，没有用什么实际的加密算法
     *
     * @param filePath : Human can readily the original file data of full path
     * @return
     */
	
	/* 
	
    public static boolean dataToEncrypt(String filePath, CipherListener listener)
	{
        try
		{
            long startTime = System.currentTimeMillis();
            File fileobject = new File(filePath);
            RandomAccessFile raf = new RandomAccessFile(fileobject, "rw");
            long totalLength = raf.length();
            FileChannel channel = raf.getChannel();

            long multiples = totalLength / CIPHER_BUFFER_LENGTH;
            long remainder = totalLength % CIPHER_BUFFER_LENGTH;

            MappedByteBuffer buffer = null;
            byte temporary;
            byte rawByte;

            //先对整除部分加密
            for (int count = 0; count < multiples; count++)
			{
                buffer = channel.map(FileChannel.MapMode.READ_WRITE, count * CIPHER_BUFFER_LENGTH, (count + 1) * CIPHER_BUFFER_LENGTH);

                //此处的加密方法很简单，只是简单的异或计算
                for (int loopdata = 0; loopdata < CIPHER_BUFFER_LENGTH; ++loopdata)
				{
                    rawByte = buffer.get(loopdata);
                    temporary = (byte) (rawByte ^ loopdata);
                    buffer.put(loopdata, temporary);

                    if (listener != null)
					{
                        listener.onProgress(count * CIPHER_BUFFER_LENGTH + loopdata, totalLength);
                    }
                }
                buffer.force();
                buffer.clear();
            }

            //对余数部分加密
            buffer = channel.map(FileChannel.MapMode.READ_WRITE, multiples * CIPHER_BUFFER_LENGTH, multiples * CIPHER_BUFFER_LENGTH + remainder);

            for (int loopdata = 0; loopdata < remainder; ++loopdata)
			{
                rawByte = buffer.get(loopdata);
                temporary = (byte) (rawByte ^ loopdata);
                buffer.put(loopdata, temporary);

                if (listener != null)
				{
                    listener.onProgress(multiples * CIPHER_BUFFER_LENGTH + loopdata, totalLength);
                }
            }
            buffer.force();
            buffer.clear();

            channel.close();
            raf.close();

            //对加密后的文件重命名，增加.protectedFile-byTDOM后缀
			fileobject.renameTo(new File(fileobject.getPath() + CIPHER_TEXT_SUFFIX));
			
            Log.d("Used time from encrypt file：", (System.currentTimeMillis() - startTime) / 1000 + "s");
            return true;
        }
		catch (Exception e)
		{
            e.printStackTrace();
            return false;
        }
    }

	*/
	
	
    /**
     * 解密，这里主要是演示加密的原理，没有用什么实际的加密算法
     *
     * @param filePath 密文文件绝对路径，文件需要以.protectedFile-byTDOM结尾才会认为其实可解密密文
     * @return
     */
	 
	 /*
	 
    public static boolean dataToDecrypt(String filePath, CipherListener listener)
	{
        try
		{
            long startTime = System.currentTimeMillis();
            File fileobject = new File(filePath);
			//if(!f.getPath().endsWith(CIPHER_TEXT_SUFFIX))
			//{
			//后缀不同，认为是不可解密的密文
			//return false;
			//}

            RandomAccessFile raf = new RandomAccessFile(fileobject, "rw");
            long totalLength = raf.length();
            FileChannel channel = raf.getChannel();

            long multiples = totalLength / CIPHER_BUFFER_LENGTH;
            long remainder = totalLength % CIPHER_BUFFER_LENGTH;

            MappedByteBuffer buffer = null;
            byte temporary;
            byte rawByte;

            //先对整除部分解密
            for (int count = 0; count < multiples; count++)
			{
                buffer = channel.map(FileChannel.MapMode.READ_WRITE, count * CIPHER_BUFFER_LENGTH, (count + 1) * CIPHER_BUFFER_LENGTH);

                //此处的解密方法很简单，只是简单的异或计算
                for (int loopdata = 0; loopdata < CIPHER_BUFFER_LENGTH; ++loopdata)
				{
                    rawByte = buffer.get(loopdata);
                    temporary = (byte) (rawByte ^ loopdata);
                    buffer.put(loopdata, temporary);

                    if (listener != null)
					{
                        listener.onProgress(count * CIPHER_BUFFER_LENGTH + loopdata, totalLength);
                    }
                }
                buffer.force();
                buffer.clear();
            }

            //对余数部分解密
            buffer = channel.map(FileChannel.MapMode.READ_WRITE, multiples * CIPHER_BUFFER_LENGTH, multiples * CIPHER_BUFFER_LENGTH + remainder);

            for (int loopdata = 0; loopdata < remainder; ++loopdata)
			{
                rawByte = buffer.get(loopdata);
                temporary = (byte) (rawByte ^ loopdata);
                buffer.put(loopdata, temporary);

                if (listener != null)
				{
                    listener.onProgress(multiples * CIPHER_BUFFER_LENGTH + loopdata, totalLength);
                }
            }
            buffer.force();
            buffer.clear();

            channel.close();
            raf.close();

            //对解密后的文件重命名，减去.protectedFile-byTDOM后缀//
			fileobject.renameTo(new File(fileobject.getPath().substring(fileobject.getPath().indexOf(CIPHER_TEXT_SUFFIX))));

            Log.d("Used time from decrypt file：", (System.currentTimeMillis() - startTime) / 1000 + "s");
            return true;
        }
		catch (Exception e)
		{
            e.printStackTrace();
            return false;
        }
    }
	
	*/
	
	
    //用于加解密进度的监听器
	public interface CipherListener
	{
        void onProgress(long current, long total);
    }
}
