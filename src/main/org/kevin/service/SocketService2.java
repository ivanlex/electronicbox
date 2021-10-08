package org.kevin.service;

import org.kevin.dao.MCUOpInfoDao;
import org.kevin.service.interfaces.IRequest;
import org.kevin.service.interfaces.IResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

@Service
public class SocketService2 {
    MCUOpInfoDao mMCUOpInfoDao;

    private ServerSocket mServerSocket;
    private boolean mStarted;
    private Integer mPort = 2800;

    private ExecutorService threadPool = Executors.newCachedThreadPool();

    @Autowired
    public void setMCUOpInfoDao(MCUOpInfoDao MCUOpInfoDao) {
        mMCUOpInfoDao = MCUOpInfoDao;
    }

    public void start() {
        System.out.println("Running");
        start(2800);
    }

    /**
     * Start socket server by using specified port
     *
     * @param port specified port number
     */
    private void start(Integer port) {
        try {
            mServerSocket = new ServerSocket(port == null ? this.mPort : port);
            mStarted = true;

        } catch (IOException e) {
            System.exit(0);
        }

        while (mStarted) {
            try {
                Socket socket = mServerSocket.accept();
                Runnable runnable = () -> {
                    try{
                        System.out.println("Sockect client connected");
                        byte[] messages = onMessage(socket);
                        //IResponse response = handleUseCase(new MCURequest(msg));
                        //if (response != null)
                        //sendMessage(socket, response);
                        onProcessMessage(messages);
                        socket.close();
                    }
                    catch (IOException e)
                    {
                        e.printStackTrace();
                    }

                };
                Future future = threadPool.submit(runnable);
            } catch (IOException e) {
                e.printStackTrace();
            }


        }
    }

    /**
     * Retrieve socket messages
     *
     * @param socket working socket
     * @return message retrieved
     */
    private static byte[] onMessage(Socket socket) {
        byte[] content = new byte[200];
        int len;
        try {
            // after connection is established, get socket input stream from socket, read messages from buffer
            InputStream inputStream = socket.getInputStream();
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            if ((len = inputStream.read(content)) >0) {
                // please use same encoding between sender and receiver,recommended by using UTF-8 encoding.
                baos.write(content,0,len);
            }
            // shutdown server side output stream
            socket.shutdownInput();
            return baos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[]{};
    }

    void onProcessMessage(byte[] bytes){
        if(bytes.length != 6) {
            System.out.println("Message length is too low");
        }
        else
        {

            String mcuId = Integer.valueOf ((int)bytes[0]).toString();
            System.out.println("MCUId:" +  mcuId + "Lightning count : " + bytes[5]);
            mMCUOpInfoDao.updateMCUOpInfo(mcuId,bytes[1],bytes[2],bytes[3],bytes[4],bytes[5]);
        }
    }
}
